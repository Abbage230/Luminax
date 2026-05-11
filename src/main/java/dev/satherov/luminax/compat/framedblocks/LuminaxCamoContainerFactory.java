package dev.satherov.luminax.compat.framedblocks;

import dev.satherov.luminax.common.block.LuminaxBlock;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.luminax.core.LXProperties;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.neoforged.neoforge.transfer.access.ItemAccess;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import org.jspecify.annotations.Nullable;

import io.github.xfacthd.framedblocks.api.camo.CamoContainerFactory;
import io.github.xfacthd.framedblocks.api.camo.TriggerRegistrar;
import io.github.xfacthd.framedblocks.api.camo.block.AbstractBlockCamoContainerFactory;
import io.github.xfacthd.framedblocks.api.util.CamoMessageVerbosity;
import io.github.xfacthd.framedblocks.api.util.FramedConstants;

@NothingNull
public class LuminaxCamoContainerFactory extends AbstractBlockCamoContainerFactory<LuminaxCamoContainer> {
    
    private static final MapCodec<LuminaxCamoContainer> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockState.CODEC.fieldOf("state").forGetter(LuminaxCamoContainer::getState),
            Codec.INT.fieldOf("color").forGetter(LuminaxCamoContainer::getColor),
            Codec.BOOL.fieldOf("light").forGetter(LuminaxCamoContainer::isLight)
    ).apply(instance, LuminaxCamoContainer::new));
    
    private static final StreamCodec<RegistryFriendlyByteBuf, LuminaxCamoContainer> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.idMapper(Block.BLOCK_STATE_REGISTRY), LuminaxCamoContainer::getState,
            ByteBufCodecs.INT, LuminaxCamoContainer::getColor,
            ByteBufCodecs.BOOL, LuminaxCamoContainer::isLight,
            LuminaxCamoContainer::new
    );
    
    private static final int DEFAULT_TINT = 0xFFFFFF;
    
    @Override
    protected LuminaxCamoContainer createContainer(BlockState camoState, Level level, BlockPos blockPos, Player player, ItemAccess itemAccess) {
        return LuminaxCamoContainerFactory.createContainer(camoState, LuminaxWandItem.find(player));
    }
    
    private static LuminaxCamoContainer createContainer(BlockState camoState, ItemStack stack) {
        return new LuminaxCamoContainer(camoState, stack.getOrDefault(LXRegistry.COLOR, LuminaxCamoContainerFactory.DEFAULT_TINT), stack.getOrDefault(LXRegistry.GLOWING, false));
    }
    
    @Override
    protected @Nullable BlockState getStateFromItemStack(Level level, BlockPos pos, Player player, ItemAccess itemAccess) {
        if (itemAccess.getResource().getItem() instanceof BlockItem item) {
            return LuminaxCamoContainerFactory.applyWandModifiers(item.getBlock().defaultBlockState(), LuminaxWandItem.find(player));
        }
        return null;
    }
    
    private static BlockState applyWandModifiers(BlockState state, ItemStack wand) {
        if (!(state.getBlock() instanceof LuminaxBlock)) return state;
        return LXProperties.CONTAINER.applyToBlock(wand, state).state();
    }
    
    @Override
    protected LuminaxCamoContainer copyContainerWithState(LuminaxCamoContainer container, BlockState newCamoState) {
        return new LuminaxCamoContainer(newCamoState, container.getColor(), container.isLight());
    }
    
    @Override
    protected ItemStack createItemStack(Level level, BlockPos blockPos, Player player, ItemAccess itemAccess, LuminaxCamoContainer LuminaxCamoContainer) {
        return this.dropCamo(LuminaxCamoContainer);
    }
    
    @Override
    public ItemStack dropCamo(LuminaxCamoContainer LuminaxCamoContainer) {
        return new ItemStack(LuminaxCamoContainer.getState().getBlock());
    }
    
    @Override
    public LuminaxCamoContainer handleInteraction(Level level, BlockPos pos, Player player, LuminaxCamoContainer camo, ItemStack stack, InteractionHand hand) {
        if (stack.is(LXRegistry.LUMINAX_WAND)) {
            BlockState state = LuminaxCamoContainerFactory.applyWandModifiers(camo.getState(), stack);
            return LuminaxCamoContainerFactory.createContainer(state, stack);
        }
        return camo;
    }
    
    @Override
    protected boolean isValidBlock(BlockState camoState, BlockGetter level, BlockPos pos, @Nullable Player player) {
        if (!(camoState.getBlock() instanceof LuminaxBlock)) return false;
        if (camoState.is(FramedConstants.Tags.BLOCK_BLACKLIST)) {
            CamoContainerFactory.displayValidationMessage(player, CamoContainerFactory.MSG_BLACKLISTED, CamoMessageVerbosity.DEFAULT);
            return false;
        }
        return true;
    }
    
    @Override
    protected void writeToNetwork(ValueOutput output, LuminaxCamoContainer container) {
        output.putInt("state", Block.getId(container.getState()));
        output.putInt("tint", container.getColor());
        output.putBoolean("light", container.isLight());
    }
    
    @Override
    protected LuminaxCamoContainer readFromNetwork(ValueInput input) {
        final BlockState state = Block.stateById(input.getInt("state").orElseGet(() -> Block.getId(Blocks.AIR.defaultBlockState())));
        final int tint = input.getInt("tint").orElse(LuminaxCamoContainerFactory.DEFAULT_TINT);
        final boolean light = input.getBooleanOr("light", false);
        return new LuminaxCamoContainer(state, tint, light);
    }
    
    @Override
    public boolean canTriviallyConvertToItemStack() {
        return true;
    }
    
    @Override
    public MapCodec<LuminaxCamoContainer> codec() {
        return LuminaxCamoContainerFactory.CODEC;
    }
    
    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, LuminaxCamoContainer> streamCodec() {
        return LuminaxCamoContainerFactory.STREAM_CODEC;
    }
    
    @Override
    public void registerTriggerItems(TriggerRegistrar registrar) {
        registrar.registerApplicationItem(LXRegistry.BLOCK.get().asItem());
        registrar.registerRemovalItem(FramedConstants.Objects.FRAMED_HAMMER.value());
    }
}

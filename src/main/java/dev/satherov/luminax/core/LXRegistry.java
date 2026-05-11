package dev.satherov.luminax.core;

import lombok.experimental.UtilityClass;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.client.lang.LXLanguage;
import dev.satherov.luminax.common.block.LuminaxBlock;
import dev.satherov.luminax.common.block.LuminaxBlockEntity;
import dev.satherov.luminax.common.block.LuminaxButton;
import dev.satherov.luminax.common.block.LuminaxPressurePlate;
import dev.satherov.luminax.common.block.LuminaxSlab;
import dev.satherov.luminax.common.block.LuminaxStair;
import dev.satherov.luminax.common.block.LuminaxWall;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.sathlib.common.block.SLBlockProperties;
import dev.satherov.sathlib.common.item.SLItemProperties;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import com.mojang.serialization.Codec;

import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public final class LXRegistry {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Luminax.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Luminax.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Luminax.MOD_ID);
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Luminax.MOD_ID);
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Luminax.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Luminax.MOD_ID);
    public static final Supplier<DataComponentType<Integer>> COLOR = LXRegistry.COMPONENTS.register("color", () ->
            DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT)
                    .build()
    );
    public static final Supplier<DataComponentType<Boolean>> GLOWING = LXRegistry.COMPONENTS.register("glowing", () ->
            DataComponentType.<Boolean>builder()
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL)
                    .build()
    );
    public static final DeferredHolder<Item, LuminaxWandItem> LUMINAX_WAND = LXRegistry.ITEMS.register("luminax_wand", k -> new LuminaxWandItem(SLItemProperties.create(k)));
    public static final DeferredHolder<Block, LuminaxBlock> BLOCK = LXRegistry.register("luminax_block", LuminaxBlock::new);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = LXRegistry.TABS.register("creative_tab", () -> CreativeModeTab.builder()
            .title(LXLanguage.CREATIVE_TAB_DEFAULT.translate())
            .icon(() -> LXRegistry.BLOCK.get().asItem().getDefaultInstance())
            .displayItems((_, out) -> LXRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).forEach(out::accept))
            .build()
    );
    public static final DeferredHolder<Block, LuminaxStair> STAIRS = LXRegistry.register("luminax_stair", LuminaxStair::new);
    public static final DeferredHolder<Block, LuminaxSlab> SLAB = LXRegistry.register("luminax_slab", LuminaxSlab::new);
    public static final DeferredHolder<Block, LuminaxWall> WALL = LXRegistry.register("luminax_wall", LuminaxWall::new);
    public static final DeferredHolder<Block, LuminaxPressurePlate> PRESSURE_PLATE = LXRegistry.register("luminax_pressure_plate", LuminaxPressurePlate::new);
    public static final DeferredHolder<Block, LuminaxButton> BUTTON = LXRegistry.register("luminax_button", LuminaxButton::new);
    
    private static <T extends Block> DeferredHolder<Block, T> register(String name, Function<BlockBehaviour.Properties, T> factory) {
        DeferredHolder<Block, T> holder = LXRegistry.BLOCKS.register(name, k -> factory.apply(SLBlockProperties.ofFullCopy(Blocks.STONE).setId(ResourceKey.create(Registries.BLOCK, k))));
        LXRegistry.ITEMS.register(name, k -> new BlockItem(holder.get(), SLItemProperties.create(k).useBlockDescriptionPrefix()));
        return holder;
    }    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LuminaxBlockEntity>> BLOCK_ENTITY = LXRegistry.BLOCK_ENTITIES.register("glass_tile", () ->
            new BlockEntityType<>(LuminaxBlockEntity::new,
                    LXRegistry.BLOCKS.getEntries().stream()
                            .map(DeferredHolder::get)
                            .toArray(Block[]::new)
            ));
    
    public static void register(IEventBus bus) {
        LXRegistry.BLOCKS.register(bus);
        LXRegistry.ITEMS.register(bus);
        LXRegistry.TABS.register(bus);
        LXRegistry.COMPONENTS.register(bus);
        LXRegistry.ATTACHMENTS.register(bus);
        LXRegistry.BLOCK_ENTITIES.register(bus);
    }
    

}

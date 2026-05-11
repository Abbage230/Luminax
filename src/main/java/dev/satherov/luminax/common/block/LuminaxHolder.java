package dev.satherov.luminax.common.block;

import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.luminax.core.LXRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Objects;

public interface LuminaxHolder {
    
    private Block self() {
        return (Block) this;
    }
    
    default BlockState getStateForPlacement(BlockPlaceContext context, BlockState fallback) {
        Player player = context.getPlayer();
        if (player == null) return fallback;
        
        ItemStack stack = player.getOffhandItem();
        if (stack.getItem() instanceof LuminaxWandItem) {
            return fallback.setValue(BlockStateProperties.LIT, Objects.requireNonNull(stack.get(LXRegistry.GLOWING)));
        }
        
        return fallback;
    }
    
    default int getLightBlock(BlockState state, BlockGetter level) {
        return state.getValue(BlockStateProperties.LIT) ? 15 : 0;
    }
    
    default void setPlacedBy(Level level, BlockPos pos, ServerPlayer player) {
        ItemStack wand = LuminaxWandItem.find(player);
        if (wand.isEmpty()) return;
        if (level.getBlockEntity(pos) instanceof LuminaxBlockEntity tile) {
            tile.setColor(Objects.requireNonNull(wand.get(LXRegistry.COLOR)));
        }
    }
}

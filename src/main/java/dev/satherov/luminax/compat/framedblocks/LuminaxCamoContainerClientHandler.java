package dev.satherov.luminax.compat.framedblocks;

import dev.satherov.sathlib.core.annotations.NothingNull;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import io.github.xfacthd.framedblocks.api.camo.CamoContainerClientHandler;
import io.github.xfacthd.framedblocks.api.camo.block.BlockCamoContent;
import it.unimi.dsi.fastutil.ints.IntList;

@NothingNull
public final class LuminaxCamoContainerClientHandler extends CamoContainerClientHandler<BlockCamoContent, LuminaxCamoContainer> {
    
    public static final CamoContainerClientHandler<BlockCamoContent, LuminaxCamoContainer> INSTANCE = new LuminaxCamoContainerClientHandler();
    
    @Override
    public int getTintCount(LuminaxCamoContainer container) {
        return container.getColor();
    }
    
    @Override
    public void collectTintValues(LuminaxCamoContainer container, BlockAndTintGetter blockAndTintGetter, BlockPos blockPos, IntList intList) {
        intList.add(container.getColor() | 0xFF000000);
    }
    
    @Override
    public void collectTintValues(LuminaxCamoContainer container, ItemStack itemStack, IntList intList) {
        intList.add(container.getColor() | 0xFF000000);
    }
}

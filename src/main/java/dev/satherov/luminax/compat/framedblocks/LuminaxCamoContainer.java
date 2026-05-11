package dev.satherov.luminax.compat.framedblocks;

import lombok.Getter;

import dev.satherov.sathlib.core.annotations.NothingNull;

import net.minecraft.world.level.block.state.BlockState;

import io.github.xfacthd.framedblocks.api.camo.CamoContainerClientHandler;
import io.github.xfacthd.framedblocks.api.camo.block.AbstractBlockCamoContainer;
import io.github.xfacthd.framedblocks.api.camo.block.AbstractBlockCamoContainerFactory;
import io.github.xfacthd.framedblocks.api.camo.block.BlockCamoContent;

@NothingNull
public class LuminaxCamoContainer extends AbstractBlockCamoContainer<LuminaxCamoContainer> {
    
    private final @Getter int color;
    private final @Getter boolean light;
    
    protected LuminaxCamoContainer(BlockState state, int color, boolean light) {
        super(state);
        this.color = color;
        this.light = light;
    }
    
    @Override
    public int hashCode() {
        int result = this.content.hashCode();
        result = 31 * result + Integer.hashCode(this.color);
        result = 31 * result + Boolean.hashCode(this.light);
        return result;
    }
    
    @Override
    public AbstractBlockCamoContainerFactory<LuminaxCamoContainer> getFactory() {
        return LXFramedBlocksCompat.CRYSTALIX_GLASS_CAMO_FACTORY.get();
    }
    
    @Override
    public CamoContainerClientHandler<BlockCamoContent, LuminaxCamoContainer> getClientHandler() {
        return LuminaxCamoContainerClientHandler.INSTANCE;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof LuminaxCamoContainer other)) return false;
        return this.content.equals(other.content) && this.color == other.color && this.light == other.light;
    }
    
    @Override
    public String toString() {
        return "LuminaxCamoContainer{" + "content=" + this.content + ", color=" + this.color + ", light=" + this.light + '}';
    }
}

package dev.satherov.luminax.data.provider.tags;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.neoforged.neoforge.common.data.BlockTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

@NothingNull
public class LXBlockTagsProvider extends BlockTagsProvider {
    
    public LXBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Luminax.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        LXRegistry.BLOCKS.getEntries().forEach(entry -> this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(entry.get()));
        this.tag(BlockTags.STAIRS).add(LXRegistry.STAIRS.get());
        this.tag(BlockTags.SLABS).add(LXRegistry.SLAB.get());
        this.tag(BlockTags.WALLS).add(LXRegistry.WALL.get());
        this.tag(BlockTags.BUTTONS).add(LXRegistry.BUTTON.get());
        this.tag(BlockTags.PRESSURE_PLATES).add(LXRegistry.PRESSURE_PLATE.get());
    }
}

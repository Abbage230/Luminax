package dev.satherov.luminax.data.provider.tags;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.neoforged.neoforge.common.data.ItemTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

@NothingNull
public class LXItemTagsProvider extends ItemTagsProvider {
    
    public LXItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, Luminax.MOD_ID);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ItemTags.STAIRS).add(LXRegistry.STAIRS.get().asItem());
        this.tag(ItemTags.SLABS).add(LXRegistry.SLAB.get().asItem());
        this.tag(ItemTags.WALLS).add(LXRegistry.WALL.get().asItem());
        this.tag(ItemTags.BUTTONS).add(LXRegistry.BUTTON.get().asItem());
    }
}

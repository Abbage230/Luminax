package com.satherov.luminax.datagen.data.tags;

import net.neoforged.neoforge.common.Tags;

import com.satherov.luminax.content.BlockSet;
import com.satherov.luminax.content.LuminaxRegistry;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class LuminaxItemTagProvider extends ItemTagsProvider {

    public LuminaxItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SLABS)
                .addTag(LuminaxRegistry.ITEMTAG_SLAB)
                .addTag(LuminaxRegistry.ITEMTAG_DIM_SLAB);

        tag(ItemTags.STAIRS)
                .addTag(LuminaxRegistry.ITEMTAG_STAIRS)
                .addTag(LuminaxRegistry.ITEMTAG_DIM_STAIRS);

        tag(ItemTags.WALLS)
                .addTag(LuminaxRegistry.ITEMTAG_WALL)
                .addTag(LuminaxRegistry.ITEMTAG_DIM_WALL);

        tag(ItemTags.BUTTONS)
                .addTag(LuminaxRegistry.ITEMTAG_BUTTON)
                .addTag(LuminaxRegistry.ITEMTAG_DIM_BUTTON);

        BlockSet.apply(set -> {
            tag(LuminaxRegistry.ITEMTAG_BLOCK).add(set.BLOCK.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_BLOCK).add(set.DIM_BLOCK.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_SLAB).add(set.SLAB.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_SLAB).add(set.DIM_SLAB.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_STAIRS).add(set.STAIRS.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_STAIRS).add(set.DIM_STAIRS.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_WALL).add(set.WALL.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_WALL).add(set.DIM_WALL.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_PRESSURE_PLATE).add(set.PRESSURE_PLATE.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_PRESSURE_PLATE).add(set.DIM_PRESSURE_PLATE.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_BUTTON).add(set.BUTTON.get().asItem());
            tag(LuminaxRegistry.ITEMTAG_DIM_BUTTON).add(set.DIM_BUTTON.get().asItem());
        });
    }
}

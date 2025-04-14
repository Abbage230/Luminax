package com.satherov.luminax.datagen.data.tags;

import com.satherov.luminax.Luminax;
import com.satherov.luminax.content.BlockSet;
import com.satherov.luminax.content.LuminaxRegistry;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class LuminaxBlockTagProvider extends BlockTagsProvider {

    public LuminaxBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Luminax.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(LuminaxRegistry.BLOCKTAG_BLOCK)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_BLOCK)
                .addTag(LuminaxRegistry.BLOCKTAG_SLAB)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_SLAB)
                .addTag(LuminaxRegistry.BLOCKTAG_STAIRS)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_STAIRS)
                .addTag(LuminaxRegistry.BLOCKTAG_WALL)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_WALL)
                .addTag(LuminaxRegistry.BLOCKTAG_PRESSURE_PLATE)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_PRESSURE_PLATE);

        tag(BlockTags.SLABS)
                .addTag(LuminaxRegistry.BLOCKTAG_SLAB)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_SLAB);

        tag(BlockTags.STAIRS)
                .addTag(LuminaxRegistry.BLOCKTAG_STAIRS)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_STAIRS);

        tag(BlockTags.WALLS)
                .addTag(LuminaxRegistry.BLOCKTAG_WALL)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_WALL);

        tag(BlockTags.BUTTONS)
                .addTag(LuminaxRegistry.BLOCKTAG_BUTTON)
                .addTag(LuminaxRegistry.BLOCKTAG_DIM_BUTTON);

        BlockSet.apply(set -> {
            tag(LuminaxRegistry.BLOCKTAG_BLOCK).add(set.BLOCK.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_BLOCK).add(set.DIM_BLOCK.get());
            tag(LuminaxRegistry.BLOCKTAG_SLAB).add(set.SLAB.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_SLAB).add(set.DIM_SLAB.get());
            tag(LuminaxRegistry.BLOCKTAG_STAIRS).add(set.STAIRS.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_STAIRS).add(set.DIM_STAIRS.get());
            tag(LuminaxRegistry.BLOCKTAG_WALL).add(set.WALL.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_WALL).add(set.DIM_WALL.get());
            tag(LuminaxRegistry.BLOCKTAG_PRESSURE_PLATE).add(set.PRESSURE_PLATE.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_PRESSURE_PLATE).add(set.DIM_PRESSURE_PLATE.get());
            tag(LuminaxRegistry.BLOCKTAG_BUTTON).add(set.BUTTON.get());
            tag(LuminaxRegistry.BLOCKTAG_DIM_BUTTON).add(set.DIM_BUTTON.get());
        });
    }
}

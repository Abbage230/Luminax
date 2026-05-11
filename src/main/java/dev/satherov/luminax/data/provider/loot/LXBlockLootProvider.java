package dev.satherov.luminax.data.provider.loot;

import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

@NothingNull
public class LXBlockLootProvider extends BlockLootSubProvider {
    
    protected LXBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return LXRegistry.BLOCKS.getEntries().stream().map(Holder::value).toList();
    }
    
    @Override
    protected void generate() {
        LXRegistry.BLOCKS.getEntries().forEach(block -> this.dropSelf(block.get()));
    }
}

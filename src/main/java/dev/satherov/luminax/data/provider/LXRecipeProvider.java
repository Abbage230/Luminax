package dev.satherov.luminax.data.provider;

import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.neoforged.neoforge.common.Tags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

@NothingNull
public class LXRecipeProvider extends RecipeProvider {
    
    private final HolderGetter<Item> items;
    private final RecipeOutput output;
    
    protected LXRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
        this.output = output;
    }
    
    @Override
    protected void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.LUMINAX_WAND.get().asItem())
                .pattern(" gd")
                .pattern(" sg")
                .pattern("s  ")
                .define('s', Items.STICK)
                .define('g', LXRegistry.BLOCK.get())
                .define('d', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond", this.has(Tags.Items.GEMS_DIAMOND))
                .save(this.output);
        
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.BLOCK.get(), 4)
                .pattern("aga")
                .pattern("gbg")
                .pattern("aga")
                .define('a', Tags.Items.STONES)
                .define('b', Tags.Items.DYES_WHITE)
                .define('g', Tags.Items.DUSTS_GLOWSTONE)
                .unlockedBy("has_stone", this.has(Tags.Items.STONES))
                .save(this.output);
        
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.SLAB.get(), 6)
                .pattern("aaa")
                .define('a', LXRegistry.BLOCK.get())
                .unlockedBy("has_luminax_block", this.has(LXRegistry.BLOCK.get()))
                .save(this.output);
        
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.STAIRS.get(), 4)
                .pattern("a  ")
                .pattern("aa ")
                .pattern("aaa")
                .define('a', LXRegistry.BLOCK.get())
                .unlockedBy("has_luminax_block", this.has(LXRegistry.BLOCK.get()))
                .save(this.output);
        
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.WALL.get(), 6)
                .pattern("aaa")
                .pattern("aaa")
                .define('a', LXRegistry.BLOCK.get())
                .unlockedBy("has_luminax_block", this.has(LXRegistry.BLOCK.get()))
                .save(this.output);
        
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, LXRegistry.PRESSURE_PLATE.get(), 1)
                .pattern("aa")
                .define('a', LXRegistry.BLOCK.get())
                .unlockedBy("has_luminax_block", this.has(LXRegistry.BLOCK.get()))
                .save(this.output);
        
        ShapelessRecipeBuilder.shapeless(this.items, RecipeCategory.MISC, LXRegistry.BUTTON.get(), 1)
                .requires(LXRegistry.PRESSURE_PLATE.get())
                .unlockedBy("has_luminax_block", this.has(LXRegistry.BLOCK.get()))
                .save(this.output);
        
    }
    
    
    public static class Runner extends RecipeProvider.Runner {
        
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }
        
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
            return new LXRecipeProvider(lookupProvider, output);
        }
        
        @Override
        public String getName() {
            return "Luminax recipes";
        }
    }
}

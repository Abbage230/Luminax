package com.satherov.luminax;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.satherov.luminax.content.BlockSet;
import com.satherov.luminax.content.LuminaxRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

@Mod(Luminax.MOD_ID)
public class Luminax
{
    public static final String MOD_ID = "luminax";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public Luminax()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        LuminaxRegistry.CREATIVE_TABS.register(modEventBus);
        LuminaxRegistry.BLOCKS.register(modEventBus);
        LuminaxRegistry.ITEMS.register(modEventBus);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class DataFixer {
        @SuppressWarnings("UnstableApiUsage")
        @SubscribeEvent
        public static void registerAliases(FMLConstructModEvent event) {
            BlockSet.apply(set -> {
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_block", set.name)), set.BLOCK.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_slab", set.name)), set.SLAB.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_stairs", set.name)), set.STAIRS.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_wall", set.name)), set.WALL.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_pressure_plate", set.name)), set.PRESSURE_PLATE.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_button", set.name)), set.BUTTON.getId());

                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_block", set.name)), set.DIM_BLOCK.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_slab", set.name)), set.DIM_SLAB.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_stairs", set.name)), set.DIM_STAIRS.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_wall", set.name)), set.DIM_WALL.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_pressure_plate", set.name)), set.DIM_PRESSURE_PLATE.getId());
                ((ForgeRegistry<Item>) ForgeRegistries.ITEMS).addAlias(prefix(String.format("%s_dim_button", set.name)), set.DIM_BUTTON.getId());

                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_block", set.name)), set.BLOCK.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_slab", set.name)), set.SLAB.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_stairs", set.name)), set.STAIRS.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_wall", set.name)), set.WALL.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_pressure_plate", set.name)), set.PRESSURE_PLATE.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_button", set.name)), set.BUTTON.getId());

                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_block", set.name)), set.DIM_BLOCK.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_slab", set.name)), set.DIM_SLAB.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_stairs", set.name)), set.DIM_STAIRS.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_wall", set.name)), set.DIM_WALL.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_pressure_plate", set.name)), set.DIM_PRESSURE_PLATE.getId());
                ((ForgeRegistry<Block>) ForgeRegistries.BLOCKS).addAlias(prefix(String.format("%s_dim_button", set.name)), set.DIM_BUTTON.getId());
            });
        }
    }
}

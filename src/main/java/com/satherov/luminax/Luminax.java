package com.satherov.luminax;

import com.satherov.luminax.content.BlockSet;
import com.satherov.luminax.content.LuminaxRegistry;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@Mod(Luminax.MOD_ID)
public class Luminax
{
    public static final String MOD_ID = "luminax";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix (String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public Luminax(IEventBus modEventBus, ModContainer modContainer)
    {
        LuminaxRegistry.BLOCKS.register(modEventBus);
        LuminaxRegistry.ITEMS.register(modEventBus);
        LuminaxRegistry.CREATIVE_TABS.register(modEventBus);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    private static class DataFixer {
        @SubscribeEvent
            public static void registerAliases(FMLLoadCompleteEvent event) {

            BlockSet.apply(set -> {
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_block", set.name)), set.BLOCK.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_slab", set.name)), set.SLAB.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_stairs", set.name)), set.STAIRS.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_wall", set.name)), set.WALL.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_pressure_plate", set.name)), set.PRESSURE_PLATE.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_button", set.name)), set.BUTTON.getId());

                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_block", set.name)), set.DIM_BLOCK.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_slab", set.name)), set.DIM_SLAB.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_stairs", set.name)), set.DIM_STAIRS.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_wall", set.name)), set.DIM_WALL.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_pressure_plate", set.name)), set.DIM_PRESSURE_PLATE.getId());
                BuiltInRegistries.ITEM.addAlias(prefix(String.format("%s_dim_button", set.name)), set.DIM_BUTTON.getId());

                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_block", set.name)), set.BLOCK.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_slab", set.name)), set.SLAB.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_stairs", set.name)), set.STAIRS.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_wall", set.name)), set.WALL.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_pressure_plate", set.name)), set.PRESSURE_PLATE.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_button", set.name)), set.BUTTON.getId());

                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_block", set.name)), set.DIM_BLOCK.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_slab", set.name)), set.DIM_SLAB.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_stairs", set.name)), set.DIM_STAIRS.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_wall", set.name)), set.DIM_WALL.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_pressure_plate", set.name)), set.DIM_PRESSURE_PLATE.getId());
                BuiltInRegistries.BLOCK.addAlias(prefix(String.format("%s_dim_button", set.name)), set.DIM_BUTTON.getId());
            });
        }
    }
}

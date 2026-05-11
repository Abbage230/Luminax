package dev.satherov.luminax.data;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.data.provider.LXLanguageProvider;
import dev.satherov.luminax.data.provider.LXModelProvider;
import dev.satherov.luminax.data.provider.LXRecipeProvider;
import dev.satherov.luminax.data.provider.loot.LXLootProvider;
import dev.satherov.luminax.data.provider.tags.LXBlockTagsProvider;
import dev.satherov.luminax.data.provider.tags.LXItemTagsProvider;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Luminax.MOD_ID)
public class LXDataGenerator {
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        if (!Luminax.MOD_ID.equalsIgnoreCase(event.getModContainer().getModId())) return;
        
        event.createProvider(LXLanguageProvider::new);
        event.createProvider(LXModelProvider::new);
        
        event.createProvider(LXBlockTagsProvider::new);
        event.createProvider(LXItemTagsProvider::new);
        event.createProvider(LXLootProvider::create);
        event.createProvider(LXRecipeProvider.Runner::new);
    }
}

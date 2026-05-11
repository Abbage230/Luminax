package dev.satherov.luminax;

import dev.satherov.luminax.compat.framedblocks.LXFramedBlocksCompat;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.luminax.network.SetColorPayload;
import dev.satherov.luminax.network.ToggleGlowing;
import dev.satherov.sathlib.compat.Mods;
import dev.satherov.sathlib.config.SLConfigLoader;
import dev.satherov.sathlib.network.handling.SLNetworkManager;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;

import net.minecraft.resources.Identifier;

@Mod(Luminax.MOD_ID)
public class Luminax {
    
    public static final String MOD_ID = "luminax";
    
    public static final SLNetworkManager NETWORK = SLNetworkManager.create(Luminax.MOD_ID);
    
    public Luminax(final IEventBus bus, final FMLModContainer container) {
        SLConfigLoader.discover(container);
        LXRegistry.register(bus);
        Luminax.NETWORK.add(new ToggleGlowing.Provider());
        Luminax.NETWORK.add(new SetColorPayload.Provider());
        Luminax.NETWORK.register(bus);
        
        Mods.FRAMED_BLOCKS.run(() -> LXFramedBlocksCompat.register(bus));
    }
    
    public static Identifier id(final String path) {
        return Identifier.fromNamespaceAndPath(Luminax.MOD_ID, path);
    }
}

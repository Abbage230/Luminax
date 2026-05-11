package dev.satherov.luminax.compat.framedblocks;

import dev.satherov.luminax.Luminax;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import io.github.xfacthd.framedblocks.api.camo.CamoContainerFactory;
import io.github.xfacthd.framedblocks.api.util.FramedConstants;

public class LXFramedBlocksCompat {
    
    private static final DeferredRegister<CamoContainerFactory<?>> CAMO_FACTORIES = DeferredRegister.create(FramedConstants.Registries.CAMO_CONTAINER_FACTORY_REGISTRY_KEY, Luminax.MOD_ID);
    
    protected static final DeferredHolder<CamoContainerFactory<?>, LuminaxCamoContainerFactory> CRYSTALIX_GLASS_CAMO_FACTORY = LXFramedBlocksCompat.CAMO_FACTORIES.register(
            "luminax_block", LuminaxCamoContainerFactory::new
    );
    
    public static void register(IEventBus modBus) {
        LXFramedBlocksCompat.CAMO_FACTORIES.register(modBus);
    }
}

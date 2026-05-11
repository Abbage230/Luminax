package dev.satherov.luminax.client.screen;

import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.luminax.network.SetColorPayload;
import dev.satherov.sathlib.client.screen.ColorPickerScreen;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.item.ItemStack;

public class LXColorScreen extends ColorPickerScreen {
    
    public LXColorScreen(ItemStack stack) {
        super(stack.getOrDefault(LXRegistry.COLOR, 0xFFFFFF), value -> {
            stack.set(LXRegistry.COLOR, value);
            ClientPacketDistributor.sendToServer(new SetColorPayload(value));
        });
    }
}

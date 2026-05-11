package dev.satherov.luminax.network;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.client.lang.LXLanguage;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;
import dev.satherov.sathlib.network.chat.SLComponent;
import dev.satherov.sathlib.network.handling.SLPayload;
import dev.satherov.sathlib.network.handling.ServerPayloadProvider;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

@NothingNull
public record ToggleGlowing(boolean enabled) implements SLPayload<ToggleGlowing> {
    
    private static final Type<ToggleGlowing> TYPE = SLPayload.type(Luminax.id("toggle_glowing"));
    
    private static final StreamCodec<RegistryFriendlyByteBuf, ToggleGlowing> STREAM_CODEC = SLPayload.codec(ToggleGlowing::encode, ToggleGlowing::new);
    
    @Override
    public Type<ToggleGlowing> type() {
        return ToggleGlowing.TYPE;
    }
    
    @Override
    public void encode(RegistryFriendlyByteBuf buf) {
        buf.writeBoolean(this.enabled);
    }
    
    private ToggleGlowing(RegistryFriendlyByteBuf buf) {
        this(buf.readBoolean());
    }
    
    public static final class Provider implements ServerPayloadProvider<ToggleGlowing> {
        
        @Override
        public void handle(ToggleGlowing payload, IPayloadContext context, ServerPlayer player) {
            final ItemStack stack = LuminaxWandItem.find(player);
            if (stack.isEmpty()) return;
            
            stack.set(LXRegistry.GLOWING, payload.enabled());
            player.sendSystemMessage(
                    Component.empty()
                            .append(LXLanguage.PROPERTY_GLOWING.translate(ChatFormatting.GRAY))
                            .append(": ")
                            .append(SLComponent.enabledDisabled(payload.enabled())),
                    true
            );
            
            player.getInventory().setChanged();
        }
        
        @Override
        public SLPayload.Type<ToggleGlowing> type() {
            return ToggleGlowing.TYPE;
        }
        
        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ToggleGlowing> codec() {
            return ToggleGlowing.STREAM_CODEC;
        }
    }
}

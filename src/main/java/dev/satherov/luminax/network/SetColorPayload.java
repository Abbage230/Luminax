package dev.satherov.luminax.network;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.luminax.core.LXProperties;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;
import dev.satherov.sathlib.network.handling.SLPayload;
import dev.satherov.sathlib.network.handling.ServerPayloadProvider;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

@NothingNull
public record SetColorPayload(int color) implements SLPayload<SetColorPayload> {
    
    private static final Type<SetColorPayload> TYPE = SLPayload.type(Luminax.id("set_color"));
    
    private static final StreamCodec<RegistryFriendlyByteBuf, SetColorPayload> STREAM_CODEC = SLPayload.codec(SetColorPayload::encode, SetColorPayload::new);
    
    @Override
    public Type<SetColorPayload> type() {
        return SetColorPayload.TYPE;
    }
    
    @Override
    public void encode(RegistryFriendlyByteBuf buf) {
        buf.writeInt(this.color);
    }
    
    private SetColorPayload(RegistryFriendlyByteBuf buf) {
        this(buf.readInt());
    }
    
    public static final class Provider implements ServerPayloadProvider<SetColorPayload> {
        
        @Override
        public void handle(SetColorPayload payload, IPayloadContext context, ServerPlayer player) {
            final ItemStack stack = LuminaxWandItem.find(player);
            if (stack.isEmpty()) return;
            
            LXProperties.COLOR.applyValueItem(payload.color(), stack, LXRegistry.BLOCK.get().defaultBlockState());
            player.getInventory().setChanged();
        }
        
        @Override
        public SLPayload.Type<SetColorPayload> type() {
            return SetColorPayload.TYPE;
        }
        
        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, SetColorPayload> codec() {
            return SetColorPayload.STREAM_CODEC;
        }
    }
}

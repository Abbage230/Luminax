package dev.satherov.luminax;

import dev.satherov.luminax.client.lang.LXLanguage;
import dev.satherov.luminax.client.screen.LXColorScreen;
import dev.satherov.luminax.common.block.LuminaxBlockEntity;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.luminax.network.ToggleGlowing;
import dev.satherov.sathlib.client.input.SLKeybindManager;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import org.lwjgl.glfw.GLFW;

import java.util.List;

@NothingNull
@Mod(value = Luminax.MOD_ID, dist = Dist.CLIENT)
public class LuminaxClient {
    
    public static final SLKeybindManager KEYBINDS = SLKeybindManager.create(Luminax.MOD_ID, Luminax.id("default"));
    
    private static final List<BlockTintSource> LUMINAX_BLOCK_TINT = List.of(new BlockTintSource() {
        
        @Override
        public int color(BlockState state) {
            return ARGB.opaque(0xFFFFFF);
        }
        
        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            LuminaxBlockEntity entity = LXRegistry.BLOCK_ENTITY.get().getBlockEntity(level, pos);
            return ARGB.opaque(entity != null ? entity.getColor() : 0xFFFFFF);
        }
    });
    
    public static final KeyMapping TOGGLE_GLOWING = LuminaxClient.KEYBINDS.add(LXLanguage.KEY_TOGGLE_GLOWING, GLFW.GLFW_KEY_X, event -> {
        final Minecraft mc = Minecraft.getInstance();
        final Player player = mc.player;
        if (mc.level == null || player == null || mc.screen != null) return;
        
        final ItemStack stack = LuminaxWandItem.find(player);
        if (stack.isEmpty()) return;
        
        if (event.getAction() == GLFW.GLFW_PRESS) {
            boolean enabled = !stack.getOrDefault(LXRegistry.GLOWING, false);
            stack.set(LXRegistry.GLOWING, enabled);
            ClientPacketDistributor.sendToServer(new ToggleGlowing(enabled));
        }
    });
    
    public static final KeyMapping OPEN_COLOR_PICKER = LuminaxClient.KEYBINDS.add(LXLanguage.KEY_OPEN_COLOR_PICKER, GLFW.GLFW_KEY_V, event -> {
        final Minecraft mc = Minecraft.getInstance();
        final Player player = mc.player;
        if (mc.level == null || player == null || mc.screen != null) return;
        
        final ItemStack stack = LuminaxWandItem.find(player);
        if (stack.isEmpty()) return;
        
        if (event.getAction() == GLFW.GLFW_PRESS) {
            if (mc.screen instanceof LXColorScreen) mc.setScreen(null);
            else mc.setScreen(new LXColorScreen(stack));
        }
    });
    
    public LuminaxClient(final IEventBus bus, final FMLModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        bus.addListener(RegisterColorHandlersEvent.BlockTintSources.class, event -> {
            LXRegistry.BLOCKS.getEntries().forEach(block -> event.register(LuminaxClient.LUMINAX_BLOCK_TINT, block.get()));
        });
        LuminaxClient.KEYBINDS.register(bus);
    }
}

package dev.satherov.luminax.client.lang;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import dev.satherov.luminax.Luminax;
import dev.satherov.sathlib.client.lang.SLTranslatable;

import net.minecraft.util.Util;

import java.util.function.BiConsumer;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum LXLanguage implements SLTranslatable {
    // @formatter:off
    CREATIVE_TAB_DEFAULT   ("creative_tab", "default",           "Luminax"),
                                                                
    PROPERTY_COLOR         ("property",     "color",             "Color"),
    PROPERTY_COLOR_TOOLTIP ("property",     "color.tooltip",     "Changes the color of the block"),
                                                                
    PROPERTY_GLOWING       ("property",     "glowing",           "Glowing"),
    TOOLTIP_GLOWING_ON     ("tooltip",      "glowing.on",        "Makes the block emit light"),
    TOOLTIP_GLOWING_OFF    ("tooltip",      "glowing.off",       "Makes the block emit light"),
    
    KEY_CATEGORY           ("key.category", "default",           "Luminax"),
    KEY_TOGGLE_GLOWING     ("key",          "toggle_glowing",    "Toggle Glowing"),
    KEY_OPEN_COLOR_PICKER  ("key",          "open_color_picker", "Open Color Picker"),
    
    TOOLTIP_MMB            ("tooltip",      "mmb",               "%s to open color picker"),
    TOOLTIP_LMB            ("tooltip",      "lmb",               "%s or %s to cycle forward"),
    TOOLTIP_RMB            ("tooltip",      "rmb",               "%s or %s to cycle backward"),
    // @formatter:on
    ;
    
    private final String key;
    private final String translation;
    
    LXLanguage(String type, String key, String translation) {
        this(Util.makeDescriptionId(type, Luminax.id(key)), translation);
    }
    
    public static void translate(BiConsumer<String, String> consumer) {
        for (LXLanguage lang : LXLanguage.values()) {
            consumer.accept(lang.key(), lang.translation());
        }
    }
}

package dev.satherov.luminax.data.provider;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.client.lang.LXLanguage;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.config.SLConfigLoader;
import dev.satherov.sathlib.core.annotations.NothingNull;
import dev.satherov.sathlib.util.SLStringUtils;

import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.data.LanguageProvider;

import net.minecraft.data.PackOutput;

@NothingNull
public class LXLanguageProvider extends LanguageProvider {
    
    public LXLanguageProvider(PackOutput output) {
        super(output, Luminax.MOD_ID, "en_us");
    }
    
    @Override
    protected void addTranslations() {
        LXLanguage.translate(this::add);
        SLConfigLoader.translate(ModList.get().getModContainerById(Luminax.MOD_ID).orElseThrow(), this::add);
        LXRegistry.BLOCKS.getEntries().forEach(block -> this.addBlock(block, SLStringUtils.toTitleCase(block.getId().getPath())));
        this.addItem(LXRegistry.LUMINAX_WAND, "Luminax Wand");
    }
}

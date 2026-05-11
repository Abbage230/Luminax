package dev.satherov.luminax.core;

import lombok.experimental.UtilityClass;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.client.lang.LXLanguage;
import dev.satherov.luminax.common.block.LuminaxBlockEntity;
import dev.satherov.luminax.common.item.LuminaxWandItem;
import dev.satherov.sathlib.common.properties.BlockItemProperty;
import dev.satherov.sathlib.common.properties.BlockItemPropertyContainer;
import dev.satherov.sathlib.common.properties.PropertyApplicator;
import dev.satherov.sathlib.common.properties.PropertyCycler;
import dev.satherov.sathlib.common.properties.PropertyDisplayer;
import dev.satherov.sathlib.common.properties.PropertyExtractor;
import dev.satherov.sathlib.network.chat.SLComponent;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@UtilityClass
public class LXProperties {
    
    public static final BlockItemProperty<Integer> COLOR = BlockItemProperty.builder(Luminax.id("color"), Integer.class, LXLanguage.PROPERTY_COLOR)
            .tooltipDisplayer(PropertyDisplayer.direct(LXLanguage.PROPERTY_COLOR_TOOLTIP.translate()))
            .valueDisplayer(val -> {
                String hex = String.format("#%06X", (0xFFFFFF & val));
                return SLComponent.of(Component.literal(hex).withColor(val));
            })
            .cycler(PropertyCycler.numberCycler(0x00000, 0xFFFFFF))
            .item(PropertyExtractor.item(LXRegistry.COLOR, 0xFFFFFF), PropertyApplicator.item(LXRegistry.COLOR))
            .block(PropertyExtractor.blockEntity(LuminaxBlockEntity.class, LuminaxBlockEntity::getColor, 0xFFFFFF), PropertyApplicator.blockEntity(LuminaxBlockEntity.class, LuminaxBlockEntity::setColor))
            .build();
    
    public static final BlockItemProperty<Boolean> GLOWING = BlockItemProperty.builder(Luminax.id("glowing"), Boolean.class, LXLanguage.PROPERTY_GLOWING)
            .tooltipDisplayer(PropertyDisplayer.boolDisplayer(LXLanguage.TOOLTIP_GLOWING_ON, LXLanguage.TOOLTIP_GLOWING_OFF))
            .cycler(PropertyCycler.BOOLEAN)
            .item(PropertyExtractor.item(LXRegistry.GLOWING, false), PropertyApplicator.item(LXRegistry.GLOWING))
            .block(PropertyExtractor.block(BlockStateProperties.LIT, false), PropertyApplicator.block(BlockStateProperties.LIT))
            .build();
    
    public static final BlockItemPropertyContainer<Block, LuminaxWandItem> CONTAINER = BlockItemPropertyContainer.builder(Block.class, LuminaxWandItem.class)
            .property(LXProperties.COLOR)
            .property(LXProperties.GLOWING)
            .build();
}

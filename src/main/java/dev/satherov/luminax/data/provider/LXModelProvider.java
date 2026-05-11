package dev.satherov.luminax.data.provider;

import dev.satherov.luminax.Luminax;
import dev.satherov.luminax.core.LXRegistry;
import dev.satherov.sathlib.core.annotations.NothingNull;
import dev.satherov.sathlib.data.model.SLBlockModelGenerators;
import dev.satherov.sathlib.data.model.SLModelProvider;

import net.neoforged.neoforge.client.model.generators.template.ElementBuilder;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplate;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

@NothingNull
public class LXModelProvider extends SLModelProvider {
    
    public static final TexturedModel.Provider CUBE = LXModelProvider.createProvider(ModelTemplates.CUBE_ALL, LXModelProvider::buildCube);
    public static final TexturedModel.Provider SLAB = LXModelProvider.createProvider(ModelTemplates.SLAB_BOTTOM, LXModelProvider::buildSlab);
    public static final TexturedModel.Provider SLAB_TOP = LXModelProvider.createProvider(ModelTemplates.SLAB_TOP, LXModelProvider::buildSlabTop);
    public static final TexturedModel.Provider STAIRS = LXModelProvider.createProvider(ModelTemplates.STAIRS_STRAIGHT, LXModelProvider::buildStairs);
    public static final TexturedModel.Provider STAIRS_INNER = LXModelProvider.createProvider(ModelTemplates.STAIRS_INNER, LXModelProvider::buildInnerStairs);
    public static final TexturedModel.Provider STAIRS_OUTER = LXModelProvider.createProvider(ModelTemplates.STAIRS_OUTER, LXModelProvider::buildOuterStairs);
    public static final TexturedModel.Provider BUTTON = LXModelProvider.createProvider(ModelTemplates.BUTTON, LXModelProvider::buildButton);
    public static final TexturedModel.Provider BUTTON_PRESSED = LXModelProvider.createProvider(ModelTemplates.BUTTON_PRESSED, LXModelProvider::buildPressedButton);
    public static final TexturedModel.Provider BUTTON_INVENTORY = LXModelProvider.createProvider(ModelTemplates.BUTTON_INVENTORY, LXModelProvider::buildButtonInventory);
    public static final TexturedModel.Provider PRESSURE_PLATE = LXModelProvider.createProvider(ModelTemplates.PRESSURE_PLATE_UP, LXModelProvider::buildPressurePlate);
    public static final TexturedModel.Provider PRESSURE_PLATE_DOWN = LXModelProvider.createProvider(ModelTemplates.PRESSURE_PLATE_DOWN, LXModelProvider::buildPressedPressurePlate);
    public static final TexturedModel.Provider WALL_POST = LXModelProvider.createProvider(ModelTemplates.WALL_POST, LXModelProvider::buildWallPost);
    public static final TexturedModel.Provider WALL_SIDE = LXModelProvider.createProvider(ModelTemplates.WALL_LOW_SIDE, LXModelProvider::buildWallSide);
    public static final TexturedModel.Provider WALL_SIDE_TALL = LXModelProvider.createProvider(ModelTemplates.WALL_TALL_SIDE, LXModelProvider::buildTallWallSide);
    public static final TexturedModel.Provider WALL_INVENTORY = LXModelProvider.createProvider(ModelTemplates.WALL_INVENTORY, LXModelProvider::buildWallInventory);
    
    public LXModelProvider(PackOutput output) {
        super(output, Luminax.MOD_ID);
    }
    
    private static TexturedModel.Provider createProvider(ModelTemplate template, Consumer<ExtendedModelTemplateBuilder> geometry) {
        return TexturedModel.createDefault(_ -> TextureMapping.cube(LXRegistry.BLOCK.get()), LXModelProvider.extend(template, geometry));
    }
    
    private static ExtendedModelTemplate extend(ModelTemplate template, Consumer<ExtendedModelTemplateBuilder> geometry) {
        ExtendedModelTemplateBuilder builder = template.extend().ambientOcclusion(false);
        geometry.accept(builder);
        return builder.build();
    }
    
    private static void buildCube(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.ALL, Direction.DOWN);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.ALL, Direction.UP);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.ALL, Direction.NORTH);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.ALL, Direction.SOUTH);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.ALL, Direction.WEST);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.ALL, Direction.EAST);
        });
    }
    
    private static void buildSlab(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 0.0F, 0.0F, 0.0F, 16.0F, 8.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.BOTTOM, Direction.DOWN, 0.0F, 0.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TOP, 0.0F, 0.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.SIDE, Direction.NORTH, 0.0F, 8.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.SIDE, Direction.SOUTH, 0.0F, 8.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.SIDE, Direction.WEST, 0.0F, 8.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.SIDE, Direction.EAST, 0.0F, 8.0F, 16.0F, 16.0F);
        });
    }
    
    private static void buildSlabTop(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 0.0F, 8.0F, 0.0F, 16.0F, 16.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.BOTTOM, 0.0F, 0.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TOP, Direction.UP, 0.0F, 0.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.SIDE, Direction.NORTH, 0.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.SIDE, Direction.SOUTH, 0.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.SIDE, Direction.WEST, 0.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.SIDE, Direction.EAST, 0.0F, 0.0F, 16.0F, 8.0F);
        });
    }
    
    private static void buildStairs(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.buildSlab(builder);
        LXModelProvider.addElement(builder, 8.0F, 8.0F, 0.0F, 16.0F, 16.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TOP, Direction.UP, 8.0F, 0.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.SIDE, Direction.NORTH, 0.0F, 0.0F, 8.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.SIDE, Direction.SOUTH, 8.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.SIDE, 0.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.SIDE, Direction.EAST, 0.0F, 0.0F, 16.0F, 8.0F);
        });
    }
    
    private static void buildInnerStairs(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.buildStairs(builder);
        LXModelProvider.addElement(builder, 0.0F, 8.0F, 8.0F, 8.0F, 16.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TOP, Direction.UP, 0.0F, 8.0F, 8.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.SIDE, 8.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.SIDE, Direction.SOUTH, 0.0F, 0.0F, 8.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.SIDE, Direction.WEST, 8.0F, 0.0F, 16.0F, 8.0F);
        });
    }
    
    private static void buildOuterStairs(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.buildSlab(builder);
        LXModelProvider.addElement(builder, 8.0F, 8.0F, 8.0F, 16.0F, 16.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TOP, Direction.UP, 8.0F, 8.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.SIDE, 0.0F, 0.0F, 8.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.SIDE, Direction.SOUTH, 8.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.SIDE, 8.0F, 0.0F, 16.0F, 8.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.SIDE, Direction.EAST, 0.0F, 0.0F, 8.0F, 8.0F);
        });
    }
    
    private static void buildButton(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 5.0F, 0.0F, 6.0F, 11.0F, 2.0F, 10.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.TEXTURE, Direction.DOWN, 5.0F, 6.0F, 11.0F, 10.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TEXTURE, 5.0F, 6.0F, 11.0F, 10.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.TEXTURE, 5.0F, 14.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.TEXTURE, 5.0F, 14.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.TEXTURE, 6.0F, 14.0F, 10.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.TEXTURE, 6.0F, 14.0F, 10.0F, 16.0F);
        });
    }
    
    private static void buildPressedButton(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 5.0F, 0.0F, 6.0F, 11.0F, 1.02F, 10.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.TEXTURE, Direction.DOWN, 5.0F, 6.0F, 11.0F, 10.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TEXTURE, 5.0F, 6.0F, 11.0F, 10.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.TEXTURE, 5.0F, 14.0F, 11.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.TEXTURE, 5.0F, 14.0F, 11.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.TEXTURE, 6.0F, 14.0F, 10.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.TEXTURE, 6.0F, 14.0F, 10.0F, 15.0F);
        });
    }
    
    private static void buildButtonInventory(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 5.0F, 6.0F, 6.0F, 11.0F, 10.0F, 10.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.TEXTURE, 5.0F, 6.0F, 11.0F, 10.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TEXTURE, 5.0F, 10.0F, 11.0F, 6.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.TEXTURE, 5.0F, 12.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.TEXTURE, 5.0F, 12.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.TEXTURE, 6.0F, 12.0F, 10.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.TEXTURE, 6.0F, 12.0F, 10.0F, 16.0F);
        });
    }
    
    private static void buildPressurePlate(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 15.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.TEXTURE, Direction.DOWN, 1.0F, 1.0F, 15.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TEXTURE, 1.0F, 1.0F, 15.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 16.0F);
        });
    }
    
    private static void buildPressedPressurePlate(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 1.0F, 0.0F, 1.0F, 15.0F, 0.5F, 15.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.TEXTURE, Direction.DOWN, 1.0F, 1.0F, 15.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.TEXTURE, 1.0F, 1.0F, 15.0F, 15.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 15.5F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 15.5F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 15.5F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.TEXTURE, 1.0F, 15.0F, 15.0F, 15.5F);
        });
    }
    
    private static void buildWallPost(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.WALL, Direction.DOWN);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.WALL, Direction.UP);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.WALL);
        });
    }
    
    private static void buildWallSide(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 5.0F, 0.0F, 0.0F, 11.0F, 14.0F, 8.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.WALL, Direction.DOWN);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.WALL, Direction.NORTH);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.WALL);
        });
    }
    
    private static void buildTallWallSide(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 5.0F, 0.0F, 0.0F, 11.0F, 16.0F, 8.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.WALL, Direction.DOWN);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.WALL, Direction.UP);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.WALL, Direction.NORTH);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.WALL);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.WALL);
        });
    }
    
    private static void buildWallInventory(ExtendedModelTemplateBuilder builder) {
        LXModelProvider.addElement(builder, 4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.WALL, Direction.DOWN, 4.0F, 4.0F, 12.0F, 12.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.WALL, 4.0F, 4.0F, 12.0F, 12.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.WALL, 4.0F, 0.0F, 12.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.WALL, 4.0F, 0.0F, 12.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.WALL, 4.0F, 0.0F, 12.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.WALL, 4.0F, 0.0F, 12.0F, 16.0F);
        });
        LXModelProvider.addElement(builder, 5.0F, 0.0F, 0.0F, 11.0F, 13.0F, 16.0F, element -> {
            LXModelProvider.addFace(element, Direction.DOWN, TextureSlot.WALL, Direction.DOWN, 5.0F, 0.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.UP, TextureSlot.WALL, 5.0F, 0.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.NORTH, TextureSlot.WALL, Direction.NORTH, 5.0F, 3.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.SOUTH, TextureSlot.WALL, Direction.SOUTH, 5.0F, 3.0F, 11.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.WEST, TextureSlot.WALL, 0.0F, 3.0F, 16.0F, 16.0F);
            LXModelProvider.addFace(element, Direction.EAST, TextureSlot.WALL, 0.0F, 3.0F, 16.0F, 16.0F);
        });
    }
    
    private static void addElement(ExtendedModelTemplateBuilder builder, float fromX, float fromY, float fromZ, float toX, float toY, float toZ, Consumer<ElementBuilder> config) {
        builder.element(element -> {
            element.from(fromX, fromY, fromZ)
                    .to(toX, toY, toZ)
                    .shade(false)
                    .lightEmission(15);
            config.accept(element);
        });
    }
    
    private static void addFace(ElementBuilder element, Direction direction, TextureSlot texture) {
        element.face(direction, face -> face.texture(texture).tintindex(0));
    }
    
    private static void addFace(ElementBuilder element, Direction direction, TextureSlot texture, Direction cullface) {
        element.face(direction, face -> face.texture(texture).tintindex(0).cullface(cullface));
    }
    
    private static void addFace(ElementBuilder element, Direction direction, TextureSlot texture, float u1, float v1, float u2, float v2) {
        element.face(direction, face -> face.texture(texture).tintindex(0).uvs(u1, v1, u2, v2));
    }
    
    private static void addFace(ElementBuilder element, Direction direction, TextureSlot texture, Direction cullface, float u1, float v1, float u2, float v2) {
        element.face(direction, face -> face.texture(texture).tintindex(0).cullface(cullface).uvs(u1, v1, u2, v2));
    }
    
    @Override
    protected void registerModels(SLBlockModelGenerators blocks, ItemModelGenerators items) {
        this.generateCube(blocks, LXRegistry.BLOCK.get());
        this.generateSlab(blocks, LXRegistry.SLAB.get(), LXRegistry.BLOCK.get());
        this.generateStairs(blocks, LXRegistry.STAIRS.get());
        this.generatePressurePlate(blocks, LXRegistry.PRESSURE_PLATE.get());
        this.generateButton(blocks, LXRegistry.BUTTON.get());
        this.generateWall(blocks, LXRegistry.WALL.get());
        items.generateFlatItem(LXRegistry.LUMINAX_WAND.get(), ModelTemplates.FLAT_ITEM);
    }
    
    private void generateCube(SLBlockModelGenerators blocks, Block block) {
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createSimpleBlock(block, SLBlockModelGenerators.plainVariant(LXModelProvider.CUBE.create(block, blocks.modelOutput))));
    }
    
    private void generateSlab(SLBlockModelGenerators blocks, Block slab, Block fullBlock) {
        Identifier bottomModel = LXModelProvider.SLAB.create(slab, blocks.modelOutput);
        Identifier topModel = LXModelProvider.SLAB_TOP.create(slab, blocks.modelOutput);
        Identifier fullBlockModel = ModelLocationUtils.getModelLocation(fullBlock);
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createSlab(
                slab,
                SLBlockModelGenerators.plainVariant(bottomModel),
                SLBlockModelGenerators.plainVariant(topModel),
                SLBlockModelGenerators.plainVariant(fullBlockModel)
        ));
    }
    
    private void generateStairs(SLBlockModelGenerators blocks, Block stairs) {
        Identifier innerModel = LXModelProvider.STAIRS_INNER.create(stairs, blocks.modelOutput);
        Identifier straightModel = LXModelProvider.STAIRS.create(stairs, blocks.modelOutput);
        Identifier outerModel = LXModelProvider.STAIRS_OUTER.create(stairs, blocks.modelOutput);
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createStairs(
                stairs,
                SLBlockModelGenerators.plainVariant(innerModel),
                SLBlockModelGenerators.plainVariant(straightModel),
                SLBlockModelGenerators.plainVariant(outerModel)
        ));
    }
    
    private void generatePressurePlate(SLBlockModelGenerators blocks, Block pressurePlate) {
        Identifier upModel = LXModelProvider.PRESSURE_PLATE.create(pressurePlate, blocks.modelOutput);
        Identifier downModel = LXModelProvider.PRESSURE_PLATE_DOWN.create(pressurePlate, blocks.modelOutput);
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createPressurePlate(
                pressurePlate,
                SLBlockModelGenerators.plainVariant(upModel),
                SLBlockModelGenerators.plainVariant(downModel)
        ));
    }
    
    private void generateButton(SLBlockModelGenerators blocks, Block button) {
        Identifier buttonModel = LXModelProvider.BUTTON.create(button, blocks.modelOutput);
        Identifier pressedModel = LXModelProvider.BUTTON_PRESSED.create(button, blocks.modelOutput);
        Identifier inventoryModel = LXModelProvider.BUTTON_INVENTORY.create(button, blocks.modelOutput);
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createButton(
                button,
                SLBlockModelGenerators.plainVariant(buttonModel),
                SLBlockModelGenerators.plainVariant(pressedModel)
        ));
        this.generateBlockItem(blocks, button, inventoryModel);
    }
    
    private void generateWall(SLBlockModelGenerators blocks, Block wall) {
        Identifier postModel = LXModelProvider.WALL_POST.create(wall, blocks.modelOutput);
        Identifier sideModel = LXModelProvider.WALL_SIDE.create(wall, blocks.modelOutput);
        Identifier tallSideModel = LXModelProvider.WALL_SIDE_TALL.create(wall, blocks.modelOutput);
        Identifier inventoryModel = LXModelProvider.WALL_INVENTORY.create(wall, blocks.modelOutput);
        blocks.blockStateOutput.accept(SLBlockModelGenerators.createWall(
                wall,
                SLBlockModelGenerators.plainVariant(postModel),
                SLBlockModelGenerators.plainVariant(sideModel),
                SLBlockModelGenerators.plainVariant(tallSideModel)
        ));
        this.generateBlockItem(blocks, wall, inventoryModel);
    }
    
    private void generateBlockItem(SLBlockModelGenerators blocks, Block block, Identifier modelLocation) {
        blocks.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(modelLocation));
    }
}

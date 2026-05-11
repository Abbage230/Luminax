package dev.satherov.luminax.common.block;

import dev.satherov.sathlib.common.block.SLEntityBlock;
import dev.satherov.sathlib.core.annotations.NothingNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

@NothingNull
public class LuminaxWall extends WallBlock implements SLEntityBlock<LuminaxBlockEntity>, LuminaxHolder {
    
    private final Function<BlockState, VoxelShape> shapes;
    private final Function<BlockState, VoxelShape> collisionShapes;
    
    public LuminaxWall(Properties properties) {
        super(properties.lightLevel((state) -> state.getValue(BlockStateProperties.LIT) ? 15 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.LIT, false));
        this.shapes = this.makeShapes(16.0F, 14.0F);
        this.collisionShapes = this.makeShapes(24.0F, 24.0F);
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(BlockStateProperties.LIT));
    }
    
    private Function<BlockState, VoxelShape> makeShapes(float postHeight, float wallTop) {
        VoxelShape post = Block.column(8.0, 0.0, postHeight);
        Map<Direction, VoxelShape> low = Shapes.rotateHorizontal(Block.boxZ(6.0, 0.0, wallTop, 0.0, 11.0));
        Map<Direction, VoxelShape> tall = Shapes.rotateHorizontal(Block.boxZ(6.0, 0.0, postHeight, 0.0, 11.0));
        return this.getShapeForEachState(state -> {
            VoxelShape shape = state.getValue(WallBlock.UP) ? post : Shapes.empty();
            
            for (Map.Entry<Direction, EnumProperty<WallSide>> entry : WallBlock.PROPERTY_BY_DIRECTION.entrySet()) {
                shape = Shapes.or(shape, switch (state.getValue(entry.getValue())) {
                    case NONE -> Shapes.empty();
                    case LOW -> (VoxelShape) low.get(entry.getKey());
                    case TALL -> (VoxelShape) tall.get(entry.getKey());
                });
            }
            
            return shape;
        }, WallBlock.WATERLOGGED, BlockStateProperties.LIT);
    }
    
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapes.apply(state);
    }
    
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.collisionShapes.apply(state);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return LuminaxHolder.super.getStateForPlacement(context, super.getStateForPlacement(context));
    }
    
    @Override
    public int getLightBlock(BlockState state, BlockGetter level) {
        return LuminaxHolder.super.getLightBlock(state, level);
    }
    
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer instanceof ServerPlayer player) LuminaxHolder.super.setPlacedBy(level, pos, player);
    }
    
    @Override
    public LuminaxBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LuminaxBlockEntity(pos, state);
    }
}

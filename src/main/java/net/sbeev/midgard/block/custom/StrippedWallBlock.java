package net.sbeev.midgard.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.sbeev.midgard.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class StrippedWallBlock extends WallBlock {
    public StrippedWallBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.PINE_TRUNK.get())) {
                return ModBlocks.STRIPPED_PINE_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.ASPEN_TRUNK.get())) {
                return ModBlocks.STRIPPED_ASPEN_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.MAPLE_TRUNK.get())) {
                return ModBlocks.STRIPPED_MAPLE_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.OAK_TRUNK.get())) {
                return ModBlocks.STRIPPED_OAK_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.BIRCH_TRUNK.get())) {
                return ModBlocks.STRIPPED_BIRCH_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.SPRUCE_TRUNK.get())) {
                return ModBlocks.STRIPPED_SPRUCE_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.ACACIA_TRUNK.get())) {
                return ModBlocks.STRIPPED_ACACIA_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.JUNGLE_TRUNK.get())) {
                return ModBlocks.STRIPPED_JUNGLE_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.DARK_OAK_TRUNK.get())) {
                return ModBlocks.STRIPPED_DARK_OAK_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.MANGROVE_TRUNK.get())) {
                return ModBlocks.STRIPPED_MANGROVE_TRUNK.get().defaultBlockState();
            }
            if (state.is(ModBlocks.CHERRY_TRUNK.get())) {
                return ModBlocks.STRIPPED_CHERRY_TRUNK.get().defaultBlockState();
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}

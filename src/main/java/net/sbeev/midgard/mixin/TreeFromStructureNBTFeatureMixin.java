package net.sbeev.midgard.mixin;

import dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TreeFromStructureNBTFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(TreeFromStructureNBTFeature.class)
public class TreeFromStructureNBTFeatureMixin {

    @Inject(method = "placeKnownBlockPositions", at = @At(value = "RETURN"), remap = false)
    private static void updateTrunk(Map<BlockPos, BlockState> trunkPositions, WorldGenLevel level, CallbackInfo ci) {
        trunkPositions.forEach(((blockPos, blockState) -> {
            if (blockState.is(BlockTags.FENCES) || blockState.is(BlockTags.WALLS)) {
                boolean changed = false;
                BlockState state = blockState;
                for (Direction dir : Direction.values()) {
                    BlockPos neighborPos = blockPos.offset(dir.getNormal());
                    BlockState neighborState = level.getBlockState(neighborPos);
                    BlockState updatedState = state.updateShape(dir, neighborState, level, blockPos, neighborPos);
                    if (!updatedState.equals(state)) {
                        state = updatedState;
                        changed = true;
                    }
                }
                if (changed) {
                    level.setBlock(blockPos, state, 2);
                }
            }
        }));
    }
}
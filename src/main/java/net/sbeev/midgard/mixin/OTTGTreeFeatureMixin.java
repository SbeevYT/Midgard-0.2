package net.sbeev.midgard.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Pseudo
@Mixin(targets = {
        "dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TreeFromStructureNBTFeature",
        "dev.corgitaco.ohthetreesyoullgrow.world.level.levelgen.feature.TreeFromStructureNBTFeatureV2"
}, remap = false)
public class OTTGTreeFeatureMixin {

    private static final ThreadLocal<Deque<Set<BlockPos>>> MIDGARD$COLLECTOR =
            ThreadLocal.withInitial(ArrayDeque::new);

    @Inject(
            method = {
                    "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
                    "m_142674_(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z"
            },
            at = @At("HEAD"),
            remap = false,
            require = 0
    )
    private void midgard$pushCollector(FeaturePlaceContext<?> ctx, CallbackInfoReturnable<Boolean> cir) {
        MIDGARD$COLLECTOR.get().push(new HashSet<>());
    }

    @Inject(
            method = "placeKnownBlockPositions(Ljava/util/Map;Lnet/minecraft/world/level/WorldGenLevel;)V",
            at = @At("RETURN"),
            remap = false,
            require = 0
    )
    private static void midgard$collectBlocks(Map<BlockPos, BlockState> map, WorldGenLevel level, CallbackInfo ci) {
        midgard$collectInto(map);
    }

    @Inject(
            method = "placeKnownLeavePositions(Ljava/util/Map;Lnet/minecraft/world/level/WorldGenLevel;)V",
            at = @At("RETURN"),
            remap = false,
            require = 0
    )
    private static void midgard$collectLeaves(Map<BlockPos, BlockState> map, WorldGenLevel level, CallbackInfo ci) {
        midgard$collectInto(map);
    }

    @Inject(
            method = {
                    "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
                    "m_142674_(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z"
            },
            at = @At("RETURN"),
            remap = false,
            require = 0
    )
    private void midgard$updateFenceShapes(FeaturePlaceContext<?> ctx, CallbackInfoReturnable<Boolean> cir) {
        Set<BlockPos> positions = MIDGARD$COLLECTOR.get().poll();
        if (positions == null || positions.isEmpty()) return;
        if (!Boolean.TRUE.equals(cir.getReturnValue())) return;

        WorldGenLevel level = ctx.level();
        for (BlockPos pos : positions) {
            BlockState state = level.getBlockState(pos);
            if (!isFenceLike(state)) continue;

            BlockState current = state;
            boolean changed = false;
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos neigh = pos.relative(dir);
                BlockState neighState = level.getBlockState(neigh);
                BlockState updated = current.updateShape(dir, neighState, level, pos, neigh);
                if (current != updated) {
                    current = updated;
                    changed = true;
                }
            }
            if (changed) {
                level.setBlock(pos, current, Block.UPDATE_CLIENTS);
            }
        }
    }

    private static void midgard$collectInto(Map<BlockPos, BlockState> map) {
        Deque<Set<BlockPos>> stack = MIDGARD$COLLECTOR.get();
        Set<BlockPos> top = stack.peek();
        if (top == null) return;
        for (Map.Entry<BlockPos, BlockState> entry : map.entrySet()) {
            if (isFenceLike(entry.getValue())) {
                top.add(entry.getKey().immutable());
            }
        }
    }

    private static boolean isFenceLike(BlockState state) {
        return state.is(BlockTags.FENCES) || state.is(BlockTags.WALLS) || state.is(BlockTags.FENCE_GATES);
    }
}

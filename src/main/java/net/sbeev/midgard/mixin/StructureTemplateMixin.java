package net.sbeev.midgard.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {

    @Inject(
            method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Lnet/minecraft/util/RandomSource;I)Z",
            at = @At("RETURN")
    )
    private void midgard$updateFenceShapes(
            ServerLevelAccessor level,
            BlockPos offset,
            BlockPos pivot,
            StructurePlaceSettings settings,
            RandomSource random,
            int flags,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!Boolean.TRUE.equals(cir.getReturnValue())) return;

        BoundingBox bb;
        try {
            bb = ((StructureTemplate) (Object) this).getBoundingBox(settings, offset);
        } catch (Throwable t) {
            return;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int x = bb.minX(); x <= bb.maxX(); x++) {
            for (int z = bb.minZ(); z <= bb.maxZ(); z++) {
                for (int y = bb.minY(); y <= bb.maxY(); y++) {
                    pos.set(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (!(state.is(BlockTags.FENCES) || state.is(BlockTags.WALLS) || state.is(BlockTags.FENCE_GATES))) continue;

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
        }
    }
}

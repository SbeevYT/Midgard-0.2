/*package net.sbeev.midgard;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Mod.EventBusSubscriber(modid = "midgard", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FenceWallUpdater {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final java.util.Map<ServerLevel, Queue<net.minecraft.world.level.ChunkPos>> PENDING_CHUNKS = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        LevelAccessor level = event.getLevel();
        if (level.isClientSide()) return;
        PENDING_CHUNKS.computeIfAbsent((ServerLevel) level, k -> new ArrayDeque<>())
                .add(event.getChunk().getPos());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) return;
        MinecraftServer server = event.getServer();
        server.getAllLevels().forEach(level -> {
            Queue<net.minecraft.world.level.ChunkPos> queue = PENDING_CHUNKS.get(level);
            if (queue != null && !queue.isEmpty()) {
                for (int i = 0; i < 1; i++) {  // Safe: ~1-2ms/tick
                    net.minecraft.world.level.ChunkPos chunkPos = queue.poll();
                    if (chunkPos == null) break;
                    processChunk(level, chunkPos);
                }
                if (queue.isEmpty()) {
                    PENDING_CHUNKS.remove(level);
                }
            }
        });
    }

    private static void processChunk(ServerLevel level, net.minecraft.world.level.ChunkPos chunkPos) {
        LevelChunk chunk = (LevelChunk) level.getChunk(chunkPos.getWorldPosition());
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int updated = 0;
        int minY = Math.max(chunk.getMinBuildHeight(), -64);
        int maxY = Math.min(chunk.getMaxBuildHeight(), 320);

        for (int lx = 0; lx < 16; lx++) {
            for (int lz = 0; lz < 16; lz++) {
                for (int ly = minY; ly < maxY; ly++) {
                    mutablePos.set(chunk.getPos().getMinBlockX() + lx, ly, chunk.getPos().getMinBlockZ() + lz);
                    BlockState state = chunk.getBlockState(mutablePos);
                    if (state.isAir() || (!state.is(BlockTags.FENCES) && !state.is(BlockTags.WALLS) && !state.is(BlockTags.FENCE_GATES))) continue;

                    BlockState current = state;
                    boolean changed = false;
                    for (Direction dir : Direction.Plane.HORIZONTAL) {
                        BlockPos neighPos = mutablePos.offset(dir.getNormal());
                        BlockState neighState = level.getBlockState(neighPos);
                        BlockState newState = current.updateShape(dir, neighState, level, mutablePos, neighPos);
                        if (!current.equals(newState)) {
                            current = newState;
                            changed = true;
                        }
                    }
                    if (changed) {
                        level.setBlock(mutablePos, current, 1);  // UPDATE_CLIENTS only: no lag chains
                        updated++;
                    }
                }
            }
        }
        if (updated > 0) {
            LOGGER.info("FenceWallUpdater: Fixed {} fence/wall/gate blocks in chunk {}", updated, chunkPos);
        }
    }
}
*/
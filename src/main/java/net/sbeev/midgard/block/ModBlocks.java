package net.sbeev.midgard.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.BirchTreeGrower;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.grower.SpruceTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sbeev.midgard.Midgard;
import net.sbeev.midgard.block.custom.FlammableWallBlock;
import net.sbeev.midgard.block.custom.ModFlammableRotatedPillarBlock;
import net.sbeev.midgard.block.custom.StrippedWallBlock;
import net.sbeev.midgard.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Midgard.MOD_ID);


    public static final RegistryObject<Block> CONIFER_TOPSOIL = registerBlock("conifer_topsoil",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT)) {
                @Override
                public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction facing, IPlantable plantable) {
                        PlantType plantType = plantable.getPlantType(level, pos.relative(facing.getOpposite()));
                        return plantType == PlantType.PLAINS || plantType == PlantType.WATER;
                }
            });

    public static final RegistryObject<Block> WHITE_SAND = registerBlock("white_sand",
            () -> new FallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistryObject<Block> WHITE_SANDSTONE = registerBlock("white_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> WHITE_SANDSTONE_STAIRS = registerBlock("white_sandstone_stairs",
            () -> new StairBlock(() -> ModBlocks.WHITE_SANDSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> WHITE_SANDSTONE_SLAB = registerBlock("white_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> WHITE_SANDSTONE_WALL = registerBlock("white_sandstone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> CUT_WHITE_SANDSTONE = registerBlock("cut_white_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistryObject<Block> CUT_WHITE_SANDSTONE_SLAB = registerBlock("cut_white_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE)));
    public static final RegistryObject<Block> SMOOTH_WHITE_SANDSTONE = registerBlock("smooth_white_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistryObject<Block> SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock("smooth_white_sandstone_stairs",
            () -> new StairBlock(() -> ModBlocks.SMOOTH_WHITE_SANDSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistryObject<Block> SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock("smooth_white_sandstone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)));
    public static final RegistryObject<Block> CHISELED_WHITE_SANDSTONE = registerBlock("chiseled_white_sandstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE)));

    public static final RegistryObject<Block> SHRUB = registerBlock("shrub",
            () -> new DeadBushBlock(BlockBehaviour.Properties.copy(Blocks.FERN).noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BEACH_GRASS = registerBlock("beach_grass",
            () -> new DeadBushBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH).noOcclusion().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> BROWN_OAK_LEAVES = registerBlock("brown_oak_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> LEAF_PILE = registerBlock("leaf_pile",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                    }

            });

    public static final RegistryObject<Block> MAPLE_LEAF_PILE = registerBlock("maple_leaf_pile",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                }

            });

    public static final RegistryObject<Block> AUTUMNAL_MAPLE_LEAF_PILE = registerBlock("autumnal_maple_leaf_pile",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                }

            });

    public static final RegistryObject<Block> ASPEN_LEAF_PILE = registerBlock("aspen_leaf_pile",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                }

            });

    public static final RegistryObject<Block> AUTUMNAL_ASPEN_LEAF_PILE = registerBlock("autumnal_aspen_leaf_pile",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                }

            });

    public static final RegistryObject<Block> FALLEN_NEEDLES = registerBlock("fallen_needles",
            () -> new SnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)) {
                @Override
                public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {}

                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return Shapes.empty();
                }

            });

    public static final RegistryObject<Block> OAK_TRUNK = registerBlock("oak_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_OAK_TRUNK = registerBlock("stripped_oak_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> OAK_BRANCH = registerBlock("oak_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> BIRCH_TRUNK = registerBlock("birch_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_BIRCH_TRUNK = registerBlock("stripped_birch_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> BIRCH_BRANCH = registerBlock("birch_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> SPRUCE_TRUNK = registerBlock("spruce_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_SPRUCE_TRUNK = registerBlock("stripped_spruce_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_BRANCH = registerBlock("spruce_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> JUNGLE_TRUNK = registerBlock("jungle_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_JUNGLE_TRUNK = registerBlock("stripped_jungle_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_BRANCH = registerBlock("jungle_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ACACIA_TRUNK = registerBlock("acacia_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_ACACIA_TRUNK = registerBlock("stripped_acacia_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> ACACIA_BRANCH = registerBlock("acacia_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> DARK_OAK_TRUNK = registerBlock("dark_oak_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_DARK_OAK_TRUNK = registerBlock("stripped_dark_oak_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_BRANCH = registerBlock("dark_oak_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> MANGROVE_TRUNK = registerBlock("mangrove_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_MANGROVE_TRUNK = registerBlock("stripped_mangrove_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> MANGROVE_BRANCH = registerBlock("mangrove_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> CHERRY_TRUNK = registerBlock("cherry_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_CHERRY_TRUNK = registerBlock("stripped_cherry_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> CHERRY_BRANCH = registerBlock("cherry_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_LOG = registerBlock("pine_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> PINE_WOOD = registerBlock("pine_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> PINE_PLANKS = registerBlock("pine_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_SLAB = registerBlock("pine_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_STAIRS = registerBlock("pine_stairs",
            () -> new StairBlock(() -> ModBlocks.PINE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

            });

    public static final RegistryObject<Block> PINE_LEAVES = registerBlock("pine_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> FALLEN_NEEDLE_BLOCK = registerBlock("fallen_needle_block",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> PINE_TRUNK = registerBlock("pine_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_PINE_TRUNK = registerBlock("stripped_pine_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PINE_BRANCH = registerBlock("pine_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_FENCE = registerBlock("pine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> PINE_BUTTON = registerBlock("pine_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> PINE_DOOR = registerBlock("pine_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> PINE_SAPLING = registerBlock("pine_sapling",
            () -> new SaplingBlock(new SpruceTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> WHITE_PINE_SAPLING = registerBlock("white_pine_sapling",
            () -> new SaplingBlock(new SpruceTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> ASPEN_LOG = registerBlock("aspen_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> ASPEN_WOOD = registerBlock("aspen_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_ASPEN_LOG = registerBlock("stripped_aspen_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_ASPEN_WOOD = registerBlock("stripped_aspen_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> ASPEN_PLANKS = registerBlock("aspen_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ASPEN_SLAB = registerBlock("aspen_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ASPEN_STAIRS = registerBlock("aspen_stairs",
            () -> new StairBlock(() -> ModBlocks.ASPEN_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

            });

    public static final RegistryObject<Block> ASPEN_TRUNK = registerBlock("aspen_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_ASPEN_TRUNK = registerBlock("stripped_aspen_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> ASPEN_BRANCH = registerBlock("aspen_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ASPEN_FENCE = registerBlock("aspen_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ASPEN_FENCE_GATE = registerBlock("aspen_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> ASPEN_BUTTON = registerBlock("aspen_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> ASPEN_PRESSURE_PLATE = registerBlock("aspen_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> ASPEN_DOOR = registerBlock("aspen_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> ASPEN_TRAPDOOR = registerBlock("aspen_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> ASPEN_SAPLING = registerBlock("aspen_sapling",
            () -> new SaplingBlock(new BirchTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> YELLOW_ASPEN_SAPLING = registerBlock("yellow_aspen_sapling",
            () -> new SaplingBlock(new BirchTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> ASPEN_LEAVES = registerBlock("aspen_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> YELLOW_ASPEN_LEAVES = registerBlock("yellow_aspen_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> BROWN_ASPEN_LEAVES = registerBlock("brown_aspen_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () -> new StairBlock(() -> ModBlocks.ASPEN_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

            });

    public static final RegistryObject<Block> MAPLE_TRUNK = registerBlock("maple_trunk",
            () -> new StrippedWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_TRUNK = registerBlock("stripped_maple_trunk",
            () -> new FlammableWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> MAPLE_BRANCH = registerBlock("maple_branch",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> MAPLE_BUTTON = registerBlock("maple_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(new OakTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> YELLOW_MAPLE_SAPLING = registerBlock("yellow_maple_sapling",
            () -> new SaplingBlock(new OakTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> ORANGE_MAPLE_SAPLING = registerBlock("orange_maple_sapling",
            () -> new SaplingBlock(new OakTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> RED_MAPLE_SAPLING = registerBlock("red_maple_sapling",
            () -> new SaplingBlock(new OakTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> YELLOW_MAPLE_LEAVES = registerBlock("yellow_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> ORANGE_MAPLE_LEAVES = registerBlock("orange_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> RED_MAPLE_LEAVES = registerBlock("red_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> BROWN_MAPLE_LEAVES = registerBlock("brown_maple_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

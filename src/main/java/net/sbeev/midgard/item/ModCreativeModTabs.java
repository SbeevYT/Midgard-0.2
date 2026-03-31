package net.sbeev.midgard.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sbeev.midgard.Midgard;
import net.sbeev.midgard.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Midgard.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MIDGARD_TAB = CREATIVE_MODE_TABS.register("midgard_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PINE_SAPLING.get()))

                    .title(Component.translatable("creativetab.midgard_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SHRUB.get());
                        output.accept(ModBlocks.BEACH_GRASS.get());

                        output.accept(ModBlocks.WHITE_SAND.get());
                        output.accept(ModBlocks.WHITE_SANDSTONE.get());
                        output.accept(ModBlocks.WHITE_SANDSTONE_STAIRS.get());
                        output.accept(ModBlocks.WHITE_SANDSTONE_SLAB.get());
                        output.accept(ModBlocks.WHITE_SANDSTONE_WALL.get());
                        output.accept(ModBlocks.CUT_WHITE_SANDSTONE.get());
                        output.accept(ModBlocks.CUT_WHITE_SANDSTONE_SLAB.get());
                        output.accept(ModBlocks.SMOOTH_WHITE_SANDSTONE.get());
                        output.accept(ModBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS.get());
                        output.accept(ModBlocks.SMOOTH_WHITE_SANDSTONE_SLAB.get());
                        output.accept(ModBlocks.CHISELED_WHITE_SANDSTONE.get());

                        output.accept(ModBlocks.PINE_LOG.get());
                        output.accept(ModBlocks.PINE_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_PINE_LOG.get());
                        output.accept(ModBlocks.STRIPPED_PINE_WOOD.get());
                        output.accept(ModBlocks.PINE_PLANKS.get());
                        output.accept(ModBlocks.PINE_STAIRS.get());
                        output.accept(ModBlocks.PINE_SLAB.get());
                        output.accept(ModBlocks.PINE_FENCE.get());
                        output.accept(ModBlocks.PINE_FENCE_GATE.get());
                        output.accept(ModBlocks.PINE_BUTTON.get());
                        output.accept(ModBlocks.PINE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.PINE_DOOR.get());
                        output.accept(ModBlocks.PINE_TRAPDOOR.get());
                        output.accept(ModBlocks.PINE_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_PINE_TRUNK.get());
                        output.accept(ModBlocks.PINE_BRANCH.get());
                        output.accept(ModBlocks.PINE_LEAVES.get());
                        output.accept(ModBlocks.FALLEN_NEEDLES.get());
                        output.accept(ModBlocks.FALLEN_NEEDLE_BLOCK.get());
                        output.accept(ModBlocks.PINE_SAPLING.get());
                        output.accept(ModBlocks.WHITE_PINE_SAPLING.get());

                        output.accept(ModBlocks.ASPEN_LOG.get());
                        output.accept(ModBlocks.ASPEN_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_ASPEN_LOG.get());
                        output.accept(ModBlocks.STRIPPED_ASPEN_WOOD.get());
                        output.accept(ModBlocks.ASPEN_PLANKS.get());
                        output.accept(ModBlocks.ASPEN_STAIRS.get());
                        output.accept(ModBlocks.ASPEN_SLAB.get());
                        output.accept(ModBlocks.ASPEN_FENCE.get());
                        output.accept(ModBlocks.ASPEN_FENCE_GATE.get());
                        output.accept(ModBlocks.ASPEN_BUTTON.get());
                        output.accept(ModBlocks.ASPEN_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.ASPEN_DOOR.get());
                        output.accept(ModBlocks.ASPEN_TRAPDOOR.get());
                        output.accept(ModBlocks.ASPEN_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_ASPEN_TRUNK.get());
                        output.accept(ModBlocks.ASPEN_BRANCH.get());
                        output.accept(ModBlocks.ASPEN_LEAVES.get());
                        output.accept(ModBlocks.YELLOW_ASPEN_LEAVES.get());
                        output.accept(ModBlocks.BROWN_ASPEN_LEAVES.get());
                        output.accept(ModBlocks.ASPEN_LEAF_PILE.get());
                        output.accept(ModBlocks.AUTUMNAL_ASPEN_LEAF_PILE.get());
                        output.accept(ModBlocks.ASPEN_SAPLING.get());
                        output.accept(ModBlocks.YELLOW_ASPEN_SAPLING.get());

                        output.accept(ModBlocks.MAPLE_LOG.get());
                        output.accept(ModBlocks.MAPLE_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_MAPLE_LOG.get());
                        output.accept(ModBlocks.STRIPPED_MAPLE_WOOD.get());
                        output.accept(ModBlocks.MAPLE_PLANKS.get());
                        output.accept(ModBlocks.MAPLE_STAIRS.get());
                        output.accept(ModBlocks.MAPLE_SLAB.get());
                        output.accept(ModBlocks.MAPLE_FENCE.get());
                        output.accept(ModBlocks.MAPLE_FENCE_GATE.get());
                        output.accept(ModBlocks.MAPLE_BUTTON.get());
                        output.accept(ModBlocks.MAPLE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.MAPLE_DOOR.get());
                        output.accept(ModBlocks.MAPLE_TRAPDOOR.get());
                        output.accept(ModBlocks.MAPLE_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_MAPLE_TRUNK.get());
                        output.accept(ModBlocks.MAPLE_BRANCH.get());
                        output.accept(ModBlocks.MAPLE_LEAVES.get());
                        output.accept(ModBlocks.YELLOW_MAPLE_LEAVES.get());
                        output.accept(ModBlocks.ORANGE_MAPLE_LEAVES.get());
                        output.accept(ModBlocks.RED_MAPLE_LEAVES.get());
                        output.accept(ModBlocks.BROWN_MAPLE_LEAVES.get());
                        output.accept(ModBlocks.MAPLE_LEAF_PILE.get());
                        output.accept(ModBlocks.AUTUMNAL_MAPLE_LEAF_PILE.get());
                        output.accept(ModBlocks.MAPLE_SAPLING.get());
                        output.accept(ModBlocks.YELLOW_MAPLE_SAPLING.get());
                        output.accept(ModBlocks.ORANGE_MAPLE_SAPLING.get());
                        output.accept(ModBlocks.RED_MAPLE_SAPLING.get());

                        output.accept(ModBlocks.OAK_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_OAK_TRUNK.get());
                        output.accept(ModBlocks.OAK_BRANCH.get());
                        output.accept(ModBlocks.BROWN_OAK_LEAVES.get());
                        output.accept(ModBlocks.LEAF_PILE.get());
                        output.accept(ModBlocks.BIRCH_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_BIRCH_TRUNK.get());
                        output.accept(ModBlocks.BIRCH_BRANCH.get());
                        output.accept(ModBlocks.SPRUCE_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_SPRUCE_TRUNK.get());
                        output.accept(ModBlocks.SPRUCE_BRANCH.get());
                        output.accept(ModBlocks.JUNGLE_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_JUNGLE_TRUNK.get());
                        output.accept(ModBlocks.JUNGLE_BRANCH.get());
                        output.accept(ModBlocks.ACACIA_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_ACACIA_TRUNK.get());
                        output.accept(ModBlocks.ACACIA_BRANCH.get());
                        output.accept(ModBlocks.DARK_OAK_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_DARK_OAK_TRUNK.get());
                        output.accept(ModBlocks.DARK_OAK_BRANCH.get());
                        output.accept(ModBlocks.MANGROVE_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_MANGROVE_TRUNK.get());
                        output.accept(ModBlocks.MANGROVE_BRANCH.get());
                        output.accept(ModBlocks.CHERRY_TRUNK.get());
                        output.accept(ModBlocks.STRIPPED_CHERRY_TRUNK.get());
                        output.accept(ModBlocks.CHERRY_BRANCH.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

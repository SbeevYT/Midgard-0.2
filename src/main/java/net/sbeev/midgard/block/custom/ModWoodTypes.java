package net.sbeev.midgard.block.custom;

import net.sbeev.midgard.Midgard;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType PINE = WoodType.register(new WoodType(Midgard.MOD_ID + ":pine", BlockSetType.OAK));
    public static final WoodType ASPEN = WoodType.register(new WoodType(Midgard.MOD_ID + ":aspen", BlockSetType.OAK));
    public static final WoodType MAPLE = WoodType.register(new WoodType(Midgard.MOD_ID + ":maple", BlockSetType.OAK));
}
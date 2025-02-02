package net.tslat.aoa3.content.fluid;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public final class ToxicWaste extends FluidType {
	public ToxicWaste() {
		super(FluidType.Properties.create()
				.canSwim(true)
				.canDrown(true)
				.supportsBoating(true)
				.pathType(PathType.DAMAGE_OTHER)
				.adjacentPathType(PathType.DAMAGE_OTHER)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
				.fallDistanceModifier(0.1f)
				.viscosity(10000)
				.density(5000)
				.temperature(400));
	}
}

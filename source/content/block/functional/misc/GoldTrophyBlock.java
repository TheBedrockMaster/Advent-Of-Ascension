package net.tslat.aoa3.content.block.functional.misc;

import com.google.common.base.Suppliers;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import net.minecraft.Util;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.content.item.misc.summoning.BossTokenItem;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.EntitySpawningUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Supplier;

public class GoldTrophyBlock extends TrophyBlock implements BossTokenItem {
	private static final Supplier<Map<EntityType<?>, EntityType<?>>> TYPE_SUBSTITUTES = Suppliers.memoize(() -> Util.make(new Reference2ReferenceArrayMap<>(), map -> {
		map.put(AoAMonsters.SMASH.value(), AoAMonsters.ELITE_SMASH.value());
		map.put(AoAMonsters.KING_BAMBAMBAM.value(), AoAMonsters.ELITE_KING_BAMBAMBAM.value());
		map.put(AoAMonsters.NETHENGEIC_WITHER.value(), AoAMonsters.ELITE_NETHENGEIC_WITHER.value());
		map.put(AoAMonsters.SKELETRON.value(), AoAMonsters.ELITE_SKELETRON.value());
		map.put(AoAMonsters.TYROSAUR.value(), AoAMonsters.ELITE_TYROSAUR.value());
	}));

	public GoldTrophyBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public Entity spawnBoss(ServerLevel level, Vec3 position, ItemStack itemStack, int playerCount) {
		Entity boss = EntitySpawningUtil.spawnEntity(level, getEntityType(itemStack), position, MobSpawnType.TRIGGERED);

		if (playerCount > 1 && boss instanceof LivingEntity entity) {
			AttributeUtil.applyPermanentModifier(entity, Attributes.MAX_HEALTH, getPerPlayerHealthBuff(playerCount));
			entity.setHealth(entity.getMaxHealth());
		}

		return boss;
	}

	@Nullable
	@Override
	public EntityType<?> getEntityType(ItemStack stack) {
		TrophyData trophyData = stack.get(AoADataComponents.TROPHY_DATA);

		if (trophyData == null || !trophyData.isOriginalTrophy())
			return null;

		return TYPE_SUBSTITUTES.get().getOrDefault(trophyData.getEntityType(), trophyData.getEntityType());
	}
}

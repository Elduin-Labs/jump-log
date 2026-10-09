package com.elduin.jump_log.content;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.phys.Vec3;

/**
 * What the Bounce Log does. A player standing on one (and not sneaking) is thrown upward.
 * The throw is about 6 blocks per tick, which is roughly a 120 block jump. Minecraft's own
 * speed check kicks in at 10 blocks per tick, so this stays safely under it.
 */
public final class BounceLog {

	public static final double LAUNCH_SPEED = 6.0;

	/** How long (in ticks) a launched player is safe from fall damage: one minute. */
	private static final long SAFE_TICKS = 20 * 60;

	/** Players who were launched, and the game time their fall protection runs out. */
	private static final Map<UUID, Long> SAFE_UNTIL = new HashMap<>();

	private BounceLog() {
	}

	/** Called every server tick for every player. */
	public static void tick(ServerPlayer player) {
		if (!player.onGround() || player.isShiftKeyDown()) {
			return;
		}
		if (!player.level().getBlockState(player.getOnPos()).is(ModBlocks.BOUNCE_LOG)) {
			return;
		}
		Vec3 motion = player.getDeltaMovement();
		player.setDeltaMovement(motion.x, LAUNCH_SPEED, motion.z);
		player.hurtMarked = true;
		SAFE_UNTIL.put(player.getUUID(), player.level().getGameTime() + SAFE_TICKS);
		player.level().playSound(null, player.blockPosition(), SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f);
	}

	/** Returns false to cancel a hit, when it is fall damage on a player who was just launched. */
	public static boolean allowDamage(net.minecraft.world.entity.LivingEntity entity, DamageSource source) {
		if (!(entity instanceof ServerPlayer player) || !source.is(DamageTypeTags.IS_FALL)) {
			return true;
		}
		Long until = SAFE_UNTIL.remove(player.getUUID());
		return until == null || player.level().getGameTime() > until;
	}
}

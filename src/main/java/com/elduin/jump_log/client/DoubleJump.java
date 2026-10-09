package com.elduin.jump_log.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

/**
 * Press jump a second time in the air and you jump again, once, until you touch the ground.
 * Your own game moves your own player, so this runs on the client and needs no messages.
 */
public final class DoubleJump {

	/** A normal jump is 0.42. A little more feels like a real second push. */
	private static final double SECOND_JUMP_SPEED = 0.5;

	private static boolean jumpWasDown = false;
	private static boolean secondJumpReady = true;

	private DoubleJump() {
	}

	public static void tick(Minecraft client) {
		LocalPlayer player = client.player;
		if (player == null) {
			return;
		}
		boolean jumpDown = client.options.keyJump.isDown();

		boolean canNotDoubleJump = player.onGround() || player.isInWater() || player.isInLava()
				|| player.getAbilities().flying || player.isFallFlying() || player.onClimbable()
				|| player.isPassenger();
		if (canNotDoubleJump) {
			secondJumpReady = true;
		} else if (jumpDown && !jumpWasDown && secondJumpReady) {
			Vec3 motion = player.getDeltaMovement();
			player.setDeltaMovement(motion.x, SECOND_JUMP_SPEED, motion.z);
			secondJumpReady = false;
		}
		jumpWasDown = jumpDown;
	}
}

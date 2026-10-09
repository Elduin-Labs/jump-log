package com.elduin.jump_log.event;

import com.elduin.jump_log.JumpLog;
import net.minecraft.server.level.ServerPlayer;

public class ExampleEventHandler {

	public static void onPlayerHurt(ServerPlayer player) {
		JumpLog.LOGGER.info("{} took damage.", player.getDisplayName());
	}
}

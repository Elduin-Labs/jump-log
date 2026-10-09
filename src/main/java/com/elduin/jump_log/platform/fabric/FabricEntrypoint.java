package com.elduin.jump_log.platform.fabric;

//? fabric {

import com.elduin.jump_log.JumpLog;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		JumpLog.onInitialize();
		FabricEventSubscriber.registerEvents();
	}
}
//?}

package com.elduin.jump_log.platform.fabric;

//? fabric {

import com.elduin.jump_log.JumpLog;
import com.elduin.jump_log.client.DoubleJump;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		JumpLog.onInitializeClient();
		ClientTickEvents.END_CLIENT_TICK.register(DoubleJump::tick);
	}

}
//?}

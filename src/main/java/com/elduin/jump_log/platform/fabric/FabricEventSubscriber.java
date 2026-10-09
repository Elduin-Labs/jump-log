package com.elduin.jump_log.platform.fabric;

//? fabric {

import com.elduin.jump_log.content.BounceLog;
import com.elduin.jump_log.content.ModBlocks;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class FabricEventSubscriber {

	public static void registerEvents() {
		ServerTickEvents.END_SERVER_TICK.register(server -> server.getPlayerList().getPlayers().forEach(BounceLog::tick));
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> BounceLog.allowDamage(entity, source));
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS)
				.register(entries -> entries.accept(ModBlocks.BOUNCE_LOG_ITEM));
	}
}
//?}

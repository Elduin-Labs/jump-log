package com.elduin.jump_log.content;

import com.elduin.jump_log.JumpLog;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class ModBlocks {

	/** A log. Stand on it and it launches you high into the sky. The launch lives in {@link BounceLog}. */
	public static final Block BOUNCE_LOG = registerBlock("bounce_log",
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0f).sound(SoundType.WOOD));

	public static final Item BOUNCE_LOG_ITEM = registerBlockItem("bounce_log", BOUNCE_LOG);

	private ModBlocks() {
	}

	/** Loads this class, which registers everything above. */
	public static void init() {
	}

	private static Block registerBlock(String name, BlockBehaviour.Properties properties) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, JumpLog.id(name));
		return Registry.register(BuiltInRegistries.BLOCK, key, new RotatedPillarBlock(properties.setId(key)));
	}

	private static Item registerBlockItem(String name, Block block) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, JumpLog.id(name));
		return Registry.register(BuiltInRegistries.ITEM, key,
				new BlockItem(block, new Item.Properties().setId(key).useBlockDescriptionPrefix()));
	}
}

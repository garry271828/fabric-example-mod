package com.cardtana.equinox;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * This registers all the blocks in static properties (so as soon as the class is loaded)
 * Optionally, can register an item to accompany the block
 * 
 * So named to avoid confusion with minecraft's internal Blocks
 */
public class ModBlocks {
    public static final Block ORICHALCUM_BLOCK = register(
		"orichalcum_block",
		Block::new,
		AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK),
		true
    );
    
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean registerItem) {
		// Create a registry key for the block
		RegistryKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		Block block = blockFactory.apply(settings.registryKey(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
		if (registerItem) {
			// Items need to be registered with a different type of registry key, but the ID can be the same.
			RegistryKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
			Registry.register(Registries.ITEM, itemKey, blockItem);
		}

		return Registry.register(Registries.BLOCK, blockKey, block);
	}

    // QoL thunks, these concatenate our namespace to it
    private static RegistryKey<Block> keyOfBlock(String name) {
		return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Equinox.MOD_ID, name));
	}

	private static RegistryKey<Item> keyOfItem(String name) {
		return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Equinox.MOD_ID, name));
	}

    /**
     * Dummy method, sole purpose is to load the static properties
     */
    public static void init() {}
}


package com.cardtana.equinox;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * Almost identical to ModBlocks, just a bit inside that register's if statement only
 * Go see there if you're confused
 */
public class ModItems {
    public static final Item ORICHALCUM_INGOT = register(
		"orichalcum_ingot",
		Item::new, // own item's constructor for custom behaviour
        new Item.Settings() // no settings
    );

    public static final Item LUCENITE_INGOT = register(
		"lucenite_ingot",
		Item::new, // own item's constructor for custom behaviour
        new Item.Settings() // no settings
    );

	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		// Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Equinox.MOD_ID, name));

		// Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));

		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);

		return item;
	}
    
    /**
     * Dummy method, sole purpose is to load the static properties
     */
    public static void init() {}
}


package net.usases.kordsmod;

import net.fabricmc.api.ModInitializer;

import net.usases.kordsmod.block.ModBlocks;
import net.usases.kordsmod.displayMods.CordDisplay;
import net.usases.kordsmod.displayMods.FpsDisplayMod;
import net.usases.kordsmod.item.ModItems;
import net.usases.kordsmod.item.ModItemsGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KordsMod implements ModInitializer {
	public static final String MOD_ID = "kordsmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemsGroups.registerItemGroup();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		new FpsDisplayMod();
		new CordDisplay();
	}
}
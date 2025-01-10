package net.usases.kordsmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.usases.kordsmod.KordsMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item COIN = registerItem("coin", new Item(new FabricItemSettings()));




    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(KordsMod.MOD_ID, name ),item);
    }
    public static void registerModItems(){
        KordsMod.LOGGER.info("Registering mod items for "+ KordsMod.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
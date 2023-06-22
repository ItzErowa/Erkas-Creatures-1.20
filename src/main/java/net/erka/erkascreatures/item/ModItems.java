package net.erka.erkascreatures.item;

import net.erka.erkascreatures.ErkasCreatures;
import net.erka.erkascreatures.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CATSHARK_BUCKET = registerItem("catshark_bucket",
           new Item(new FabricItemSettings()));

    public static final Item MUSK_OX_SPAWN_EGG = registerItem("musk_ox_spawn_egg",
            new SpawnEggItem(ModEntities.MUSK_OX, 0xD57E36, 0x1D0D00,
                    new FabricItemSettings()));






    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ErkasCreatures.MOD_ID, name), item);
    }
    public static void addItemsToItemGroup() {
        addToItemGroup(ModItemGroup.Erkas_Creatures, CATSHARK_BUCKET);
        addToItemGroup(ModItemGroup.Erkas_Creatures, MUSK_OX_SPAWN_EGG);

    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    public static void registerModItems() {
        ErkasCreatures.LOGGER.info("Registering Mod Items for " + ErkasCreatures.MOD_ID);

        addItemsToItemGroup();
    }
}

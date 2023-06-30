package net.erka.erkascreatures.item;

import net.erka.erkascreatures.ErkasCreatures;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

public class ModItemGroup {
    public static ItemGroup erkascreatures = Registry.register(Registries.ITEM_GROUP, new Identifier(ErkasCreatures.MOD_ID, "erkas-creatures"),
            FabricItemGroup.builder().displayName(Text.literal("Erka's Creatures"))
                    .icon(() -> new ItemStack(ModItems.CATSHARK_BUCKET)).entries((displayContext, entries) -> {

                        entries.add(ModItems.CATSHARK_BUCKET);
                        entries.add(ModItems.MUSK_OX_SPAWN_EGG);

    }).build());
//dodawanie do istniejącej grupy
    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(ModItems.MUSK_OX_SPAWN_EGG);

        });
    }

}


package net.erka.erkascreatures.item;

import net.erka.erkascreatures.ErkasCreatures;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup Erkas_Creatures;

    public static void registerItemGroups() {
    Erkas_Creatures = FabricItemGroup.builder(new Identifier(ErkasCreatures.MOD_ID, "catshark_bucket"))
            .displayName(Text.literal("Erka's Creatures"))
            .icon(() -> new ItemStack(ModItems.CATSHARK_BUCKET)).build();
    }
}


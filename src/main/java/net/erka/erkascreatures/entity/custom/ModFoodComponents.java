package net.erka.erkascreatures.entity.custom;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {


    public static final FoodComponent MUSK_OX_TONGUE = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent COOKED_MUSK_OX_TONGUE = new FoodComponent.Builder().hunger(7).saturationModifier(0.7f).meat().build();
}

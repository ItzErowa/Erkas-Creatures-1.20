package net.erka.erkascreatures.entity;

import net.erka.erkascreatures.ErkasCreatures;
import net.erka.erkascreatures.entity.custom.MuskOxEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MuskOxEntity> MUSK_OX = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(ErkasCreatures.MOD_ID, "musk_ox"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MuskOxEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());
}

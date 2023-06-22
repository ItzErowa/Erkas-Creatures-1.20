package net.erka.erkascreatures;

import net.erka.erkascreatures.data.ModModelProvider;
import net.erka.erkascreatures.entity.ModEntities;
import net.erka.erkascreatures.entity.custom.MuskOxEntity;
import net.erka.erkascreatures.item.ModItemGroup;
import net.erka.erkascreatures.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErkasCreatures implements ModInitializer {
	public static final String MOD_ID = "erkas-creatures";
    public static final Logger LOGGER = LoggerFactory.getLogger("erkas-creatures");

	@Override
	public void onInitialize() {

		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.MUSK_OX, MuskOxEntity.setAttributes());

		LOGGER.info("Hello Fabric world!");
	}
}
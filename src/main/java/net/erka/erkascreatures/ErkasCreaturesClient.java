package net.erka.erkascreatures;

import net.erka.erkascreatures.entity.ModEntities;
import net.erka.erkascreatures.entity.client.MuskOxRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ErkasCreaturesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.MUSK_OX, MuskOxRenderer::new);

    }
}

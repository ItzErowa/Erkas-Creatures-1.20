package net.erka.erkascreatures.entity.client;

import net.erka.erkascreatures.ErkasCreatures;
import net.erka.erkascreatures.entity.custom.MuskOxEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MuskOxRenderer extends GeoEntityRenderer<MuskOxEntity> {

    public MuskOxRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MuskOxModel());
    }

    @Override
    public Identifier getTexture(MuskOxEntity animatable) {
        return new Identifier(ErkasCreatures.MOD_ID, "textures/entity/muskox.png");
    }

    @Override
    public void render(MuskOxEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

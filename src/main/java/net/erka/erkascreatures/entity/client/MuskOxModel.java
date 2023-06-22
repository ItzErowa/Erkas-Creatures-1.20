package net.erka.erkascreatures.entity.client;

import net.erka.erkascreatures.ErkasCreatures;
import net.erka.erkascreatures.entity.custom.MuskOxEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MuskOxModel extends GeoModel<MuskOxEntity> {
    @Override
    public Identifier getModelResource(MuskOxEntity animatable) {
        return new Identifier(ErkasCreatures.MOD_ID, "geo/muskox.geo.json");
    }

    @Override
    public Identifier getTextureResource(MuskOxEntity animatable) {
        return new Identifier(ErkasCreatures.MOD_ID, "textures/entity/muskox.png");
    }

    @Override
    public Identifier getAnimationResource(MuskOxEntity animatable) {
        return new Identifier(ErkasCreatures.MOD_ID, "animations/muskox.animation.json");
    }

    @Override
    public void setCustomAnimations(MuskOxEntity animatable, long instanceId, AnimationState<MuskOxEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}

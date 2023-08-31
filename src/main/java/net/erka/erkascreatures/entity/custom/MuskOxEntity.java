package net.erka.erkascreatures.entity.custom;

import net.erka.erkascreatures.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;

public class MuskOxEntity extends AnimalEntity implements GeoEntity {
    private static final TrackedData<Boolean> LEFT_HORN = DataTracker.registerData(MuskOxEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> RIGHT_HORN = DataTracker.registerData(MuskOxEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);


    public MuskOxEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0f);
        this.setStepHeight(1.3f);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1d);
    }
    @Override
    protected void onGrowUp() {
        if (this.isBaby()) {
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(1.0);
            this.removeHorns();
        } else {
            Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(2.0);
            this.addHorns();
        }
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(LEFT_HORN, true);
        this.dataTracker.startTracking(RIGHT_HORN, true);
    }

    public void addHorns() {
        this.dataTracker.set(LEFT_HORN, true);
        this.dataTracker.set(RIGHT_HORN, true);
    }

    public void removeHorns() {
        this.dataTracker.set(LEFT_HORN, false);
        this.dataTracker.set(RIGHT_HORN, false);
    }

    @Override
    @Nullable
    public MuskOxEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MUSK_OX.create(world);
    }
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new FollowParentGoal(this, 0.45D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, LivingEntity.class, 6f));
        this.goalSelector.add(8, new MeleeAttackGoal(this, 1.2D, false));

        this.targetSelector.add(1, new MuskOxRevengeGoal());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackController", 1, this::attackPredicate));
    }
    private PlayState predicate(AnimationState<?> state) {
        if (state.isMoving()) {
            state.getController().setAnimation(RawAnimation.begin().thenPlay("walk"));
    } else {
            state.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
        }
        return PlayState.CONTINUE;
    }
    private PlayState attackPredicate(AnimationState<?> state)
    {
        if (this.handSwinging)

            state.getController().setAnimation(RawAnimation.begin().thenPlay("attack"));

        return PlayState.CONTINUE;
    }


    class MuskOxRevengeGoal  //working
            extends RevengeGoal {
        public MuskOxRevengeGoal() {
            super(MuskOxEntity.this);
        }
        @Override
        public void start() {
            super.start();
            if (MuskOxEntity.this.isBaby()) {
                this.callSameTypeForRevenge();
                this.stop();
            }
        }
        @Override
        protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
            if (mob instanceof MuskOxEntity && !mob.isBaby()) {
                super.setMobEntityTarget(mob, target);
            }
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.DANDELION;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}

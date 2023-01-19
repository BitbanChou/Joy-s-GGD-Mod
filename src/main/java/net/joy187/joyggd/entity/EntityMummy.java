package net.joy187.joyggd.entity;

import net.joy187.joyggd.init.SoundInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class EntityMummy extends WitherSkeleton implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(EntityMummy.class,
            EntityDataSerializers.INT);

    public EntityMummy(EntityType<? extends WitherSkeleton> p_34166_, Level p_34167_) {
        super(p_34166_, p_34167_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new EntityMummy.AttackGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityGoose.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EntityDuck.class, true));
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.FOLLOW_RANGE, 50.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void aiStep() {
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
        super.aiStep();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void setAttackingState(int time) {
        this.entityData.set(STATE, time);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    static class AttackGoal extends Goal
    {
        private final EntityMummy parentEntity;
        protected int attackTimer = 0;

        public AttackGoal(EntityMummy mob) {
            this.parentEntity = mob;
        }

        public boolean canUse() {
            return this.parentEntity.getTarget() != null;
        }

        public void start() {
            super.start();
            this.parentEntity.setAggressive(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.parentEntity.setAggressive(false);
            this.parentEntity.setAttackingState(0);
            this.attackTimer = -1;
        }

        public void tick() {
            LivingEntity livingentity = this.parentEntity.getTarget();
            if (this.parentEntity.hasLineOfSight(livingentity)) {
                ++this.attackTimer;

                if (this.attackTimer == 15) {
                    if(this.parentEntity.distanceToSqr(livingentity)>8.0D)
                    {
                        //this.parentEntity.setAttackingState(2);
                        Vec3 vec3 = this.parentEntity.position().add(0.0D, (double)1.6F, 0.0D);
                        Vec3 vec31 = livingentity.getEyePosition().subtract(vec3);
                        Vec3 vec32 = vec31.normalize();

                        for(int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                            Vec3 vec33 = vec3.add(vec32.scale((double)i));
                            ((ServerLevel)this.parentEntity.level).sendParticles(ParticleTypes.SONIC_BOOM, vec33.x, vec33.y, vec33.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
                        }

                        this.parentEntity.playSound(SoundInit.MUMMYKILL.get(), 3.0F, 1.0F);
                        if(livingentity instanceof IronGolem)
                            livingentity.hurt(DamageSource.sonicBoom(this.parentEntity), livingentity.getHealth()*0.25F);
                        else
                            livingentity.hurt(DamageSource.sonicBoom(this.parentEntity), 10.0F);
                        double d1 = 0.25D * (1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        double d0 = 1.5D * (1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        livingentity.push(-vec32.x() * d0, vec32.y() * d1, -vec32.z() * d0);
                    }
                }
                else{
                    this.parentEntity.setAttackingState(1);
                }
                if (this.attackTimer == 30) {
                    this.parentEntity.setAttackingState(0);
                    this.attackTimer = -5;
                }
            }
            else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
            this.parentEntity.lookAt(livingentity, 30.0F, 30.0F);
        }
    }
}


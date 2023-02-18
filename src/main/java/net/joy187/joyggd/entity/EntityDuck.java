package net.joy187.joyggd.entity;

import net.joy187.joyggd.entity.variant.DuckVariant;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntityDuck extends ZombieEntity implements IAnimatable {

    private boolean isdeath;
    private AnimationFactory factory = new AnimationFactory(this);

    public static final DataParameter<Integer> STATE = EntityDataManager.defineId(EntityDuck.class,
            DataSerializers.INT);

    private static final DataParameter<Integer> DATA_ID_TYPE_VARIANT =
            EntityDataManager.defineId(EntityDuck.class, DataSerializers.INT);

    public EntityDuck(EntityType<? extends ZombieEntity> p_34271_, World p_34272_) {
        super(p_34271_, p_34272_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(1, new EntityDuck.AttackGoal(this));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityGoose.class, true));
    }

    public static AttributeModifierMap.MutableAttribute prepareAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH,20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 5D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.isDeadOrDying()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.die", true));
            return PlayState.CONTINUE;
        }

        if(!this.onGround){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.fall", true));
            return PlayState.CONTINUE;
        }

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.entityData.get(STATE) == 1) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.attack1", false));
            //System.out.println("1!");
            this.swinging = false;
        }

        if(this.entityData.get(STATE) == 2) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goose.attack2", false));
            //System.out.println("2!");
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        int co=this.random.nextInt(3);
        if(co==1) return SoundInit.AMBIENT1.get();
        else if(co==2) return SoundInit.AMBIENT2.get();
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    public void aiStep() {
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
        if (this.isDeadOrDying() && !this.isdeath)
        {
            BlockPos blockpos1 = new BlockPos(this.position()); //relative(p_41297_.getClickedFace());
            BlockState blockstate1 = BlockInit.GOOSEBODY.get().defaultBlockState();

            this.level.setBlock(blockpos1, blockstate1, 11);
            //this.level.gameEvent(this, GameEvent.BLOCK_PLACE, blockpos1);
            this.isdeath=true;
        }
        super.aiStep();
    }

    public int getAttckingState() {
        return this.entityData.get(STATE);
    }

    public void setAttackingState(int time) {
        this.entityData.set(STATE, time);
    }

    public DuckVariant getVariant() {
        return DuckVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(DuckVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(DATA_ID_TYPE_VARIANT, this.random.nextInt(5));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        //setSitting(tag.getBoolean("isSitting"));
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        //tag.putBoolean("isSitting", this.isSitting());
        tag.putInt("Variant", this.getTypeVariant());
    }

    public ILivingEntityData finalizeSpawn(IServerWorld p_146746_, DifficultyInstance p_146747_,
                                           SpawnReason p_146748_, @Nullable ILivingEntityData p_146749_,
                                           @Nullable CompoundNBT p_146750_) {
        DuckVariant variant = Util.getRandom(DuckVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

//    public boolean doHurtTarget(Entity p_70652_1_) {
//        if (p_70652_1_ instanceof LivingEntity) {
//            float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
//            if(this.getVariant()==DuckVariant.DEMOLITIONIST)
//                ((LivingEntity)p_70652_1_).addEffect(new EffectInstance(EffectInit.BOOM.get(), 100 * (int)f, 2, true, true));
//            else if(this.getVariant()==DuckVariant.INVISIBILITY)
//                this.addEffect(new EffectInstance(Effects.INVISIBILITY, 100 * (int)f,0, false, false));
//            else if(this.getVariant()==DuckVariant.PROFESSIONAL)
//            {
//                if(p_70652_1_ instanceof EntityGoose)
//                {
//                    p_70652_1_.remove(RemovalReason.KILLED);
//                }
//            }
//        }
//
//        return super.doHurtTarget(p_70652_1_);
//    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    static class AttackGoal extends Goal {
        private final EntityDuck parentEntity;
        protected int attackTimer = 0;

        public AttackGoal(EntityDuck mob) {
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
            if (this.parentEntity.canSee(livingentity)) {
                ++this.attackTimer;
                //System.out.println(this.attackTimer);
                this.parentEntity.getNavigation().moveTo(livingentity, 1.5D);
                if (this.attackTimer == 15) {
                    this.parentEntity.setAttackingState(2);
                    Vector3d vec3 = this.parentEntity.position().add(0.0D, (double)1.6F, 0.0D);
                    Vector3d vec31 = livingentity.getEyePosition(1).subtract(vec3);
                    Vector3d vec32 = vec31.normalize();
                    this.parentEntity.setDeltaMovement(this.parentEntity.getDeltaMovement().add(vec32.x(), vec32.y(), vec32.z()));
                }
                else if(this.attackTimer % 25==0){
                    this.parentEntity.setAttackingState(1);
                    if(this.parentEntity.getVariant()!=DuckVariant.DEMOLITIONIST)
                    {
                        if(this.parentEntity.distanceToSqr(livingentity)<3D)
                        {
                            livingentity.hurt(DamageSource.mobAttack(this.parentEntity),Math.round(livingentity.getMaxHealth()*0.2F));
                        }
                    }
                    if(this.parentEntity.getVariant()==DuckVariant.PROFESSIONAL)
                    {
                        if(livingentity instanceof EntityGoose)
                        {
                            livingentity.remove();
                        }
                    }
                    if(this.parentEntity.getVariant()==DuckVariant.DEMOLITIONIST)
                    {
                        if(livingentity.getEffect(EffectInit.BOOM.get())==null)
                            livingentity.addEffect(new EffectInstance(EffectInit.BOOM.get(), Math.round(120 * this.parentEntity.random.nextFloat()), 3, true, true));
                        else
                            livingentity.hurt(DamageSource.mobAttack(this.parentEntity),Math.round(livingentity.getMaxHealth()*0.1F));
                    }

                    if(this.parentEntity.getVariant()==DuckVariant.INVISIBILITY)
                        this.parentEntity.addEffect(new EffectInstance(Effects.INVISIBILITY, Math.round(150 * this.parentEntity.random.nextFloat()),0, false, false));
                }
                else this.parentEntity.setAttackingState(0);
                if (this.attackTimer == 30) {
                    //System.out.println(this.parentEntity.getVariant());
                    this.parentEntity.setAttackingState(0);
                    this.attackTimer = -5;
                }
            }
            else if (this.attackTimer > 0) {
                --this.attackTimer;
            }
            this.parentEntity.lookAt(livingentity, 20.0F, 20.0F);
        }
    }
    
}

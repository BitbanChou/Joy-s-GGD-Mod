package net.joy187.joyggd.entity;

import net.joy187.joyggd.entity.variant.GooseVariant;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGoose extends ChickenEntity implements IAnimatable {

    private boolean isdeath;
    private AnimationFactory factory = new AnimationFactory(this);

    private static final DataParameter<Integer> DATA_ID_TYPE_VARIANT =
            EntityDataManager.defineId(EntityGoose.class, DataSerializers.INT);

    public EntityGoose(EntityType<? extends ChickenEntity> p_28236_, World p_28237_) {
        super(p_28236_, p_28237_);
        this.isdeath=false;
    }

    public static AttributeModifierMap.MutableAttribute prepareAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying() && !this.isdeath)
        {
            BlockPos blockpos1 = new BlockPos(this.position()); //relative(p_41297_.getClickedFace());
            BlockState blockstate1 = BlockInit.GOOSEBODY.get().defaultBlockState();

            //this.level.setBlock();
            this.level.setBlock(blockpos1, blockstate1, 11);
            //this.level.gameEvent(this, GameEvent.BLOCK_PLACE, blockpos1);
            //System.out.println("ji tui!");
            this.isdeath=true;
        }

        super.aiStep();
    }

    protected SoundEvent getAmbientSound() {
        int co=this.random.nextInt(3);
        if(co==1) return SoundInit.AMBIENT1.get();
        else if(co==2) return SoundInit.AMBIENT2.get();
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public GooseVariant getVariant() {
        return GooseVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(GooseVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, this.random.nextInt(6));
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
}

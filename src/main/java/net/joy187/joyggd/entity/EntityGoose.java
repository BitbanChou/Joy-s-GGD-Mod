package net.joy187.joyggd.entity;

import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGoose extends Chicken implements IAnimatable {

    private boolean isdeath;
    private AnimationFactory factory = new AnimationFactory(this);

    public EntityGoose(EntityType<? extends Chicken> p_28236_, Level p_28237_) {
        super(p_28236_, p_28237_);
        this.isdeath=false;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes()
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
            this.level.gameEvent(this, GameEvent.BLOCK_PLACE, blockpos1);
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
}

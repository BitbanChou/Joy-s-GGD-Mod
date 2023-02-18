package net.joy187.joyggd.block.blockentity;


import net.joy187.joyggd.init.BlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BlockEntityGooseBody extends TileEntity implements IAnimatable {
    //private final AnimationFactory manager = GeckoLibUtil.createFactory(this);
    private final AnimationFactory manager = new AnimationFactory(this);

//    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        //event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goosebody.rotate", true));
//
//        return PlayState.CONTINUE;
//    }

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goosebody.rotate", true));
        return PlayState.CONTINUE;
    }

    public BlockEntityGooseBody() {
        super(BlockEntityInit.GOOSEBODY.get());
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return manager;
    }
}

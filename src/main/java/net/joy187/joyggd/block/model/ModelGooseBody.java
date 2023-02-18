package net.joy187.joyggd.block.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGooseBody extends AnimatedGeoModel<BlockEntityGooseBody> {

    @Override
    public ResourceLocation getModelLocation(BlockEntityGooseBody animatable) {
        return new ResourceLocation(Main.MOD_ID, "geo/goosebody.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlockEntityGooseBody entity) {

        return new ResourceLocation(Main.MOD_ID, "textures/block/body.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlockEntityGooseBody entity) {
        return new ResourceLocation(Main.MOD_ID, "animations/goosebody.animation.json");
    }

}

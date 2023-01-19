package net.joy187.joyggd.block.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGooseBody extends AnimatedGeoModel<BlockEntityGooseBody> {

    @Override
    public ResourceLocation getModelResource(BlockEntityGooseBody animatable) {
        return new ResourceLocation(Main.MOD_ID, "geo/goosebody.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlockEntityGooseBody entity) {

        return new ResourceLocation(Main.MOD_ID, "textures/block/body.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlockEntityGooseBody entity) {
        return new ResourceLocation(Main.MOD_ID, "animations/goosebody.animation.json");
    }

}

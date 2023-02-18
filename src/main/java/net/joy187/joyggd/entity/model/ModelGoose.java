package net.joy187.joyggd.entity.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityGoose;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelGoose extends AnimatedGeoModel<EntityGoose> {

    @Override
    public ResourceLocation getModelLocation(EntityGoose object) {
        return new ResourceLocation(Main.MOD_ID, "geo/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGoose object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityGoose animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
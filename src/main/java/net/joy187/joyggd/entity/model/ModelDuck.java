package net.joy187.joyggd.entity.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityDuck;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelDuck extends AnimatedGeoModel<EntityDuck> {

    @Override
    public ResourceLocation getModelLocation(EntityDuck object) {
        return new ResourceLocation(Main.MOD_ID, "geo/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDuck object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityDuck animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
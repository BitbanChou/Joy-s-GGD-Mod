package net.joy187.joyggd.entity.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityDuck;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelDuck extends AnimatedGeoModel<EntityDuck> {

    @Override
    public ResourceLocation getModelResource(EntityDuck object) {
        return new ResourceLocation(Main.MOD_ID, "geo/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityDuck object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityDuck animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
package net.joy187.joyggd.entity.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityGoose;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelGoose extends AnimatedGeoModel<EntityGoose> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID, "goose"), "main");

    @Override
    public ResourceLocation getModelResource(EntityGoose object) {
        return new ResourceLocation(Main.MOD_ID, "geo/goose.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityGoose object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityGoose animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
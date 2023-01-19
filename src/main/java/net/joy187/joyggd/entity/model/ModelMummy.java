package net.joy187.joyggd.entity.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityMummy;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMummy extends AnimatedGeoModel<EntityMummy> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID, "goose"), "main");

    @Override
    public ResourceLocation getModelResource(EntityMummy object) {
        return new ResourceLocation(Main.MOD_ID, "geo/mummy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityMummy object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/mummy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityMummy animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
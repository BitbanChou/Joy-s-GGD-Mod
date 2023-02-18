package net.joy187.joyggd.entity.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityMummy;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMummy extends AnimatedGeoModel<EntityMummy> {

    @Override
    public ResourceLocation getModelLocation(EntityMummy object) {
        return new ResourceLocation(Main.MOD_ID, "geo/mummy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMummy object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/mummy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityMummy animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/goose.animation.json");
    }

}
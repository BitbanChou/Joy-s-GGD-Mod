package net.joy187.joyggd.entity.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntitySpaceship;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelSpaceship extends AnimatedGeoModel<EntitySpaceship> {

    @Override
    public ResourceLocation getModelLocation(EntitySpaceship object) {
        return new ResourceLocation(Main.MOD_ID, "geo/spaceship.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpaceship object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/spaceship.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySpaceship animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/policecar.animation.json");
    }

}
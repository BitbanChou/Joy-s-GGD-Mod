package net.joy187.joyggd.entity.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityPoliceCar;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelPoliceCar extends AnimatedGeoModel<EntityPoliceCar> {

    @Override
    public ResourceLocation getModelLocation(EntityPoliceCar object) {
        return new ResourceLocation(Main.MOD_ID, "geo/policecar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPoliceCar object) {
        return new ResourceLocation(Main.MOD_ID, "textures/entity/policecar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityPoliceCar animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/policecar.animation.json");
    }

}
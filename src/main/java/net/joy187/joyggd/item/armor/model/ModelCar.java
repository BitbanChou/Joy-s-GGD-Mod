package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.CarArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelCar extends AnimatedGeoModel<CarArmorItem> {
    @Override
    public ResourceLocation getModelLocation(CarArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/car.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CarArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/car.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CarArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
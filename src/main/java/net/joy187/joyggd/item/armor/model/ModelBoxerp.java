package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BoxerpArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBoxerp extends AnimatedGeoModel<BoxerpArmorItem> {
    @Override
    public ResourceLocation getModelLocation(BoxerpArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/boxerp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BoxerpArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/boxer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BoxerpArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
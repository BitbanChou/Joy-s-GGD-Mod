package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.OverallArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelOverall extends AnimatedGeoModel<OverallArmorItem> {
    @Override
    public ResourceLocation getModelLocation(OverallArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/overall.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OverallArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/overall.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OverallArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
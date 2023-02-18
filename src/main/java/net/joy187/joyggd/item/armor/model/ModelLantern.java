package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.LanternArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelLantern extends AnimatedGeoModel<LanternArmorItem> {
    @Override
    public ResourceLocation getModelLocation(LanternArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/lantern.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LanternArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/lantern_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LanternArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
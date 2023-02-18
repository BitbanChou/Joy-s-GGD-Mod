package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.SlackArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelSlack extends AnimatedGeoModel<SlackArmorItem> {
    @Override
    public ResourceLocation getModelLocation(SlackArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/slack.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SlackArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/slack.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SlackArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
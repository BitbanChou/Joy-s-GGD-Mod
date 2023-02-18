package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.MaidArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelMaid extends AnimatedGeoModel<MaidArmorItem> {
    @Override
    public ResourceLocation getModelLocation(MaidArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/maid.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MaidArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/maid_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MaidArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
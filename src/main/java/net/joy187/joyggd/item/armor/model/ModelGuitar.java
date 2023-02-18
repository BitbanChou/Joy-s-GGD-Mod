package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.GuitarArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelGuitar extends AnimatedGeoModel<GuitarArmorItem> {
    @Override
    public ResourceLocation getModelLocation(GuitarArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/guitar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GuitarArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/guitar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GuitarArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.GuitarPantArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelGuitarPant extends AnimatedGeoModel<GuitarPantArmorItem> {
    @Override
    public ResourceLocation getModelLocation(GuitarPantArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/guitarpants.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GuitarPantArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/guitar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GuitarPantArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
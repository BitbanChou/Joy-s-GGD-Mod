package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.MerMaidBraArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMerMaidBra extends AnimatedGeoModel<MerMaidBraArmorItem> {
    @Override
    public ResourceLocation getModelLocation(MerMaidBraArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/mermaidfinb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MerMaidBraArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/mermaidfin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MerMaidBraArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
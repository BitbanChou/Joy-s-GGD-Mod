package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.MerMaidFinArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelMerMaidFin extends AnimatedGeoModel<MerMaidFinArmorItem> {
    @Override
    public ResourceLocation getModelLocation(MerMaidFinArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/mermaidfin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MerMaidFinArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/mermaidfin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MerMaidFinArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
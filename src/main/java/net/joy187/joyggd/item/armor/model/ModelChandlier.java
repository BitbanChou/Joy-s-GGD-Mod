package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.ChandlierArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelChandlier extends AnimatedGeoModel<ChandlierArmorItem> {
	@Override
	public ResourceLocation getModelResource(ChandlierArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/chandlier.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ChandlierArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/chandlier_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(ChandlierArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
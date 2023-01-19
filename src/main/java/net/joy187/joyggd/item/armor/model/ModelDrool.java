package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.DroolArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelDrool extends AnimatedGeoModel<DroolArmorItem> {
	@Override
	public ResourceLocation getModelResource(DroolArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/drool.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(DroolArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/drool_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(DroolArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
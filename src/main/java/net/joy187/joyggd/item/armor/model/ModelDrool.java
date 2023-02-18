package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.DroolArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelDrool extends AnimatedGeoModel<DroolArmorItem> {
	@Override
	public ResourceLocation getModelLocation(DroolArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/drool.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(DroolArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/drool_layer_1.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(DroolArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
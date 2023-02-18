package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BoxerArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBoxer extends AnimatedGeoModel<BoxerArmorItem> {
	@Override
	public ResourceLocation getModelLocation(BoxerArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/boxer.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BoxerArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/boxer.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BoxerArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
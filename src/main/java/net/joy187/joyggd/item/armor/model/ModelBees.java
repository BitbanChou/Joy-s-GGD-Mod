package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BeesArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBees extends AnimatedGeoModel<BeesArmorItem> {
	@Override
	public ResourceLocation getModelLocation(BeesArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/bees.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BeesArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/bees.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BeesArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
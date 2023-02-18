package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BeeArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBee extends AnimatedGeoModel<BeeArmorItem> {
	@Override
	public ResourceLocation getModelLocation(BeeArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/bee.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BeeArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/bee.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BeeArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
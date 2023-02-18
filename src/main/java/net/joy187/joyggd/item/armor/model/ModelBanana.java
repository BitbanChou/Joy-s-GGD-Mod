package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BananaArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBanana extends AnimatedGeoModel<BananaArmorItem> {
	@Override
	public ResourceLocation getModelLocation(BananaArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/banana.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BananaArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/banana.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BananaArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.NightVisionArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelNightVision extends AnimatedGeoModel<NightVisionArmorItem> {
	@Override
	public ResourceLocation getModelLocation(NightVisionArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/nightvision.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(NightVisionArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/nightvision.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(NightVisionArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
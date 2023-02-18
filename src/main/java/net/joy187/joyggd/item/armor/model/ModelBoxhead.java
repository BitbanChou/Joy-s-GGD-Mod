package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.BoxheadArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelBoxhead extends AnimatedGeoModel<BoxheadArmorItem> {
	@Override
	public ResourceLocation getModelLocation(BoxheadArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/boxhead.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BoxheadArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/boxhead.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BoxheadArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
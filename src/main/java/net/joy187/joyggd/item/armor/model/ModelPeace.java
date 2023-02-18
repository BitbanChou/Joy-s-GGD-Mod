package net.joy187.joyggd.item.armor.model;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.PeaceArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ModelPeace extends AnimatedGeoModel<PeaceArmorItem> {
	@Override
	public ResourceLocation getModelLocation(PeaceArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "geo/peace.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(PeaceArmorItem object) {
		return new ResourceLocation(Main.MOD_ID, "textures/models/armor/peace.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(PeaceArmorItem animatable) {
		return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
	}
}
package net.joy187.joyggd.item.armor.render;


import net.joy187.joyggd.item.armor.ChandlierArmorItem;
import net.joy187.joyggd.item.armor.model.ModelChandlier;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;


public class RenderChandlier extends GeoArmorRenderer<ChandlierArmorItem> {
	public RenderChandlier() {
		super(new ModelChandlier());

		this.headBone = "Head";
		this.bodyBone = "other";
		this.rightArmBone = "other";
		this.leftArmBone = "other";
		this.rightLegBone = "other";
		this.leftLegBone = "other";
		this.rightBootBone = "other";
		this.leftBootBone = "other";
	}
	
}
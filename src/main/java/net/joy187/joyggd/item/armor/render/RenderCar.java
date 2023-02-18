package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.CarArmorItem;
import net.joy187.joyggd.item.armor.model.ModelCar;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;


public class RenderCar extends GeoArmorRenderer<CarArmorItem> {
    public RenderCar() {
        super(new ModelCar());

        this.headBone = "other";
        this.bodyBone = "Body";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
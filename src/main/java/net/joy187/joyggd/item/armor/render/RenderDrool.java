package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.DroolArmorItem;
import net.joy187.joyggd.item.armor.model.ModelDrool;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderDrool extends GeoArmorRenderer<DroolArmorItem> {
    public RenderDrool() {
        super(new ModelDrool());

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
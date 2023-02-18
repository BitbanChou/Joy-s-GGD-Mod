package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.BoxerArmorItem;
import net.joy187.joyggd.item.armor.model.ModelBoxer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderBoxer extends GeoArmorRenderer<BoxerArmorItem> {
    public RenderBoxer() {
        super(new ModelBoxer());

        this.headBone = "other";
        this.bodyBone = "dress";
        this.rightArmBone = "rightArm";
        this.leftArmBone = "leftArm";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
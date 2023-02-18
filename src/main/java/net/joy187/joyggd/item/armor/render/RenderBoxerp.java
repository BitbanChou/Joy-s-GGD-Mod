package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.BoxerpArmorItem;
import net.joy187.joyggd.item.armor.model.ModelBoxerp;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderBoxerp extends GeoArmorRenderer<BoxerpArmorItem> {
    public RenderBoxerp() {
        super(new ModelBoxerp());

        this.headBone = "other";
        this.bodyBone = "other";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "leftLeg";
        this.leftLegBone = "rightLeg";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
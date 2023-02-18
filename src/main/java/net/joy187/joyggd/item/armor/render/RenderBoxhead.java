package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.BoxheadArmorItem;
import net.joy187.joyggd.item.armor.model.ModelBoxhead;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderBoxhead extends GeoArmorRenderer<BoxheadArmorItem> {
    public RenderBoxhead() {
        super(new ModelBoxhead());

        this.headBone = "boxhead";
        this.bodyBone = "other";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
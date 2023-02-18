package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.DressArmorItem;
import net.joy187.joyggd.item.armor.model.ModelDress;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderDress extends GeoArmorRenderer<DressArmorItem> {
    public RenderDress() {
        super(new ModelDress());

        this.headBone = "other";
        this.bodyBone = "dress";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
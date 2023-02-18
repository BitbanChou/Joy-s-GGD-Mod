package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.OverallArmorItem;
import net.joy187.joyggd.item.armor.model.ModelOverall;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderOverall extends GeoArmorRenderer<OverallArmorItem> {
    public RenderOverall() {
        super(new ModelOverall());

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
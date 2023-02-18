package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.MerMaidFinArmorItem;
import net.joy187.joyggd.item.armor.model.ModelMerMaidFin;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderMerMaidFin extends GeoArmorRenderer<MerMaidFinArmorItem> {
    public RenderMerMaidFin() {
        super(new ModelMerMaidFin());

        this.headBone = "other";
        this.bodyBone = "body";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "leftLeg";
        this.leftLegBone = "rightLeg";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
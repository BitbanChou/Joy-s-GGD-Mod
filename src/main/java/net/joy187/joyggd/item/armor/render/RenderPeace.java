package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.PeaceArmorItem;
import net.joy187.joyggd.item.armor.model.ModelPeace;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderPeace extends GeoArmorRenderer<PeaceArmorItem> {
    public RenderPeace() {
        super(new ModelPeace());

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
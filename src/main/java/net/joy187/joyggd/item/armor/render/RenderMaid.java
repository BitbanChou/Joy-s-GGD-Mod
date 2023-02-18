package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.MaidArmorItem;
import net.joy187.joyggd.item.armor.model.ModelMaid;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderMaid extends GeoArmorRenderer<MaidArmorItem> {
    public RenderMaid() {
        super(new ModelMaid());

        this.headBone = "other";
        this.bodyBone = "maid";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
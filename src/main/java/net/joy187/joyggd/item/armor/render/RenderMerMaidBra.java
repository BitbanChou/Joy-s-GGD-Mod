package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.MerMaidBraArmorItem;
import net.joy187.joyggd.item.armor.model.ModelMerMaidBra;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderMerMaidBra extends GeoArmorRenderer<MerMaidBraArmorItem> {
    public RenderMerMaidBra() {
        super(new ModelMerMaidBra());

        this.headBone = "other";
        this.bodyBone = "mermaidfin";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
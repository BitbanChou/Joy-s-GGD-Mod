package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.LanternArmorItem;
import net.joy187.joyggd.item.armor.model.ModelLantern;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderLantern extends GeoArmorRenderer<LanternArmorItem> {
    public RenderLantern() {
        super(new ModelLantern());

        this.headBone = "other";
        this.bodyBone = "lantern1";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
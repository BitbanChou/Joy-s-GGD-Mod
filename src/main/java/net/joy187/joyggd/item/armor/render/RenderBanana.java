package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.BananaArmorItem;
import net.joy187.joyggd.item.armor.model.ModelBanana;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderBanana extends GeoArmorRenderer<BananaArmorItem> {
    public RenderBanana() {
        super(new ModelBanana());

        this.headBone = "banana";
        this.bodyBone = "other";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
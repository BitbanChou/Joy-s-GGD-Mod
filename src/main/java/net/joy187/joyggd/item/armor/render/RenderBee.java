package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.BananaArmorItem;
import net.joy187.joyggd.item.armor.BeeArmorItem;
import net.joy187.joyggd.item.armor.model.ModelBanana;
import net.joy187.joyggd.item.armor.model.ModelBee;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderBee extends GeoArmorRenderer<BeeArmorItem> {
    public RenderBee() {
        super(new ModelBee());

        this.headBone = "bee";
        this.bodyBone = "other";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
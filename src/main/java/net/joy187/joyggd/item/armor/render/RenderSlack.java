package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.GuitarPantArmorItem;
import net.joy187.joyggd.item.armor.SlackArmorItem;
import net.joy187.joyggd.item.armor.model.ModelGuitarPant;
import net.joy187.joyggd.item.armor.model.ModelSlack;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderSlack extends GeoArmorRenderer<SlackArmorItem> {
    public RenderSlack() {
        super(new ModelSlack());

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
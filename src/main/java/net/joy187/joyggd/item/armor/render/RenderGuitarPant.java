package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.GuitarArmorItem;
import net.joy187.joyggd.item.armor.GuitarPantArmorItem;
import net.joy187.joyggd.item.armor.model.ModelGuitar;
import net.joy187.joyggd.item.armor.model.ModelGuitarPant;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderGuitarPant extends GeoArmorRenderer<GuitarPantArmorItem> {
    public RenderGuitarPant() {
        super(new ModelGuitarPant());

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
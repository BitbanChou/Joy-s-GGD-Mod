package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.GuitarArmorItem;
import net.joy187.joyggd.item.armor.model.ModelGuitar;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderGuitar extends GeoArmorRenderer<GuitarArmorItem> {
    public RenderGuitar() {
        super(new ModelGuitar());

        this.headBone = "other";
        this.bodyBone = "guitar";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
package net.joy187.joyggd.item.armor.render;

import net.joy187.joyggd.item.armor.NightVisionArmorItem;
import net.joy187.joyggd.item.armor.model.ModelNightVision;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RenderNightVision extends GeoArmorRenderer<NightVisionArmorItem> {
    public RenderNightVision() {
        super(new ModelNightVision());

        this.headBone = "head";
        this.bodyBone = "other";
        this.rightArmBone = "other";
        this.leftArmBone = "other";
        this.rightLegBone = "other";
        this.leftLegBone = "other";
        this.rightBootBone = "other";
        this.leftBootBone = "other";
    }

}
package net.joy187.joyggd.entity.render;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.entity.model.ModelGoose;
import net.joy187.joyggd.entity.variant.GooseVariant;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;


public class RenderGoose extends GeoEntityRenderer<EntityGoose> {

    public RenderGoose(EntityRendererManager renderManager) {
        super(renderManager, new ModelGoose());
        this.shadowRadius = 0.6f;
    }

//    @Override
//    public ResourceLocation getTextureLocation(EntityGoose instance) {
//        //return LOCATION_BY_VARIANT.get(instance.getVariant());
//        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
//    }
    public static final Map<GooseVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GooseVariant.class), (p_114874_) -> {
                p_114874_.put(GooseVariant.A,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png"));
                p_114874_.put(GooseVariant.B,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose2.png"));
                p_114874_.put(GooseVariant.C,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose3.png"));
                p_114874_.put(GooseVariant.D,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose4.png"));
                p_114874_.put(GooseVariant.E,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose5.png"));
                p_114874_.put(GooseVariant.F,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose6.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(EntityGoose instance) {
        //return LOCATION_BY_VARIANT.get(instance.getVariant());
        //return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");

        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }
    @Override
    public RenderType getRenderType(EntityGoose animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1F, 1F, 1F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
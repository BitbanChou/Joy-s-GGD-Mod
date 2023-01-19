package net.joy187.joyggd.entity.render;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityDuck;
import net.joy187.joyggd.entity.model.ModelDuck;
import net.joy187.joyggd.entity.model.ModelGoose;
import net.joy187.joyggd.entity.variant.DuckVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;
import java.util.function.Predicate;


public class RenderDuck extends GeoEntityRenderer<EntityDuck> {
    
    public static final Map<DuckVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DuckVariant.class), (p_114874_) -> {
                p_114874_.put(DuckVariant.DEFAULT,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png"));
                p_114874_.put(DuckVariant.PROFESSIONAL,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose2.png"));
                p_114874_.put(DuckVariant.DEMOLITIONIST,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose3.png"));
                p_114874_.put(DuckVariant.INVISIBILITY,
                        new ResourceLocation(Main.MOD_ID, "textures/entity/goose4.png"));
            });

    public RenderDuck(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelDuck());
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDuck instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

//    @Override
//    public ResourceLocation getTextureLocation(EntityDuck instance) {
//        //return LOCATION_BY_VARIANT.get(instance.getVariant());
//        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
//    }

    @Override
    public RenderType getRenderType(EntityDuck animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1F, 1F, 1F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}
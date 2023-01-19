package net.joy187.joyggd.entity.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.entity.model.ModelGoose;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class RenderGoose extends GeoEntityRenderer<EntityGoose> {

    public RenderGoose(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ModelGoose());
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityGoose instance) {
        //return LOCATION_BY_VARIANT.get(instance.getVariant());
        return new ResourceLocation(Main.MOD_ID, "textures/entity/goose.png");
    }

    @Override
    public RenderType getRenderType(EntityGoose animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(1F, 1F, 1F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
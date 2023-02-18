package net.joy187.joyggd.entity.render;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntitySpaceship;
import net.joy187.joyggd.entity.model.ModelSpaceship;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class RenderSpaceship extends GeoEntityRenderer<EntitySpaceship> {

    public RenderSpaceship(EntityRendererManager renderManager) {
        super(renderManager, new ModelSpaceship());
        this.shadowRadius = 1f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpaceship instance) {
        //return LOCATION_BY_VARIANT.get(instance.getVariant());
        return new ResourceLocation(Main.MOD_ID, "textures/entity/spaceship.png");
    }

    @Override
    public RenderType getRenderType(EntitySpaceship animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(5F, 5F, 5F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
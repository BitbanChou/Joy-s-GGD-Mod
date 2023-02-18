package net.joy187.joyggd.entity.render;



import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityPoliceCar;
import net.joy187.joyggd.entity.model.ModelPoliceCar;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class RenderPoliceCar extends GeoEntityRenderer<EntityPoliceCar> {

    public RenderPoliceCar(EntityRendererManager renderManager) {
        super(renderManager, new ModelPoliceCar());
        this.shadowRadius = 1f;
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPoliceCar instance) {
        //return LOCATION_BY_VARIANT.get(instance.getVariant());
        return new ResourceLocation(Main.MOD_ID, "textures/entity/policecar.png");
    }

    @Override
    public RenderType getRenderType(EntityPoliceCar animatable, float partialTicks, MatrixStack stack,
                                    IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

        stack.scale(5F, 5F, 5F);

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
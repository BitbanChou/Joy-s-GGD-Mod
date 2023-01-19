package net.joy187.joyggd.block.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.joy187.joyggd.block.model.ModelGooseBody;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RenderGooseBody extends GeoBlockRenderer<BlockEntityGooseBody> {
    public RenderGooseBody(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ModelGooseBody());
    }

    @Override
    public RenderType getRenderType(BlockEntityGooseBody animatable, float partialTick, PoseStack poseStack,
                                    MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}

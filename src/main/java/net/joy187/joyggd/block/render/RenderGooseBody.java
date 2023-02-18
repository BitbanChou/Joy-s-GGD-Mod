package net.joy187.joyggd.block.render;



import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.joy187.joyggd.block.model.ModelGooseBody;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.client.model.tile.BotariumModel;
import software.bernie.example.client.model.tile.FertilizerModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class RenderGooseBody extends GeoBlockRenderer<BlockEntityGooseBody> {
    public RenderGooseBody(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new ModelGooseBody());
    }

    @Override
    public RenderType getRenderType(BlockEntityGooseBody animatable, float partialTick, MatrixStack poseStack,
                                    IRenderTypeBuffer bufferSource, IVertexBuilder buffer, int packedLight,
                                    ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}

package net.joy187.joyggd.entity.render;



import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityBullet;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;


public class RenderBullet extends EntityRenderer<EntityBullet> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID ,"textures/entity/mummy.png");
    private static final float MIN_CAMERA_DISTANCE_SQUARED = 12.25F;
    //private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean fullBright;

    public RenderBullet(EntityRendererManager manager) {
        //this.itemRenderer = manager.getItemRenderer();
        super(manager);
        //this.itemRenderer=manager.getItemRenderer();
        this.scale=0.25F;
        this.fullBright=false;

    }

    private static final RenderType field_229044_e_ = RenderType.entityCutoutNoCull(TEXTURE);

    protected int getSkyLightLevel(EntityBullet p_239381_1_, BlockPos p_239381_2_) {
        return 1;
    }
    @Override
    public ResourceLocation getTextureLocation(EntityBullet p_110775_1_) {
        return TEXTURE;
    }

    public void render(EntityBullet entityIn, float entityYaw, float partialTicks, MatrixStack poseStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entityIn) < 12.25D)) {
            poseStackIn.pushPose();
            poseStackIn.scale(this.scale, this.scale, this.scale);
            poseStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
            poseStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            //this.itemRenderer.renderStatic(entityIn.getItem(), ItemTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, poseStackIn, bufferIn, entityIn.getId());
            poseStackIn.popPose();
            super.render(entityIn, entityYaw, partialTicks, poseStackIn, bufferIn, packedLightIn);

        }
    }
//
//    private static void vertexRender(VertexConsumer p_229045_0_, Matrix4f p_229045_1_, Matrix3f p_229045_2_, int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
//        p_229045_0_.vertex(p_229045_1_, p_229045_4_ - 0.5F, (float)p_229045_5_ - 0.25F, 0.0F).color(255, 255, 255, 255).overlayCoords(OverlayTexture.NO_OVERLAY).normal(p_229045_2_, 0.0F, 1.0F, 0.0F).endVertex();
//    }
    private static void func_229045_a_(IVertexBuilder p_229045_0_, Matrix4f p_229045_1_, Matrix3f p_229045_2_, int p_229045_3_, float p_229045_4_, int p_229045_5_, int p_229045_6_, int p_229045_7_) {
        p_229045_0_.vertex(p_229045_1_, p_229045_4_ - 0.5F, (float)p_229045_5_ - 0.25F, 0.0F).color(255, 255, 255, 255).overlayCoords(OverlayTexture.NO_OVERLAY).normal(p_229045_2_, 0.0F, 1.0F, 0.0F).endVertex();
    }
}

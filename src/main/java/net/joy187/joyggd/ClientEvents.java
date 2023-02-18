package net.joy187.joyggd;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    private static final ResourceLocation VIEWFINDER = new ResourceLocation(Main.MOD_ID, "textures/gui/assassin.png");

    public static final float MAX_FOV = 90F;
    public static final float MIN_FOV = 5F;

    private Minecraft mc;
    private boolean inCameraMode;
    private float fov;
    private ResourceLocation currentShader;

    public ClientEvents() {
        mc = Minecraft.getInstance();
        inCameraMode = false;
        fov = 50F;
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Pre event) {

        if(!esper())
        {
            mc.setCameraEntity(mc.player);
        }

        if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            return;
        }

        inCameraMode = isInCameraMode();

        if (!inCameraMode) {
            setShader(null);
            return;
        }

        event.setCanceled(true);

        if (!event.getType().equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
            return;
        }

        mc.options.setCameraType(PointOfView.FIRST_PERSON);

        //setShader(getShader(mc.player));
        drawScope(event.getMatrixStack());
        //drawZoom(event.getMatrixStack (), getFOVPercentage());
    }

    private void drawScope(MatrixStack matrixStack) {
        mc.getTextureManager().bind(VIEWFINDER);
        float imageWidth = 192F;
        float imageHeight = 100F;

        BufferBuilder buffer = Tessellator.getInstance().getBuilder();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        float ws = (float) mc.getWindow().getGuiScaledWidth();
        float hs = (float) mc.getWindow().getGuiScaledHeight();

        float rs = ws / hs;
        float ri = imageWidth / imageHeight;

        float hnew;
        float wnew;

        if (rs > ri) {
            wnew = imageWidth * hs / imageHeight;
            hnew = hs;
        } else {
            wnew = ws;
            hnew = imageHeight * ws / imageWidth;
        }

        float top = (hs - hnew) / 2F;
        float left = (ws - wnew) / 2F;

        Matrix4f matrix = matrixStack.last().pose();
        buffer.vertex(matrix, left, top, 0F).uv(0F, 0F).endVertex();
        buffer.vertex(matrix, left, top + hnew, 0F).uv(0F, 100F / 256F).endVertex();
        buffer.vertex(matrix, left + wnew, top + hnew, 0F).uv(192F / 256F, 100F / 256F).endVertex();
        buffer.vertex(matrix, left + wnew, top, 0F).uv(192F / 256F, 0F).endVertex();
        buffer.end();
        WorldVertexBufferUploader.end(buffer);
    }

    private static final Predicate<Entity> ESPER_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public boolean esper() {

        if (mc.player!=null)
        {
            for (LivingEntity living : mc.player.level.getEntitiesOfClass(LivingEntity.class, mc.player.getBoundingBox().inflate(20), ESPER_AREA)) {
                if(living.getEffect(EffectInit.ESPER.get())!=null)
                {
                    if(living.isDeadOrDying() || living.getEffect(EffectInit.ESPER.get()).getDuration()==5)
                    {
                        //mc.player.setInvisible(false);
                        return false;
                    }

                    if(living.getHealth()>0)
                    {
                        if(living.getEffect(EffectInit.ESPER.get()).getDuration()>5) {
                            //System.out.println("GET!");
                            mc.setCameraEntity(living);
                            //mc.player.setInvisible(true);
                            return true;
                        }
                    }
                }
            }
            //System.out.println("BACK!");
            return false;
        }
        return false;

    }

    @SubscribeEvent
    public void renderHand(RenderHandEvent event) {
        if (inCameraMode) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (inCameraMode) {
            if (event.getGui() instanceof IngameMenuScreen) {
                //Main.SIMPLE_CHANNEL.sendToServer(new MessageDisableCameraMode());
                event.setCanceled(true);
            }
        }
    }

//    private ResourceLocation getShader(Player player) {
//        for (InteractionHand hand : InteractionHand.values()) {
//            ItemStack stack = player.getItemInHand(hand);
//            if (!stack.getItem().equals(ItemInit.ASSASSIN.get())) {
//                continue;
//            }
//            return Shaders.getShader(ItemInit.ASSASSIN.get().getShader(stack));
//        }
//        return null;
//    }

    private void setShader(ResourceLocation shader) {
        if (shader == null) {
            if (currentShader != null) {
                mc.gameRenderer.shutdownEffect();
            }
        } else if (!shader.equals(currentShader)) {
            try {
                mc.gameRenderer.loadEffect(shader);
            } catch (Exception e) {
            }
        }
        currentShader = shader;
    }

//    @SubscribeEvent
//    public void renderPlayer(RenderPlayerEvent.Pre event) {
//        Player player = event.getEntity();
//        if (player == mc.player) {
//            return;
//        }
//        for (InteractionHand hand : InteractionHand.values()) {
//            ItemStack stack = player.getItemInHand(hand);
//            if (stack.getItem() instanceof CameraItem && ItemInit.ASSASSIN.get().isActive(stack)) {
//                player.startUsingItem(hand);
//            }
//        }
//
//    }

    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent.Post event) {
        PlayerEntity player = event.getPlayer();
        if (player == mc.player) {
            return;
        }
        if (!inCameraMode) {
            return;
        }

        event.getPlayer().stopUsingItem();
    }

    @SubscribeEvent
    public void onMouseEvent(InputEvent.MouseScrollEvent event) {
        if (event.getScrollDelta() == 0D) {
            return;
        }
        if (!inCameraMode) {
            return;
        }

        if (event.getScrollDelta() < 0D) {
            fov = Math.min(fov + 5F, MAX_FOV);
        } else {
            fov = Math.max(fov - 5F, MIN_FOV);
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onFOVModifierEvent(EntityViewRenderEvent.FOVModifier event) {
        if (!inCameraMode) {
            fov = (float) event.getFOV();
            return;
        }

        /*
            To trigger the rendering of the chunks that were outside of the FOV
        */
        mc.player.setPos(mc.player.getX(), mc.player.getY() + 0.000000001D, mc.player.getZ());

        event.setFOV(fov);
    }

    public float getFOVPercentage() {
        return 1F - (fov - MIN_FOV) / (MAX_FOV - MIN_FOV);
    }

    private ItemStack getScopeAssassin() {
        if (mc.player == null) {
            return null;
        }
        for (Hand hand : Hand.values()) {
            ItemStack stack = mc.player.getItemInHand(hand);
            if ((stack.getItem().equals(ItemInit.ASSASSIN.get())
            || stack.getItem().equals(ItemInit.BIRDWATCHER.get())) &&
                    Screen.hasShiftDown()) { //&& ItemInit.ASSASSIN.get().isActive(stack)
                return stack;
            }
        }
        return null;
    }

    private boolean isInCameraMode() {
        return getScopeAssassin() != null;
    }

}


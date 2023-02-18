package net.joy187.joyggd.block.render;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.joy187.joyggd.block.Stove3Block;
import net.joy187.joyggd.block.blockentity.Stove3BlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;


public class Stove3Renderer extends TileEntityRenderer<Stove3BlockEntity> {
	public Stove3Renderer(TileEntityRendererDispatcher context) {
		super(context);
	}

	@Override
	public void render(Stove3BlockEntity stoveEntity, float partialTicks, MatrixStack poseStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
		Direction direction = stoveEntity.getBlockState().getValue(Stove3Block.FACING).getOpposite();

		//ItemStackHandler inventory = stoveEntity.inventory;
		NonNullList<ItemStack> inventory = stoveEntity.getItems();
		int posLong = (int) stoveEntity.getBlockPos().asLong();

		for (int i = 0; i < inventory.size(); ++i) {
			ItemStack stoveStack = inventory.get(i);
			if (!stoveStack.isEmpty()) {
				poseStack.pushPose();

				// Center item above the stove
				poseStack.translate(0.5D, 1.02D, 0.5D);

				// Rotate item to face the stove's front side
				float f = -direction.toYRot();
				poseStack.mulPose(Vector3f.YP.rotationDegrees(f));

				// Rotate item flat on the stove. Use X and Y from now on
				poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));

				// Neatly align items according to their index
				Vector2f itemOffset = stoveEntity.getStoveItemOffset(i);
				poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

				// Resize the items
				poseStack.scale(0.375F, 0.375F, 0.375F);

				if (stoveEntity.getLevel() != null) {
					Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemCameraTransforms.TransformType.FIXED, WorldRenderer.getLightColor(stoveEntity.getLevel(), stoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer);
				}
				poseStack.popPose();
			}
		}
	}
}
package net.joy187.joyggd.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.joy187.joyggd.block.blockentity.StoveBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;


public class StoveRenderer implements BlockEntityRenderer<StoveBlockEntity>
{
	public StoveRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(StoveBlockEntity stoveEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLightIn, int combinedOverlayIn) {
		Direction direction = stoveEntity.getBlockState().getValue(StoveBlock.FACING).getOpposite();

		ItemStackHandler inventory = stoveEntity.getInventory();
		int posLong = (int) stoveEntity.getBlockPos().asLong();

		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack stoveStack = inventory.getStackInSlot(i);
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
				Vec2 itemOffset = stoveEntity.getStoveItemOffset(i);
				poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

				// Resize the items
				poseStack.scale(0.375F, 0.375F, 0.375F);

				if (stoveEntity.getLevel() != null)
					Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemTransforms.TransformType.FIXED, LevelRenderer.getLightColor(stoveEntity.getLevel(), stoveEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, posLong + i);
				poseStack.popPose();
			}
		}
	}
}
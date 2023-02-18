package net.joy187.joyggd.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.Random;

import static net.minecraft.util.math.MathHelper.degreesDifference;

/**
 * Util for providing and calculating math-related objects across the mod.
 */
public class MathUtils
{
	public static final Random RAND = new Random();

	/**
	 * Calculates a comparator signal using an ItemHandler inventory, instead of IInventory.
	 * Employing a RecipeWrapper would have caused a divide-by-zero, hence why this method was made.
	 *
	 * @param handler The inventory to compare.
	 * @return The redstone signal strength.
	 */
	public static int calcRedstoneFromItemHandler(@Nullable IItemHandlerModifiable handler) {
		if (handler == null) {
			return 0;
		}
		else {
			int i = 0;
			float f = 0.0F;

			for (int j = 0; j < handler.getSlots(); ++j) {
				ItemStack itemstack = handler.getStackInSlot(j);
				if (!itemstack.isEmpty()) {
					f += (float) itemstack.getCount() / (float) Math.min(handler.getSlotLimit(j), itemstack.getMaxStackSize());
					++i;
				}
			}

			f = f / (float) handler.getSlots();
			return MathHelper.floor(f * 14.0F) + (i > 0 ? 1 : 0);
		}
	}

	public static BlockPos getOnPosL(LivingEntity player) {
		int i = MathHelper.floor(player.position().x);
		int j = MathHelper.floor(player.position().y - (double)0.2F);
		int k = MathHelper.floor(player.position().z);
		BlockPos blockpos = new BlockPos(i, j, k);
		if (player.level.isEmptyBlock(blockpos)) {
			BlockPos blockpos1 = blockpos.below();
			BlockState blockstate = player.level.getBlockState(blockpos1);
			if (blockstate.collisionExtendsVertically(player.level, blockpos1, player)) {
				return blockpos1;
			}
		}

		return blockpos;
	}

	public static BlockPos getOnPos(PlayerEntity player) {
		int i = MathHelper.floor(player.position().x);
		int j = MathHelper.floor(player.position().y - (double)0.2F);
		int k = MathHelper.floor(player.position().z);
		BlockPos blockpos = new BlockPos(i, j, k);
		if (player.level.isEmptyBlock(blockpos)) {
			BlockPos blockpos1 = blockpos.below();
			BlockState blockstate = player.level.getBlockState(blockpos1);
			if (blockstate.collisionExtendsVertically(player.level, blockpos1, player)) {
				return blockpos1;
			}
		}

		return blockpos;
	}


}

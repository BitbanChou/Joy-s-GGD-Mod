package net.joy187.joyggd.item;

import javafx.geometry.BoundingBox;
import net.joy187.joyggd.config.ModConfigs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemFalcon extends Item {

    public ItemFalcon(Item.Properties p_41383_) {
        super(p_41383_);
    }

//    public BaseSlimeSlingItem(Properties props, SlimeType type) {
//        super(props);
//
//    }

    @Override
    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (worldIn.isClientSide || !(entityLiving instanceof PlayerEntity)) {
            return;
        }

        float f = getForce(stack, timeLeft) / 2;

        float range = 25F;
        PlayerEntity player = (PlayerEntity)entityLiving;
        Vector3d start = player.getEyePosition(1F);
        Vector3d look = player.getLookAngle();
        Vector3d direction = start.add(look.x * range, look.y * range, look.z * range);
        AxisAlignedBB bb = player.getBoundingBox().expandTowards(look.x * range, look.y * range, look.z * range).expandTowards(25, 25, 25);

        EntityRayTraceResult emop = ProjectileHelper.getEntityHitResult(worldIn, player, start, direction, bb, (e) -> e instanceof LivingEntity);
        if (emop != null) {
            LivingEntity target = (LivingEntity) emop.getEntity();
            target.hurt(DamageSource.mobAttack(entityLiving),2+target.getMaxHealth()*0.1f*f);
            double targetDist = start.distanceToSqr(target.getEyePosition(1F));
            player.moveTo(target.position());

            // cancel if there's a block in the way
            RayTraceResult mop = getPlayerPOVHitResult(worldIn, player, RayTraceContext.FluidMode.NONE);
            double blockDist = mop.getLocation().distanceToSqr(start);
            if (mop.getType() == RayTraceResult.Type.BLOCK && targetDist > blockDist) {
                playMissSound(player);
                return;
            }

            player.getCooldowns().addCooldown(this,140+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            //target.knockback(f , -look.x, -look.z);
//            if (player instanceof ServerPlayerEntity playerMP) {
//                GGDNetwork.getInstance().sendVanillaPacket(new ClientboundSetEntityMotionPacket(player), playerMP);
//            }
            onSuccess(player, stack);
        } else {
            playMissSound(player);
        }
    }

//    @Override
//    public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
//        if (!(entityLiving instanceof PlayerEntity)) {
//            return;
//        }
//
//        PlayerEntity player = (PlayerEntity) entityLiving;
//
//        // don't allow free flight when using an elytra, should use fireworks
//        if (player.isFallFlying()) {
//            return;
//        }
//
//        player.causeFoodExhaustion(0.2F);
//        player.setSprinting(true);
//        float f = getForce(stack, timeLeft);
//        float speed = f / 3F;
//        Vec3 look = player.getLookAngle();
//        player.push(
//                (look.x * speed),
//                (1 + look.y) * speed / 2f,
//                (look.z * speed));
//
//        onSuccess(player, stack);
//        //SlimeBounceHandler.addBounceHandler(player);
//        if (!worldIn.isClientSide) {
//            player.getCooldowns().addCooldown(stack.getItem(), 3);
//            onSuccess(player, stack);
//        }
//    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }



    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);
        playerIn.startUsingItem(hand);
        return ActionResult.success(itemStackIn);
    }

    /** How long it takes to use or consume an item */
    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    /** returns the action that specifies what animation to play when the items is being used */
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    public float getForce(ItemStack stack, int timeLeft) {
        int i = this.getUseDuration(stack) - timeLeft;
        float f = i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        f *= 4f;

        if (f > 6f) {
            f = 6f;
        }
        return f;
    }

    /** Plays the success sound and damages the sling */
    protected void onSuccess(PlayerEntity player, ItemStack sling) {
        //player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.SLIME_SLING.getSound(), player.getSoundSource(), 1f, 1f);
        sling.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
    }

    protected void playMissSound(PlayerEntity player) {
        //player.level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.SLIME_SLING.getSound(), player.getSoundSource(), 1f, .5f);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.falcon"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }
}

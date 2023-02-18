package net.joy187.joyggd.item;



import net.joy187.joyggd.config.ModConfigs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class ItemBirdwatcher extends Item {
    public static final int USE_DURATION = 650;
    public static final float ZOOM_FOV_MODIFIER = 0.3F;

    private static final Predicate<Entity> BIRDWATCHER_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public ItemBirdwatcher(Item.Properties p_151205_) {
        super(p_151205_);
    }

    public int getUseDuration(ItemStack p_151222_) {
        return USE_DURATION;
    }

    public UseAction getUseAnimation(ItemStack p_151224_) {
        return UseAction.BOW;
    }

    public ActionResult<ItemStack> use(World p_151218_, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
            if(playerIn.getEffect(Effects.NIGHT_VISION)==null)
                playerIn.addEffect(new EffectInstance(Effects.NIGHT_VISION, 320, 0));
            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.LEVER_CLICK, SoundCategory.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(20.0D), BIRDWATCHER_AREA)) {
                if(livingentity.getEffect(Effects.GLOWING)==null)
                    livingentity.addEffect(new EffectInstance(Effects.GLOWING, 360, 0));
            }

            playerIn.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
            playerIn.awardStat(Stats.ITEM_USED.get(this));

            playerIn.getCooldowns().addCooldown(this,360+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }
        return ActionResult.success(itemstack);
    }

    public ItemStack finishUsingItem(ItemStack p_151209_, World p_151210_, LivingEntity p_151211_) {
        this.stopUsing(p_151211_);
        return p_151209_;
    }

    public void releaseUsing(ItemStack p_151213_, World p_151214_, LivingEntity p_151215_, int p_151216_) {
        this.stopUsing(p_151215_);
    }

    private void stopUsing(LivingEntity p_151207_) {
        p_151207_.playSound(SoundEvents.LEVER_CLICK, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.birdwatcher"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }


}

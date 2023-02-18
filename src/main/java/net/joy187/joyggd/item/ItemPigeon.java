package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class ItemPigeon extends Item {

    private static final Predicate<Entity> PIGEON_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public ItemPigeon(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.PIGEON.get(), SoundCategory.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(6.0D), PIGEON_AREA)) {
                livingentity.addEffect(new EffectInstance(EffectInit.PIGEON.get(), 10 * 20, 2, true, true));
            }

            playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });

        }
        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.pigeon"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}

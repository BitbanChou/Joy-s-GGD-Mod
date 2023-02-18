package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import java.util.List;

public class ItemAdventurer extends Item {

    public ItemAdventurer(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);

        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem()))
        {
            //playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.BOOMTICK.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            playerIn.addEffect(new EffectInstance(EffectInit.ADVENTURER.get(), 30*20, 0, true, true));
            playerIn.getCooldowns().addCooldown(this,600+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.adventure"));
        }
        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

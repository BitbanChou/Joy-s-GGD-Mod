package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.property.Properties;


import java.util.List;

public class ItemEsper extends Item {
    public ItemEsper(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {

        if(!playerIn.getCooldowns().isOnCooldown(stack.getItem()))
        {
            if(!(entity instanceof EndermanEntity) && !(entity instanceof SpiderEntity) && !(entity instanceof CreeperEntity))
            {
                entity.addEffect(new EffectInstance(EffectInit.ESPER.get(), 10 * 20, 1, true, true));
            }
            else{
                entity.setHealth(0);
            }
            playerIn.getCooldowns().addCooldown(this,400+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });

        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.esper"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

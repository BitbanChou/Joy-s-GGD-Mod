package net.joy187.joyggd.item;


import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

public class ItemInvisibility extends Item {

    public ItemInvisibility(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);

        playerIn.addEffect(new EffectInstance(Effects.INVISIBILITY, 6*20, 0, false, false));
        playerIn.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 6*20, 1, false, false));

        itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
            p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
        });

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.invisibility"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}

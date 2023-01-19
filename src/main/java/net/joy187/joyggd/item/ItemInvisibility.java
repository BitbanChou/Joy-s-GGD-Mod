package net.joy187.joyggd.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemInvisibility extends Item {

    public ItemInvisibility(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);

        playerIn.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 6*20, 0, false, false));
        playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6*20, 1, false, false));

        itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
            p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
        });

        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.invisibility"));
        }
    }

}

package net.joy187.joyggd.item;

import net.joy187.joyggd.Main;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemDuckKnife extends SwordItem {
    public ItemDuckKnife() {
        super(CustomItemTier.TOOL_DUCKKNIFE,0,-3,new Properties().tab(Main.ITEM_TAB));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if((pTarget.getHealth()-CustomItemTier.TOOL_DUCKKNIFE.getAttackDamageBonus())<1E-6)
        {
            pAttacker.moveTo(pTarget.getX(),pTarget.getY(),pTarget.getZ());
        }

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.duckknife"));
        }
    }

}

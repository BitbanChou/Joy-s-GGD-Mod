package net.joy187.joyggd.item;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;

public class ItemDuckKnife extends SwordItem {
    public ItemDuckKnife() {
        super(CustomItemTier.TOOL_DUCKKNIFE,0,-3,new Properties().tab(Main.ITEM_TAB));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        float extradamage=0f;
        if(!NBTHelper.hasTag(pStack,"slaynumber"))
        {
            CompoundNBT counter = new CompoundNBT();
            counter.putString("slaynumber","0");
            pStack.setTag(counter);
            //NBTHelper.putTag(pStack, "slaynumber", counter);
        }
        else{
            extradamage=Float.parseFloat(pStack.getTag().getString("slaynumber"));
        }
        float damage=Math.min(ModConfigs.Client.maxDamage.get(),
                CustomItemTier.TOOL_SHERIFF.getAttackDamageBonus()+extradamage*ModConfigs.Client.amplifier.get());

        if((pTarget.getHealth()-damage)<1E-6)
        {
            pAttacker.moveTo(pTarget.getX(),pTarget.getY(),pTarget.getZ());
            float k;
            if(NBTHelper.hasTag(pStack, "slaynumber"))
            {
                k=Float.parseFloat(pStack.getTag().getString("slaynumber"))+1f;
                pStack.getTag().putString("slaynumber",Float.toString(k));
            }
        }

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.duckknife"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

        if(stack.hasTag()) {
            String number = stack.getTag().getString("slaynumber");

            tooltip.add(new TranslationTextComponent("tooltip.slay", TextFormatting.RED+number));
        }
    }

}

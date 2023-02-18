package net.joy187.joyggd.item;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;

public class ItemVigilante extends SwordItem {

    public ItemVigilante() {
        super(CustomItemTier.TOOL_VIGILANTE,0,-2,new Properties().tab(Main.ITEM_TAB));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        float extradamage=0f;
        if(!NBTHelper.hasTag(pStack,"slaynumber"))
        {
            CompoundNBT counter = new CompoundNBT();
            counter.putString("slaynumber","0");
            pStack.setTag(counter);
        }
        else{
            extradamage=Float.parseFloat(pStack.getTag().getString("slaynumber"));
        }

        float damage=Math.min(ModConfigs.Client.maxDamage.get(),
                CustomItemTier.TOOL_VIGILANTE.getAttackDamageBonus()+extradamage*ModConfigs.Client.amplifier.get());
        //System.out.println("damage："+damage);

        if(!((PlayerEntity)pAttacker).getCooldowns().isOnCooldown(this))
            pTarget.hurt(DamageSource.mobAttack(pAttacker),damage);

        if(pTarget.getHealth()<damage)
        {
            float k;
            if(NBTHelper.hasTag(pStack, "slaynumber"))
            {
                k=Float.parseFloat(pStack.getTag().getString("slaynumber"))+1f;
                pStack.getTag().putString("slaynumber",Float.toString(k));
            }

            if(pAttacker instanceof PlayerEntity)
            {
                ((PlayerEntity)pAttacker).getCooldowns().addCooldown(this, Math.round(damage)+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            }
        }


        //pTarget.addEffect(new EffectInstance(EffectInit.DEADLY.get(), 20*3, 0, true, true), pAttacker);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.vigilante"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
        if(stack.hasTag()) {
            String number = stack.getTag().getString("slaynumber");
            tooltip.add(new TranslationTextComponent("tooltip.slay", TextFormatting.RED+number));
            //tooltip.add(new TranslationTextComponent("tooltip.slay", TextFormatting.RED+Float.toString(Float.parseFloat(number)*ModConfigs.Client.amplifier.get())));
        }
    }

}

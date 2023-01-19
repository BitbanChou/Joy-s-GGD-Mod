package net.joy187.joyggd.item;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

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
            CompoundTag counter = new CompoundTag();
            counter.putString("slaynumber","0");
            pStack.setTag(counter);
        }
        else{
            extradamage=Float.parseFloat(pStack.getTag().getString("slaynumber"));
        }

        float damage=Math.min(ModConfigs.Client.maxDamage.get(),
                CustomItemTier.TOOL_VIGILANTE.getAttackDamageBonus()+extradamage*ModConfigs.Client.amplifier.get());
        //System.out.println("damage："+damage);

        if(!((Player)pAttacker).getCooldowns().isOnCooldown(this))
            pTarget.hurt(DamageSource.mobAttack(pAttacker),damage);

        if(pTarget.getHealth()<damage)
        {
            float k;
            if(NBTHelper.hasTag(pStack, "slaynumber"))
            {
                k=Float.parseFloat(pStack.getTag().getString("slaynumber"))+1f;
                pStack.getTag().putString("slaynumber",Float.toString(k));
            }

            if(pAttacker instanceof Player)
            {
                ((Player)pAttacker).getCooldowns().addCooldown(this, Math.round(damage)+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            }
        }


        //pTarget.addEffect(new MobEffectInstance(EffectInit.DEADLY.get(), 20*3, 0, true, true), pAttacker);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.vigilante"));
        }
        if(stack.hasTag()) {
            String number = stack.getTag().getString("slaynumber");
            tooltip.add(Component.translatable("tooltip.slay", ChatFormatting.RED+number));
            //tooltip.add(Component.translatable("tooltip.slay", ChatFormatting.RED+Float.toString(Float.parseFloat(number)*ModConfigs.Client.amplifier.get())));
        }
    }

}

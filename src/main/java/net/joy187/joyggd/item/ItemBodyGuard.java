package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.CommonFunctions;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;

public class ItemBodyGuard extends Item {

    public ItemBodyGuard(Properties p_41383_) {
        super(p_41383_);
    }

    PlayerEntity owner;

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
        if(!playerIn.getCooldowns().isOnCooldown(stack.getItem()))
        {
            this.owner=playerIn;
            ItemStack itemstack = playerIn.getItemInHand(hand);
            if(!NBTHelper.hasTag(itemstack,"isGuarding"))
            {
                CompoundNBT counter = new CompoundNBT();
                counter.putString("isGuarding","1");
                itemstack.setTag(counter);
            }

            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.FINISH.get(), SoundCategory.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            entity.heal(entity.getMaxHealth()*0.25F);
            CommonFunctions.spawnHaloParticleAround(entity, entity.getBbHeight());
            playerIn.getCooldowns().addCooldown(this,240+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }
        return ActionResultType.SUCCESS;
    }

    public boolean isFoil(ItemStack stack) {
        return NBTHelper.hasTag(stack,"isGuarding");
    }

    public void inventoryTick(@Nullable ItemStack stack, World level, Entity entity, int p_41407_, boolean p_41408_) {

        if (!level.isClientSide) {
            if(this.owner!=null)
            {
                //System.out.println("0");
                for (int i = 0; i < this.owner.inventory.getContainerSize(); ++i)
                {
                    ItemStack itemstack = this.owner.inventory.getItem(i);
                    if(itemstack.getItem() == ItemInit.BODYGUARD.get() && this.owner.getHealth()<2F)
                    {
                        //System.out.println("1");
                        this.owner.awardStat(Stats.ITEM_USED.get(ItemInit.BODYGUARD.get()));
                        itemstack.shrink(1);
                        this.owner.setHealth(10.0F);
                        this.owner.removeAllEffects();
                        this.owner.addEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
                        this.owner.level.broadcastEntityEvent(this.owner, (byte) 35);
                        if(this.owner instanceof ServerPlayerEntity)
                        {
                            CriteriaTriggers.USED_TOTEM.trigger((ServerPlayerEntity)this.owner, itemstack);
                        }
                    }
                }
                //ItemStack itemstack = this.owner.getItemInHand(Hand.MAIN_HAND);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.bodyguard"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }
}

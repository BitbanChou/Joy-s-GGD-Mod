package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.CommonFunctions;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBodyGuard extends Item {

    public ItemBodyGuard(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
        if(!playerIn.getCooldowns().isOnCooldown(stack.getItem()))
        {
            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.FINISH.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            entity.heal(entity.getMaxHealth()*0.25F);
            CommonFunctions.spawnHaloParticleAround(entity, ParticleTypes.HEART, entity.getBbHeight());
            playerIn.getCooldowns().addCooldown(this,240+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.bodyguard"));
        }
    }
}

package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public class ItemPigeon extends Item {

    private static final Predicate<Entity> PIGEON_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof Player);
    };

    public ItemPigeon(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
            playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.PIGEON.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(6.0D), PIGEON_AREA)) {
                livingentity.addEffect(new MobEffectInstance(EffectInit.PIGEON.get(), 10 * 20, 1, true, true));
            }

            playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });

        }
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.pigeon"));
        }
    }

}

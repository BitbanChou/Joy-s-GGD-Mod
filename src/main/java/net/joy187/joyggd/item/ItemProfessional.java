package net.joy187.joyggd.item;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.entity.EntityBullet;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ItemProfessional extends ProjectileWeaponItem implements Vanishable {

	public ItemProfessional(Properties name) {
		  super(name);
	}

    public void releaseUsing(ItemStack p_77615_1_, Level level, LivingEntity p_77615_3_, int p_77615_4_) {
        if (p_77615_3_ instanceof Player) {
            Player Player = (Player)p_77615_3_;
            boolean flag = Player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_77615_1_) > 0;
//            ItemStack itemstack = Player.getProjectile(p_77615_1_);

            //ItemStack itemstack = this.findAmmo(Player);

            int i = this.getUseDuration(p_77615_1_) - p_77615_4_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_77615_1_, level, Player, i,  flag);
            if (i < 0) return;

//            if (flag) {
//
//
                float f = getPowerForTime(i);
//                if (!((double)f < 0.1D)) {
//                    boolean flag1 = Player.getAbilities().instabuild;
//                    if (!level.isClientSide) {
//                        ItemM1851B arrowitem = (ItemM1851B)(itemstack.getItem() instanceof ItemM1851B ? itemstack.getItem() : ItemInit.M1851B.get().asItem());
                        EntityBullet abstractarrowentity = new EntityBullet(level, Player); //arrowitem.createArrow(level, itemstack, Player);

                        abstractarrowentity = customArrow(abstractarrowentity);
                        //abstractarrowentity.setItem(itemstack);
                        abstractarrowentity.shootFromRotation(Player, Player.getXRot(), Player.getYRot(), 0.0F, f * 30.0F, 0.75F);

                        abstractarrowentity.level.addParticle(ParticleTypes.FLAME, abstractarrowentity.getX(), abstractarrowentity.getY(), abstractarrowentity.getZ(), abstractarrowentity.position().x , abstractarrowentity.position().y, abstractarrowentity.position().z);
                        abstractarrowentity.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 4.5F,4.5F);


                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, p_77615_1_) > 0) {
                            abstractarrowentity.setSecondsOnFire(100);
                        }

                        p_77615_1_.hurtAndBreak(1, Player, (p_220009_1_) -> {
                            p_220009_1_.broadcastBreakEvent(Player.getUsedItemHand());
                        });


                        abstractarrowentity.level.addParticle(ParticleTypes.FLAME, abstractarrowentity.getX(), abstractarrowentity.getY(), abstractarrowentity.getZ(), abstractarrowentity.position().x * -0.2D, 0.08D, abstractarrowentity.position().z * -0.2D);

                        level.addFreshEntity(abstractarrowentity);
//                    }
                    level.playSound((Player)null, Player.getX(), Player.getY(), Player.getZ(), SoundEvents.SNOW_GOLEM_SHOOT, SoundSource.PLAYERS, 4.5F, 4.5F / (Player.getRandom().nextFloat() * 0.2F + 1.2F) + f * 0.5F);

                    Player.awardStat(Stats.ITEM_USED.get(this));
//                }
//            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem())) {
            boolean flag = true;

            playerIn.getCooldowns().addCooldown(this,220+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, playerIn, handIn, flag);
            if (ret != null) return ret;

            if (!playerIn.getAbilities().instabuild && !flag) {
                return InteractionResultHolder.fail(itemstack);
            } else {
                playerIn.startUsingItem(handIn);
                return InteractionResultHolder.consume(itemstack);
            }

        }
        return InteractionResultHolder.fail(itemstack);
    }

    public static float getPowerForTime(int p_185059_0_) {
        float f = (float)p_185059_0_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }


    public int getUseDuration(ItemStack p_77626_1_) {
        return 500;
    }


    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_OR_FIREWORK;
    }

    public  EntityBullet customArrow(EntityBullet arrow) {
        return arrow;
    }

    public int getDefaultProjectileRange() {
        return 15;
    }


    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity)
    {
        return true;
    }
	

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.professional"));
        }
    }

}
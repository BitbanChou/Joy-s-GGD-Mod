package net.joy187.joyggd.item;

import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

import javax.annotation.Nullable;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.entity.EntityBullet;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


public class ItemProfessional extends ShootableItem implements IVanishable {

	public ItemProfessional(Properties name) {
		  super(name);
	}

    public void releaseUsing(ItemStack p_77615_1_, World level, LivingEntity p_77615_3_, int p_77615_4_) {
        if (p_77615_3_ instanceof PlayerEntity) {
            PlayerEntity PlayerEntity = (PlayerEntity)p_77615_3_;
            boolean flag = PlayerEntity.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, p_77615_1_) > 0;
//            ItemStack itemstack = PlayerEntity.getProjectile(p_77615_1_);

            //ItemStack itemstack = this.findAmmo(PlayerEntity);

            int i = this.getUseDuration(p_77615_1_) - p_77615_4_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_77615_1_, level, PlayerEntity, i,  flag);
            if (i < 0) return;

//            if (flag) {
//
//
                float f = getPowerForTime(i);
//                if (!((double)f < 0.1D)) {
//                    boolean flag1 = PlayerEntity.abilities.instabuild;
//                    if (!level.isClientSide) {
//                        ItemM1851B arrowitem = (ItemM1851B)(itemstack.getItem() instanceof ItemM1851B ? itemstack.getItem() : ItemInit.M1851B.get().asItem());
                        EntityBullet abstractarrowentity = new EntityBullet(level, PlayerEntity); //arrowitem.createArrow(level, itemstack, PlayerEntity);

                        abstractarrowentity = customArrow(abstractarrowentity);
                        //abstractarrowentity.setItem(itemstack);
                        abstractarrowentity.shootFromRotation(PlayerEntity, PlayerEntity.xRot, PlayerEntity.yRot, 0.0F, f * 30.0F, 0.75F);

                        abstractarrowentity.level.addParticle(ParticleTypes.FLAME, abstractarrowentity.getX(), abstractarrowentity.getY(), abstractarrowentity.getZ(), abstractarrowentity.position().x , abstractarrowentity.position().y, abstractarrowentity.position().z);
                        abstractarrowentity.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 4.5F,4.5F);


                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, p_77615_1_) > 0) {
                            abstractarrowentity.setSecondsOnFire(100);
                        }

                        p_77615_1_.hurtAndBreak(1, PlayerEntity, (p_220009_1_) -> {
                            p_220009_1_.broadcastBreakEvent(PlayerEntity.getUsedItemHand());
                        });


                        abstractarrowentity.level.addParticle(ParticleTypes.FLAME, abstractarrowentity.getX(), abstractarrowentity.getY(), abstractarrowentity.getZ(), abstractarrowentity.position().x * -0.2D, 0.08D, abstractarrowentity.position().z * -0.2D);

                        level.addFreshEntity(abstractarrowentity);
//                    }
                    level.playSound((PlayerEntity)null, PlayerEntity.getX(), PlayerEntity.getY(), PlayerEntity.getZ(), SoundEvents.SNOW_GOLEM_SHOOT, SoundCategory.PLAYERS, 4.5F, 4.5F / (PlayerEntity.getRandom().nextFloat() * 0.2F + 1.2F) + f * 0.5F);

                    PlayerEntity.awardStat(Stats.ITEM_USED.get(this));
//                }
//            }
        }
    }

    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem())) {
            boolean flag = true;

            playerIn.getCooldowns().addCooldown(this,220+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, playerIn, handIn, flag);
            if (ret != null) return ret;

            if (!playerIn.abilities.instabuild && !flag) {
                return ActionResult.fail(itemstack);
            } else {
                playerIn.startUsingItem(handIn);
                return ActionResult.consume(itemstack);
            }

        }
        return ActionResult.fail(itemstack);
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
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.professional"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}
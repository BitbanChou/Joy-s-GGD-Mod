package net.joy187.joyggd.item;


import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.entity.EntityBullet;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import java.util.List;

public class ItemAssassin extends Item {
    public ItemAssassin(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem())) {
            EntityBullet abstractarrowentity = new EntityBullet(level, playerIn); //arrowitem.createArrow(level, itemstack, Player);

//        abstractarrowentity = customArrow(abstractarrowentity);
            //abstractarrowentity.setItem(itemstack);
            abstractarrowentity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 15.5F, 0.75F);

            abstractarrowentity.level.addParticle(ParticleTypes.FLAME, abstractarrowentity.getX(), abstractarrowentity.getY(), abstractarrowentity.getZ(), abstractarrowentity.position().x, abstractarrowentity.position().y, abstractarrowentity.position().z);
            abstractarrowentity.playSound(SoundInit.ASSASSINSHOT.get(), 1F, 1F);
            abstractarrowentity.damage*=1.25;

            level.addFreshEntity(abstractarrowentity);

            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemstack) > 0) {
                abstractarrowentity.setSecondsOnFire(100);
            }

            playerIn.getCooldowns().addCooldown(this,220+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            itemstack.hurtAndBreak(1, playerIn, (p_220009_1_) -> {
                p_220009_1_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.assassin"));
        }
        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.util.MathUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;


public class ItemDDodo extends Item {

    private static final Predicate<Entity> DDODO_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public ItemDDodo(Item.Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);

        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem()))
        {
            //playerIn.level.playSound(playerIn, playerIn.blockPosition(), SoundInit.BOOMTICK.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (playerIn.level.getRandom().nextFloat() * 0.4F + 0.8F));
            BlockPos blockpos = MathUtils.getOnPos(playerIn).above();


            int brightness=level.getRawBrightness(blockpos,0);

            //Block block = playerIn.getBlockStateOn().getBlock();
            //System.out.println(brightness);
            int radius=brightness+2;
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(radius), DDODO_AREA)) {
                //livingentity.addEffect(new EffectInstance(EffectInit.PIGEON.get(), 10 * 20, 2, true, true));
//                double d1 = 0.25D * (1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
//                double d0 = 1.5D *(15-brightness)* (1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                //double d12 = Math.sqrt(LivingEntity.distanceToSqr(playerIn)) / (double)f2;
                double d0 = (livingentity.getX() - playerIn.getX())* (1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                double d1 = livingentity.getZ() - playerIn.getZ();
                double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
                livingentity.push(d0 / d2 * radius*-1, 0.2D, d1 / d2 * radius*-1);
                //livingentity.push(vec32.x() * d0, vec32.y() * d1, vec32.z() * d0);
            }

            playerIn.getCooldowns().addCooldown(this,200+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.ddodo"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}


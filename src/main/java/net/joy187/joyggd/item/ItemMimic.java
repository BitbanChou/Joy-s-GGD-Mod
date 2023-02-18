package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.EffectInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import java.util.List;
import java.util.function.Predicate;

public class ItemMimic extends Item {

    private static final Predicate<Entity> MIMIC_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    public ItemMimic(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
        if(entity instanceof MonsterEntity)
        {
            if(((MonsterEntity) entity).getTarget()==playerIn)
            {
                ((MonsterEntity) entity).setTarget(null);
            }
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);

        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem()))
        {
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(10), MIMIC_AREA)) {
                if(livingentity instanceof MobEntity)
                {
                    if(((MobEntity) livingentity).getTarget()==playerIn)
                    {
                        livingentity.addEffect(new EffectInstance(EffectInit.MIMIC.get(), 10 * 20, 0, true, true));
                    }
                }

            }

            playerIn.getCooldowns().addCooldown(this,340+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        return ActionResult.success(itemstack);
    }


    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.mimic"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.util.ItemUtils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import java.util.List;
import java.util.function.Predicate;

public class ItemSpy extends Item {

    public ItemSpy(Properties p_41383_) {
        super(p_41383_);
    }

    private static final Predicate<Entity> SPY_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem())) {
            for (LivingEntity livingentity : playerIn.level.getEntitiesOfClass(LivingEntity.class, playerIn.getBoundingBox().inflate(5), SPY_AREA)) {
                if (livingentity instanceof AbstractVillagerEntity) {
                    ((AbstractVillagerEntity) livingentity).setUnhappyCounter(0);
                }

                if(livingentity instanceof PiglinEntity)
                {
                    if(((PiglinEntity) livingentity).getTarget() == playerIn)
                    {
                        ((PiglinEntity) livingentity).setTarget(null);
                    }
                    ItemStack resultStack = new ItemStack(Items.GOLD_INGOT);
                    ItemUtils.spawnItemEntity(level, resultStack.copy(),
                            livingentity.getX() + 0.5, livingentity.getY() + 1.0, livingentity.getZ() + 0.5,
                            level.random.nextGaussian() * (double) 0.01F, 0.1F, level.random.nextGaussian() * (double) 0.01F);

                }

                if(livingentity instanceof MobEntity)
                {
                    if(((MobEntity) livingentity).getTarget() == playerIn)
                    {
                        if(playerIn.getLastHurtByMob()!=null && playerIn.getLastHurtByMob()!=livingentity)
                            ((MobEntity) livingentity).setTarget(playerIn.getLastHurtByMob());
                    }
                }

            }

            playerIn.getCooldowns().addCooldown(this,250+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });

        }

        return ActionResult.success(itemstack);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity  playerIn, LivingEntity livingentity, Hand hand) {

        if(!playerIn.getCooldowns().isOnCooldown(stack.getItem()))
        {
            if(livingentity instanceof AbstractVillagerEntity)
            {
//                entity.discard();
//                Villager villager=new Villager(EntityType.VILLAGER,playerIn.level);
//                if(villager.getVillagerData().getProfession() == VillagerProfession.NONE)
//                {
//                    int co = playerIn.getRandom().nextInt(6);
//                    if(co==0) villager.getVillagerData().setProfession(VillagerProfession.ARMORER);
//                    else if(co==1) villager.getVillagerData().setProfession(VillagerProfession.BUTCHER);
//                    else if(co==2)villager.getVillagerData().setProfession(VillagerProfession.CARTOGRAPHER);
//                    else if(co==3)villager.getVillagerData().setProfession(VillagerProfession.CLERIC);
//                    else if(co==4)villager.getVillagerData().setProfession(VillagerProfession.FARMER);
//                    else if(co==5)villager.getVillagerData().setProfession(VillagerProfession.FISHERMAN);
//                    System.out.println(villager.getVillagerData().getProfession());
//                }
//                villager.copyPosition(entity);
//                playerIn.level.addFreshEntity(villager);
                ItemStack resultStack = new ItemStack(Items.EMERALD);
                ItemUtils.spawnItemEntity(playerIn.level, resultStack.copy(),
                        livingentity.getX() + 0.5, livingentity.getY() + 1.0, livingentity.getZ() + 0.5,
                        playerIn.level.random.nextGaussian() * (double) 0.01F, 0.1F, playerIn.level.random.nextGaussian() * (double) 0.01F);

            }


            playerIn.getCooldowns().addCooldown(this,100+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.spy"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

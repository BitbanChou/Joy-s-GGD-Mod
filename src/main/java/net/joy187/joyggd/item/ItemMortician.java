package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemMortician extends Item {
    public ItemMortician(Item.Properties p_41383_) {
        super(p_41383_);
    }

    public ActionResultType useOn(ItemUseContext contex) {
        PlayerEntity player = contex.getPlayer();
        World level = contex.getLevel();
        BlockPos blockpos = contex.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Block block=blockstate.getBlock();
        if(player!=null && !player.getCooldowns().isOnCooldown(this))
        {

            if(block== BlockInit.GOOSEBODY.get())
            {
                Random ran = new Random();
                int co=ran.nextInt(50);
                ItemStack resultStack = new ItemStack(ItemInit.GOOSE_SPAWN_EGG.get());
                if(co>10 && co<15) resultStack = new ItemStack(ItemInit.VIGILANTE.get());
                else if(co>=15 && co<20) resultStack = new ItemStack(ItemInit.MIMIC.get());
                else if(co>=20 && co<25) resultStack = new ItemStack(ItemInit.ASSASSIN.get());
                else if(co>=25 && co<30) resultStack = new ItemStack(ItemInit.DEMOLITIONIST.get());
                else if(co>=30 && co<35) resultStack = new ItemStack(ItemInit.DODO.get());
                else if(co>=35 && co<40) resultStack = new ItemStack(ItemInit.PIGEON.get());
                else if(co>=40 && co<45) resultStack = new ItemStack(ItemInit.ADVENTURER.get());
                else if(co>=45 && co<50) resultStack = new ItemStack(ItemInit.FALCON.get());
                else if(co>=50 && co<55) resultStack = new ItemStack(ItemInit.INVISIBILITY.get());
                else if(co>=55 && co<60) resultStack = new ItemStack(ItemInit.DUCK_SPAWN_EGG.get());

                ItemUtils.spawnItemEntity(level, resultStack.copy(),
                        blockpos.getX() , blockpos.getY() , blockpos.getZ() ,
                        level.random.nextGaussian() * (double) 0.01F, 0.1F, level.random.nextGaussian() * (double) 0.01F);


            }

            level.removeBlock(blockpos, false);
            //level.playSound(player, player.blockPosition(), SoundInit.PELEBALL.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this,300+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            player.getMainHandItem().hurtAndBreak(1, player, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
            });

            return ActionResultType.sidedSuccess(level.isClientSide());
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.mortician"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }
}

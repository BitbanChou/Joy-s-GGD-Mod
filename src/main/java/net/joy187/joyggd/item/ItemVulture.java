package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.List;

public class ItemVulture extends Item {

    public ItemVulture(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResult useOn(UseOnContext contex) {
        Player player = contex.getPlayer();
        Level level = contex.getLevel();
        BlockPos blockpos = contex.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Block block=blockstate.getBlock();
        if(player!=null && !player.getCooldowns().isOnCooldown(this))
        {
            if (!player.canEat(false) || blockstate.getMaterial() == Material.PORTAL
            || block==Blocks.BEDROCK || block==Blocks.END_PORTAL_FRAME
                || block instanceof BaseEntityBlock)
            {
                //System.out.println("0");
                return InteractionResult.PASS;
            }

            if(block==BlockInit.GOOSEBODY.get())
            {
                player.getFoodData().eat(10,1);
            }
            else if(blockstate.getMaterial() == Material.WOOD)
            {
                player.getFoodData().eat(2,0.2F);
            }
            else if(blockstate.getMaterial() == Material.STONE)
            {
                player.getFoodData().eat(1,0.1F);
            }
            else if(blockstate.getMaterial() == Material.CAKE)
            {
                player.getFoodData().eat(18,1);
            }
            else if(blockstate.getMaterial() == Material.VEGETABLE ||
                    blockstate.getMaterial() == Material.EGG)
            {
                player.getFoodData().eat(5,0.5F);
            }
            else if(blockstate.getMaterial() == Material.METAL || blockstate.getMaterial() == Material.HEAVY_METAL)
            {
                player.getFoodData().eat(1,0.05F);
            }
            else if(blockstate.getMaterial() == Material.GRASS || blockstate.getMaterial() == Material.LEAVES
            || blockstate.getMaterial() == Material.SAND)
            {
                player.getFoodData().eat(3,0.3F);
            }
            else if(blockstate.getMaterial() == Material.CACTUS)
            {
                player.setHealth(0.0F);
            }
            else if(blockstate.getMaterial() == Material.LAVA || blockstate.getMaterial() == Material.FIRE)
            {
                player.setSecondsOnFire(6);
            }
            else if(blockstate.getMaterial() == Material.SNOW || blockstate.getMaterial() == Material.POWDER_SNOW
            || blockstate.getMaterial() == Material.TOP_SNOW)
            {
                player.hurt(DamageSource.FREEZE, (float)2);
            }
            else{
                player.getFoodData().eat(0,0);
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10*20, 1, true, true));
            }
            level.setBlock(blockpos,Blocks.AIR.defaultBlockState(),4);
            level.playSound(player, player.blockPosition(), SoundInit.PELEBALL.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this,100+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            player.getMainHandItem().hurtAndBreak(1, player, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
            });

            //System.out.println("1");
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.vulture"));
        }
    }

}

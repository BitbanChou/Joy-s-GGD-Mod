package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemVulture extends Item {

    public ItemVulture(Properties p_41383_) {
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
            if (!player.canEat(false) || blockstate.getMaterial() == Material.PORTAL
            || block== Blocks.BEDROCK || block==Blocks.END_PORTAL_FRAME
                || block instanceof ContainerBlock)
            {
                //System.out.println("0");
                return ActionResultType.PASS;
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
            else if(blockstate.getMaterial() == Material.SNOW || blockstate.getMaterial() == Material.SNOW
            || blockstate.getMaterial() == Material.TOP_SNOW)
            {
                player.hurt(DamageSource.badRespawnPointExplosion(), (float)2);
            }
            else{
                player.getFoodData().eat(0,0);
                player.addEffect(new EffectInstance(Effects.CONFUSION, 10*20, 1, true, true));
            }
            level.removeBlock(blockpos,false);
            level.playSound(player, player.blockPosition(), SoundInit.PELEBALL.get(), SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this,100+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            player.getMainHandItem().hurtAndBreak(1, player, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
            });

            //System.out.println("1");
            return ActionResultType.sidedSuccess(level.isClientSide());
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.vulture"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}

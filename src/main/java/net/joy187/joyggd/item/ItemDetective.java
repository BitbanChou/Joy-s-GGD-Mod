package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.util.ItemUtils;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import java.util.List;

public class ItemDetective extends Item {
    public ItemDetective(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(itemstack.getItem()))
        {
            BlockPos pos = new BlockPos(playerIn.getPosition(1));
            boolean flag=false;
            for(int x=-4;x<=4;x++)
            {
                for(int z=-4;z<=4;z++)
                {
                    for(int y = pos.getY(); y>= Math.max(-64,pos.getY()-20); y--)
                    {
                        BlockPos curPos = new BlockPos(x+pos.getX(),y,z+pos.getZ());
                        BlockState blockstate = level.getBlockState(curPos);
                        if(     blockstate.getBlock() == Blocks.DIAMOND_ORE ||
                                blockstate.getBlock() == Blocks.EMERALD_ORE ||
                                blockstate.getBlock()==Blocks.IRON_ORE ||
                                blockstate.getBlock()==Blocks.ANCIENT_DEBRIS ||
                                blockstate.is(Blocks.LAPIS_ORE) ||
                                blockstate.is(Blocks.REDSTONE_ORE) ||
                                blockstate.is(BlockTags.GOLD_ORES) ||
                                blockstate.getBlock() instanceof OreBlock)
                        {
                            level.removeBlock(curPos,false);
                            //level.setBlock(curPos,Blocks.AIR.defaultBlockState(),4);
                            ItemStack resultStack = new ItemStack(blockstate.getBlock().asItem());
                            ItemUtils.spawnItemEntity(level, resultStack.copy(),
                                    pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                                    level.random.nextGaussian() * (double) 0.01F, 0.1F, level.random.nextGaussian() * (double) 0.01F);
                        }

                        if(blockstate.is(BlockTags.PORTALS) || blockstate.getBlock()==Blocks.END_PORTAL_FRAME)
                        {
                            flag=true;
                        }

                    }
                }
            }
            if(flag){
                //System.out.println("YYY");
                if(!NBTHelper.hasTag(itemstack,"found"))
                {
                    CompoundNBT counter = new CompoundNBT();
                    counter.putString("found","1");
                    itemstack.setTag(counter);
                }
                else itemstack.getTag().putString("found","1");
            }
            else{
                //System.out.println("NNN");
                if(!NBTHelper.hasTag(itemstack,"found"))
                {
                    CompoundNBT counter = new CompoundNBT();
                    counter.putString("found","0");
                    itemstack.setTag(counter);
                }
                else itemstack.getTag().putString("found","0");
            }

            playerIn.getCooldowns().addCooldown(this,360+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            playerIn.getMainHandItem().hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
        }

        return ActionResult.success(itemstack);
    }

    public boolean isFoil(ItemStack stack) {
        if(stack.hasTag())
        {
            if(stack.getTag().getString("found")=="1")
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.isFoil(stack)){
            tooltip.add(new TranslationTextComponent("tooltip.found"));
        }

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.detective"));
        }
        else tooltip.add(new TranslationTextComponent("tooltip.shift"));


    }
}

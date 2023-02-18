package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.util.MathUtils;
import net.minecraft.block.*;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;


import java.util.List;

public class ItemMechanic extends Item {
    public ItemMechanic(Item.Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this)) {

            BlockPos center= MathUtils.getOnPos(playerIn);

            for(int i=-8;i<8;i++)
            {
                for(int j=-8;j<8;j++)
                {
                    for(int z=-8;z<8;z++)
                    {
                        BlockPos blockpos = new BlockPos(center.getX()+i,center.getY()+j,center.getZ()+z);
                        BlockState state=level.getBlockState(blockpos);

                        if(state.getBlock() instanceof RedstoneLampBlock)
                        {
                            Boolean flag = state.getValue(RedstoneLampBlock.LIT);
                            if(flag)
                            {
                                level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(false)), 11);
                            }
                            else level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);

                        }

                        if(state.getBlock() instanceof RedstoneTorchBlock)
                        {
                            Boolean flag = state.getValue(RedstoneTorchBlock.LIT);
                            if(flag)
                            {
                                level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(false)), 11);
                            }
                            else level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);

                        }

                        if(state.getBlock() instanceof FenceGateBlock)
                        {
                            Boolean flag = state.getValue(FenceGateBlock.OPEN);
                            if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(false)), 11);
                            else level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(true)), 11);
                        }

                        if(state.getBlock() instanceof DoorBlock)
                        {
                            Boolean flag = state.getValue(DoorBlock.OPEN);
                            if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(false)), 11);
                            else level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(true)), 11);
                        }

                        if(state.getBlock() instanceof TrapDoorBlock)
                        {
                            Boolean flag = state.getValue(TrapDoorBlock.OPEN);
                            if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(false)), 11);
                            else level.setBlock(blockpos, state.setValue(BlockStateProperties.OPEN, Boolean.valueOf(true)), 11);
                        }

                        if(state.hasProperty(BlockStateProperties.POWERED))
                        {

                            if(state.getBlock() instanceof AbstractButtonBlock)
                            {

                                Boolean flag = state.getValue(AbstractButtonBlock.POWERED);
                                if(flag)
                                    level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);

                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);

                            }

                            if(state.getBlock() instanceof LeverBlock)
                            {
                                Boolean flag = state.getValue(LeverBlock.POWERED);
                                if(flag)
                                    level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);

                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);

                            }



                            if(state.getBlock() instanceof PoweredRailBlock)
                            {
                                Boolean flag = state.getValue(PoweredRailBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                            if(state.getBlock() instanceof DetectorRailBlock)
                            {
                                Boolean flag = state.getValue(DetectorRailBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                            if(state.getBlock() instanceof BellBlock)
                            {
                                Boolean flag = state.getValue(BellBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                            if(state.getBlock() instanceof BellBlock)
                            {
                                Boolean flag = state.getValue(BellBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                            if(state.getBlock() instanceof TripWireBlock)
                            {
                                Boolean flag = state.getValue(TripWireBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                            if(state.getBlock() instanceof PressurePlateBlock)
                            {
                                Boolean flag = state.getValue(PressurePlateBlock.POWERED);
                                if(flag) level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(false)), 11);
                                else level.setBlock(blockpos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(true)), 11);
                            }

                        }


                    }
                }
            }


            playerIn.getCooldowns().addCooldown(this,200+ Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
            itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
            });
            level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.LEVER_CLICK, SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        }
        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.mechanic"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

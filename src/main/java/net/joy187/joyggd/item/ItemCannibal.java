package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;


public class ItemCannibal extends ItemVulture {
    public ItemCannibal(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if(pAttacker instanceof PlayerEntity)
        {
            if(!((PlayerEntity)pAttacker).getCooldowns().isOnCooldown(this))
            {
                float extradamage=6F;
                if(!NBTHelper.hasTag(pStack,"slaynumber"))
                {
                    CompoundNBT counter = new CompoundNBT();
                    counter.putString("slaynumber","0");
                    pStack.setTag(counter);
                    //NBTHelper.putTag(pStack, "slaynumber", counter);
                }
                else{
                    extradamage= Float.parseFloat(pStack.getTag().getString("slaynumber"));
                }
                float damage= Math.min(ModConfigs.Client.maxDamage.get(),
                        CustomItemTier.TOOL_SHERIFF.getAttackDamageBonus()+extradamage* ModConfigs.Client.amplifier.get());
                pTarget.hurt(DamageSource.mobAttack(pAttacker),damage-1);

                if((pTarget.getHealth()-damage)<1E-6)
                {
                    //pAttacker.moveTo(pTarget.getX(),pTarget.getY(),pTarget.getZ());
                    float k;
                    if(NBTHelper.hasTag(pStack, "slaynumber"))
                    {
                        k= Float.parseFloat(pStack.getTag().getString("slaynumber"))+1f;
                        pStack.getTag().putString("slaynumber", Float.toString(k));
                    }
                }
            }

        }


        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
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
                player.getFoodData().eat(10,1);
                level.removeBlock(blockpos,false);
            }

            //level.setBlock(blockpos,Blocks.AIR.defaultBlockState(),4);
            level.playSound(player, player.blockPosition(), SoundInit.PELEBALL.get(), SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.getCooldowns().addCooldown(this,100+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

            player.getMainHandItem().hurtAndBreak(1, player, (p_40665_) -> {
                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
            });

            return ActionResultType.sidedSuccess(level.isClientSide());
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.cannibal"));
        }
        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

        if(stack.hasTag()) {
            String number = stack.getTag().getString("slaynumber");

            tooltip.add(new TranslationTextComponent("tooltip.slay", TextFormatting.RED+number));
        }
    }

}

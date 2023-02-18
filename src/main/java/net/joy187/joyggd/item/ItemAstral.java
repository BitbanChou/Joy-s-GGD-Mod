package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.management.ValueExp;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class ItemAstral extends Item {

    PlayerEntity owner;

    public ItemAstral(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        this.owner=playerIn;
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
            if(!NBTHelper.hasTag(itemstack,"isAstraling"))
            {
                CompoundNBT counter = new CompoundNBT();
                counter.putString("x",String.format("%.2f", playerIn.getX()));
                counter.putString("y",String.format("%.2f", playerIn.getY()));
                counter.putString("z",String.format("%.2f", playerIn.getZ()));

                counter.putString("isAstraling","1");
                itemstack.setTag(counter);

                playerIn.abilities.mayfly = true;
                playerIn.abilities.invulnerable=true;
                playerIn.abilities.mayBuild=false;
                playerIn.noPhysics=true;

                playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

                itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
                    p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
                });
                level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

//                System.out.println(itemstack.getTag().getString("isAstraling")+"给一个nbt变身");
//                System.out.println(itemstack.getTag().getString("x")+"给一个nbt变身");
//                System.out.println(itemstack.getTag().getString("y")+"给一个nbt变身");
//                System.out.println(itemstack.getTag().getString("z")+"给一个nbt变身");
            }
            else if(itemstack.getTag().getString("isAstraling").equals("0")) {
                String format222 = String.format("%.2f", playerIn.getX());
                itemstack.getTag().putString("x", String.format("%.2f", playerIn.getX()));
                itemstack.getTag().putString("y", String.format("%.2f", playerIn.getY()));
                itemstack.getTag().putString("z", String.format("%.2f", playerIn.getZ()));
                itemstack.getTag().putString("isAstraling", "1");

                playerIn.abilities.mayfly = true;
                playerIn.abilities.invulnerable=true;
                playerIn.abilities.mayBuild=false;
                playerIn.noPhysics=true;

                playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
                itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
                    p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
                });
                level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

//                System.out.println(itemstack.getTag().getString("isAstraling")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("x")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("y")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("z")+"有nbt变身");
            }
            else{

            }
        }
        //playerIn.getCooldowns().addCooldown(this,200);

        return ActionResult.success(itemstack);
    }

    public void inventoryTick(@Nullable ItemStack stack, World level, Entity entity, int p_41407_, boolean p_41408_) {

        if (!level.isClientSide && stack.getItem()==ItemInit.ASTRAL.get()) {
            if(this.owner!=null)
            {
                for (int i = 0; i < this.owner.inventory.getContainerSize(); ++i)
                {
                    ItemStack itemstack = this.owner.inventory.getItem(i);
                    if(itemstack.getItem() == ItemInit.ASTRAL.get() &&
                            NBTHelper.hasTag(itemstack,"isAstraling") &&
                            itemstack.getTag().getString("isAstraling").equals("1"))
                    {

                        if(!this.owner.getCooldowns().isOnCooldown(itemstack.getItem()))
                        {
                            this.owner.teleportTo(Double.parseDouble(itemstack.getTag().getString("x")),
                                    Double.parseDouble(itemstack.getTag().getString("y")),
                                    Double.parseDouble(itemstack.getTag().getString("z")));
                            itemstack.getTag().putString("isAstraling", "0");


                            this.owner.abilities.mayfly = false;
                            this.owner.abilities.invulnerable=false;
                            this.owner.abilities.mayBuild=true;
                            this.owner.noPhysics=false;
                            this.owner.abilities.flying = false;
                            this.owner.onUpdateAbilities();

                            level.playSound(this.owner, this.owner.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                            this.owner.getCooldowns().addCooldown(itemstack.getItem(),300+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
                        }
                    }
                }
                //ItemStack itemstack = this.owner.getItemInHand(Hand.MAIN_HAND);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.astral"));
        }
        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
    }

}

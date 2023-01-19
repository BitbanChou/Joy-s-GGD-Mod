package net.joy187.joyggd.item;

import net.joy187.joyggd.config.ModConfigs;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.management.ValueExp;
import java.util.List;
import java.util.Random;

public class ItemAstral extends Item {

    Player owner;

    public ItemAstral(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand hand) {
        owner=playerIn;
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if(!playerIn.getCooldowns().isOnCooldown(this))
        {
            if(!NBTHelper.hasTag(itemstack,"isAstraling"))
            {
                CompoundTag counter = new CompoundTag();
                counter.putString("x",String.format("%.2f", playerIn.getX()));
                counter.putString("y",String.format("%.2f", playerIn.getY()));
                counter.putString("z",String.format("%.2f", playerIn.getZ()));

                counter.putString("isAstraling","1");
                itemstack.setTag(counter);

                playerIn.getAbilities().mayfly = true;
                playerIn.getAbilities().invulnerable=true;
                playerIn.getAbilities().mayBuild=false;
                playerIn.noPhysics=true;

                playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));

                itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
                    p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
                });
                level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

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

                playerIn.getAbilities().mayfly = true;
                playerIn.getAbilities().invulnerable=true;
                playerIn.getAbilities().mayBuild=false;
                playerIn.noPhysics=true;

                playerIn.getCooldowns().addCooldown(this,200+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
                itemstack.hurtAndBreak(1, playerIn, (p_40665_) -> {
                    p_40665_.broadcastBreakEvent(playerIn.getUsedItemHand());
                });
                level.playSound(playerIn, playerIn.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

//                System.out.println(itemstack.getTag().getString("isAstraling")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("x")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("y")+"有nbt变身");
//                System.out.println(itemstack.getTag().getString("z")+"有nbt变身");
            }
            else{

            }
        }
        //playerIn.getCooldowns().addCooldown(this,200);

        return InteractionResultHolder.success(itemstack);
    }

    public void inventoryTick(@NotNull ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {

        if (!level.isClientSide && stack.getItem()==ItemInit.ASTRAL.get()) {
            if(this.owner!=null)
            {
                for (int i = 0; i < this.owner.getInventory().getContainerSize(); ++i)
                {
                    ItemStack itemstack = this.owner.getInventory().getItem(i);
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


                            this.owner.getAbilities().mayfly = false;
                            this.owner.getAbilities().invulnerable=false;
                            this.owner.getAbilities().mayBuild=true;
                            this.owner.noPhysics=false;
                            this.owner.getAbilities().flying = false;
                            this.owner.onUpdateAbilities();

                            level.playSound(this.owner, this.owner.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.5F, 0.8F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                            this.owner.getCooldowns().addCooldown(itemstack.getItem(),300+Math.min(0, ModConfigs.Client.bonusSkillCoolDownTime.get()));
                        }
                    }
                }
                //ItemStack itemstack = this.owner.getItemInHand(InteractionHand.MAIN_HAND);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.astral"));
        }

    }

}

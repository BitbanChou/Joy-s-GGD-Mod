package net.joy187.joyggd.item;

import net.joy187.joyggd.entity.EntitySpaceship;
import net.joy187.joyggd.init.EntityInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;


import javax.annotation.Nullable;
import java.util.List;

public class ItemSpaceship extends Item{

    public ItemSpaceship(Item.Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        EntitySpaceship ship = new EntitySpaceship(EntityInit.SPACESHIP.get(),playerIn.level);
        ship.setPos(playerIn.getX(),playerIn.getY(),playerIn.getZ());
        playerIn.level.addFreshEntity(ship);

        if (!playerIn.abilities.instabuild) {
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                playerIn.inventory.removeItem(itemstack);
            }
        }

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.spaceship"));
    }

}


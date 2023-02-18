package net.joy187.joyggd.item;

import net.joy187.joyggd.entity.EntityPoliceCar;
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


import java.awt.*;
import java.util.List;

public class ItemCanadian extends Item {

    public ItemCanadian(Item.Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
        ItemStack itemstack = p_77659_2_.getItemInHand(p_77659_3_);

        EntityPoliceCar e = new EntityPoliceCar(EntityInit.POLICECAR.get(), p_77659_2_.level);
        e.setPos(p_77659_2_.position().x,p_77659_2_.position().y,p_77659_2_.position().z);
        p_77659_2_.level.addFreshEntity(e);

        if (!p_77659_2_.abilities.instabuild) {
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                p_77659_2_.inventory.removeItem(itemstack);
            }
        }

        return ActionResult.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.canadian"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));

    }

}

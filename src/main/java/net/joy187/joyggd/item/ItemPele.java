package net.joy187.joyggd.item;


import java.awt.*;
import java.util.List;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityPele;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class ItemPele extends Item {

    public ItemPele(Properties properties) {
        super(properties.durability(10));
    }

//    @Override
//    protected boolean allowedIn(CreativeModeTab group) {
//        return true;
//    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);

        if (!worldIn.isClientSide) {
            EntityPele pokeball = new EntityPele(playerIn, worldIn, itemStackIn.copy());
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.1F, 0.1F);
            worldIn.addFreshEntity(pokeball);
        }

        worldIn.playSound(playerIn, playerIn.blockPosition(), SoundInit.PELEBALL.get(), SoundCategory.PLAYERS, 1.5F, 0.8F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!playerIn.isCreative() || this.getEntityCompound(itemStackIn) != null) {
            return ActionResult.success(ItemStack.EMPTY);
        }

        return ActionResult.success(itemStackIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT entityTag = this.getEntityCompound(stack);
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.peleball"));
        }        else tooltip.add(new TranslationTextComponent("tooltip.shift"));
        if (entityTag != null) {
            String entityName = entityTag.getString("id");
            if (entityTag.contains("peleball_name")) {
                entityName = entityTag.getString("peleball_name");
            }

            if (entityTag.contains("CustomName")) {
                String s = entityTag.getString("CustomName");

                try {
                    ITextComponent customName = ITextComponent.Serializer.fromJson(entityTag.getString("CustomName"));
                    //customName.withStyle(TextFormatting.BLUE, TextFormatting.ITALIC);
                    tooltip.add(new TranslationTextComponent("tooltip.peleball.stored_custom_name", customName, new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
                } catch (Exception exception) {
                    Main.LOGGER.warn("Failed to parse entity custom name {}", s, exception);
                    tooltip.add(new TranslationTextComponent("tooltip.peleball.stored", new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
                }
            } else {
                tooltip.add(new TranslationTextComponent("tooltip.peleball.stored", new TranslationTextComponent(entityName).withStyle(TextFormatting.AQUA)));
            }
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.peleball.empty").withStyle(TextFormatting.GRAY));
        }
    }

    public CompoundNBT getEntityCompound(ItemStack stack) {
        return NBTHelper.hasTag(stack, "StoredEntity") ? NBTHelper.getTag(stack, "StoredEntity") : null;
    }
}


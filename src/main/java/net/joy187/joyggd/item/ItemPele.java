package net.joy187.joyggd.item;


import java.util.List;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.EntityPele;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemPele extends Item {

    public ItemPele(Properties properties) {
        super(properties.durability(10));
    }

    @Override
    protected boolean allowedIn(CreativeModeTab group) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);

        if (!worldIn.isClientSide) {
            EntityPele pokeball = new EntityPele(playerIn, worldIn, itemStackIn.copy());
            pokeball.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.1F, 0.1F);
            worldIn.addFreshEntity(pokeball);
        }

        worldIn.playSound(playerIn, playerIn.blockPosition(), SoundInit.PELEBALL.get(), SoundSource.PLAYERS, 1.5F, 0.8F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!playerIn.isCreative() || this.getEntityCompound(itemStackIn) != null) {
            return InteractionResultHolder.success(ItemStack.EMPTY);
        }

        return InteractionResultHolder.success(itemStackIn);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        CompoundTag entityTag = this.getEntityCompound(stack);
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.peleball"));
        }
        if (entityTag != null) {
            String entityName = entityTag.getString("id");
            if (entityTag.contains("peleball_name")) {
                entityName = entityTag.getString("peleball_name");
            }

            if (entityTag.contains("CustomName")) {
                String s = entityTag.getString("CustomName");

                try {
                    MutableComponent customName = Component.Serializer.fromJson(entityTag.getString("CustomName"));
                    customName.withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC);
                    tooltip.add(Component.translatable("tooltip.peleball.stored_custom_name", customName, Component.translatable(entityName).withStyle(ChatFormatting.AQUA)));
                } catch (Exception exception) {
                    Main.LOGGER.warn("Failed to parse entity custom name {}", s, exception);
                    tooltip.add(Component.translatable("tooltip.peleball.stored", Component.translatable(entityName).withStyle(ChatFormatting.AQUA)));
                }
            } else {
                tooltip.add(Component.translatable("tooltip.peleball.stored", Component.translatable(entityName).withStyle(ChatFormatting.AQUA)));
            }
        } else {
            tooltip.add(Component.translatable("tooltip.peleball.empty").withStyle(ChatFormatting.GRAY));
        }
    }

    public CompoundTag getEntityCompound(ItemStack stack) {
        return NBTHelper.hasTag(stack, "StoredEntity") ? NBTHelper.getTag(stack, "StoredEntity") : null;
    }
}


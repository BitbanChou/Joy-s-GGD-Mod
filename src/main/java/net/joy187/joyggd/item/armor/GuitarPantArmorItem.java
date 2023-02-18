package net.joy187.joyggd.item.armor;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.item.CustomArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class GuitarPantArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public GuitarPantArmorItem(CustomArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder.tab(Main.ITEM_TAB));
    }

    @SuppressWarnings("unused")
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        List<EquipmentSlotType> slotData = event.getExtraDataOfType(EquipmentSlotType.class);
        List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        // If the living entity is an armorstand just play the animation nonstop
        if (livingEntity instanceof ArmorStandEntity) {
            return PlayState.CONTINUE;
        }


        else if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;

            // Get all the equipment, aka the armor, currently held item, and offhand item
            List<Item> equipmentList = new ArrayList<>();
            player.getAllSlots().forEach((x) -> equipmentList.add(x.getItem()));

            // elements 2 to 6 are the armor so we take the sublist. Armorlist now only
            // contains the 4 armor slots
            List<Item> armorList = equipmentList.subList(3, 4);

            boolean isWearingAll = armorList
                    .containsAll(Arrays.asList(ItemInit.GUITARPANTS.get()));
            return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        }
        return PlayState.STOP;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private static final Predicate<Entity> GUITAR_AREA = (entity) -> {
        return entity.isAlive() && !(entity instanceof PlayerEntity);
    };

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if(!world.isClientSide()) {
            if(!player.inventory.getArmor(1).isEmpty()
            && !player.inventory.getArmor(2).isEmpty()
            && !player.inventory.getArmor(3).isEmpty()) {
                ArmorItem legs = ((ArmorItem)player.inventory.getArmor(1).getItem());
                ArmorItem chest = ((ArmorItem)player.inventory.getArmor(2).getItem());
                ArmorItem head = ((ArmorItem)player.inventory.getArmor(3).getItem());
                if(chest == ItemInit.GUITAR.get() && head==ItemInit.PEACE.get() && legs==ItemInit.GUITARPANTS.get()) {
                    for (LivingEntity livingentity : player.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10.0D), GUITAR_AREA)) {

                        if(livingentity instanceof WolfEntity)
                        {
                            ((WolfEntity)livingentity).tame(player);
                        }

                        if(livingentity instanceof CatEntity)
                        {
                            ((CatEntity)livingentity).tame(player);
                        }

                        if(livingentity instanceof ParrotEntity)
                        {
                            ((ParrotEntity)livingentity).tame(player);
                        }
                    }

//                    boolean hasPlayerEntityEffect = player.hasEffect(mapStatusEffect1.getEffect());
//                    if(!hasPlayerEntityEffect) {
//                        player.addEffect(new EffectInstance(mapStatusEffect1.getEffect(),mapStatusEffect1.getDuration(), mapStatusEffect1.getAmplifier()));
//                        player.addEffect(new EffectInstance(mapStatusEffect2.getEffect(),mapStatusEffect2.getDuration(), mapStatusEffect2.getAmplifier()));
//                    }
                }
            	//player.addEffect(new EffectInstance(EffectInit.ZENHEART.get(), 100, 1));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.guitar"));
    }
}
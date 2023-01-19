package net.joy187.joyggd.item.armor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.item.CustomArmorMaterial;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;


public class ChandlierArmorItem extends GeoArmorItem implements IAnimatable{
	private AnimationFactory factory = new AnimationFactory(this);

	public ChandlierArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
		super(materialIn, slot, builder.tab(Main.ITEM_TAB));
	}

	@SuppressWarnings("unused")
	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		List<EquipmentSlot> slotData = event.getExtraDataOfType(EquipmentSlot.class);
		List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
		LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

		if(!event.isMoving())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chandlier.rotate", true));
		}

		// If the living entity is an armorstand just play the animation nonstop
		if (livingEntity instanceof ArmorStand) {
			return PlayState.CONTINUE;
		}


		else if (livingEntity instanceof Player) {
			Player player = (Player) livingEntity;

			// Get all the equipment, aka the armor, currently held item, and offhand item
			List<Item> equipmentList = new ArrayList<>();
			player.getAllSlots().forEach((x) -> equipmentList.add(x.getItem()));

			// elements 2 to 6 are the armor so we take the sublist. Armorlist now only
			// contains the 4 armor slots
			List<Item> armorList = equipmentList.subList(5, 6);

			boolean isWearingAll = armorList
					.containsAll(Arrays.asList(ItemInit.CHANDLIER.get()));
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
	
//    @Override
//    public void onArmorTick(ItemStack stack, Level world, Player player) {
//        if(!world.isClientSide()) {
//            if(!player.getInventory().getArmor(3).isEmpty()) {
//                MobEffectInstance mapStatusEffect1 = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*60, 1);
//                MobEffectInstance mapStatusEffect2 = new MobEffectInstance(MobEffects.NIGHT_VISION, 20*60, 1);
//
//                ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());
//                if(helmet.getMaterial() == CustomArmorMaterial.ARMOR_MATERIAL_ADASUIT) {
//                    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect1.getEffect());
//                    if(!hasPlayerEffect) {
//                        player.addEffect(new MobEffectInstance(mapStatusEffect1.getEffect(),mapStatusEffect1.getDuration(), mapStatusEffect1.getAmplifier()));
//                        player.addEffect(new MobEffectInstance(mapStatusEffect2.getEffect(),mapStatusEffect2.getDuration(), mapStatusEffect2.getAmplifier()));
//                    }
//                }
//            	//player.addEffect(new MobEffectInstance(EffectInit.ZENHEART.get(), 100, 1));
//            }
//        }
//    }
    
}
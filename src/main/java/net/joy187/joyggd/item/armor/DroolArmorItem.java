package net.joy187.joyggd.item.armor;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.item.CustomArmorMaterial;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
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
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DroolArmorItem extends GeoArmorItem implements IAnimatable{
	private AnimationFactory factory = new AnimationFactory(this);

	public DroolArmorItem(CustomArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder.tab(Main.ITEM_TAB));
	}

	@SuppressWarnings("unused")
	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
		List<EquipmentSlotType> slotData = event.getExtraDataOfType(EquipmentSlotType.class);
		List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
		LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

		if(!event.isMoving())
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chandlier.rotate", true));
		}

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
			List<Item> armorList = equipmentList.subList(5, 6);

			boolean isWearingAll = armorList
					.containsAll(Arrays.asList(ItemInit.DROOL.get()));
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

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		if(!world.isClientSide()) {
			if(!player.inventory.getArmor(3).isEmpty()) {
				ArmorItem helmet = ((ArmorItem)player.inventory.getArmor(3).getItem());
				if(helmet.getMaterial() == CustomArmorMaterial.ARMOR_MATERIAL_DROOL) {
					if(player.getCooldowns().isOnCooldown(ItemInit.VULTURE.get()))
					{
						player.getCooldowns().removeCooldown(ItemInit.VULTURE.get());
					}
//                    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect1.getEffect());
//                    if(!hasPlayerEffect) {
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
		tooltip.add(new TranslationTextComponent("tooltip.drool"));
	}
}
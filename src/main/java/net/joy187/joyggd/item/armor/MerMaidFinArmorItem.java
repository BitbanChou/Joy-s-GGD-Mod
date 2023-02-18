package net.joy187.joyggd.item.armor;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.item.CustomArmorMaterial;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
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

import java.security.Guard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class MerMaidFinArmorItem extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public MerMaidFinArmorItem(CustomArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
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
                    .containsAll(Arrays.asList(ItemInit.MERMAID.get()));
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
            if(!player.inventory.getArmor(1).isEmpty()
                    && !player.inventory.getArmor(2).isEmpty()) {
                ArmorItem legs = ((ArmorItem)player.inventory.getArmor(1).getItem());
                ArmorItem chest = ((ArmorItem)player.inventory.getArmor(2).getItem());

                if(legs == ItemInit.MERMAID.get() && chest == ItemInit.MERMAIDB.get()) {
                    if(!player.hasEffect(Effects.WATER_BREATHING))
                    {
                        player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 20*30, 1));
                    }

                    if(player.isInWater())
                    {
                        if(player.isSwimming())
                        {
                            Vector3d look = player.getLookAngle();
//                            //Vec3 vec3 = new Vec3(look.x*2, look.y*2, look.z*2);
//                            Vec3 vec3 = new Vec3(1, 1, 1);
//                            player.setDeltaMovement(player.getDeltaMovement().add(vec3));
//                            if(Screen.hasShiftDown()){
                                //System.out.println("yeah");
                                BlockPos pos = new BlockPos(player.position().x+look.x,player.position().y,player.position().z+look.z);
                                if(player.level.getBlockState(pos) == Blocks.WATER.defaultBlockState())
                                    player.moveTo(player.position().x+look.x,player.position().y,player.position().z+look.z);
//                            }
                        }
                    }
                }


            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.mermaid"));
    }



}
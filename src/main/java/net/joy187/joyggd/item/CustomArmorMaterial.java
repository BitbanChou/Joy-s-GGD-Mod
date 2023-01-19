package net.joy187.joyggd.item;

import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum CustomArmorMaterial implements ArmorMaterial {

//    ARMOR_MATERIAL_FRANKSUIT("franksuit", 80, new int[]{8, 13, 20, 12}, 35, SoundEvents.ARMOR_EQUIP_IRON, 7.0F, 0.8F, () -> {
//        return Ingredient.of(ItemInit.ASTRAL.get());
//    }),
    ARMOR_MATERIAL_DROOL("drool", 40, new int[]{6, 10, 12, 8}, 25, SoundInit.FINISH.get(), 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    ARMOR_MATERIAL_CHANDLIER("chandlier", 40, new int[]{8, 10, 12, 8}, 15, SoundInit.FINISH.get(), 2.0F, 0.5F, () -> {
            return Ingredient.of(Items.DIAMOND);
    });

    private static final int[] baseDurability = { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorVal;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;


    private CustomArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound,
                                float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorVal = armorVal;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return baseDurability[slot.getIndex()] * durabilityMultiplier;
    }


    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.armorVal[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }


    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }


    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}

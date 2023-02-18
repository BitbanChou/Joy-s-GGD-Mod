package net.joy187.joyggd.item;


import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum CustomArmorMaterial implements IArmorMaterial {


    ARMOR_MATERIAL_DRESS("dress", 50, new int[]{5, 8, 10, 4}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    ARMOR_MATERIAL_DROOL("drool", 40, new int[]{6, 10, 12, 5}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    ARMOR_MATERIAL_BOXHEAD("box", 40, new int[]{6, 10, 12, 4}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    }),
    ARMOR_MATERIAL_CHANDLIER("chandlier", 40, new int[]{8, 10, 12, 8}, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.5F, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    ARMOR_MATERIAL_PEACE("peace", 50, new int[]{6, 8, 10, 6}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 4.0F, 0.2F, () -> {
        return Ingredient.of(Items.DIAMOND);
    }),
    ARMOR_MATERIAL_BOXER("boxer", 35, new int[]{4, 6, 8, 4}, 8, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    ARMOR_MATERIAL_MERMAID("mermaid", 35, new int[]{4, 8, 6, 4}, 25, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    ARMOR_MATERIAL_LANTERN("lantern", 50, new int[]{8, 10, 12, 6}, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.1F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    });


    private static final int[] baseDurability = { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorVal;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient repairMaterial;


    private CustomArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound,
                                float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorVal = armorVal;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairIngredient.get();
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return baseDurability[slot.getIndex()] * durabilityMultiplier;
    }


    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
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

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial;
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

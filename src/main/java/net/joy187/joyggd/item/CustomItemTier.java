package net.joy187.joyggd.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;


import java.util.function.Supplier;

public enum CustomItemTier implements IItemTier {

    TOOL_DUCKKNIFE( 3, 180, 8.0F, 12.0F, 35, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),

    TOOL_VIGILANTE( 3, 150, 8.0F, 10.0F, 40, () -> {
        return Ingredient.of(Items.GOLD_INGOT);
    }),

    TOOL_SHERIFF(3, 200, 8.0F, 10.0F, 60,() -> {
        return Ingredient.of(Items.DIAMOND);
    });


    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairMaterial;

    private CustomItemTier(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
        this.level = p_43332_;
        this.uses = p_43333_;
        this.speed = p_43334_;
        this.damage = p_43335_;
        this.enchantmentValue = p_43336_;
        this.repairMaterial = p_43337_.get();
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial;
    }

}


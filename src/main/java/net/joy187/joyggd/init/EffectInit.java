package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.effects.EffectBoom;
import net.joy187.joyggd.effects.EffectPigeon;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MOD_ID);

    public static RegistryObject<MobEffect> BOOM = EFFECTS.register("boom",()->
    {
        return new EffectBoom(MobEffectCategory.HARMFUL, 0xCF344C, false);
    });

    public static RegistryObject<MobEffect> PIGEON = EFFECTS.register("pigeon",()->
    {
        return new EffectPigeon(MobEffectCategory.HARMFUL, 0x9B32CA, false);
    });
    
//    public static RegistryObject<MobEffect>BURN = EFFECTS.register("burn",()->
//    {
//        return new EffectBurn(MobEffectCategory.HARMFUL, 0x000033, false)
//        		.addAttributeModifier(Attributes.MOVEMENT_SPEED,
//        		"7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL);
//    });
    


	public static void register(IEventBus eventBus) {
		EFFECTS.register(eventBus);
	}
}

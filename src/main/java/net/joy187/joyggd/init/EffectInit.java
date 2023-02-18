package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.effects.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MOD_ID);

    public static RegistryObject<Effect> BOOM = EFFECTS.register("boom",()->
    {
        return new EffectBoom(EffectType.HARMFUL, 0xCF344C, false);
    });

    public static RegistryObject<Effect> PIGEON = EFFECTS.register("pigeon",()->
    {
        return new EffectPigeon(EffectType.HARMFUL, 0x9B32CA, false);
    });
    
    public static RegistryObject<Effect> ADVENTURER = EFFECTS.register("adventure",()->
    {
        return new EffectAdventurer(EffectType.BENEFICIAL, 0x9B5D43, false);
    });

    public static RegistryObject<Effect> MIMIC = EFFECTS.register("mimic",()->
    {
        return new EffectMimic(EffectType.BENEFICIAL, 0xFFFFFF, false);
    });

    public static RegistryObject<Effect> ESPER = EFFECTS.register("esper",()->
    {
        return new EffectEsper(EffectType.HARMFUL, 14564800, false);
    });
//    public static RegistryObject<Effect>BURN = EFFECTS.register("burn",()->
//    {
//        return new EffectBurn(EffectCategory.HARMFUL, 0x000033, false)
//        		.addAttributeModifier(Attributes.MOVEMENT_SPEED,
//        		"7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.25F, AttributeModifier.Operation.MULTIPLY_TOTAL);
//    });
    


	public static void register(IEventBus eventBus) {
		EFFECTS.register(eventBus);
	}
}

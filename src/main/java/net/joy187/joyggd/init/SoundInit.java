package net.joy187.joyggd.init;


import net.joy187.joyggd.Main;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    public static final RegistryObject<SoundEvent> PELEBALL = build("entity.peleball");
    public static final RegistryObject<SoundEvent> MUMMYKILL = build("entity.mummykill");
    public static final RegistryObject<SoundEvent> AMBIENT1 = build("entity.ambient1");
    public static final RegistryObject<SoundEvent> AMBIENT2 = build("entity.ambient2");
    public static final RegistryObject<SoundEvent> FINISH = build("entity.finish");
    public static final RegistryObject<SoundEvent> BOOMTICK = build("entity.boomtick");
    public static final RegistryObject<SoundEvent> PIGEON = build("entity.pigeon");
    public static final RegistryObject<SoundEvent> DRAW = build("entity.draw");
    public static final RegistryObject<SoundEvent> ENTITY_HEISENCAR_ROLLING = build("entity.rolling");
    public static final RegistryObject<SoundEvent> ENTITY_HEISENCAR_FIRING = build("entity.firing");
    public static final RegistryObject<SoundEvent> ASSASSINSCOPE = build("entity.ascope");
    public static final RegistryObject<SoundEvent> ASSASSINSHOT = build("entity.ashot");

    private static RegistryObject<SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, id)));
    }
}

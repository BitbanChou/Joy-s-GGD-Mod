package net.joy187.joyggd.init;


import net.joy187.joyggd.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    private static RegistryObject<SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, id)));
    }
}

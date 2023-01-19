package net.joy187.joyggd.init;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            Main.MOD_ID);


    public static final RegistryObject<EntityType<EntityPele>> PELEBALL_ENTITY = ENTITY_TYPES.register("peleball",
            () -> EntityType.Builder.<EntityPele>of(EntityPele::new, MobCategory.MISC).sized(0.5F, 0.5F)
                    .setTrackingRange(4).updateInterval(10).build("peleball"));

    public static final RegistryObject<EntityType<EntityGoose>> GOOSE = ENTITY_TYPES.register("goose",
            () -> EntityType.Builder.of(EntityGoose::new, MobCategory.CREATURE).sized(0.6f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "goose").toString()));

    public static final RegistryObject<EntityType<EntityDuck>> DUCK = ENTITY_TYPES.register("duck",
            () -> EntityType.Builder.of(EntityDuck::new, MobCategory.MONSTER).sized(0.8f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "duck").toString()));

    public static final RegistryObject<EntityType<EntityMummy>> MUMMY = ENTITY_TYPES.register("mummy",
            () -> EntityType.Builder.of(EntityMummy::new, MobCategory.MONSTER).sized(0.6f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "mummy").toString()));

    public static final RegistryObject<EntityType<EntityBullet>> BULLET = ENTITY_TYPES.register("bullet",
            () -> EntityType.Builder.<EntityBullet>of(EntityBullet::new, MobCategory.MISC).sized(0.25F, 0.25F)
                    .setTrackingRange(4).updateInterval(10).build("bullet"));

}

package net.joy187.joyggd.init;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Main.MOD_ID);


    public static final RegistryObject<EntityType<EntityPele>> PELEBALL_ENTITY = ENTITY_TYPES.register("peleball",
            () -> EntityType.Builder.<EntityPele>of(EntityPele::new, EntityClassification.MISC).sized(0.5F, 0.5F)
                    .setTrackingRange(4).updateInterval(10).build("peleball"));

    public static final RegistryObject<EntityType<EntityGoose>> GOOSE = ENTITY_TYPES.register("goose",
            () -> EntityType.Builder.of(EntityGoose::new, EntityClassification.CREATURE).sized(0.6f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "goose").toString()));

    public static final RegistryObject<EntityType<EntityDuck>> DUCK = ENTITY_TYPES.register("duck",
            () -> EntityType.Builder.of(EntityDuck::new, EntityClassification.MONSTER).sized(0.8f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "duck").toString()));

    public static final RegistryObject<EntityType<EntityMummy>> MUMMY = ENTITY_TYPES.register("mummy",
            () -> EntityType.Builder.of(EntityMummy::new, EntityClassification.MONSTER).sized(0.6f,1.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "mummy").toString()));

    public static final RegistryObject<EntityType<EntityBullet>> BULLET = ENTITY_TYPES.register("bullet",
            () -> EntityType.Builder.<EntityBullet>of(EntityBullet::new, EntityClassification.MISC).sized(0.25F, 0.25F)
                    .setTrackingRange(4).updateInterval(10).build("bullet"));

    public static final RegistryObject<EntityType<EntityPoliceCar>> POLICECAR = ENTITY_TYPES.register("policecar",
            () -> EntityType.Builder.of(EntityPoliceCar::new, EntityClassification.MISC).sized(1.5f,3.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "policecar").toString()));

    public static final RegistryObject<EntityType<EntitySpaceship>> SPACESHIP = ENTITY_TYPES.register("spaceship",
            () -> EntityType.Builder.of(EntitySpaceship::new, EntityClassification.MISC).sized(0.5f,3.5f).setTrackingRange(40)
                    .build(new ResourceLocation(Main.MOD_ID, "spaceship").toString()));


}

//package net.joy187.joyggd.world.gen;
//
//import net.joy187.joyggd.init.EntityInit;
//import net.minecraft.entity.EntityType;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.Biomes;
//import net.minecraftforge.event.world.BiomeLoadingEvent;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class ModEntityGeneration {
//
//    //生物实体生成函数
//    public static void onEntitySpawn(final BiomeLoadingEvent event) {
////        addEntityToAllOverworldBiomes(event, EntityInit.ETHAN.get(),
////                40, 2, 4);
//        addEntityToSpecificBiomes(event, EntityInit.DUCK.get(),35,1,2, Biomes.DESERT);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2, Biomes.PLAINS);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.ICE_SPIKES);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.BADLANDS);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.DARK_FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.BIRCH_FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.JUNGLE);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.MEADOW);
//        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.NETHER_WASTES);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.STONY_SHORE);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.DRIPSTONE_CAVES);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.LUSH_CAVES);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.OLD_GROWTH_BIRCH_FOREST);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.GROVE);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.OLD_GROWTH_PINE_TAIGA);
////        addEntityToSpecificBiomes(event,EntityInit.DUCK.get(),35,1,2,Biomes.OLD_GROWTH_SPRUCE_TAIGA);
//
//
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2, Biomes.DESERT);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2, Biomes.PLAINS);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.ICE_SPIKES);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.BADLANDS);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.DARK_FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.BIRCH_FOREST);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.JUNGLE);
////        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.MEADOW);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.SAVANNA);
//        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),45,1,2,Biomes.BEACH);
////        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),35,1,2,Biomes.OLD_GROWTH_BIRCH_FOREST);
////        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),35,1,2,Biomes.GROVE);
////        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),35,1,2,Biomes.OLD_GROWTH_PINE_TAIGA);
////        addEntityToSpecificBiomes(event,EntityInit.GOOSE.get(),35,1,2,Biomes.OLD_GROWTH_SPRUCE_TAIGA);
//
//        addEntityToSpecificBiomes(event,EntityInit.MUMMY.get(),10,1,1, Biomes.DESERT);
//        //addEntityToAllBiomesExceptThese(event,EntityInit.DUND1.get(),ModCommonConfig.SPAWN_Moroaicas.get(),1,2,Biomes.SAVANNA , Biomes.BEACH);
//
//    }
//
//    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
//                                                        int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
//        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
//        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
//                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));
//
//        if(!isBiomeSelected) {
//            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//        }
//    }
//
//    @SafeVarargs
//    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
//                                                  int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
//        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
//        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
//                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));
//
//        if(isBiomeSelected) {
//            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//        }
//    }
//
//    private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
//                                                      int weight, int minCount, int maxCount) {
//        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
//            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//        }
//    }
//
//    private static void addEntityToAllBiomesNoNether(BiomeLoadingEvent event, EntityType<?> type,
//                                                     int weight, int minCount, int maxCount) {
//        if(!event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
//            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//        }
//    }
//
//    private static void addEntityToAllBiomesNoEnd(BiomeLoadingEvent event, EntityType<?> type,
//                                                  int weight, int minCount, int maxCount) {
//        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
//            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//        }
//    }
//
//    private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
//                                             int weight, int minCount, int maxCount) {
//        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//        base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//    }
//}
//

package net.joy187.joyggd.world;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.init.EntityInit;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class EntityEvents {
    /**
     * Called when a biome is loaded
     *
     * @author Leronus
     */
    @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent biomeLoadingEvent) {
        if (biomeLoadingEvent.getName() == null) {
            return;
        }

        MobSpawnInfoBuilder spawns = biomeLoadingEvent.getSpawns();
        boolean beach = biomeLoadingEvent.getCategory().equals(Biome.Category.BEACH);
        boolean river = biomeLoadingEvent.getCategory().equals(Biome.Category.RIVER);
        boolean swamp = biomeLoadingEvent.getCategory().equals(Biome.Category.SWAMP);
        boolean ocean = biomeLoadingEvent.getCategory().equals(Biome.Category.OCEAN);
        boolean savanna = biomeLoadingEvent.getCategory().equals(Biome.Category.SAVANNA);
        boolean jungle = biomeLoadingEvent.getCategory().equals(Biome.Category.JUNGLE);
        boolean plain = biomeLoadingEvent.getCategory().equals(Biome.Category.PLAINS);
        boolean taiga = biomeLoadingEvent.getCategory().equals(Biome.Category.TAIGA);
        boolean forest = biomeLoadingEvent.getCategory().equals(Biome.Category.FOREST);
        boolean de = biomeLoadingEvent.getCategory().equals(Biome.Category.DESERT);
        boolean icy = biomeLoadingEvent.getCategory().equals(Biome.Category.ICY);
        boolean nether = biomeLoadingEvent.getCategory().equals(Biome.Category.NETHER);
        boolean mesa = biomeLoadingEvent.getCategory().equals(Biome.Category.MESA);
        boolean hills = biomeLoadingEvent.getCategory().equals(Biome.Category.EXTREME_HILLS);

        if(plain || forest || savanna || taiga)
        {
            spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityInit.GOOSE.get(),  25, 1, 2));

        }

        if(de)
        {
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityInit.MUMMY.get(),  10, 1, 1));

        }

        if(nether || mesa || beach || swamp)
        {
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityInit.DUCK.get(),  15, 1, 2));

        }
    }



}
package net.joy187.joyggd.world.gen;


import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);


        if(types.contains(BiomeDictionary.Type.PLAINS) ||
                types.contains(BiomeDictionary.Type.SAVANNA)) {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_TANK1_HOUSE);
        }

        if(types.contains(BiomeDictionary.Type.BEACH) ||
                types.contains(BiomeDictionary.Type.FOREST)) {
            event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_SPACE_HOUSE);
        }

    }
}

package net.joy187.joyggd.world.gen;


import net.joy187.joyggd.Main;
import net.joy187.joyggd.world.structure.ModStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModConfiguredStructures {
    /**
     * Static instance of our structure so we can reference it and add it to biomes easily.
     */
    public static StructureFeature<?, ?> CONFIGURED_TANK1_HOUSE = ModStructures.CHURCH.get().configured(IFeatureConfig.NONE);

    public static StructureFeature<?, ?> CONFIGURED_SPACE_HOUSE = ModStructures.SPACE.get().configured(IFeatureConfig.NONE);

    /**
     * Registers the configured structure which is what gets added to the biomes.
     * Noticed we are not using a forge registry because there is none for configured structures.
     *
     * We can register configured structures at any time before a world is clicked on and made.
     * But the best time to register configured features by code is honestly to do it in FMLCommonSetupEvent.
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        Registry.register(registry, new ResourceLocation(Main.MOD_ID, "configured_church_house"), CONFIGURED_TANK1_HOUSE);
        Registry.register(registry, new ResourceLocation(Main.MOD_ID, "configured_space_house"), CONFIGURED_SPACE_HOUSE);

        /*
         * Requires AccessTransformer ( see resources/META-INF/accesstransformer.cfg )
         */
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.CHURCH.get(), CONFIGURED_TANK1_HOUSE);
        FlatGenerationSettings.STRUCTURE_FEATURES.put(ModStructures.SPACE.get(), CONFIGURED_SPACE_HOUSE);

    }
}


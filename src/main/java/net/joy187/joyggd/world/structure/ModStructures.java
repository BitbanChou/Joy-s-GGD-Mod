package net.joy187.joyggd.world.structure;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.joy187.joyggd.Main;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ModStructures {
    //  STStructures.java

    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Main.MOD_ID);

    //    public static final RegistryObject<Structure<NoFeatureConfig>> TANK1 = STRUCTURES.register("tank1", Tank1Structure::new);
    public static final RegistryObject<Structure<NoFeatureConfig>> CHURCH =
            STRUCTURES.register("church", () -> (new ChurchStructure(NoFeatureConfig.CODEC)));

    public static final RegistryObject<Structure<NoFeatureConfig>> SPACE =
            STRUCTURES.register("space", () -> (new SpaceStructure(NoFeatureConfig.CODEC)));

    /* average distance apart in chunks between spawn attempts */
    /* minimum distance apart in chunks between spawn attempts. MUST BE LESS THAN ABOVE VALUE*/
    /* this modifies the seed of the structure so no two structures always spawn over each-other.
    Make this large and unique. */
    public static void setupStructures() {
        setupMapSpacingAndLand(CHURCH.get(),new StructureSeparationSettings(24,16, 1234567897), true);
        setupMapSpacingAndLand(SPACE.get(),new StructureSeparationSettings(48,40, 1234567871), false);

    }

    public static <F extends Structure<?>> void setupMapSpacingAndLand(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        /*
         * We need to add our structures into the map in Structure class
         * alongside vanilla structures or else it will cause errors.
         *
         * If the registration is setup properly for the structure,
         * getRegistryName() should never return null.
         */
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        /*
         * Whether surrounding land will be modified automatically to conform to the bottom of the structure.
         * Basically, it adds land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically or change in heights.
         *
         * Note: The air space this method will create will be filled with water if the structure is below sealevel.
         * This means this is best for structure above sealevel so keep that in mind.
         *
         * NOISE_AFFECTING_FEATURES requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        if(transformSurroundingLand){
            Structure.NOISE_AFFECTING_FEATURES = ImmutableList.<Structure<?>>builder()
                    .addAll(Structure.NOISE_AFFECTING_FEATURES)
                    .add(structure)
                    .build();
        }

        /*
         * This is the map that holds the default spacing of all structures.
         * Always add your structure to here so that other mods can utilize it if needed.
         *
         * However, while it does propagate the spacing to some correct dimensions from this map,
         * it seems it doesn't always work for code made dimensions as they read from this list beforehand.
         *
         * Instead, we will use the WorldEvent.Load event in StructureTutorialMain to add the structure
         * spacing from this list into that dimension or to do dimension blacklisting properly.
         * We also use our entry in DimensionStructuresSettings.DEFAULTS in WorldEvent.Load as well.
         *
         * DEFAULTS requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
         */
        DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, structureSeparationSettings)
                        .build();


        /*
         * There are very few mods that relies on seeing your structure in the noise settings registry before the world is made.
         *
         * You may see some mods add their spacings to DimensionSettings.BUILTIN_OVERWORLD instead of the NOISE_GENERATOR_SETTINGS loop below but
         * that field only applies for the default overworld and won't add to other worldtypes or dimensions (like amplified or Nether).
         * So yeah, don't do DimensionSettings.BUILTIN_OVERWORLD. Use the NOISE_GENERATOR_SETTINGS loop below instead if you must.
         */
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().structureSettings().structureConfig();

            /*
             * Pre-caution in case a mod makes the structure map immutable like datapacks do.
             * I take no chances myself. You never know what another mods does...
             *
             * structureConfig requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg)
             */
            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().structureSettings().structureConfig = tempMap;

            }
            else{
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}


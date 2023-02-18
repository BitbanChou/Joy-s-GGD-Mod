package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.BlockGooseBody;
import net.joy187.joyggd.block.Stove2Block;
import net.joy187.joyggd.block.Stove3Block;
import net.joy187.joyggd.block.StoveBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;

//    public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example_block",
//            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(5f, 6f)
//                    .sound(SoundType.METAL).requiresCorrectToolForDrops()),
//            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));

//    public static final RegistryObject<Block> GOOSEBODY = register("goosebody", () ->
//                    new BlockGooseBody(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(1.5F, 4.0F).sound(SoundType.STEM)),
//            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Block> STOVE = register("gashapon", () ->
                    new StoveBlock(false, 0, AbstractBlock.Properties.of(Material.STONE,MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(5.0F, 10.0F).sound(SoundType.METAL).lightLevel(litBlockEmission(10))),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Block> STOVE2 = register("gashapon2", () ->
                    new Stove2Block(false, 0, AbstractBlock.Properties.of(Material.STONE,MaterialColor.COLOR_BLUE).requiresCorrectToolForDrops().strength(5.0F, 10.0F).sound(SoundType.METAL).lightLevel(litBlockEmission(10))),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Block> STOVE3 = register("gashapon3", () ->
                    new Stove3Block(false, 0, AbstractBlock.Properties.of(Material.STONE,MaterialColor.GOLD).requiresCorrectToolForDrops().strength(5.0F, 10.0F).sound(SoundType.METAL).lightLevel(litBlockEmission(10))),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Block> GOOSEBODY  = register("goosebody", () ->
            new BlockGooseBody((AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).noOcclusion().requiresCorrectToolForDrops().strength(1.5F, 4.0F).sound(SoundType.STEM))),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Main.ITEM_TAB)));


    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(final String name,
                                                                     final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
                                                                Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> obj = registerBlock(name, block);
        ITEMS.register(name, item.apply(obj));
        return obj;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, ItemGroup tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            ItemGroup tab) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

//    public static Supplier<Block> createStainedGlassFromColor(DyeColor color) {
//        return () -> new StainedGlassBlock(color, BlockBehaviour.Properties.of(Material.GLASS, color).strength(0.3F)
//                .sound(SoundType.GLASS).noOcclusion().isValidSpawn(BlockInit::never).isRedstoneConductor(BlockInit::never).isSuffocating(BlockInit::never).isViewBlocking(BlockInit::never));
//    }

    public static boolean always(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static boolean always(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
        return true;
    }

    public static boolean never(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entityType) {
        return false;
    }
}

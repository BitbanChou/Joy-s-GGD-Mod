package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.joy187.joyggd.block.blockentity.Stove2BlockEntity;
import net.joy187.joyggd.block.blockentity.Stove3BlockEntity;
import net.joy187.joyggd.block.blockentity.StoveBlockEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityInit {
    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<TileEntityType<BlockEntityGooseBody>> GOOSEBODY = BLOCK_ENTITIES.register("goosebody",
            () -> TileEntityType.Builder.of(BlockEntityGooseBody::new, BlockInit.GOOSEBODY.get()).build(null));

    public static final RegistryObject<TileEntityType<StoveBlockEntity>> STOVE = BLOCK_ENTITIES.register("gashapon",
            () -> TileEntityType.Builder.of(StoveBlockEntity::new, BlockInit.STOVE.get()).build(null));

    public static final RegistryObject<TileEntityType<Stove2BlockEntity>> STOVE2 = BLOCK_ENTITIES.register("gashapon2",
            () -> TileEntityType.Builder.of(Stove2BlockEntity::new, BlockInit.STOVE2.get()).build(null));

    public static final RegistryObject<TileEntityType<Stove3BlockEntity>> STOVE3 = BLOCK_ENTITIES.register("gashapon3",
            () -> TileEntityType.Builder.of(Stove3BlockEntity::new, BlockInit.STOVE3.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

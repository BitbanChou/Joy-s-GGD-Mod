package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.block.blockentity.BlockEntityGooseBody;
import net.joy187.joyggd.block.blockentity.StoveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlockEntityGooseBody>> GOOSEBODY = BLOCK_ENTITIES.register("goosebody",
            () -> BlockEntityType.Builder.of(BlockEntityGooseBody::new, BlockInit.GOOSEBODY.get()).build(null));

    public static final RegistryObject<BlockEntityType<StoveBlockEntity>> STOVE = BLOCK_ENTITIES.register("gashapon",
            () -> BlockEntityType.Builder.of(StoveBlockEntity::new, BlockInit.STOVE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

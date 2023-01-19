package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.*;
import net.joy187.joyggd.item.armor.ChandlierArmorItem;
import net.joy187.joyggd.item.armor.DroolArmorItem;
import net.minecraft.server.commands.GameModeCommand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

//    public static final RegistryObject<Item> LEI = register("lei",
//            () -> new Item(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> SHERIFF = register("sheriff",
            () -> new ItemSheriff());

    public static final RegistryObject<Item> VIGILANTE = register("vigilante",
            () -> new ItemVigilante());

    public static final RegistryObject<Item> INVISIBILITY = register("invisibility",
            () -> new ItemInvisibility(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> DEMOLITIONIST = register("demolitionist",
            () -> new ItemDemolitionist(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> PROFESSIONAL = register("professional",
            () -> new ItemProfessional(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> VULTURE = register("vulture",
            () -> new ItemVulture(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> PIGEON = register("pigeon",
            () -> new ItemPigeon(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> DUCKKNIFE = register("duckknife",
            () -> new ItemDuckKnife());

    public static final RegistryObject<Item> ASTRAL = register("astral",
            () -> new ItemAstral(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> BODYGUARD = register("bodyguard",
            () -> new ItemBodyGuard(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<ChandlierArmorItem> CHANDLIER = ITEMS.register("chandlier",
            () -> new ChandlierArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_CHANDLIER, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<DroolArmorItem> DROOL = ITEMS.register("drool",
            () -> new DroolArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_DROOL, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<Item> GOOSE_SPAWN_EGG = register("goose_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.GOOSE, 16775667 , 16761693,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> DUCK_SPAWN_EGG = register("duck_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.DUCK, 16775667 , 0xCF344C,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> MUMMY_SPAWN_EGG = register("mummy_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.MUMMY, 15718846 , 7663359,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> PELEBALL = register("peleball",
            () -> new ItemPele(new Item.Properties().tab(Main.ITEM_TAB)));


    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}

package net.joy187.joyggd.init;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.*;
import net.joy187.joyggd.item.armor.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

//    public static final RegistryObject<Item> LEI = register("lei",
//            () -> new Item(new Item.Properties().tab(Main.TUTORIAL_TAB)));

    public static final RegistryObject<Item> SHERIFF = register("sheriff",
                     () -> { return new ItemSheriff();
    });

    public static final RegistryObject<Item> VIGILANTE = register("vigilante",
            () -> new ItemVigilante());

    public static final RegistryObject<Item> INVISIBILITY = register("invisibility",
            () -> new ItemInvisibility(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> DEMOLITIONIST = register("demolitionist",
            () -> new ItemDemolitionist(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> PROFESSIONAL = register("professional",
            () -> new ItemProfessional(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> ASSASSIN = register("assassin",
            () -> new ItemAssassin(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> CANNIBAL = register("cannibal",
            () -> new ItemCannibal(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> ESPER = register("esper",
            () -> new ItemEsper(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> SPY = register("spy",
            () -> new ItemSpy(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));


    public static final RegistryObject<Item> VULTURE = register("vulture",
            () -> new ItemVulture(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> PIGEON = register("pigeon",
            () -> new ItemPigeon(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> DODO = register("dodo",
            () -> new ItemDoDo(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> DDODO = register("ddodo",
            () -> new ItemDDodo(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> FALCON = register("falcon",
            () -> new ItemFalcon(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(35)));

    public static final RegistryObject<Item> DUCKKNIFE = register("duckknife",
            () -> new ItemDuckKnife());

    public static final RegistryObject<Item> ASTRAL = register("astral",
            () -> new ItemAstral(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> BIRDWATCHER = register("birdwatcher",
            () -> new ItemBirdwatcher(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> BODYGUARD = register("bodyguard",
            () -> new ItemBodyGuard(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> ADVENTURER = register("adventurer",
            () -> new ItemAdventurer(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> CANADIAN = register("canadian",
            () -> new ItemCanadian(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> MIMIC = register("mimic",
            () -> new ItemMimic(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> MECHANIC = register("mechanic",
            () -> new ItemMechanic(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<Item> MORTICIAN = register("mortician",
            () -> new ItemMortician(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(25)));

    public static final RegistryObject<Item> DETECTIVE = register("detective",
            () -> new ItemDetective(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1).durability(50)));

    public static final RegistryObject<ChandlierArmorItem> CHANDLIER = ITEMS.register("chandlier",
            () -> new ChandlierArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_CHANDLIER, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<DroolArmorItem> DROOL = ITEMS.register("drool",
            () -> new DroolArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_DROOL, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<LanternArmorItem> LANTERN = ITEMS.register("lantern",
            () -> new LanternArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_LANTERN, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<MaidArmorItem> MAID = ITEMS.register("maid",
            () -> new MaidArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_LANTERN, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<PeaceArmorItem> PEACE = ITEMS.register("peace",
            () -> new PeaceArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_PEACE, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<GuitarArmorItem> GUITAR = ITEMS.register("guitar",
            () -> new GuitarArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_PEACE, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<CarArmorItem> CAR = ITEMS.register("car",
            () -> new CarArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_PEACE, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<BoxheadArmorItem> BOXHEAD = ITEMS.register("boxhead",
            () -> new BoxheadArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXHEAD, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<BananaArmorItem> BANANA = ITEMS.register("banana",
            () -> new BananaArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXHEAD, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<Item> DRESS = ITEMS.register("dress",
            () -> new DressArmorItem(new Item.Properties().tab(Main.ITEM_TAB).durability(800), CustomArmorMaterial.ARMOR_MATERIAL_DRESS));

    public static final RegistryObject<BeeArmorItem> BEE = ITEMS.register("bee",
            () -> new BeeArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_DRESS, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<BeesArmorItem> BEES = ITEMS.register("bees",
            () -> new BeesArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_DRESS, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<NightVisionArmorItem> NV = ITEMS.register("nightvision",
            () -> new NightVisionArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_DRESS, EquipmentSlotType.HEAD, new Item.Properties()));

    public static final RegistryObject<GuitarPantArmorItem> GUITARPANTS = ITEMS.register("guitarpants",
            () -> new GuitarPantArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_PEACE, EquipmentSlotType.LEGS, new Item.Properties()));

    public static final RegistryObject<SlackArmorItem> SLACK = ITEMS.register("slack",
            () -> new SlackArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXER, EquipmentSlotType.LEGS, new Item.Properties()));

    public static final RegistryObject<BoxerpArmorItem> BOXERP = ITEMS.register("boxerp",
            () -> new BoxerpArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXER, EquipmentSlotType.LEGS, new Item.Properties()));

    public static final RegistryObject<BoxerArmorItem> BOXER = ITEMS.register("boxer",
            () -> new BoxerArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXER, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<OverallArmorItem> OVERALL = ITEMS.register("overall",
            () -> new OverallArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_BOXER, EquipmentSlotType.LEGS, new Item.Properties()));

    public static final RegistryObject<MerMaidBraArmorItem> MERMAIDB = ITEMS.register("mermaidb",
            () -> new MerMaidBraArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_MERMAID, EquipmentSlotType.CHEST, new Item.Properties()));

    public static final RegistryObject<MerMaidFinArmorItem> MERMAID = ITEMS.register("mermaid",
            () -> new MerMaidFinArmorItem(CustomArmorMaterial.ARMOR_MATERIAL_MERMAID, EquipmentSlotType.LEGS, new Item.Properties()));

    public static final RegistryObject<Item> GOOSE_SPAWN_EGG = register("goose_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.GOOSE, 16775667 , 16761693,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> DUCK_SPAWN_EGG = register("duck_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.DUCK, 16775667 , 0xCF344C,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> MUMMY_SPAWN_EGG = register("mummy_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.MUMMY, 15718846 , 7663359,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> POLICECAR_SPAWN_EGG = register("policecar_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.POLICECAR, 3093179 , 16718096,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> SPACESHIP_SPAWN_EGG = register("spaceship_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SPACESHIP, 65535, 16316671,
                    new Item.Properties().tab(Main.ITEM_TAB)));

    public static final RegistryObject<Item> SPACESHIP = register("spaceship",
            () -> new ItemSpaceship(new Item.Properties().tab(Main.ITEM_TAB).stacksTo(1)));

    public static final RegistryObject<Item> PELEBALL = register("peleball",
            () -> new ItemPele(new Item.Properties().tab(Main.ITEM_TAB)));


    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

}

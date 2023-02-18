package net.joy187.joyggd.item.armor.render;




//public class CustomArmorRenderProperties implements IClientItemExtensions {
//
//    private static boolean init;
//
//    public static ModelDress ELYTRA_MODEL;
//
//
//    public static void initializeModels() {
//        init = true;
//        ELYTRA_MODEL = new ModelDress(Minecraft.getInstance().getEntityModels().bakeLayer(ModelDress.DRESS));
//
//    }
//
//
//
//    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
//        if(!init){
//            initializeModels();
//        }
//        if(itemStack.getItem() == ItemInit.DRESS.get()){
//            return ELYTRA_MODEL.withAnimations(entityLiving);
//        }
//
//        return _default;
//    }
//}
package net.joy187.joyggd.item.armor.model;

import net.joy187.joyggd.Main;
import net.joy187.joyggd.item.armor.DressArmorItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;


//public class ModelDress extends HumanoidModel {
//    public static final ModelLayerLocation DRESS = createLocation("re8_elytra", "main");
//    private static ModelLayerLocation createLocation(String model, String layer) {
//        return new ModelLayerLocation(new ResourceLocation("re8joymod", model), layer);
//    }
//    private final ModelPart rightWing;
//    private final ModelPart leftWing;
//
//    public ModelDress(ModelPart part) {
//        super(part);
////        this.leftWing = part.getChild("body").getChild("left_wing");
////        this.rightWing = part.getChild("body").getChild("right_wing");
//
//        this.leftWing = part.getChild("dress").getChild("left_wing");
//        this.rightWing = part.getChild("dress").getChild("right_wing");
//    }
//
//    public static LayerDefinition createLayer(CubeDeformation deformation) {
//        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0F);
//        PartDefinition partdefinition = meshdefinition.getRoot();
////        CubeDeformation cubedeformation = new CubeDeformation(1.0F);
////        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(32, 32).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
////        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));
//
//        PartDefinition dress = partdefinition.addOrReplaceChild("dress", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//        PartDefinition left_wing = dress.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(45, 4).mirror().addBox(-4.0F, -15.25F, 0.0F, 1.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
//                .texOffs(43, 5).addBox(-3.0F, -15.0F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(48, 5).addBox(-4.25F, -15.0F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(41, 6).addBox(-2.5F, -14.75F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(49, 6).addBox(-4.5F, -14.75F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(39, 7).addBox(-2.0F, -14.5F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(37, 8).addBox(-1.5F, -14.25F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(50, 8).addBox(-4.75F, -14.25F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(35, 9).addBox(-1.0F, -14.0F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(34, 10).addBox(-0.75F, -13.75F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(51, 10).addBox(-5.0F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(26, 14).mirror().addBox(-0.25F, -12.75F, 0.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
//                .texOffs(52, 14).addBox(-5.25F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(53, 16).addBox(-5.5F, -12.25F, 0.0F, 0.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(24, 17).addBox(1.75F, -12.0F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(54, 17).addBox(-5.75F, -12.0F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(55, 19).addBox(-6.0F, -11.5F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(56, 20).addBox(-6.25F, -11.25F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(57, 22).addBox(-6.5F, -10.75F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(53, 33).addBox(-5.5F, -8.0F, 0.0F, 0.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(54, 35).addBox(-5.75F, -7.5F, 0.0F, 0.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(55, 38).addBox(-6.0F, -6.75F, 0.0F, 0.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(56, 44).addBox(-6.25F, -5.25F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(57, 48).addBox(-6.5F, -4.25F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(58, 50).addBox(-6.75F, -3.75F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(45, 57).mirror().addBox(-3.75F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
//                .texOffs(32, 57).addBox(-0.25F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(29, 56).mirror().addBox(-0.25F, -2.25F, 0.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        PartDefinition right_wing = dress.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(16, 7).addBox(3.75F, -14.5F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(14, 8).addBox(4.25F, -14.25F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(17, 8).addBox(3.5F, -14.25F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(18, 9).addBox(3.25F, -14.0F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(13, 10).addBox(4.5F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(19, 10).addBox(3.0F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(21, 11).mirror().addBox(-0.25F, -13.5F, 0.0F, 3.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
//                .texOffs(12, 14).addBox(4.75F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(21, 14).mirror().addBox(2.25F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
//                .texOffs(11, 16).addBox(5.0F, -12.25F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(10, 17).addBox(5.25F, -12.0F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(9, 19).addBox(5.5F, -11.5F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(23, 19).addBox(2.0F, -11.5F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(8, 20).addBox(5.75F, -11.25F, 0.0F, 0.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(7, 22).addBox(6.0F, -10.75F, 0.0F, 0.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(6, 24).addBox(6.25F, -10.25F, 0.0F, 0.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(5, 27).addBox(6.5F, -9.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(5, 39).addBox(6.5F, -6.5F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(4, 41).addBox(6.75F, -6.0F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(3, 42).addBox(7.0F, -5.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(2, 43).addBox(7.25F, -5.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(6, 49).addBox(6.25F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(5, 50).addBox(6.5F, -3.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(4, 51).addBox(6.75F, -3.5F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(3, 52).addBox(7.0F, -3.25F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
//                .texOffs(6, 54).addBox(6.25F, -2.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
//
//        return LayerDefinition.create(meshdefinition, 64, 64);
//    }
//
////    public static LayerDefinition createBodyLayer() {
////        MeshDefinition meshdefinition = new MeshDefinition();
////        PartDefinition partdefinition = meshdefinition.getRoot();
////
////        PartDefinition dress = partdefinition.addOrReplaceChild("dress", CubeListBuilder.create().texOffs(45, 4).mirror().addBox(-4.0F, -15.25F, 0.0F, 1.0F, 13.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
////                .texOffs(43, 5).addBox(-3.0F, -15.0F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(48, 5).addBox(-4.25F, -15.0F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(41, 6).addBox(-2.5F, -14.75F, 0.0F, 0.0F, 13.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(49, 6).addBox(-4.5F, -14.75F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(16, 7).addBox(3.75F, -14.5F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(39, 7).addBox(-2.0F, -14.5F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(14, 8).addBox(4.25F, -14.25F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(17, 8).addBox(3.5F, -14.25F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(37, 8).addBox(-1.5F, -14.25F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(50, 8).addBox(-4.75F, -14.25F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(18, 9).addBox(3.25F, -14.0F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(35, 9).addBox(-1.0F, -14.0F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(13, 10).addBox(4.5F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(19, 10).addBox(3.0F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(34, 10).addBox(-0.75F, -13.75F, 0.0F, 0.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(51, 10).addBox(-5.0F, -13.75F, 0.0F, 0.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(21, 11).mirror().addBox(-0.25F, -13.5F, 0.0F, 3.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
////                .texOffs(12, 14).addBox(4.75F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(21, 14).mirror().addBox(2.25F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
////                .texOffs(26, 14).mirror().addBox(-0.25F, -12.75F, 0.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
////                .texOffs(52, 14).addBox(-5.25F, -12.75F, 0.0F, 0.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(11, 16).addBox(5.0F, -12.25F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(53, 16).addBox(-5.5F, -12.25F, 0.0F, 0.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(10, 17).addBox(5.25F, -12.0F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(24, 17).addBox(1.75F, -12.0F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(54, 17).addBox(-5.75F, -12.0F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(9, 19).addBox(5.5F, -11.5F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(23, 19).addBox(2.0F, -11.5F, 0.0F, 0.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(55, 19).addBox(-6.0F, -11.5F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(8, 20).addBox(5.75F, -11.25F, 0.0F, 0.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(56, 20).addBox(-6.25F, -11.25F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(7, 22).addBox(6.0F, -10.75F, 0.0F, 0.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(57, 22).addBox(-6.5F, -10.75F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(6, 24).addBox(6.25F, -10.25F, 0.0F, 0.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(5, 27).addBox(6.5F, -9.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(53, 33).addBox(-5.5F, -8.0F, 0.0F, 0.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(54, 35).addBox(-5.75F, -7.5F, 0.0F, 0.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(55, 38).addBox(-6.0F, -6.75F, 0.0F, 0.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(5, 39).addBox(6.5F, -6.5F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(4, 41).addBox(6.75F, -6.0F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(3, 42).addBox(7.0F, -5.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(2, 43).addBox(7.25F, -5.5F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(56, 44).addBox(-6.25F, -5.25F, 0.0F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(57, 48).addBox(-6.5F, -4.25F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(6, 49).addBox(6.25F, -4.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(5, 50).addBox(6.5F, -3.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(58, 50).addBox(-6.75F, -3.75F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(4, 51).addBox(6.75F, -3.5F, 0.0F, 0.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(3, 52).addBox(7.0F, -3.25F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(6, 54).addBox(6.25F, -2.75F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(29, 56).mirror().addBox(-0.25F, -2.25F, 0.0F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
////                .texOffs(32, 57).addBox(-0.25F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
////                .texOffs(45, 57).mirror().addBox(-3.75F, -2.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));
////
////        return LayerDefinition.create(meshdefinition, 64, 64);
////    }
//
//    public ModelDress withAnimations(LivingEntity entity){
//        float partialTick = Minecraft.getInstance().getFrameTime();
//        float limbSwingAmount = entity.animationSpeedOld + (entity.animationSpeed - entity.animationSpeedOld) * partialTick;
//        float limbSwing = entity.animationPosition + partialTick;
//        setupAnim(entity, limbSwing, limbSwingAmount, entity.tickCount + partialTick, 0, 0);
//        return  this;
//    }
//
//    public void setupAnim(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        float f = 0.2617994F;
//        float f1 = -0.2617994F;
//        float f2 = 0.0F;
//        float f3 = 0.0F;
//        if (entityIn.isFallFlying()) {
//            float f4 = 1.0F;
//            Vec3 vector3d = entityIn.getDeltaMovement();
//            if (vector3d.y < 0.0D) {
//                Vec3 vector3d1 = vector3d.normalize();
//                f4 = 1.0F - (float)Math.pow(-vector3d1.y, 1.5D);
//            }
//
//            f = f4 * 0.34906584F + (1.0F - f4) * f;
//            f1 = f4 * (-(float)Math.PI / 2F) + (1.0F - f4) * f1;
//        } else if (entityIn.isCrouching()) {
//            f = 0.6981317F;
//            f1 = (-(float)Math.PI / 4F);
//            f2 = -1.0F;
//            f3 = 0.08726646F;
//        }
//
//        this.leftWing.x = 5.0F;
//        this.leftWing.y = f2;
//        if (entityIn instanceof AbstractClientPlayer) {
//            AbstractClientPlayer abstractclientplayerentity = (AbstractClientPlayer)entityIn;
//            abstractclientplayerentity.elytraRotX = (float)((double)abstractclientplayerentity.elytraRotX + (double)(f - abstractclientplayerentity.elytraRotX) * 0.1D);
//            abstractclientplayerentity.elytraRotY = (float)((double)abstractclientplayerentity.elytraRotY + (double)(f3 - abstractclientplayerentity.elytraRotY) * 0.1D);
//            abstractclientplayerentity.elytraRotZ = (float)((double)abstractclientplayerentity.elytraRotZ + (double)(f1 - abstractclientplayerentity.elytraRotZ) * 0.1D);
//            this.leftWing.xRot = abstractclientplayerentity.elytraRotX;
//            this.leftWing.yRot = abstractclientplayerentity.elytraRotY;
//            this.leftWing.zRot = abstractclientplayerentity.elytraRotZ;
//        } else {
//            this.leftWing.xRot = f;
//            this.leftWing.zRot = f1;
//            this.leftWing.yRot = f3;
//        }
//
//        this.rightWing.x = -this.leftWing.x;
//        this.rightWing.yRot = -this.leftWing.yRot;
//        this.rightWing.y = this.leftWing.y;
//        this.rightWing.xRot = this.leftWing.xRot;
//        this.rightWing.zRot = -this.leftWing.zRot;
//    }
//}


public class ModelDress extends AnimatedGeoModel<DressArmorItem> {
    @Override
    public ResourceLocation getModelLocation(DressArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "geo/dress.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DressArmorItem object) {
        return new ResourceLocation(Main.MOD_ID, "textures/models/armor/dress.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DressArmorItem animatable) {
        return new ResourceLocation(Main.MOD_ID, "animations/chandlier.animation.json");
    }
}
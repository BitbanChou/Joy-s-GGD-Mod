package net.joy187.joyggd;


import net.joy187.joyggd.block.render.RenderGooseBody;
import net.joy187.joyggd.block.render.Stove2Renderer;
import net.joy187.joyggd.block.render.Stove3Renderer;
import net.joy187.joyggd.block.render.StoveRenderer;
import net.joy187.joyggd.entity.EntityDuck;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.entity.EntityMummy;
import net.joy187.joyggd.entity.EntityPoliceCar;
import net.joy187.joyggd.entity.render.*;
import net.joy187.joyggd.init.BlockEntityInit;
import net.joy187.joyggd.init.BlockInit;
import net.joy187.joyggd.init.EntityInit;
import net.joy187.joyggd.item.armor.*;
import net.joy187.joyggd.item.armor.render.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.example.client.renderer.tile.BotariumTileRenderer;
import software.bernie.example.registry.TileRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber
{

//    @SubscribeEvent
//    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
//
//    }

    @SubscribeEvent
    public static void onRegisterRenderer(final FMLClientSetupEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BULLET.get(), RenderBullet::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PELEBALL_ENTITY.get(), RenderPele::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOOSE.get(), RenderGoose::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MUMMY.get(), RenderMummy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.DUCK.get(), RenderDuck::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.POLICECAR.get(), RenderPoliceCar::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SPACESHIP.get(), RenderSpaceship::new);

        GeoArmorRenderer.registerArmorRenderer(MerMaidFinArmorItem.class,new RenderMerMaidFin());
        GeoArmorRenderer.registerArmorRenderer(MerMaidBraArmorItem.class,new RenderMerMaidBra());
        GeoArmorRenderer.registerArmorRenderer(OverallArmorItem.class,new RenderOverall());
        GeoArmorRenderer.registerArmorRenderer(BoxerArmorItem.class,new RenderBoxer());
        GeoArmorRenderer.registerArmorRenderer(BoxerpArmorItem.class,new RenderBoxerp());
        GeoArmorRenderer.registerArmorRenderer(SlackArmorItem.class,new RenderSlack());
        GeoArmorRenderer.registerArmorRenderer(GuitarPantArmorItem.class,new RenderGuitarPant());
        GeoArmorRenderer.registerArmorRenderer(NightVisionArmorItem.class,new RenderNightVision());
        GeoArmorRenderer.registerArmorRenderer(BeesArmorItem.class,new RenderBees());
        GeoArmorRenderer.registerArmorRenderer(BeeArmorItem.class,new RenderBee());
        GeoArmorRenderer.registerArmorRenderer(DressArmorItem.class,new RenderDress());
        GeoArmorRenderer.registerArmorRenderer(BoxheadArmorItem.class, new RenderBoxhead());
        GeoArmorRenderer.registerArmorRenderer(BananaArmorItem.class, new RenderBanana());
        GeoArmorRenderer.registerArmorRenderer(CarArmorItem.class, new RenderCar());
        GeoArmorRenderer.registerArmorRenderer(LanternArmorItem.class,new  RenderLantern());
        GeoArmorRenderer.registerArmorRenderer(MaidArmorItem.class,new RenderMaid());
        GeoArmorRenderer.registerArmorRenderer(PeaceArmorItem.class,new RenderPeace());
        GeoArmorRenderer.registerArmorRenderer(GuitarArmorItem.class,new  RenderGuitar());
        GeoArmorRenderer.registerArmorRenderer(DroolArmorItem.class, new RenderDrool());
        GeoArmorRenderer.registerArmorRenderer(ChandlierArmorItem.class, new RenderChandlier());

        RenderTypeLookup.setRenderLayer(BlockInit.STOVE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.STOVE2.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.STOVE3.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.GOOSEBODY.get(), RenderType.cutout());

        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.GOOSEBODY.get(),RenderGooseBody::new);
        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.STOVE3.get(), Stove3Renderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.STOVE2.get(), Stove2Renderer::new);
        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.STOVE.get(), StoveRenderer::new);
    }

//    @SubscribeEvent
//    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
//
//        GeoArmorRenderer.registerArmorRenderer(DroolArmorItem.class, new RenderDrool());
//        GeoArmorRenderer.registerArmorRenderer(ChandlierArmorItem.class, new RenderChandlier());
//    }

//    @SubscribeEvent
//    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event){
//
//        event.registerBlockEntityRenderer(BlockEntityInit.STOVE.get(), StoveRenderer::new);
//        event.registerBlockEntityRenderer(BlockEntityInit.GOOSEBODY.get(), RenderGooseBody::new);
//    }

}

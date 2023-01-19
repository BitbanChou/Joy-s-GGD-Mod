package net.joy187.joyggd;


import net.joy187.joyggd.block.StoveRenderer;
import net.joy187.joyggd.block.render.RenderGooseBody;
import net.joy187.joyggd.entity.EntityDuck;
import net.joy187.joyggd.entity.EntityGoose;
import net.joy187.joyggd.entity.EntityMummy;
import net.joy187.joyggd.entity.render.*;
import net.joy187.joyggd.init.BlockEntityInit;
import net.joy187.joyggd.init.EntityInit;
import net.joy187.joyggd.item.armor.ChandlierArmorItem;
import net.joy187.joyggd.item.armor.DroolArmorItem;
import net.joy187.joyggd.item.armor.render.RenderChandlier;
import net.joy187.joyggd.item.armor.render.RenderDrool;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.example.client.renderer.armor.GeckoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEventSubscriber
{

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(EntityInit.BULLET.get(), RenderBullet::new);
        event.registerEntityRenderer(EntityInit.PELEBALL_ENTITY.get(), RenderPele::new);
        event.registerEntityRenderer(EntityInit.GOOSE.get(), RenderGoose::new);
        event.registerEntityRenderer(EntityInit.MUMMY.get(), RenderMummy::new);
        event.registerEntityRenderer(EntityInit.DUCK.get(), RenderDuck::new);
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.MUMMY.get(), EntityMummy.prepareAttributes().build());
        event.put(EntityInit.GOOSE.get(), EntityGoose.prepareAttributes().build());
        event.put(EntityInit.DUCK.get(), EntityDuck.prepareAttributes().build());
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {

        GeoArmorRenderer.registerArmorRenderer(DroolArmorItem.class, RenderDrool::new);
        GeoArmorRenderer.registerArmorRenderer(ChandlierArmorItem.class, RenderChandlier::new);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event){

        event.registerBlockEntityRenderer(BlockEntityInit.STOVE.get(), StoveRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.GOOSEBODY.get(), RenderGooseBody::new);
    }

}

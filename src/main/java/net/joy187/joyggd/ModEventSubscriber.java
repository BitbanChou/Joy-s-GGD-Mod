package net.joy187.joyggd;

import net.joy187.joyggd.entity.*;
import net.joy187.joyggd.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.MUMMY.get(), EntityMummy.prepareAttributes().build());
        event.put(EntityInit.GOOSE.get(), EntityGoose.prepareAttributes().build());
        event.put(EntityInit.DUCK.get(), EntityDuck.prepareAttributes().build());
        event.put(EntityInit.POLICECAR.get(), EntityPoliceCar.prepareAttributes().build());
        event.put(EntityInit.SPACESHIP.get(), EntitySpaceship.prepareAttributes().build());
    }

}

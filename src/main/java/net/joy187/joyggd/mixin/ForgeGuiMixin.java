//package net.joy187.joyggd.mixin;
//
//
//import net.joy187.joyggd.init.ItemInit;
//import net.minecraft.world.entity.player.Player;
//import net.minecraftforge.client.gui.overlay.ForgeGui;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(ForgeGui.class)
//public abstract class ForgeGuiMixin {
//
//    @Inject(method = {"renderSpyglassOverlay"}, at = {@At(value = "HEAD",target = "renderSpyglassOverlay()")}, cancellable = true)
//    public void isScoping(CallbackInfo ci) {
//        ForgeGui livingEntity = ((ForgeGui)(Object)this);
//        if ()
//        {
//            livingEntity.renderSpyglassOverlay(0.5F);
//        }
////        if (livingEntity instanceof Player) {
////            if(livingEntity.getUseItem().is(ItemInit.BIRDWATCHER.get()))
////            {
////                return true;
////            }
////        }
////        livingEntity.getUseItem().is(ItemInit.BIRDWATCHER.get());
//    }
//
//}

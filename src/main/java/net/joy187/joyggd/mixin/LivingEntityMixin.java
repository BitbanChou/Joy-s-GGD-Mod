//package net.joy187.joyggd.mixin;
//
//
//import net.joy187.joyggd.init.ItemInit;
//import net.minecraft.advancements.CriteriaTriggers;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.stats.Stats;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.effect.EffectInstance;
//import net.minecraft.world.effect.Effects;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.item.ItemStack;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(LivingEntity.class)
//public abstract class LivingEntityMixin {
//
//    @Inject(method = {"checkTotemDeathProtection"}, at = {@At("HEAD")}, cancellable = true)
//    private void checkProtection(DamageSource source, CallbackInfoReturnable<Boolean> info) {
//        LivingEntity livingEntity = ((LivingEntity)(Object)this);
//        if (livingEntity instanceof ServerPlayerEntity player) {
//            ItemStack itemStack = null;
//            Inventory inventory = player.inventory;
//            for (int i = 0; i < inventory.getContainerSize(); i++) {
//                ItemStack stack = inventory.getItem(i);
//                if (stack.getItem().equals(ItemInit.BODYGUARD.get())) {
//                    itemStack = stack;
//                    break;
//                }
//            }
//            if (itemStack != null) {
//                player.awardStat(Stats.ITEM_USED.get(ItemInit.BODYGUARD.get()));
//                CriteriaTriggers.USED_TOTEM.trigger(player, itemStack);
//                itemStack.shrink(1);
//                player.setHealth(10.0F);
//                player.removeAllEffects();
//                player.addEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
//                player.level.broadcastEntityEvent(player, (byte) 35);
//                info.setReturnValue(true);
//            }
//        }
//    }
//}
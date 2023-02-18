package net.joy187.joyggd.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;



public class EffectMimic extends BaseEffect {
    public EffectMimic(EffectType type, int color, boolean isInstant) {
        super(type, color, isInstant);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 2 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified) {
        if(living instanceof MobEntity)
        {
            if(((MobEntity) living).getTarget() instanceof PlayerEntity)
            {
                ((MobEntity) living).setTarget(null);
            }
        }

    }

    @Override
    public boolean isBeneficial()
    {
        return true;
    }
}

package net.joy187.joyggd.effects;

import net.joy187.joyggd.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class EffectEsper extends BaseEffect {

    public EffectEsper(EffectType type, int color, boolean isInstant) {
        super(type, color, isInstant);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 1 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified) {
        if(living.getEffect(EffectInit.ESPER.get())!=null)
        {
            living.hurt(DamageSource.MAGIC,2*amplified);
        }

    }

    @Override
    public boolean isBeneficial()
    {
        return false;
    }
}

package net.joy187.joyggd.effects;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;


public class EffectPigeon extends BaseEffect{
	   public EffectPigeon(EffectType type, int color, boolean isInstant) {
	        super(type, color, isInstant);
	   }

	   @Override
	   protected boolean canApplyEffect(int remainingTicks, int level) {
	        return remainingTicks % 7 == 0;
	   }

	@Override
	public void applyEffectTick(LivingEntity living, int amplified) {
		amplified++;
		Random ran = new Random();
		int co = ran.nextInt(5);
		//EntityUtil.spawnCubeParticleAround(living, ParticleTypes.SOUL,1);
		if (co == 0 || co == 1) living.setDeltaMovement(Math.sin(0.02) * 0.02D, 0.0D, 0.0D);
		else living.setDeltaMovement(-0.02D, 0.0D, 0.0D);
		if (co == 2 || co == 1) living.setDeltaMovement(0.0D, 0.0D, 0.02D);
		else living.setDeltaMovement(0.0D, 0.0D, -Math.sin(0.02) * 0.02D);
//	        living.motionY += (0.05D * (double)(living.getActivePotionEffect(ModPotions.DEADLY).getAmplifier() + 1) - living.motionY) * 0.2D;
		//if(co==3) living.heal(ran.nextFloat());
		if (co == 3 || co == 4 && living.getHealth() > 1F) {
			if (amplified > 1) living.hurt(DamageSource.MAGIC, amplified * 0.5F);
			else living.hurt(DamageSource.MAGIC, 1);
		}
	}

	@Override
	public boolean isBeneficial()
	{
	   	return false;
	}
}

package net.joy187.joyggd.effects;

import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.init.SoundInit;
import net.joy187.joyggd.util.CommonFunctions;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class EffectBoom extends BaseEffect{

    public EffectBoom(MobEffectCategory type, int color, boolean isInstant) {

        super(type, color, isInstant);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 5 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified) {
//        living.level.addParticle(ParticleTypes.FIREWORK, living.getX()+living.getRandomX(1),
//                living.getY()+living.getRandomX(1), living.getZ(), 0.0D, 0.0D, 0.0D);
        amplified ++;
        living.hurt(DamageSource.CACTUS, 0.05F*amplified);

        Random ran = new Random();
        int co = ran.nextInt(5);
        for (int j = 0; j < 10; j++)
        {
            Vec3 pos = new Vec3(living.position().x,living.position().y,living.position().z);
            Random random = new Random();
            float flunc = (float) CommonFunctions.flunctate(0, 0.8, random);
            living.level.addParticle(ParticleTypes.FIREWORK, pos.x + 0.8, pos.y, pos.z + flunc, 0,0,0);
            living.level.addParticle(ParticleTypes.FIREWORK, pos.x - 0.8, pos.y, pos.z + flunc, 0,0,0);
            living.level.addParticle(ParticleTypes.FIREWORK, pos.x + flunc, pos.y, pos.z + 0.8, 0,0,0);
            living.level.addParticle(ParticleTypes.FIREWORK, pos.x + flunc, pos.y, pos.z - 0.8, 0,0,0);
        }

        if(living.getEffect(EffectInit.BOOM.get())!=null)
        {
            //System.out.println("tick:"+living.getEffect(EffectInit.BOOM.get()).getDuration());
            if(living.getEffect(EffectInit.BOOM.get()).getDuration()==5){
                living.level.explode(living, living.getX(), living.getY()+0.01D, living.getZ(), 4F+amplified,
                        Explosion.BlockInteraction.NONE);
                //living.hurt(DamageSource.mobAttack(living), 10.0F);
                living.hurt(DamageSource.CACTUS, 1F*amplified);
            }
        }
//        amplified ++;
//        living.hurt(DamageSource.WITHER, 1F*amplified);

//        amplified ++;
//        Random ran = new Random();
//        int co = ran.nextInt(5);
//        for (EquipmentSlot slot: EquipmentSlot.values()) {
//            DamageItemInSlot(slot, living, co*amplified);
//        }

        //EntityUtil.spawnCubeParticleAround(living, EnumParticleTypes.SPELL_MOB_AMBIENT,0.5);

    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}

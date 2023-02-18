package net.joy187.joyggd.effects;

import net.joy187.joyggd.init.EffectInit;
import net.joy187.joyggd.util.MathUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;

public class EffectAdventurer extends BaseEffect {
    public EffectAdventurer(EffectType type, int color, boolean isInstant) {
        super(type, color, isInstant);
    }

    @Override
    protected boolean canApplyEffect(int remainingTicks, int level) {
        return remainingTicks % 2 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplified) {
//        living.level.addParticle(ParticleTypes.FIREWORK, living.getX()+living.getRandomX(1),
//                living.getY()+living.getRandomX(1), living.getZ(), 0.0D, 0.0D, 0.0D);
        amplified++;

        if (living.getEffect(EffectInit.ADVENTURER.get()) != null) {
            //System.out.println("tick:"+living.getEffect(EffectInit.BOOM.get()).getDuration());
            living.fallDistance=0;

            if(living.getLastDamageSource()== DamageSource.DROWN)
            {
                living.setAirSupply(200);
            }

            if(living.getLastDamageSource()== DamageSource.OUT_OF_WORLD)
            {
                living.heal(0.5F);
                living.addEffect(new EffectInstance(Effects.LEVITATION,200));
            }

            if(living.isOnFire()) living.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE,100)); //.remainingFireTicks

//            if(living.getLastDamageSource()== DamageSource.F)
//            {
//                living.level.setBlock(living.getOnPos(), Blocks.SNOW_BLOCK.defaultBlockState(),4);
//            }

            if(living.getLastDamageSource()== DamageSource.CACTUS) {
                living.heal(0.25F);
            }

            if (living.isOnGround()) {

                if(living.getFeetBlockState().is(Blocks.SOUL_CAMPFIRE))
                {
//                Block t = living.getBlockStateOn().getBlock();
//                BlockPos blockpos = living.blockPosition();
                    BlockPos blockpos = MathUtils.getOnPosL(living);
                    if(living.getLastDamageSource()== DamageSource.IN_FIRE)
                        living.heal(0.125F);
                }

                if(living.getFeetBlockState().is(Blocks.CAMPFIRE) || living.getFeetBlockState().is(Blocks.SOUL_CAMPFIRE))
                {
                    BlockPos blockpos = MathUtils.getOnPosL(living);
                    if(living.getLastDamageSource()== DamageSource.IN_FIRE)
                        living.heal(0.25F);
                }

            }

        }

    }

    @Override
    public boolean isBeneficial() {
        return true;
    }
}

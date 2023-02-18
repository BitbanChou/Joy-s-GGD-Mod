package net.joy187.joyggd.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Random;




public class CommonFunctions {
    
	public static double sq3 = 1.732;
	public static float angle = 0f;

	public static double flunctate(double ori, double radius, Random random) {
        return ori + (random.nextFloat() * 2 - 1) * radius;
    }
	
	
	public static void spawnHexAround(World level, BlockPos blockpos, float amp) {
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.5*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.6*amp, blockpos.getY(), blockpos.getZ() + 0.4*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.7*amp, blockpos.getY(), blockpos.getZ() + 0.3*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.8*amp, blockpos.getY(), blockpos.getZ() + 0.2*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.9*amp, blockpos.getY(), blockpos.getZ() + 0.1*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +1.0*amp, blockpos.getY(), blockpos.getZ() , 0*amp,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.9*amp, blockpos.getY(), blockpos.getZ() - 0.1*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.8*amp, blockpos.getY(), blockpos.getZ() - 0.2*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.7*amp, blockpos.getY(), blockpos.getZ() - 0.3*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.6*amp, blockpos.getY(), blockpos.getZ() - 0.4*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.5*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.3*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.1*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.1*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.3*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.5*amp, blockpos.getY(), blockpos.getZ() - 0.5*sq3*amp, 0,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.6*amp, blockpos.getY(), blockpos.getZ() - 0.4*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.7*amp, blockpos.getY(), blockpos.getZ() - 0.3*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.8*amp, blockpos.getY(), blockpos.getZ() - 0.2*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.9*amp, blockpos.getY(), blockpos.getZ() - 0.1*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -1*amp, blockpos.getY(), blockpos.getZ() , 0*amp,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.9*amp, blockpos.getY(), blockpos.getZ() + 0.1*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.8*amp, blockpos.getY(), blockpos.getZ() + 0.2*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.7*amp, blockpos.getY(), blockpos.getZ() + 0.3*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.6*amp, blockpos.getY(), blockpos.getZ() + 0.4*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.5*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0.5,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.3*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() -0.1*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.1*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0,0);
        level.addParticle(ParticleTypes.ENTITY_EFFECT,blockpos.getX() +0.3*amp, blockpos.getY(), blockpos.getZ() + 0.5*sq3*amp, 0,0,0);
	}
	
	
    public static void spawnHaloParticleAround(LivingEntity entity, float radius)
    {
        for (int i = 0; i < 10; i++)
        {
            float deltaOmega = 1.0f * i;//ModConfig.DEBUG_CONF.HALO_OMEGA;
            Vector3d pos = new Vector3d(entity.getX() + radius * Math.sin(angle + deltaOmega),  entity.getY() + 0.1f * entity.getRandom().nextFloat(), entity.getZ() + radius * Math.cos(angle + deltaOmega));
            entity.level.addParticle(ParticleTypes.HEART, pos.x, pos.y, pos.z, 0,0,0);
        }
    }
    
//    public static void spawnCubeParticleAround(LivingEntity entity, float radius)
//    {
//        for (int j = 0; j < 10; j++)
//        {
//            Vector3d pos = new Vector3d(entity.position().x + radius * Math.sin(0 ),  entity.position().y, entity.position().z + radius * Math.cos(0));
//            Random random = new Random();
//            float flunc = (float) CommonFunctions.flunctate(0, radius, random);
//            entity.level.addParticle(particleTypes, pos.x + radius, pos.y, pos.z + flunc, 0,0,0);
//            entity.level.addParticle(particleTypes, pos.x - radius, pos.y, pos.z + flunc, 0,0,0);
//            entity.level.addParticle(particleTypes, pos.x + flunc, pos.y, pos.z + radius, 0,0,0);
//            entity.level.addParticle(particleTypes, pos.x + flunc, pos.y, pos.z - radius, 0,0,0);
//        }
//    }
}

package net.joy187.joyggd.entity;


import net.joy187.joyggd.init.EntityInit;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Optional;


public class EntityPele extends ProjectileItemEntity {

    private boolean hasEntity;
    private ItemStack currentPokeball = new ItemStack(ItemInit.PELEBALL.get(), 1);

    public EntityPele(EntityType<? extends EntityPele> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityPele(EntityType<? extends EntityPele> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public EntityPele(LivingEntity livingEntityIn, World worldIn, ItemStack stack) {
        super(EntityInit.PELEBALL_ENTITY.get(), livingEntityIn, worldIn);
        this.currentPokeball = stack;
        this.hasEntity = false;
        if (this.currentPokeball.hasTag()) {
            this.hasEntity = NBTHelper.hasTag(stack, "StoredEntity");
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (!level.isClientSide) {
            if (result.getType() == RayTraceResult.Type.BLOCK) {
                if (this.hasEntity) {
                    Optional<Entity> loadEntity = EntityType.create(NBTHelper.getTag(currentPokeball, "StoredEntity"), this.level);
                    if (loadEntity.isPresent()) {
                        Entity spawnEntity = loadEntity.get();
                        spawnEntity.moveTo(this.getX(), this.getY() + 1.0D, this.getZ(), this.getYHeadRot(), 0.0F);
                        this.level.addFreshEntity(spawnEntity);
                    }

                    // Always reset pokeball
                    this.currentPokeball = new ItemStack(ItemInit.PELEBALL.get());
                }
            } else if (result.getType() == RayTraceResult.Type.ENTITY) {
                EntityRayTraceResult entityResult = (EntityRayTraceResult) result;

                if (entityResult != null) {
                    Entity hitEntity = entityResult.getEntity();
                    if (hitEntity != null && !this.hasEntity && !(hitEntity instanceof PlayerEntity || hitEntity instanceof EnderDragonEntity || hitEntity instanceof EnderDragonPartEntity)) {
                        if (hitEntity instanceof LivingEntity) {

                            LivingEntity livingEntity = (LivingEntity) hitEntity;
                            CompoundNBT entity = livingEntity.serializeNBT();
                            entity.putString("peleball_name", livingEntity.getType().getDescriptionId());

                            NBTHelper.putTag(currentPokeball, "StoredEntity", entity);
                            this.currentPokeball.hurtAndBreak(1, livingEntity, (ent) -> {
                            });
                            this.currentPokeball.setCount(1);

                            hitEntity.remove();
                        }
                    }
                }
            }

            this.spawnAtLocation(this.currentPokeball, 0.2F);
            this.level.broadcastEntityEvent(this, (byte) 3);
            this.removeAfterChangingDimensions();
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(ParticleTypes.SNEEZE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.PELEBALL.get();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
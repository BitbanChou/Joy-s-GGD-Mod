package net.joy187.joyggd.entity;


import net.joy187.joyggd.init.EntityInit;
import net.joy187.joyggd.init.ItemInit;
import net.joy187.joyggd.util.NBTHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import java.util.Optional;

public class EntityPele extends ThrowableItemProjectile {

    private boolean hasEntity;
    private ItemStack currentPokeball = new ItemStack(ItemInit.PELEBALL.get(), 1);

    public EntityPele(EntityType<? extends EntityPele> type, Level worldIn) {
        super(type, worldIn);
    }

    public EntityPele(EntityType<? extends EntityPele> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn);
    }

    public EntityPele(LivingEntity livingEntityIn, Level worldIn, ItemStack stack) {
        super(EntityInit.PELEBALL_ENTITY.get(), livingEntityIn, worldIn);
        this.currentPokeball = stack;
        this.hasEntity = false;
        if (this.currentPokeball.hasTag()) {
            this.hasEntity = NBTHelper.hasTag(stack, "StoredEntity");
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (!level.isClientSide) {
            if (result.getType() == HitResult.Type.BLOCK) {
                if (this.hasEntity) {
                    Optional<Entity> loadEntity = EntityType.create(NBTHelper.getTag(currentPokeball, "StoredEntity"), this.level);
                    if (loadEntity.isPresent()) {
                        Entity spawnEntity = loadEntity.get();
                        spawnEntity.moveTo(this.getX(), this.getY() + 1.0D, this.getZ(), this.getYRot(), 0.0F);
                        this.level.addFreshEntity(spawnEntity);
                    }

                    // Always reset pokeball
                    this.currentPokeball = new ItemStack(ItemInit.PELEBALL.get());
                }
            } else if (result.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityResult = (EntityHitResult) result;

                if (entityResult != null) {
                    Entity hitEntity = entityResult.getEntity();
                    if (hitEntity != null && !this.hasEntity && !(hitEntity instanceof Player || hitEntity instanceof EnderDragon || hitEntity instanceof EnderDragonPart)) {
                        if (hitEntity instanceof LivingEntity) {

                            LivingEntity livingEntity = (LivingEntity) hitEntity;
                            CompoundTag entity = livingEntity.serializeNBT();
                            entity.putString("peleball_name", livingEntity.getType().getDescriptionId());

                            NBTHelper.putTag(currentPokeball, "StoredEntity", entity);
                            this.currentPokeball.hurtAndBreak(1, livingEntity, (ent) -> {
                            });
                            this.currentPokeball.setCount(1);

                            hitEntity.discard();
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
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
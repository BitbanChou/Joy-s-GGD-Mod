package net.joy187.joyggd.entity;

import io.netty.buffer.Unpooled;
import net.joy187.joyggd.init.EntityInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;


public class EntityBullet extends ThrowableItemProjectile{

    public double damage;
    private int ticksInGround;
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(EntityBullet.class, EntityDataSerializers.INT);

	
	public EntityBullet(EntityType<?> entityIn, Level level) {
		super((EntityType<? extends EntityBullet>) entityIn, level);
        //this.size(1.0F, 1.0F);
        this.damage = 18D;
		// TODO Auto-generated constructor stub
	}
	
	public EntityBullet(Level world, LivingEntity entity) {
	    super(EntityInit.BULLET.get(), entity, world);
	    this.damage= 18D;
	}
	
	
	@Override
	protected Item getDefaultItem() {
		return Items.ARROW;
	}
	   
    protected void onHitEntity(EntityHitResult result) {

        Entity entity = result.getEntity();

        int i = 10;
        if(entity instanceof Blaze || entity instanceof Ravager){
            i = 18;
        }
        else if(entity instanceof EntityGoose){
            entity.remove(RemovalReason.KILLED);
        }

        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)(i+random.nextFloat()*0.5*this.damage));
        super.onHitEntity(result);
    }
    
    
    protected void onHit(EntityHitResult p_70227_1_) {
        super.onHit(p_70227_1_);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove(RemovalReason.KILLED);
        }
    }
    
    @Override
    public Packet<?> getAddEntityPacket() {
    	FriendlyByteBuf pack = new FriendlyByteBuf(Unpooled.buffer());
        pack.writeDouble(getX());
        pack.writeDouble(getY());
        pack.writeDouble(getZ());
        pack.writeInt(getId());
        pack.writeUUID(getUUID());

        return NetworkHooks.getEntitySpawningPacket(this);

    }
    
}

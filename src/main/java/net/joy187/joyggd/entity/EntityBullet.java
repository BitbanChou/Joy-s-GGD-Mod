package net.joy187.joyggd.entity;

import io.netty.buffer.Unpooled;
import net.joy187.joyggd.init.EntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;



public class EntityBullet extends ProjectileItemEntity {

    public double damage;
    private int ticksInGround;

	
	public EntityBullet(EntityType<?> entityIn, World level) {
		super((EntityType<? extends EntityBullet>) entityIn, level);
        //this.size(1.0F, 1.0F);
        this.damage = 18D;
		// TODO Auto-generated constructor stub
	}
	
	public EntityBullet(World world, LivingEntity entity) {
	    super(EntityInit.BULLET.get(), entity, world);
	    this.damage= 18D;
	}
	
	
	@Override
	protected Item getDefaultItem() {
		return Items.ARROW;
	}
	   
    protected void onHitEntity(EntityRayTraceResult result) {

        Entity entity = result.getEntity();

        int i = 10;
        if(entity instanceof BlazeEntity || entity instanceof RavagerEntity){
            i = 18;
        }
        else if(entity instanceof EntityGoose){
            entity.remove();
        }

        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)(i+random.nextFloat()*0.5*this.damage));
        super.onHitEntity(result);
    }
    
    
    protected void onHit(RayTraceResult p_70227_1_) {
        super.onHit(p_70227_1_);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }
    
    @Override
    public IPacket<?> getAddEntityPacket() {
        PacketBuffer pack = new PacketBuffer(Unpooled.buffer());
        pack.writeDouble(getX());
        pack.writeDouble(getY());
        pack.writeDouble(getZ());
        pack.writeInt(getId());
        pack.writeUUID(getUUID());

        return NetworkHooks.getEntitySpawningPacket(this);

    }
    
}

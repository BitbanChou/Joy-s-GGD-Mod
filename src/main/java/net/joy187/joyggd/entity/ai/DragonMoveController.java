package net.joy187.joyggd.entity.ai;

import net.joy187.joyggd.entity.EntitySpaceship;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;


public class DragonMoveController extends MovementController
{
    private final EntitySpaceship dragon;

    public DragonMoveController(EntitySpaceship dragon)
    {
        super(dragon);
        this.dragon = dragon;
    }

    @Override
    public void tick()
    {
        // original movement behavior if the entity isn't flying
        if (!dragon.isFlying())
        {
            super.tick();
            return;
        }

        if (operation == Action.MOVE_TO)
        {
            operation = Action.WAIT;
            double xDif = wantedX - mob.getX();
            double yDif = wantedY - mob.getY();
            double zDif = wantedZ - mob.getZ();
            double sq = xDif * xDif + yDif * yDif + zDif * zDif;
            if (sq < (double) 2.5000003E-7F)
            {
                mob.setYya(0.0F);
                mob.setZza(0.0F);
                return;
            }

            float speed = (float) (speedModifier * mob.getAttributeValue(Attributes.FLYING_SPEED));
            double distSq = Math.sqrt(xDif * xDif + zDif * zDif);
            mob.setSpeed(speed);
            if (Math.abs(yDif) > (double) 1.0E-5F || Math.abs(distSq) > (double) 1.0E-5F)
                mob.setYya(yDif > 0d? speed : -speed);

            float yaw = (float) (Math.atan2(zDif, xDif) * (double) (180F / (float) Math.PI)) - 90.0F;
            mob.setYHeadRot(rotlerp(mob.yRot, yaw, 6));
        }
        else
        {
            mob.setYya(0);
            mob.setZza(0);
        }
    }
}

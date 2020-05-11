// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityThrowable;

public class EntityEndestPearl extends EntityThrowable
{
    public EntityEndestPearl(final World world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityEndestPearl(final World world, final EntityLivingBase ent) {
        super(world, ent);
    }
    
    public EntityEndestPearl(final World world) {
        super(world);
    }
    
    protected void func_70184_a(final MovingObjectPosition pos) {
        if (pos.field_72308_g != null) {
            pos.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), 0.0f);
        }
        for (int i = 0; i < 100; ++i) {
            this.field_70170_p.func_72869_a("portal", this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 3.0, this.field_70146_Z.nextGaussian() * 3.0, this.field_70146_Z.nextGaussian() * 3.0);
        }
        if (!this.field_70170_p.field_72995_K) {
            final Entity ent = new EntityGapingVoid(this.field_70170_p);
            final ForgeDirection dir = ForgeDirection.getOrientation(pos.field_72310_e);
            ent.func_70012_b(this.field_70165_t + dir.offsetX * 0.25, this.field_70163_u + dir.offsetY * 0.25, this.field_70161_v + dir.offsetZ * 0.25, this.field_70177_z, 0.0f);
            this.field_70170_p.func_72838_d(ent);
            this.func_70106_y();
        }
    }
}

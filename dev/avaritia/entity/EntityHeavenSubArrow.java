// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityArrow;

public class EntityHeavenSubArrow extends EntityArrow
{
    public EntityHeavenSubArrow(final World world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityHeavenSubArrow(final World world, final EntityLivingBase entity, final EntityLivingBase entity2, final float something, final float otherthing) {
        super(world, entity, entity2, something, otherthing);
    }
    
    public EntityHeavenSubArrow(final World world, final EntityLivingBase entity, final float something) {
        super(world, entity, something);
    }
    
    public EntityHeavenSubArrow(final World world) {
        super(world);
    }
    
    public void func_70071_h_() {
        this.field_70125_A = 0.0f;
        this.field_70177_z = 0.0f;
        super.func_70071_h_();
        if (EntityHeavenArrow.getInGround(this) && EntityHeavenArrow.getTicksInGround(this) >= 20) {
            this.func_70106_y();
        }
    }
}

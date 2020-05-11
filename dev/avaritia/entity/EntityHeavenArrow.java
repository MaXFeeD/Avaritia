// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import java.util.Random;
import java.lang.reflect.Field;
import net.minecraft.entity.projectile.EntityArrow;

public class EntityHeavenArrow extends EntityArrow
{
    public static Field inGroundField;
    public static Field ticksInGroundField;
    public boolean impacted;
    public Random randy;
    
    public EntityHeavenArrow(final World world, final double x, final double y, final double z) {
        super(world, x, y, z);
        this.impacted = false;
        this.randy = new Random();
    }
    
    public EntityHeavenArrow(final World world, final EntityLivingBase entity, final EntityLivingBase entity2, final float something, final float otherthing) {
        super(world, entity, entity2, something, otherthing);
        this.impacted = false;
        this.randy = new Random();
    }
    
    public EntityHeavenArrow(final World world, final EntityLivingBase entity, final float something) {
        super(world, entity, something);
        this.impacted = false;
        this.randy = new Random();
    }
    
    public EntityHeavenArrow(final World world) {
        super(world);
        this.impacted = false;
        this.randy = new Random();
    }
    
    public void func_70071_h_() {
        this.field_70125_A = 0.0f;
        this.field_70177_z = 0.0f;
        super.func_70071_h_();
        if (!this.impacted) {
            try {
                if (EntityHeavenArrow.inGroundField.getBoolean(this)) {
                    this.impacted = true;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.impacted && !this.field_70170_p.field_72995_K) {
                this.barrage();
            }
        }
        if (getInGround(this) && getTicksInGround(this) >= 100) {
            this.func_70106_y();
        }
    }
    
    public void func_70014_b(final NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("impacted", this.impacted);
    }
    
    public void func_70037_a(final NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.impacted = tag.func_74767_n("impacted");
    }
    
    public static boolean getInGround(final EntityArrow arrow) {
        try {
            return EntityHeavenArrow.inGroundField.getBoolean(arrow);
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public static int getTicksInGround(final EntityArrow arrow) {
        try {
            return EntityHeavenArrow.ticksInGroundField.getInt(arrow);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public void barrage() {
        for (int i = 0; i < 35; ++i) {
            final double angle = this.randy.nextDouble() * 2.0 * 3.141592653589793;
            final double dist = this.randy.nextGaussian() * 0.75;
            final double x = Math.sin(angle) * dist + this.field_70165_t;
            final double z = Math.cos(angle) * dist + this.field_70161_v;
            final double y = this.field_70163_u + 25.0;
            final double dangle = this.randy.nextDouble() * 2.0 * 3.141592653589793;
            final double ddist = this.randy.nextDouble() * 0.35;
            final double dx = Math.sin(dangle) * ddist;
            final double dz = Math.cos(dangle) * ddist;
            final EntityArrow arrow = new EntityHeavenSubArrow(this.field_70170_p, x, y, z);
            arrow.field_70250_c = this.field_70250_c;
            arrow.func_70024_g(dx, -(this.randy.nextDouble() * 1.85 + 0.15), dz);
            arrow.func_70239_b(this.func_70242_d());
            arrow.func_70243_d(true);
            this.field_70170_p.func_72838_d((Entity)arrow);
        }
    }
    
    static {
        try {
            EntityHeavenArrow.inGroundField = ReflectionHelper.findField((Class)EntityArrow.class, new String[] { "inGround", "field_70254_i" });
            EntityHeavenArrow.ticksInGroundField = ReflectionHelper.findField((Class)EntityArrow.class, new String[] { "ticksInGround", "field_70252_j" });
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.command.IEntitySelector;
import java.util.Random;
import net.minecraft.entity.Entity;

public class EntityGapingVoid extends Entity
{
    private static Random randy;
    public static final int maxLifetime = 186;
    public static double collapse;
    public static double suckrange;
    public static final IEntitySelector sucklector;
    public static final IEntitySelector nomlector;
    
    public EntityGapingVoid(final World world) {
        super(world);
        this.field_70178_ae = true;
        this.func_70105_a(0.1f, 0.1f);
        this.field_70158_ak = true;
        this.field_70155_l = 100.0;
    }
    
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(12, (Object)0);
        this.field_70180_af.func_82708_h(12);
    }
    
    public void func_70071_h_() {
        super.func_70071_h_();
        final int age = this.getAge();
        if (age >= 186) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 6.0f, true);
            this.func_70106_y();
        }
        else {
            if (age == 0) {
                this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "Avaritia:gapingVoid", 8.0f, 1.0f);
            }
            this.setAge(age + 1);
        }
        final double particlespeed = 4.5;
        final double size = getVoidScale(age) * 0.5 - 0.2;
        for (int i = 0; i < 50; ++i) {
            final Vec3 pootdir = Vec3.func_72443_a(0.0, 0.0, size);
            pootdir.func_72442_b(EntityGapingVoid.randy.nextFloat() * 180.0f);
            pootdir.func_72440_a(EntityGapingVoid.randy.nextFloat() * 360.0f);
            final Vec3 func_72432_b;
            final Vec3 pootspeed = func_72432_b = pootdir.func_72432_b();
            func_72432_b.field_72450_a *= particlespeed;
            final Vec3 vec3 = pootspeed;
            vec3.field_72448_b *= particlespeed;
            final Vec3 vec4 = pootspeed;
            vec4.field_72449_c *= particlespeed;
            this.field_70170_p.func_72869_a("portal", this.field_70165_t + pootdir.field_72450_a, this.field_70163_u + pootdir.field_72448_b, this.field_70161_v + pootdir.field_72449_c, pootspeed.field_72450_a, pootspeed.field_72448_b, pootspeed.field_72449_c);
        }
        final AxisAlignedBB suckzone = AxisAlignedBB.func_72330_a(this.field_70165_t - EntityGapingVoid.suckrange, this.field_70163_u - EntityGapingVoid.suckrange, this.field_70161_v - EntityGapingVoid.suckrange, this.field_70165_t + EntityGapingVoid.suckrange, this.field_70163_u + EntityGapingVoid.suckrange, this.field_70161_v + EntityGapingVoid.suckrange);
        final List<Entity> sucked = (List<Entity>)this.field_70170_p.func_82733_a((Class)Entity.class, suckzone, EntityGapingVoid.sucklector);
        final double radius = getVoidScale(age) * 0.5;
        for (final Entity suckee : sucked) {
            if (suckee != this) {
                final double dx = this.field_70165_t - suckee.field_70165_t;
                final double dy = this.field_70163_u - suckee.field_70163_u;
                final double dz = this.field_70161_v - suckee.field_70161_v;
                final double lensquared = dx * dx + dy * dy + dz * dz;
                final double len = Math.sqrt(lensquared);
                final double lenn = len / EntityGapingVoid.suckrange;
                if (len > EntityGapingVoid.suckrange) {
                    continue;
                }
                final double strength = (1.0 - lenn) * (1.0 - lenn);
                final double power = 0.075 * radius;
                final Entity entity = suckee;
                entity.field_70159_w += dx / len * strength * power;
                final Entity entity2 = suckee;
                entity2.field_70181_x += dy / len * strength * power;
                final Entity entity3 = suckee;
                entity3.field_70179_y += dz / len * strength * power;
            }
        }
        final double nomrange = radius * 0.95;
        final AxisAlignedBB nomzone = AxisAlignedBB.func_72330_a(this.field_70165_t - nomrange, this.field_70163_u - nomrange, this.field_70161_v - nomrange, this.field_70165_t + nomrange, this.field_70163_u + nomrange, this.field_70161_v + nomrange);
        final List<Entity> nommed = (List<Entity>)this.field_70170_p.func_82733_a((Class)EntityLivingBase.class, nomzone, EntityGapingVoid.nomlector);
        for (final Entity nommee : nommed) {
            if (nommee != this) {
                final double dx2 = this.field_70165_t - nommee.field_70165_t;
                final double dy2 = this.field_70163_u - nommee.field_70163_u;
                final double dz2 = this.field_70161_v - nommee.field_70161_v;
                final double lensquared2 = dx2 * dx2 + dy2 * dy2 + dz2 * dz2;
                final double len2 = Math.sqrt(lensquared2);
                if (len2 > nomrange) {
                    continue;
                }
                nommee.func_70097_a(DamageSource.field_76380_i, 3.0f);
            }
        }
        if (age % 10 == 0) {
            final int bx = (int)Math.floor(this.field_70165_t);
            final int by = (int)Math.floor(this.field_70163_u);
            final int bz = (int)Math.floor(this.field_70161_v);
            for (int blockrange = (int)Math.round(nomrange), y = -blockrange; y <= blockrange; ++y) {
                for (int z = -blockrange; z <= blockrange; ++z) {
                    for (int x = -blockrange; x <= blockrange; ++x) {
                        final int lx = bx + x;
                        final int ly = by + y;
                        final int lz = bz + z;
                        if (ly >= 0) {
                            if (ly <= 255) {
                                final double dist = Math.sqrt(x * x + y * y + z * z);
                                if (dist <= nomrange && !this.field_70170_p.func_147437_c(lx, ly, lz)) {
                                    final Block b = this.field_70170_p.func_147439_a(lx, ly, lz);
                                    final int meta = this.field_70170_p.func_72805_g(lx, ly, lz);
                                    final float resist = b.getExplosionResistance((Entity)this, this.field_70170_p, lx, ly, lz, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                                    if (resist <= 10.0) {
                                        b.func_149690_a(this.field_70170_p, lx, ly, lz, meta, 0.9f, 0);
                                        this.field_70170_p.func_147468_f(lx, ly, lz);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void setAge(final int age) {
        this.field_70180_af.func_75692_b(12, (Object)age);
    }
    
    public int getAge() {
        return this.field_70180_af.func_75679_c(12);
    }
    
    protected void func_70037_a(final NBTTagCompound tag) {
        this.setAge(tag.func_74762_e("age"));
    }
    
    protected void func_70014_b(final NBTTagCompound tag) {
        tag.func_74768_a("age", this.getAge());
    }
    
    public static double getVoidScale(final double age) {
        final double life = age / 186.0;
        double curve;
        if (life < EntityGapingVoid.collapse) {
            curve = 0.005 + ease(1.0 - (EntityGapingVoid.collapse - life) / EntityGapingVoid.collapse) * 0.995;
        }
        else {
            curve = ease(1.0 - (life - EntityGapingVoid.collapse) / (1.0 - EntityGapingVoid.collapse));
        }
        return 10.0 * curve;
    }
    
    private static double ease(final double in) {
        final double t = in - 1.0;
        return Math.sqrt(1.0 - t * t);
    }
    
    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }
    
    public boolean func_70067_L() {
        return false;
    }
    
    static {
        EntityGapingVoid.randy = new Random();
        EntityGapingVoid.collapse = 0.95;
        EntityGapingVoid.suckrange = 20.0;
        sucklector = (IEntitySelector)new IEntitySelector() {
            public boolean func_82704_a(final Entity ent) {
                if (ent instanceof EntityPlayer) {
                    final EntityPlayer p = (EntityPlayer)ent;
                    if (p.field_71075_bZ.field_75098_d && p.field_71075_bZ.field_75100_b) {
                        return false;
                    }
                }
                return true;
            }
        };
        nomlector = (IEntitySelector)new IEntitySelector() {
            public boolean func_82704_a(final Entity ent) {
                if (!(ent instanceof EntityLivingBase)) {
                    return false;
                }
                if (ent instanceof EntityPlayer) {
                    final EntityPlayer p = (EntityPlayer)ent;
                    if (p.field_71075_bZ.field_75098_d) {
                        return false;
                    }
                }
                else if (ent instanceof EntityImmortalItem) {
                    return false;
                }
                return true;
            }
        };
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.Entity;
import java.util.Iterator;
import java.util.List;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import fox.spiteful.avaritia.Lumberjack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.entity.EntityLivingBase;
import fox.spiteful.avaritia.tile.TileLudicrous;

public class TileInfinitato extends TileLudicrous
{
    private static final String TAG_NAME = "name";
    public int jumpTicks;
    public String name;
    public int nextDoIt;
    
    public TileInfinitato() {
        this.jumpTicks = 0;
        this.name = "";
        this.nextDoIt = 0;
    }
    
    public void interact() {
        this.jump();
        if (this.name.equalsIgnoreCase("shia labeouf") && !this.field_145850_b.field_72995_K && this.nextDoIt == 0) {
            this.nextDoIt = 40;
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:doit", 2.5f, 0.7f);
        }
        final double radius = 10.5;
        final int time = 3600;
        final List<EntityLivingBase> inspired = (List<EntityLivingBase>)this.field_145850_b.func_72872_a((Class)EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_145851_c - radius, this.field_145848_d - radius, this.field_145849_e - radius, this.field_145851_c + radius, this.field_145848_d + radius, this.field_145849_e + radius));
        for (final EntityLivingBase ent : inspired) {
            Lumberjack.info(ent);
            final double dx = ent.field_70165_t - this.field_145851_c;
            final double dy = ent.field_70163_u - this.field_145848_d;
            final double dz = ent.field_70161_v - this.field_145849_e;
            final double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (dist <= radius) {
                ent.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, time, 1));
                ent.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, time, 0));
                ent.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, time, 0));
                ent.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, time, 1));
                ent.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, time, 1));
                ent.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, time, 1));
                ent.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, time, 0));
                ent.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, time, 0));
                ent.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, time, 0));
                ent.func_70690_d(new PotionEffect(Potion.field_76444_x.field_76415_H, time, 4));
                ent.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, time, 4));
            }
        }
    }
    
    public void jump() {
        if (this.jumpTicks == 0) {
            this.jumpTicks = 40;
        }
    }
    
    public void func_145845_h() {
        if (this.jumpTicks > 0) {
            --this.jumpTicks;
            if (this.jumpTicks == 20 || this.jumpTicks == 0) {
                this.field_145850_b.func_72876_a((Entity)null, this.field_145851_c + 0.5, (double)this.field_145848_d, this.field_145849_e + 0.5, 0.0f, true);
            }
        }
        if (this.nextDoIt > 0) {
            --this.nextDoIt;
        }
    }
    
    @Override
    public void writeCustomNBT(final NBTTagCompound cmp) {
        cmp.func_74778_a("name", this.name);
    }
    
    @Override
    public void readCustomNBT(final NBTTagCompound cmp) {
        this.name = cmp.func_74779_i("name");
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityItem;

public class EntityImmortalItem extends EntityItem
{
    public EntityImmortalItem(final World world, final Entity original, final ItemStack stack) {
        this(world, original.field_70165_t, original.field_70163_u, original.field_70161_v, stack);
        this.field_145804_b = 20;
        this.field_70159_w = original.field_70159_w;
        this.field_70181_x = original.field_70181_x;
        this.field_70179_y = original.field_70179_y;
        this.func_92058_a(stack);
    }
    
    public EntityImmortalItem(final World world, final double x, final double y, final double z, final ItemStack stack) {
        super(world, x, y, z);
        this.func_92058_a(stack);
    }
    
    public EntityImmortalItem(final World world, final double x, final double y, final double z) {
        super(world, x, y, z);
        this.field_70178_ae = true;
    }
    
    public EntityImmortalItem(final World world) {
        super(world);
        this.field_70178_ae = true;
    }
    
    protected void func_70081_e(final int damage) {
    }
    
    public boolean func_70097_a(final DamageSource source, final float damage) {
        return source.func_76355_l().equals("outOfWorld");
    }
    
    public void func_70071_h_() {
        final ItemStack stack = this.func_70096_w().func_82710_f(10);
        if (stack != null && stack.func_77973_b() != null && stack.func_77973_b().onEntityItemUpdate((EntityItem)this)) {
            return;
        }
        if (this.func_92059_d() == null) {
            this.func_70106_y();
        }
        else {
            super.func_70071_h_();
            if (this.field_145804_b > 0) {
                --this.field_145804_b;
            }
            this.field_70169_q = this.field_70165_t;
            this.field_70167_r = this.field_70163_u;
            this.field_70166_s = this.field_70161_v;
            this.field_70181_x -= 0.03999999910593033;
            this.field_70145_X = this.func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0, this.field_70161_v);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            final boolean flag = (int)this.field_70169_q != (int)this.field_70165_t || (int)this.field_70167_r != (int)this.field_70163_u || (int)this.field_70166_s != (int)this.field_70161_v;
            if (flag || this.field_70173_aa % 25 == 0) {
                if (this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)).func_149688_o() == Material.field_151587_i) {
                    this.field_70181_x = 0.20000000298023224;
                    this.field_70159_w = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f;
                    this.field_70179_y = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f;
                    this.func_85030_a("random.fizz", 0.4f, 2.0f + this.field_70146_Z.nextFloat() * 0.4f);
                }
                if (!this.field_70170_p.field_72995_K) {
                    this.searchForOtherItemsNearby2();
                }
            }
            float f = 0.98f;
            if (this.field_70122_E) {
                f = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v)).field_149765_K * 0.98f;
            }
            this.field_70159_w *= f;
            this.field_70181_x *= 0.9800000190734863;
            this.field_70179_y *= f;
            if (this.field_70122_E) {
                this.field_70181_x *= -0.5;
            }
            ++this.field_70292_b;
            final ItemStack item = this.func_70096_w().func_82710_f(10);
            if (!this.field_70170_p.field_72995_K && this.field_70292_b >= this.lifespan) {
                if (item == null) {
                    this.func_70106_y();
                }
            }
            if (item != null && item.field_77994_a <= 0) {
                this.func_70106_y();
            }
        }
    }
    
    private void searchForOtherItemsNearby2() {
        for (final EntityItem entityitem : this.field_70170_p.func_72872_a((Class)EntityItem.class, this.field_70121_D.func_72314_b(0.5, 0.0, 0.5))) {
            this.func_70289_a(entityitem);
        }
    }
}

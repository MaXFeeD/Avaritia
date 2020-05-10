// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;

public class TileEntityNeutron extends TileLudicrous implements IInventory
{
    private ItemStack neutrons;
    private int facing;
    private int progress;
    
    public TileEntityNeutron() {
        this.facing = 2;
    }
    
    public void func_145845_h() {
        if (++this.progress >= 7111) {
            if (this.neutrons == null) {
                this.neutrons = new ItemStack(LudicrousItems.resource, 1, 2);
            }
            else if (this.neutrons.func_77973_b() == LudicrousItems.resource && this.neutrons.func_77960_j() == 2 && this.neutrons.field_77994_a < 64) {
                final ItemStack neutrons = this.neutrons;
                ++neutrons.field_77994_a;
            }
            this.progress = 0;
            this.func_70296_d();
        }
    }
    
    public int getFacing() {
        return this.facing;
    }
    
    public void setFacing(final int dir) {
        this.facing = dir;
    }
    
    @Override
    public void readCustomNBT(final NBTTagCompound tag) {
        this.neutrons = ItemStack.func_77949_a(tag.func_74775_l("Neutrons"));
        this.progress = tag.func_74762_e("Progress");
        this.facing = tag.func_74765_d("Facing");
    }
    
    @Override
    public void writeCustomNBT(final NBTTagCompound tag) {
        tag.func_74768_a("Progress", this.progress);
        tag.func_74777_a("Facing", (short)this.facing);
        if (this.neutrons != null) {
            final NBTTagCompound produce = new NBTTagCompound();
            this.neutrons.func_77955_b(produce);
            tag.func_74782_a("Neutrons", (NBTBase)produce);
        }
        else {
            tag.func_82580_o("Neutrons");
        }
    }
    
    public int func_70302_i_() {
        return 1;
    }
    
    public ItemStack func_70301_a(final int slot) {
        return this.neutrons;
    }
    
    public ItemStack func_70298_a(final int slot, final int decrement) {
        if (this.neutrons == null) {
            return null;
        }
        if (decrement < this.neutrons.field_77994_a) {
            final ItemStack take = this.neutrons.func_77979_a(decrement);
            if (this.neutrons.field_77994_a <= 0) {
                this.neutrons = null;
            }
            return take;
        }
        final ItemStack take = this.neutrons;
        this.neutrons = null;
        return take;
    }
    
    public void func_70295_k_() {
    }
    
    public void func_70305_f() {
    }
    
    public boolean func_70300_a(final EntityPlayer player) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e(this.field_145851_c + 0.5, this.field_145848_d + 0.5, this.field_145849_e + 0.5) <= 64.0;
    }
    
    public boolean func_94041_b(final int slot, final ItemStack stack) {
        return false;
    }
    
    public int func_70297_j_() {
        return 64;
    }
    
    public void func_70299_a(final int slot, final ItemStack stack) {
        this.neutrons = stack;
    }
    
    public ItemStack func_70304_b(final int slot) {
        return null;
    }
    
    public String func_145825_b() {
        return "container.neutron";
    }
    
    public boolean func_145818_k_() {
        return false;
    }
}

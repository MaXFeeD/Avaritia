// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.IInventory;

public class TileEntityDireCrafting extends TileLudicrous implements IInventory, ISidedInventory
{
    private ItemStack result;
    private ItemStack[] matrix;
    
    public TileEntityDireCrafting() {
        this.matrix = new ItemStack[81];
    }
    
    public boolean canUpdate() {
        return false;
    }
    
    @Override
    public void readCustomNBT(final NBTTagCompound tag) {
        this.result = ItemStack.func_77949_a(tag.func_74775_l("Result"));
        for (int x = 0; x < this.matrix.length; ++x) {
            this.matrix[x] = ItemStack.func_77949_a(tag.func_74775_l("Craft" + x));
        }
    }
    
    @Override
    public void writeCustomNBT(final NBTTagCompound tag) {
        if (this.result != null) {
            final NBTTagCompound produce = new NBTTagCompound();
            this.result.func_77955_b(produce);
            tag.func_74782_a("Result", (NBTBase)produce);
        }
        else {
            tag.func_82580_o("Result");
        }
        for (int x = 0; x < this.matrix.length; ++x) {
            if (this.matrix[x] != null) {
                final NBTTagCompound craft = new NBTTagCompound();
                this.matrix[x].func_77955_b(craft);
                tag.func_74782_a("Craft" + x, (NBTBase)craft);
            }
            else {
                tag.func_82580_o("Craft" + x);
            }
        }
    }
    
    public int func_70302_i_() {
        return 82;
    }
    
    public ItemStack func_70301_a(final int slot) {
        if (slot == 0) {
            return this.result;
        }
        if (slot <= this.matrix.length) {
            return this.matrix[slot - 1];
        }
        return null;
    }
    
    public ItemStack func_70298_a(final int slot, final int decrement) {
        if (slot == 0) {
            if (this.result == null) {
                return null;
            }
            for (int x = 1; x <= this.matrix.length; ++x) {
                this.func_70298_a(x, 1);
            }
            if (this.result.field_77994_a <= decrement) {
                final ItemStack craft = this.result;
                this.result = null;
                return craft;
            }
            final ItemStack split = this.result.func_77979_a(decrement);
            if (this.result.field_77994_a <= 0) {
                this.result = null;
            }
            return split;
        }
        else {
            if (slot > this.matrix.length || this.matrix[slot - 1] == null) {
                return null;
            }
            if (this.matrix[slot - 1].field_77994_a <= decrement) {
                final ItemStack ingredient = this.matrix[slot - 1];
                this.matrix[slot - 1] = null;
                return ingredient;
            }
            final ItemStack split = this.matrix[slot - 1].func_77979_a(decrement);
            if (this.matrix[slot - 1].field_77994_a <= 0) {
                this.matrix[slot - 1] = null;
            }
            return split;
        }
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
        if (slot == 0) {
            this.result = stack;
        }
        else if (slot <= this.matrix.length) {
            this.matrix[slot - 1] = stack;
        }
    }
    
    public ItemStack func_70304_b(final int slot) {
        return null;
    }
    
    public String func_145825_b() {
        return "container.dire";
    }
    
    public boolean func_145818_k_() {
        return false;
    }
    
    public int[] func_94128_d(final int side) {
        return new int[0];
    }
    
    public boolean func_102007_a(final int slot, final ItemStack item, final int side) {
        return false;
    }
    
    public boolean func_102008_b(final int slot, final ItemStack item, final int side) {
        return false;
    }
}

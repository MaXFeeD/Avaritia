// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Container;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import net.minecraft.inventory.InventoryCrafting;

public class InventoryDireCrafting extends InventoryCrafting
{
    private TileEntityDireCrafting craft;
    private Container container;
    
    public InventoryDireCrafting(final Container cont, final TileEntityDireCrafting table) {
        super(cont, 9, 9);
        this.craft = table;
        this.container = cont;
    }
    
    public ItemStack func_70301_a(final int slot) {
        return (slot >= this.func_70302_i_()) ? null : this.craft.func_70301_a(slot + 1);
    }
    
    public ItemStack func_70463_b(final int row, final int column) {
        if (row >= 0 && row < 9) {
            final int x = row + column * 9;
            return this.func_70301_a(x);
        }
        return null;
    }
    
    public ItemStack func_70304_b(final int par1) {
        return null;
    }
    
    public ItemStack func_70298_a(final int slot, final int decrement) {
        ItemStack stack = this.craft.func_70301_a(slot + 1);
        if (stack == null) {
            return null;
        }
        if (stack.field_77994_a <= decrement) {
            final ItemStack itemstack = stack.func_77946_l();
            stack = null;
            this.craft.func_70299_a(slot + 1, null);
            this.container.func_75130_a((IInventory)this);
            return itemstack;
        }
        final ItemStack itemstack = stack.func_77979_a(decrement);
        if (stack.field_77994_a == 0) {
            stack = null;
        }
        this.container.func_75130_a((IInventory)this);
        return itemstack;
    }
    
    public void func_70299_a(final int slot, final ItemStack itemstack) {
        this.craft.func_70299_a(slot + 1, itemstack);
        this.container.func_75130_a((IInventory)this);
    }
}

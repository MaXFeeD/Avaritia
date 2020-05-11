// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile.inventory;

import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import net.minecraft.inventory.InventoryCraftResult;

public class InventoryDireCraftResult extends InventoryCraftResult
{
    private TileEntityDireCrafting craft;
    
    public InventoryDireCraftResult(final TileEntityDireCrafting table) {
        this.craft = table;
    }
    
    public ItemStack func_70301_a(final int par1) {
        return this.craft.func_70301_a(0);
    }
    
    public ItemStack func_70298_a(final int par1, final int par2) {
        final ItemStack stack = this.craft.func_70301_a(0);
        if (stack != null) {
            final ItemStack itemstack = stack;
            this.craft.func_70299_a(0, null);
            return itemstack;
        }
        return null;
    }
    
    public ItemStack func_70304_b(final int par1) {
        return null;
    }
    
    public void func_70299_a(final int par1, final ItemStack par2ItemStack) {
        this.craft.func_70299_a(0, par2ItemStack);
    }
}

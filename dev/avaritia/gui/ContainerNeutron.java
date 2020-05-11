// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import net.minecraft.inventory.Container;

public class ContainerNeutron extends Container
{
    private TileEntityNeutron tileNeutron;
    
    public ContainerNeutron(final InventoryPlayer player, final TileEntityNeutron machine) {
        this.tileNeutron = machine;
        this.func_75146_a((Slot)new SlotFurnace(player.field_70458_d, (IInventory)machine, 2, 80, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)player, i, 8 + i * 18, 142));
        }
    }
    
    public boolean func_75145_c(final EntityPlayer player) {
        return this.tileNeutron.func_70300_a(player);
    }
    
    public ItemStack func_82846_b(final EntityPlayer player, final int slotNumber) {
        ItemStack itemstack = null;
        final Slot slot = this.field_75151_b.get(slotNumber);
        if (slot != null && slot.func_75216_d()) {
            final ItemStack itemstack2 = slot.func_75211_c();
            itemstack = itemstack2.func_77946_l();
            if (slotNumber == 0) {
                if (!this.func_75135_a(itemstack2, 1, 37, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack2, itemstack);
            }
            else if (slotNumber >= 1 && slotNumber < 28) {
                if (!this.func_75135_a(itemstack2, 28, 37, false)) {
                    return null;
                }
            }
            else if (slotNumber >= 28 && slotNumber < 37 && !this.func_75135_a(itemstack2, 1, 28, false)) {
                return null;
            }
            if (itemstack2.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            }
            else {
                slot.func_75218_e();
            }
            if (itemstack2.field_77994_a == itemstack.field_77994_a) {
                return null;
            }
            slot.func_82870_a(player, itemstack2);
        }
        return itemstack;
    }
}

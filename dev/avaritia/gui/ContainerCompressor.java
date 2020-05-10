// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import fox.spiteful.avaritia.crafting.CompressorManager;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.entity.player.InventoryPlayer;
import fox.spiteful.avaritia.tile.TileEntityCompressor;
import net.minecraft.inventory.Container;

public class ContainerCompressor extends Container
{
    private TileEntityCompressor compressor;
    
    public ContainerCompressor(final InventoryPlayer player, final TileEntityCompressor machine) {
        this.compressor = machine;
        this.func_75146_a(new Slot((IInventory)this.compressor, 0, 56, 27));
        this.func_75146_a((Slot)new SlotFurnace(player.field_70458_d, (IInventory)machine, 1, 116, 27));
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
        return this.compressor.func_70300_a(player);
    }
    
    public ItemStack func_82846_b(final EntityPlayer player, final int slotNumber) {
        ItemStack itemstack = null;
        final Slot slot = this.field_75151_b.get(slotNumber);
        if (slot != null && slot.func_75216_d()) {
            final ItemStack itemstack2 = slot.func_75211_c();
            itemstack = itemstack2.func_77946_l();
            if (slotNumber == 1) {
                if (!this.func_75135_a(itemstack2, 2, 38, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack2, itemstack);
            }
            else if (slotNumber != 0) {
                if (CompressorManager.getOutput(itemstack2) != null) {
                    if (!this.func_75135_a(itemstack2, 0, 1, false)) {
                        return null;
                    }
                }
                else if (slotNumber >= 3 && slotNumber < 30) {
                    if (!this.func_75135_a(itemstack2, 29, 38, false)) {
                        return null;
                    }
                }
                else if (slotNumber >= 29 && slotNumber < 38 && !this.func_75135_a(itemstack2, 2, 29, false)) {
                    return null;
                }
            }
            else if (!this.func_75135_a(itemstack2, 2, 38, false)) {
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

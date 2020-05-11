// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraft.entity.player.EntityPlayer;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import fox.spiteful.avaritia.tile.TileEntityAutoDireCrafting;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Container;

public class ContainerAutoExtremeCrafting extends Container
{
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    
    public ContainerAutoExtremeCrafting(final InventoryPlayer player, final World world, final int x, final int y, final int z, final TileEntityAutoDireCrafting table) {
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.func_75146_a((Slot)new SlotCrafting(player.field_70458_d, (IInventory)this.craftMatrix, this.craftResult, 0, 210, 80));
        for (int wy = 0; wy < 9; ++wy) {
            for (int ex = 0; ex < 9; ++ex) {
                this.func_75146_a(new Slot((IInventory)this.craftMatrix, ex + wy * 9, 12 + ex * 18, 8 + wy * 18));
            }
        }
        for (int wy = 0; wy < 3; ++wy) {
            for (int ex = 0; ex < 9; ++ex) {
                this.func_75146_a(new Slot((IInventory)player, ex + wy * 9 + 9, 39 + ex * 18, 174 + wy * 18));
            }
        }
        for (int ex = 0; ex < 9; ++ex) {
            this.func_75146_a(new Slot((IInventory)player, ex, 39 + ex * 18, 232));
        }
        this.func_75130_a((IInventory)this.craftMatrix);
    }
    
    public void func_75130_a(final IInventory matrix) {
        this.craftResult.func_70299_a(0, ExtremeCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }
    
    public void func_75134_a(final EntityPlayer player) {
        super.func_75134_a(player);
    }
    
    public boolean func_75145_c(final EntityPlayer player) {
        return this.worldObj.func_147439_a(this.posX, this.posY, this.posZ) == LudicrousBlocks.dire_crafting && player.func_70092_e(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5) <= 64.0;
    }
    
    public ItemStack func_82846_b(final EntityPlayer player, final int slotNumber) {
        ItemStack itemstack = null;
        final Slot slot = this.field_75151_b.get(slotNumber);
        if (slot != null && slot.func_75216_d()) {
            final ItemStack itemstack2 = slot.func_75211_c();
            itemstack = itemstack2.func_77946_l();
            if (slotNumber == 0) {
                if (!this.func_75135_a(itemstack2, 82, 118, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack2, itemstack);
            }
            else if (slotNumber >= 82 && slotNumber < 109) {
                if (!this.func_75135_a(itemstack2, 109, 118, false)) {
                    return null;
                }
            }
            else if (slotNumber >= 109 && slotNumber < 118) {
                if (!this.func_75135_a(itemstack2, 82, 109, false)) {
                    return null;
                }
            }
            else if (!this.func_75135_a(itemstack2, 82, 118, false)) {
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

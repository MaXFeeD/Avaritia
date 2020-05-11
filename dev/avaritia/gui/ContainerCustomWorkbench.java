// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import net.minecraft.inventory.ContainerWorkbench;

public class ContainerCustomWorkbench extends ContainerWorkbench
{
    int field_75164_h;
    int field_75165_i;
    int field_75163_j;
    World field_75161_g;
    
    public ContainerCustomWorkbench(final InventoryPlayer inv, final World world, final int x, final int y, final int z) {
        super(inv, world, x, y, z);
        this.field_75164_h = x;
        this.field_75165_i = y;
        this.field_75163_j = z;
        this.field_75161_g = world;
    }
    
    public boolean func_75145_c(final EntityPlayer player) {
        return !this.field_75161_g.func_147437_c(this.field_75164_h, this.field_75165_i, this.field_75163_j) && player.func_70092_e(this.field_75164_h + 0.5, this.field_75165_i + 0.5, this.field_75163_j + 0.5) <= 64.0;
    }
}

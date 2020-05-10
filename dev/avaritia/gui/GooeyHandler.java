// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import fox.spiteful.avaritia.tile.TileEntityAutoDireCrafting;
import fox.spiteful.avaritia.tile.TileEntityCompressor;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.IGuiHandler;

public class GooeyHandler implements IGuiHandler
{
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        if (ID == 0) {
            return new GuiCrafting(player.field_71071_by, world, x, y, z);
        }
        if (ID == 1) {
            return new GUIExtremeCrafting(player.field_71071_by, world, x, y, z, (TileEntityDireCrafting)world.func_147438_o(x, y, z));
        }
        if (ID == 2) {
            return new GUINeutron(player.field_71071_by, (TileEntityNeutron)world.func_147438_o(x, y, z));
        }
        if (ID == 3) {
            return new GUICompressor(player.field_71071_by, (TileEntityCompressor)world.func_147438_o(x, y, z));
        }
        if (ID == 4) {
            return new GUIAutoExtremeCrafting(player.field_71071_by, world, x, y, z, (TileEntityAutoDireCrafting)world.func_147438_o(x, y, z));
        }
        return null;
    }
    
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        if (ID == 0) {
            return new ContainerCustomWorkbench(player.field_71071_by, world, x, y, z);
        }
        if (ID == 1) {
            return new ContainerExtremeCrafting(player.field_71071_by, world, x, y, z, (TileEntityDireCrafting)world.func_147438_o(x, y, z));
        }
        if (ID == 2) {
            return new ContainerNeutron(player.field_71071_by, (TileEntityNeutron)world.func_147438_o(x, y, z));
        }
        if (ID == 3) {
            return new ContainerCompressor(player.field_71071_by, (TileEntityCompressor)world.func_147438_o(x, y, z));
        }
        if (ID == 4) {
            return new ContainerAutoExtremeCrafting(player.field_71071_by, world, x, y, z, (TileEntityAutoDireCrafting)world.func_147438_o(x, y, z));
        }
        return null;
    }
}

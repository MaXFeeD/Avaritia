// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.inventory.Container;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import net.minecraft.world.World;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GUIExtremeCrafting extends GuiContainer
{
    private static final ResourceLocation tex;
    
    public GUIExtremeCrafting(final InventoryPlayer par1InventoryPlayer, final World par2World, final int x, final int y, final int z, final TileEntityDireCrafting table) {
        super((Container)new ContainerExtremeCrafting(par1InventoryPlayer, par2World, x, y, z, table));
        this.field_147000_g = 256;
        this.field_146999_f = 238;
    }
    
    protected void func_146979_b(final int i, final int j) {
    }
    
    protected void func_146976_a(final float par1, final int par2, final int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(GUIExtremeCrafting.tex);
        final int foo = (this.field_146294_l - this.field_146999_f) / 2;
        final int bar = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(foo, bar, 0, 0, this.field_147000_g, this.field_147000_g);
    }
    
    static {
        tex = new ResourceLocation("avaritia:textures/gui/dire_crafting_gui.png");
    }
}

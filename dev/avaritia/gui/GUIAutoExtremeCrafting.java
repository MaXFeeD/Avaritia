// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import net.minecraft.inventory.Container;
import fox.spiteful.avaritia.tile.TileEntityAutoDireCrafting;
import net.minecraft.world.World;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GUIAutoExtremeCrafting extends GuiContainer
{
    private static final ResourceLocation tex;
    
    public GUIAutoExtremeCrafting(final InventoryPlayer par1InventoryPlayer, final World par2World, final int x, final int y, final int z, final TileEntityAutoDireCrafting table) {
        super((Container)new ContainerAutoExtremeCrafting(par1InventoryPlayer, par2World, x, y, z, table));
        this.field_147000_g = 256;
        this.field_146999_f = 238;
    }
    
    protected void func_146979_b(final int i, final int j) {
    }
    
    protected void func_146976_a(final float par1, final int par2, final int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(GUIAutoExtremeCrafting.tex);
        final Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)this.field_147003_i, (double)this.field_147009_r, 0.0, 0.0, 0.0);
        tessellator.func_78374_a((double)this.field_147003_i, (double)(this.field_147009_r + this.field_146999_f), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(this.field_147003_i + this.field_146999_f), (double)(this.field_147009_r + this.field_146999_f), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(this.field_147003_i + this.field_146999_f), (double)this.field_147009_r, 0.0, 1.0, 0.0);
        tessellator.func_78381_a();
    }
    
    static {
        tex = new ResourceLocation("avaritia:textures/gui/dire_autocrafting_gui.png");
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.util.StatCollector;
import net.minecraft.inventory.Container;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;

@SideOnly(Side.CLIENT)
public class GUINeutron extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures;
    
    public GUINeutron(final InventoryPlayer player, final TileEntityNeutron machine) {
        super((Container)new ContainerNeutron(player, machine));
    }
    
    protected void func_146979_b(final int p_146979_1_, final int p_146979_2_) {
        final String s = StatCollector.func_74838_a("container.neutron_collector");
        this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }
    
    protected void func_146976_a(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(GUINeutron.furnaceGuiTextures);
        final int k = (this.field_146294_l - this.field_146999_f) / 2;
        final int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    }
    
    static {
        furnaceGuiTextures = new ResourceLocation("avaritia", "textures/gui/neutron_collector_gui.png");
    }
}

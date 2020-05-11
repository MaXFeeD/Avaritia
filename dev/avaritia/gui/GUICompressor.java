// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.util.StatCollector;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.InventoryPlayer;
import fox.spiteful.avaritia.tile.TileEntityCompressor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GUICompressor extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures;
    private TileEntityCompressor compressor;
    
    public GUICompressor(final InventoryPlayer player, final TileEntityCompressor machine) {
        super((Container)new ContainerCompressor(player, machine));
        this.compressor = machine;
    }
    
    protected void func_146979_b(final int p_146979_1_, final int p_146979_2_) {
        String s = StatCollector.func_74838_a("container.neutronium_compressor");
        this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
        if (this.compressor.getProgress() > 0) {
            s = this.compressor.getProgress() + " / " + this.compressor.getTarget();
            this.field_146289_q.func_78276_b(s, 41, 49, 4210752);
            this.field_146289_q.func_78276_b(this.compressor.getIngredient(), 41, 60, 4210752);
        }
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }
    
    protected void func_146976_a(final float p_146976_1_, final int p_146976_2_, final int p_146976_3_) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(GUICompressor.furnaceGuiTextures);
        final int k = (this.field_146294_l - this.field_146999_f) / 2;
        final int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.compressor.getProgress() > 0) {
            final int i1 = this.compressor.getProgress() * 24 / this.compressor.getTarget();
            this.func_73729_b(k + 79, l + 26, 176, 14, i1 + 1, 16);
        }
    }
    
    static {
        furnaceGuiTextures = new ResourceLocation("avaritia", "textures/gui/compressor.png");
    }
}

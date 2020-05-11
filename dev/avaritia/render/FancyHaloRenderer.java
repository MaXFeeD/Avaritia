// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.util.IIcon;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.Random;
import net.minecraftforge.client.IItemRenderer;

public class FancyHaloRenderer implements IItemRenderer
{
    public Random rand;
    
    public FancyHaloRenderer() {
        this.rand = new Random();
    }
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        final Item itype = item.func_77973_b();
        if (itype instanceof IHaloRenderItem) {
            final IHaloRenderItem ihri = (IHaloRenderItem)itype;
            if (!ihri.drawHalo(item) && !ihri.drawPulseEffect(item)) {
                return false;
            }
        }
        switch (type) {
            case INVENTORY: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return false;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        boolean renderHalo = false;
        boolean renderPulse = false;
        int spread = 0;
        IIcon halo = null;
        int haloColour = 0;
        final Item itype = item.func_77973_b();
        if (itype instanceof IHaloRenderItem) {
            final IHaloRenderItem ihri = (IHaloRenderItem)itype;
            spread = ihri.getHaloSize(item);
            halo = ihri.getHaloTexture(item);
            haloColour = ihri.getHaloColour(item);
            renderHalo = ihri.drawHalo(item);
            renderPulse = ihri.drawPulseEffect(item);
        }
        final RenderItem r = RenderItem.getInstance();
        final Minecraft mc = Minecraft.func_71410_x();
        final Tessellator t = Tessellator.field_78398_a;
        switch (type) {
            case INVENTORY: {
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                RenderHelper.func_74520_c();
                GL11.glDisable(3008);
                GL11.glDisable(2929);
                if (renderHalo) {
                    final float ca = (haloColour >> 24 & 0xFF) / 255.0f;
                    final float cr = (haloColour >> 16 & 0xFF) / 255.0f;
                    final float cg = (haloColour >> 8 & 0xFF) / 255.0f;
                    final float cb = (haloColour & 0xFF) / 255.0f;
                    GL11.glColor4f(cr, cg, cb, ca);
                    t.func_78382_b();
                    t.func_78374_a((double)(0 - spread), (double)(0 - spread), 0.0, (double)halo.func_94209_e(), (double)halo.func_94206_g());
                    t.func_78374_a((double)(0 - spread), (double)(16 + spread), 0.0, (double)halo.func_94209_e(), (double)halo.func_94210_h());
                    t.func_78374_a((double)(16 + spread), (double)(16 + spread), 0.0, (double)halo.func_94212_f(), (double)halo.func_94210_h());
                    t.func_78374_a((double)(16 + spread), (double)(0 - spread), 0.0, (double)halo.func_94212_f(), (double)halo.func_94206_g());
                    t.func_78381_a();
                }
                if (renderPulse) {
                    GL11.glPushMatrix();
                    final double xs = this.rand.nextGaussian() * 0.15 + 0.95;
                    final double ox = (1.0 - xs) / 2.0;
                    GL11.glEnable(3042);
                    GL11.glTranslated(ox * 16.0, ox * 16.0, 1.0);
                    GL11.glScaled(xs, xs, 1.0);
                    final IIcon icon = item.func_77973_b().getIcon(item, 0);
                    t.func_78382_b();
                    t.func_78369_a(1.0f, 1.0f, 1.0f, 0.6f);
                    t.func_78374_a(0.0 - ox, 0.0 - ox, 0.0, (double)icon.func_94209_e(), (double)icon.func_94206_g());
                    t.func_78374_a(0.0 - ox, 16.0 + ox, 0.0, (double)icon.func_94209_e(), (double)icon.func_94210_h());
                    t.func_78374_a(16.0 + ox, 16.0 + ox, 0.0, (double)icon.func_94212_f(), (double)icon.func_94210_h());
                    t.func_78374_a(16.0 + ox, 0.0 - ox, 0.0, (double)icon.func_94212_f(), (double)icon.func_94206_g());
                    t.func_78381_a();
                    GL11.glPopMatrix();
                }
                r.renderItemIntoGUI(mc.field_71466_p, mc.func_110434_K(), item, 0, 0, true);
                GL11.glEnable(3008);
                GL11.glEnable(32826);
                GL11.glEnable(2929);
                r.field_77024_a = true;
                GL11.glDisable(3042);
                GL11.glPopMatrix();
                break;
            }
        }
    }
}

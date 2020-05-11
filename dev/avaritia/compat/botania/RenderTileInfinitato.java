// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import fox.spiteful.avaritia.compat.tails.InfiniteFoxes;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileInfinitato extends TileEntitySpecialRenderer
{
    public static boolean drawHalo;
    private static final ResourceLocation texture;
    public static final ResourceLocation halo;
    private static final ModelInfinitato model;
    
    public void func_147500_a(final TileEntity var1, final double x, final double y, final double z, final float partialTicks) {
        final TileInfinitato potato = (TileInfinitato)var1;
        final Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glEnable(32826);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslated(x, y, z);
        final Minecraft mc = Minecraft.func_71410_x();
        GL11.glTranslatef(0.5f, 1.5f, 0.5f);
        GL11.glScalef(1.0f, -1.0f, -1.0f);
        final int meta = (potato.func_145831_w() == null) ? 3 : potato.func_145832_p();
        if (RenderTileInfinitato.drawHalo) {
            mc.field_71446_o.func_110577_a(RenderTileInfinitato.halo);
            GL11.glPushMatrix();
            double xdiff = potato.field_145851_c + 0.5 - RenderManager.field_78727_a.field_78730_l;
            double ydiff = potato.field_145848_d + 0.4 - RenderManager.field_78727_a.field_78731_m;
            double zdiff = potato.field_145849_e + 0.5 - RenderManager.field_78727_a.field_78728_n;
            final double len = Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);
            xdiff /= len;
            ydiff /= len;
            zdiff /= len;
            GL11.glTranslated(-xdiff, ydiff, zdiff);
            GL11.glScalef(1.0f, -1.0f, -1.0f);
            GL11.glTranslatef(0.0f, -1.15f, 0.0f);
            GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0f, 0.0f, 0.0f);
            final float f = 1.6f;
            final float f2 = 0.016666668f * f;
            GL11.glScalef(f2, f2, f2);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            tessellator.func_78382_b();
            final int i = 60;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 1.0f);
            tessellator.func_78374_a((double)(-i), (double)(-i), 0.0, 0.0, 0.0);
            tessellator.func_78374_a((double)(-i), (double)i, 0.0, 1.0, 0.0);
            tessellator.func_78374_a((double)i, (double)i, 0.0, 1.0, 1.0);
            tessellator.func_78374_a((double)i, (double)(-i), 0.0, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glDepthMask(true);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
        final float rotY = meta * 90.0f - 180.0f;
        GL11.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
        float jump = potato.jumpTicks * 0.5f;
        if (jump > 0.0f) {
            jump += partialTicks * 0.5f;
        }
        final float up = (float)(-Math.abs(Math.sin(jump / 10.0f * 3.141592653589793))) * 0.2f;
        final float rotZ = (float)Math.sin(jump / 10.0f * 3.141592653589793) * 2.0f;
        GL11.glTranslatef(0.0f, up, 0.0f);
        GL11.glRotatef(rotZ, 0.0f, 0.0f, 1.0f);
        mc.field_71446_o.func_110577_a(RenderTileInfinitato.texture);
        RenderTileInfinitato.model.render();
        GL11.glPushMatrix();
        final String name = potato.name.toLowerCase();
        mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
        final float scale = 0.25f;
        GL11.glTranslatef(0.0f, 1.0f, 0.0f);
        GL11.glScalef(scale, scale, scale);
        if (name.equals("armstrong")) {
            GL11.glScalef(1.75f, 1.75f, 1.25f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -0.55f, -0.8f);
            this.renderIcon(Tsundere.costumes.func_77617_a(0));
        }
        else if (name.startsWith("moo") && name.endsWith("oon")) {
            GL11.glScalef(2.5f, 2.5f, 1.25f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -0.6f, -0.8f);
            this.renderIcon(Tsundere.costumes.func_77617_a(1));
        }
        else if (name.equals("egbert")) {
            GL11.glScalef(1.25f, 1.25f, 1.25f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.5f, -1.4f, -0.8f);
            this.renderIcon(Tsundere.costumes.func_77617_a(2));
        }
        else if (name.equals("popetato")) {
            GL11.glScalef(1.75f, 1.75f, 1.25f);
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(-0.5f, 0.0f, -0.8f);
            this.renderIcon(Tsundere.costumes.func_77617_a(3));
        }
        else if (name.startsWith("foxtato")) {
            InfiniteFoxes.renderInfinitatoFluff(partialTicks);
        }
        GL11.glPopMatrix();
        GL11.glRotatef(-rotZ, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(-rotY, 0.0f, 1.0f, 0.0f);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glScalef(1.0f, -1.0f, -1.0f);
        final MovingObjectPosition pos = mc.field_71476_x;
        if (!potato.name.isEmpty() && pos != null && pos.field_72311_b == potato.field_145851_c && pos.field_72312_c == potato.field_145848_d && pos.field_72309_d == potato.field_145849_e) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, -0.4f, 0.0f);
            GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0f, 0.0f, 0.0f);
            final float f3 = 1.6f;
            final float f4 = 0.016666668f * f3;
            GL11.glScalef(-f4, -f4, f4);
            GL11.glDisable(2896);
            GL11.glTranslatef(0.0f, 0.0f / f4, 0.0f);
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glDisable(3553);
            tessellator.func_78382_b();
            final int j = mc.field_71466_p.func_78256_a(potato.name) / 2;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
            tessellator.func_78377_a((double)(-j - 1), -1.0, 0.0);
            tessellator.func_78377_a((double)(-j - 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(j + 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(j + 1), -1.0, 0.0);
            tessellator.func_78381_a();
            GL11.glEnable(3553);
            GL11.glDepthMask(true);
            mc.field_71466_p.func_78276_b(potato.name, -mc.field_71466_p.func_78256_a(potato.name) / 2, 0, 16777215);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glScalef(1.0f / -f4, 1.0f / -f4, 1.0f / f4);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
    
    public void renderIcon(final IIcon icon) {
        final float f = icon.func_94209_e();
        final float f2 = icon.func_94212_f();
        final float f3 = icon.func_94206_g();
        final float f4 = icon.func_94210_h();
        ItemRenderer.func_78439_a(Tessellator.field_78398_a, f2, f3, f, f4, icon.func_94211_a(), icon.func_94216_b(), 0.0625f);
    }
    
    static {
        RenderTileInfinitato.drawHalo = true;
        texture = new ResourceLocation("avaritia", "textures/blocks/infinitato.png");
        halo = new ResourceLocation("avaritia", "textures/items/halo128.png");
        model = new ModelInfinitato();
    }
}

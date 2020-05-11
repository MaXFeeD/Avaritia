// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderArrow;

public class RenderHeavenArrow extends RenderArrow
{
    private static final ResourceLocation tex;
    
    public void func_76986_a(final EntityArrow p_76986_1_, final double p_76986_2_, final double p_76986_4_, final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        final EntityRenderer er = Minecraft.func_71410_x().field_71460_t;
        er.func_78483_a(0.0);
        GL11.glDisable(2896);
        this.func_110777_b((Entity)p_76986_1_);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(p_76986_1_.field_70126_B + (p_76986_1_.field_70177_z - p_76986_1_.field_70126_B) * p_76986_9_ - 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_, 0.0f, 0.0f, 1.0f);
        final Tessellator tessellator = Tessellator.field_78398_a;
        final byte b0 = 0;
        final float f2 = 0.0f;
        final float f3 = 0.5f;
        final float f4 = (0 + b0 * 10) / 32.0f;
        final float f5 = (5 + b0 * 10) / 32.0f;
        final float f6 = 0.0f;
        final float f7 = 0.15625f;
        final float f8 = (5 + b0 * 10) / 32.0f;
        final float f9 = (10 + b0 * 10) / 32.0f;
        final float f10 = 0.05625f;
        GL11.glEnable(32826);
        final float f11 = p_76986_1_.field_70249_b - p_76986_9_;
        if (f11 > 0.0f) {
            final float f12 = -MathHelper.func_76126_a(f11 * 3.0f) * f11;
            GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
        }
        GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
        GL11.glNormal3f(f10, 0.0f, 0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        GL11.glNormal3f(-f10, 0.0f, 0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glNormal3f(0.0f, 0.0f, f10);
            tessellator.func_78382_b();
            tessellator.func_78374_a(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.func_78374_a(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.func_78374_a(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.func_78381_a();
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
        GL11.glEnable(2896);
        er.func_78463_b(0.0);
    }
    
    protected ResourceLocation func_110775_a(final EntityArrow p_110775_1_) {
        return RenderHeavenArrow.tex;
    }
    
    static {
        tex = new ResourceLocation("avaritia", "textures/entity/heavenarrow.png");
    }
}

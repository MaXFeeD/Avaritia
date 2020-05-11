// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.Minecraft;
import fox.spiteful.avaritia.entity.EntityGapingVoid;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.Render;

public class RenderGapingVoid extends Render
{
    private ResourceLocation fill;
    private ResourceLocation halo;
    private IModelCustom model;
    
    public RenderGapingVoid() {
        this.fill = new ResourceLocation("avaritia", "textures/entity/void.png");
        this.halo = new ResourceLocation("avaritia", "textures/entity/voidhalo.png");
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation("avaritia", "model/hemisphere.obj"));
    }
    
    public void func_76986_a(final Entity ent, final double x, final double y, final double z, final float p_76986_8_, final float partialTicks) {
        final EntityGapingVoid egv = (EntityGapingVoid)ent;
        final Minecraft mc = Minecraft.func_71410_x();
        mc.field_71446_o.func_110577_a(this.halo);
        final Tessellator tess = Tessellator.field_78398_a;
        final double age = egv.getAge() + partialTicks;
        this.setColour(age, 1.0);
        final double scale = EntityGapingVoid.getVoidScale(age);
        final double fullfadedist = 0.6 * scale;
        final double fadedist = fullfadedist + 1.5;
        double halocoord = 0.58 * scale;
        final double haloscaledist = 2.2 * scale;
        final double dx = ent.field_70165_t - RenderManager.field_78725_b;
        final double dy = ent.field_70163_u - RenderManager.field_78726_c;
        final double dz = ent.field_70161_v - RenderManager.field_78723_d;
        final double xzlen = Math.sqrt(dx * dx + dz * dz);
        final double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len <= haloscaledist) {
            final double close = (haloscaledist - len) / haloscaledist;
            halocoord *= 1.0 + close * close * close * close * 1.5;
        }
        final double yang = Math.atan2(xzlen, dy) * 57.29577951308232;
        final double xang = Math.atan2(dx, dz) * 57.29577951308232;
        GL11.glDisable(2896);
        mc.field_71460_t.func_78483_a(0.0);
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotated(xang, 0.0, 1.0, 0.0);
        GL11.glRotated(yang + 90.0, 1.0, 0.0, 0.0);
        GL11.glPushMatrix();
        GL11.glRotated(90.0, 1.0, 0.0, 0.0);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        tess.func_78382_b();
        tess.func_78374_a(-halocoord, 0.0, -halocoord, 0.0, 0.0);
        tess.func_78374_a(-halocoord, 0.0, halocoord, 0.0, 1.0);
        tess.func_78374_a(halocoord, 0.0, halocoord, 1.0, 1.0);
        tess.func_78374_a(halocoord, 0.0, -halocoord, 1.0, 0.0);
        tess.func_78381_a();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.fill);
        GL11.glScaled(scale, scale, scale);
        this.model.renderAll();
        GL11.glPopMatrix();
        if (len <= fadedist) {
            double alpha = 1.0;
            if (len >= fullfadedist) {
                alpha = 1.0 - (len - fullfadedist) / (fadedist - fullfadedist);
                alpha = alpha * alpha * (3.0 - 2.0 * alpha);
            }
            this.setColour(age, alpha);
            GL11.glPushMatrix();
            GL11.glDisable(3008);
            GL11.glEnable(3042);
            GL11.glRotatef(180.0f - this.field_76990_c.field_78735_i, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0f, 0.0f, 0.0f);
            final double d = 0.0;
            tess.func_78382_b();
            tess.func_78374_a(-100.0, 100.0, d, 0.0, 0.0);
            tess.func_78374_a(-100.0, -100.0, d, 0.0, 1.0);
            tess.func_78374_a(100.0, -100.0, d, 1.0, 1.0);
            tess.func_78374_a(100.0, 100.0, d, 1.0, 0.0);
            tess.func_78381_a();
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            GL11.glPopMatrix();
        }
        mc.field_71460_t.func_78463_b(0.0);
        GL11.glEnable(2896);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
    }
    
    protected ResourceLocation func_110775_a(final Entity ent) {
        return this.fill;
    }
    
    private void setColour(final double age, final double alpha) {
        final double life = age / 186.0;
        double f = Math.max(0.0, (life - EntityGapingVoid.collapse) / (1.0 - EntityGapingVoid.collapse));
        f = Math.max(f, 1.0 - life * 30.0);
        GL11.glColor4d(f, f, f, alpha);
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.IRenderHandler;

public class ApocalypseSkyRenderer extends IRenderHandler
{
    private static IModelCustom model;
    private static final ResourceLocation texture;
    private static final ResourceLocation halo;
    private static final ResourceLocation glow;
    
    public ApocalypseSkyRenderer() {
        if (ApocalypseSkyRenderer.model == null) {
            ApocalypseSkyRenderer.model = AdvancedModelLoader.loadModel(new ResourceLocation("avaritia", "model/sun.obj"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void render(final float partialTicks, final WorldClient world, final Minecraft mc) {
        GL11.glDisable(2912);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        RenderHelper.func_74518_a();
        GL11.glDepthMask(false);
        final Tessellator t = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotated(25.0, 1.0, 0.0, 0.0);
        GL11.glTranslated(0.0, 0.0, -350.0);
        GL11.glScaled(500.0, 500.0, 500.0);
        GL11.glRotated(90.0, -1.0, 0.0, 0.0);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ApocalypseSkyRenderer.glow);
        GL11.glColor4d(0.9490196078431372, 0.23921568627450981, 0.00392156862745098, 0.3);
        double bound = 0.7;
        double depth = -0.4;
        t.func_78382_b();
        t.func_78374_a(-bound, depth, -bound, 0.0, 0.0);
        t.func_78374_a(bound, depth, -bound, 1.0, 0.0);
        t.func_78374_a(bound, depth, bound, 1.0, 1.0);
        t.func_78374_a(-bound, depth, bound, 0.0, 1.0);
        t.func_78381_a();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ApocalypseSkyRenderer.halo);
        GL11.glColor3d(0.9490196078431372, 0.23921568627450981, 0.00392156862745098);
        bound = 0.562;
        depth = -0.2;
        t.func_78382_b();
        t.func_78374_a(-bound, depth, -bound, 0.0, 0.0);
        t.func_78374_a(bound, depth, -bound, 1.0, 0.0);
        t.func_78374_a(bound, depth, bound, 1.0, 1.0);
        t.func_78374_a(-bound, depth, bound, 0.0, 1.0);
        t.func_78381_a();
        GL11.glRotated(90.0, 1.0, 0.0, 0.0);
        GL11.glColor3d(1.0, 1.0, 1.0);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ApocalypseSkyRenderer.texture);
        ApocalypseSkyRenderer.model.renderAll();
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
    }
    
    static {
        texture = new ResourceLocation("avaritia", "textures/models/sun.png");
        halo = new ResourceLocation("avaritia", "textures/entity/voidhalo.png");
        glow = new ResourceLocation("avaritia", "textures/items/halo128.png");
    }
}

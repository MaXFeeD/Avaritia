// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.renderer.EntityRenderer;
import org.lwjgl.opengl.ARBShaderObjects;
import net.minecraft.client.renderer.texture.TextureMap;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import java.lang.reflect.Field;

public class CosmicRenderShenanigans
{
    public static final ShaderCallback shaderCallback;
    public static float[] lightlevel;
    public static String[] lightmapobf;
    public static boolean inventoryRender;
    public static float cosmicOpacity;
    private static Field mapfield;
    
    public static void useShader() {
        ShaderHelper.useShader(ShaderHelper.cosmicShader, CosmicRenderShenanigans.shaderCallback);
    }
    
    public static void releaseShader() {
        ShaderHelper.releaseShader();
    }
    
    public static void setLightFromLocation(final World world, final int x, final int y, final int z) {
        if (world == null) {
            setLightLevel(1.0f);
            return;
        }
        final int coord = world.func_72802_i(x, y, z, 0);
        int[] map = null;
        try {
            map = (int[])CosmicRenderShenanigans.mapfield.get(Minecraft.func_71410_x().field_71460_t);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e, "Failure to get light map");
        }
        if (map == null) {
            setLightLevel(1.0f);
            return;
        }
        final int mx = coord % 65536 / 16;
        final int my = coord / 65536 / 16;
        final int lightcolour = map[my * 16 + mx];
        setLightLevel((lightcolour >> 16 & 0xFF) / 256.0f, (lightcolour >> 8 & 0xFF) / 256.0f, (lightcolour & 0xFF) / 256.0f);
    }
    
    public static void setLightLevel(final float level) {
        setLightLevel(level, level, level);
    }
    
    public static void setLightLevel(final float r, final float g, final float b) {
        CosmicRenderShenanigans.lightlevel[0] = Math.max(0.0f, Math.min(1.0f, r));
        CosmicRenderShenanigans.lightlevel[1] = Math.max(0.0f, Math.min(1.0f, g));
        CosmicRenderShenanigans.lightlevel[2] = Math.max(0.0f, Math.min(1.0f, b));
    }
    
    public static void bindItemTexture() {
        final Minecraft mc = Minecraft.func_71410_x();
        mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
    }
    
    static {
        CosmicRenderShenanigans.lightlevel = new float[3];
        CosmicRenderShenanigans.lightmapobf = new String[] { "lightmapColors", "field_78504_Q", "U" };
        CosmicRenderShenanigans.inventoryRender = false;
        CosmicRenderShenanigans.cosmicOpacity = 1.0f;
        shaderCallback = new ShaderCallback() {
            @Override
            public void call(final int shader) {
                final Minecraft mc = Minecraft.func_71410_x();
                float yaw = 0.0f;
                float pitch = 0.0f;
                float scale = 1.0f;
                if (!CosmicRenderShenanigans.inventoryRender) {
                    yaw = (float)(mc.field_71439_g.field_70177_z * 2.0f * 3.141592653589793 / 360.0);
                    pitch = -(float)(mc.field_71439_g.field_70125_A * 2.0f * 3.141592653589793 / 360.0);
                }
                else {
                    scale = 25.0f;
                }
                final int x = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"yaw");
                ARBShaderObjects.glUniform1fARB(x, yaw);
                final int z = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"pitch");
                ARBShaderObjects.glUniform1fARB(z, pitch);
                final int l = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"lightlevel");
                ARBShaderObjects.glUniform3fARB(l, CosmicRenderShenanigans.lightlevel[0], CosmicRenderShenanigans.lightlevel[1], CosmicRenderShenanigans.lightlevel[2]);
                final int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"lightmix");
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);
                final int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"cosmicuvs");
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, LudicrousRenderEvents.cosmicUVs);
                final int s = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"externalScale");
                ARBShaderObjects.glUniform1fARB(s, scale);
                final int o = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"opacity");
                ARBShaderObjects.glUniform1fARB(o, CosmicRenderShenanigans.cosmicOpacity);
            }
        };
        CosmicRenderShenanigans.mapfield = ReflectionHelper.findField((Class)EntityRenderer.class, CosmicRenderShenanigans.lightmapobf);
    }
}

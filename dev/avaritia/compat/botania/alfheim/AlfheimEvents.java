// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.potion.Potion;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.Timer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.world.World;
import fox.spiteful.avaritia.FieldHelper;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class AlfheimEvents
{
    public static Field lightmapUpdateNeededField;
    public static Field mcTimerField;
    public static Field torchFlickerField;
    public static Field lightmapColorsField;
    public static Field lightmapTextureField;
    public static Field bossColorModifierField;
    public static Field bossColorModifierPrevField;
    public static Method getNightVisionBrightnessMethod;
    
    @SubscribeEvent
    public void onRenderStart(final TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            final Minecraft mc = Minecraft.func_71410_x();
            if (mc.field_71441_e == null) {
                return;
            }
            final World world = (World)mc.field_71441_e;
            if (world.field_73011_w == null || !(world.field_73011_w instanceof WorldProviderAlfheim)) {
                return;
            }
            final EntityRenderer er = mc.field_71460_t;
            final boolean needsupdate = FieldHelper.get(AlfheimEvents.lightmapUpdateNeededField, er);
            if (needsupdate) {
                this.updateLightmapRedGiant();
            }
        }
    }
    
    public void updateLightmapRedGiant() {
        final Minecraft mc = Minecraft.func_71410_x();
        final EntityRenderer er = mc.field_71460_t;
        final Timer timer = FieldHelper.get(AlfheimEvents.mcTimerField, mc);
        final float partialTicks = timer.field_74281_c;
        final float flicker = FieldHelper.get(AlfheimEvents.torchFlickerField, er);
        final float bossColorModifier = FieldHelper.get(AlfheimEvents.bossColorModifierField, er);
        final float bossColorModifierPrev = FieldHelper.get(AlfheimEvents.bossColorModifierPrevField, er);
        final int[] lightmapColors = FieldHelper.get(AlfheimEvents.lightmapColorsField, er);
        final DynamicTexture lightmapTexture = FieldHelper.get(AlfheimEvents.lightmapTextureField, er);
        final WorldClient worldclient = mc.field_71441_e;
        if (worldclient != null) {
            for (int i = 0; i < 256; ++i) {
                final float brightness = worldclient.func_72971_b(1.0f) * 0.95f + 0.05f;
                float sun = worldclient.field_73011_w.field_76573_f[i / 16] * brightness;
                final float torch = worldclient.field_73011_w.field_76573_f[i % 16] * (flicker * 0.1f + 1.5f);
                if (worldclient.field_73016_r > 0) {
                    sun = worldclient.field_73011_w.field_76573_f[i / 16];
                }
                final float sunr = sun * (worldclient.func_72971_b(1.0f) * 0.65f + 0.35f);
                final float sung = sun * (worldclient.func_72971_b(1.0f) * 0.65f + 0.35f);
                final float torchg = torch * ((torch * 0.6f + 0.4f) * 0.6f + 0.4f);
                final float torchb = torch * (torch * torch * 0.6f + 0.4f);
                float fr = sunr + torch;
                float fg = sung * 0.3f + torchg;
                float fb = sun * 0.2f + torchb;
                fr = fr * 0.96f + 0.03f;
                fg = fg * 0.96f + 0.03f;
                fb = fb * 0.96f + 0.03f;
                if (bossColorModifier > 0.0f) {
                    final float modifier = bossColorModifierPrev + (bossColorModifier - bossColorModifierPrev) * partialTicks;
                    fr = fr * (1.0f - modifier) + fr * 0.7f * modifier;
                    fg = fg * (1.0f - modifier) + fg * 0.6f * modifier;
                    fb = fb * (1.0f - modifier) + fb * 0.6f * modifier;
                }
                if (worldclient.field_73011_w.field_76574_g == 1) {
                    fr = 0.22f + torch * 0.75f;
                    fg = 0.28f + torchg * 0.75f;
                    fb = 0.25f + torchb * 0.75f;
                }
                if (mc.field_71439_g.func_70644_a(Potion.field_76439_r)) {
                    final float modifier = FieldHelper.invoke(AlfheimEvents.getNightVisionBrightnessMethod, er, mc.field_71439_g, partialTicks);
                    float nightvision = 1.0f / fr;
                    if (nightvision > 1.0f / fg) {
                        nightvision = 1.0f / fg;
                    }
                    if (nightvision > 1.0f / fb) {
                        nightvision = 1.0f / fb;
                    }
                    fr = fr * (1.0f - modifier) + fr * nightvision * modifier;
                    fg = fg * (1.0f - modifier) + fg * nightvision * modifier;
                    fb = fb * (1.0f - modifier) + fb * nightvision * modifier;
                }
                if (fr > 1.0f) {
                    fr = 1.0f;
                }
                if (fg > 1.0f) {
                    fg = 1.0f;
                }
                if (fb > 1.0f) {
                    fb = 1.0f;
                }
                final float modifier = mc.field_71474_y.field_74333_Y;
                float gr = 1.0f - fr;
                float gg = 1.0f - fg + 0.25f * sun;
                float gb = 1.0f - fb + 0.2f * sun;
                gr = 1.0f - gr * gr * gr * gr;
                gg = 1.0f - gg * gg * gg * gg;
                gb = 1.0f - gb * gb * gb * gb;
                fr = fr * (1.0f - modifier) + gr * modifier;
                fg = fg * (1.0f - modifier) + gg * modifier;
                fb = fb * (1.0f - modifier) + gb * modifier;
                fr = fr * 0.96f + 0.03f;
                fg = fg * 0.96f + 0.03f;
                fb = fb * 0.96f + 0.03f;
                if (fr > 1.0f) {
                    fr = 1.0f;
                }
                if (fg > 1.0f) {
                    fg = 1.0f;
                }
                if (fb > 1.0f) {
                    fb = 1.0f;
                }
                if (fr < 0.0f) {
                    fr = 0.0f;
                }
                if (fg < 0.0f) {
                    fg = 0.0f;
                }
                if (fb < 0.0f) {
                    fb = 0.0f;
                }
                final short alpha = 255;
                final int r = (int)(fr * 255.0f);
                final int g = (int)(fg * 255.0f);
                final int b = (int)(fb * 255.0f);
                lightmapColors[i] = (alpha << 24 | r << 16 | g << 8 | b);
            }
            lightmapTexture.func_110564_a();
            FieldHelper.set(AlfheimEvents.lightmapUpdateNeededField, er, false);
        }
    }
    
    static {
        AlfheimEvents.getNightVisionBrightnessMethod = ReflectionHelper.findMethod((Class)EntityRenderer.class, (Object)Minecraft.func_71410_x().field_71460_t, new String[] { "getNightVisionBrightness", "func_82830_a" }, new Class[] { EntityPlayer.class, Float.TYPE });
        try {
            AlfheimEvents.mcTimerField = ReflectionHelper.findField((Class)Minecraft.class, new String[] { "timer", "field_71428_T" });
            AlfheimEvents.lightmapUpdateNeededField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "lightmapUpdateNeeded", "field_78536_aa" });
            AlfheimEvents.torchFlickerField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "torchFlickerX", "field_78514_e" });
            AlfheimEvents.lightmapColorsField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "lightmapColors", "field_78504_Q" });
            AlfheimEvents.lightmapTextureField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "lightmapTexture", "field_78513_d" });
            AlfheimEvents.bossColorModifierField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "bossColorModifier", "field_82831_U" });
            AlfheimEvents.bossColorModifierPrevField = ReflectionHelper.findField((Class)EntityRenderer.class, new String[] { "bossColorModifierPrev", "field_82832_V" });
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
        }
    }
}

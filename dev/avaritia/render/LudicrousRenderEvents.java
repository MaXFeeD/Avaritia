// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraftforge.client.event.GuiScreenEvent;
import org.lwjgl.BufferUtils;
import cpw.mods.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraft.util.IIcon;
import java.nio.FloatBuffer;

public class LudicrousRenderEvents
{
    private static final int cosmicCount = 10;
    public static String[] cosmicTextures;
    public static FloatBuffer cosmicUVs;
    public static IIcon[] cosmicIcons;
    
    @SubscribeEvent
    public void letsMakeAQuilt(final TextureStitchEvent.Pre event) {
        if (event.map.func_130086_a() != 1) {
            return;
        }
        for (int i = 0; i < LudicrousRenderEvents.cosmicTextures.length; ++i) {
            final IIcon icon = event.map.func_94245_a(LudicrousRenderEvents.cosmicTextures[i]);
            LudicrousRenderEvents.cosmicIcons[i] = icon;
        }
        ModelArmorInfinity.overlayIcon = event.map.func_94245_a("avaritia:infinity_armor_mask");
        ModelArmorInfinity.invulnOverlayIcon = event.map.func_94245_a("avaritia:infinity_armor_mask2");
        ModelArmorInfinity.wingOverlayIcon = event.map.func_94245_a("avaritia:infinity_armor_wingmask");
    }
    
    @SubscribeEvent
    public void weMadeAQuilt(final TextureStitchEvent.Post event) {
        if (event.map.func_130086_a() != 1) {
            return;
        }
        CosmicRenderShenanigans.bindItemTexture();
        ModelArmorInfinity.itempagewidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
        ModelArmorInfinity.itempageheight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
        ModelArmorInfinity.armorModel.rebuildOverlay();
        ModelArmorInfinity.legModel.rebuildOverlay();
    }
    
    @SubscribeEvent
    public void pushTheCosmicFancinessToTheLimit(final TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            LudicrousRenderEvents.cosmicUVs = BufferUtils.createFloatBuffer(4 * LudicrousRenderEvents.cosmicIcons.length);
            for (int i = 0; i < LudicrousRenderEvents.cosmicIcons.length; ++i) {
                final IIcon icon = LudicrousRenderEvents.cosmicIcons[i];
                LudicrousRenderEvents.cosmicUVs.put(icon.func_94209_e());
                LudicrousRenderEvents.cosmicUVs.put(icon.func_94206_g());
                LudicrousRenderEvents.cosmicUVs.put(icon.func_94212_f());
                LudicrousRenderEvents.cosmicUVs.put(icon.func_94210_h());
            }
            LudicrousRenderEvents.cosmicUVs.flip();
        }
    }
    
    @SubscribeEvent
    public void makeCosmicStuffLessDumbInGUIs(final GuiScreenEvent.DrawScreenEvent.Pre event) {
        CosmicRenderShenanigans.inventoryRender = true;
    }
    
    @SubscribeEvent
    public void finishMakingCosmicStuffLessDumbInGUIs(final GuiScreenEvent.DrawScreenEvent.Post event) {
        CosmicRenderShenanigans.inventoryRender = false;
    }
    
    static {
        LudicrousRenderEvents.cosmicTextures = new String[10];
        for (int i = 0; i < 10; ++i) {
            LudicrousRenderEvents.cosmicTextures[i] = "avaritia:cosmic" + i;
        }
        LudicrousRenderEvents.cosmicUVs = BufferUtils.createFloatBuffer(4 * LudicrousRenderEvents.cosmicTextures.length);
        LudicrousRenderEvents.cosmicIcons = new IIcon[LudicrousRenderEvents.cosmicTextures.length];
    }
}

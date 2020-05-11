// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import fox.spiteful.avaritia.render.FancyHaloRenderer;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.Config;
import fox.spiteful.avaritia.compat.ticon.TonkersClient;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import fox.spiteful.avaritia.compat.botania.RenderTileInfinitato;
import fox.spiteful.avaritia.compat.botania.TileInfinitato;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fox.spiteful.avaritia.compat.botania.RenderInfinitato;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CompatClient
{
    public static void earlyComprettify() {
    }
    
    public static void comprettify() {
        if (Compat.botan) {
            RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderInfinitato(RenderingRegistry.getNextAvailableRenderId()));
            ClientRegistry.bindTileEntitySpecialRenderer((Class)TileInfinitato.class, (TileEntitySpecialRenderer)new RenderTileInfinitato());
        }
        if (Compat.ticon) {
            TonkersClient.dunkThosePaintbrushes();
        }
    }
    
    public static void lateComprettify() {
        if (Compat.forestry && Config.bees) {
            MinecraftForgeClient.registerItemRenderer(LudicrousItems.beesource, (IItemRenderer)new FancyHaloRenderer());
        }
    }
}

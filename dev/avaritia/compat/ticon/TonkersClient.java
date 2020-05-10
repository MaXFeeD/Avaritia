// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import tconstruct.library.client.TConstructClientRegistry;
import fox.spiteful.avaritia.Lumberjack;

public class TonkersClient
{
    public static void dunkThosePaintbrushes() {
        Lumberjack.info("Registering TiCon renderer mappings");
        TConstructClientRegistry.addMaterialRenderMapping(500, "tinker", "avaritia_neutronium", true);
        TConstructClientRegistry.addMaterialRenderMapping(501, "tinker", "avaritia_infinitymetal", true);
        final NeutroniumIcons iconN = new NeutroniumIcons();
        iconN.register();
        final InfinityIcons iconI = new InfinityIcons();
        iconI.register();
    }
}

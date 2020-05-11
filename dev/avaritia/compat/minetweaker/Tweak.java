// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.minetweaker;

import fox.spiteful.avaritia.Config;
import minetweaker.MineTweakerAPI;

public class Tweak
{
    public static void registrate() {
        MineTweakerAPI.registerClass((Class)ExtremeCrafting.class);
        if (Config.craftingOnly) {
            return;
        }
        MineTweakerAPI.registerClass((Class)Compressor.class);
    }
}

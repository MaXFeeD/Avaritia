// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat;

import com.emoniph.witchery.util.CreatureUtil;
import cpw.mods.fml.common.Loader;
import net.minecraft.entity.Entity;

public class Belmont
{
    public static boolean isVampire(final Entity entity) {
        if (Loader.isModLoaded("witchery")) {
            try {
                return CreatureUtil.isVampire(entity);
            }
            catch (Throwable t) {}
        }
        return false;
    }
}

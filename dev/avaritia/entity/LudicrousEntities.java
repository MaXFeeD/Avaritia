// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import fox.spiteful.avaritia.Avaritia;

public class LudicrousEntities
{
    public static void letLooseTheDogsOfWar() {
        EntityRegistry.registerModEntity((Class)EntityEndestPearl.class, "EndestPearl", 1, (Object)Avaritia.instance, 64, 10, true);
        EntityRegistry.registerModEntity((Class)EntityGapingVoid.class, "GapingVoid", 2, (Object)Avaritia.instance, 256, 10, false);
        EntityRegistry.registerModEntity((Class)EntityHeavenArrow.class, "HeavenArrow", 3, (Object)Avaritia.instance, 32, 1, true);
        EntityRegistry.registerModEntity((Class)EntityHeavenSubArrow.class, "HeavenSubArrow", 4, (Object)Avaritia.instance, 32, 2, true);
    }
}

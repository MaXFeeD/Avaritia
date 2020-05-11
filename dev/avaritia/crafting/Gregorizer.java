// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import fox.spiteful.avaritia.Config;
import cpw.mods.fml.common.Loader;

public class Gregorizer
{
    public static int modifier;
    public static int multiplier;
    
    public static void balance() {
        if (Loader.isModLoaded("Thaumcraft")) {
            Gregorizer.modifier += 100;
        }
        if (Loader.isModLoaded("TConstruct") || Loader.isModLoaded("HydCraft")) {
            Gregorizer.modifier += 100;
        }
        if (Loader.isModLoaded("ThermalExpansion") || Loader.isModLoaded("TSteelworks") || Loader.isModLoaded("IC2") || Loader.isModLoaded("ThaumicTinkerer")) {
            Gregorizer.modifier += 300;
        }
        if (Loader.isModLoaded("technom")) {
            Gregorizer.modifier += 600;
        }
        if (Loader.isModLoaded("magicalcrops")) {
            ++Gregorizer.multiplier;
        }
        if (Loader.isModLoaded("AgriCraft")) {
            ++Gregorizer.multiplier;
        }
        if (Loader.isModLoaded("MineFactoryReloaded")) {
            Gregorizer.multiplier += 9;
        }
        if (Loader.isModLoaded("BigReactors")) {
            Gregorizer.modifier += 100;
        }
        if (Loader.isModLoaded("EE3")) {
            ++Gregorizer.multiplier;
        }
        else if (Loader.isModLoaded("ProjectE")) {
            Gregorizer.multiplier += 3;
        }
        if (Loader.isModLoaded("Botania")) {
            Gregorizer.modifier += 50;
        }
        if (Loader.isModLoaded("ExtraUtilities")) {
            Gregorizer.modifier += 500;
        }
        if (Loader.isModLoaded("appliedenergistics2")) {
            Gregorizer.modifier += 200;
        }
        if (Loader.isModLoaded("ImmersiveEngineering")) {
            Gregorizer.modifier += 300;
        }
        if (Loader.isModLoaded("Mekanism")) {
            Gregorizer.modifier += 500;
            ++Gregorizer.multiplier;
        }
        if (Loader.isModLoaded("Torcherino")) {
            Gregorizer.multiplier += 2;
        }
        if (Loader.isModLoaded("DraconicEvolution")) {
            Gregorizer.modifier += 300;
            ++Gregorizer.multiplier;
        }
        Gregorizer.modifier = Math.max(Gregorizer.modifier + Config.modifier, 0);
        Gregorizer.multiplier = Math.max(Gregorizer.multiplier + Config.multiplier, 1);
    }
    
    public static int balanceCost(final int cost) {
        return (cost + Gregorizer.modifier) * Gregorizer.multiplier;
    }
    
    static {
        Gregorizer.modifier = 0;
        Gregorizer.multiplier = 1;
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import org.apache.logging.log4j.Level;
import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class Config
{
    public static boolean craftingOnly;
    public static boolean endStone;
    public static boolean bedrockBreaker;
    public static boolean boringFood;
    public static boolean fractured;
    public static boolean fast;
    public static boolean thaumic;
    public static boolean sc2;
    public static boolean ae2;
    public static boolean exu;
    public static boolean ic2;
    public static boolean gt;
    public static boolean botan;
    public static boolean blood;
    public static boolean lolDargon;
    public static boolean bigReactors;
    public static boolean ticon;
    public static boolean pe;
    public static boolean mfr;
    public static boolean twilight;
    public static boolean magicrops;
    public static boolean am2;
    public static boolean te;
    public static boolean numanuma;
    public static boolean metallurgy;
    public static boolean enderio;
    public static boolean forestry;
    public static boolean bees;
    public static boolean ee3;
    public static boolean extracells;
    public static boolean witch;
    public static boolean rotisserie;
    public static boolean copper;
    public static boolean tin;
    public static boolean silver;
    public static boolean lead;
    public static boolean steel;
    public static boolean nickel;
    public static boolean ultimateBalance;
    public static int modifier;
    public static int multiplier;
    
    public static void configurate(final File file) {
        final Configuration conf = new Configuration(file);
        try {
            conf.load();
            Config.craftingOnly = conf.get("general", "Crafting Only", Config.craftingOnly, "Enable to completely disable most of the mod except for the Dire Crafting table. For if you just want the mod for Minetweaking purposes.").getBoolean(false);
            Config.endStone = conf.get("general", "Use End Stone", Config.endStone, "Disable to take end stone out of recipes").getBoolean(true);
            Config.bedrockBreaker = conf.get("general", "Break Bedrock", Config.bedrockBreaker, "Disable if you don't want the World Breaker to break unbreakable blocks").getBoolean(true);
            Config.boringFood = conf.get("general", "Boring Food", Config.boringFood, "Enable to keep the Ultimate Stew and Cosmic Meatballs from grabbing more ingredients").getBoolean(false);
            Config.fractured = conf.get("general", "Fractured Ores", Config.fractured, "Enable if you don't have Rotarycraft installed and want some buggy fractured ores").getBoolean(false);
            Config.fast = conf.get("general", "Gotta Go Fast", Config.fast, "Disable if the Infinity Boots' speed boost is too ridiculous").getBoolean(true);
            conf.addCustomCategoryComment("compatibility", "Disable to stop compatibility with that particular mod. Will not use the mod in recipes or add new items for that mod.");
            Config.thaumic = conf.get("compatibility", "Thaumcraft", true).getBoolean(true);
            Config.sc2 = conf.get("compatibility", "Steve's Carts 2", true).getBoolean(true);
            Config.ae2 = conf.get("compatibility", "Applied Energistics 2", true).getBoolean(true);
            Config.exu = conf.get("compatibility", "Extra Utilities", true).getBoolean(true);
            Config.ic2 = conf.get("compatibility", "Industrialcraft 2 Experimental", true).getBoolean(true);
            Config.gt = conf.get("compatibility", "Gregtech 5", true).getBoolean(true);
            Config.botan = conf.get("compatibility", "Botania", true).getBoolean(true);
            Config.blood = conf.get("compatibility", "Blood Magic", true).getBoolean(true);
            Config.lolDargon = conf.get("compatibility", "Draconic Evolution", true).getBoolean(true);
            Config.bigReactors = conf.get("compatibility", "Big Reactors", true).getBoolean(true);
            Config.ticon = conf.get("compatibility", "Tinkers Construct", true).getBoolean(true);
            Config.pe = conf.get("compatibility", "Project E", true).getBoolean(true);
            Config.mfr = conf.get("compatibility", "MineFactory Reloaded", true).getBoolean(true);
            Config.twilight = conf.get("compatibility", "Twilight Forest", true).getBoolean(true);
            Config.magicrops = conf.get("compatibility", "Magical Crops", true).getBoolean(true);
            Config.am2 = conf.get("compatibility", "Ars Magica 2", true).getBoolean(true);
            Config.te = conf.get("compatibility", "Thermal Expansion", true).getBoolean(true);
            Config.numanuma = conf.get("compatibility", "Pneumaticraft", true).getBoolean(true);
            Config.metallurgy = conf.get("compatibility", "Metallurgy", true).getBoolean(true);
            Config.enderio = conf.get("compatibility", "EnderIO", true).getBoolean(true);
            Config.forestry = conf.get("compatibility", "Forestry", true).getBoolean(true);
            Config.bees = conf.get("compatibility", "Forestry Bees", false).getBoolean(false);
            Config.ee3 = conf.get("compatibility", "Equivalent Exchange 3", true).getBoolean(true);
            Config.extracells = conf.get("compatibility", "Extra Cells", true).getBoolean(true);
            Config.witch = conf.get("compatibility", "Witchery", true).getBoolean(true);
            Config.rotisserie = conf.get("compatibility", "Rotarycraft", true).getBoolean(true);
            conf.addCustomCategoryComment("materials", "Disable to stop using that material in recipes. Useful if a mod adds unobtainable placeholder ores.");
            Config.copper = conf.get("materials", "Copper", true).getBoolean(true);
            Config.tin = conf.get("materials", "Tin", true).getBoolean(true);
            Config.silver = conf.get("materials", "Silver", true).getBoolean(true);
            Config.lead = conf.get("materials", "Lead", true).getBoolean(true);
            Config.nickel = conf.get("materials", "Nickel/Ferrous", true).getBoolean(true);
            Config.steel = conf.get("materials", "Steel", true).getBoolean(true);
            Config.ultimateBalance = conf.get("materials", "Clay", true).getBoolean(true);
            Config.modifier = conf.get("balance!", "Cost Modifier", 0, "Added to the existing modifier to make prices more expensive or cheaper. Can be negative.").getInt(0);
            Config.multiplier = conf.get("balance!", "Cost Multiplier", 0, "Added to the existing multiplier to make prices more expensive or cheaper. Can be negative.").getInt(0);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e, "Avaritia couldn't find its config!");
        }
        finally {
            conf.save();
        }
    }
    
    static {
        Config.craftingOnly = false;
        Config.endStone = true;
        Config.bedrockBreaker = true;
        Config.boringFood = false;
        Config.fractured = false;
        Config.fast = true;
        Config.thaumic = true;
        Config.sc2 = true;
        Config.ae2 = true;
        Config.exu = true;
        Config.ic2 = true;
        Config.gt = true;
        Config.botan = true;
        Config.blood = true;
        Config.lolDargon = true;
        Config.bigReactors = true;
        Config.ticon = true;
        Config.pe = true;
        Config.mfr = true;
        Config.twilight = true;
        Config.magicrops = true;
        Config.am2 = true;
        Config.te = true;
        Config.numanuma = true;
        Config.metallurgy = true;
        Config.enderio = true;
        Config.forestry = true;
        Config.bees = false;
        Config.ee3 = true;
        Config.extracells = true;
        Config.witch = true;
        Config.rotisserie = true;
        Config.copper = true;
        Config.tin = true;
        Config.silver = true;
        Config.lead = true;
        Config.steel = true;
        Config.nickel = true;
        Config.ultimateBalance = true;
        Config.modifier = 0;
        Config.multiplier = 0;
    }
}

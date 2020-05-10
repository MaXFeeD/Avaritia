// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import fox.spiteful.avaritia.compat.tails.InfiniteFoxes;
import net.minecraft.init.Items;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import fox.spiteful.avaritia.compat.forestry.Ranger;
import net.minecraftforge.oredict.OreDictionary;
import fox.spiteful.avaritia.compat.ticon.Tonkers;
import fox.spiteful.avaritia.compat.bloodmagic.Bloody;
import fox.spiteful.avaritia.compat.botania.Tsundere;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.compat.thaumcraft.Lucrum;
import fox.spiteful.avaritia.compat.minetweaker.Tweak;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import fox.spiteful.avaritia.compat.nei.NotEnough;
import fox.spiteful.avaritia.Config;
import cpw.mods.fml.common.Loader;

public class Compat
{
    public static boolean nei;
    public static boolean thaumic;
    public static boolean ae2;
    public static boolean exu;
    public static boolean ic2;
    public static boolean gt;
    public static boolean botan;
    public static boolean blood;
    public static boolean bigReactors;
    public static boolean ticon;
    public static boolean pe;
    public static boolean tweak;
    public static boolean mfr;
    public static boolean am2;
    public static boolean forestry;
    public static boolean te;
    
    public static void census() {
        Compat.nei = Loader.isModLoaded("NotEnoughItems");
        Compat.tweak = Loader.isModLoaded("MineTweaker3");
        Compat.thaumic = (Loader.isModLoaded("Thaumcraft") && Config.thaumic);
        Compat.ae2 = (Loader.isModLoaded("appliedenergistics2") && Config.ae2);
        Compat.exu = (Loader.isModLoaded("ExtraUtilities") && Config.exu);
        Compat.ic2 = (Loader.isModLoaded("IC2") && Config.ic2);
        Compat.gt = (Loader.isModLoaded("gregtech") && Config.gt);
        Compat.botan = (Loader.isModLoaded("Botania") && Config.botan);
        Compat.blood = (Loader.isModLoaded("AWWayofTime") && Config.blood);
        Compat.bigReactors = (Loader.isModLoaded("BigReactors") && Config.bigReactors);
        Compat.ticon = (Loader.isModLoaded("TConstruct") && Config.ticon);
        Compat.pe = (Loader.isModLoaded("ProjectE") && Config.pe);
        Compat.mfr = (Loader.isModLoaded("MineFactoryReloaded") && Config.mfr);
        Compat.am2 = (Loader.isModLoaded("arsmagica2") && Config.am2);
        Compat.forestry = (Loader.isModLoaded("Forestry") && Config.forestry);
        Compat.te = (Loader.isModLoaded("ThermalExpansion") && Config.te);
    }
    
    public static void compatify() {
        if (Compat.nei) {
            try {
                NotEnough.items();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia had Too Many Items.");
            }
        }
        if (Compat.tweak) {
            try {
                Tweak.registrate();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia is too good for tweaking, apparently.");
            }
        }
        if (Config.craftingOnly) {
            return;
        }
        if (Compat.thaumic) {
            try {
                Lucrum.abracadabra();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia accumulated too much Warp!");
                e.printStackTrace();
                Compat.thaumic = false;
            }
        }
        if (Loader.isModLoaded("StevesCarts") && Config.sc2) {
            try {
                final Block resource = getBlock("StevesCarts", "BlockMetalStorage");
                final ItemStack galg = new ItemStack(resource, 1, 2);
                Grinder.catalyst.getInput().add(galg);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia died of old age while trying to craft the Galgadorian Drill.");
                e.printStackTrace();
            }
        }
        if (Compat.ae2) {
            try {
                final Item resource2 = getItem("appliedenergistics2", "item.ItemMultiMaterial");
                if (Loader.isModLoaded("extracells") && Config.extracells) {
                    final Item extracell = getItem("extracells", "storage.component");
                    Grinder.catalyst.getInput().add(new ItemStack(extracell, 1, 3));
                }
                else {
                    Grinder.catalyst.getInput().add(new ItemStack(resource2, 1, 38));
                }
                Grinder.catalyst.getInput().add(new ItemStack(resource2, 1, 47));
                final Block creative = getBlock("appliedenergistics2", "tile.BlockCreativeEnergyCell");
                final Block dense = getBlock("appliedenergistics2", "tile.BlockDenseEnergyCell");
                ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(creative, 1, 0), "IIIIDIIII", "IEEEDEEEI", "IEEEDEEEI", "IEEEDEEEI", "DDDDDDDDD", "IEEEDEEEI", "IEEEDEEEI", "IEEEDEEEI", "IIIIDIIII", 'D', new ItemStack(resource2, 1, 24), 'E', new ItemStack(dense, 1, 0), 'I', new ItemStack(LudicrousItems.resource, 1, 6));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia couldn't figure out how channels work.");
                e.printStackTrace();
                Compat.ae2 = false;
            }
        }
        if (Compat.exu) {
            try {
                final Block bedrockium = getBlock("ExtraUtilities", "block_bedrockium");
                final Block deco = getBlock("ExtraUtilities", "decorativeBlock1");
                final ItemStack bed = new ItemStack(bedrockium, 1, 0);
                final ItemStack unstable = new ItemStack(deco, 1, 5);
                Grinder.catalyst.getInput().add(bed);
                Grinder.catalyst.getInput().add(unstable);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia was unable to stop Lavos.");
                e.printStackTrace();
                Compat.exu = false;
            }
        }
        if (Compat.ic2) {
            try {
                if (Compat.gt) {
                    try {
                        final Item resource2 = getItem("gregtech", "gt.metaitem.01");
                        final ItemStack lolneutronium = new ItemStack(resource2, 1, 9129);
                        Grinder.catalyst.getInput().add(lolneutronium);
                    }
                    catch (Throwable e) {
                        Lumberjack.log(Level.INFO, "Avaritia got sick of only getting 2 planks per log.");
                        e.printStackTrace();
                        Compat.gt = false;
                    }
                }
                if (!Compat.gt) {
                    final Item iridium = getItem("IC2", "itemPartIridium");
                    final ItemStack plate = new ItemStack(iridium, 1, 0);
                    Grinder.catalyst.getInput().add(plate);
                }
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia blew up the macerator.");
                e.printStackTrace();
                Compat.ic2 = false;
            }
        }
        if (Compat.botan) {
            try {
                Tsundere.baka();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia is wondering where all the dayblooms went.");
                e.printStackTrace();
                Compat.botan = false;
            }
        }
        if (Compat.blood) {
            try {
                Bloody.bloodlett();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia decided to use a Fallen Kanade instead.");
                e.printStackTrace();
                Compat.blood = false;
            }
        }
        if (Loader.isModLoaded("DraconicEvolution") && Config.lolDargon) {
            try {
                final Block dargon = getBlock("DraconicEvolution", "draconicBlock");
                final ItemStack lol = new ItemStack(dargon, 1, 0);
                Grinder.catalyst.getInput().add(lol);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia was distracted by a giant glowing sphere.");
            }
        }
        if (Compat.bigReactors) {
            try {
                final Item ingot = getItem("BigReactors", "BRIngot");
                final ItemStack ludicrite = new ItemStack(ingot, 1, 8);
                Grinder.catalyst.getInput().add(ludicrite);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia decided it just didn't need all that RF.");
                e.printStackTrace();
                Compat.bigReactors = false;
            }
        }
        if (Compat.ticon) {
            try {
                final Block metal = getBlock("TConstruct", "MetalBlock");
                final ItemStack menomena = new ItemStack(metal, 1, 2);
                Grinder.catalyst.getInput().add(menomena);
                Tonkers.buildstruct();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia fell in the smeltery.");
                e.printStackTrace();
                Compat.ticon = false;
            }
        }
        if (Compat.pe) {
            try {
                final Item matter = getItem("ProjectE", "item.pe_matter");
                final ItemStack red = new ItemStack(matter, 1, 1);
                Grinder.catalyst.getInput().add(red);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia got tired of abusing the blaze rod exploit.");
                e.printStackTrace();
                Compat.pe = false;
            }
        }
        if (Compat.mfr) {
            try {
                final Item ponk = getItem("MineFactoryReloaded", "pinkslime");
                final ItemStack pank = new ItemStack(ponk, 1, 1);
                Grinder.catalyst.getInput().add(pank);
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia walked into the laser drill.");
                e.printStackTrace();
                Compat.mfr = false;
            }
        }
        if (Loader.isModLoaded("TwilightForest") && Config.twilight) {
            try {
                final Item ironwood = getItem("TwilightForest", "item.ironwoodIngot");
                Grinder.catalyst.getInput().add(new ItemStack(ironwood, 1));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia was killed by a hydra.");
            }
        }
        if (Loader.isModLoaded("magicalcrops") && Config.magicrops) {
            try {
                final Item essence = getItem("magicalcrops", "magicalcrops_MagicEssence");
                final Item meat = getItem("magicalcrops", "magicalcrops_RawMeat");
                final Item crop = getItem("magicalcrops", "magicalcrops_CropProduce");
                final ItemStack extreme = new ItemStack(essence, 1, 4);
                Grinder.catalyst.getInput().add(extreme);
                OreDictionary.registerOre("cropBlackberry", new ItemStack(crop, 1, 0));
                OreDictionary.registerOre("cropBlueberry", new ItemStack(crop, 1, 1));
                OreDictionary.registerOre("cropChilipepper", new ItemStack(crop, 1, 2));
                OreDictionary.registerOre("cropCucumber", new ItemStack(crop, 1, 3));
                OreDictionary.registerOre("cropGrape", new ItemStack(crop, 1, 4));
                OreDictionary.registerOre("cropRaspberry", new ItemStack(crop, 1, 5));
                OreDictionary.registerOre("cropStrawberry", new ItemStack(crop, 1, 6));
                OreDictionary.registerOre("cropCorn", new ItemStack(crop, 1, 7));
                OreDictionary.registerOre("cropTomato", new ItemStack(crop, 1, 8));
                OreDictionary.registerOre("rawMutton", new ItemStack(meat, 1, 0));
                OreDictionary.registerOre("rawCalamari", new ItemStack(meat, 1, 1));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia got bored of waiting for magical crops to grow.");
            }
        }
        if (Loader.isModLoaded("ganyssurface")) {
            try {
                final Item mutton = getItem("ganyssurface", "mutton_raw");
                OreDictionary.registerOre("rawMutton", new ItemStack(mutton, 1, 0));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia forgot which Gany's mod it was dealing with.");
            }
        }
        if (Loader.isModLoaded("harvestcraft")) {
            try {
                final Item mutton = getItem("harvestcraft", "muttonrawItem");
                final Item beet = getItem("harvestcraft", "beetItem");
                final Item calamari = getItem("harvestcraft", "calamarirawItem");
                OreDictionary.registerOre("rawMutton", new ItemStack(mutton, 1, 0));
                OreDictionary.registerOre("rawCalamari", new ItemStack(calamari, 1, 0));
                OreDictionary.registerOre("cropBeetroot", new ItemStack(beet, 1, 0));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia got overwhelmed by all the food choices. D:");
                e.printStackTrace();
            }
        }
        if (Loader.isModLoaded("Natura")) {
            try {
                final Item barley = getItem("Natura", "barleyFood");
                OreDictionary.registerOre("cropBarley", new ItemStack(barley, 1, 0));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia got overwhelmed by all the food choices. D:");
            }
        }
        if (Compat.am2) {
            try {
                final Item gem = getItem("arsmagica2", "itemOre");
                final Item essence2 = getItem("arsmagica2", "essence");
                Grinder.catalyst.getInput().add(new ItemStack(essence2, 1, 10));
                Grinder.catalyst.getInput().add(new ItemStack(gem, 1, 6));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia got sick of the arcane guardian's healspam.");
                Compat.am2 = false;
            }
        }
        if (Compat.forestry) {
            try {
                Ranger.stopForestFires();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia got stung by a bee.");
                Compat.forestry = false;
            }
        }
        if (Loader.isModLoaded("EE3") && Config.ee3) {
            try {
                final Block fuel = getBlock("EE3", "alchemicalFuelBlock");
                Grinder.catalyst.getInput().add(new ItemStack(fuel, 1, 2));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia tried to do human transmutation.");
            }
        }
        if (Compat.te) {
            try {
                final Block cell = getBlock("ThermalExpansion", "Cell");
                ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(cell, 1, 0), "IIIIRIIII", "IEEEREEEI", "IERRRRREI", "IERRRRREI", "RRRRXRRRR", "IERRRRREI", "IERRRRREI", "IEEEREEEI", "IIIIRIIII", 'X', new ItemStack(LudicrousBlocks.resource_block, 1, 1), 'E', "blockEnderium", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'R', new ItemStack(LudicrousItems.singularity, 1, 3));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia forgot one of Thermal Expansion's 500 prerequisites.");
                Compat.te = false;
            }
        }
        if (Loader.isModLoaded("witchery") && Config.witch) {
            try {
                final Item ingredient = getItem("witchery", "ingredient");
                Grinder.catalyst.getInput().add(new ItemStack(ingredient, 1, 150));
                final Block egg = getBlock("witchery", "infinityegg");
                ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(egg, 1, 0), "   NNN   ", "  NNNNN  ", "  NNNNN  ", " NNNINNN ", "NNNIIINNN", "NNIIEIINN", "NNNIIINNN", " NNNINNN ", "  NNNNN  ", 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'E', new ItemStack(Items.field_151110_aK), 'I', new ItemStack(LudicrousItems.resource, 1, 6));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia suffered from Curse of the Incompatibility.");
            }
        }
        if (Loader.isModLoaded("RotaryCraft") && Config.rotisserie) {
            try {
                final Item stuff = getItem("RotaryCraft", "rotarycraft_item_compacts");
                Grinder.catalyst.getInput().add(new ItemStack(stuff, 1, 3));
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, e, "Avaritia couldn't figure out how torque works.");
            }
        }
        if (Loader.isModLoaded("Tails")) {
            try {
                InfiniteFoxes.floof();
            }
            catch (Throwable e) {
                Lumberjack.log(Level.INFO, "Avaritia was not fluffy enough!");
                e.printStackTrace();
            }
        }
    }
    
    public static Block getBlock(final String mod, final String block) throws ItemNotFoundException {
        final Block target = GameRegistry.findBlock(mod, block);
        if (target == null) {
            throw new ItemNotFoundException(mod, block);
        }
        return target;
    }
    
    public static Item getItem(final String mod, final String item) throws ItemNotFoundException {
        final Item target = GameRegistry.findItem(mod, item);
        if (target == null) {
            throw new ItemNotFoundException(mod, item);
        }
        return target;
    }
    
    static {
        Compat.nei = false;
        Compat.thaumic = false;
        Compat.ae2 = false;
        Compat.exu = false;
        Compat.ic2 = false;
        Compat.gt = false;
        Compat.botan = false;
        Compat.blood = false;
        Compat.bigReactors = false;
        Compat.ticon = false;
        Compat.pe = false;
        Compat.tweak = false;
        Compat.mfr = false;
        Compat.am2 = false;
        Compat.forestry = false;
        Compat.te = false;
    }
    
    public static class ItemNotFoundException extends Exception
    {
        public ItemNotFoundException(final String mod, final String item) {
            super("Unable to find " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
        }
    }
}

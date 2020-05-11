// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.item.Item;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import fox.spiteful.avaritia.compat.Compat;
import cpw.mods.fml.common.Loader;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.Config;
import java.util.Random;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Mincer
{
    public static ShapelessOreRecipe stewRecipe;
    public static ShapelessOreRecipe meatballRecipe;
    private static final String[] sacredCropNames;
    private static final String[] forbiddenCropNames;
    private static final String[] knownMeatEntries;
    private static List<ItemStack> knownMeats;
    private static Random randy;
    
    public static void countThoseCalories() {
        if (Config.boringFood) {
            Mincer.stewRecipe = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(LudicrousItems.ultimate_stew, 1), new ItemStack(Items.field_151015_O, 1), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151174_bG), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151127_ba), new ItemStack(Blocks.field_150423_aK), new ItemStack(Blocks.field_150434_aF), new ItemStack((Block)Blocks.field_150337_Q), new ItemStack((Block)Blocks.field_150338_P));
            Mincer.meatballRecipe = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(LudicrousItems.cosmic_meatballs, 1), new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151082_bd), new ItemStack(Items.field_151076_bf), new ItemStack(Items.field_151076_bf), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_151115_aP), new ItemStack(Items.field_151115_aP));
            return;
        }
        final String[] orenames = OreDictionary.getOreNames();
        final List<String> rawCrops = new ArrayList<String>();
        final List<String> crops = new ArrayList<String>();
        final List<String> meatNames = new ArrayList<String>();
        final List<String> rawMeats = new ArrayList<String>();
        final List<String> meats = new ArrayList<String>();
        for (int i = 0; i < orenames.length; ++i) {
            final String orename = orenames[i];
            if (orename.startsWith("crop") && !bannedCrop(orename) && !orename.startsWith("cropBotania")) {
                rawCrops.add(orename);
            }
        }
        for (int i = 0; i < Mincer.sacredCropNames.length; ++i) {
            final String crop = Mincer.sacredCropNames[i];
            if (rawCrops.contains(crop)) {
                rawCrops.remove(crop);
                crops.add(crop);
            }
        }
        List<FoodInfo> cropSortingList = new ArrayList<FoodInfo>();
        Mincer.randy = new Random(12345L);
        for (int j = 0; j < rawCrops.size(); ++j) {
            final List<ItemStack> ores = (List<ItemStack>)OreDictionary.getOres((String)rawCrops.get(j));
            if (ores.size() > 0) {
                cropSortingList.add(new FoodInfo(rawCrops.get(j), ores.size()));
            }
        }
        Collections.sort(cropSortingList, new Comparator<FoodInfo>() {
            @Override
            public int compare(final FoodInfo a, final FoodInfo b) {
                if (a.count != b.count) {
                    return (b.count > a.count) ? 1 : -1;
                }
                return a.orename.compareTo(b.orename);
            }
        });
        Collections.shuffle(cropSortingList, Mincer.randy);
        Collections.sort(cropSortingList, new Comparator<FoodInfo>() {
            @Override
            public int compare(final FoodInfo a, final FoodInfo b) {
                if (a.count != b.count) {
                    return (b.count > a.count) ? 1 : -1;
                }
                return 0;
            }
        });
        if (cropSortingList.size() > 80 - crops.size()) {
            final int shouldHave = 80 - crops.size();
            cropSortingList = cropSortingList.subList(0, shouldHave);
        }
        for (int j = 0; j < cropSortingList.size(); ++j) {
            crops.add(cropSortingList.get(j).orename);
        }
        int croptypes;
        int cropmultiplier;
        for (croptypes = crops.size(), cropmultiplier = 1; croptypes * cropmultiplier < 8; ++cropmultiplier) {}
        final int makesstew = (int)Math.round(croptypes * cropmultiplier / 9.0);
        Mincer.stewRecipe = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(LudicrousItems.ultimate_stew, makesstew), new ItemStack(LudicrousItems.resource, 1, 2));
        final List<Object> stewInputs = (List<Object>)Mincer.stewRecipe.getInput();
        for (int k = 0; k < crops.size(); ++k) {
            for (int l = 0; l < cropmultiplier; ++l) {
                stewInputs.add(OreDictionary.getOres((String)crops.get(k)));
            }
        }
        List<FoodInfo> meatSortingList = new ArrayList<FoodInfo>();
        Mincer.randy = new Random(54321L);
        for (int m = 0; m < Mincer.knownMeatEntries.length; ++m) {
            if (OreDictionary.doesOreNameExist(Mincer.knownMeatEntries[m])) {
                final List<ItemStack> meatstacks = (List<ItemStack>)OreDictionary.getOres(Mincer.knownMeatEntries[m]);
                if (!meatstacks.isEmpty()) {
                    rawMeats.add(Mincer.knownMeatEntries[m]);
                    meatSortingList.add(new FoodInfo(Mincer.knownMeatEntries[m], meatstacks.size()));
                }
            }
        }
        if (Loader.isModLoaded("TwilightForest") && Config.twilight) {
            try {
                final Item venison = Compat.getItem("TwilightForest", "item.venisonRaw");
                final Item meef = Compat.getItem("TwilightForest", "item.meefRaw");
                Mincer.knownMeats.add(new ItemStack(venison));
                Mincer.knownMeats.add(new ItemStack(meef));
            }
            catch (Exception ex) {}
        }
        if (Loader.isModLoaded("Natura")) {
            try {
                final Item imp = Compat.getItem("Natura", "impmeat");
                Mincer.knownMeats.add(new ItemStack(imp));
            }
            catch (Exception ex2) {}
        }
        if (Compat.am2) {
            try {
                final Item stuff = Compat.getItem("arsmagica2", "itemOre");
                Mincer.knownMeats.add(new ItemStack(stuff, 1, 8));
            }
            catch (Exception e) {
                Lumberjack.log(Level.INFO, e, "Avaritia got sick of the arcane guardian's healspam.");
                Compat.am2 = false;
            }
        }
        Lumberjack.info("rawMeats: " + rawMeats);
        Lumberjack.info("knownMeats: " + Mincer.knownMeats);
        Collections.sort(meatSortingList, new Comparator<FoodInfo>() {
            @Override
            public int compare(final FoodInfo a, final FoodInfo b) {
                if (a.count != b.count) {
                    return (b.count > a.count) ? 1 : -1;
                }
                return a.orename.compareTo(b.orename);
            }
        });
        Collections.sort(meatSortingList, new Comparator<FoodInfo>() {
            @Override
            public int compare(final FoodInfo a, final FoodInfo b) {
                if (a.count != b.count) {
                    return (b.count > a.count) ? 1 : -1;
                }
                return Mincer.randy.nextBoolean() ? 1 : -1;
            }
        });
        if (meatSortingList.size() > 80 - meats.size() - Mincer.knownMeats.size()) {
            final int shouldHave2 = 80 - crops.size() - Mincer.knownMeats.size();
            meatSortingList = meatSortingList.subList(0, shouldHave2);
        }
        for (int m = 0; m < meatSortingList.size(); ++m) {
            meats.add(meatSortingList.get(m).orename);
        }
        int meattypes;
        int meatmultiplier;
        for (meattypes = meats.size() + Mincer.knownMeats.size(), meatmultiplier = 1; meattypes * meatmultiplier < 8; ++meatmultiplier) {}
        final int makesmeatballs = (int)Math.round(meattypes * meatmultiplier / 9.0);
        Mincer.meatballRecipe = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(LudicrousItems.cosmic_meatballs, makesmeatballs), new ItemStack(LudicrousItems.resource, 1, 2));
        final List<Object> meatballInputs = (List<Object>)Mincer.meatballRecipe.getInput();
        for (int i2 = 0; i2 < Mincer.knownMeats.size(); ++i2) {
            for (int j2 = 0; j2 < meatmultiplier; ++j2) {
                meatballInputs.add(Mincer.knownMeats.get(i2));
            }
        }
        for (int i2 = 0; i2 < meats.size(); ++i2) {
            for (int j2 = 0; j2 < meatmultiplier; ++j2) {
                meatballInputs.add(OreDictionary.getOres((String)meats.get(i2)));
            }
        }
    }
    
    private static boolean bannedCrop(final String crop) {
        for (final String ban : Mincer.forbiddenCropNames) {
            if (ban.equals(crop)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        sacredCropNames = new String[] { "cropWheat", "cropCarrot", "cropPotato", "cropApple", "cropMelon", "cropPumpkin", "cropCactus", "cropMushroomRed", "cropMushroomBrown", "cropCherry" };
        forbiddenCropNames = new String[] { "cropEdibleroot", "cropWhitemushroom", "cropBeet", "cropCotton", "cropPoppy", "cropTulipRed", "cropTulipWhite", "cropDaisy", "cropTulipPink", "cropAllium", "cropOrchid", "cropTulipOrange", "cropDandelion", "cropShroomRed", "cropShroomBrown", "cropFerranium", "cropAurigold", "cropDiamahlia", "cropLapender", "cropEmeryllis", "cropRedstodendron", "cropCuprosia", "cropPetinia", "cropPlombean", "cropSilverweed", "cropJaslumine", "cropNiccissus", "cropPlatiolus", "cropOsmonium", "cropSandPear", "cropCitron" };
        knownMeatEntries = new String[] { "ingotMeatRaw", "dustMeat", "rawMutton" };
        (Mincer.knownMeats = new ArrayList<ItemStack>()).add(new ItemStack(Items.field_151082_bd));
        Mincer.knownMeats.add(new ItemStack(Items.field_151076_bf));
        Mincer.knownMeats.add(new ItemStack(Items.field_151147_al));
        Mincer.knownMeats.add(new ItemStack(Items.field_151115_aP));
        OreDictionary.registerOre("cropCactus", new ItemStack(Blocks.field_150434_aF));
        OreDictionary.registerOre("cropMushroomRed", new ItemStack((Block)Blocks.field_150337_Q));
        OreDictionary.registerOre("cropMushroomBrown", new ItemStack((Block)Blocks.field_150338_P));
    }
    
    private static class FoodInfo
    {
        public final String orename;
        public final int count;
        
        public FoodInfo(final String orename, final int count) {
            this.orename = orename;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return this.orename + ": " + this.count;
        }
    }
}

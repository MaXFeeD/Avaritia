// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.item.crafting.IRecipe;
import cpw.mods.fml.common.Loader;
import fox.spiteful.avaritia.compat.Compat;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import fox.spiteful.avaritia.Config;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Grinder
{
    public static ShapelessOreRecipe catalyst;
    
    public static void artsAndCrafts() {
        OreDictionary.registerOre("blockCrystalMatrix", new ItemStack(LudicrousBlocks.crystal_matrix, 1, 0));
        OreDictionary.registerOre("blockCosmicNeutronium", new ItemStack(LudicrousBlocks.resource_block, 1, 0));
        OreDictionary.registerOre("blockInfinity", new ItemStack(LudicrousBlocks.resource_block, 1, 1));
        OreDictionary.registerOre("ingotCrystalMatrix", new ItemStack(LudicrousItems.resource, 1, 1));
        OreDictionary.registerOre("ingotCosmicNeutronium", new ItemStack(LudicrousItems.resource, 1, 4));
        OreDictionary.registerOre("ingotInfinity", new ItemStack(LudicrousItems.resource, 1, 6));
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 1, 0), new Object[] { "X X", " X ", "X X", 'X', new ItemStack(Items.field_151045_i) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 1, 1), new Object[] { "DSD", "DSD", 'D', new ItemStack(LudicrousItems.resource, 1, 0), 'S', new ItemStack(Items.field_151156_bN) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.double_craft, 1), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(Blocks.field_150462_ai) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.triple_craft, 1), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousBlocks.double_craft) });
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.field_150462_ai, 9), new Object[] { "C", 'C', new ItemStack(LudicrousBlocks.double_craft) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.double_craft, 9), new Object[] { "C", 'C', new ItemStack(LudicrousBlocks.triple_craft) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.dire_crafting, 1), new Object[] { "CCC", "CXC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 1), 'X', new ItemStack(LudicrousBlocks.triple_craft) });
        if (Config.craftingOnly) {
            return;
        }
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.crystal_matrix, 1, 0), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 1) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 9, 1), new Object[] { "C", 'C', new ItemStack(LudicrousBlocks.crystal_matrix, 1, 0) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 1, 3), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 2) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 1, 4), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 3) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 9, 3), new Object[] { "C", 'C', new ItemStack(LudicrousItems.resource, 1, 4) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 9, 2), new Object[] { "C", 'C', new ItemStack(LudicrousItems.resource, 1, 3) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.resource_block, 1, 0), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 4) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 9, 4), new Object[] { "C", 'C', new ItemStack(LudicrousBlocks.resource_block, 1, 0) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousBlocks.resource_block, 1, 1), new Object[] { "CCC", "CCC", "CCC", 'C', new ItemStack(LudicrousItems.resource, 1, 6) });
        GameRegistry.addShapedRecipe(new ItemStack(LudicrousItems.resource, 9, 6), new Object[] { "C", 'C', new ItemStack(LudicrousBlocks.resource_block, 1, 1) });
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousBlocks.neutron_collector, 1), "IIQQQQQII", "I QQQQQ I", "I  RRR  I", "X RRRRR X", "I RRXRR I", "X RRRRR X", "I  RRR  I", "I       I", "IIIXIXIII", 'X', new ItemStack(LudicrousItems.resource, 1, 1), 'I', new ItemStack(Blocks.field_150339_S, 1), 'Q', new ItemStack(Blocks.field_150371_ca, 1), 'R', new ItemStack(Blocks.field_150451_bX, 1));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.resource, 1, 6), "NNNNNNNNN", "NCXXCXXCN", "NXCCXCCXN", "NCXXCXXCN", "NNNNNNNNN", 'C', new ItemStack(LudicrousItems.resource, 1, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'X', new ItemStack(LudicrousItems.resource, 1, 5));
        Grinder.catalyst = ExtremeCraftingManager.getInstance().addShapelessOreRecipe(new ItemStack(LudicrousItems.resource, 1, 5), new ItemStack(Blocks.field_150475_bE, 1), new ItemStack(LudicrousItems.singularity, 1, 0), new ItemStack(LudicrousItems.singularity, 1, 1), new ItemStack(LudicrousItems.singularity, 1, 2), new ItemStack(LudicrousItems.singularity, 1, 3), new ItemStack(LudicrousItems.singularity, 1, 4), new ItemStack(LudicrousItems.ultimate_stew), new ItemStack(LudicrousItems.cosmic_meatballs), new ItemStack(LudicrousItems.endest_pearl));
        final ItemStack result = new ItemStack(LudicrousItems.infinity_pickaxe, 1);
        result.func_77966_a(Enchantment.field_77346_s, 10);
        ExtremeCraftingManager.getInstance().addRecipe(result, " IIIIIII ", "IIIICIIII", "II  N  II", "    N    ", "    N    ", "    N    ", "    N    ", "    N    ", "    N    ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'C', new ItemStack(LudicrousBlocks.crystal_matrix, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_sword), "       II", "      III", "     III ", "    III  ", " C III   ", "  CII    ", "  NC     ", " N  C    ", "X        ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'C', new ItemStack(LudicrousItems.resource, 1, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_shovel), "      III", "     IIXI", "      III", "     N I ", "    N    ", "   N     ", "  N      ", " N       ", "N        ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousBlocks.resource_block, 1, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_axe), " I   ", "IIIII", "IIII ", " IN  ", "  N  ", "  N  ", "  N  ", "  N  ", "  N  ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_bow), "   II", "  I W", " I  W", "I   W", "X   W", "I   W", " I  W", "  I W", "   II", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousBlocks.crystal_matrix, 1), 'W', new ItemStack(Blocks.field_150325_L, 1, 0));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_armor), " NN   NN ", "NNN   NNN", "NNN   NNN", " NIIIIIN ", " NIIXIIN ", " NIIIIIN ", " NIIIIIN ", " NIIIIIN ", "  NNNNN  ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousBlocks.crystal_matrix, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_helm), "  NNNNN  ", " NIIIIIN ", " N XIX N ", " NIIIIIN ", " NIIIIIN ", " NI I IN ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_pants), "NNNNNNNNN", "NIIIXIIIN", "NINNXNNIN", "NIN   NIN", "NCN   NCN", "NIN   NIN", "NIN   NIN", "NIN   NIN", "NNN   NNN", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'C', new ItemStack(LudicrousBlocks.crystal_matrix, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.infinity_shoes), " NNN NNN ", " NIN NIN ", " NIN NIN ", "NNIN NINN", "NIIN NIIN", "NNNN NNNN", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(LudicrousItems.skull_sword), "       IX", "      IXI", "     IXI ", "    IXI  ", " B IXI   ", "  BXI    ", "  WB     ", " W  B    ", "D        ", 'I', new ItemStack(LudicrousItems.resource, 1, 1), 'X', new ItemStack(Items.field_151065_br), 'B', new ItemStack(Items.field_151103_aS), 'D', new ItemStack(Items.field_151156_bN), 'W', "logWood");
        CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 0), 400, new ItemStack(Blocks.field_150339_S));
        CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 1), 200, new ItemStack(Blocks.field_150340_R, 1));
        CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 2), 400, new ItemStack(Blocks.field_150368_y, 1));
        CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 3), 500, new ItemStack(Blocks.field_150451_bX, 1));
        CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 4), 300, new ItemStack(Blocks.field_150371_ca, 1));
        if (Config.endStone) {
            ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(LudicrousItems.endest_pearl), "   EEE   ", " EEPPPEE ", " EPPPPPE ", "EPPPNPPPE", "EPPNSNPPE", "EPPPNPPPE", " EPPPPPE ", " EEPPPEE ", "   EEE   ", 'E', new ItemStack(Blocks.field_150377_bs), 'P', new ItemStack(Items.field_151079_bi), 'S', new ItemStack(Items.field_151156_bN), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        }
        else {
            ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(LudicrousItems.endest_pearl), "   EEE   ", " EEPPPEE ", " EPPPPPE ", "EPPPNPPPE", "EPPNSNPPE", "EPPPNPPPE", " EPPPPPE ", " EEPPPEE ", "   EEE   ", 'P', new ItemStack(Items.field_151079_bi), 'S', new ItemStack(Items.field_151156_bN), 'N', new ItemStack(LudicrousItems.resource, 1, 4));
        }
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousBlocks.compressor), "IIIHHHIII", "X N   N X", "I N   N I", "X N   N X", "RNN O NNR", "X N   N X", "I N   N I", "X N   N X", "IIIXIXIII", 'X', new ItemStack(LudicrousItems.resource, 1, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'I', new ItemStack(Blocks.field_150339_S, 1), 'H', new ItemStack((Block)Blocks.field_150438_bZ, 1), 'R', new ItemStack(Blocks.field_150451_bX, 1), 'O', new ItemStack(LudicrousBlocks.resource_block, 1, 0));
    }
    
    public static void lastMinuteChanges() {
        if (Compat.mfr) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("record"));
        }
        else {
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.resource, 1, 7));
            final IRecipe smashysmashy = (IRecipe)new ShapelessOreRecipe(new ItemStack(LudicrousItems.resource, 8, 7), new Object[] { "record" });
            GameRegistry.addRecipe(smashysmashy);
        }
        if (Config.copper && !OreDictionary.getOres("blockCopper").isEmpty()) {
            CompressorManager.addOreRecipe(new ItemStack(LudicrousItems.singularity, 1, 5), 400, "blockCopper");
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 5));
        }
        if (Config.tin && !OreDictionary.getOres("blockTin").isEmpty()) {
            CompressorManager.addOreRecipe(new ItemStack(LudicrousItems.singularity, 1, 6), 400, "blockTin");
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 6));
        }
        if (Config.lead && !OreDictionary.getOres("blockLead").isEmpty()) {
            CompressorManager.addOreRecipe(new ItemStack(LudicrousItems.singularity, 1, 7), 300, "blockLead");
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 7));
        }
        if (Config.silver && !OreDictionary.getOres("blockSilver").isEmpty()) {
            CompressorManager.addOreRecipe(new ItemStack(LudicrousItems.singularity, 1, 8), 300, "blockSilver");
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 8));
        }
        if (Config.nickel && !OreDictionary.getOres("blockNickel").isEmpty()) {
            CompressorManager.addOreRecipe(new ItemStack(LudicrousItems.singularity, 1, 9), 400, "blockNickel");
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 9));
        }
        if (Config.te && !OreDictionary.getOres("blockEnderium").isEmpty()) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("blockEnderium"));
        }
        if (Config.steel && !OreDictionary.getOres("blockSteel").isEmpty()) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("blockSteel"));
        }
        if (Config.metallurgy && !OreDictionary.getOres("ingotTartarite").isEmpty()) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("ingotTartarite"));
        }
        if (Config.numanuma && !OreDictionary.getOres("blockIronCompressed").isEmpty()) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("blockIronCompressed"));
        }
        if (Config.enderio && !OreDictionary.getOres("blockDarkSteel").isEmpty()) {
            Grinder.catalyst.getInput().add(OreDictionary.getOres("blockDarkSteel"));
        }
        if (Config.ultimateBalance && (Loader.isModLoaded("Botania") || Loader.isModLoaded("Mekanism"))) {
            Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.singularity, 1, 10));
            CompressorManager.addRecipe(new ItemStack(LudicrousItems.singularity, 1, 10), 150, new ItemStack(Items.field_151119_aD, 1));
        }
    }
}

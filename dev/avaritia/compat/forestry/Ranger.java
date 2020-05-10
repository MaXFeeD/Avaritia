// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.util.EnumChatFormatting;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import forestry.api.recipes.RecipeManagers;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.Config;
import fox.spiteful.avaritia.compat.Compat;
import cpw.mods.fml.common.Loader;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;

public class Ranger
{
    public static Item honey;
    public static Item honeydew;
    public static Item comb;
    public static boolean magic;
    public static boolean extra;
    public static EnumRarity trash;
    
    public static void stopForestFires() throws Compat.ItemNotFoundException {
        Ranger.magic = Loader.isModLoaded("MagicBees");
        Ranger.extra = Loader.isModLoaded("ExtraBees");
        Ranger.comb = Compat.getItem("Forestry", "beeCombs");
        Ranger.honey = Compat.getItem("Forestry", "honeyDrop");
        Ranger.honeydew = Compat.getItem("Forestry", "honeydew");
        final Item panel = Compat.getItem("Forestry", "craftingMaterial");
        if (!Config.bees) {
            return;
        }
        GameRegistry.registerItem(LudicrousItems.combs = new ItemComb(), "Combs");
        GameRegistry.registerItem(LudicrousItems.beesource = new ItemBeesource(), "Beesource");
        Allele.prepareGenes();
        GreedyBeeSpecies.buzz();
        ExpensiveMutation.mutate();
        RecipeManagers.centrifugeManager.addRecipe(20, new ItemStack(LudicrousItems.combs, 1, 1), new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151100_aR, 1, 2), new ItemStack(Items.field_151100_aR, 1, 4), new ItemStack(Items.field_151100_aR, 1, 5), new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151100_aR, 1, 14) }, new int[] { 16, 16, 16, 16, 16, 16 });
        RecipeManagers.centrifugeManager.addRecipe(20, new ItemStack(LudicrousItems.combs, 1, 0), new ItemStack(LudicrousItems.beesource, 1, 1));
        Grinder.catalyst.getInput().add(new ItemStack(panel, 1, 6));
        Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.beesource, 1, 0));
    }
    
    static {
        Ranger.magic = false;
        Ranger.extra = false;
        Ranger.trash = EnumHelper.addRarity("TRASH", EnumChatFormatting.DARK_GRAY, "Trash");
    }
}

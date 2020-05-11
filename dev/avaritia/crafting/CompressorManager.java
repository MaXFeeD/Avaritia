// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;

public class CompressorManager
{
    private static ArrayList<CompressorRecipe> recipes;
    
    public static void addRecipe(final ItemStack output, final int amount, final ItemStack input) {
        CompressorManager.recipes.add(new CompressorRecipe(output, amount, input));
    }
    
    public static void addOreRecipe(final ItemStack output, final int amount, final String ore) {
        CompressorManager.recipes.add(new CompressOreRecipe(output, amount, ore));
    }
    
    public static ItemStack getOutput(final ItemStack input) {
        for (final CompressorRecipe recipe : CompressorManager.recipes) {
            if (recipe.validInput(input)) {
                return recipe.getOutput();
            }
        }
        return null;
    }
    
    public static int getCost(final ItemStack input) {
        if (input == null) {
            return 0;
        }
        for (final CompressorRecipe recipe : CompressorManager.recipes) {
            if (recipe.validInput(input)) {
                return recipe.getCost();
            }
        }
        return 0;
    }
    
    public static int getPrice(final ItemStack output) {
        if (output == null) {
            return 0;
        }
        for (final CompressorRecipe recipe : CompressorManager.recipes) {
            if (recipe.getOutput().func_77969_a(output)) {
                return recipe.getCost();
            }
        }
        return 0;
    }
    
    public static String getName(final ItemStack input) {
        for (final CompressorRecipe recipe : CompressorManager.recipes) {
            if (recipe.validInput(input)) {
                return recipe.getIngredientName();
            }
        }
        return null;
    }
    
    public static ArrayList<CompressorRecipe> getRecipes() {
        return CompressorManager.recipes;
    }
    
    static {
        CompressorManager.recipes = new ArrayList<CompressorRecipe>();
    }
}

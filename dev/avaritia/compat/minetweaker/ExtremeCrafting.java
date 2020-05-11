// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.minetweaker;

import java.util.List;
import fox.spiteful.avaritia.crafting.Grinder;
import java.util.Iterator;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.crafting.ExtremeShapedOreRecipe;
import stanhebben.zenscript.annotations.ZenMethod;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass("mods.avaritia.ExtremeCrafting")
public class ExtremeCrafting
{
    @ZenMethod
    public static void addShapeless(final IItemStack output, final IIngredient[] ingredients) {
        MineTweakerAPI.apply((IUndoableAction)new Add((IRecipe)new ShapelessOreRecipe(toStack(output), toObjects(ingredients))));
    }
    
    @ZenMethod
    public static void addShaped(final IItemStack output, final IIngredient[][] ingredients) {
        final int height = ingredients.length;
        int width = 0;
        for (final IIngredient[] row : ingredients) {
            if (width < row.length) {
                width = row.length;
            }
        }
        final Object[] input = new Object[width * height];
        int x = 0;
        for (final IIngredient[] array : ingredients) {
            final IIngredient[] row2 = array;
            for (final IIngredient ingredient : array) {
                input[x++] = toActualObject(ingredient);
            }
        }
        MineTweakerAPI.apply((IUndoableAction)new Add((IRecipe)new ExtremeShapedOreRecipe(toStack(output), input, width, height)));
    }
    
    @ZenMethod
    public static void remove(final IItemStack target) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(toStack(target)));
    }
    
    private static ItemStack toStack(final IItemStack item) {
        if (item == null) {
            return null;
        }
        final Object internal = item.getInternal();
        if (internal == null || !(internal instanceof ItemStack)) {
            MineTweakerAPI.getLogger().logError("Not a valid item stack: " + item);
        }
        return (ItemStack)internal;
    }
    
    private static Object toObject(final IIngredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        if (ingredient instanceof IOreDictEntry) {
            return toString((IOreDictEntry)ingredient);
        }
        if (ingredient instanceof IItemStack) {
            return toStack((IItemStack)ingredient);
        }
        return null;
    }
    
    private static Object[] toObjects(final IIngredient[] list) {
        if (list == null) {
            return null;
        }
        final Object[] ingredients = new Object[list.length];
        for (int x = 0; x < list.length; ++x) {
            ingredients[x] = toObject(list[x]);
        }
        return ingredients;
    }
    
    private static Object toActualObject(final IIngredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        if (ingredient instanceof IOreDictEntry) {
            return OreDictionary.getOres(toString((IOreDictEntry)ingredient));
        }
        if (ingredient instanceof IItemStack) {
            return toStack((IItemStack)ingredient);
        }
        return null;
    }
    
    private static String toString(final IOreDictEntry entry) {
        return entry.getName();
    }
    
    private static class Add implements IUndoableAction
    {
        IRecipe recipe;
        
        public Add(final IRecipe add) {
            this.recipe = add;
        }
        
        public void apply() {
            ExtremeCraftingManager.getInstance().getRecipeList().add(this.recipe);
        }
        
        public boolean canUndo() {
            return true;
        }
        
        public void undo() {
            ExtremeCraftingManager.getInstance().getRecipeList().remove(this.recipe);
        }
        
        public String describe() {
            return "Adding Xtreme Crafting Recipe for " + this.recipe.func_77571_b().func_82833_r();
        }
        
        public String describeUndo() {
            return "Un-adding Xtreme Crafting Recipe for " + this.recipe.func_77571_b().func_82833_r();
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
    
    private static class Remove implements IUndoableAction
    {
        IRecipe recipe;
        ItemStack remove;
        
        public Remove(final ItemStack rem) {
            this.recipe = null;
            this.remove = rem;
        }
        
        public void apply() {
            for (final Object obj : ExtremeCraftingManager.getInstance().getRecipeList()) {
                if (obj instanceof IRecipe) {
                    final IRecipe craft = (IRecipe)obj;
                    if (craft.func_77571_b().func_77969_a(this.remove)) {
                        this.recipe = craft;
                        ExtremeCraftingManager.getInstance().getRecipeList().remove(obj);
                        break;
                    }
                    continue;
                }
            }
        }
        
        public boolean canUndo() {
            return this.recipe != null;
        }
        
        public void undo() {
            ExtremeCraftingManager.getInstance().getRecipeList().add(this.recipe);
        }
        
        public String describe() {
            return "Removing Xtreme Crafting Recipe for " + this.remove.func_82833_r();
        }
        
        public String describeUndo() {
            return "Un-removing Xtreme Crafting Recipe for " + this.remove.func_82833_r();
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
    
    private static class AddCatalyst implements IUndoableAction
    {
        Object ingredient;
        
        public AddCatalyst(final Object add) {
            this.ingredient = add;
        }
        
        public void apply() {
            Grinder.catalyst.getInput().add(this.ingredient);
        }
        
        public boolean canUndo() {
            return true;
        }
        
        public void undo() {
            Grinder.catalyst.getInput().remove(this.ingredient);
        }
        
        public String describe() {
            if (this.ingredient instanceof ItemStack) {
                return "Adding " + ((ItemStack)this.ingredient).func_82833_r() + " to Infinity Catalyst recipe.";
            }
            if (this.ingredient instanceof List) {
                return "Adding " + ((List)this.ingredient).get(0).func_82833_r() + " to Infinity Catalyst recipe.";
            }
            return "Adding something to Infinity Catalyst recipe.";
        }
        
        public String describeUndo() {
            if (this.ingredient instanceof ItemStack) {
                return "Removing " + ((ItemStack)this.ingredient).func_82833_r() + " from Infinity Catalyst recipe.";
            }
            if (this.ingredient instanceof List) {
                return "Removing " + ((List)this.ingredient).get(0).func_82833_r() + " from Infinity Catalyst recipe.";
            }
            return "Removing something from Infinity Catalyst recipe.";
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
    
    private static class RemoveCatalyst implements IUndoableAction
    {
        IIngredient ingredient;
        
        public RemoveCatalyst(final IIngredient rem) {
            this.ingredient = rem;
        }
        
        public void apply() {
            Grinder.catalyst.getInput().add(this.ingredient);
            final Iterator<Object> iterator = Grinder.catalyst.getInput().iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
        }
        
        public boolean canUndo() {
            return true;
        }
        
        public void undo() {
            Grinder.catalyst.getInput().remove(this.ingredient);
        }
        
        public String describe() {
            return "Adding something to Infinity Catalyst recipe.";
        }
        
        public String describeUndo() {
            return "Removing something from Infinity Catalyst recipe.";
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.minetweaker;

import java.util.Iterator;
import fox.spiteful.avaritia.crafting.CompressorManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenMethod;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import fox.spiteful.avaritia.crafting.CompressorRecipe;
import fox.spiteful.avaritia.crafting.CompressOreRecipe;
import minetweaker.api.oredict.IOreDictEntry;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;

@ZenClass("mods.avaritia.Compressor")
public class Compressor
{
    @ZenMethod
    public static void add(final IItemStack output, final int amount, final IIngredient input, final boolean exact) {
        CompressorRecipe recipe = null;
        if (input instanceof IOreDictEntry) {
            recipe = new CompressOreRecipe(toStack(output), amount, toString((IOreDictEntry)input), exact);
        }
        else if (input instanceof IItemStack) {
            recipe = new CompressorRecipe(toStack(output), amount, toStack((IItemStack)input), exact);
        }
        if (recipe != null) {
            MineTweakerAPI.apply((IUndoableAction)new Add(recipe));
        }
    }
    
    @ZenMethod
    public static void add(final IItemStack output, final int amount, final IIngredient input) {
        add(output, amount, input, true);
    }
    
    @ZenMethod
    public static void remove(final IItemStack output) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(toStack(output)));
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
        CompressorRecipe recipe;
        
        public Add(final CompressorRecipe add) {
            this.recipe = add;
        }
        
        public void apply() {
            CompressorManager.getRecipes().add(this.recipe);
        }
        
        public boolean canUndo() {
            return true;
        }
        
        public void undo() {
            CompressorManager.getRecipes().remove(this.recipe);
        }
        
        public String describe() {
            return "Adding Compressor Recipe for " + this.recipe.getOutput().func_82833_r();
        }
        
        public String describeUndo() {
            return "Un-adding Compressor Recipe for " + this.recipe.getOutput().func_82833_r();
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
    
    private static class Remove implements IUndoableAction
    {
        CompressorRecipe recipe;
        ItemStack remove;
        
        public Remove(final ItemStack rem) {
            this.recipe = null;
            this.remove = rem;
        }
        
        public void apply() {
            for (final Object obj : CompressorManager.getRecipes()) {
                if (obj instanceof CompressorRecipe) {
                    final CompressorRecipe craft = (CompressorRecipe)obj;
                    if (craft.getOutput().func_77969_a(this.remove)) {
                        this.recipe = craft;
                        CompressorManager.getRecipes().remove(obj);
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
            CompressorManager.getRecipes().add(this.recipe);
        }
        
        public String describe() {
            return "Removing Compressor Recipe for " + this.remove.func_82833_r();
        }
        
        public String describeUndo() {
            return "Un-removing Compressor Recipe for " + this.remove.func_82833_r();
        }
        
        public Object getOverrideKey() {
            return null;
        }
    }
}

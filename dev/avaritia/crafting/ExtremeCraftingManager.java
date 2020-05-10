// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraft.item.crafting.IRecipe;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import java.util.HashMap;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class ExtremeCraftingManager
{
    private static final ExtremeCraftingManager instance;
    private List recipes;
    
    public ExtremeCraftingManager() {
        this.recipes = new ArrayList();
    }
    
    public static final ExtremeCraftingManager getInstance() {
        return ExtremeCraftingManager.instance;
    }
    
    public ExtremeShapedRecipe addRecipe(final ItemStack result, final Object... recipe) {
        String s = "";
        int i = 0;
        int width = 0;
        int height = 0;
        if (recipe[i] instanceof String[]) {
            final String[] astring = (String[])recipe[i++];
            for (int l = 0; l < astring.length; ++l) {
                final String s2 = astring[l];
                ++height;
                width = s2.length();
                s += s2;
            }
        }
        else {
            while (recipe[i] instanceof String) {
                final String s3 = (String)recipe[i++];
                ++height;
                width = s3.length();
                s += s3;
            }
        }
        final HashMap hashmap = new HashMap();
        while (i < recipe.length) {
            final Character character = (Character)recipe[i];
            ItemStack itemstack1 = null;
            if (recipe[i + 1] instanceof Item) {
                itemstack1 = new ItemStack((Item)recipe[i + 1]);
            }
            else if (recipe[i + 1] instanceof Block) {
                itemstack1 = new ItemStack((Block)recipe[i + 1], 1, 32767);
            }
            else if (recipe[i + 1] instanceof ItemStack) {
                itemstack1 = (ItemStack)recipe[i + 1];
            }
            hashmap.put(character, itemstack1);
            i += 2;
        }
        final ItemStack[] ingredients = new ItemStack[width * height];
        for (int i2 = 0; i2 < width * height; ++i2) {
            final char c0 = s.charAt(i2);
            if (hashmap.containsKey(c0)) {
                ingredients[i2] = hashmap.get(c0).func_77946_l();
            }
            else {
                ingredients[i2] = null;
            }
        }
        final ExtremeShapedRecipe shapedrecipes = new ExtremeShapedRecipe(width, height, ingredients, result);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }
    
    public ExtremeShapedOreRecipe addExtremeShapedOreRecipe(final ItemStack result, final Object... recipe) {
        final ExtremeShapedOreRecipe craft = new ExtremeShapedOreRecipe(result, recipe);
        this.recipes.add(craft);
        return craft;
    }
    
    public ExtremeShapedRecipe addSingularityRecipe(final ItemStack result, final ItemStack single) {
        return this.addRecipe(result, "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIINIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", 'I', single, 'N', new ItemStack(LudicrousBlocks.resource_block, 1, 0));
    }
    
    public void addOreSingularityRecipe(final ItemStack result, final String ore) {
        final IRecipe recipe = (IRecipe)new ExtremeShapedOreRecipe(result, new Object[] { "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIINIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", 'I', ore, 'N', new ItemStack(LudicrousBlocks.resource_block, 1, 0) });
        this.recipes.add(recipe);
    }
    
    public ExtremeShapelessRecipe addShapelessRecipe(final ItemStack result, final Object... ingredients) {
        final ArrayList arraylist = new ArrayList();
        final Object[] aobject = ingredients;
        for (int i = ingredients.length, j = 0; j < i; ++j) {
            final Object object1 = aobject[j];
            if (object1 instanceof ItemStack) {
                arraylist.add(((ItemStack)object1).func_77946_l());
            }
            else if (object1 instanceof Item) {
                arraylist.add(new ItemStack((Item)object1));
            }
            else {
                if (!(object1 instanceof Block)) {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }
                arraylist.add(new ItemStack((Block)object1));
            }
        }
        final ExtremeShapelessRecipe recipe = new ExtremeShapelessRecipe(result, arraylist);
        this.recipes.add(recipe);
        return recipe;
    }
    
    public ShapelessOreRecipe addShapelessOreRecipe(final ItemStack result, final Object... ingredients) {
        final ShapelessOreRecipe recipe = new ShapelessOreRecipe(result, ingredients);
        this.recipes.add(recipe);
        return recipe;
    }
    
    public ItemStack findMatchingRecipe(final InventoryCrafting matrix, final World world) {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack2 = null;
        for (int j = 0; j < matrix.func_70302_i_(); ++j) {
            final ItemStack itemstack3 = matrix.func_70301_a(j);
            if (itemstack3 != null) {
                if (i == 0) {
                    itemstack = itemstack3;
                }
                if (i == 1) {
                    itemstack2 = itemstack3;
                }
                ++i;
            }
        }
        if (i == 2 && itemstack.func_77973_b() == itemstack2.func_77973_b() && itemstack.field_77994_a == 1 && itemstack2.field_77994_a == 1 && itemstack.func_77973_b().isRepairable()) {
            final Item item = itemstack.func_77973_b();
            final int j2 = item.func_77612_l() - itemstack.func_77952_i();
            final int k = item.func_77612_l() - itemstack2.func_77952_i();
            final int l = j2 + k + item.func_77612_l() * 5 / 100;
            int i2 = item.func_77612_l() - l;
            if (i2 < 0) {
                i2 = 0;
            }
            return new ItemStack(itemstack.func_77973_b(), 1, i2);
        }
        for (int j = 0; j < this.recipes.size(); ++j) {
            final IRecipe irecipe = this.recipes.get(j);
            if (irecipe.func_77569_a(matrix, world)) {
                return irecipe.func_77572_b(matrix);
            }
        }
        return null;
    }
    
    public List getRecipeList() {
        return this.recipes;
    }
    
    static {
        instance = new ExtremeCraftingManager();
    }
}

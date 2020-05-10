// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.nei;

import java.util.Arrays;
import codechicken.nei.PositionedStack;
import fox.spiteful.avaritia.gui.GUIExtremeCrafting;
import codechicken.lib.gui.GuiDraw;
import org.lwjgl.opengl.GL11;
import codechicken.nei.recipe.RecipeInfo;
import net.minecraft.inventory.Container;
import net.minecraft.client.gui.inventory.GuiContainer;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import codechicken.nei.NEIServerUtils;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import java.util.List;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapelessRecipe;
import net.minecraft.item.crafting.IRecipe;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import net.minecraft.util.StatCollector;
import codechicken.nei.recipe.ShapelessRecipeHandler;

public class ExtremeShapelessRecipeHandler extends ShapelessRecipeHandler
{
    public String getRecipeName() {
        return StatCollector.func_74838_a("crafting.extreme.shapeless");
    }
    
    public void loadCraftingRecipes(final String outputId, final Object... results) {
        if (outputId.equals("extreme") && this.getClass() == ExtremeShapelessRecipeHandler.class) {
            final List<IRecipe> allrecipes = (List<IRecipe>)ExtremeCraftingManager.getInstance().getRecipeList();
            for (final IRecipe irecipe : allrecipes) {
                CachedExtremeShapelessRecipe recipe = null;
                if (irecipe instanceof ExtremeShapelessRecipe) {
                    recipe = this.shapelessRecipe((ExtremeShapelessRecipe)irecipe);
                }
                else if (irecipe instanceof ShapelessOreRecipe) {
                    recipe = this.forgeExtremeShapelessRecipe((ShapelessOreRecipe)irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                this.arecipes.add(recipe);
            }
        }
        else {
            super.loadCraftingRecipes(outputId, results);
        }
    }
    
    public void loadCraftingRecipes(final ItemStack result) {
        final List<IRecipe> allrecipes = (List<IRecipe>)ExtremeCraftingManager.getInstance().getRecipeList();
        for (final IRecipe irecipe : allrecipes) {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.func_77571_b(), result)) {
                CachedExtremeShapelessRecipe recipe = null;
                if (irecipe instanceof ExtremeShapelessRecipe) {
                    recipe = this.shapelessRecipe((ExtremeShapelessRecipe)irecipe);
                }
                else if (irecipe instanceof ShapelessOreRecipe) {
                    recipe = this.forgeExtremeShapelessRecipe((ShapelessOreRecipe)irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                this.arecipes.add(recipe);
            }
        }
    }
    
    public void loadUsageRecipes(final ItemStack ingredient) {
        final List<IRecipe> allrecipes = (List<IRecipe>)ExtremeCraftingManager.getInstance().getRecipeList();
        for (final IRecipe irecipe : allrecipes) {
            CachedExtremeShapelessRecipe recipe = null;
            if (irecipe instanceof ExtremeShapelessRecipe) {
                recipe = this.shapelessRecipe((ExtremeShapelessRecipe)irecipe);
            }
            else if (irecipe instanceof ShapelessOreRecipe) {
                recipe = this.forgeExtremeShapelessRecipe((ShapelessOreRecipe)irecipe);
            }
            if (recipe == null) {
                continue;
            }
            if (!recipe.contains((Collection)recipe.ingredients, ingredient)) {
                continue;
            }
            recipe.setIngredientPermutation((Collection)recipe.ingredients, ingredient);
            this.arecipes.add(recipe);
        }
    }
    
    private CachedExtremeShapelessRecipe shapelessRecipe(final ExtremeShapelessRecipe recipe) {
        if (recipe.recipeItems == null) {
            return null;
        }
        return new CachedExtremeShapelessRecipe(recipe.recipeItems, recipe.func_77571_b());
    }
    
    public CachedExtremeShapelessRecipe forgeExtremeShapelessRecipe(final ShapelessOreRecipe recipe) {
        final ArrayList<Object> items = (ArrayList<Object>)recipe.getInput();
        for (final Object item : items) {
            if (item instanceof List && ((List)item).isEmpty()) {
                return null;
            }
        }
        return new CachedExtremeShapelessRecipe(items, recipe.func_77571_b());
    }
    
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(166, 74, 24, 18), "extreme", new Object[0]));
    }
    
    public String getOverlayIdentifier() {
        return "extreme";
    }
    
    public String getGuiTexture() {
        return "avaritia:textures/gui/extreme_nei.png";
    }
    
    public boolean hasOverlay(final GuiContainer gui, final Container container, final int recipe) {
        return RecipeInfo.hasDefaultOverlay(gui, "extreme");
    }
    
    public void drawBackground(final int recipe) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GuiDraw.changeTexture(this.getGuiTexture());
        GuiDraw.drawTexturedModalRect(-9, -20, 0, 0, 256, 208);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
    }
    
    public int recipiesPerPage() {
        return 1;
    }
    
    public Class<? extends GuiContainer> getGuiClass() {
        return GUIExtremeCrafting.class;
    }
    
    public class CachedExtremeShapelessRecipe extends TemplateRecipeHandler.CachedRecipe
    {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;
        
        public CachedExtremeShapelessRecipe() {
            super((TemplateRecipeHandler)ExtremeShapelessRecipeHandler.this);
            this.ingredients = new ArrayList<PositionedStack>();
        }
        
        public CachedExtremeShapelessRecipe(final ExtremeShapelessRecipeHandler this$0, final ItemStack output) {
            this(this$0);
            this.setResult(output);
        }
        
        public CachedExtremeShapelessRecipe(final ExtremeShapelessRecipeHandler this$0, final Object[] input, final ItemStack output) {
            this(Arrays.asList(input), output);
        }
        
        public CachedExtremeShapelessRecipe(final ExtremeShapelessRecipeHandler this$0, final List<?> input, final ItemStack output) {
            this(this$0, output);
            this.setIngredients(input);
        }
        
        public void setIngredients(final List<?> items) {
            this.ingredients.clear();
            for (int ingred = 0; ingred < items.size(); ++ingred) {
                int ex = 3 + ingred % 9 * 18;
                final int wy = 3 + ingred / 9 * 18;
                if (wy == 129) {
                    if (ex == 3 || ex == 129) {
                        --ex;
                    }
                    else if (ex == 21 || ex == 147) {
                        ++ex;
                    }
                }
                final PositionedStack stack = new PositionedStack((Object)items.get(ingred), ex, wy);
                stack.setMaxSize(1);
                this.ingredients.add(stack);
            }
        }
        
        public void setResult(final ItemStack output) {
            this.result = new PositionedStack((Object)output, 201, 75);
        }
        
        public List<PositionedStack> getIngredients() {
            return (List<PositionedStack>)this.getCycledIngredients(ExtremeShapelessRecipeHandler.this.cycleticks / 20, (List)this.ingredients);
        }
        
        public PositionedStack getResult() {
            return this.result;
        }
    }
}

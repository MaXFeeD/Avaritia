// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.nei;

import codechicken.nei.PositionedStack;
import java.util.ArrayList;
import codechicken.lib.gui.GuiDraw;
import org.lwjgl.opengl.GL11;
import codechicken.nei.recipe.RecipeInfo;
import net.minecraft.inventory.Container;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.List;
import java.util.Collection;
import codechicken.nei.NEIServerUtils;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import fox.spiteful.avaritia.crafting.ExtremeShapedOreRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapedRecipe;
import net.minecraft.item.crafting.IRecipe;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import net.minecraft.util.StatCollector;
import fox.spiteful.avaritia.gui.GUIExtremeCrafting;
import net.minecraft.client.gui.inventory.GuiContainer;
import codechicken.nei.recipe.ShapedRecipeHandler;

public class ExtremeShapedRecipeHandler extends ShapedRecipeHandler
{
    public int recipiesPerPage() {
        return 1;
    }
    
    public Class<? extends GuiContainer> getGuiClass() {
        return GUIExtremeCrafting.class;
    }
    
    public String getRecipeName() {
        return StatCollector.func_74838_a("crafting.extreme");
    }
    
    public void loadCraftingRecipes(final String outputId, final Object... results) {
        if (outputId.equals("extreme") && this.getClass() == ExtremeShapedRecipeHandler.class) {
            for (final IRecipe irecipe : ExtremeCraftingManager.getInstance().getRecipeList()) {
                CachedExtremeRecipe recipe = null;
                if (irecipe instanceof ExtremeShapedRecipe) {
                    recipe = new CachedExtremeRecipe((ExtremeShapedRecipe)irecipe);
                }
                else if (irecipe instanceof ExtremeShapedOreRecipe) {
                    recipe = this.forgeExtremeShapedRecipe((ExtremeShapedOreRecipe)irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                recipe.computeVisuals();
                this.arecipes.add(recipe);
            }
        }
        else {
            super.loadCraftingRecipes(outputId, results);
        }
    }
    
    public void loadCraftingRecipes(final ItemStack result) {
        for (final IRecipe irecipe : ExtremeCraftingManager.getInstance().getRecipeList()) {
            if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.func_77571_b(), result)) {
                CachedExtremeRecipe recipe = null;
                if (irecipe instanceof ExtremeShapedRecipe) {
                    recipe = new CachedExtremeRecipe((ExtremeShapedRecipe)irecipe);
                }
                else if (irecipe instanceof ExtremeShapedOreRecipe) {
                    recipe = this.forgeExtremeShapedRecipe((ExtremeShapedOreRecipe)irecipe);
                }
                if (recipe == null) {
                    continue;
                }
                recipe.computeVisuals();
                this.arecipes.add(recipe);
            }
        }
    }
    
    public void loadUsageRecipes(final ItemStack ingredient) {
        for (final IRecipe irecipe : ExtremeCraftingManager.getInstance().getRecipeList()) {
            CachedExtremeRecipe recipe = null;
            if (irecipe instanceof ExtremeShapedRecipe) {
                recipe = new CachedExtremeRecipe((ExtremeShapedRecipe)irecipe);
            }
            else if (irecipe instanceof ExtremeShapedOreRecipe) {
                recipe = this.forgeExtremeShapedRecipe((ExtremeShapedOreRecipe)irecipe);
            }
            if (recipe != null) {
                if (!recipe.contains((Collection)recipe.ingredients, ingredient.func_77973_b())) {
                    continue;
                }
                recipe.computeVisuals();
                if (!recipe.contains((Collection)recipe.ingredients, ingredient)) {
                    continue;
                }
                recipe.setIngredientPermutation((Collection)recipe.ingredients, ingredient);
                this.arecipes.add(recipe);
            }
        }
    }
    
    public CachedExtremeRecipe forgeExtremeShapedRecipe(final ExtremeShapedOreRecipe recipe) {
        final int width = recipe.width;
        final int height = recipe.height;
        final Object[] input;
        final Object[] items = input = recipe.getInput();
        for (final Object item : input) {
            if (item instanceof List && ((List)item).isEmpty()) {
                return null;
            }
        }
        return new CachedExtremeRecipe(width, height, items, recipe.func_77571_b());
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
    
    public class CachedExtremeRecipe extends TemplateRecipeHandler.CachedRecipe
    {
        public ArrayList<PositionedStack> ingredients;
        public PositionedStack result;
        
        public CachedExtremeRecipe(final ExtremeShapedRecipeHandler this$0, final ExtremeShapedRecipe recipe) {
            this(this$0, recipe.recipeWidth, recipe.recipeHeight, recipe.recipeItems, recipe.func_77571_b());
        }
        
        public CachedExtremeRecipe(final int width, final int height, final Object[] items, final ItemStack out) {
            super((TemplateRecipeHandler)ExtremeShapedRecipeHandler.this);
            this.result = new PositionedStack((Object)out, 201, 75);
            this.ingredients = new ArrayList<PositionedStack>();
            this.setIngredients(width, height, items);
        }
        
        public void setIngredients(final int width, final int height, final Object[] items) {
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    if (items[y * width + x] != null) {
                        int ex = 3 + x * 18;
                        final int wy = 3 + y * 18;
                        if (wy == 129) {
                            if (ex == 3 || ex == 129) {
                                --ex;
                            }
                            else if (ex == 21 || ex == 147) {
                                ++ex;
                            }
                        }
                        final PositionedStack stack = new PositionedStack(items[y * width + x], ex, wy);
                        stack.setMaxSize(1);
                        this.ingredients.add(stack);
                    }
                }
            }
        }
        
        public ArrayList<PositionedStack> getIngredients() {
            return (ArrayList<PositionedStack>)this.getCycledIngredients(ExtremeShapedRecipeHandler.this.cycleticks / 20, (List)this.ingredients);
        }
        
        public PositionedStack getResult() {
            return this.result;
        }
        
        public void computeVisuals() {
            for (final PositionedStack p : this.ingredients) {
                p.generatePermutations();
            }
        }
    }
}

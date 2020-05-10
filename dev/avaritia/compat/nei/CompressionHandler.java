// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.nei;

import java.util.Arrays;
import java.util.List;
import codechicken.nei.PositionedStack;
import java.util.ArrayList;
import fox.spiteful.avaritia.crafting.CompressOreRecipe;
import codechicken.nei.NEIServerUtils;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import fox.spiteful.avaritia.crafting.CompressorRecipe;
import fox.spiteful.avaritia.crafting.CompressorManager;
import net.minecraft.util.StatCollector;
import fox.spiteful.avaritia.gui.GUICompressor;
import net.minecraft.client.gui.inventory.GuiContainer;
import java.awt.Rectangle;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CompressionHandler extends TemplateRecipeHandler
{
    @SideOnly(Side.CLIENT)
    private FontRenderer fontRender;
    
    @SideOnly(Side.CLIENT)
    public CompressionHandler() {
        this.fontRender = Minecraft.func_71410_x().field_71466_p;
    }
    
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 23, 24, 18), "extreme_compression", new Object[0]));
    }
    
    public Class<? extends GuiContainer> getGuiClass() {
        return GUICompressor.class;
    }
    
    public String getRecipeName() {
        return StatCollector.func_74838_a("crafting.extreme_compression");
    }
    
    public void loadCraftingRecipes(final String outputId, final Object... results) {
        if (outputId.equals("extreme_compression") && this.getClass() == CompressionHandler.class) {
            for (final CompressorRecipe recipe : CompressorManager.getRecipes()) {
                if (this.safeOre(recipe)) {
                    final CachedCompression r = new CachedCompression(recipe);
                    r.computeVisuals();
                    this.arecipes.add(r);
                }
            }
        }
        else {
            super.loadCraftingRecipes(outputId, results);
        }
    }
    
    public void loadCraftingRecipes(final ItemStack result) {
        for (final CompressorRecipe recipe : CompressorManager.getRecipes()) {
            if (this.safeOre(recipe) && NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
                final CachedCompression r = new CachedCompression(recipe);
                r.computeVisuals();
                this.arecipes.add(r);
            }
        }
    }
    
    public void loadUsageRecipes(final String inputId, final Object... ingredients) {
        if (inputId.equals("extreme_compression") && this.getClass() == CompressionHandler.class) {
            this.loadCraftingRecipes("extreme_compression", new Object[0]);
        }
        else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }
    
    public void loadUsageRecipes(final ItemStack ingredient) {
        for (final CompressorRecipe recipe : CompressorManager.getRecipes()) {
            if (this.safeOre(recipe) && recipe.validInput(ingredient)) {
                final CachedCompression r = new CachedCompression(recipe.getOutput(), recipe.getCost(), ingredient);
                this.arecipes.add(r);
            }
        }
    }
    
    private boolean safeOre(final CompressorRecipe recipe) {
        return !(recipe instanceof CompressOreRecipe) || !((ArrayList)recipe.getIngredient()).isEmpty();
    }
    
    public String getGuiTexture() {
        return "avaritia:textures/gui/compressor.png";
    }
    
    @SideOnly(Side.CLIENT)
    public void drawForeground(final int recipe) {
        super.drawForeground(recipe);
        this.fontRender.func_78276_b(this.arecipes.get(recipe).getCost() + "", 50, 38, 4210752);
    }
    
    public void drawExtras(final int recipe) {
        this.drawProgressBar(74, 15, 176, 14, 24, 16, 48, 0);
    }
    
    public String getOverlayIdentifier() {
        return "extreme_compression";
    }
    
    public class CachedCompression extends TemplateRecipeHandler.CachedRecipe
    {
        private int cost;
        PositionedStack ingred;
        PositionedStack result;
        
        public CachedCompression(final CompressorRecipe recipe) {
            super((TemplateRecipeHandler)CompressionHandler.this);
            this.ingred = new PositionedStack(recipe.getIngredient(), 51, 16);
            this.result = new PositionedStack((Object)recipe.getOutput(), 111, 16);
            this.cost = recipe.getCost();
        }
        
        public CachedCompression(final ItemStack output, final int price, final ItemStack ingredient) {
            super((TemplateRecipeHandler)CompressionHandler.this);
            this.ingred = new PositionedStack((Object)ingredient, 51, 16);
            this.result = new PositionedStack((Object)output, 111, 16);
            this.cost = price;
        }
        
        public List<PositionedStack> getIngredients() {
            return (List<PositionedStack>)this.getCycledIngredients(CompressionHandler.this.cycleticks / 48, (List)Arrays.asList(this.ingred));
        }
        
        public PositionedStack getResult() {
            return this.result;
        }
        
        public void computeVisuals() {
            this.ingred.generatePermutations();
        }
        
        public int getCost() {
            return this.cost;
        }
    }
}

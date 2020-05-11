// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.item.ItemStack;

public class CompressorRecipe
{
    private ItemStack product;
    private int cost;
    private ItemStack input;
    private boolean specific;
    
    public CompressorRecipe(final ItemStack output, final int amount, final ItemStack ingredient, final boolean exact) {
        this.product = output;
        this.cost = amount;
        this.input = ingredient;
        this.specific = exact;
    }
    
    public CompressorRecipe(final ItemStack output, final int amount, final ItemStack ingredient) {
        this(output, amount, ingredient, false);
    }
    
    public ItemStack getOutput() {
        return this.product.func_77946_l();
    }
    
    public int getCost() {
        if (this.specific) {
            return this.cost;
        }
        return Gregorizer.balanceCost(this.cost);
    }
    
    public boolean validInput(final ItemStack ingredient) {
        return ingredient.func_77969_a(this.input);
    }
    
    public String getIngredientName() {
        return this.input.func_82833_r();
    }
    
    public Object getIngredient() {
        return this.input;
    }
}

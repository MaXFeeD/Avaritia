// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class ExtremeShapedRecipe implements IRecipe
{
    public final int recipeWidth;
    public final int recipeHeight;
    public final ItemStack[] recipeItems;
    private ItemStack recipeOutput;
    
    public ExtremeShapedRecipe(final int width, final int height, final ItemStack[] ingredients, final ItemStack result) {
        this.recipeWidth = width;
        this.recipeHeight = height;
        this.recipeItems = ingredients;
        this.recipeOutput = result;
    }
    
    public ItemStack func_77571_b() {
        return this.recipeOutput;
    }
    
    public boolean func_77569_a(final InventoryCrafting matrix, final World world) {
        for (int i = 0; i <= 9 - this.recipeWidth; ++i) {
            for (int j = 0; j <= 9 - this.recipeHeight; ++j) {
                if (this.checkMatch(matrix, i, j, true)) {
                    return true;
                }
                if (this.checkMatch(matrix, i, j, false)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkMatch(final InventoryCrafting matrix, final int x, final int y, final boolean mirrored) {
        for (int k = 0; k < 9; ++k) {
            for (int l = 0; l < 9; ++l) {
                final int i1 = k - x;
                final int j1 = l - y;
                ItemStack itemstack = null;
                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
                    if (mirrored) {
                        itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }
                    else {
                        itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                    }
                }
                final ItemStack itemstack2 = matrix.func_70463_b(k, l);
                if (itemstack2 != null || itemstack != null) {
                    if ((itemstack2 == null && itemstack != null) || (itemstack2 != null && itemstack == null)) {
                        return false;
                    }
                    if (itemstack.func_77973_b() != itemstack2.func_77973_b()) {
                        return false;
                    }
                    if (itemstack.func_77960_j() != 32767 && itemstack.func_77960_j() != itemstack2.func_77960_j()) {
                        return false;
                    }
                    if (itemstack.func_77942_o() && !ItemStack.func_77970_a(itemstack, itemstack2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting p_77572_1_) {
        return this.func_77571_b().func_77946_l();
    }
    
    public int func_77570_a() {
        return this.recipeWidth * this.recipeHeight;
    }
}

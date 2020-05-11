// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class ExtremeShapelessRecipe implements IRecipe
{
    private final ItemStack recipeOutput;
    public final List recipeItems;
    
    public ExtremeShapelessRecipe(final ItemStack result, final List ingredients) {
        this.recipeOutput = result;
        this.recipeItems = ingredients;
    }
    
    public ItemStack func_77571_b() {
        return this.recipeOutput;
    }
    
    public boolean func_77569_a(final InventoryCrafting matrix, final World world) {
        final ArrayList arraylist = new ArrayList(this.recipeItems);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                final ItemStack itemstack = matrix.func_70463_b(j, i);
                if (itemstack != null) {
                    boolean flag = false;
                    for (final ItemStack itemstack2 : arraylist) {
                        if (itemstack.func_77973_b() == itemstack2.func_77973_b() && (itemstack2.func_77960_j() == 32767 || itemstack.func_77960_j() == itemstack2.func_77960_j()) && (!itemstack2.func_77942_o() || ItemStack.func_77970_a(itemstack2, itemstack))) {
                            flag = true;
                            arraylist.remove(itemstack2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            }
        }
        return arraylist.isEmpty();
    }
    
    public ItemStack func_77572_b(final InventoryCrafting matrix) {
        return this.recipeOutput.func_77946_l();
    }
    
    public int func_77570_a() {
        return this.recipeItems.size();
    }
}

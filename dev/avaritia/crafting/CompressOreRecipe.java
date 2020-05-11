// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import java.util.ArrayList;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.item.ItemStack;

public class CompressOreRecipe extends CompressorRecipe
{
    private int oreID;
    
    public CompressOreRecipe(final ItemStack output, final int amount, final String lex, final boolean exact) {
        super(output, amount, null, exact);
        this.oreID = OreDictionary.getOreID(lex);
    }
    
    public CompressOreRecipe(final ItemStack output, final int amount, final String lex) {
        this(output, amount, lex, false);
    }
    
    @Override
    public boolean validInput(final ItemStack ingredient) {
        final int[] ids = OreDictionary.getOreIDs(ingredient);
        for (int x = 0; x < ids.length; ++x) {
            if (this.oreID == ids[x]) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getIngredientName() {
        final ArrayList<ItemStack> ores = (ArrayList<ItemStack>)OreDictionary.getOres(OreDictionary.getOreName(this.oreID));
        if (!ores.isEmpty()) {
            return ores.get(0).func_82833_r();
        }
        return OreDictionary.getOreName(this.oreID);
    }
    
    @Override
    public Object getIngredient() {
        return OreDictionary.getOres(OreDictionary.getOreName(this.oreID));
    }
}

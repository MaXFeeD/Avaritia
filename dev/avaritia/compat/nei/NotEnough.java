// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.nei;

import net.minecraft.item.ItemStack;
import codechicken.nei.recipe.IUsageHandler;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.api.API;

public class NotEnough
{
    public static void items() {
        final ExtremeShapedRecipeHandler shaped = new ExtremeShapedRecipeHandler();
        final ExtremeShapelessRecipeHandler shapeless = new ExtremeShapelessRecipeHandler();
        final CompressionHandler compress = new CompressionHandler();
        API.registerRecipeHandler((ICraftingHandler)shaped);
        API.registerRecipeHandler((ICraftingHandler)shapeless);
        API.registerRecipeHandler((ICraftingHandler)compress);
        API.registerUsageHandler((IUsageHandler)shaped);
        API.registerUsageHandler((IUsageHandler)shapeless);
        API.registerUsageHandler((IUsageHandler)compress);
    }
    
    public static void hide(final ItemStack stack) {
        API.hideItem(stack);
    }
}

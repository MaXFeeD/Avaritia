// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import net.minecraft.util.IIcon;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileChicken extends SubTileGenerating
{
    public static LexiconEntry lexicon;
    
    public int getMaxMana() {
        return 1000000;
    }
    
    public int getColor() {
        return 1179392;
    }
    
    public LexiconEntry getEntry() {
        return SubTileChicken.lexicon;
    }
    
    public boolean canGeneratePassively() {
        return true;
    }
    
    public int getDelayBetweenPassiveGeneration() {
        return 20;
    }
    
    public int getValueForPassiveGeneration() {
        return 0;
    }
    
    public IIcon getIcon() {
        return BotaniaAPI.getSignatureForName("soarleander").getIconForStack((ItemStack)null);
    }
}

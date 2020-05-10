// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconEntry;

public class LudicrousLexicon extends LexiconEntry implements IAddonEntry
{
    public LudicrousLexicon(final String name, final LexiconCategory category) {
        super(name, category);
        BotaniaAPI.addEntry((LexiconEntry)this, category);
    }
    
    public String getSubtitle() {
        return "[Avaritia]";
    }
    
    public String getUnlocalizedName() {
        return "avaritia.lexicon." + super.getUnlocalizedName();
    }
}

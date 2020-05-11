// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.thaumcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;

public class LudicrousResearchItem extends ResearchItem
{
    public LudicrousResearchItem(final String par1, final String x) {
        super(par1, x);
    }
    
    public LudicrousResearchItem(final String par1, final String x, final AspectList tags, final int y, final int z, final int par5, final ResourceLocation icon) {
        super(par1, x, tags, y, z, par5, icon);
    }
    
    public LudicrousResearchItem(final String par1, final String x, final AspectList tags, final int y, final int z, final int par5, final ItemStack icon) {
        super(par1, x, tags, y, z, par5, icon);
    }
    
    @SideOnly(Side.CLIENT)
    public String getName() {
        return StatCollector.func_74838_a("avaritia.research_name." + this.key);
    }
    
    @SideOnly(Side.CLIENT)
    public String getText() {
        return "[AV] " + StatCollector.func_74838_a("avaritia.research_text." + this.key);
    }
}

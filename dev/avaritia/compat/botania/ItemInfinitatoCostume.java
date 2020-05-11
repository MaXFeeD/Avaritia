// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemInfinitatoCostume extends Item
{
    private static final String[] types;
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;
    
    public ItemInfinitatoCostume() {
        this.func_77627_a(true);
        this.func_77656_e(0);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        this.icons = new IIcon[ItemInfinitatoCostume.types.length];
        for (int x = 0; x < ItemInfinitatoCostume.types.length; ++x) {
            this.icons[x] = ir.func_94245_a("avaritia:costume_" + ItemInfinitatoCostume.types[x]);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(final int dam) {
        return this.icons[dam % this.icons.length];
    }
    
    static {
        types = new String[] { "armstrong", "moon", "egbert", "francis" };
    }
}

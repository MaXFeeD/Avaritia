// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import net.minecraft.util.MathHelper;
import java.awt.Color;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import fox.spiteful.avaritia.Avaritia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemComb extends Item
{
    private static final String[] types;
    private static final int[] mainColors;
    private static final int[] otherColors;
    @SideOnly(Side.CLIENT)
    private IIcon secondIcon;
    
    public ItemComb() {
        this.func_77637_a(Avaritia.tab);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77655_b("avaritia.comb");
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        for (int j = 0; j < ItemComb.types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("forestry:beeCombs.0");
        this.secondIcon = par1IconRegister.func_94245_a("forestry:beeCombs.1");
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        return (pass == 0) ? this.field_77791_bV : this.secondIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }
    
    public int getRenderPasses(final int meta) {
        return 2;
    }
    
    @SideOnly(Side.CLIENT)
    public int func_82790_a(final ItemStack stack, final int pass) {
        if (pass != 0) {
            return ItemComb.otherColors[stack.func_77960_j() % ItemComb.otherColors.length];
        }
        if (stack.func_77960_j() == 1) {
            final int hue = (int)(System.currentTimeMillis() >> 2) % 360;
            return Color.getHSBColor(hue / 360.0f, 0.75f, 0.8f).getRGB();
        }
        return ItemComb.mainColors[stack.func_77960_j() % ItemComb.mainColors.length];
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int i = MathHelper.func_76125_a(stack.func_77960_j(), 0, ItemComb.types.length);
        return super.func_77658_a() + "." + ItemComb.types[i];
    }
    
    static {
        types = new String[] { "nerfed", "cosmic" };
        mainColors = new int[] { 11974326, 15685631 };
        otherColors = new int[] { 7697781, 393308 };
    }
}

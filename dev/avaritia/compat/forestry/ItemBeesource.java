// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.EnumRarity;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import fox.spiteful.avaritia.Avaritia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import fox.spiteful.avaritia.render.IHaloRenderItem;
import net.minecraft.item.Item;

public class ItemBeesource extends Item implements IHaloRenderItem
{
    private static final String[] types;
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;
    @SideOnly(Side.CLIENT)
    public IIcon halo;
    
    public ItemBeesource() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77655_b("avaritia_beesource");
        this.func_77637_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        this.icons = new IIcon[ItemBeesource.types.length];
        for (int x = 0; x < ItemBeesource.types.length; ++x) {
            this.icons[x] = ir.func_94245_a("avaritia:resource_" + ItemBeesource.types[x]);
        }
        this.halo = ir.func_94245_a("avaritia:halo");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(final int dam) {
        return this.icons[dam % this.icons.length];
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int i = MathHelper.func_76125_a(stack.func_77960_j(), 0, ItemBeesource.types.length);
        return super.func_77658_a() + "." + ItemBeesource.types[i];
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        for (int j = 0; j < ItemBeesource.types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        switch (stack.func_77960_j()) {
            case 0: {
                return LudicrousItems.cosmic;
            }
            case 1: {
                return Ranger.trash;
            }
            default: {
                return EnumRarity.common;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawHalo(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return meta == 0;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getHaloTexture(final ItemStack stack) {
        return this.halo;
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloSize(final ItemStack stack) {
        return 10;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawPulseEffect(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return meta == 0;
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloColour(final ItemStack stack) {
        return -16777216;
    }
    
    static {
        types = new String[] { "infinity_drop", "dust" };
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import fox.spiteful.avaritia.LudicrousText;
import net.minecraft.util.StatCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.client.renderer.texture.IIconRegister;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import fox.spiteful.avaritia.render.IHaloRenderItem;
import net.minecraft.item.Item;

public class ItemSingularity extends Item implements IHaloRenderItem
{
    public static final String[] types;
    public static final int[] colors;
    public static final int[] colors2;
    public static IIcon background;
    public static IIcon foreground;
    
    public ItemSingularity() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77655_b("avaritia_singularity");
        this.func_111206_d("avaritia:singularity");
        this.func_77637_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public int func_82790_a(final ItemStack itemstack, final int renderpass) {
        return (renderpass == 0) ? ItemSingularity.colors2[itemstack.func_77960_j() % ItemSingularity.colors.length] : ItemSingularity.colors[itemstack.func_77960_j() % ItemSingularity.colors2.length];
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int i = MathHelper.func_76125_a(stack.func_77960_j(), 0, ItemSingularity.types.length);
        return "item.singularity_" + ItemSingularity.types[i];
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        for (int j = 0; j < ItemSingularity.types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        ItemSingularity.foreground = ir.func_94245_a("avaritia:singularity");
        ItemSingularity.background = ir.func_94245_a("avaritia:singularity2");
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        if (pass == 0) {
            return ItemSingularity.background;
        }
        return ItemSingularity.foreground;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        if (stack.func_77960_j() == 10) {
            return LudicrousItems.cosmic;
        }
        return EnumRarity.uncommon;
    }
    
    public void func_77624_a(final ItemStack item, final EntityPlayer player, final List tooltip, final boolean wut) {
        final int meta = item.func_77960_j();
        if (meta == 10) {
            tooltip.add(LudicrousText.makeFabulous(StatCollector.func_74838_a("tooltip.claybalance.desc")));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawHalo(final ItemStack stack) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getHaloTexture(final ItemStack stack) {
        return ((ItemResource)LudicrousItems.resource).halo[0];
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloSize(final ItemStack stack) {
        if (stack.func_77960_j() == 10) {
            return 8;
        }
        return 4;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawPulseEffect(final ItemStack stack) {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloColour(final ItemStack stack) {
        return -16777216;
    }
    
    static {
        types = new String[] { "iron", "gold", "lapis", "redstone", "quartz", "copper", "tin", "lead", "silver", "nickel", "clay" };
        colors = new int[] { 12566463, 15265571, 5931746, 14614528, 15657958, 14971392, 10864606, 4472946, 16382457, 14606727, 8949933 };
        colors2 = new int[] { 8355711, 14393875, 2247599, 9437184, 9733757, 8999194, 10201522, 4078926, 14013909, 12895896, 6712191 };
    }
}

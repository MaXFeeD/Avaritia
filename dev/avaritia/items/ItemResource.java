// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import fox.spiteful.avaritia.entity.EntityImmortalItem;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.item.EnumRarity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.EnumChatFormatting;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import fox.spiteful.avaritia.Avaritia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import fox.spiteful.avaritia.render.IHaloRenderItem;
import net.minecraft.item.Item;

public class ItemResource extends Item implements IHaloRenderItem
{
    private static final String[] types;
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;
    @SideOnly(Side.CLIENT)
    public IIcon[] halo;
    
    public ItemResource() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77655_b("avaritia_resource");
        this.func_77637_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        this.icons = new IIcon[ItemResource.types.length];
        for (int x = 0; x < ItemResource.types.length; ++x) {
            this.icons[x] = ir.func_94245_a("avaritia:resource_" + ItemResource.types[x]);
        }
        (this.halo = new IIcon[2])[0] = ir.func_94245_a("avaritia:halo");
        this.halo[1] = ir.func_94245_a("avaritia:halonoise");
    }
    
    public void func_77624_a(final ItemStack item, final EntityPlayer player, final List tooltip, final boolean wut) {
        final int meta = item.func_77960_j();
        if (meta != 0 && meta < 8) {
            tooltip.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("tooltip." + ItemResource.types[meta] + ".desc"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(final int dam) {
        return this.icons[dam % this.icons.length];
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int i = MathHelper.func_76125_a(stack.func_77960_j(), 0, ItemResource.types.length);
        return super.func_77658_a() + "." + ItemResource.types[i];
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        for (int j = 0; j < ItemResource.types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        switch (stack.func_77960_j()) {
            case 0:
            case 2:
            case 3: {
                return EnumRarity.uncommon;
            }
            case 1:
            case 4:
            case 8:
            case 9: {
                return EnumRarity.rare;
            }
            case 5: {
                return EnumRarity.epic;
            }
            case 6: {
                return LudicrousItems.cosmic;
            }
            default: {
                return EnumRarity.common;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawHalo(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return (meta >= 2 && meta <= 6) || meta >= 8;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getHaloTexture(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        if (meta == 2 || meta == 3 || meta == 4 || meta == 9) {
            return this.halo[1];
        }
        return this.halo[0];
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloSize(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        switch (meta) {
            case 5:
            case 6: {
                return 10;
            }
            default: {
                return 8;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawPulseEffect(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return meta == 5 || meta == 6;
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloColour(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        if (meta == 2) {
            return 872415231;
        }
        if (meta == 3) {
            return 1308622847;
        }
        if (meta == 4 || meta == 9) {
            return -1711276033;
        }
        return -16777216;
    }
    
    public boolean hasCustomEntity(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return meta == 5 || meta == 6;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        final int meta = itemstack.func_77960_j();
        return (Entity)((meta == 5 || meta == 6) ? new EntityImmortalItem(world, location, itemstack) : null);
    }
    
    static {
        types = new String[] { "diamond_lattice", "crystal_matrix_ingot", "neutron_pile", "neutron_nugget", "neutronium_ingot", "infinity_catalyst", "infinity_ingot", "record_fragment", "starfuel", "neutronium_gear" };
    }
}

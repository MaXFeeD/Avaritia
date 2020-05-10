// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.Block;

public class BlockResource extends Block
{
    public static final String[] types;
    private IIcon[] icons;
    
    public BlockResource() {
        super(Material.field_151573_f);
        this.func_149672_a(Block.field_149777_j);
        this.func_149711_c(50.0f);
        this.func_149752_b(2000.0f);
        this.func_149663_c("avaritia_resource");
        this.setHarvestLevel("pickaxe", 3);
        this.func_149647_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(final int side, final int metadata) {
        return this.icons[metadata % BlockResource.types.length];
    }
    
    public int func_149692_a(final int metadata) {
        return metadata;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_149666_a(final Item item, final CreativeTabs tab, final List list) {
        for (int x = 0; x < BlockResource.types.length; ++x) {
            list.add(new ItemStack(item, 1, x));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_149651_a(final IIconRegister iconRegister) {
        this.icons = new IIcon[BlockResource.types.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a("avaritia:block_" + BlockResource.types[i]);
        }
    }
    
    public boolean isBeaconBase(final IBlockAccess worldObj, final int x, final int y, final int z, final int beaconX, final int beaconY, final int beaconZ) {
        return true;
    }
    
    public boolean canEntityDestroy(final IBlockAccess world, final int x, final int y, final int z, final Entity entity) {
        return false;
    }
    
    static {
        types = new String[] { "neutronium", "infinity" };
    }
}

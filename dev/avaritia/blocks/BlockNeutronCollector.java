// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import java.util.Random;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockNeutronCollector extends BlockContainer
{
    private IIcon top;
    private IIcon sides;
    private IIcon front;
    private Random randy;
    
    public BlockNeutronCollector() {
        super(Material.field_151573_f);
        this.randy = new Random();
        this.func_149672_a(Block.field_149777_j);
        this.func_149711_c(20.0f);
        this.func_149663_c("neutron_collector");
        this.setHarvestLevel("pickaxe", 3);
        this.func_149647_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_149651_a(final IIconRegister iconRegister) {
        this.top = iconRegister.func_94245_a("avaritia:collector_top");
        this.sides = iconRegister.func_94245_a("avaritia:collector_side");
        this.front = iconRegister.func_94245_a("avaritia:collector_front");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(final IBlockAccess world, final int x, final int y, final int z, final int side) {
        if (side == 1) {
            return this.top;
        }
        int facing = 2;
        final TileEntityNeutron machine = (TileEntityNeutron)world.func_147438_o(x, y, z);
        if (machine != null) {
            facing = machine.getFacing();
        }
        if (side == facing) {
            return this.front;
        }
        return this.sides;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(final int side, final int metadata) {
        if (side == 1) {
            return this.top;
        }
        if (side == 3) {
            return this.front;
        }
        return this.sides;
    }
    
    public boolean func_149727_a(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        if (world.field_72995_K) {
            return true;
        }
        player.openGui((Object)Avaritia.instance, 2, world, x, y, z);
        return true;
    }
    
    public TileEntity func_149915_a(final World world, final int meta) {
        return new TileEntityNeutron();
    }
    
    public void func_149689_a(final World world, final int x, final int y, final int z, final EntityLivingBase player, final ItemStack item) {
        final TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof TileEntityNeutron) {
            final TileEntityNeutron machine = (TileEntityNeutron)tile;
            final int l = MathHelper.func_76128_c(player.field_70177_z * 4.0f / 360.0f + 0.5) & 0x3;
            if (l == 0) {
                machine.setFacing(2);
            }
            if (l == 1) {
                machine.setFacing(5);
            }
            if (l == 2) {
                machine.setFacing(3);
            }
            if (l == 3) {
                machine.setFacing(4);
            }
        }
    }
    
    public void func_149749_a(final World world, final int x, final int y, final int z, final Block block, final int wut) {
        final TileEntityNeutron collector = (TileEntityNeutron)world.func_147438_o(x, y, z);
        if (collector != null) {
            final ItemStack itemstack = collector.func_70301_a(0);
            if (itemstack != null) {
                final float f = this.randy.nextFloat() * 0.8f + 0.1f;
                final float f2 = this.randy.nextFloat() * 0.8f + 0.1f;
                final float f3 = this.randy.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int j1 = this.randy.nextInt(21) + 10;
                    if (j1 > itemstack.field_77994_a) {
                        j1 = itemstack.field_77994_a;
                    }
                    final ItemStack itemStack = itemstack;
                    itemStack.field_77994_a -= j1;
                    final EntityItem entityitem = new EntityItem(world, (double)(x + f), (double)(y + f2), (double)(z + f3), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    final float f4 = 0.05f;
                    entityitem.field_70159_w = (float)this.randy.nextGaussian() * f4;
                    entityitem.field_70181_x = (float)this.randy.nextGaussian() * f4 + 0.2f;
                    entityitem.field_70179_y = (float)this.randy.nextGaussian() * f4;
                    world.func_72838_d((Entity)entityitem);
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.func_149749_a(world, x, y, z, block, wut);
    }
}

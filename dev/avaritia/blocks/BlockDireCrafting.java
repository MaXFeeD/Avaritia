// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import java.util.Random;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockDireCrafting extends BlockContainer
{
    private static IIcon top;
    private static IIcon sides;
    private static IIcon bottom;
    private Random randy;
    
    public BlockDireCrafting() {
        super(Material.field_151573_f);
        this.randy = new Random();
        this.func_149672_a(Block.field_149778_k);
        this.func_149711_c(50.0f);
        this.func_149752_b(2000.0f);
        this.func_149663_c("dire_crafting");
        this.setHarvestLevel("pickaxe", 3);
        this.func_149647_a(Avaritia.tab);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_149651_a(final IIconRegister iconRegister) {
        BlockDireCrafting.top = iconRegister.func_94245_a("avaritia:dire_crafting_top");
        BlockDireCrafting.sides = iconRegister.func_94245_a("avaritia:dire_crafting_side");
        BlockDireCrafting.bottom = iconRegister.func_94245_a("avaritia:block_crystal_matrix");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(final int side, final int metadata) {
        if (side == 0) {
            return BlockDireCrafting.bottom;
        }
        if (side == 1) {
            return BlockDireCrafting.top;
        }
        return BlockDireCrafting.sides;
    }
    
    public boolean func_149727_a(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        if (world.field_72995_K) {
            return true;
        }
        player.openGui((Object)Avaritia.instance, 1, world, x, y, z);
        return true;
    }
    
    public TileEntity func_149915_a(final World world, final int meta) {
        return new TileEntityDireCrafting();
    }
    
    public void func_149749_a(final World world, final int x, final int y, final int z, final Block block, final int wut) {
        final TileEntityDireCrafting craft = (TileEntityDireCrafting)world.func_147438_o(x, y, z);
        if (craft != null) {
            for (int i = 1; i < 82; ++i) {
                final ItemStack itemstack = craft.func_70301_a(i);
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
        }
        super.func_149749_a(world, x, y, z, block, wut);
    }
}

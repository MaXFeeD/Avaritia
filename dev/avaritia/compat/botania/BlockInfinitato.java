// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.block.Block;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.block.material.Material;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.ILexiconable;
import net.minecraft.block.BlockContainer;

public class BlockInfinitato extends BlockContainer implements ILexiconable
{
    public static LexiconEntry lexiconEntry;
    
    public BlockInfinitato() {
        super(Material.field_151580_n);
        this.func_149711_c(0.25f);
        this.func_149663_c("infinitato");
        final float f = 0.25f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, f * 3.0f, 1.0f - f);
    }
    
    public void func_149651_a(final IIconRegister par1IconRegister) {
    }
    
    public IIcon func_149691_a(final int side, final int meta) {
        return Blocks.field_150405_ch.func_149691_a(0, 11);
    }
    
    public boolean func_149727_a(final World par1World, final int par2, final int par3, final int par4, final EntityPlayer par5EntityPlayer, final int par6, final float par7, final float par8, final float par9) {
        final TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile instanceof TileInfinitato) {
            ((TileInfinitato)tile).interact();
            par1World.func_72869_a("heart", par2 + this.field_149759_B + Math.random() * (this.field_149755_E - this.field_149759_B), par3 + this.field_149756_F, par4 + this.field_149754_D + Math.random() * (this.field_149757_G - this.field_149754_D), 0.0, 0.0, 0.0);
            par1World.func_72869_a("heart", par2 + this.field_149759_B + Math.random() * (this.field_149755_E - this.field_149759_B), par3 + this.field_149756_F, par4 + this.field_149754_D + Math.random() * (this.field_149757_G - this.field_149754_D), 0.0, 0.0, 0.0);
            par1World.func_72869_a("heart", par2 + this.field_149759_B + Math.random() * (this.field_149755_E - this.field_149759_B), par3 + this.field_149756_F, par4 + this.field_149754_D + Math.random() * (this.field_149757_G - this.field_149754_D), 0.0, 0.0, 0.0);
        }
        return true;
    }
    
    public void func_149689_a(final World par1World, final int par2, final int par3, final int par4, final EntityLivingBase par5EntityLiving, final ItemStack par6ItemStack) {
        final int l1 = MathHelper.func_76128_c(par5EntityLiving.field_70177_z * 4.0f / 360.0f + 0.5) & 0x3;
        par1World.func_72921_c(par2, par3, par4, l1, 2);
        if (par6ItemStack.func_82837_s()) {
            ((TileInfinitato)par1World.func_147438_o(par2, par3, par4)).name = par6ItemStack.func_82833_r();
        }
    }
    
    public void func_149681_a(final World par1World, final int par2, final int par3, final int par4, final int par5, final EntityPlayer par6EntityPlayer) {
        if (!par6EntityPlayer.field_71075_bZ.field_75098_d) {
            this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        }
    }
    
    public ArrayList<ItemStack> getDrops(final World world, final int x, final int y, final int z, final int metadata, final int fortune) {
        final ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        final TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            final ItemStack stack = new ItemStack((Block)this);
            final String name = ((TileInfinitato)tile).name;
            if (!name.isEmpty()) {
                stack.func_151001_c(name);
            }
            list.add(stack);
        }
        return list;
    }
    
    public boolean func_149662_c() {
        return false;
    }
    
    public boolean func_149686_d() {
        return false;
    }
    
    public int func_149645_b() {
        return RenderInfinitato.renderID;
    }
    
    public TileEntity func_149915_a(final World world, final int meta) {
        return new TileInfinitato();
    }
    
    public LexiconEntry getEntry(final World world, final int x, final int y, final int z, final EntityPlayer player, final ItemStack lexicon) {
        return BlockInfinitato.lexiconEntry;
    }
}

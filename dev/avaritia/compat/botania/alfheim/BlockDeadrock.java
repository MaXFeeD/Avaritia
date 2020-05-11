// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import vazkii.botania.api.lexicon.LexiconEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import vazkii.botania.api.lexicon.ILexiconable;
import net.minecraft.block.Block;

public class BlockDeadrock extends Block implements ILexiconable
{
    private static final int TYPES = 5;
    IIcon[] icons;
    
    public BlockDeadrock() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(BlockDeadrock.field_149769_e);
        this.func_149663_c("alfheim_deadrock");
    }
    
    public int func_149692_a(final int par1) {
        return par1;
    }
    
    public void func_149666_a(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        for (int i = 0; i < 5; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    public void func_149651_a(final IIconRegister ir) {
        this.icons = new IIcon[5];
        for (int i = 0; i < 5; ++i) {
            this.icons[i] = ir.func_94245_a("avaritia:deadrock" + i);
        }
    }
    
    public IIcon func_149691_a(final int par1, final int par2) {
        return this.icons[Math.min(4, par2)];
    }
    
    public ItemStack getPickBlock(final MovingObjectPosition target, final World world, final int x, final int y, final int z) {
        final int meta = world.func_72805_g(x, y, z);
        return new ItemStack((Block)this, 1, meta);
    }
    
    public LexiconEntry getEntry(final World world, final int x, final int y, final int z, final EntityPlayer player, final ItemStack lexicon) {
        final int meta = world.func_72805_g(x, y, z);
        return null;
    }
}

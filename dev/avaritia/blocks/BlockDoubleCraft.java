// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

public class BlockDoubleCraft extends Block
{
    public BlockDoubleCraft() {
        super(Material.field_151575_d);
        this.func_149711_c(4.0f);
        this.func_149663_c("compressed_workbench");
        this.func_149672_a(Block.field_149766_f);
        this.func_149658_d("avaritia:double_craft");
        this.func_149647_a(Avaritia.tab);
    }
    
    public boolean func_149727_a(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par6, final float par7, final float par8, final float par9) {
        if (world.field_72995_K) {
            return true;
        }
        player.openGui((Object)Avaritia.instance, 0, world, x, y, z);
        return true;
    }
}

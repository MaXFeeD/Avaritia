// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

public class BlockCrystalMatrix extends Block
{
    public BlockCrystalMatrix() {
        super(Material.field_151573_f);
        this.func_149672_a(Block.field_149778_k);
        this.func_149711_c(50.0f);
        this.func_149752_b(2000.0f);
        this.func_149663_c("block_crystal_matrix");
        this.setHarvestLevel("pickaxe", 3);
        this.func_149658_d("avaritia:block_crystal_matrix");
        this.func_149647_a(Avaritia.tab);
    }
    
    public boolean isBeaconBase(final IBlockAccess worldObj, final int x, final int y, final int z, final int beaconX, final int beaconY, final int beaconZ) {
        return true;
    }
    
    public boolean canEntityDestroy(final IBlockAccess world, final int x, final int y, final int z, final Entity entity) {
        return false;
    }
}

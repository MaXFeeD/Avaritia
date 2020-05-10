// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockResource extends ItemBlock
{
    public ItemBlockResource(final Block block) {
        super(block);
        this.func_77627_a(true);
    }
    
    public int func_77647_b(final int meta) {
        return meta;
    }
    
    public String func_77667_c(final ItemStack itemStack) {
        return "tile.block_" + BlockResource.types[itemStack.func_77960_j() % BlockResource.types.length];
    }
}

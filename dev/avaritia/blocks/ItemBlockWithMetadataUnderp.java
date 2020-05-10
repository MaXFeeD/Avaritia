// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;

public class ItemBlockWithMetadataUnderp extends ItemBlockWithMetadata
{
    public ItemBlockWithMetadataUnderp(final Block block) {
        super(block, block);
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int meta = stack.func_77960_j();
        return this.field_150939_a.func_149739_a() + meta;
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.blocks.ItemBlockWithMetadataUnderp;
import net.minecraft.block.Block;

public class AlfheimBlocks
{
    public static Block deadrock;
    
    public static void alfheimify() {
        AlfheimBlocks.deadrock = GameRegistry.registerBlock((Block)new BlockDeadrock(), (Class)ItemBlockWithMetadataUnderp.class, "Alf_Deadrock");
    }
}

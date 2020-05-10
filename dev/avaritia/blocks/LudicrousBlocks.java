// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.blocks;

import fox.spiteful.avaritia.tile.TileEntityCompressor;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import fox.spiteful.avaritia.Config;
import fox.spiteful.avaritia.tile.TileEntityDireCrafting;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class LudicrousBlocks
{
    public static Block double_craft;
    public static Block triple_craft;
    public static Block crystal_matrix;
    public static Block resource_block;
    public static Block dire_crafting;
    public static Block auto_dire_crafting;
    public static Block neutron_collector;
    public static Block compressor;
    public static Block infinitato;
    
    public static void voxelize() {
        LudicrousBlocks.double_craft = GameRegistry.registerBlock((Block)new BlockDoubleCraft(), "Double_Craft");
        LudicrousBlocks.triple_craft = GameRegistry.registerBlock((Block)new BlockTripleCraft(), "Triple_Craft");
        LudicrousBlocks.dire_crafting = GameRegistry.registerBlock((Block)new BlockDireCrafting(), "Dire_Crafting");
        GameRegistry.registerTileEntity((Class)TileEntityDireCrafting.class, "Avaritia_Dire_Craft");
        if (Config.craftingOnly) {
            return;
        }
        LudicrousBlocks.crystal_matrix = GameRegistry.registerBlock((Block)new BlockCrystalMatrix(), "Crystal_Matrix");
        LudicrousBlocks.resource_block = GameRegistry.registerBlock((Block)new BlockResource(), (Class)ItemBlockResource.class, "Resource_Block");
        LudicrousBlocks.neutron_collector = GameRegistry.registerBlock((Block)new BlockNeutronCollector(), "Neutron_Collector");
        GameRegistry.registerTileEntity((Class)TileEntityNeutron.class, "Avaritia_Neutron");
        LudicrousBlocks.compressor = GameRegistry.registerBlock((Block)new BlockCompressor(), "Neutronium_Compressor");
        GameRegistry.registerTileEntity((Class)TileEntityCompressor.class, "Avaritia_Compressor");
    }
}

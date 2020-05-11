// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.world.IBlockAccess;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.block.Block;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderInfinitato implements ISimpleBlockRenderingHandler
{
    public static int renderID;
    
    public RenderInfinitato(final int id) {
        RenderInfinitato.renderID = id;
    }
    
    public void renderInventoryBlock(final Block block, final int metadata, final int modelId, final RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5f, -0.35f, 0.5f);
        GL11.glRotated(90.0, 0.0, 1.0, 0.0);
        RenderTileInfinitato.drawHalo = false;
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileInfinitato(), 0.0, 0.0, 0.0, 0.0f);
        RenderTileInfinitato.drawHalo = true;
        GL11.glPopMatrix();
    }
    
    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block, final int modelId, final RenderBlocks renderer) {
        return false;
    }
    
    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }
    
    public int getRenderId() {
        return RenderInfinitato.renderID;
    }
}

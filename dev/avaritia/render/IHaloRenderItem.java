// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public interface IHaloRenderItem
{
    @SideOnly(Side.CLIENT)
    boolean drawHalo(final ItemStack p0);
    
    @SideOnly(Side.CLIENT)
    IIcon getHaloTexture(final ItemStack p0);
    
    @SideOnly(Side.CLIENT)
    int getHaloSize(final ItemStack p0);
    
    @SideOnly(Side.CLIENT)
    boolean drawPulseEffect(final ItemStack p0);
    
    @SideOnly(Side.CLIENT)
    int getHaloColour(final ItemStack p0);
}

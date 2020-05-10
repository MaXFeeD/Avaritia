// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ICosmicRenderItem
{
    @SideOnly(Side.CLIENT)
    IIcon getMaskTexture(final ItemStack p0, final EntityPlayer p1);
    
    @SideOnly(Side.CLIENT)
    float getMaskMultiplier(final ItemStack p0, final EntityPlayer p1);
}

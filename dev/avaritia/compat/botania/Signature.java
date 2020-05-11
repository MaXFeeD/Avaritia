// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class Signature extends SubTileSignature
{
    String name;
    IIcon icon;
    
    public Signature(final String nombre) {
        this.name = nombre;
    }
    
    public void registerIcons(final IIconRegister reg) {
        this.icon = reg.func_94245_a("avaritia:" + this.name);
    }
    
    public IIcon getIconForStack(final ItemStack item) {
        return this.icon;
    }
    
    public String getUnlocalizedNameForStack(final ItemStack item) {
        return "avaritia.flower." + this.name;
    }
    
    public String getUnlocalizedLoreTextForStack(final ItemStack item) {
        return "tile.avaritia.flower." + this.name + ".lore";
    }
}

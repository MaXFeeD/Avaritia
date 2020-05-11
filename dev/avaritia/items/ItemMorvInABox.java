// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import fox.spiteful.avaritia.compat.botania.alfheim.TeleportHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemMorvInABox extends Item
{
    public IIcon nameoverlay;
    
    public ItemMorvInABox() {
        this.func_77655_b("morvinabox");
        this.func_77637_a(Avaritia.tab);
        this.func_111206_d("avaritia:morvinabox");
    }
    
    public void func_77624_a(final ItemStack item, final EntityPlayer player, final List tooltip, final boolean wut) {
        tooltip.add(StatCollector.func_74838_a("tooltip.morvinabox.desc"));
        tooltip.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("tooltip.morvinabox.subdesc"));
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        super.func_94581_a(ir);
        this.nameoverlay = ir.func_94245_a("avaritia:morvinabox2");
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        TeleportHelper.travelToOrFromAlfheim(player);
        return stack;
    }
}

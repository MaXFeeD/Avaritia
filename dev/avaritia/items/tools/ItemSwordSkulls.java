// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import net.minecraft.util.StatCollector;
import net.minecraft.util.EnumChatFormatting;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemSwordSkulls extends ItemSword
{
    public ItemSwordSkulls() {
        super(Item.ToolMaterial.EMERALD);
        this.func_77655_b("skullfire_sword");
        this.func_111206_d("avaritia:skull_sword");
        this.func_77637_a(Avaritia.tab);
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return EnumRarity.epic;
    }
    
    public void func_77624_a(final ItemStack item, final EntityPlayer player, final List tooltip, final boolean wut) {
        tooltip.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("tooltip.skullfire_sword.desc"));
    }
}

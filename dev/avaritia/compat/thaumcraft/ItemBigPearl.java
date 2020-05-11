// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.thaumcraft;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.item.Item;

public class ItemBigPearl extends Item
{
    public ItemBigPearl() {
        this.func_77625_d(1);
        this.func_77655_b("extremely_primordial_pearl");
        this.func_111206_d("avaritia:big_pearl");
        this.func_77637_a(Avaritia.tab);
    }
    
    public EnumRarity func_77613_e(final ItemStack itemstack) {
        return EnumRarity.epic;
    }
    
    public boolean func_77634_r() {
        return true;
    }
    
    public boolean func_77630_h(final ItemStack itemStack) {
        return false;
    }
    
    public ItemStack getContainerItem(final ItemStack itemStack) {
        return itemStack;
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.bloodmagic;

import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.Entity;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBindable;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBloodOrb;
import net.minecraft.item.Item;

public class ItemOrbArmok extends Item implements IBloodOrb, IBindable
{
    public ItemOrbArmok() {
        this.func_77625_d(1);
        this.func_77655_b("orb_armok");
        this.func_111206_d("avaritia:orb_armok");
        this.func_77637_a(Avaritia.tab);
        AltarRecipeRegistry.registerAltarOrbRecipe(new ItemStack((Item)this), 1, 140);
    }
    
    public ItemStack func_77659_a(final ItemStack itemstack, final World world, final EntityPlayer player) {
        if (!world.field_72995_K) {
            SoulNetworkHandler.checkAndSetItemOwner(itemstack, player);
        }
        return itemstack;
    }
    
    public void func_77663_a(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
        if (!world.field_72995_K && entity instanceof EntityPlayer) {
            final NBTTagCompound itemTag = stack.field_77990_d;
            if (itemTag == null || itemTag.func_74779_i("ownerName").equals("")) {
                return;
            }
            SoulNetworkHandler.setCurrentEssence(itemTag.func_74779_i("ownerName"), this.getMaxEssence());
        }
    }
    
    public void func_77624_a(final ItemStack item, final EntityPlayer player, final List tooltip, final boolean wut) {
        tooltip.add(StatCollector.func_74838_a("tooltip.armok.desc"));
        if (item.func_77978_p() != null) {
            tooltip.add(StatCollector.func_74838_a("tooltip.owner.currentowner") + " " + item.func_77978_p().func_74779_i("ownerName"));
        }
    }
    
    public int getMaxEssence() {
        return 1000000000;
    }
    
    public int getOrbLevel() {
        return 6;
    }
    
    public EnumRarity func_77613_e(final ItemStack itemstack) {
        return LudicrousItems.cosmic;
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

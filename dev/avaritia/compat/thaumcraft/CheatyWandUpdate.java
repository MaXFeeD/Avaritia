// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.thaumcraft;

import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class CheatyWandUpdate implements IWandRodOnUpdate
{
    Aspect[] primals;
    
    public CheatyWandUpdate() {
        this.primals = Aspect.getPrimalAspects().toArray(new Aspect[0]);
    }
    
    public void onUpdate(final ItemStack itemstack, final EntityPlayer player) {
        for (int x = 0; x < this.primals.length; ++x) {
            if (((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.primals[x]) < ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack)) {
                ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, this.primals[x], ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) - ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.primals[x]), true);
            }
        }
    }
}

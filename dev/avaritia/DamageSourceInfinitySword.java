// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import java.util.Random;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceInfinitySword extends EntityDamageSource
{
    private static final Random randy;
    
    public DamageSourceInfinitySword(final Entity source) {
        super("infinity", source);
    }
    
    public IChatComponent func_151519_b(final EntityLivingBase p_151519_1_) {
        final ItemStack itemstack = (this.field_76386_o instanceof EntityLivingBase) ? ((EntityLivingBase)this.field_76386_o).func_70694_bm() : null;
        String s = "death.attack.infinity";
        final int rando = DamageSourceInfinitySword.randy.nextInt(5);
        if (rando != 0) {
            s = s + "." + rando;
        }
        return (IChatComponent)new ChatComponentTranslation(s, new Object[] { p_151519_1_.func_145748_c_(), this.field_76386_o.func_145748_c_() });
    }
    
    public boolean func_76350_n() {
        return false;
    }
    
    static {
        randy = new Random();
    }
}

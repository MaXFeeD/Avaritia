// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import net.minecraft.item.ItemStack;

public class ItemStackWrapper
{
    public final ItemStack stack;
    
    public ItemStackWrapper(final ItemStack stack) {
        this.stack = stack;
    }
    
    @Override
    public boolean equals(final Object otherobj) {
        if (otherobj instanceof ItemStackWrapper) {
            final ItemStackWrapper other = (ItemStackWrapper)otherobj;
            if (this.stack.func_77973_b().equals(other.stack.func_77973_b()) && this.stack.func_77960_j() == other.stack.func_77960_j()) {
                if (this.stack.field_77990_d == null && other.stack.field_77990_d == null) {
                    return true;
                }
                if (this.stack.field_77990_d == null ^ other.stack.field_77990_d == null) {
                    return false;
                }
                if (this.stack.field_77990_d.equals((Object)other.stack.field_77990_d)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int h = this.stack.func_77973_b().hashCode();
        if (this.stack.field_77990_d != null) {
            h ^= this.stack.field_77990_d.hashCode();
        }
        return h ^ this.stack.func_77960_j();
    }
    
    @Override
    public String toString() {
        return this.stack.toString();
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import forestry.api.genetics.IAlleleFloat;

public class AlleleFloat extends Allele implements IAlleleFloat
{
    private float value;
    
    public AlleleFloat(final String moniker, final boolean dom, final float val) {
        super(moniker, dom);
        this.value = val;
    }
    
    public float getValue() {
        return this.value;
    }
}

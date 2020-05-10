// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import forestry.api.genetics.IAlleleInteger;

public class AlleleInteger extends Allele implements IAlleleInteger
{
    private int value;
    
    public AlleleInteger(final String moniker, final boolean dom, final int val) {
        super(moniker, dom);
        this.value = val;
    }
    
    public int getValue() {
        return this.value;
    }
}

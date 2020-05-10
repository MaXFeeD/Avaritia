// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;

public class ModelInfinitato extends ModelBase
{
    ModelRenderer potato;
    
    public ModelInfinitato() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        (this.potato = new ModelRenderer((ModelBase)this, 0, 0)).func_78789_a(0.0f, 0.0f, 0.0f, 8, 12, 8);
        this.potato.func_78793_a(-4.0f, 12.0f, -4.0f);
        this.potato.func_78787_b(64, 32);
    }
    
    public void render() {
        this.potato.func_78785_a(0.0625f);
    }
}

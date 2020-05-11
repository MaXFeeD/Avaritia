// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;
import net.minecraft.world.Teleporter;

public class BasicTeleporter extends Teleporter
{
    public BasicTeleporter(final WorldServer world) {
        super(world);
    }
    
    public boolean func_85188_a(final Entity entity) {
        return true;
    }
    
    public void func_77185_a(final Entity entity, final double x, final double y, final double z, final float yaw) {
        final int newX = MathHelper.func_76128_c(entity.field_70165_t);
        final int newY = MathHelper.func_76128_c(entity.field_70163_u);
        final int newZ = MathHelper.func_76128_c(entity.field_70161_v);
        entity.func_70012_b((double)newX, (double)newY, (double)newZ, entity.field_70177_z, 0.0f);
    }
}

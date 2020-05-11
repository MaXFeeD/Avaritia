// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile;

import java.util.Iterator;
import java.util.List;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public final class VanillaPacketDispatcher
{
    public static void dispatchTEToNearbyPlayers(final TileEntity tile) {
        final World world = tile.func_145831_w();
        final List players = world.field_73010_i;
        for (final Object player : players) {
            if (player instanceof EntityPlayerMP) {
                final EntityPlayerMP mp = (EntityPlayerMP)player;
                if (pointDistancePlane(mp.field_70165_t, mp.field_70161_v, tile.field_145851_c + 0.5, tile.field_145849_e + 0.5) >= 64.0f) {
                    continue;
                }
                ((EntityPlayerMP)player).field_71135_a.func_147359_a(tile.func_145844_m());
            }
        }
    }
    
    public static void dispatchTEToNearbyPlayers(final World world, final int x, final int y, final int z) {
        final TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            dispatchTEToNearbyPlayers(tile);
        }
    }
    
    public static float pointDistancePlane(final double x1, final double y1, final double x2, final double y2) {
        return (float)Math.hypot(x1 - x2, y1 - y2);
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Teleporter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;

public class TeleportHelper
{
    public static void travelToDimension(final Entity ent, final int x, final int y, final int z, final int dim) {
        if (ent instanceof EntityPlayerMP) {
            final MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            final WorldServer newWorldServer = minecraftserver.func_71218_a(dim);
            minecraftserver.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)ent, dim, (Teleporter)new BasicTeleporter(newWorldServer));
            ((EntityPlayer)ent).func_70634_a(x + 0.5, y + 0.5, z + 0.5);
            return;
        }
        if (!ent.field_70170_p.field_72995_K && !ent.field_70128_L) {
            ent.field_70170_p.field_72984_F.func_76320_a("changeDimension");
            final MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            final int j = ent.field_71093_bK;
            final WorldServer worldserver = minecraftserver.func_71218_a(j);
            WorldServer worldserver2 = minecraftserver.func_71218_a(dim);
            ent.field_71093_bK = dim;
            if (j == 1 && dim == 1) {
                worldserver2 = minecraftserver.func_71218_a(0);
                ent.field_71093_bK = 0;
            }
            ent.field_70170_p.func_72900_e(ent);
            ent.field_70128_L = false;
            ent.field_70170_p.field_72984_F.func_76320_a("reposition");
            minecraftserver.func_71203_ab().func_82448_a(ent, j, worldserver, worldserver2);
            ent.field_70170_p.field_72984_F.func_76318_c("reloading");
            final Entity entity = EntityList.func_75620_a(EntityList.func_75621_b(ent), (World)worldserver2);
            if (entity != null) {
                entity.func_82141_a(ent, true);
                if (j == 1 && dim == 1) {
                    entity.func_70012_b(x + 0.5, y + 0.5, z + 0.5, entity.field_70177_z, entity.field_70125_A);
                }
                worldserver2.func_72838_d(entity);
                entity.func_70107_b(x + 0.5, y + 0.5, z + 0.5);
            }
            ent.field_70128_L = true;
            ent.field_70170_p.field_72984_F.func_76319_b();
            worldserver.func_82742_i();
            worldserver2.func_82742_i();
            ent.field_70170_p.field_72984_F.func_76319_b();
        }
    }
    
    public static void travelToOrFromAlfheim(final EntityPlayer player) {
        if (DimensionManager.getWorld(0) == player.field_70170_p) {
            travelToDimension((Entity)player, 0, 85, 0, WorldProviderAlfheim.dimensionID);
        }
        else {
            sendPlayerHome(player);
        }
    }
    
    public static void sendPlayerHome(final EntityPlayer player) {
        final World world = (World)DimensionManager.getWorld(0);
        ChunkCoordinates coord = player.getBedLocation(0);
        if (coord == null) {
            coord = world.func_72861_E();
        }
        coord = forceLocation(world, coord);
        if (coord == null) {
            coord = world.func_72861_E();
        }
        travelToDimension((Entity)player, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, 0);
        while (!world.func_72945_a((Entity)player, player.field_70121_D).isEmpty()) {
            player.func_70634_a(player.field_70165_t, player.field_70163_u + 1.0, player.field_70161_v);
        }
    }
    
    public static ChunkCoordinates forceLocation(final World world, final ChunkCoordinates coord) {
        return null;
    }
}

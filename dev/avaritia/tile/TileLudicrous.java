// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.tile;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.Packet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileLudicrous extends TileEntity
{
    public void func_145841_b(final NBTTagCompound tag) {
        super.func_145841_b(tag);
        this.writeCustomNBT(tag);
    }
    
    public void func_145839_a(final NBTTagCompound tag) {
        super.func_145839_a(tag);
        this.readCustomNBT(tag);
    }
    
    public void writeCustomNBT(final NBTTagCompound tag) {
    }
    
    public void readCustomNBT(final NBTTagCompound tag) {
    }
    
    public Packet func_145844_m() {
        final NBTTagCompound tag = new NBTTagCompound();
        this.writeCustomNBT(tag);
        return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, tag);
    }
    
    public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        this.readCustomNBT(packet.func_148857_g());
    }
}

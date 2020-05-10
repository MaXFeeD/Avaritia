// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.WorldProvider;

public class WorldProviderAlfheim extends WorldProvider
{
    public static int dimensionID;
    
    public void func_76572_b() {
        this.field_76578_c = (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.field_76778_j, 0.0f);
        this.field_76575_d = true;
        this.field_76576_e = false;
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.setCloudRenderer((IRenderHandler)new ApocalypseCloudRenderer());
            this.setSkyRenderer((IRenderHandler)new ApocalypseSkyRenderer());
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3 func_76562_b(final float p_76562_1_, final float p_76562_2_) {
        return Vec3.func_72443_a(0.1, 0.04, 0.0);
    }
    
    public IChunkProvider func_76555_c() {
        return (IChunkProvider)new ChunkProviderAlfheim(this.field_76579_a, this.field_76579_a.func_72905_C());
    }
    
    public boolean func_76569_d() {
        return false;
    }
    
    public boolean func_76566_a(final int x, final int z) {
        return true;
    }
    
    public float func_76563_a(final long tick, final float partialTick) {
        return 1.0f;
    }
    
    public boolean func_76567_e() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_76568_b(final int x, final int z) {
        return false;
    }
    
    public String func_80007_l() {
        return "Alfheim Eternal";
    }
    
    public float getSunBrightnessFactor(final float par1) {
        return 0.6f;
    }
    
    public ChunkCoordinates func_76554_h() {
        return new ChunkCoordinates(0, 85, 0);
    }
    
    static {
        WorldProviderAlfheim.dimensionID = 13;
    }
}

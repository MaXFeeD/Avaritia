// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.world.ChunkPosition;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import vazkii.botania.common.block.ModBlocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.World;
import java.util.Random;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderAlfheim implements IChunkProvider
{
    public static final int CITYRADIUS = 600;
    public static final int FLATRADIUS = 620;
    public static final int HILLRADIUS = 870;
    public final long seed;
    public Random rand;
    public final World world;
    protected AlfheimNoise noise;
    protected MapGenAlfheimRavines cracks;
    protected MapGenCity city;
    public List spawnList;
    
    public ChunkProviderAlfheim(final World world, final long seed) {
        this.cracks = new MapGenAlfheimRavines();
        this.city = new MapGenCity();
        this.spawnList = new ArrayList();
        this.seed = seed;
        this.rand = new Random(this.seed);
        this.world = world;
        this.noise = new AlfheimNoise(this.rand.nextLong());
    }
    
    public boolean func_73149_a(final int chunkX, final int chunkZ) {
        return true;
    }
    
    public Chunk func_73154_d(final int chunkX, final int chunkZ) {
        this.rand.setSeed(chunkX * 1497631652873L + chunkZ * 902659997773L);
        final Block[] blocks = new Block[65536];
        final byte[] meta = new byte[65536];
        this.fillChunkArray(chunkX, chunkZ, blocks, meta);
        final int cityrad = (int)Math.ceil(37.5) + 1;
        if (chunkX * chunkX + chunkZ * chunkZ <= cityrad) {
            this.city.func_151539_a((IChunkProvider)this, this.world, chunkX, chunkZ, blocks);
        }
        final Chunk chunk = new Chunk(this.world, blocks, meta, chunkX, chunkZ);
        final byte[] biomes = chunk.func_76605_m();
        for (int i = 0; i < biomes.length; ++i) {
            biomes[i] = (byte)BiomeGenBase.field_76769_d.field_76756_M;
        }
        chunk.func_76603_b();
        return chunk;
    }
    
    public void fillChunkArray(final int chunkX, final int chunkZ, final Block[] blocks, final byte[] meta) {
        for (int x = 0; x <= 15; ++x) {
            for (int z = 0; z <= 15; ++z) {
                final int blockX = chunkX * 16 + x;
                final int blockZ = chunkZ * 16 + z;
                final double dist = Math.sqrt(blockX * blockX + blockZ * blockZ);
                int threshold = 64;
                double mix = (dist - 620.0) / 250.0;
                mix = Math.max(0.0, Math.min(1.0, mix));
                mix = mix * mix * (3.0 - 2.0 * mix);
                final double height = this.noise.getJordanDefault(chunkX * 16 + x, chunkZ * 16 + z);
                final double hill = mix * height * 64.0;
                final double lumps = Math.min(3.0, (1.0 - mix) * height * 5.0);
                threshold = MathHelper.func_76128_c(64.0 + hill + lumps);
                for (int y = 0; y < 254; ++y) {
                    final int pos = y | z << 8 | x << 12;
                    if (y <= threshold) {
                        if (y < threshold - (15 + this.rand.nextInt(3))) {
                            blocks[pos] = ModBlocks.livingrock;
                            meta[pos] = 0;
                        }
                        else {
                            blocks[pos] = AlfheimBlocks.deadrock;
                            meta[pos] = 0;
                        }
                    }
                }
            }
        }
    }
    
    public Chunk func_73158_c(final int chunkX, final int chunkZ) {
        return this.func_73154_d(chunkX, chunkZ);
    }
    
    public void func_73153_a(final IChunkProvider provider, final int chunkX, final int chunkZ) {
        this.rand.setSeed(this.world.func_72905_C());
        final long xseed = this.rand.nextLong() / 2L * 2L + 1L;
        final long zseed = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * xseed + chunkZ * zseed ^ this.world.func_72905_C());
        final int cityrad = (int)Math.ceil(37.5) + 1;
        if (Math.sqrt(chunkX * chunkX + chunkZ * chunkZ) <= cityrad) {
            this.city.func_75051_a(this.world, this.rand, chunkX, chunkZ);
        }
    }
    
    public boolean func_73151_a(final boolean mode, final IProgressUpdate progress) {
        return true;
    }
    
    public boolean func_73156_b() {
        return false;
    }
    
    public boolean func_73157_c() {
        return true;
    }
    
    public String func_73148_d() {
        return "AlfheimEternalSource";
    }
    
    public List func_73155_a(final EnumCreatureType type, final int x, final int y, final int z) {
        return this.spawnList;
    }
    
    public ChunkPosition func_147416_a(final World world, final String structureType, final int x, final int y, final int z) {
        return null;
    }
    
    public int func_73152_e() {
        return 0;
    }
    
    public void func_82695_e(final int chunkX, final int chunkZ) {
        final int cityrad = (int)Math.ceil(37.5) + 1;
        if (chunkX * chunkX + chunkZ * chunkZ <= cityrad) {
            this.city.func_151539_a((IChunkProvider)this, this.world, chunkX, chunkZ, (Block[])null);
        }
    }
    
    public void func_104112_b() {
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import java.util.List;
import java.util.Collections;
import net.minecraft.util.ChunkCoordinates;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.MapGenStructure;

public class MapGenCity extends MapGenStructure
{
    public MapGenCity() {
        this.field_75040_a = (int)Math.ceil(37.0);
    }
    
    public String func_143025_a() {
        return "AlfCity";
    }
    
    protected boolean func_75047_a(final int chunkX, final int chunkZ) {
        return chunkX == 0 && chunkZ == 0;
    }
    
    protected StructureStart func_75049_b(final int chunkX, final int chunkZ) {
        return new Start(this.field_75039_c, this.field_75038_b, chunkX, chunkZ);
    }
    
    public static class Start extends StructureStart
    {
        protected int CITYRADIUS;
        protected int ROADSIZE;
        protected int BLOCKSIZE;
        protected int BLOCKS;
        
        public Start() {
            this.ROADSIZE = 8;
            this.BLOCKSIZE = 24;
        }
        
        public Start(final World world, final Random rand, final int chunkX, final int chunkZ) {
            super(chunkX, chunkZ);
            this.ROADSIZE = 8;
            this.BLOCKSIZE = 24;
            this.CITYRADIUS = 600;
            this.BLOCKS = MathHelper.func_76128_c((this.CITYRADIUS * 2 + this.ROADSIZE) / (double)(this.BLOCKSIZE + this.ROADSIZE));
            if (this.BLOCKS % 2 != 0) {
                --this.BLOCKS;
            }
            final int blockwidth = this.BLOCKS * this.BLOCKSIZE + (this.BLOCKS - 1) * this.ROADSIZE;
            final int origin = -blockwidth / 2;
            final List<ChunkCoordinates> cells = new ArrayList<ChunkCoordinates>();
            final int[] cellfill = new int[this.BLOCKS * this.BLOCKS];
            for (int i = 0; i < cellfill.length; ++i) {
                cellfill[i] = 0;
            }
            for (int bx = 0; bx < this.BLOCKS; ++bx) {
                for (int bz = 0; bz < this.BLOCKS; ++bz) {
                    if (bx <= this.BLOCKS / 2 - 3 || bx >= this.BLOCKS / 2 + 2 || bz <= this.BLOCKS / 2 - 3 || bz >= this.BLOCKS / 2 + 2) {
                        final int x = origin + (this.BLOCKSIZE + this.ROADSIZE) * bx;
                        final int z = origin + (this.BLOCKSIZE + this.ROADSIZE) * bz;
                        final double cx = x + this.BLOCKSIZE * 0.5;
                        final double cz = z + this.BLOCKSIZE * 0.5;
                        final double rad = Math.sqrt(cx * cx + cz * cz) / (this.CITYRADIUS - this.BLOCKSIZE * 0.75);
                        if (rad < 1.0 && rand.nextDouble() > rad - 0.4) {
                            cells.add(new ChunkCoordinates(bx, bz, 0));
                            cellfill[bz * this.BLOCKS + bx] = 1;
                        }
                    }
                }
            }
            Collections.shuffle(cells);
        Label_0793_Outer:
            while (cells.size() > 0) {
                final ChunkCoordinates cell = cells.remove(0);
                final int bx2 = cell.field_71574_a;
                final int bz2 = cell.field_71572_b;
                final int index = bz2 * this.BLOCKS + bx2;
                if (cellfill[index] != 1) {
                    continue Label_0793_Outer;
                }
                final int dx = this.BLOCKS / 2 - bx2;
                final int dz = this.BLOCKS / 2 - bz2;
                final double dist = Math.sqrt(dx * dx + dz * dz) / (this.BLOCKS * 0.5);
                int width = 1;
                int height = 1;
                if (rand.nextDouble() < 1.5 * (1.0 - dist) - 0.05) {
                    ++width;
                    ++height;
                    if (rand.nextDouble() < 0.1) {
                        ++width;
                    }
                    if (rand.nextDouble() < 0.1) {
                        ++height;
                    }
                }
                while (true) {
                    while (width != 1 || height != 1) {
                        boolean ok = true;
                        for (int cx2 = 0; cx2 < width; ++cx2) {
                            for (int cz2 = 0; cz2 < height; ++cz2) {
                                if (bx2 + cx2 >= this.BLOCKS || bz2 + cz2 >= this.BLOCKS) {
                                    ok = false;
                                    break;
                                }
                                if ((bx2 < this.BLOCKS / 2 && bx2 + cx2 >= this.BLOCKS / 2) || (bz2 < this.BLOCKS / 2 && bz2 + cz2 >= this.BLOCKS / 2)) {
                                    ok = false;
                                    break;
                                }
                                final int cindex = (bz2 + cz2) * this.BLOCKS + (bx2 + cx2);
                                if (cellfill[cindex] != 1) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                        if (ok) {
                            for (int cx3 = 0; cx3 < width; ++cx3) {
                                for (int cz3 = 0; cz3 < height; ++cz3) {
                                    final int cindex2 = (bz2 + cz3) * this.BLOCKS + (bx2 + cx3);
                                    cellfill[cindex2] = 2;
                                }
                            }
                            final double size = Math.sqrt(width * height);
                            final int floors = (int)Math.round(0.5 + size * 1.45 * (1.0 - dist + 0.3) * (rand.nextDouble() * rand.nextDouble()) + rand.nextDouble() * (1.0 - dist) * 0.6 + rand.nextDouble() * 0.2);
                            final int x2 = origin + (this.BLOCKSIZE + this.ROADSIZE) * bx2;
                            final int z2 = origin + (this.BLOCKSIZE + this.ROADSIZE) * bz2;
                            final int structurewidth = width * this.BLOCKSIZE + (width - 1) * this.ROADSIZE;
                            final int structureheight = height * this.BLOCKSIZE + (height - 1) * this.ROADSIZE;
                            final ComponentCityParts.CityRuin ruin = new ComponentCityParts.CityRuin(rand, x2, z2, structurewidth, structureheight, floors);
                            this.field_75075_a.add(ruin);
                            continue Label_0793_Outer;
                        }
                        if (width > 1 && height > 1) {
                            if (rand.nextBoolean()) {
                                --width;
                            }
                            else {
                                --height;
                            }
                        }
                        else if (width > 1) {
                            --width;
                        }
                        else {
                            --height;
                        }
                    }
                    continue;
                }
            }
            this.func_75072_c();
        }
    }
}

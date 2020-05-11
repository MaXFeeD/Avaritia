// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Ruin
{
    public BlockArray blocks;
    public double ruinLevel;
    public Random rand;
    public BlockRuinPalette palette;
    public int xSize;
    public int ySize;
    public int zSize;
    public int xOffset;
    public int yOffset;
    public int zOffset;
    
    public Ruin(final BlockArray blocks, final double ruinLevel, final Random rand) {
        this(blocks, ruinLevel, rand, new BlockRuinPalette());
    }
    
    public Ruin(final BlockArray blocks, final double ruinLevel, final Random rand, final BlockRuinPalette palette) {
        this(blocks, ruinLevel, rand, blocks.xSize, blocks.ySize, blocks.zSize, 0, 0, 0, palette);
    }
    
    public Ruin(final BlockArray blocks, final double ruinLevel, final Random rand, final int xSize, final int ySize, final int zSize, final int xOffset, final int yOffset, final int zOffset) {
        this(blocks, ruinLevel, rand, xSize, ySize, zSize, xOffset, yOffset, zOffset, new BlockRuinPalette());
    }
    
    public Ruin(final BlockArray blocks, final double ruinLevel, final Random rand, final int xSize, final int ySize, final int zSize, final int xOffset, final int yOffset, final int zOffset, final BlockRuinPalette palette) {
        this.blocks = blocks;
        this.ruinLevel = ruinLevel;
        this.rand = rand;
        this.palette = palette;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        final int volume = this.xSize * this.ySize * this.zSize;
        for (int breaks = (int)Math.ceil(volume / 16.0 * ruinLevel), i = 0; i < breaks; ++i) {
            final int x = rand.nextInt(this.xSize) - this.xOffset;
            final int z = rand.nextInt(this.zSize) - this.zOffset;
            final double ylevel = 1.0 - rand.nextDouble() * rand.nextDouble();
            final int y = (int)Math.round(ylevel * this.ySize) - this.yOffset;
            final double rad = (6.0 * rand.nextDouble() + 1.0) * ylevel * (0.1 + 0.9 * ruinLevel) + 0.45 + 0.55 * ruinLevel;
            this.ruinArea(x, y, z, rad);
        }
        this.disconnectionCheck();
        this.disconnectionCheck();
    }
    
    protected void dropBlock(final int ox, final int oy, final int oz) {
        this.dropBlock(ox, oy, oz, 0);
    }
    
    protected void dropBlock(final int ox, final int oy, final int oz, final int initialdistance) {
        int x = ox;
        int y = oy;
        int z = oz;
        final BlockMeta block = this.blocks.getBlock(x, y, z);
        if (block == null) {
            return;
        }
        this.blocks.setBlock(ox, oy, oz, null);
        int falldistance = initialdistance;
        while (true) {
            final BlockMeta below = this.blocks.getBlock(x, y - 1, z);
            if (this.blocks.outOfBounds(x, y - 1, z)) {
                this.blocks.setBlock(x, y, z, block);
                break;
            }
            if (below == null) {
                --y;
                ++falldistance;
            }
            else {
                boolean slide = false;
                int slidedir = 0;
                final List<Integer> falldirs = new ArrayList<Integer>();
                if (!this.blocks.outOfBounds(x - 1, y, z) && this.blocks.getBlock(x - 1, y, z) == null && this.blocks.getBlock(x - 1, y - 1, z) == null) {
                    falldirs.add(0);
                }
                if (!this.blocks.outOfBounds(x + 1, y, z) && this.blocks.getBlock(x + 1, y, z) == null && this.blocks.getBlock(x + 1, y - 1, z) == null) {
                    falldirs.add(1);
                }
                if (!this.blocks.outOfBounds(x, y, z - 1) && this.blocks.getBlock(x, y, z - 1) == null && this.blocks.getBlock(x, y - 1, z - 1) == null) {
                    falldirs.add(2);
                }
                if (!this.blocks.outOfBounds(x, y, z + 1) && this.blocks.getBlock(x, y, z + 1) == null && this.blocks.getBlock(x, y - 1, z + 1) == null) {
                    falldirs.add(3);
                }
                if (!falldirs.isEmpty()) {
                    slide = true;
                    slidedir = falldirs.get(this.rand.nextInt(falldirs.size()));
                }
                if (!slide) {
                    final BlockRuinPalette.BlockPaletteInfo blockinfo = this.palette.get(block);
                    final double breakchance = this.fallBreakChance(falldistance) * blockinfo.breakchance;
                    if (this.rand.nextDouble() <= breakchance) {
                        this.blocks.setBlock(x, y, z, blockinfo.broken);
                    }
                    else if (blockinfo.hardness >= 1.0) {
                        this.blocks.setBlock(x, y, z, block);
                    }
                    this.impact(x, y - 1, z, falldistance, block);
                    break;
                }
                this.impact(x, y - 1, z, falldistance / 4, block);
                if (slidedir == 0) {
                    --x;
                }
                else if (slidedir == 1) {
                    ++x;
                }
                else if (slidedir == 2) {
                    --z;
                }
                else {
                    ++z;
                }
            }
        }
    }
    
    protected void impact(final int x, final int y, final int z, final int falldistance, final BlockMeta fallingblock) {
        final BlockMeta block = this.blocks.getBlock(x, y, z);
        final BlockRuinPalette.BlockPaletteInfo blockinfo = this.palette.get(block);
        final double smashchance = this.fallBreakChance(falldistance) * blockinfo.smashchance;
        if (this.rand.nextDouble() <= smashchance) {
            this.blocks.setBlock(x, y, z, null);
            this.dropBlock(x, y + 1, z, falldistance / 3);
        }
        double breakchance = this.fallBreakChance(falldistance) * blockinfo.breakchance;
        if (this.blocks.getBlock(x, y - 1, z) != null) {
            breakchance *= 0.75;
        }
        if (this.rand.nextDouble() <= breakchance) {
            if (blockinfo.broken != null) {
                this.blocks.setBlock(x, y, z, blockinfo.broken);
                this.impact(x, y, z, falldistance / 2, fallingblock);
            }
            else {
                this.dropBlock(x, y, z);
                this.dropBlock(x, y + 1, z, falldistance / 3);
            }
        }
    }
    
    protected void ruinArea(final int ox, final int oy, final int oz, final double radius) {
        final int range = (int)Math.ceil(radius * 2.0);
        final int offset = range / 2;
        for (int y = 0; y <= range; ++y) {
            final List<Coord> coords = new ArrayList<Coord>();
            for (int x = 0; x <= range; ++x) {
                if (x >= 0) {
                    if (x < this.blocks.xSize) {
                        for (int z = 0; z <= range; ++z) {
                            if (z >= 0) {
                                if (z < this.blocks.zSize) {
                                    final BlockMeta block = this.blocks.getBlock(ox + x - offset, oy + y - offset, oz + z - offset);
                                    if (block != null) {
                                        final BlockRuinPalette.BlockPaletteInfo blockinfo = this.palette.get(block);
                                        final double rx = x - range / 2;
                                        final double ry = y - range / 2;
                                        final double rz = z - range / 2;
                                        final double dist = Math.sqrt(rx * rx + ry * ry + rz * rz) / radius;
                                        if (dist <= 1.0) {
                                            if (dist > 0.75) {
                                                final double dchance = (dist - 0.75) * 4.0;
                                                if (this.rand.nextDouble() > dchance) {
                                                    coords.add(new Coord(x, y, z));
                                                }
                                                else if (blockinfo.broken != null || blockinfo.hardness < 1.0) {
                                                    this.blocks.setBlock(ox + x - offset, oy + y - offset, oz + z - offset, blockinfo.broken);
                                                }
                                            }
                                            else {
                                                coords.add(new Coord(x, y, z));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Collections.shuffle(coords, this.rand);
            for (int i = 0; i < coords.size(); ++i) {
                final Coord c = coords.get(i);
                this.dropBlock(ox + c.x - range / 2, oy + c.y - range / 2, oz + c.z - range / 2);
            }
        }
    }
    
    protected void disconnectionCheck() {
        final int ox = this.blocks.xSize / 2;
        final int oz = this.blocks.zSize / 2;
        final boolean[] connected = new boolean[this.blocks.length];
        final List<Coord> open = new ArrayList<Coord>();
        open.add(new Coord(ox, 0, oz));
        while (!open.isEmpty()) {
            final Coord c = open.remove(open.size() - 1);
            if (!this.blocks.outOfBounds(c.x, c.y, c.z) && this.blocks.getBlock(c.x, c.y, c.z) != null) {
                connected[this.blocks.index(c.x, c.y, c.z)] = true;
                if (!this.blocks.outOfBounds(c.x - 1, c.y, c.z) && this.blocks.getBlock(c.x - 1, c.y, c.z) != null && !connected[this.blocks.index(c.x - 1, c.y, c.z)]) {
                    open.add(new Coord(c.x - 1, c.y, c.z));
                }
                if (!this.blocks.outOfBounds(c.x + 1, c.y, c.z) && this.blocks.getBlock(c.x + 1, c.y, c.z) != null && !connected[this.blocks.index(c.x + 1, c.y, c.z)]) {
                    open.add(new Coord(c.x + 1, c.y, c.z));
                }
                if (!this.blocks.outOfBounds(c.x, c.y, c.z - 1) && this.blocks.getBlock(c.x, c.y, c.z - 1) != null && !connected[this.blocks.index(c.x, c.y, c.z - 1)]) {
                    open.add(new Coord(c.x, c.y, c.z - 1));
                }
                if (!this.blocks.outOfBounds(c.x, c.y, c.z + 1) && this.blocks.getBlock(c.x, c.y, c.z + 1) != null && !connected[this.blocks.index(c.x, c.y, c.z + 1)]) {
                    open.add(new Coord(c.x, c.y, c.z + 1));
                }
                if (!this.blocks.outOfBounds(c.x, c.y - 1, c.z) && this.blocks.getBlock(c.x, c.y - 1, c.z) != null && !connected[this.blocks.index(c.x, c.y - 1, c.z)]) {
                    open.add(new Coord(c.x, c.y - 1, c.z));
                }
                if (this.blocks.outOfBounds(c.x, c.y + 1, c.z) || this.blocks.getBlock(c.x, c.y + 1, c.z) == null || connected[this.blocks.index(c.x, c.y + 1, c.z)]) {
                    continue;
                }
                open.add(new Coord(c.x, c.y + 1, c.z));
            }
        }
        for (int y = 0; y <= this.blocks.ySize; ++y) {
            final List<Coord> coords = new ArrayList<Coord>();
            for (int x = 0; x <= this.blocks.xSize; ++x) {
                for (int z = 0; z <= this.blocks.zSize; ++z) {
                    if (this.blocks.getBlock(x, y, z) != null && !connected[this.blocks.index(x, y, z)]) {
                        coords.add(new Coord(x, y, z));
                    }
                }
            }
            Collections.shuffle(coords, this.rand);
            for (int i = 0; i < coords.size(); ++i) {
                final Coord c2 = coords.get(i);
                this.dropBlock(c2.x, c2.y, c2.z);
            }
        }
    }
    
    protected double fallBreakChance(final int distance) {
        final double maxdist = 2.0;
        return Math.min(distance, maxdist) / maxdist;
    }
    
    public static class BlockArray
    {
        public BlockMeta[] blocks;
        public int xSize;
        public int ySize;
        public int zSize;
        public int length;
        
        public BlockArray(final int xSize, final int ySize, final int zSize) {
            this.length = xSize * ySize * zSize;
            this.blocks = new BlockMeta[this.length];
            this.xSize = xSize;
            this.ySize = ySize;
            this.zSize = zSize;
        }
        
        public boolean outOfBounds(final int x, final int y, final int z) {
            return x < 0 || x >= this.xSize || y < 0 || y >= this.ySize || z < 0 || z >= this.zSize;
        }
        
        public void setBlock(final int x, final int y, final int z, final BlockMeta block) {
            if (this.outOfBounds(x, y, z)) {
                return;
            }
            this.blocks[this.index(x, y, z)] = block;
        }
        
        public BlockMeta getBlock(final int x, final int y, final int z) {
            if (this.outOfBounds(x, y, z)) {
                return null;
            }
            return this.blocks[this.index(x, y, z)];
        }
        
        public void fillBlocks(final int x, final int y, final int z, final int x2, final int y2, final int z2, final BlockMeta block) {
            for (int ix = x; ix <= x2; ++ix) {
                for (int iy = y; iy <= y2; ++iy) {
                    for (int iz = z; iz <= z2; ++iz) {
                        this.setBlock(ix, iy, iz, block);
                    }
                }
            }
        }
        
        protected int index(final int x, final int y, final int z) {
            return y * this.zSize * this.xSize + z * this.xSize + x;
        }
    }
    
    public static class BlockMeta
    {
        public Block block;
        public byte meta;
        
        public BlockMeta(final Block block, final int meta) {
            this.block = block;
            this.meta = (byte)(meta & 0xFF);
        }
        
        @Override
        public String toString() {
            return this.block.toString() + "#" + this.meta;
        }
        
        @Override
        public int hashCode() {
            return this.block.hashCode() + this.meta;
        }
    }
    
    public static class BlockRuinPalette
    {
        protected Map<BlockMeta, BlockPaletteInfo> infomap;
        
        public BlockRuinPalette() {
            this.infomap = new HashMap<BlockMeta, BlockPaletteInfo>();
        }
        
        public BlockPaletteInfo get(final BlockMeta block) {
            if (this.infomap.containsKey(block)) {
                return this.infomap.get(block);
            }
            return BlockPaletteInfo.DEFAULT;
        }
        
        public void set(final BlockMeta block, final double hardness, final double breakchance, final double smashchance) {
            this.set(block, hardness, breakchance, smashchance, null);
        }
        
        public void set(final BlockMeta block, final double hardness, final double breakchance, final double smashchance, final BlockMeta broken) {
            this.infomap.put(block, new BlockPaletteInfo(hardness, breakchance, smashchance, broken));
        }
        
        public static class BlockPaletteInfo
        {
            public static final BlockPaletteInfo DEFAULT;
            public final double hardness;
            public final double breakchance;
            public final double smashchance;
            public final BlockMeta broken;
            
            public BlockPaletteInfo(final double hardness, final double breakchance, final double smashchance, final BlockMeta broken) {
                this.hardness = hardness;
                this.breakchance = breakchance;
                this.smashchance = smashchance;
                this.broken = broken;
            }
            
            static {
                DEFAULT = new BlockPaletteInfo(1.0, 0.2, 0.2, null);
            }
        }
    }
    
    protected static class Coord
    {
        int x;
        int y;
        int z;
        
        public Coord(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}

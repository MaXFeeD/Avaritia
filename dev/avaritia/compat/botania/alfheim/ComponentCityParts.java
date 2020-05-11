// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.init.Blocks;
import fox.spiteful.avaritia.Lumberjack;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.World;
import java.util.Random;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class ComponentCityParts
{
    public static void registerParts() {
        MapGenStructureIO.func_143031_a((Class)CityRuin.class, "AlfRuin");
    }
    
    public static class CityRuin extends CityPart
    {
        public static final int outline = 5;
        
        public CityRuin() {
        }
        
        public CityRuin(final Random rand, final int x, final int z, final int xsize, final int zsize, final int floors) {
            super(rand, x - 5, 64, z - 5, xsize + 10, floors * 7 + 3 + 8, zsize + 10);
        }
        
        public boolean func_74875_a(final World world, final Random rand, final StructureBoundingBox bounds) {
            if (!this.processHeight(world, bounds, -3)) {
                return false;
            }
            this.genRand.setSeed(this.randSeed);
            final int border = 16;
            final int minx = Math.max(this.field_74887_e.field_78897_a, bounds.field_78897_a - border);
            final int maxx = Math.min(this.field_74887_e.field_78893_d, bounds.field_78893_d + border);
            final int minz = Math.max(this.field_74887_e.field_78896_c, bounds.field_78896_c - border);
            final int maxz = Math.min(this.field_74887_e.field_78892_f, bounds.field_78892_f + border);
            final int xsize = this.field_74887_e.func_78883_b();
            final int ysize = this.field_74887_e.func_78882_c();
            final int zsize = this.field_74887_e.func_78880_d();
            final int axsize = maxx - minx + 1;
            final int aysize = ysize;
            final int azsize = maxz - minz + 1;
            final int xoffset = minx - this.field_74887_e.field_78897_a;
            final int zoffset = minz - this.field_74887_e.field_78896_c;
            Lumberjack.info("xyz: " + this.field_74887_e + ", chunk: " + bounds + ", size: " + xsize + "," + ysize + "," + zsize + ",  mxmy: " + minx + "," + minz + "; " + maxx + "," + maxz + ", asize: " + axsize + "," + aysize + "," + azsize + ",  offset: " + xoffset + "," + zoffset);
            final Ruin.BlockArray blocks = new Ruin.BlockArray(axsize, aysize, azsize);
            blocks.fillBlocks(5 - xoffset, 0, 5 - zoffset, xsize - 5 - 1 - xoffset, ysize, zsize - 5 - 1 - zoffset, new Ruin.BlockMeta(Blocks.field_150347_e, 0));
            new Ruin(blocks, 0.3 + this.genRand.nextDouble() * 0.7, this.genRand, xsize, ysize, zsize, xoffset, 0, zoffset);
            for (int x = 0; x < axsize; ++x) {
                for (int y = 0; y < aysize; ++y) {
                    for (int z = 0; z < azsize; ++z) {
                        final Ruin.BlockMeta block = blocks.getBlock(x, y, z);
                        if (block != null) {
                            this.func_151550_a(world, block.block, (int)block.meta, x + xoffset, y, z + zoffset, bounds);
                        }
                    }
                }
            }
            return true;
        }
    }
    
    public abstract static class CityPart extends StructureComponent
    {
        protected int partSizeX;
        protected int partSizeY;
        protected int partSizeZ;
        protected int partPosY;
        protected Random genRand;
        protected long randSeed;
        
        public CityPart() {
            this.partPosY = -1;
        }
        
        protected CityPart(final Random rand, final int x, final int y, final int z, final int xsize, final int ysize, final int zsize) {
            super(0);
            this.partPosY = -1;
            this.partSizeX = xsize;
            this.partSizeY = ysize;
            this.partSizeZ = zsize;
            this.field_74885_f = 0;
            this.field_74887_e = new StructureBoundingBox(x, y, z, x + xsize - 1, y + ysize - 1, z + zsize - 1);
            this.randSeed = rand.nextLong();
            this.genRand = new Random(this.randSeed);
        }
        
        protected void func_143012_a(final NBTTagCompound tag) {
            tag.func_74768_a("Width", this.partSizeX);
            tag.func_74768_a("Height", this.partSizeY);
            tag.func_74768_a("Depth", this.partSizeZ);
            tag.func_74768_a("HPos", this.partPosY);
            tag.func_74772_a("RSeed", this.randSeed);
        }
        
        protected void func_143011_b(final NBTTagCompound tag) {
            this.partSizeX = tag.func_74762_e("Width");
            this.partSizeY = tag.func_74762_e("Height");
            this.partSizeZ = tag.func_74762_e("Depth");
            this.partPosY = tag.func_74762_e("HPos");
            this.randSeed = tag.func_74763_f("RSeed");
            this.genRand = new Random(this.randSeed);
        }
        
        protected boolean processHeight(final World world, final StructureBoundingBox bounds, final int offset) {
            if (this.partPosY >= 0) {
                return true;
            }
            int j = 0;
            int k = 0;
            for (int l = this.field_74887_e.field_78896_c; l <= this.field_74887_e.field_78892_f; ++l) {
                for (int i1 = this.field_74887_e.field_78897_a; i1 <= this.field_74887_e.field_78893_d; ++i1) {
                    if (bounds.func_78890_b(i1, 64, l)) {
                        j += Math.max(world.func_72825_h(i1, l), world.field_73011_w.func_76557_i());
                        ++k;
                    }
                }
            }
            if (k == 0) {
                return false;
            }
            this.partPosY = j / k;
            this.field_74887_e.func_78886_a(0, this.partPosY - this.field_74887_e.field_78895_b + offset, 0);
            return true;
        }
    }
}

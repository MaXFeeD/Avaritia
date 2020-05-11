// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import fox.spiteful.avaritia.Lumberjack;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenRavine;

public class MapGenAlfheimRavines extends MapGenRavine
{
    protected void func_151538_a(final World world, final int originChunkX, final int originChunkZ, final int chunkX, final int chunkZ, final Block[] blocks) {
        if (this.field_75038_b.nextInt(6) == 0) {
            Lumberjack.info("crack!");
            final double x = originChunkX * 16 + this.field_75038_b.nextInt(16);
            final double y = 67.0;
            final double z = originChunkZ * 16 + this.field_75038_b.nextInt(16);
            final int angles = this.field_75038_b.nextInt(4) + 3;
            final double angleinc = 6.283185307179586 / angles;
            final double baseangle = this.field_75038_b.nextDouble() * 3.141592653589793 * 2.0;
            for (int i = 0; i < angles; ++i) {
                final float f1 = 1.0f;
                final float f2 = 1.0f;
                final float a = (float)(baseangle + angleinc * i);
                this.func_151540_a(this.field_75038_b.nextLong(), chunkX, chunkZ, blocks, x, y, z, f2, a, f1, 0, 0, 3.0);
            }
        }
    }
    
    protected void digBlock(final Block[] data, final int index, final int x, final int y, final int z, final int chunkX, final int chunkZ, final boolean foundTop) {
        final Block block = data[index];
        if (block != null) {
            data[index] = null;
        }
    }
}

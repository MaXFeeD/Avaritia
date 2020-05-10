// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import java.util.WeakHashMap;
import java.util.HashSet;
import java.util.HashMap;
import fox.spiteful.avaritia.items.ItemStackWrapper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import fox.spiteful.avaritia.items.ItemMatterCluster;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Set;
import java.util.Random;
import net.minecraft.block.material.Material;

public class ToolHelper
{
    public static Material[] materialsPick;
    public static Material[] materialsShovel;
    public static Material[] materialsAxe;
    private static Random randy;
    public static Set<EntityPlayer> hammering;
    public static Map<EntityPlayer, List<ItemStack>> hammerdrops;
    
    public static void removeBlocksInIteration(final EntityPlayer player, final ItemStack stack, final World world, final int x, final int y, final int z, final int xs, final int ys, final int zs, final int xe, final int ye, final int ze, final Block block, final Material[] materialsListing, final boolean silk, final int fortune, final boolean dispose) {
        final float blockHardness = (block == null) ? 1.0f : block.func_149712_f(world, x, y, z);
        if (!ToolHelper.hammerdrops.containsKey(player) || ToolHelper.hammerdrops.get(player) == null) {
            ToolHelper.hammerdrops.put(player, new ArrayList<ItemStack>());
        }
        if (!ToolHelper.hammering.contains(player)) {
            ToolHelper.hammering.add(player);
        }
        for (int x2 = xs; x2 < xe; ++x2) {
            for (int y2 = ys; y2 < ye; ++y2) {
                for (int z2 = zs; z2 < ze; ++z2) {
                    removeBlockWithDrops(player, stack, world, x2 + x, y2 + y, z2 + z, block, materialsListing, silk, fortune, blockHardness, dispose);
                }
            }
        }
        final int meta = world.func_72805_g(x, y, z);
        if (!world.field_72995_K) {
            world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (meta << 12));
        }
        if (ToolHelper.hammering.contains(player)) {
            ToolHelper.hammering.remove(player);
        }
        if (!world.field_72995_K) {
            final List<ItemStack> clusters = ItemMatterCluster.makeClusters(ToolHelper.hammerdrops.get(player));
            for (final ItemStack cluster : clusters) {
                final EntityItem ent = new EntityItem(world, (double)x, (double)y, (double)z, cluster);
                world.func_72838_d((Entity)ent);
            }
        }
        ToolHelper.hammerdrops.put(player, null);
    }
    
    public static boolean isRightMaterial(final Material material, final Material[] materialsListing) {
        for (final Material mat : materialsListing) {
            if (material == mat) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeBlockWithDrops(final EntityPlayer player, final ItemStack stack, final World world, final int x, final int y, final int z, final Block block, final Material[] materialsListing, final boolean silk, final int fortune, final float blockHardness, final boolean dispose) {
        if (!world.func_72899_e(x, y, z)) {
            return;
        }
        final Block blk = world.func_147439_a(x, y, z);
        final int meta = world.func_72805_g(x, y, z);
        if (block != null && blk != block) {
            return;
        }
        final Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!world.field_72995_K && blk != null && !blk.isAir((IBlockAccess)world, x, y, z)) {
            if (blk == Blocks.field_150349_c && stack.func_77973_b() == LudicrousItems.infinity_axe) {
                world.func_147449_b(x, y, z, Blocks.field_150346_d);
            }
            if (!blk.canHarvestBlock(player, meta) || !isRightMaterial(mat, materialsListing)) {
                return;
            }
            if (!player.field_71075_bZ.field_75098_d) {
                final int localMeta = world.func_72805_g(x, y, z);
                blk.func_149681_a(world, x, y, z, localMeta, player);
                if (blk.removedByPlayer(world, player, x, y, z, true)) {
                    blk.func_149664_b(world, x, y, z, localMeta);
                    if (!dispose) {
                        if (blk.func_149737_a(player, world, x, y, z) < 0.0f && blk.func_149745_a(ToolHelper.randy) == 0) {
                            ItemStack drop = blk.getPickBlock(raytraceFromEntity(world, (Entity)player, true, 10.0), world, x, y, z, player);
                            if (drop == null) {
                                drop = new ItemStack(blk, 1, meta);
                            }
                            dropItem(drop, world, x, y, z);
                        }
                        blk.func_149636_a(world, player, x, y, z, localMeta);
                    }
                }
            }
            else {
                world.func_147468_f(x, y, z);
            }
        }
    }
    
    public static MovingObjectPosition raytraceFromEntity(final World world, final Entity player, final boolean wut, final double range) {
        final float f = 1.0f;
        final float f2 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
        final float f3 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
        final double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * f;
        double d2 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * f;
        if (!world.field_72995_K && player instanceof EntityPlayer) {
            d2 += 1.62;
        }
        final double d3 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * f;
        final Vec3 vec3 = Vec3.func_72443_a(d0, d2, d3);
        final float f4 = MathHelper.func_76134_b(-f3 * 0.017453292f - 3.1415927f);
        final float f5 = MathHelper.func_76126_a(-f3 * 0.017453292f - 3.1415927f);
        final float f6 = -MathHelper.func_76134_b(-f2 * 0.017453292f);
        final float f7 = MathHelper.func_76126_a(-f2 * 0.017453292f);
        final float f8 = f5 * f6;
        final float f9 = f4 * f6;
        final double d4 = range;
        final Vec3 vec4 = vec3.func_72441_c(f8 * d4, f7 * d4, f9 * d4);
        return world.func_72901_a(vec3, vec4, wut);
    }
    
    public static void dropItem(final ItemStack drop, final World world, final int x, final int y, final int z) {
        final float f = 0.7f;
        final double d0 = ToolHelper.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final double d2 = ToolHelper.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final double d3 = ToolHelper.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final EntityItem entityitem = new EntityItem(world, x + d0, y + d2, z + d3, drop);
        entityitem.field_145804_b = 10;
        world.func_72838_d((Entity)entityitem);
    }
    
    public static List<ItemStack> collateDropList(final List<ItemStack> input) {
        return collateMatterClusterContents(collateMatterCluster(input));
    }
    
    public static List<ItemStack> collateMatterClusterContents(final Map<ItemStackWrapper, Integer> input) {
        final List<ItemStack> collated = new ArrayList<ItemStack>();
        for (final Map.Entry<ItemStackWrapper, Integer> e : input.entrySet()) {
            int count = e.getValue();
            final ItemStackWrapper wrap = e.getKey();
            final int size = wrap.stack.func_77976_d();
            for (int fullstacks = (int)Math.floor(count / size), i = 0; i < fullstacks; ++i) {
                count -= size;
                final ItemStack stack = wrap.stack.func_77946_l();
                stack.field_77994_a = size;
                collated.add(stack);
            }
            if (count > 0) {
                final ItemStack stack2 = wrap.stack.func_77946_l();
                stack2.field_77994_a = count;
                collated.add(stack2);
            }
        }
        return collated;
    }
    
    public static Map<ItemStackWrapper, Integer> collateMatterCluster(final List<ItemStack> input) {
        final Map<ItemStackWrapper, Integer> counts = new HashMap<ItemStackWrapper, Integer>();
        if (input != null) {
            for (final ItemStack stack : input) {
                final ItemStackWrapper wrap = new ItemStackWrapper(stack);
                if (!counts.containsKey(wrap)) {
                    counts.put(wrap, 0);
                }
                counts.put(wrap, counts.get(wrap) + stack.field_77994_a);
            }
        }
        return counts;
    }
    
    static {
        ToolHelper.materialsPick = new Material[] { Material.field_151576_e, Material.field_151573_f, Material.field_151588_w, Material.field_151592_s, Material.field_76233_E, Material.field_151574_g };
        ToolHelper.materialsShovel = new Material[] { Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B };
        ToolHelper.materialsAxe = new Material[] { Material.field_151589_v, Material.field_151584_j, Material.field_151585_k, Material.field_151575_d, Material.field_151582_l };
        ToolHelper.randy = new Random();
        ToolHelper.hammering = new HashSet<EntityPlayer>();
        ToolHelper.hammerdrops = new WeakHashMap<EntityPlayer, List<ItemStack>>();
    }
}

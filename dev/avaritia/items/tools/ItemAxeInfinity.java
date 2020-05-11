// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.world.IBlockAccess;
import java.util.HashMap;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.util.Collection;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.entity.EntityImmortalItem;
import java.util.ArrayList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.block.Block;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import fox.spiteful.avaritia.Avaritia;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ItemAxeInfinity extends ItemAxe
{
    private static final Item.ToolMaterial opAxe;
    private static Map<Integer, List<BlockSwapper>> blockSwappers;
    
    public ItemAxeInfinity() {
        super(ItemAxeInfinity.opAxe);
        this.func_77655_b("infinity_axe");
        this.func_111206_d("avaritia:infinity_axe");
        this.func_77637_a(Avaritia.tab);
        FMLCommonHandler.instance().bus().register((Object)this);
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    public float getDigSpeed(final ItemStack stack, final Block block, final int meta) {
        if (ForgeHooks.isToolEffective(stack, block, meta) || block.func_149688_o() == Material.field_151584_j) {
            return this.field_77864_a;
        }
        return Math.max(this.func_150893_a(stack, block), 6.0f);
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        if (player.func_70093_af()) {
            player.func_71038_i();
            final int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
            final boolean silk = EnchantmentHelper.func_77502_d((EntityLivingBase)player);
            final int range = 13;
            ToolHelper.removeBlocksInIteration(player, stack, world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, -range, -3, -range, range, range * 2 - 3, range, null, ToolHelper.materialsAxe, silk, fortune, false);
        }
        return stack;
    }
    
    public boolean onBlockStartBreak(final ItemStack stack, final int x, final int y, final int z, final EntityPlayer player) {
        final MovingObjectPosition raycast = ToolHelper.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0);
        if (raycast != null) {
            this.breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.field_72310_e);
        }
        return false;
    }
    
    public void breakOtherBlock(final EntityPlayer player, final ItemStack stack, final int x, final int y, final int z, final int originX, final int originY, final int originZ, final int side) {
        if (player.func_70093_af()) {
            return;
        }
        final ChunkCoordinates coords = new ChunkCoordinates(x, y, z);
        addBlockSwapper(player.field_70170_p, player, stack, coords, coords, 32, false, true, new ArrayList<String>());
    }
    
    public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityImmortalItem(world, location, itemstack);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack, final int pass) {
        return false;
    }
    
    @SubscribeEvent
    public void onTickEnd(final TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            final int dim = event.world.field_73011_w.field_76574_g;
            if (ItemAxeInfinity.blockSwappers.containsKey(dim)) {
                final List<BlockSwapper> swappers = ItemAxeInfinity.blockSwappers.get(dim);
                final List<BlockSwapper> swappersSafe = new ArrayList<BlockSwapper>(swappers);
                swappers.clear();
                for (final BlockSwapper s : swappersSafe) {
                    if (s != null) {
                        s.tick();
                    }
                }
            }
        }
    }
    
    private static BlockSwapper addBlockSwapper(final World world, final EntityPlayer player, final ItemStack stack, final ChunkCoordinates origCoords, final ChunkCoordinates coords, final int steps, final boolean leaves, final boolean force, final List<String> posChecked) {
        final BlockSwapper swapper = new BlockSwapper(world, player, stack, origCoords, coords, steps, leaves, force, posChecked);
        final int dim = world.field_73011_w.field_76574_g;
        if (!ItemAxeInfinity.blockSwappers.containsKey(dim)) {
            ItemAxeInfinity.blockSwappers.put(dim, new ArrayList<BlockSwapper>());
        }
        ItemAxeInfinity.blockSwappers.get(dim).add(swapper);
        return swapper;
    }
    
    static {
        opAxe = EnumHelper.addToolMaterial("INFINITY_PICKAXE", 32, 9999, 9999.0f, 20.0f, 200);
        ItemAxeInfinity.blockSwappers = new HashMap<Integer, List<BlockSwapper>>();
    }
    
    private static class BlockSwapper
    {
        final World world;
        final EntityPlayer player;
        final ItemStack stack;
        final ChunkCoordinates origCoords;
        final int steps;
        final ChunkCoordinates coords;
        final boolean leaves;
        final boolean force;
        final List<String> posChecked;
        
        BlockSwapper(final World world, final EntityPlayer player, final ItemStack stack, final ChunkCoordinates origCoords, final ChunkCoordinates coords, final int steps, final boolean leaves, final boolean force, final List<String> posChecked) {
            this.world = world;
            this.player = player;
            this.stack = stack;
            this.origCoords = origCoords;
            this.coords = coords;
            this.steps = steps;
            this.leaves = leaves;
            this.force = force;
            this.posChecked = posChecked;
        }
        
        void tick() {
            final Block blockat = this.world.func_147439_a(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c);
            if (!this.force && blockat.isAir((IBlockAccess)this.world, this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c)) {
                return;
            }
            ToolHelper.removeBlockWithDrops(this.player, this.stack, this.world, this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c, null, ToolHelper.materialsAxe, EnchantmentHelper.func_77506_a(Enchantment.field_77348_q.field_77352_x, this.stack) > 0, EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, this.stack), 0.0f, false);
            if (this.steps == 0) {
                return;
            }
            for (final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                final int x = this.coords.field_71574_a + dir.offsetX;
                final int y = this.coords.field_71572_b + dir.offsetY;
                final int z = this.coords.field_71573_c + dir.offsetZ;
                final String pstr = this.posStr(x, y, z);
                if (!this.posChecked.contains(pstr)) {
                    final Block block = this.world.func_147439_a(x, y, z);
                    final boolean log = block.isWood((IBlockAccess)this.world, x, y, z);
                    final boolean leaf = block.isLeaves((IBlockAccess)this.world, x, y, z);
                    if (log || leaf) {
                        int steps = this.steps - 1;
                        steps = (leaf ? (this.leaves ? steps : 3) : steps);
                        addBlockSwapper(this.world, this.player, this.stack, this.origCoords, new ChunkCoordinates(x, y, z), steps, leaf, false, this.posChecked);
                        this.posChecked.add(pstr);
                    }
                }
            }
        }
        
        String posStr(final int x, final int y, final int z) {
            return x + ":" + y + ":" + z;
        }
    }
}

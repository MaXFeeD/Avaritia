// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import fox.spiteful.avaritia.items.ItemMatterCluster;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.init.Items;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import fox.spiteful.avaritia.items.tools.ItemSwordInfinity;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import fox.spiteful.avaritia.items.ItemFracturedOre;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import fox.spiteful.avaritia.items.tools.ToolHelper;
import net.minecraft.block.material.Material;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import java.util.Random;

public class LudicrousEvents
{
    private static Random randy;
    static final String[] trash;
    
    @SubscribeEvent
    public void onPlayerMine(final PlayerInteractEvent event) {
        if (!Config.bedrockBreaker || event.face == -1 || event.world.field_72995_K || event.action != PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || event.entityPlayer.func_70694_bm() == null || event.entityPlayer.field_71075_bZ.field_75098_d) {
            return;
        }
        final Block block = event.world.func_147439_a(event.x, event.y, event.z);
        final int meta = event.world.func_72805_g(event.x, event.y, event.z);
        if (block.func_149712_f(event.entityPlayer.field_70170_p, event.x, event.y, event.z) <= -1.0f && event.entityPlayer.func_70694_bm().func_77973_b() == LudicrousItems.infinity_pickaxe && (block.func_149688_o() == Material.field_151576_e || block.func_149688_o() == Material.field_151573_f)) {
            if (event.entityPlayer.func_70694_bm().func_77978_p() != null && event.entityPlayer.func_70694_bm().func_77978_p().func_74767_n("hammer")) {
                LudicrousItems.infinity_pickaxe.onBlockStartBreak(event.entityPlayer.func_70694_bm(), event.x, event.y, event.z, event.entityPlayer);
            }
            else {
                if (block.func_149745_a(LudicrousEvents.randy) == 0) {
                    ItemStack drop = block.getPickBlock(ToolHelper.raytraceFromEntity(event.world, (Entity)event.entityPlayer, true, 10.0), event.world, event.x, event.y, event.z, event.entityPlayer);
                    if (drop == null) {
                        drop = new ItemStack(block, 1, meta);
                    }
                    dropItem(drop, event.entityPlayer.field_70170_p, event.x, event.y, event.z);
                }
                else {
                    block.func_149636_a(event.world, event.entityPlayer, event.x, event.y, event.z, meta);
                }
                event.entityPlayer.field_70170_p.func_147468_f(event.x, event.y, event.z);
                event.world.func_72926_e(2001, event.x, event.y, event.z, Block.func_149682_b(block) + (meta << 12));
            }
        }
    }
    
    @SubscribeEvent
    public void handleExtraLuck(final BlockEvent.HarvestDropsEvent event) {
        if (event.harvester == null) {
            return;
        }
        if (event.harvester.func_70694_bm() == null) {
            return;
        }
        final ItemStack held = event.harvester.func_70694_bm();
        if (held.func_77973_b() == LudicrousItems.infinity_pickaxe) {
            extraLuck(event, 4);
            if (held.func_77978_p() != null && held.func_77978_p().func_74767_n("hammer") && ToolHelper.hammering.contains(event.harvester) && ToolHelper.hammerdrops.containsKey(event.harvester) && ToolHelper.hammerdrops.get(event.harvester) != null) {
                ToolHelper.hammerdrops.get(event.harvester).addAll(event.drops);
                event.drops.clear();
            }
        }
        else if (held.func_77973_b() == LudicrousItems.infinity_shovel) {
            if (held.func_77978_p() != null && held.func_77978_p().func_74767_n("destroyer") && ToolHelper.hammering.contains(event.harvester) && ToolHelper.hammerdrops.containsKey(event.harvester) && ToolHelper.hammerdrops.get(event.harvester) != null) {
                final ArrayList<ItemStack> garbage = new ArrayList<ItemStack>();
                for (final ItemStack drop : event.drops) {
                    if (isGarbage(drop)) {
                        garbage.add(drop);
                    }
                }
                for (final ItemStack junk : garbage) {
                    event.drops.remove(junk);
                }
                ToolHelper.hammerdrops.get(event.harvester).addAll(event.drops);
                event.drops.clear();
            }
        }
        else if (held.func_77973_b() == LudicrousItems.infinity_axe && ToolHelper.hammering.contains(event.harvester) && ToolHelper.hammerdrops.containsKey(event.harvester) && ToolHelper.hammerdrops.get(event.harvester) != null) {
            ToolHelper.hammerdrops.get(event.harvester).addAll(event.drops);
            event.drops.clear();
        }
    }
    
    public static void extraLuck(final BlockEvent.HarvestDropsEvent event, final int mult) {
        if (event.block.func_149688_o() == Material.field_151576_e) {
            final List<ItemStack> adds = new ArrayList<ItemStack>();
            final List<ItemStack> removals = new ArrayList<ItemStack>();
            for (final ItemStack drop : event.drops) {
                if (drop.func_77973_b() != Item.func_150898_a(event.block) && !(drop.func_77973_b() instanceof ItemBlock)) {
                    drop.field_77994_a = Math.min(drop.field_77994_a * mult, drop.func_77976_d());
                }
                else {
                    if (!Config.fractured || drop.func_77973_b() != Item.func_150898_a(event.block)) {
                        continue;
                    }
                    final ItemFracturedOre ifo = (ItemFracturedOre)LudicrousItems.fractured_ore;
                    final int[] oreids = OreDictionary.getOreIDs(drop);
                    for (int i = 0; i < oreids.length; ++i) {
                        final String orename = OreDictionary.getOreName(oreids[i]);
                        if (orename.startsWith("ore")) {
                            adds.add(ifo.getStackForOre(drop, Math.min(drop.field_77994_a * (mult + 1), drop.func_77976_d())));
                            removals.add(drop);
                            break;
                        }
                    }
                }
            }
            event.drops.addAll(adds);
            event.drops.removeAll(removals);
        }
    }
    
    private static boolean isGarbage(final ItemStack drop) {
        for (final int id : OreDictionary.getOreIDs(drop)) {
            for (final String ore : LudicrousEvents.trash) {
                if (OreDictionary.getOreName(id).equals(ore)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void dropItem(final ItemStack drop, final World world, final int x, final int y, final int z) {
        final float f = 0.7f;
        final double d0 = LudicrousEvents.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final double d2 = LudicrousEvents.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final double d3 = LudicrousEvents.randy.nextFloat() * f + (1.0f - f) * 0.5;
        final EntityItem entityitem = new EntityItem(world, x + d0, y + d2, z + d3, drop);
        entityitem.field_145804_b = 10;
        world.func_72838_d((Entity)entityitem);
    }
    
    @SubscribeEvent
    public void onTooltip(final ItemTooltipEvent event) {
        if (event.itemStack.func_77973_b() instanceof ItemSwordInfinity) {
            for (int x = 0; x < event.toolTip.size(); ++x) {
                if (event.toolTip.get(x).contains(StatCollector.func_74838_a("attribute.name.generic.attackDamage")) || event.toolTip.get(x).contains(StatCollector.func_74838_a("Attack Damage"))) {
                    event.toolTip.set(x, EnumChatFormatting.BLUE + "+" + LudicrousText.makeFabulous(StatCollector.func_74838_a("tip.infinity")) + " " + EnumChatFormatting.BLUE + StatCollector.func_74838_a("attribute.name.generic.attackDamage"));
                    return;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onGetHurt(final LivingHurtEvent event) {
        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }
        final EntityPlayer player = (EntityPlayer)event.entityLiving;
        if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == LudicrousItems.infinity_sword && player.func_71039_bw()) {
            event.setCanceled(true);
        }
        if (LudicrousItems.isInfinite(player) && !event.source.field_76373_n.equals("infinity")) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onAttacked(final LivingAttackEvent event) {
        if (!(event.entityLiving instanceof EntityPlayer)) {
            return;
        }
        if (event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            return;
        }
        final EntityPlayer player = (EntityPlayer)event.entityLiving;
        if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == LudicrousItems.infinity_sword && player.func_71039_bw()) {
            event.setCanceled(true);
        }
        if (LudicrousItems.isInfinite(player) && !event.source.field_76373_n.equals("infinity")) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onLivingDrops(final LivingDropsEvent event) {
        if (event.recentlyHit && event.entityLiving instanceof EntitySkeleton && event.source.func_76346_g() instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.source.func_76346_g();
            if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == LudicrousItems.skull_sword) {
                if (event.drops.isEmpty()) {
                    this.addDrop(event, new ItemStack(Items.field_151144_bL, 1, 1));
                }
                else {
                    int skulls = 0;
                    for (int i = 0; i < event.drops.size(); ++i) {
                        final EntityItem drop = event.drops.get(i);
                        final ItemStack stack = drop.func_92059_d();
                        if (stack.func_77973_b() == Items.field_151144_bL) {
                            if (stack.func_77960_j() == 1) {
                                ++skulls;
                            }
                            else if (stack.func_77960_j() == 0) {
                                ++skulls;
                                stack.func_77964_b(1);
                            }
                        }
                    }
                    if (skulls == 0) {
                        this.addDrop(event, new ItemStack(Items.field_151144_bL, 1, 1));
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void diggity(final PlayerEvent.BreakSpeed event) {
        if (event.entityPlayer.func_70694_bm() != null) {
            final ItemStack held = event.entityPlayer.func_70694_bm();
            if (held.func_77973_b() == LudicrousItems.infinity_pickaxe || held.func_77973_b() == LudicrousItems.infinity_shovel) {
                if (!event.entityPlayer.field_70122_E) {
                    event.newSpeed *= 5.0f;
                }
                if (!event.entityPlayer.func_70055_a(Material.field_151586_h) && !EnchantmentHelper.func_77510_g((EntityLivingBase)event.entityPlayer)) {
                    event.newSpeed *= 5.0f;
                }
                if (held.func_77978_p() != null && (held.func_77978_p().func_74767_n("hammer") || held.func_77978_p().func_74767_n("destroyer"))) {
                    event.newSpeed *= 0.5;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void canHarvest(final PlayerEvent.HarvestCheck event) {
        if (event.entityPlayer.func_70694_bm() != null) {
            final ItemStack held = event.entityPlayer.func_70694_bm();
            if (held.func_77973_b() == LudicrousItems.infinity_shovel && event.block.func_149688_o() == Material.field_151576_e && held.func_77978_p() != null && held.func_77978_p().func_74767_n("destroyer") && isGarbageBlock(event.block)) {
                event.success = true;
            }
        }
    }
    
    private static boolean isGarbageBlock(final Block block) {
        for (final int id : OreDictionary.getOreIDs(new ItemStack(block, 1))) {
            final String ore = OreDictionary.getOreName(id);
            if (ore.equals("cobblestone") || ore.equals("stone") || ore.equals("netherrack")) {
                return true;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (LudicrousItems.isInfinite(player) && !event.source.func_76355_l().equals("infinity")) {
                event.setCanceled(true);
                player.func_70606_j(player.func_110138_aP());
            }
        }
    }
    
    private void addDrop(final LivingDropsEvent event, final ItemStack drop) {
        final EntityItem entityitem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, drop);
        entityitem.field_145804_b = 10;
        event.drops.add(entityitem);
    }
    
    @SubscribeEvent
    public void clusterClustererererer(final EntityItemPickupEvent event) {
        if (event.entityPlayer != null && event.item.func_92059_d().func_77973_b() == LudicrousItems.matter_cluster) {
            final ItemStack stack = event.item.func_92059_d();
            final EntityPlayer player = event.entityPlayer;
            for (int i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                if (stack.field_77994_a == 0) {
                    break;
                }
                final ItemStack slot = player.field_71071_by.field_70462_a[i];
                if (slot != null && slot.func_77973_b() != null && slot.func_77973_b() == LudicrousItems.matter_cluster) {
                    ItemMatterCluster.mergeClusters(stack, slot);
                }
            }
        }
    }
    
    static {
        LudicrousEvents.randy = new Random();
        trash = new String[] { "dirt", "sand", "gravel", "cobblestone", "netherrack" };
    }
}

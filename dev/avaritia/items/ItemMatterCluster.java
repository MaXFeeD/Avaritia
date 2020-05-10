// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import codechicken.lib.math.MathHelper;
import net.minecraft.world.World;
import fox.spiteful.avaritia.Lumberjack;
import net.minecraft.nbt.NBTBase;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.ArrayList;
import fox.spiteful.avaritia.items.tools.ToolHelper;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import java.util.Random;
import fox.spiteful.avaritia.render.ICosmicRenderItem;
import net.minecraft.item.Item;

public class ItemMatterCluster extends Item implements ICosmicRenderItem
{
    protected static Random randy;
    public static final String MAINTAG = "clusteritems";
    public static final String LISTTAG = "items";
    public static final String ITEMTAG = "item";
    public static final String COUNTTAG = "count";
    public static final String MAINCOUNTTAG = "total";
    public static int capacity;
    public IIcon iconFull;
    public IIcon cosmicIcon;
    public IIcon cosmicIconFull;
    
    public ItemMatterCluster() {
        this.func_77625_d(1);
        this.func_77655_b("avaritia_mattercluster");
        this.func_111206_d("avaritia:mattercluster");
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        super.func_94581_a(ir);
        this.cosmicIcon = ir.func_94245_a("avaritia:mattercluster_mask");
        this.iconFull = ir.func_94245_a("avaritia:mattercluster_full");
        this.cosmicIconFull = ir.func_94245_a("avaritia:mattercluster_full_mask");
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    public void func_77624_a(final ItemStack stack, final EntityPlayer player, final List tooltip, final boolean debug) {
        if (!stack.func_77942_o() || !stack.func_77978_p().func_74764_b("clusteritems")) {
            return;
        }
        final NBTTagCompound clustertag = stack.func_77978_p().func_74775_l("clusteritems");
        tooltip.add(clustertag.func_74762_e("total") + "/" + ItemMatterCluster.capacity + " " + StatCollector.func_74838_a("tooltip.matter_cluster.counter"));
        tooltip.add("");
        if (GuiScreen.func_146272_n()) {
            final NBTTagList list = clustertag.func_150295_c("items", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                final NBTTagCompound tag = list.func_150305_b(i);
                final ItemStack countstack = ItemStack.func_77949_a(tag.func_74775_l("item"));
                final int count = tag.func_74762_e("count");
                tooltip.add(countstack.func_77973_b().func_77613_e(countstack).field_77937_e + countstack.func_82833_r() + EnumChatFormatting.GRAY + " x " + count);
            }
        }
        else {
            tooltip.add(EnumChatFormatting.DARK_GRAY + StatCollector.func_74838_a("tooltip.matter_cluster.desc"));
            tooltip.add(EnumChatFormatting.DARK_GRAY.toString() + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("tooltip.matter_cluster.desc2"));
        }
    }
    
    public static List<ItemStack> makeClusters(final List<ItemStack> input) {
        final Map<ItemStackWrapper, Integer> items = ToolHelper.collateMatterCluster(input);
        final List<ItemStack> clusters = new ArrayList<ItemStack>();
        final List<Map.Entry<ItemStackWrapper, Integer>> itemlist = new ArrayList<Map.Entry<ItemStackWrapper, Integer>>();
        itemlist.addAll(items.entrySet());
        int currentTotal = 0;
        Map<ItemStackWrapper, Integer> currentItems = new HashMap<ItemStackWrapper, Integer>();
        while (!itemlist.isEmpty()) {
            final Map.Entry<ItemStackWrapper, Integer> e = itemlist.get(0);
            final ItemStackWrapper wrap = e.getKey();
            final int wrapcount = e.getValue();
            final int count = Math.min(ItemMatterCluster.capacity - currentTotal, wrapcount);
            if (!currentItems.containsKey(e.getKey())) {
                currentItems.put(wrap, count);
            }
            else {
                currentItems.put(wrap, currentItems.get(wrap) + count);
            }
            currentTotal += count;
            e.setValue(wrapcount - count);
            if (e.getValue() == 0) {
                itemlist.remove(0);
            }
            if (currentTotal == ItemMatterCluster.capacity) {
                final ItemStack cluster = makeCluster(currentItems);
                clusters.add(cluster);
                currentTotal = 0;
                currentItems = new HashMap<ItemStackWrapper, Integer>();
            }
        }
        if (currentTotal > 0) {
            final ItemStack cluster2 = makeCluster(currentItems);
            clusters.add(cluster2);
        }
        return clusters;
    }
    
    public static ItemStack makeCluster(final Map<ItemStackWrapper, Integer> input) {
        final ItemStack cluster = new ItemStack(LudicrousItems.matter_cluster);
        int total = 0;
        for (final int num : input.values()) {
            total += num;
        }
        setClusterData(cluster, input, total);
        return cluster;
    }
    
    public static Map<ItemStackWrapper, Integer> getClusterData(final ItemStack cluster) {
        if (!cluster.func_77942_o() || !cluster.func_77978_p().func_74764_b("clusteritems")) {
            return null;
        }
        final NBTTagCompound tag = cluster.func_77978_p().func_74775_l("clusteritems");
        final NBTTagList list = tag.func_150295_c("items", 10);
        final Map<ItemStackWrapper, Integer> data = new HashMap<ItemStackWrapper, Integer>();
        for (int i = 0; i < list.func_74745_c(); ++i) {
            final NBTTagCompound entry = list.func_150305_b(i);
            final ItemStackWrapper wrap = new ItemStackWrapper(ItemStack.func_77949_a(entry.func_74775_l("item")));
            final int count = entry.func_74762_e("count");
            data.put(wrap, count);
        }
        return data;
    }
    
    public static int getClusterSize(final ItemStack cluster) {
        if (!cluster.func_77942_o() || !cluster.func_77978_p().func_74764_b("clusteritems")) {
            return 0;
        }
        return cluster.func_77978_p().func_74775_l("clusteritems").func_74762_e("total");
    }
    
    public static void setClusterData(final ItemStack stack, final Map<ItemStackWrapper, Integer> data, final int count) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        final NBTTagCompound clustertag = new NBTTagCompound();
        final NBTTagList list = new NBTTagList();
        for (final Map.Entry<ItemStackWrapper, Integer> entry : data.entrySet()) {
            final NBTTagCompound itemtag = new NBTTagCompound();
            itemtag.func_74782_a("item", (NBTBase)entry.getKey().stack.func_77955_b(new NBTTagCompound()));
            itemtag.func_74768_a("count", (int)entry.getValue());
            list.func_74742_a((NBTBase)itemtag);
        }
        clustertag.func_74782_a("items", (NBTBase)list);
        clustertag.func_74768_a("total", count);
        stack.func_77978_p().func_74782_a("clusteritems", (NBTBase)clustertag);
    }
    
    public static void mergeClusters(final ItemStack donor, final ItemStack recipient) {
        int donorcount = getClusterSize(donor);
        int recipientcount = getClusterSize(recipient);
        Lumberjack.info(donorcount + ", " + recipientcount);
        if (donorcount == 0 || donorcount == ItemMatterCluster.capacity || recipientcount == ItemMatterCluster.capacity) {
            return;
        }
        final Map<ItemStackWrapper, Integer> donordata = getClusterData(donor);
        final Map<ItemStackWrapper, Integer> recipientdata = getClusterData(recipient);
        final List<Map.Entry<ItemStackWrapper, Integer>> datalist = new ArrayList<Map.Entry<ItemStackWrapper, Integer>>();
        datalist.addAll(donordata.entrySet());
        while (recipientcount < ItemMatterCluster.capacity && donorcount > 0) {
            final Map.Entry<ItemStackWrapper, Integer> e = datalist.get(0);
            final ItemStackWrapper wrap = e.getKey();
            final int wrapcount = e.getValue();
            final int count = Math.min(ItemMatterCluster.capacity - recipientcount, wrapcount);
            if (!recipientdata.containsKey(wrap)) {
                recipientdata.put(wrap, count);
            }
            else {
                recipientdata.put(wrap, recipientdata.get(wrap) + count);
            }
            donorcount -= count;
            recipientcount += count;
            if (wrapcount - count > 0) {
                e.setValue(wrapcount - count);
            }
            else {
                donordata.remove(wrap);
                datalist.remove(0);
            }
        }
        setClusterData(recipient, recipientdata, recipientcount);
        if (donorcount > 0) {
            setClusterData(donor, donordata, donorcount);
        }
        else {
            donor.func_77982_d((NBTTagCompound)null);
            donor.field_77994_a = 0;
        }
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        if (!world.field_72995_K) {
            final List<ItemStack> drops = ToolHelper.collateMatterClusterContents(getClusterData(stack));
            for (final ItemStack drop : drops) {
                ToolHelper.dropItem(drop, world, MathHelper.floor_double(player.field_70165_t), MathHelper.floor_double(player.field_70163_u), MathHelper.floor_double(player.field_70161_v));
            }
        }
        stack.field_77994_a = 0;
        return stack;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getMaskTexture(final ItemStack stack, final EntityPlayer player) {
        final int count = getClusterSize(stack);
        if (count == ItemMatterCluster.capacity) {
            return this.cosmicIconFull;
        }
        return this.cosmicIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public float getMaskMultiplier(final ItemStack stack, final EntityPlayer player) {
        final int count = getClusterSize(stack);
        return count / (float)ItemMatterCluster.capacity;
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        final int count = getClusterSize(stack);
        if (count == ItemMatterCluster.capacity) {
            return this.iconFull;
        }
        return super.getIcon(stack, pass);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77650_f(final ItemStack stack) {
        return this.getIcon(stack, 0);
    }
    
    public String func_77667_c(final ItemStack stack) {
        final int count = getClusterSize(stack);
        if (count == ItemMatterCluster.capacity) {
            return super.func_77667_c(stack) + ".full";
        }
        return super.func_77667_c(stack);
    }
    
    static {
        ItemMatterCluster.randy = new Random();
        ItemMatterCluster.capacity = 4096;
    }
}

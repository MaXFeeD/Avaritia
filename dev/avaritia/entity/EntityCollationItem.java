// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.init.Items;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.AchievementList;
import net.minecraft.item.Item;
import java.util.ArrayList;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Iterator;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import java.util.Collection;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityItem;

@Deprecated
public class EntityCollationItem extends EntityItem
{
    public static final String ITEMTAG = "item";
    
    public EntityCollationItem(final World world, final double x, final double y, final double z, final Collection<ItemStack> items) {
        super(world, x, y, z);
        final ItemStack watchstack = new ItemStack(Blocks.field_150357_h);
        final NBTTagCompound tag = new NBTTagCompound();
        final NBTTagList itemlist = new NBTTagList();
        for (final ItemStack stack : items) {
            final NBTTagCompound itemtag = stack.func_77955_b(new NBTTagCompound());
            itemlist.func_74742_a((NBTBase)itemtag);
        }
        tag.func_74782_a("item", (NBTBase)itemlist);
        watchstack.func_77982_d(tag);
        this.func_92058_a(watchstack);
        this.lifespan = 18000;
        this.field_145804_b = 20;
        this.field_70178_ae = true;
    }
    
    public EntityCollationItem(final World world, final double x, final double y, final double z, final ItemStack stack) {
        super(world, x, y, z, stack);
    }
    
    public EntityCollationItem(final World world, final double x, final double y, final double z) {
        super(world, x, y, z);
    }
    
    public EntityCollationItem(final World world) {
        super(world);
    }
    
    public void func_70071_h_() {
        super.func_70071_h_();
        final ItemStack stack = this.func_92059_d();
        final NBTTagList list = stack.func_77978_p().func_150295_c("item", 10);
        if (list.func_74745_c() == 0) {
            this.func_70106_y();
        }
    }
    
    public boolean func_70289_a(final EntityItem other) {
        return false;
    }
    
    public void func_70100_b_(final EntityPlayer player) {
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_145804_b > 0) {
                return;
            }
            final EntityItemPickupEvent event = new EntityItemPickupEvent(player, (EntityItem)this);
            if (MinecraftForge.EVENT_BUS.post((Event)event)) {
                return;
            }
            final String owner = this.func_145798_i();
            final ItemStack basestack = this.func_92059_d();
            final NBTTagList list = basestack.func_77978_p().func_150295_c("item", 10);
            final List<Integer> removed = new ArrayList<Integer>();
            for (int i = 0; i < list.func_74745_c(); ++i) {
                final ItemStack itemstack = ItemStack.func_77949_a(list.func_150305_b(i));
                if (this.field_145804_b <= 0 && (owner == null || this.lifespan - this.field_70292_b <= 200 || owner.equals(player.func_70005_c_())) && (event.getResult() == Event.Result.ALLOW || player.field_71071_by.func_70441_a(itemstack))) {
                    if (itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150364_r)) {
                        player.func_71029_a((StatBase)AchievementList.field_76005_g);
                    }
                    if (itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150363_s)) {
                        player.func_71029_a((StatBase)AchievementList.field_76005_g);
                    }
                    if (itemstack.func_77973_b() == Items.field_151116_aA) {
                        player.func_71029_a((StatBase)AchievementList.field_76022_t);
                    }
                    if (itemstack.func_77973_b() == Items.field_151045_i) {
                        player.func_71029_a((StatBase)AchievementList.field_76019_w);
                    }
                    if (itemstack.func_77973_b() == Items.field_151072_bj) {
                        player.func_71029_a((StatBase)AchievementList.field_76027_z);
                    }
                    if (itemstack.func_77973_b() == Items.field_151045_i && this.func_145800_j() != null) {
                        final EntityPlayer entityplayer1 = this.field_70170_p.func_72924_a(this.func_145800_j());
                        if (entityplayer1 != null && entityplayer1 != player) {
                            entityplayer1.func_71029_a((StatBase)AchievementList.field_150966_x);
                        }
                    }
                    FMLCommonHandler.instance().firePlayerItemPickupEvent(player, (EntityItem)this);
                    this.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                    if (itemstack.field_77994_a <= 0) {
                        removed.add(0, i);
                    }
                }
            }
            for (int i = 0; i < removed.size(); ++i) {
                final int index = removed.get(i);
                list.func_74744_a(index);
            }
            if (list.func_74745_c() == 0) {
                this.func_70106_y();
            }
            this.func_92058_a(basestack);
        }
    }
}

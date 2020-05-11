// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.crafting.FurnaceRecipes;
import java.util.HashSet;
import com.google.common.collect.HashMultimap;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.StatCollector;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import java.util.Map;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.item.Item;

public class ItemFracturedOre extends Item
{
    public static final String OREKEY = "ore";
    protected static List<ItemStack> emulatedOres;
    protected static Map<String, ItemStack> nameMapping;
    public static IIcon unknownIcon;
    
    public ItemFracturedOre() {
        this.func_77637_a(Avaritia.tab);
        this.func_77655_b("avaritia_fracturedore");
        this.func_111206_d("avaritia:fracturedore");
        this.func_77627_a(true);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
    }
    
    public ItemStack getStackForOre(final ItemStack orestack, final int stacksize) {
        final NBTTagCompound oretag = NameStack.saveStackToNBT(orestack);
        final ItemStack outstack = new ItemStack((Item)this, stacksize, 0);
        final NBTTagCompound stacktag = new NBTTagCompound();
        stacktag.func_74782_a("ore", (NBTBase)oretag);
        outstack.func_77982_d(stacktag);
        outstack.func_77964_b(this.getDamage(outstack));
        return outstack;
    }
    
    public String func_77653_i(final ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("ore")) {
            final NBTTagCompound tag = stack.func_77978_p().func_74775_l("ore");
            final NameStack namestack = NameStack.loadFromNBT(tag);
            if (namestack != null) {
                final ItemStack orestack = namestack.getStack();
                final Item oreitem = orestack.func_77973_b();
                return StatCollector.func_74838_a("item.avaritia_fracturedore.prefix") + " " + oreitem.func_77653_i(orestack);
            }
        }
        return super.func_77653_i(stack);
    }
    
    public int getDamage(final ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("ore")) {
            final NameStack nstack = NameStack.loadFromNBT(stack.func_77978_p().func_74775_l("ore"));
            final int id = Item.func_150891_b(nstack.getItem());
            final int meta = nstack.damage;
            final int out = meta + (id << 4);
            stack.func_77964_b(out);
            return out;
        }
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        super.func_94581_a(ir);
        ItemFracturedOre.unknownIcon = ir.func_94245_a("avaritia:unknown");
    }
    
    public static void brushUpUncomfortablyAgainstTheOreDictionary() {
        final String[] names = OreDictionary.getOreNames();
        final ItemFracturedOre fracore = (ItemFracturedOre)LudicrousItems.fractured_ore;
        final Multimap<String, ItemStack> toRegister = (Multimap<String, ItemStack>)HashMultimap.create();
        final Set<ItemStackWrapper> antiDupePool = new HashSet<ItemStackWrapper>();
        for (final String name : names) {
            if (name.startsWith("ore") && !name.startsWith("oreberry")) {
                final List<ItemStack> ores = (List<ItemStack>)OreDictionary.getOres(name);
                for (final ItemStack ore : ores) {
                    final ItemStackWrapper compare = new ItemStackWrapper(ore);
                    if (!antiDupePool.contains(compare)) {
                        antiDupePool.add(compare);
                        ItemFracturedOre.emulatedOres.add(ore.func_77946_l());
                        final ItemStack frac = fracore.getStackForOre(ore, 1);
                        toRegister.put((Object)name, (Object)frac);
                    }
                }
            }
        }
        for (final String name2 : toRegister.keySet()) {
            final Collection<ItemStack> stacks = (Collection<ItemStack>)toRegister.get((Object)name2);
            for (final ItemStack stack : stacks) {
                final ItemStack orestack = NameStack.loadFromNBT(stack.func_77978_p().func_74775_l("ore")).getStack();
                final int[] oreids = OreDictionary.getOreIDs(orestack);
                for (int i = 0; i < oreids.length; ++i) {
                    final String oreidname = OreDictionary.getOreName(oreids[i]);
                    OreDictionary.registerOre(oreidname, stack);
                }
                final ItemStack smeltingResult = FurnaceRecipes.func_77602_a().func_151395_a(orestack);
                if (smeltingResult != null) {
                    final float exp = FurnaceRecipes.func_77602_a().func_151398_b(orestack);
                    GameRegistry.addSmelting(stack, smeltingResult, exp);
                }
            }
        }
    }
    
    static {
        ItemFracturedOre.emulatedOres = new ArrayList<ItemStack>();
        ItemFracturedOre.nameMapping = new HashMap<String, ItemStack>();
    }
    
    public static class NameStack
    {
        String name;
        int damage;
        NBTTagCompound tag;
        int size;
        
        public NameStack(final ItemStack source) {
            this(source.func_77973_b().delegate.name(), source.func_77960_j(), source.field_77994_a, source.func_77978_p());
        }
        
        public NameStack(final String name, final int damage, final int size, final NBTTagCompound nbt) {
            this.name = name;
            this.damage = damage;
            this.tag = nbt;
            this.size = size;
        }
        
        public NBTTagCompound saveToNBT() {
            final NBTTagCompound savetag = new NBTTagCompound();
            savetag.func_74768_a("meta", this.damage);
            if (this.tag != null) {
                savetag.func_74782_a("nbt", (NBTBase)this.tag);
            }
            savetag.func_74778_a("name", this.name);
            savetag.func_74768_a("size", this.size);
            return savetag;
        }
        
        public static NameStack loadFromNBT(final NBTTagCompound tag) {
            NBTTagCompound stacktag = null;
            if (tag.func_74764_b("nbt")) {
                stacktag = tag.func_74775_l("nbt");
            }
            return new NameStack(tag.func_74779_i("name"), tag.func_74762_e("meta"), tag.func_74762_e("size"), stacktag);
        }
        
        public Item getItem() {
            return (Item)Item.field_150901_e.func_82594_a(this.name);
        }
        
        public static NBTTagCompound saveStackToNBT(final ItemStack stack) {
            return new NameStack(stack).saveToNBT();
        }
        
        public ItemStack getStack() {
            final ItemStack stack = new ItemStack(this.getItem(), this.size, this.damage);
            if (this.tag != null) {
                stack.func_77982_d((NBTTagCompound)this.tag.func_74737_b());
            }
            return stack;
        }
        
        @Override
        public String toString() {
            return "NameStack: " + this.size + "x " + this.name + "@" + this.damage + ", " + this.tag;
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.crafting;

import net.minecraft.nbt.NBTTagCompound;
import java.util.ArrayList;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.OreDictionary;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class ExtremeShapedOreRecipe implements IRecipe
{
    private static final int MAX_CRAFT_GRID_WIDTH = 9;
    private static final int MAX_CRAFT_GRID_HEIGHT = 9;
    private ItemStack output;
    private Object[] input;
    public int width;
    public int height;
    private boolean mirrored;
    
    public ExtremeShapedOreRecipe(final Block result, final Object... recipe) {
        this(new ItemStack(result), recipe);
    }
    
    public ExtremeShapedOreRecipe(final Item result, final Object... recipe) {
        this(new ItemStack(result), recipe);
    }
    
    public ExtremeShapedOreRecipe(final ItemStack result, Object... recipe) {
        this.output = null;
        this.input = null;
        this.width = 0;
        this.height = 0;
        this.mirrored = true;
        this.output = result.func_77946_l();
        String shape = "";
        int idx = 0;
        if (recipe[idx] instanceof Boolean) {
            this.mirrored = (boolean)recipe[idx];
            if (recipe[idx + 1] instanceof Object[]) {
                recipe = (Object[])recipe[idx + 1];
            }
            else {
                idx = 1;
            }
        }
        if (recipe[idx] instanceof String[]) {
            final String[] array;
            final String[] parts = array = (String[])recipe[idx++];
            for (final String s : array) {
                this.width = s.length();
                shape += s;
            }
            this.height = parts.length;
        }
        else {
            while (recipe[idx] instanceof String) {
                final String s2 = (String)recipe[idx++];
                shape += s2;
                this.width = s2.length();
                ++this.height;
            }
        }
        if (this.width * this.height != shape.length()) {
            String ret = "Invalid shaped ore recipe: ";
            for (final Object tmp : recipe) {
                ret = ret + tmp + ", ";
            }
            ret += this.output;
            throw new RuntimeException(ret);
        }
        final HashMap<Character, Object> itemMap = new HashMap<Character, Object>();
        while (idx < recipe.length) {
            final Character chr = (Character)recipe[idx];
            final Object in = recipe[idx + 1];
            if (in instanceof ItemStack) {
                itemMap.put(chr, ((ItemStack)in).func_77946_l());
            }
            else if (in instanceof Item) {
                itemMap.put(chr, new ItemStack((Item)in));
            }
            else if (in instanceof Block) {
                itemMap.put(chr, new ItemStack((Block)in, 1, 32767));
            }
            else {
                if (!(in instanceof String)) {
                    String ret2 = "Invalid shaped ore recipe: ";
                    for (final Object tmp2 : recipe) {
                        ret2 = ret2 + tmp2 + ", ";
                    }
                    ret2 += this.output;
                    throw new RuntimeException(ret2);
                }
                itemMap.put(chr, OreDictionary.getOres((String)in));
            }
            idx += 2;
        }
        this.input = new Object[this.width * this.height];
        int x = 0;
        for (final char chr2 : shape.toCharArray()) {
            this.input[x++] = itemMap.get(chr2);
        }
    }
    
    ExtremeShapedOreRecipe(final ShapedRecipes recipe, final Map<ItemStack, String> replacements) {
        this.output = null;
        this.input = null;
        this.width = 0;
        this.height = 0;
        this.mirrored = true;
        this.output = recipe.func_77571_b();
        this.width = recipe.field_77576_b;
        this.height = recipe.field_77577_c;
        this.input = new Object[recipe.field_77574_d.length];
        for (int i = 0; i < this.input.length; ++i) {
            final ItemStack ingred = recipe.field_77574_d[i];
            if (ingred != null) {
                this.input[i] = recipe.field_77574_d[i];
                for (final Map.Entry<ItemStack, String> replace : replacements.entrySet()) {
                    if (OreDictionary.itemMatches((ItemStack)replace.getKey(), ingred, true)) {
                        this.input[i] = OreDictionary.getOres((String)replace.getValue());
                        break;
                    }
                }
            }
        }
    }
    
    public ExtremeShapedOreRecipe(final ItemStack result, final Object[] ingredients, final int wid, final int hei) {
        this.output = null;
        this.input = null;
        this.width = 0;
        this.height = 0;
        this.mirrored = true;
        this.width = wid;
        this.height = hei;
        this.output = result;
        this.input = ingredients;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting var1) {
        return this.output.func_77946_l();
    }
    
    public int func_77570_a() {
        return this.input.length;
    }
    
    public ItemStack func_77571_b() {
        return this.output;
    }
    
    public boolean func_77569_a(final InventoryCrafting inv, final World world) {
        for (int x = 0; x <= 9 - this.width; ++x) {
            for (int y = 0; y <= 9 - this.height; ++y) {
                if (this.checkMatch(inv, x, y, false)) {
                    return true;
                }
                if (this.mirrored && this.checkMatch(inv, x, y, true)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkMatch(final InventoryCrafting inv, final int startX, final int startY, final boolean mirror) {
        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                final int subX = x - startX;
                final int subY = y - startY;
                Object target = null;
                if (subX >= 0 && subY >= 0 && subX < this.width && subY < this.height) {
                    if (mirror) {
                        target = this.input[this.width - subX - 1 + subY * this.width];
                    }
                    else {
                        target = this.input[subX + subY * this.width];
                    }
                }
                final ItemStack slot = inv.func_70463_b(x, y);
                if (target instanceof ItemStack) {
                    final ItemStack targetStack = (ItemStack)target;
                    if (!OreDictionary.itemMatches((ItemStack)target, slot, false)) {
                        return false;
                    }
                    if (targetStack.func_77942_o()) {
                        final NBTTagCompound tagCompound = targetStack.func_77978_p();
                        if (!slot.func_77942_o()) {
                            return false;
                        }
                        final NBTTagCompound slotTagCompound = slot.func_77978_p();
                        if (!slotTagCompound.equals((Object)tagCompound)) {
                            return false;
                        }
                    }
                }
                else if (target instanceof ArrayList) {
                    boolean matched = false;
                    for (Iterator<ItemStack> itr = ((ArrayList)target).iterator(); itr.hasNext() && !matched; matched = OreDictionary.itemMatches((ItemStack)itr.next(), slot, false)) {}
                    if (!matched) {
                        return false;
                    }
                }
                else if (target == null && slot != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public ExtremeShapedOreRecipe setMirrored(final boolean mirror) {
        this.mirrored = mirror;
        return this;
    }
    
    public Object[] getInput() {
        return this.input;
    }
}

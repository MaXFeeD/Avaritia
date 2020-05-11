// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.bloodmagic;

import net.minecraft.item.Item;
import net.minecraft.block.Block;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.compat.Compat;

public class Bloody
{
    public static void bloodlett() throws Compat.ItemNotFoundException {
        final Block crystal = Compat.getBlock("AWWayofTime", "blockCrystal");
        final ItemStack cluster = new ItemStack(crystal, 1, 0);
        Grinder.catalyst.getInput().add(cluster);
        GameRegistry.registerItem(LudicrousItems.armok_orb = new ItemOrbArmok(), "Orb_Armok");
        final Item transorb = Compat.getItem("AWWayofTime", "transcendentBloodOrb");
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousItems.armok_orb, 1), "   III   ", "  IOIOI  ", "  IIXII  ", " NIOIOIN ", "NNNIIINNN", " NNNNNNN ", "   NNN   ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'O', new ItemStack(transorb));
    }
}

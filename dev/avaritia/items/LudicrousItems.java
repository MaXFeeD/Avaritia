// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import fox.spiteful.avaritia.items.tools.ItemAxeInfinity;
import fox.spiteful.avaritia.items.tools.ItemShovelInfinity;
import fox.spiteful.avaritia.items.tools.ItemBowInfinity;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemFood;
import fox.spiteful.avaritia.items.tools.ItemSwordSkulls;
import fox.spiteful.avaritia.items.tools.ItemSwordInfinity;
import fox.spiteful.avaritia.items.tools.ItemPickaxeInfinity;
import fox.spiteful.avaritia.Config;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;

public class LudicrousItems
{
    public static Item resource;
    public static Item singularity;
    public static Item skull_sword;
    public static Item ultimate_stew;
    public static Item cosmic_meatballs;
    public static Item endest_pearl;
    public static Item fractured_ore;
    public static Item matter_cluster;
    public static Item infinity_pickaxe;
    public static Item infinity_sword;
    public static Item infinity_shovel;
    public static Item infinity_axe;
    public static Item infinity_bow;
    public static Item infinity_helm;
    public static Item infinity_armor;
    public static Item infinity_pants;
    public static Item infinity_shoes;
    public static Item akashic_record;
    public static Item armok_orb;
    public static Item combs;
    public static Item beesource;
    public static Item bigPearl;
    public static EnumRarity cosmic;
    
    public static void grind() {
        GameRegistry.registerItem(LudicrousItems.resource = new ItemResource(), "Resource");
        if (Config.craftingOnly) {
            return;
        }
        LudicrousItems.singularity = register(new ItemSingularity(), "Singularity");
        LudicrousItems.infinity_pickaxe = register((Item)new ItemPickaxeInfinity(), "Infinity_Pickaxe");
        LudicrousItems.infinity_sword = register((Item)new ItemSwordInfinity(), "Infinity_Sword");
        LudicrousItems.skull_sword = register((Item)new ItemSwordSkulls(), "Skull_Sword");
        LudicrousItems.ultimate_stew = register(new ItemFood(20, 20.0f, false).func_77844_a(Potion.field_76428_l.func_76396_c(), 300, 1, 1.0f).func_111206_d("avaritia:stew").func_77655_b("avaritia_stew").func_77637_a(Avaritia.tab), "Ultimate_Stew");
        LudicrousItems.cosmic_meatballs = register(new ItemFood(20, 20.0f, false).func_77844_a(Potion.field_76420_g.func_76396_c(), 300, 1, 1.0f).func_111206_d("avaritia:meatballs").func_77655_b("avaritia_meatballs").func_77637_a(Avaritia.tab), "Cosmic_Meatballs");
        LudicrousItems.endest_pearl = register((Item)new ItemEndestPearl(), "Endest_Pearl");
        LudicrousItems.infinity_helm = register((Item)new ItemArmorInfinity(0), "Infinity_Helm");
        LudicrousItems.infinity_armor = register((Item)new ItemArmorInfinity(1), "Infinity_Chest");
        LudicrousItems.infinity_pants = register((Item)new ItemArmorInfinity(2), "Infinity_Pants");
        LudicrousItems.infinity_shoes = register((Item)new ItemArmorInfinity(3), "Infinity_Shoes");
        if (Config.fractured) {
            LudicrousItems.fractured_ore = register(new ItemFracturedOre(), "Fractured_Ore");
        }
        LudicrousItems.matter_cluster = register(new ItemMatterCluster(), "Matter_Cluster");
        LudicrousItems.infinity_bow = register(new ItemBowInfinity(), "Infinity_Bow");
        LudicrousItems.infinity_shovel = register((Item)new ItemShovelInfinity(), "Infinity_Shovel");
        LudicrousItems.infinity_axe = register((Item)new ItemAxeInfinity(), "Infinity_Axe");
        MinecraftForge.EVENT_BUS.register((Object)new ItemArmorInfinity.abilityHandler());
    }
    
    public static boolean isInfinite(final EntityPlayer player) {
        return player.func_71124_b(1) != null && player.func_71124_b(2) != null && player.func_71124_b(3) != null && player.func_71124_b(4) != null && (player.func_71124_b(1).func_77973_b() == LudicrousItems.infinity_shoes && player.func_71124_b(2).func_77973_b() == LudicrousItems.infinity_pants && player.func_71124_b(3).func_77973_b() == LudicrousItems.infinity_armor && player.func_71124_b(4).func_77973_b() == LudicrousItems.infinity_helm);
    }
    
    public static Item register(final Item item, final String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }
    
    static {
        LudicrousItems.cosmic = EnumHelper.addRarity("COSMIC", EnumChatFormatting.RED, "Cosmic");
    }
}

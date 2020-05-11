// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.achievements;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import fox.spiteful.avaritia.compat.Compat;
import net.minecraft.init.Items;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class Achievements
{
    public static AchievementPage page;
    public static Achievement crystal_matrix;
    public static Achievement dire_crafting;
    public static Achievement dire_uncrafting;
    public static Achievement collector;
    public static Achievement neutronium;
    public static Achievement singularity;
    public static Achievement catalyst;
    public static Achievement infinity;
    public static Achievement armok;
    public static Achievement creative_kill;
    
    public static void achieve() {
        Achievements.crystal_matrix = new LudicrousAchievement("crystal_matrix", 0, 0, new ItemStack(LudicrousItems.resource, 1, 1), null);
        Achievements.dire_crafting = new LudicrousAchievement("dire_crafting", 1, 1, new ItemStack(LudicrousBlocks.dire_crafting), Achievements.crystal_matrix);
        Achievements.collector = new LudicrousAchievement("collector", 2, 2, new ItemStack(LudicrousBlocks.neutron_collector), Achievements.dire_crafting);
        Achievements.neutronium = new LudicrousAchievement("neutronium", 4, 1, new ItemStack(LudicrousItems.resource, 1, 4), Achievements.collector);
        Achievements.singularity = new LudicrousAchievement("singularity", 6, 3, new ItemStack(LudicrousItems.singularity, 1, 0), Achievements.neutronium);
        Achievements.catalyst = new LudicrousAchievement("catalyst", 6, -3, new ItemStack(LudicrousItems.resource, 1, 5), Achievements.singularity).func_75987_b();
        Achievements.infinity = new LudicrousAchievement("infinity", 4, -6, new ItemStack(LudicrousItems.resource, 1, 6), Achievements.neutronium).func_75987_b();
        Achievements.creative_kill = new LudicrousAchievement("creative_kill", -6, -6, new ItemStack(Items.field_151144_bL, 1, 3), null).func_75987_b();
        Achievements.dire_uncrafting = new LudicrousAchievement("dire_uncrafting", -1, 3, new ItemStack(Items.field_151046_w), Achievements.dire_crafting);
        if (Compat.blood) {
            Achievements.armok = new LudicrousAchievement("armok", 2, -5, LudicrousItems.armok_orb, Achievements.infinity);
        }
        AchievementPage.registerAchievementPage(Achievements.page = new AchievementPage("Avaritia", (Achievement[])LudicrousAchievement.achievements.toArray(new Achievement[LudicrousAchievement.achievements.size()])));
        final AchievementTrigger tigger = new AchievementTrigger();
        FMLCommonHandler.instance().bus().register((Object)tigger);
        MinecraftForge.EVENT_BUS.register((Object)tigger);
    }
}

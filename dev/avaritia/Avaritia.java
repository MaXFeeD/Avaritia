// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import fox.spiteful.avaritia.achievements.Achievements;
import fox.spiteful.avaritia.crafting.Mincer;
import fox.spiteful.avaritia.crafting.Gregorizer;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fox.spiteful.avaritia.items.ItemFracturedOre;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.network.IGuiHandler;
import fox.spiteful.avaritia.gui.GooeyHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fox.spiteful.avaritia.crafting.Grinder;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fox.spiteful.avaritia.entity.LudicrousEntities;
import fox.spiteful.avaritia.compat.Compat;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import fox.spiteful.avaritia.items.LudicrousItems;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;

@Mod(modid = "Avaritia", name = "Avaritia", dependencies = "after:Thaumcraft;after:AWWayofTime;after:Botania")
public class Avaritia
{
    @Mod.Instance
    public static Avaritia instance;
    @SidedProxy(serverSide = "fox.spiteful.avaritia.CommonProxy", clientSide = "fox.spiteful.avaritia.ClientProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tab;
    
    @Mod.EventHandler
    public void earlyGame(final FMLPreInitializationEvent event) {
        Avaritia.instance = this;
        Config.configurate(event.getSuggestedConfigurationFile());
        LudicrousItems.grind();
        LudicrousBlocks.voxelize();
        Compat.census();
        if (Config.craftingOnly) {
            return;
        }
        LudicrousEntities.letLooseTheDogsOfWar();
        Avaritia.proxy.prepareForPretty();
    }
    
    @Mod.EventHandler
    public void midGame(final FMLInitializationEvent event) {
        Grinder.artsAndCrafts();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)Avaritia.instance, (IGuiHandler)new GooeyHandler());
        if (Config.craftingOnly) {
            return;
        }
        Avaritia.proxy.makeThingsPretty();
        MinecraftForge.EVENT_BUS.register((Object)new LudicrousEvents());
        if (Config.fractured) {
            ItemFracturedOre.brushUpUncomfortablyAgainstTheOreDictionary();
        }
    }
    
    @Mod.EventHandler
    public void endGame(final FMLPostInitializationEvent event) {
        Compat.compatify();
        Gregorizer.balance();
        if (Config.craftingOnly) {
            return;
        }
        Mincer.countThoseCalories();
        Grinder.lastMinuteChanges();
        Achievements.achieve();
        PotionHelper.healthInspection();
        Avaritia.proxy.theAfterPretty();
    }
    
    static {
        Avaritia.tab = new CreativeTabs("avaritia") {
            @SideOnly(Side.CLIENT)
            public Item func_78016_d() {
                return LudicrousItems.resource;
            }
            
            @SideOnly(Side.CLIENT)
            public int func_151243_f() {
                return 5;
            }
        };
    }
}

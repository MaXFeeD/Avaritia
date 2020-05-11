// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import fox.spiteful.avaritia.render.ShaderHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import fox.spiteful.avaritia.render.LudicrousRenderEvents;
import fox.spiteful.avaritia.render.FracturedOreRenderer;
import fox.spiteful.avaritia.render.CosmicBowRenderer;
import fox.spiteful.avaritia.render.CosmicItemRenderer;
import fox.spiteful.avaritia.entity.EntityHeavenSubArrow;
import fox.spiteful.avaritia.entity.EntityHeavenArrow;
import fox.spiteful.avaritia.render.RenderHeavenArrow;
import fox.spiteful.avaritia.render.RenderGapingVoid;
import fox.spiteful.avaritia.entity.EntityGapingVoid;
import net.minecraft.client.renderer.entity.Render;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import fox.spiteful.avaritia.entity.EntityEndestPearl;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.render.FancyHaloRenderer;
import fox.spiteful.avaritia.compat.CompatClient;

public class ClientProxy extends CommonProxy
{
    public static final double toDeg = 57.29577951308232;
    
    @Override
    public void prepareForPretty() {
        CompatClient.earlyComprettify();
    }
    
    @Override
    public void makeThingsPretty() {
        final FancyHaloRenderer shiny = new FancyHaloRenderer();
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.resource, (IItemRenderer)shiny);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.singularity, (IItemRenderer)shiny);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.endest_pearl, (IItemRenderer)shiny);
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityEndestPearl.class, (Render)new RenderSnowball(LudicrousItems.endest_pearl));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityGapingVoid.class, (Render)new RenderGapingVoid());
        final RenderHeavenArrow arrowrender = new RenderHeavenArrow();
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityHeavenArrow.class, (Render)arrowrender);
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityHeavenSubArrow.class, (Render)arrowrender);
        final CosmicItemRenderer sparkly = new CosmicItemRenderer();
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_sword, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_helm, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_armor, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_pants, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_shoes, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.matter_cluster, (IItemRenderer)sparkly);
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.infinity_bow, (IItemRenderer)new CosmicBowRenderer());
        MinecraftForgeClient.registerItemRenderer(LudicrousItems.fractured_ore, (IItemRenderer)new FracturedOreRenderer());
        CompatClient.comprettify();
        final LudicrousRenderEvents fancyevents = new LudicrousRenderEvents();
        MinecraftForge.EVENT_BUS.register((Object)fancyevents);
        FMLCommonHandler.instance().bus().register((Object)fancyevents);
        ShaderHelper.initShaders();
    }
    
    @Override
    public void theAfterPretty() {
        CompatClient.lateComprettify();
    }
}

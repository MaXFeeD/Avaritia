// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.thaumcraft;

import thaumcraft.api.crafting.InfusionRecipe;
import net.minecraft.item.Item;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import cpw.mods.fml.common.Loader;
import thaumcraft.api.research.ResearchPage;
import fox.spiteful.avaritia.compat.Compat;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.config.ConfigItems;
import net.minecraft.init.Blocks;
import thaumcraft.api.wands.IWandRodOnUpdate;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.crafting.Grinder;
import cpw.mods.fml.common.registry.GameRegistry;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.api.aspects.Aspect;

public class Lucrum
{
    public static Aspect ULTRA_DEATH;
    public static WandRod WAND_ROD_NEUTRONIUM;
    public static WandCap WAND_CAP_CRYSTAL;
    
    public static void abracadabra() throws Compat.ItemNotFoundException {
        Lucrum.ULTRA_DEATH = new Aspect("terminus", 12124160, new Aspect[] { Aspect.GREED, Aspect.ELDRITCH }, new ResourceLocation("avaritia", "textures/misc/terminus.png"), 771);
        GameRegistry.registerItem(LudicrousItems.akashic_record = new ItemAkashicRecord(), "Akashic_Record");
        GameRegistry.registerItem(LudicrousItems.bigPearl = new ItemBigPearl(), "big_pearl");
        Grinder.catalyst.getInput().add(new ItemStack(LudicrousItems.bigPearl));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.resource, 1, 1), new AspectList().add(Lucrum.ULTRA_DEATH, 1).add(Aspect.ENERGY, 8).add(Aspect.CRYSTAL, 32));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.resource, 1, 4), new AspectList().add(Lucrum.ULTRA_DEATH, 2).add(Aspect.METAL, 12).add(Aspect.ENERGY, 12));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.resource, 1, 5), new AspectList().add(Lucrum.ULTRA_DEATH, 5).add(Aspect.EXCHANGE, 12));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.resource, 1, 6), new AspectList().add(Lucrum.ULTRA_DEATH, 16).add(Aspect.ELDRITCH, 64));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousBlocks.neutron_collector, 1, 32767), new AspectList().add(Lucrum.ULTRA_DEATH, 5).add(Aspect.METAL, 64).add(Aspect.ENERGY, 64).add(Aspect.MECHANISM, 64));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 0), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 1), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100).add(Aspect.GREED, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 2), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.SENSES, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 3), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.MECHANISM, 100).add(Aspect.ENERGY, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 4), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.CRYSTAL, 100).add(Aspect.ORDER, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 5), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 6), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 7), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.singularity, 1, 8), new AspectList().add(Lucrum.ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        ThaumcraftApi.registerObjectTag(new ItemStack(LudicrousItems.infinity_sword), new AspectList().add(Lucrum.ULTRA_DEATH, 60).add(Aspect.WEAPON, 999).add(Aspect.DEATH, 999).add(Aspect.ELDRITCH, 100));
        ThaumcraftApi.registerComplexObjectTag(new ItemStack(LudicrousItems.skull_sword), new AspectList().add(Lucrum.ULTRA_DEATH, 1).add(Aspect.FIRE, 2).add(Aspect.CRYSTAL, 16).add(Aspect.DEATH, 4));
        (Lucrum.WAND_ROD_NEUTRONIUM = new WandRod("infinity", 9999999, new ItemStack(LudicrousBlocks.resource_block, 1, 0), 9999, (IWandRodOnUpdate)new CheatyWandUpdate(), new ResourceLocation("avaritia", "textures/models/wand_rod_neutronium.png"))).setGlowing(true);
        (Lucrum.WAND_CAP_CRYSTAL = new WandCap("matrix", 0.0f, new ItemStack(Blocks.field_150483_bI), 1000)).setTexture(new ResourceLocation("avaritia", "textures/models/wand_cap_crystal.png"));
        final ItemStack wand = new ItemStack(ConfigItems.itemWandCasting, 1, 9000);
        ((ItemWandCasting)ConfigItems.itemWandCasting).setRod(wand, Lucrum.WAND_ROD_NEUTRONIUM);
        ((ItemWandCasting)ConfigItems.itemWandCasting).setCap(wand, Lucrum.WAND_CAP_CRYSTAL);
        ExtremeCraftingManager.getInstance().addRecipe(wand, "      C  ", "     CIC ", "     NIIC", "    NINC ", "   NXN   ", " CNIN    ", "CIIN     ", " CIC     ", "  C      ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'C', new ItemStack(LudicrousItems.resource, 1, 1), 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'X', new ItemStack(LudicrousBlocks.resource_block, 1, 1));
        final Item eldritch = Compat.getItem("Thaumcraft", "ItemEldritchObject");
        final Item resource = Compat.getItem("Thaumcraft", "ItemResource");
        final InfusionRecipe pearl_recipe = ThaumcraftApi.addInfusionCraftingRecipe("BIG_PEARL", (Object)new ItemStack(LudicrousItems.bigPearl), 12, new AspectList().add(Aspect.ELDRITCH, 64).add(Aspect.MAGIC, 64).add(Aspect.WATER, 64).add(Aspect.FIRE, 64).add(Aspect.EARTH, 64).add(Aspect.AIR, 64).add(Aspect.ORDER, 64).add(Aspect.ENTROPY, 64).add(Lucrum.ULTRA_DEATH, 32), new ItemStack(eldritch, 1, 3), new ItemStack[] { new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3), new ItemStack(eldritch, 1, 3) });
        new LudicrousResearchItem("BIG_PEARL", "ELDRITCH", new AspectList().add(Lucrum.ULTRA_DEATH, 8).add(Aspect.MAGIC, 16), -1, 8, 5, new ItemStack(LudicrousItems.bigPearl)).setPages(new ResearchPage[] { new ResearchPage("avaritia.research_page.BIG_PEARL.1"), new ResearchPage(pearl_recipe) }).setParents(new String[] { "PRIMPEARL" }).setConcealed().setSecondary().registerResearchItem();
        final InfusionRecipe akashic_recipe = ThaumcraftApi.addInfusionCraftingRecipe("AKASHIC", (Object)new ItemStack(LudicrousItems.akashic_record), 12, new AspectList().add(Aspect.MIND, 64).add(Aspect.MAGIC, 64).add(Lucrum.ULTRA_DEATH, 32), new ItemStack(resource, 1, 9), new ItemStack[] { new ItemStack(LudicrousItems.resource, 1, 6), new ItemStack(LudicrousItems.resource, 1, 6), new ItemStack(LudicrousItems.resource, 1, 6), new ItemStack(LudicrousItems.resource, 1, 6) });
        new LudicrousResearchItem("AKASHIC", "ELDRITCH", new AspectList().add(Lucrum.ULTRA_DEATH, 8).add(Aspect.MIND, 16), -3, 8, 5, new ItemStack(LudicrousItems.akashic_record)).setPages(new ResearchPage[] { new ResearchPage("avaritia.research_page.AKASHIC.1"), new ResearchPage(akashic_recipe) }).setParents(new String[] { "BIG_PEARL" }).setConcealed().setSecondary().registerResearchItem();
        if (Loader.isModLoaded("ThaumicTinkerer")) {
            try {
                final boolean kami = Class.forName("thaumic.tinkerer.common.core.handler.ConfigHandler").getField("enableKami").getBoolean(null);
                if (kami) {
                    final Item kamiResource = Compat.getItem("ThaumicTinkerer", "kamiResource");
                    Grinder.catalyst.getInput().add(new ItemStack(kamiResource, 1, 2));
                }
            }
            catch (Exception e) {
                Lumberjack.log(Level.INFO, e, "Avaritia couldn't find the last research it needs to unlock Ichor.");
            }
        }
    }
}

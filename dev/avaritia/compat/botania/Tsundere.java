// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import net.minecraft.nbt.NBTTagCompound;
import fox.spiteful.avaritia.crafting.ExtremeShapedRecipe;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import net.minecraft.item.crafting.IRecipe;
import fox.spiteful.avaritia.compat.nei.NotEnough;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.lexicon.LexiconPage;
import net.minecraft.init.Items;
import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.api.BotaniaAPI;
import fox.spiteful.avaritia.crafting.Grinder;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.compat.Compat;
import net.minecraft.item.Item;

public class Tsundere
{
    public static Item costumes;
    
    public static void baka() throws Compat.ItemNotFoundException {
        final Item resource = Compat.getItem("Botania", "manaResource");
        final Block storage = Compat.getBlock("Botania", "storage");
        final ItemStack terra = new ItemStack(storage, 1, 1);
        final ItemStack gaia = new ItemStack(resource, 1, 5);
        Grinder.catalyst.getInput().add(terra);
        Grinder.catalyst.getInput().add(gaia);
        BotaniaAPI.registerSubTile("asgardandelion", (Class)SubTileCheaty.class);
        BotaniaAPI.registerSubTileSignature((Class)SubTileCheaty.class, (SubTileSignature)new Signature("asgardandelion"));
        BotaniaAPI.addSubTileToCreativeMenu("asgardandelion");
        final ItemStack cheaty = getFlower("asgardandelion");
        (SubTileCheaty.lexicon = new LudicrousLexicon("asgardandelion", BotaniaAPI.categoryGenerationFlowers)).addPage(BotaniaAPI.internalHandler.textPage("avaritia.lexicon.asgardandelion.0"));
        SubTileCheaty.lexicon.setIcon(cheaty);
        ExtremeCraftingManager.getInstance().addRecipe(cheaty, "   III   ", "  IIIII  ", "  IIXII  ", "  IIIII  ", "   III   ", " nn N nn ", "nnnnNnnnn", " nn N nn ", "    N    ", 'I', new ItemStack(LudicrousItems.resource, 1, 6), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'N', new ItemStack(LudicrousItems.resource, 1, 4), 'n', new ItemStack(LudicrousItems.resource, 1, 3));
        BotaniaAPI.registerSubTile("soarleander", (Class)SubTileChicken.class);
        BotaniaAPI.registerSubTileSignature((Class)SubTileChicken.class, (SubTileSignature)new Signature("soarleander"));
        BotaniaAPI.addSubTileToCreativeMenu("soarleander");
        final ItemStack chicken = getFlower("soarleander");
        final ItemStack chickenitem = new ItemStack(Items.field_151076_bf);
        final RecipeRuneAltar chickenrecipe = BotaniaAPI.registerRuneAltarRecipe(chicken, 8000, new Object[] { getFlower("gourmaryllis"), chickenitem, chickenitem, chickenitem, chickenitem, chickenitem, chickenitem, chickenitem, chickenitem });
        (SubTileChicken.lexicon = new LudicrousLexicon("soarleander", BotaniaAPI.categoryGenerationFlowers)).setLexiconPages(new LexiconPage[] { BotaniaAPI.internalHandler.textPage("avaritia.lexicon.soarleander.0"), BotaniaAPI.internalHandler.runeRecipePage("avaritia.lexicon.soarleander.1", chickenrecipe) });
        SubTileChicken.lexicon.setIcon(chicken);
        LudicrousBlocks.infinitato = GameRegistry.registerBlock((Block)new BlockInfinitato(), "infinitato");
        GameRegistry.registerTileEntity((Class)TileInfinitato.class, "Avaritia_Infinitato");
        GameRegistry.registerItem(Tsundere.costumes = new ItemInfinitatoCostume(), "costumes");
        if (Compat.nei) {
            try {
                NotEnough.hide(new ItemStack(Tsundere.costumes, 1, 32767));
            }
            catch (Throwable t) {}
        }
        final Block potato = Compat.getBlock("Botania", "tinyPotato");
        final ExtremeShapedRecipe tatorecipe = ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(LudicrousBlocks.infinitato), "IIIIIIIII", "IIIIIIIII", "IIISISIII", "IIIIIIIII", "IISIXISII", "IIISSSIII", "IIIIIIIII", "IIIIIIIII", "IIIIIIIII", 'I', new ItemStack(potato), 'X', new ItemStack(LudicrousItems.resource, 1, 5), 'S', new ItemStack(Items.field_151045_i));
        BlockInfinitato.lexiconEntry = new LudicrousLexicon("infinitato", BotaniaAPI.categoryMisc);
        BlockInfinitato.lexiconEntry.setLexiconPages(new LexiconPage[] { BotaniaAPI.internalHandler.textPage("avaritia.lexicon.infinitato.0"), (LexiconPage)new PageLudicrousRecipe("avaritia.lexicon.infinitato.1", (IRecipe)tatorecipe) }).setIcon(new ItemStack(LudicrousBlocks.infinitato));
    }
    
    private static ItemStack getFlower(final String type) throws Compat.ItemNotFoundException {
        final Item specialFlower = Compat.getItem("Botania", "specialFlower");
        final ItemStack flower = new ItemStack(specialFlower, 1, 0);
        final NBTTagCompound tag = new NBTTagCompound();
        tag.func_74778_a("type", type);
        flower.func_77982_d(tag);
        return flower;
    }
}

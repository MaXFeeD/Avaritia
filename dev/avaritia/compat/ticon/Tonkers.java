// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import net.minecraft.nbt.NBTBase;
import tconstruct.library.modifier.ItemModifier;
import tconstruct.library.crafting.ModifyBuilder;
import net.minecraft.nbt.NBTTagCompound;
import tconstruct.modifiers.tools.ModExtraModifier;
import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.item.Item;
import tconstruct.weaponry.TinkerWeaponry;
import tconstruct.tools.TinkerTools;
import net.minecraft.item.ItemStack;
import tconstruct.library.crafting.PatternBuilder;
import tconstruct.library.TConstructRegistry;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.util.EnumChatFormatting;
import tconstruct.library.tools.ToolMaterial;

public class Tonkers
{
    public static ToolMaterial neutronium;
    public static ToolMaterial infinityMetal;
    public static final int neutroniumId = 500;
    public static final int infinityMetalId = 501;
    public static final String neutroniumName = "avaritia_neutronium";
    public static final String infinityMetalName = "avaritia_infinitymetal";
    
    public static void buildstruct() {
        Tonkers.neutronium = new ToolMaterial("avaritia_neutronium", "material.avaritia_neutronium", 5, 4800, 900, 8, 2.5f, 0, 0.0f, EnumChatFormatting.DARK_GRAY.toString(), 3158064);
        Tonkers.infinityMetal = new ToolMaterial("avaritia_infinitymetal", "material.avaritia_infinitymetal", 5, 10000, 6000, 50, 10.0f, 10, 0.0f, LudicrousItems.cosmic.field_77937_e.toString(), 16777215);
        TConstructRegistry.addtoolMaterial(500, Tonkers.neutronium);
        TConstructRegistry.addtoolMaterial(501, Tonkers.infinityMetal);
        TConstructRegistry.addDefaultToolPartMaterial(500);
        TConstructRegistry.addDefaultToolPartMaterial(501);
        TConstructRegistry.addBowMaterial(500, 109, 10.0f);
        TConstructRegistry.addBowMaterial(501, 10, 60.0f);
        TConstructRegistry.addArrowMaterial(500, 5.0f, 0.1f);
        TConstructRegistry.addArrowMaterial(501, 4.0f, 0.0f);
        TConstructRegistry.addDefaultShardMaterial(500);
        final PatternBuilder pb = PatternBuilder.instance;
        pb.registerFullMaterial(new ItemStack(LudicrousItems.resource, 1, 4), 2, "avaritia_neutronium", new ItemStack(TinkerTools.toolShard, 1, 500), new ItemStack(TinkerTools.toolRod, 1, 500), 500);
        for (int m = 0; m < TinkerTools.patternOutputs.length; ++m) {
            if (TinkerTools.patternOutputs[m] != null) {
                TConstructRegistry.addPartMapping(TinkerTools.woodPattern, m + 1, 500, new ItemStack(TinkerTools.patternOutputs[m], 1, 500));
            }
        }
        for (int m = 0; m < TinkerWeaponry.patternOutputs.length; ++m) {
            TConstructRegistry.addPartMapping((Item)TinkerWeaponry.woodPattern, m, 500, new ItemStack(TinkerWeaponry.patternOutputs[m], 1, 500));
        }
        TConstructRegistry.addPartMapping(TinkerTools.woodPattern, 25, 500, new ItemStack((Item)TinkerWeaponry.arrowhead, 1, 500));
        final TonkersEvents events = new TonkersEvents();
        MinecraftForge.EVENT_BUS.register((Object)events);
        FMLCommonHandler.instance().bus().register((Object)events);
        final ItemStack ingot = new ItemStack(LudicrousItems.resource, 1, 6);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.toolRod, 1, 501), "    X", "   X ", "  X  ", " X   ", "X    ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.pickaxeHead, 1, 501), "XXX  ", "  XX ", "   XX", "    X", "    X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.shovelHead, 1, 501), " XXX", "XXXX", "  XX", "  X ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.hatchetHead, 1, 501), "  X  ", " XXX ", "XXXXX", "   X ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.binding, 1, 501), "X X", " X ", "X X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.toughBinding, 1, 501), "X   X", " X X ", "  X  ", " X X ", "X   X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.toughRod, 1, 501), "        X", "       X ", "      X  ", "     X   ", "    X    ", "   X     ", "  X      ", " X       ", "X        ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.largePlate, 1, 501), "XXXXX", "X X X", "XXXXX", "X   X", "XXXXX", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.swordBlade, 1, 501), "   XX", "  XXX", " XXX ", "XXX  ", " X   ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.wideGuard, 1, 501), "X   ", " X  ", " XX ", "   X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.handGuard, 1, 501), "XXX", "  X", "  X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.crossbar, 1, 501), "X  ", "XX ", " XX", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.knifeBlade, 1, 501), "  XX", " XX ", "XX  ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.frypanHead, 1, 501), " XX ", "XXXX", "XXXX", " XX ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.signHead, 1, 501), "XXXXX", "XXXXX", "XXXXX", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.chiselHead, 1, 501), "    X ", "   XXX", "   XX ", "  X   ", " X    ", "X     ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.scytheBlade, 1, 501), "XXXX    ", "  XXXX  ", "    XXX ", "     XXX", "      X ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.broadAxeHead, 1, 501), "  XX", " XXX", "XXXX", "XXXX", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.excavatorHead, 1, 501), "  X  ", " XXX ", "XXXXX", "XXXX ", " XX  ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.largeSwordBlade, 1, 501), "    XXXX", "   XXXX ", "  XXXX  ", " XXXX   ", "XXXX    ", " XX     ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.hammerHead, 1, 501), "  X    ", " XXX   ", "XXXXX  ", " XXXXX ", "  XXXXX", "   XXX ", "    X  ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack((Item)TinkerWeaponry.arrowhead, 1, 501), "XX  ", "XXXX", " XX ", " X  ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack((Item)TinkerWeaponry.partShuriken, 1, 501), "  XX", "XXXX", " XX ", "X X ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack((Item)TinkerWeaponry.partBowLimb, 1, 501), "XXXXXXX", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack((Item)TinkerWeaponry.partCrossbowLimb, 1, 501), "   XXXX", "  XX   ", " XX    ", "XX     ", "X      ", "X      ", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack((Item)TinkerWeaponry.partCrossbowBody, 1, 501), "X      ", " XX    ", " XXX   ", "  XXX  ", "   XX  ", "     X ", "      X", 'X', ingot);
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(TinkerTools.fullGuard, 1, 501), " XXX", "XXX ", "XXX ", " XXX", 'X', ingot);
        ModifyBuilder.registerModifier((ItemModifier)new ModExtraModifier(new ItemStack[] { new ItemStack(LudicrousItems.resource, 1, 5) }, "AvaritiaFree") {
            public void modify(final ItemStack[] recipe, final ItemStack input) {
                final NBTTagCompound tags = this.getModifierTag(input);
                tags.func_74757_a(this.key, true);
                int modifiers = tags.func_74762_e("Modifiers");
                modifiers += 5;
                tags.func_74768_a("Modifiers", modifiers);
            }
        });
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(getBolt(500, 500), "XX", "RX", 'X', new ItemStack(LudicrousItems.resource, 1, 4), 'R', new ItemStack(TinkerTools.toolRod, 1, 500));
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(getBolt(500, 501), "XX", "RX", 'X', new ItemStack(LudicrousItems.resource, 1, 6), 'R', new ItemStack(TinkerTools.toolRod, 1, 500));
        ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(getBolt(501, 501), "XX", "RX", 'X', new ItemStack(LudicrousItems.resource, 1, 6), 'R', new ItemStack(TinkerTools.toolRod, 1, 501));
    }
    
    private static ItemStack getBolt(final int main, final int tip) {
        final ItemStack bolt = new ItemStack((Item)TinkerWeaponry.partBolt, 1, main);
        final NBTTagCompound tag = new NBTTagCompound();
        final NBTTagCompound mat = new NBTTagCompound();
        mat.func_74768_a("Material2", tip);
        tag.func_74782_a("DualMat", (NBTBase)mat);
        bolt.func_77982_d(tag);
        return bolt;
    }
}

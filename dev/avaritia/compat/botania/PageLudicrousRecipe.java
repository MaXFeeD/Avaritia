// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapelessRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapedOreRecipe;
import fox.spiteful.avaritia.crafting.ExtremeShapedRecipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureManager;
import java.util.List;
import vazkii.botania.client.core.helper.RenderHelper;
import java.util.Arrays;
import net.minecraft.util.StatCollector;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.lexicon.LexiconEntry;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageLudicrousRecipe extends PageRecipe
{
    private static final ResourceLocation craftingOverlay;
    IRecipe recipe;
    int ticksElapsed;
    boolean oreDictRecipe;
    boolean shapelessRecipe;
    
    public PageLudicrousRecipe(final String unlocalizedName, final IRecipe recipe) {
        super(unlocalizedName);
        this.ticksElapsed = 0;
        this.recipe = recipe;
    }
    
    public void onPageAdded(final LexiconEntry entry, final int index) {
        LexiconRecipeMappings.map(this.recipe.func_77571_b(), entry, index);
    }
    
    @SideOnly(Side.CLIENT)
    public void renderRecipe(final IGuiLexiconEntry gui, final int mx, final int my) {
        final boolean b = false;
        this.shapelessRecipe = b;
        this.oreDictRecipe = b;
        this.renderCraftingRecipe(gui, this.recipe);
        final TextureManager render = Minecraft.func_71410_x().field_71446_o;
        render.func_110577_a(PageLudicrousRecipe.craftingOverlay);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        final int iconX = gui.getLeft() + 115;
        int iconY = gui.getTop() + 12;
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if (this.shapelessRecipe) {
            ((GuiScreen)gui).func_73729_b(iconX, iconY, 240, 0, 16, 16);
            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16) {
                RenderHelper.renderTooltip(mx, my, (List)Arrays.asList(StatCollector.func_74838_a("botaniamisc.shapeless")));
            }
            iconY += 20;
        }
        render.func_110577_a(PageLudicrousRecipe.craftingOverlay);
        GL11.glEnable(3042);
        if (this.oreDictRecipe) {
            ((GuiScreen)gui).func_73729_b(iconX, iconY, 240, 16, 16, 16);
            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16) {
                RenderHelper.renderTooltip(mx, my, (List)Arrays.asList(StatCollector.func_74838_a("botaniamisc.oredict")));
            }
        }
        GL11.glDisable(3042);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateScreen() {
        ++this.ticksElapsed;
    }
    
    @SideOnly(Side.CLIENT)
    public void renderCraftingRecipe(final IGuiLexiconEntry gui, final IRecipe recipe) {
        if (recipe instanceof ExtremeShapedRecipe) {
            final ExtremeShapedRecipe shaped = (ExtremeShapedRecipe)recipe;
            for (int y = 0; y < shaped.recipeHeight; ++y) {
                for (int x = 0; x < shaped.recipeWidth; ++x) {
                    this.renderItemAtLudicrousGridPos(gui, x, y, shaped.recipeItems[y * shaped.recipeWidth + x], true);
                }
            }
        }
        else if (recipe instanceof ExtremeShapedOreRecipe) {
            this.oreDictRecipe = true;
        }
        else if (recipe instanceof ExtremeShapelessRecipe) {
            this.shapelessRecipe = true;
        }
        else if (recipe instanceof ShapelessOreRecipe) {
            final ShapelessOreRecipe shapeless = (ShapelessOreRecipe)recipe;
        Label_0223:
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    final int index = y * 3 + x;
                    if (index >= shapeless.func_77570_a()) {
                        break Label_0223;
                    }
                    final Object input = shapeless.getInput().get(index);
                    if (input != null) {
                        this.renderItemAtLudicrousGridPos(gui, x, y, (input instanceof ItemStack) ? ((ItemStack)input) : ((ArrayList)input).get(0), true);
                    }
                }
            }
            this.shapelessRecipe = true;
            this.oreDictRecipe = true;
        }
        this.renderItemAtLudicrousGridPos(gui, 4, -1, recipe.func_77571_b(), false);
    }
    
    @SideOnly(Side.CLIENT)
    public void renderItemAtLudicrousGridPos(final IGuiLexiconEntry gui, final int x, final int y, ItemStack stack, final boolean accountForContainer) {
        if (stack == null || stack.func_77973_b() == null) {
            return;
        }
        stack = stack.func_77946_l();
        if (stack.func_77960_j() == 32767) {
            stack.func_77964_b(0);
        }
        final int xPos = gui.getLeft() + x * 13 + 13;
        final int yPos = gui.getTop() + y * 13 + 30 - ((y == -1) ? 5 : 0);
        final ItemStack stack2 = stack.func_77946_l();
        if (stack2.func_77960_j() == -1) {
            stack2.func_77964_b(0);
        }
        this.renderItem(gui, (double)xPos, (double)yPos, stack2, accountForContainer);
    }
    
    static {
        craftingOverlay = new ResourceLocation("avaritia", "textures/gui/lexiconCraftingOverlay.png");
    }
}

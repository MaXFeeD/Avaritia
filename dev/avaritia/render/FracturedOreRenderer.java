// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.renderer.texture.TextureMap;
import fox.spiteful.avaritia.items.ItemFracturedOre;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class FracturedOreRenderer implements IItemRenderer
{
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        final Minecraft mc = Minecraft.func_71410_x();
        switch (type) {
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
                if (item.func_82839_y()) {
                    GL11.glTranslatef(0.0f, -0.3f, 0.01f);
                }
                this.render(item);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: {
                this.render(item);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                this.render(item);
                break;
            }
            case INVENTORY: {
                GL11.glPushMatrix();
                RenderHelper.func_74520_c();
                GL11.glEnable(3008);
                final RenderItem r = RenderItem.getInstance();
                r.renderItemIntoGUI(mc.field_71466_p, mc.func_110434_K(), item, 0, 0, true);
                GL11.glDisable(3008);
                boolean unknown = false;
                if (item.func_77942_o() && item.func_77978_p().func_74764_b("ore")) {
                    final ItemStack orestack = ItemFracturedOre.NameStack.loadFromNBT(item.func_77978_p().func_74775_l("ore")).getStack();
                    mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
                    final Item oreitem = orestack.func_77973_b();
                    if (oreitem instanceof ItemBlock) {
                        GL11.glDisable(2896);
                        GL11.glPushMatrix();
                        final RenderItem ri = RenderItem.getInstance();
                        GL11.glTranslated(33.125, 10.0, 32.0);
                        GL11.glScaled(16.0, 16.0, 16.0);
                        GL11.glRotatef(-90.0f, 0.0f, -1.0f, 0.0f);
                        GL11.glRotatef(45.0f, 0.0f, -1.0f, 0.0f);
                        GL11.glRotatef(210.0f, -1.0f, 0.0f, 0.0f);
                        GL11.glScalef(1.0f, 1.0f, -1.0f);
                        GL11.glTranslatef(-1.0f, -0.5f, -1.0f);
                        GL11.glScalef(0.1f, 0.1f, 0.1f);
                        GL11.glRotated(180.0, 1.0, 0.0, 0.0);
                        GL11.glTranslated(2.0, -3.0, 3.0);
                        GL11.glDepthFunc(518);
                        ri.func_77015_a(mc.field_71466_p, mc.func_110434_K(), orestack, 0, 0);
                        GL11.glDepthFunc(515);
                        GL11.glPopMatrix();
                        GL11.glEnable(2896);
                        mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
                    }
                    else {
                        unknown = true;
                    }
                }
                else {
                    unknown = true;
                }
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 1);
                GL11.glColor4d(1.0, 1.0, 1.0, 0.2);
                final IIcon fracicon = item.func_77973_b().getIcon(item, 0);
                float minu = fracicon.func_94209_e();
                float maxu = fracicon.func_94212_f();
                float minv = fracicon.func_94206_g();
                float maxv = fracicon.func_94210_h();
                final Tessellator t = Tessellator.field_78398_a;
                t.func_78382_b();
                t.func_78374_a(0.0, 0.0, 0.0, (double)minu, (double)minv);
                t.func_78374_a(0.0, 16.0, 0.0, (double)minu, (double)maxv);
                t.func_78374_a(16.0, 16.0, 0.0, (double)maxu, (double)maxv);
                t.func_78374_a(16.0, 0.0, 0.0, (double)maxu, (double)minv);
                t.func_78381_a();
                GL11.glEnable(3008);
                GL11.glBlendFunc(774, 771);
                GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
                t.func_78382_b();
                t.func_78374_a(0.0, 0.0, 0.0, (double)minu, (double)minv);
                t.func_78374_a(0.0, 16.0, 0.0, (double)minu, (double)maxv);
                t.func_78374_a(16.0, 16.0, 0.0, (double)maxu, (double)maxv);
                t.func_78374_a(16.0, 0.0, 0.0, (double)maxu, (double)minv);
                t.func_78381_a();
                GL11.glDisable(3042);
                if (unknown) {
                    final IIcon uicon = ItemFracturedOre.unknownIcon;
                    minu = uicon.func_94209_e();
                    maxu = uicon.func_94212_f();
                    minv = uicon.func_94206_g();
                    maxv = uicon.func_94210_h();
                    t.func_78382_b();
                    t.func_78374_a(0.0, 0.0, 0.0, (double)minu, (double)minv);
                    t.func_78374_a(0.0, 16.0, 0.0, (double)minu, (double)maxv);
                    t.func_78374_a(16.0, 16.0, 0.0, (double)maxu, (double)maxv);
                    t.func_78374_a(16.0, 0.0, 0.0, (double)maxu, (double)minv);
                    t.func_78381_a();
                }
                GL11.glEnable(32826);
                GL11.glEnable(2929);
                r.field_77024_a = true;
                GL11.glPopMatrix();
                break;
            }
        }
    }
    
    public void render(final ItemStack item) {
        final Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float scale = 0.0625f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final IIcon icon = item.func_77973_b().getIcon(item, 0);
        final float f = icon.func_94209_e();
        final float f2 = icon.func_94212_f();
        final float f3 = icon.func_94206_g();
        final float f4 = icon.func_94210_h();
        ItemRenderer.func_78439_a(Tessellator.field_78398_a, f2, f3, f, f4, icon.func_94211_a(), icon.func_94216_b(), scale);
        boolean unknown = false;
        if (item.func_77942_o() && item.func_77978_p().func_74764_b("ore")) {
            final ItemStack orestack = ItemFracturedOre.NameStack.loadFromNBT(item.func_77978_p().func_74775_l("ore")).getStack();
            mc.field_71446_o.func_110577_a(TextureMap.field_110575_b);
            final Item oreitem = orestack.func_77973_b();
            if (oreitem instanceof ItemBlock) {
                final ItemBlock ib = (ItemBlock)oreitem;
                final Block b = ib.field_150939_a;
                if (b instanceof ITileEntityProvider) {
                    unknown = true;
                }
                final IIcon oreicon = b.func_149691_a(0, orestack.func_77960_j());
                final float minu = oreicon.func_94209_e();
                final float maxu = oreicon.func_94212_f();
                final float minv = oreicon.func_94206_g();
                final float maxv = oreicon.func_94210_h();
                GL11.glDepthFunc(514);
                ItemRenderer.func_78439_a(Tessellator.field_78398_a, maxu, minv, minu, maxv, icon.func_94211_a(), icon.func_94216_b(), scale);
                GL11.glDepthFunc(515);
                mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
            }
        }
        else {
            unknown = true;
        }
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glColor4d(1.0, 1.0, 1.0, 0.2);
        final IIcon fracicon = item.func_77973_b().getIcon(item, 0);
        float minu2 = fracicon.func_94209_e();
        float maxu2 = fracicon.func_94212_f();
        float minv2 = fracicon.func_94206_g();
        float maxv2 = fracicon.func_94210_h();
        ItemRenderer.func_78439_a(Tessellator.field_78398_a, maxu2, minv2, minu2, maxv2, icon.func_94211_a(), icon.func_94216_b(), scale);
        GL11.glEnable(3008);
        GL11.glBlendFunc(774, 771);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        mc.field_71460_t.func_78483_a(0.0);
        ItemRenderer.func_78439_a(Tessellator.field_78398_a, maxu2, minv2, minu2, maxv2, icon.func_94211_a(), icon.func_94216_b(), scale);
        GL11.glDisable(3042);
        if (!CosmicRenderShenanigans.inventoryRender) {
            mc.field_71460_t.func_78463_b(0.0);
            GL11.glEnable(2896);
        }
        if (unknown) {
            final IIcon uicon = ItemFracturedOre.unknownIcon;
            minu2 = uicon.func_94209_e();
            maxu2 = uicon.func_94212_f();
            minv2 = uicon.func_94206_g();
            maxv2 = uicon.func_94210_h();
            ItemRenderer.func_78439_a(Tessellator.field_78398_a, maxu2, minv2, minu2, maxv2, uicon.func_94211_a(), uicon.func_94216_b(), scale);
        }
        GL11.glEnable(32826);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IIcon;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class CosmicItemRenderer implements IItemRenderer
{
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        final Minecraft mc = Minecraft.func_71410_x();
        this.processLightLevel(type, item, data);
        switch (type) {
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
                if (item.func_82839_y()) {
                    GL11.glTranslatef(0.0f, -0.3f, 0.01f);
                }
                this.render(item, null);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: {
                this.render(item, (data[1] instanceof EntityPlayer) ? ((EntityPlayer)data[1]) : null);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                this.render(item, (data[1] instanceof EntityPlayer) ? ((EntityPlayer)data[1]) : null);
                break;
            }
            case INVENTORY: {
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                RenderHelper.func_74520_c();
                GL11.glDisable(3008);
                GL11.glDisable(2929);
                final RenderItem r = RenderItem.getInstance();
                r.renderItemIntoGUI(mc.field_71466_p, mc.func_110434_K(), item, 0, 0, true);
                if (item.func_77973_b() instanceof ICosmicRenderItem) {
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    RenderHelper.func_74520_c();
                    GL11.glDisable(3008);
                    GL11.glDisable(2929);
                    final ICosmicRenderItem icri = (ICosmicRenderItem)item.func_77973_b();
                    CosmicRenderShenanigans.cosmicOpacity = icri.getMaskMultiplier(item, null);
                    CosmicRenderShenanigans.inventoryRender = true;
                    CosmicRenderShenanigans.useShader();
                    final IIcon cosmicicon = icri.getMaskTexture(item, null);
                    GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
                    final float minu = cosmicicon.func_94209_e();
                    final float maxu = cosmicicon.func_94212_f();
                    final float minv = cosmicicon.func_94206_g();
                    final float maxv = cosmicicon.func_94210_h();
                    final Tessellator t = Tessellator.field_78398_a;
                    t.func_78382_b();
                    t.func_78374_a(0.0, 0.0, 0.0, (double)minu, (double)minv);
                    t.func_78374_a(0.0, 16.0, 0.0, (double)minu, (double)maxv);
                    t.func_78374_a(16.0, 16.0, 0.0, (double)maxu, (double)maxv);
                    t.func_78374_a(16.0, 0.0, 0.0, (double)maxu, (double)minv);
                    t.func_78381_a();
                    CosmicRenderShenanigans.releaseShader();
                    CosmicRenderShenanigans.inventoryRender = false;
                }
                GL11.glEnable(3008);
                GL11.glEnable(32826);
                GL11.glEnable(2929);
                r.field_77024_a = true;
                GL11.glDisable(3042);
                GL11.glPopMatrix();
                break;
            }
        }
    }
    
    public void render(final ItemStack item, final EntityPlayer player) {
        int passes = 1;
        if (item.func_77973_b().func_77623_v()) {
            passes = item.func_77973_b().getRenderPasses(item.func_77960_j());
        }
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float scale = 0.0625f;
        for (int i = 0; i < passes; ++i) {
            final IIcon icon = this.getStackIcon(item, i, player);
            final float f = icon.func_94209_e();
            final float f2 = icon.func_94212_f();
            final float f3 = icon.func_94206_g();
            final float f4 = icon.func_94210_h();
            final int colour = item.func_77973_b().func_82790_a(item, i);
            final float r = (colour >> 16 & 0xFF) / 255.0f;
            final float g = (colour >> 8 & 0xFF) / 255.0f;
            final float b = (colour & 0xFF) / 255.0f;
            GL11.glColor4f(r, g, b, 1.0f);
            ItemRenderer.func_78439_a(Tessellator.field_78398_a, f2, f3, f, f4, icon.func_94211_a(), icon.func_94216_b(), scale);
        }
        if (item.func_77973_b() instanceof ICosmicRenderItem) {
            GL11.glDisable(3008);
            GL11.glDepthFunc(514);
            final ICosmicRenderItem icri = (ICosmicRenderItem)item.func_77973_b();
            CosmicRenderShenanigans.cosmicOpacity = icri.getMaskMultiplier(item, player);
            CosmicRenderShenanigans.useShader();
            final IIcon cosmicicon = icri.getMaskTexture(item, player);
            final float minu = cosmicicon.func_94209_e();
            final float maxu = cosmicicon.func_94212_f();
            final float minv = cosmicicon.func_94206_g();
            final float maxv = cosmicicon.func_94210_h();
            ItemRenderer.func_78439_a(Tessellator.field_78398_a, maxu, minv, minu, maxv, cosmicicon.func_94211_a(), cosmicicon.func_94216_b(), scale);
            CosmicRenderShenanigans.releaseShader();
            GL11.glDepthFunc(515);
            GL11.glEnable(3008);
        }
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public void processLightLevel(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        switch (type) {
            case ENTITY: {
                final EntityItem ent = (EntityItem)data[1];
                if (ent != null) {
                    CosmicRenderShenanigans.setLightFromLocation(ent.field_70170_p, MathHelper.func_76128_c(ent.field_70165_t), MathHelper.func_76128_c(ent.field_70163_u), MathHelper.func_76128_c(ent.field_70161_v));
                    break;
                }
                break;
            }
            case EQUIPPED: {
                final EntityLivingBase ent2 = (EntityLivingBase)data[1];
                if (ent2 != null) {
                    CosmicRenderShenanigans.setLightFromLocation(ent2.field_70170_p, MathHelper.func_76128_c(ent2.field_70165_t), MathHelper.func_76128_c(ent2.field_70163_u), MathHelper.func_76128_c(ent2.field_70161_v));
                    break;
                }
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                final EntityLivingBase ent2 = (EntityLivingBase)data[1];
                if (ent2 != null) {
                    CosmicRenderShenanigans.setLightFromLocation(ent2.field_70170_p, MathHelper.func_76128_c(ent2.field_70165_t), MathHelper.func_76128_c(ent2.field_70163_u), MathHelper.func_76128_c(ent2.field_70161_v));
                    break;
                }
                break;
            }
            case INVENTORY: {
                CosmicRenderShenanigans.setLightLevel(1.2f);
            }
            default: {
                CosmicRenderShenanigans.setLightLevel(1.0f);
            }
        }
    }
    
    public IIcon getStackIcon(final ItemStack stack, final int pass, final EntityPlayer player) {
        return stack.func_77973_b().getIcon(stack, pass);
    }
}

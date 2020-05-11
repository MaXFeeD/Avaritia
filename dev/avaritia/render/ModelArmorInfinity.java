// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.util.MathHelper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.EnumAction;
import fox.spiteful.avaritia.items.ItemArmorInfinity;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;

@SideOnly(Side.CLIENT)
public class ModelArmorInfinity extends ModelBiped
{
    public static final ModelArmorInfinity armorModel;
    public static final ModelArmorInfinity legModel;
    public static IIcon overlayIcon;
    public static IIcon invulnOverlayIcon;
    public static IIcon eyesIcon;
    public static IIcon wingOverlayIcon;
    public static ResourceLocation eyeTex;
    public static ResourceLocation wingTex;
    public static ResourceLocation wingGlowTex;
    public static int itempagewidth;
    public static int itempageheight;
    public boolean legs;
    public int currentSlot;
    private Random randy;
    private Overlay overlay;
    private Overlay invulnOverlay;
    private boolean invulnRender;
    private boolean showHat;
    private boolean showChest;
    private boolean showLeg;
    private boolean showFoot;
    private float expand;
    public ModelRenderer bipedLeftWing;
    public ModelRenderer bipedRightWing;
    
    public ModelArmorInfinity(final float expand) {
        super(expand, 0.0f, 64, 64);
        this.legs = false;
        this.currentSlot = 0;
        this.randy = new Random();
        this.invulnRender = true;
        this.expand = expand;
        this.overlay = new Overlay(this, expand);
        this.invulnOverlay = new Overlay(this, 0.0f);
        (this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0)).func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, expand * 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f, 0.0f);
    }
    
    public ModelArmorInfinity setLegs(final boolean islegs) {
        this.legs = islegs;
        final int heightoffset = 0;
        final int legoffset = islegs ? 32 : 0;
        (this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16 + legoffset)).func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, this.expand);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + heightoffset, 0.0f);
        (this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16 + legoffset)).func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, this.expand);
        this.field_78123_h.func_78793_a(-1.9f, 12.0f + heightoffset, 0.0f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16 + legoffset);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, this.expand);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + heightoffset, 0.0f);
        return this;
    }
    
    public void rebuildWings() {
        if (this.field_78115_e.field_78805_m == null) {
            this.field_78115_e.field_78805_m = new ArrayList();
        }
        if (this.bipedLeftWing != null) {
            this.field_78115_e.field_78805_m.remove(this.bipedLeftWing);
        }
        if (this.bipedRightWing != null) {
            this.field_78115_e.field_78805_m.remove(this.bipedRightWing);
        }
        this.bipedLeftWing = new ModelRendererWing((ModelBase)this, 0, 0);
        this.bipedLeftWing.field_78809_i = true;
        this.bipedLeftWing.func_78789_a(0.0f, -11.6f, 0.0f, 0, 32, 32);
        this.bipedLeftWing.func_78793_a(-1.5f, 0.0f, 2.0f);
        this.bipedLeftWing.field_78796_g = 1.2566371f;
        this.field_78115_e.func_78792_a(this.bipedLeftWing);
        (this.bipedRightWing = new ModelRendererWing((ModelBase)this, 0, 0)).func_78789_a(0.0f, -11.6f, 0.0f, 0, 32, 32);
        this.bipedRightWing.func_78793_a(1.5f, 0.0f, 2.0f);
        this.bipedRightWing.field_78796_g = -1.2566371f;
        this.field_78115_e.func_78792_a(this.bipedRightWing);
    }
    
    public void func_78088_a(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        final Minecraft mc = Minecraft.func_71410_x();
        final boolean isFlying = entity instanceof EntityPlayer && ((EntityPlayer)entity).field_71075_bZ.field_75100_b && entity.field_70160_al;
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        CosmicRenderShenanigans.useShader();
        CosmicRenderShenanigans.bindItemTexture();
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        if (this.invulnRender) {
            GL11.glColor4d(1.0, 1.0, 1.0, 0.2);
            this.invulnOverlay.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        }
        this.overlay.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        CosmicRenderShenanigans.releaseShader();
        mc.field_71446_o.func_110577_a(ModelArmorInfinity.eyeTex);
        GL11.glDisable(2896);
        mc.field_71460_t.func_78483_a(0.0);
        final long time = mc.field_71439_g.field_70170_p.func_72820_D();
        this.setGems();
        final double pulse = Math.sin(time / 10.0) * 0.5 + 0.5;
        GL11.glColor4d(0.84, 1.0, 0.95, pulse * pulse * pulse * pulse * pulse * pulse * 0.5);
        GL11.glBlendFunc(770, 1);
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        if (this.invulnRender) {
            final long frame = time / 3L;
            this.randy.setSeed(frame * 1723609L);
            final float o = this.randy.nextFloat() * 6.0f;
            final float[] col = RainbowHelper.HSVtoRGB(o, 1.0f, 1.0f);
            GL11.glColor4d((double)col[0], (double)col[1], (double)col[2], 1.0);
            this.setEyes();
            super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        }
        if (!CosmicRenderShenanigans.inventoryRender) {
            mc.field_71460_t.func_78463_b(15.0);
        }
        GL11.glEnable(2896);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        if (isFlying && !CosmicRenderShenanigans.inventoryRender) {
            this.setWings();
            mc.field_71446_o.func_110577_a(ModelArmorInfinity.wingTex);
            super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
            CosmicRenderShenanigans.useShader();
            CosmicRenderShenanigans.bindItemTexture();
            GL11.glDisable(3008);
            GL11.glEnable(3042);
            GL11.glDepthMask(false);
            this.overlay.func_78088_a(entity, f, f1, f2, f3, f4, f5);
            CosmicRenderShenanigans.releaseShader();
            mc.field_71446_o.func_110577_a(ModelArmorInfinity.wingGlowTex);
            GL11.glDisable(2896);
            mc.field_71460_t.func_78483_a(0.0);
            GL11.glColor4d(0.84, 1.0, 0.95, pulse * pulse * pulse * pulse * pulse * pulse * 0.5);
            GL11.glBlendFunc(770, 1);
            super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            if (!CosmicRenderShenanigans.inventoryRender) {
                mc.field_71460_t.func_78463_b(0.0);
            }
            GL11.glEnable(2896);
            GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        }
    }
    
    public void update(final EntityLivingBase entityLiving, final ItemStack itemstack, final int armorSlot) {
        this.currentSlot = armorSlot;
        this.invulnRender = false;
        final ItemStack hat = entityLiving.func_71124_b(4);
        final ItemStack chest = entityLiving.func_71124_b(3);
        final ItemStack leg = entityLiving.func_71124_b(2);
        final ItemStack foot = entityLiving.func_71124_b(1);
        final boolean hasHat = hat != null && hat.func_77973_b() == LudicrousItems.infinity_helm && !((ItemArmorInfinity)LudicrousItems.infinity_helm).hasPhantomInk(hat);
        final boolean hasChest = chest != null && chest.func_77973_b() == LudicrousItems.infinity_armor && !((ItemArmorInfinity)LudicrousItems.infinity_armor).hasPhantomInk(chest);
        final boolean hasLeg = leg != null && leg.func_77973_b() == LudicrousItems.infinity_pants && !((ItemArmorInfinity)LudicrousItems.infinity_pants).hasPhantomInk(leg);
        final boolean hasFoot = foot != null && foot.func_77973_b() == LudicrousItems.infinity_shoes && !((ItemArmorInfinity)LudicrousItems.infinity_shoes).hasPhantomInk(foot);
        if (armorSlot == 0 && hasHat && hasChest && hasLeg && hasFoot) {
            this.invulnRender = true;
        }
        this.showHat = (hasHat && armorSlot == 0);
        this.showChest = (hasChest && armorSlot == 1);
        this.showLeg = (hasLeg && armorSlot == 2);
        this.showFoot = (hasFoot && armorSlot == 3);
        this.field_78116_c.field_78806_j = this.showHat;
        this.field_78114_d.field_78806_j = this.showHat;
        this.field_78115_e.field_78806_j = (this.showChest || this.showLeg);
        this.field_78112_f.field_78806_j = this.showChest;
        this.field_78113_g.field_78806_j = this.showChest;
        this.field_78123_h.field_78806_j = (this.showLeg || this.showFoot);
        this.field_78124_i.field_78806_j = (this.showLeg || this.showFoot);
        this.overlay.field_78116_c.field_78806_j = this.showHat;
        this.overlay.field_78114_d.field_78806_j = this.showHat;
        this.overlay.field_78115_e.field_78806_j = (this.showChest || this.showLeg);
        this.overlay.field_78112_f.field_78806_j = this.showChest;
        this.overlay.field_78113_g.field_78806_j = this.showChest;
        this.overlay.field_78123_h.field_78806_j = (this.showLeg || this.showFoot);
        this.overlay.field_78124_i.field_78806_j = (this.showLeg || this.showFoot);
        this.bipedLeftWing.field_78806_j = false;
        this.bipedRightWing.field_78806_j = false;
        this.overlay.bipedLeftWing.field_78806_j = false;
        this.overlay.bipedRightWing.field_78806_j = false;
        this.field_78117_n = entityLiving.func_70093_af();
        this.field_78093_q = entityLiving.func_70115_ae();
        this.field_78091_s = entityLiving.func_70631_g_();
        this.overlay.field_78117_n = entityLiving.func_70093_af();
        this.overlay.field_78093_q = entityLiving.func_70115_ae();
        this.overlay.field_78091_s = entityLiving.func_70631_g_();
        this.invulnOverlay.field_78117_n = entityLiving.func_70093_af();
        this.invulnOverlay.field_78093_q = entityLiving.func_70115_ae();
        this.invulnOverlay.field_78091_s = entityLiving.func_70631_g_();
        this.overlay.field_78095_p = this.field_78095_p;
        this.invulnOverlay.field_78095_p = this.field_78095_p;
        this.field_78120_m = 0;
        this.field_78118_o = false;
        this.overlay.field_78120_m = 0;
        this.overlay.field_78118_o = false;
        this.invulnOverlay.field_78120_m = 0;
        this.invulnOverlay.field_78118_o = false;
        if (entityLiving instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)entityLiving;
            final ItemStack held_item = player.func_71124_b(0);
            if (held_item != null) {
                this.field_78120_m = 1;
                this.overlay.field_78120_m = 1;
                this.invulnOverlay.field_78120_m = 1;
                if (player.func_71052_bv() > 0) {
                    final EnumAction enumaction = held_item.func_77975_n();
                    if (enumaction == EnumAction.bow) {
                        this.field_78118_o = true;
                        this.overlay.field_78118_o = true;
                        this.invulnOverlay.field_78118_o = true;
                    }
                    else if (enumaction == EnumAction.block) {
                        this.field_78120_m = 3;
                        this.overlay.field_78120_m = 3;
                        this.invulnOverlay.field_78120_m = 3;
                    }
                }
            }
        }
    }
    
    public void func_78087_a(final float f1, final float speed, final float ticks, final float headYaw, final float headPitch, final float f6, final Entity entity) {
        super.func_78087_a(f1, speed, ticks, headYaw, headPitch, f6, entity);
        this.overlay.func_78087_a(f1, speed, ticks, headYaw, headPitch, f6, entity);
        this.invulnOverlay.func_78087_a(f1, speed, ticks, headYaw, headPitch, f6, entity);
        if (RenderManager.field_78727_a.field_78729_o.containsKey(entity.getClass())) {
            final Render r = RenderManager.field_78727_a.field_78729_o.get(entity.getClass());
            if (r instanceof RenderBiped) {
                final ModelBiped m = ((RenderBiped)r).field_77071_a;
                copyBipedAngles(m, this);
            }
        }
    }
    
    public void setEyes() {
        this.field_78116_c.field_78806_j = false;
        this.field_78115_e.field_78806_j = false;
        this.field_78112_f.field_78806_j = false;
        this.field_78113_g.field_78806_j = false;
        this.field_78123_h.field_78806_j = false;
        this.field_78124_i.field_78806_j = false;
        this.field_78114_d.field_78806_j = this.showHat;
    }
    
    public void setGems() {
        this.field_78116_c.field_78806_j = false;
        this.field_78114_d.field_78806_j = false;
        this.field_78115_e.field_78806_j = (!this.legs && this.showChest);
        this.field_78112_f.field_78806_j = (!this.legs && this.showChest);
        this.field_78113_g.field_78806_j = (!this.legs && this.showChest);
        this.field_78123_h.field_78806_j = (this.legs && this.showLeg);
        this.field_78124_i.field_78806_j = (this.legs && this.showLeg);
    }
    
    public void setWings() {
        this.field_78115_e.field_78806_j = (!this.legs && this.showChest);
        this.bipedLeftWing.field_78806_j = true;
        this.bipedRightWing.field_78806_j = true;
        this.field_78114_d.field_78806_j = false;
        this.field_78112_f.field_78806_j = false;
        this.field_78113_g.field_78806_j = false;
        this.field_78123_h.field_78806_j = false;
        this.field_78124_i.field_78806_j = false;
        this.field_78114_d.field_78806_j = false;
        this.field_78116_c.field_78806_j = false;
        this.overlay.field_78115_e.field_78806_j = (!this.legs && this.showChest);
        this.overlay.bipedLeftWing.field_78806_j = true;
        this.overlay.bipedRightWing.field_78806_j = true;
        this.overlay.field_78116_c.field_78806_j = false;
        this.overlay.field_78114_d.field_78806_j = false;
    }
    
    public void rebuildOverlay() {
        this.rebuildWings();
        this.overlay.rebuild(ModelArmorInfinity.overlayIcon, ModelArmorInfinity.wingOverlayIcon);
        this.invulnOverlay.rebuild(ModelArmorInfinity.invulnOverlayIcon, null);
    }
    
    public static void copyPartAngles(final ModelRenderer source, final ModelRenderer dest) {
        dest.field_78795_f = source.field_78795_f;
        dest.field_78796_g = source.field_78796_g;
        dest.field_78808_h = source.field_78808_h;
    }
    
    public static void copyBipedAngles(final ModelBiped source, final ModelBiped dest) {
        copyPartAngles(source.field_78115_e, dest.field_78115_e);
        copyPartAngles(source.field_78122_k, dest.field_78122_k);
        copyPartAngles(source.field_78121_j, dest.field_78121_j);
        copyPartAngles(source.field_78116_c, dest.field_78116_c);
        copyPartAngles(source.field_78114_d, dest.field_78114_d);
        copyPartAngles(source.field_78113_g, dest.field_78113_g);
        copyPartAngles(source.field_78124_i, dest.field_78124_i);
        copyPartAngles(source.field_78112_f, dest.field_78112_f);
        copyPartAngles(source.field_78123_h, dest.field_78123_h);
    }
    
    static {
        armorModel = new ModelArmorInfinity(1.0f);
        legModel = new ModelArmorInfinity(0.5f).setLegs(true);
        ModelArmorInfinity.overlayIcon = null;
        ModelArmorInfinity.invulnOverlayIcon = null;
        ModelArmorInfinity.eyesIcon = null;
        ModelArmorInfinity.wingOverlayIcon = null;
        ModelArmorInfinity.eyeTex = new ResourceLocation("avaritia", "textures/models/infinity_armor_eyes.png");
        ModelArmorInfinity.wingTex = new ResourceLocation("avaritia", "textures/models/infinity_armor_wing.png");
        ModelArmorInfinity.wingGlowTex = new ResourceLocation("avaritia", "textures/models/infinity_armor_wingglow.png");
        ModelArmorInfinity.itempagewidth = 0;
        ModelArmorInfinity.itempageheight = 0;
    }
    
    public class Overlay extends ModelBiped
    {
        public ModelArmorInfinity parent;
        public float expand;
        public ModelRenderer bipedLeftWing;
        public ModelRenderer bipedRightWing;
        
        public Overlay(final ModelArmorInfinity parent, final float expand) {
            this.parent = parent;
            this.expand = expand;
        }
        
        public void rebuild(final IIcon icon, final IIcon wingicon) {
            final int ox = MathHelper.func_76141_d(icon.func_94209_e() * ModelArmorInfinity.itempagewidth);
            final int oy = MathHelper.func_76141_d(icon.func_94206_g() * ModelArmorInfinity.itempageheight);
            final float heightoffset = 0.0f;
            final int legoffset = this.parent.legs ? 32 : 0;
            this.field_78090_t = ModelArmorInfinity.itempagewidth;
            this.field_78089_u = ModelArmorInfinity.itempageheight;
            (this.field_78122_k = new ModelRenderer((ModelBase)this, 0 + ox, 0 + oy)).func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, this.expand);
            (this.field_78121_j = new ModelRenderer((ModelBase)this, 24 + ox, 0 + legoffset + oy)).func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, this.expand);
            (this.field_78116_c = new ModelRenderer((ModelBase)this, 0 + ox, 0 + legoffset + oy)).func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, this.expand);
            this.field_78116_c.func_78793_a(0.0f, 0.0f + heightoffset, 0.0f);
            (this.field_78114_d = new ModelRenderer((ModelBase)this, 32 + ox, 0 + legoffset + oy)).func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, this.expand * 0.5f);
            this.field_78114_d.func_78793_a(0.0f, 0.0f + heightoffset, 0.0f);
            (this.field_78115_e = new ModelRenderer((ModelBase)this, 16 + ox, 16 + legoffset + oy)).func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, this.expand);
            this.field_78115_e.func_78793_a(0.0f, 0.0f + heightoffset, 0.0f);
            (this.field_78112_f = new ModelRenderer((ModelBase)this, 40 + ox, 16 + legoffset + oy)).func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, this.expand);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f + heightoffset, 0.0f);
            this.field_78113_g = new ModelRenderer((ModelBase)this, 40 + ox, 16 + legoffset + oy);
            this.field_78113_g.field_78809_i = true;
            this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, this.expand);
            this.field_78113_g.func_78793_a(5.0f, 2.0f + heightoffset, 0.0f);
            (this.field_78123_h = new ModelRenderer((ModelBase)this, 0 + ox, 16 + legoffset + oy)).func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, this.expand);
            this.field_78123_h.func_78793_a(-1.9f, 12.0f + heightoffset, 0.0f);
            this.field_78124_i = new ModelRenderer((ModelBase)this, 0 + ox, 16 + legoffset + oy);
            this.field_78124_i.field_78809_i = true;
            this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, this.expand);
            this.field_78124_i.func_78793_a(1.9f, 12.0f + heightoffset, 0.0f);
            if (wingicon != null) {
                final int oxw = MathHelper.func_76141_d(wingicon.func_94209_e() * ModelArmorInfinity.itempagewidth);
                final int oyw = MathHelper.func_76141_d(wingicon.func_94206_g() * ModelArmorInfinity.itempageheight);
                if (this.field_78115_e.field_78805_m == null) {
                    this.field_78115_e.field_78805_m = new ArrayList();
                }
                if (this.bipedLeftWing != null) {
                    this.field_78115_e.field_78805_m.remove(this.bipedLeftWing);
                }
                if (this.bipedRightWing != null) {
                    this.field_78115_e.field_78805_m.remove(this.bipedRightWing);
                }
                this.bipedLeftWing = new ModelRendererWing((ModelBase)this, oxw, oyw);
                this.bipedLeftWing.field_78809_i = true;
                this.bipedLeftWing.func_78789_a(0.0f, -11.6f, 0.0f, 0, 32, 32);
                this.bipedLeftWing.func_78793_a(-1.5f, 0.0f, 2.0f);
                this.bipedLeftWing.field_78796_g = 1.2566371f;
                this.field_78115_e.func_78792_a(this.bipedLeftWing);
                (this.bipedRightWing = new ModelRendererWing((ModelBase)this, oxw, oyw)).func_78789_a(0.0f, -11.6f, 0.0f, 0, 32, 32);
                this.bipedRightWing.func_78793_a(1.5f, 0.0f, 2.0f);
                this.bipedRightWing.field_78796_g = -1.2566371f;
                this.field_78115_e.func_78792_a(this.bipedRightWing);
            }
        }
        
        public void func_78088_a(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
            ModelArmorInfinity.copyBipedAngles(this.parent, this);
            super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        }
        
        public void func_78087_a(final float f1, final float f2, final float f3, final float f4, final float f5, final float f6, final Entity entity) {
            super.func_78087_a(f1, f2, f3, f4, f5, f6, entity);
            if (RenderManager.field_78727_a.field_78729_o.containsKey(entity.getClass())) {
                final Render r = RenderManager.field_78727_a.field_78729_o.get(entity.getClass());
                if (r instanceof RenderBiped) {
                    final ModelBiped m = ((RenderBiped)r).field_77071_a;
                    ModelArmorInfinity.copyBipedAngles(m, this);
                }
            }
        }
    }
}

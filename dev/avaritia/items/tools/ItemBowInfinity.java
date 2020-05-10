// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import fox.spiteful.avaritia.render.CosmicBowRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraft.item.EnumAction;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import fox.spiteful.avaritia.entity.EntityHeavenArrow;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import fox.spiteful.avaritia.render.ICosmicRenderItem;
import net.minecraft.item.Item;

public class ItemBowInfinity extends Item implements ICosmicRenderItem
{
    private IIcon[] iconArray;
    private IIcon[] maskArray;
    private IIcon idleMask;
    
    public ItemBowInfinity() {
        this.field_77777_bU = 1;
        this.func_77656_e(9999);
        this.func_77655_b("infinity_bow");
        this.func_111206_d("avaritia:infinity_bow");
        this.func_77637_a(Avaritia.tab);
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    public void func_77615_a(final ItemStack stack, final World world, final EntityPlayer player, final int useCount) {
    }
    
    public void onUsingTick(final ItemStack stack, final EntityPlayer player, final int count) {
        if (count == 1) {
            this.fire(stack, player.field_70170_p, player, 0);
        }
    }
    
    public void fire(final ItemStack stack, final World world, final EntityPlayer player, final int useCount) {
        final int max = this.func_77626_a(stack);
        final float maxf = (float)max;
        final int j = max - useCount;
        final boolean flag = true;
        if (flag || player.field_71071_by.func_146028_b(Items.field_151032_g)) {
            float f = j / maxf;
            f = (f * f + f * 2.0f) / 3.0f;
            if (f < 0.1) {
                return;
            }
            if (f > 1.0) {
                f = 1.0f;
            }
            final EntityArrow entityarrow = new EntityHeavenArrow(world, (EntityLivingBase)player, f * 2.0f);
            entityarrow.func_70239_b(60.0);
            if (f == 1.0f) {
                entityarrow.func_70243_d(true);
            }
            final int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, stack);
            if (k > 0) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 1 + 1.0);
            }
            final int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, stack);
            if (l > 0) {
                entityarrow.func_70240_a(l);
            }
            if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, stack) > 0) {
                entityarrow.func_70015_d(100);
            }
            stack.func_77972_a(1, (EntityLivingBase)player);
            world.func_72956_a((Entity)player, "random.bow", 1.0f, 1.0f / (ItemBowInfinity.field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            if (flag) {
                entityarrow.field_70251_a = 2;
            }
            else {
                player.field_71071_by.func_146026_a(Items.field_151032_g);
            }
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)entityarrow);
            }
        }
    }
    
    public ItemStack func_77654_b(final ItemStack stack, final World world, final EntityPlayer player) {
        return stack;
    }
    
    public int func_77626_a(final ItemStack stack) {
        return 13;
    }
    
    public EnumAction func_77661_b(final ItemStack stack) {
        return EnumAction.bow;
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        final ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return event.result;
        }
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }
    
    public int func_77619_b() {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        final int pullframes = 3;
        this.field_77791_bV = ir.func_94245_a(this.func_111208_A() + "_standby");
        this.idleMask = ir.func_94245_a(this.func_111208_A() + "_standby_mask");
        this.iconArray = new IIcon[pullframes];
        this.maskArray = new IIcon[pullframes];
        for (int i = 0; i < pullframes; ++i) {
            this.iconArray[i] = ir.func_94245_a(this.func_111208_A() + "_pulling_" + i);
            this.maskArray[i] = ir.func_94245_a(this.func_111208_A() + "_pulling_mask_" + i);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final ItemStack stack, final int renderPass, final EntityPlayer player, final ItemStack usingItem, final int useRemaining) {
        if (usingItem != null) {
            final int max = stack.func_77988_m();
            final int pull = max - useRemaining;
            if (pull >= max * 2 / 3.0) {
                return this.iconArray[2];
            }
            if (pull > max / 3.0) {
                return this.iconArray[1];
            }
            if (pull > 0) {
                return this.iconArray[0];
            }
        }
        return this.getIcon(stack, renderPass);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final ItemStack stack, final int pass) {
        return super.getIcon(stack, pass);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77662_d() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getMaskTexture(final ItemStack stack, final EntityPlayer player) {
        int frame = -1;
        if (player != null) {
            final int bframe = CosmicBowRenderer.getBowFrame(player);
            frame = ((bframe != 0) ? bframe : -1);
        }
        if (frame == -1) {
            return this.idleMask;
        }
        return this.maskArray[frame];
    }
    
    @SideOnly(Side.CLIENT)
    public float getMaskMultiplier(final ItemStack stack, final EntityPlayer player) {
        return 1.0f;
    }
}

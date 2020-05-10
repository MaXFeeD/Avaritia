// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraftforge.common.util.EnumHelper;
import fox.spiteful.avaritia.entity.EntityImmortalItem;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.stats.StatBase;
import fox.spiteful.avaritia.achievements.Achievements;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.DamageSource;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import fox.spiteful.avaritia.DamageSourceInfinitySword;
import net.minecraft.entity.Entity;
import fox.spiteful.avaritia.compat.Belmont;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import java.lang.reflect.Field;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;
import fox.spiteful.avaritia.render.ICosmicRenderItem;
import net.minecraft.item.ItemSword;

public class ItemSwordInfinity extends ItemSword implements ICosmicRenderItem
{
    private static final Item.ToolMaterial opSword;
    private IIcon cosmicMask;
    private IIcon pommel;
    public static Field stupidMojangProtectedVariable;
    
    public ItemSwordInfinity() {
        super(ItemSwordInfinity.opSword);
        this.func_77655_b("infinity_sword");
        this.func_111206_d("avaritia:infinity_sword");
        this.func_77637_a(Avaritia.tab);
    }
    
    public boolean func_77644_a(final ItemStack stack, final EntityLivingBase victim, final EntityLivingBase player) {
        if (player.field_70170_p.field_72995_K) {
            return true;
        }
        if (victim instanceof EntityPlayer) {
            final EntityPlayer pvp = (EntityPlayer)victim;
            if (LudicrousItems.isInfinite(pvp)) {
                if (Belmont.isVampire((Entity)pvp)) {
                    victim.func_70097_a(new DamageSourceInfinitySword((Entity)player).func_76361_j().func_76348_h(), 4.0f);
                }
                else {
                    victim.func_70097_a(new DamageSourceInfinitySword((Entity)player).func_76348_h(), 4.0f);
                }
                return true;
            }
            if (pvp.func_70694_bm() != null && pvp.func_70694_bm().func_77973_b() == LudicrousItems.infinity_sword && pvp.func_71039_bw()) {
                return true;
            }
        }
        try {
            ItemSwordInfinity.stupidMojangProtectedVariable.setInt(victim, 60);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e, "The sword isn't reflecting right! Polish it!");
        }
        victim.func_110142_aN().func_94547_a((DamageSource)new DamageSourceInfinitySword((Entity)player), victim.func_110143_aJ(), victim.func_110143_aJ());
        victim.func_70606_j(0.0f);
        if (Belmont.isVampire((Entity)victim)) {
            victim.func_70645_a(new EntityDamageSource("infinity", (Entity)player).func_76361_j());
        }
        else {
            victim.func_70645_a((DamageSource)new EntityDamageSource("infinity", (Entity)player));
        }
        return true;
    }
    
    public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
        if (!entity.field_70170_p.field_72995_K && entity instanceof EntityPlayer) {
            final EntityPlayer victim = (EntityPlayer)entity;
            if (victim.field_71075_bZ.field_75098_d && !victim.field_70128_L && victim.func_110143_aJ() > 0.0f && !LudicrousItems.isInfinite(victim)) {
                victim.func_110142_aN().func_94547_a((DamageSource)new DamageSourceInfinitySword((Entity)player), victim.func_110143_aJ(), victim.func_110143_aJ());
                victim.func_70606_j(0.0f);
                victim.func_70645_a((DamageSource)new EntityDamageSource("infinity", (Entity)player));
                player.func_71064_a((StatBase)Achievements.creative_kill, 1);
                return true;
            }
        }
        return false;
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getMaskTexture(final ItemStack stack, final EntityPlayer player) {
        return this.cosmicMask;
    }
    
    @SideOnly(Side.CLIENT)
    public float getMaskMultiplier(final ItemStack stack, final EntityPlayer player) {
        return 1.0f;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        super.func_94581_a(ir);
        this.cosmicMask = ir.func_94245_a("avaritia:infinity_sword_mask");
        this.pommel = ir.func_94245_a("avaritia:infinity_sword_pommel");
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        if (pass == 1) {
            return this.pommel;
        }
        return super.getIcon(stack, pass);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }
    
    public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityImmortalItem(world, location, itemstack);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack, final int pass) {
        return false;
    }
    
    static {
        opSword = EnumHelper.addToolMaterial("INFINITY_SWORD", 32, 9999, 9999.0f, -3.0f, 200);
        try {
            ItemSwordInfinity.stupidMojangProtectedVariable = ReflectionHelper.findField((Class)EntityLivingBase.class, new String[] { "recentlyHit", "field_70718_bc" });
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
        }
    }
}

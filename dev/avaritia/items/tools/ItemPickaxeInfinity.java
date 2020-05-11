// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import net.minecraftforge.common.util.EnumHelper;
import fox.spiteful.avaritia.entity.EntityImmortalItem;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.block.Block;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.EnumRarity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeInfinity extends ItemPickaxe
{
    private static final Item.ToolMaterial opPickaxe;
    private IIcon hammer;
    public static final Material[] MATERIALS;
    
    public ItemPickaxeInfinity() {
        super(ItemPickaxeInfinity.opPickaxe);
        this.func_77655_b("infinity_pickaxe");
        this.func_77637_a(Avaritia.tab);
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        final ItemStack pick = new ItemStack((Item)this);
        pick.func_77966_a(Enchantment.field_77346_s, 10);
        list.add(pick);
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    public float getDigSpeed(final ItemStack stack, final Block block, final int meta) {
        if (stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("hammer")) {
            return 5.0f;
        }
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return this.field_77864_a;
        }
        return Math.max(this.func_150893_a(stack, block), 6.0f);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        this.field_77791_bV = ir.func_94245_a("avaritia:infinity_pickaxe");
        this.hammer = ir.func_94245_a("avaritia:infinity_hammer");
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        final NBTTagCompound tags = stack.func_77978_p();
        if (tags != null && tags.func_74767_n("hammer")) {
            return this.hammer;
        }
        return this.field_77791_bV;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon func_77650_f(final ItemStack stack) {
        return this.getIcon(stack, 0);
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        if (player.func_70093_af()) {
            NBTTagCompound tags = stack.func_77978_p();
            if (tags == null) {
                tags = new NBTTagCompound();
                stack.func_77982_d(tags);
            }
            if (EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, stack) < 10) {
                stack.func_77966_a(Enchantment.field_77346_s, 10);
            }
            tags.func_74757_a("hammer", !tags.func_74767_n("hammer"));
            player.func_71038_i();
        }
        return stack;
    }
    
    public boolean func_77644_a(final ItemStack stack, final EntityLivingBase victim, final EntityLivingBase player) {
        if (stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("hammer") && (!(victim instanceof EntityPlayer) || !LudicrousItems.isInfinite((EntityPlayer)victim))) {
            final int i = 10;
            victim.func_70024_g((double)(-MathHelper.func_76126_a(player.field_70177_z * 3.1415927f / 180.0f) * i * 0.5f), 2.0, (double)(MathHelper.func_76134_b(player.field_70177_z * 3.1415927f / 180.0f) * i * 0.5f));
        }
        return true;
    }
    
    public boolean onBlockStartBreak(final ItemStack stack, final int x, final int y, final int z, final EntityPlayer player) {
        if (stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("hammer")) {
            final MovingObjectPosition raycast = ToolHelper.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0);
            if (raycast != null) {
                this.breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.field_72310_e);
            }
        }
        return false;
    }
    
    public void breakOtherBlock(final EntityPlayer player, final ItemStack stack, final int x, final int y, final int z, final int originX, final int originY, final int originZ, final int side) {
        final World world = player.field_70170_p;
        final Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!ToolHelper.isRightMaterial(mat, ItemPickaxeInfinity.MATERIALS)) {
            return;
        }
        if (world.func_147437_c(x, y, z)) {
            return;
        }
        final ForgeDirection direction = ForgeDirection.getOrientation(side);
        final int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
        final boolean silk = EnchantmentHelper.func_77502_d((EntityLivingBase)player);
        final boolean doY = direction.offsetY == 0;
        final int range = 8;
        ToolHelper.removeBlocksInIteration(player, stack, world, x, y, z, -range, doY ? -1 : (-range), -range, range, doY ? (range * 2 - 2) : range, range, null, ItemPickaxeInfinity.MATERIALS, silk, fortune, false);
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
        opPickaxe = EnumHelper.addToolMaterial("INFINITY_PICKAXE", 32, 9999, 9999.0f, 6.0f, 200);
        MATERIALS = new Material[] { Material.field_151576_e, Material.field_151573_f, Material.field_151588_w, Material.field_151592_s, Material.field_76233_E, Material.field_151574_g, Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B };
    }
}

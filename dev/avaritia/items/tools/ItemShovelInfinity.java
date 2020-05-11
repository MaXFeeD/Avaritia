// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items.tools;

import net.minecraftforge.common.util.EnumHelper;
import fox.spiteful.avaritia.entity.EntityImmortalItem;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.block.Block;
import fox.spiteful.avaritia.items.LudicrousItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ItemShovelInfinity extends ItemSpade
{
    public static final Item.ToolMaterial opShovel;
    private IIcon destroyer;
    
    public ItemShovelInfinity() {
        super(ItemShovelInfinity.opShovel);
        this.func_77655_b("infinity_shovel");
        this.func_77637_a(Avaritia.tab);
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    public float getDigSpeed(final ItemStack stack, final Block block, final int meta) {
        if (stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("destroyer")) {
            return 5.0f;
        }
        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return this.field_77864_a;
        }
        return Math.max(this.func_150893_a(stack, block), 1.0f);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        this.field_77791_bV = ir.func_94245_a("avaritia:infinity_shovel");
        this.destroyer = ir.func_94245_a("avaritia:infinity_destroyer");
    }
    
    public IIcon getIcon(final ItemStack stack, final int pass) {
        final NBTTagCompound tags = stack.func_77978_p();
        if (tags != null && tags.func_74767_n("destroyer")) {
            return this.destroyer;
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
            tags.func_74757_a("destroyer", !tags.func_74767_n("destroyer"));
            player.func_71038_i();
        }
        return stack;
    }
    
    public boolean onBlockStartBreak(final ItemStack stack, final int x, final int y, final int z, final EntityPlayer player) {
        if (stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("destroyer")) {
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
        opShovel = EnumHelper.addToolMaterial("INFINITY_SHOVEL", 32, 9999, 9999.0f, 7.0f, 200);
    }
}

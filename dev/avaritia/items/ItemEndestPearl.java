// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.entity.EntityLivingBase;
import fox.spiteful.avaritia.entity.EntityEndestPearl;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import fox.spiteful.avaritia.render.IHaloRenderItem;
import net.minecraft.item.ItemEnderPearl;

public class ItemEndestPearl extends ItemEnderPearl implements IHaloRenderItem
{
    public ItemEndestPearl() {
        this.func_77655_b("avaritia_endest_pearl");
        this.func_111206_d("avaritia:endestpearl");
        this.field_77777_bU = 16;
        this.func_77637_a(Avaritia.tab);
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        if (!player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (ItemEndestPearl.field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!world.field_72995_K) {
            world.func_72838_d((Entity)new EntityEndestPearl(world, (EntityLivingBase)player));
        }
        return stack;
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return EnumRarity.rare;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawHalo(final ItemStack stack) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getHaloTexture(final ItemStack stack) {
        return ((ItemResource)LudicrousItems.resource).halo[0];
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloSize(final ItemStack stack) {
        return 4;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean drawPulseEffect(final ItemStack stack) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public int getHaloColour(final ItemStack stack) {
        return -16777216;
    }
}

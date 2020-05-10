// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.thaumcraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import fox.spiteful.avaritia.compat.Compat;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import java.util.Iterator;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.api.aspects.Aspect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.item.Item;

public class ItemAkashicRecord extends Item
{
    public ItemAkashicRecord() {
        this.func_77655_b("akashic_record");
        this.func_111206_d("avaritia:akashic_record");
        this.func_77637_a(Avaritia.tab);
    }
    
    public ItemStack func_77659_a(final ItemStack stack, final World world, final EntityPlayer player) {
        if (world.field_72995_K) {
            return stack;
        }
        for (final Aspect aspect : Aspect.aspects.values()) {
            Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)999);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(aspect.getTag(), (Short)999, Short.valueOf(Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect))), (EntityPlayerMP)player);
        }
        if (!player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        return stack;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item item, final CreativeTabs tab, final List list) {
        super.func_150895_a(item, tab, list);
        try {
            final Item wand = Compat.getItem("Thaumcraft", "WandCasting");
            final ItemStack cosmic = new ItemStack(wand, 1, 9000);
            final NBTTagCompound tag = new NBTTagCompound();
            tag.func_74778_a("cap", "matrix");
            tag.func_74778_a("rod", "infinity");
            cosmic.func_77982_d(tag);
            list.add(cosmic);
        }
        catch (Throwable t) {}
    }
}

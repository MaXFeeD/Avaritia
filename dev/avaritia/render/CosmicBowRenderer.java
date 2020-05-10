// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class CosmicBowRenderer extends CosmicItemRenderer implements IItemRenderer
{
    private static String[] iiuObf;
    private static String[] iiucObf;
    
    @Override
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack stack, final Object... data) {
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.2f, -0.3f, 0.15f);
            super.renderItem(type, stack, data);
            GL11.glPopMatrix();
        }
        else {
            super.renderItem(type, stack, data);
        }
    }
    
    public static int getBowFrame(final EntityPlayer player) {
        final ItemStack inuse = (ItemStack)ReflectionHelper.getPrivateValue((Class)EntityPlayer.class, (Object)player, CosmicBowRenderer.iiuObf);
        final int time = (int)ReflectionHelper.getPrivateValue((Class)EntityPlayer.class, (Object)player, CosmicBowRenderer.iiucObf);
        if (inuse != null) {
            final int max = inuse.func_77988_m();
            final double pull = (max - time) / (double)max;
            return Math.max(0, (int)Math.ceil(pull * 3.0) - 1);
        }
        return 0;
    }
    
    @Override
    public IIcon getStackIcon(final ItemStack stack, final int pass, final EntityPlayer player) {
        final Item item = stack.func_77973_b();
        ItemStack inuse;
        if (player != null) {
            inuse = (ItemStack)ReflectionHelper.getPrivateValue((Class)EntityPlayer.class, (Object)player, CosmicBowRenderer.iiuObf);
        }
        else {
            inuse = stack;
        }
        int time;
        if (player != null) {
            time = (int)ReflectionHelper.getPrivateValue((Class)EntityPlayer.class, (Object)player, CosmicBowRenderer.iiucObf);
        }
        else {
            time = 0;
        }
        return item.getIcon(stack, pass, player, inuse, time);
    }
    
    static {
        CosmicBowRenderer.iiuObf = new String[] { "itemInUse", "field_71074_e", "f" };
        CosmicBowRenderer.iiucObf = new String[] { "itemInUseCount", "field_71072_f", "g" };
    }
}

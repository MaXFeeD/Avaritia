// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.tails;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.EntityLiving;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.lwjgl.opengl.GL11;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import java.lang.reflect.Constructor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import java.lang.reflect.Method;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InfiniteFoxes
{
    @SideOnly(Side.CLIENT)
    private static FakeTailEntity fakeEntity;
    @SideOnly(Side.CLIENT)
    private static Object tailPartInfo;
    @SideOnly(Side.CLIENT)
    private static Object earPartInfo;
    @SideOnly(Side.CLIENT)
    private static Object foxTailRender;
    @SideOnly(Side.CLIENT)
    private static Object foxEarsRender;
    @SideOnly(Side.CLIENT)
    private static Method m_RenderPart_render;
    
    public static void floof() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            grabReflections();
            MinecraftForge.EVENT_BUS.register((Object)new InfiniteFoxes());
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void grabReflections() {
        try {
            final Class c_PartType = Class.forName("kihira.tails.common.PartsData$PartType");
            final Class c_PartInfo = Class.forName("kihira.tails.common.PartInfo");
            final Constructor constr = c_PartInfo.getConstructor(Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, int[].class, c_PartType, ResourceLocation.class);
            InfiniteFoxes.tailPartInfo = constr.newInstance(true, 0, 2, 0, new int[] { -5480951, -6594259, -5197647 }, Enum.valueOf((Class<Object>)c_PartType, "TAIL"), null);
            InfiniteFoxes.earPartInfo = constr.newInstance(true, 0, 0, 0, new int[] { -5480951, -16777216, -5197647 }, Enum.valueOf((Class<Object>)c_PartType, "EARS"), null);
            final Class c_RenderPart = Class.forName("kihira.tails.client.render.RenderPart");
            final Class c_PartRegistry = Class.forName("kihira.tails.client.PartRegistry");
            final Method m_getRenderPart = c_PartRegistry.getMethod("getRenderPart", c_PartType, Integer.TYPE);
            InfiniteFoxes.foxTailRender = m_getRenderPart.invoke(null, Enum.valueOf((Class<Object>)c_PartType, "TAIL"), 0);
            InfiniteFoxes.foxEarsRender = m_getRenderPart.invoke(null, Enum.valueOf((Class<Object>)c_PartType, "EARS"), 0);
            InfiniteFoxes.m_RenderPart_render = c_RenderPart.getMethod("render", EntityLivingBase.class, c_PartInfo, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void renderInfinitatoFluff(final float partialTicks) {
        if (InfiniteFoxes.fakeEntity == null) {
            InfiniteFoxes.fakeEntity = new FakeTailEntity((World)Minecraft.func_71410_x().field_71441_e);
        }
        if (InfiniteFoxes.m_RenderPart_render != null) {
            try {
                GL11.glScalef(2.0f, 2.0f, 2.0f);
                GL11.glTranslatef(0.0f, 0.25f, 0.5f);
                InfiniteFoxes.m_RenderPart_render.invoke(InfiniteFoxes.foxTailRender, InfiniteFoxes.fakeEntity, InfiniteFoxes.tailPartInfo, 0, 0, 0, partialTicks);
                GL11.glTranslatef(0.0f, 0.25f, -0.75f);
                GL11.glScalef(2.0f, 2.0f, 2.0f);
                InfiniteFoxes.m_RenderPart_render.invoke(InfiniteFoxes.foxEarsRender, InfiniteFoxes.fakeEntity, InfiniteFoxes.earPartInfo, 0, 0, 0, partialTicks);
            }
            catch (Exception ex) {}
        }
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onWorldUnload(final WorldEvent.Unload e) {
        if (InfiniteFoxes.fakeEntity != null) {
            InfiniteFoxes.fakeEntity.func_70106_y();
            InfiniteFoxes.fakeEntity = null;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static class FakeTailEntity extends EntityLiving
    {
        public FakeTailEntity(final World world) {
            super(world);
        }
        
        public void func_70109_d(final NBTTagCompound nbt) {
        }
        
        public void func_70020_e(final NBTTagCompound nbt) {
        }
    }
}

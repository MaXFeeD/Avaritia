// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import java.util.Set;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import com.google.common.base.Throwables;
import javax.imageio.ImageIO;
import net.minecraft.client.resources.ResourcePackRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fox.spiteful.avaritia.Lumberjack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.util.ResourceLocation;
import java.util.HashMap;
import java.awt.image.DirectColorModel;
import java.util.List;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;

public abstract class TConTextureResourcePackBase implements IResourcePack, IResourceManagerReloadListener
{
    public static List<IResourcePack> packs;
    protected static DirectColorModel rgb;
    protected final String name;
    public HashMap<ResourceLocation, byte[]> cachedImages;
    protected IResourcePack delegate;
    protected List<IResourcePack> resourcePackz;
    private int[] colourarray;
    
    public TConTextureResourcePackBase(final String name) {
        this.cachedImages = new HashMap<ResourceLocation, byte[]>();
        this.resourcePackz = null;
        this.colourarray = new int[4];
        this.name = name.toLowerCase();
        this.delegate = FMLClientHandler.instance().getResourcePackFor("TConstruct");
    }
    
    public int brightness(final int col) {
        return this.brightness(TConTextureResourcePackBase.rgb.getRed(col), TConTextureResourcePackBase.rgb.getGreen(col), TConTextureResourcePackBase.rgb.getBlue(col));
    }
    
    public int brightness(final int r, final int g, final int b) {
        return (int)(r * 0.2126f + g * 0.7152f + b * 0.0722f);
    }
    
    public void register() {
        final List<IResourcePack> packs = this.getiResourcePacks();
        packs.add((IResourcePack)this);
        ((IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L()).func_110542_a((IResourceManagerReloadListener)this);
        Lumberjack.info("Registered TCon Resource Pack (" + this.name + ") - " + this.getClass().getSimpleName());
    }
    
    public List<IResourcePack> getiResourcePacks() {
        List<IResourcePack> packs1 = TConTextureResourcePackBase.packs;
        if (packs1 == null) {
            packs1 = (List<IResourcePack>)ObfuscationReflectionHelper.getPrivateValue((Class)FMLClientHandler.class, (Object)FMLClientHandler.instance(), new String[] { "resourcePackList" });
        }
        return packs1;
    }
    
    public InputStream getStream(final ResourceLocation location) {
        InputStream stream = null;
        for (final IResourcePack iResourcePack : this.getPacks()) {
            if (iResourcePack.func_110589_b(location)) {
                try {
                    stream = iResourcePack.func_110590_a(location);
                }
                catch (IOException ex) {}
            }
        }
        return stream;
    }
    
    public List<IResourcePack> getPacks() {
        if (this.resourcePackz == null) {
            (this.resourcePackz = new ArrayList<IResourcePack>()).add(this.delegate);
            final List<ResourcePackRepository.Entry> t = (List<ResourcePackRepository.Entry>)Minecraft.func_71410_x().func_110438_M().func_110613_c();
            for (final ResourcePackRepository.Entry entry : t) {
                final IResourcePack resourcePack = entry.func_110514_c();
                if (resourcePack.func_110587_b().contains("tinker")) {
                    this.resourcePackz.add(resourcePack);
                }
            }
        }
        return this.resourcePackz;
    }
    
    public InputStream func_110590_a(final ResourceLocation p_110590_1_) throws IOException {
        byte[] bytes = this.cachedImages.get(p_110590_1_);
        if (bytes == null) {
            ResourceLocation location = new ResourceLocation("tinker", p_110590_1_.func_110623_a().replace(this.name, ""));
            InputStream inputStream = this.getStream(location);
            if (inputStream == null) {
                location = new ResourceLocation("tinker", p_110590_1_.func_110623_a().replace(this.name, "iron"));
                inputStream = this.getStream(location);
            }
            if (inputStream == null) {
                location = new ResourceLocation("tinker", p_110590_1_.func_110623_a().replace(this.name, "stone"));
                inputStream = this.getStream(location);
            }
            if (inputStream == null) {
                return this.delegate.func_110590_a(p_110590_1_);
            }
            BufferedImage bufferedimage;
            try {
                bufferedimage = ImageIO.read(inputStream);
            }
            catch (IOException err) {
                throw Throwables.propagate((Throwable)err);
            }
            BufferedImage image;
            try {
                image = this.modifyImage(bufferedimage);
            }
            catch (Throwable t) {
                t.printStackTrace();
                return this.delegate.func_110590_a(location);
            }
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", stream);
            bytes = stream.toByteArray();
            this.cachedImages.put(location, bytes);
        }
        return new ByteArrayInputStream(bytes);
    }
    
    public abstract BufferedImage modifyImage(final BufferedImage p0);
    
    public boolean func_110589_b(final ResourceLocation p_110589_1_) {
        if (!"tinker".equals(p_110589_1_.func_110624_b())) {
            return false;
        }
        final String resourcePath = p_110589_1_.func_110623_a();
        return resourcePath.startsWith("textures/items/") && resourcePath.endsWith(".png") && !this.delegate.func_110589_b(p_110589_1_) && resourcePath.contains(this.name) && (this.delegate.func_110589_b(new ResourceLocation("tinker", resourcePath.replace(this.name, "stone"))) || this.delegate.func_110589_b(new ResourceLocation("tinker", resourcePath.replace(this.name, "iron"))) || this.delegate.func_110589_b(new ResourceLocation("tinker", resourcePath.replace(this.name, ""))));
    }
    
    public Set func_110587_b() {
        return this.delegate.func_110587_b();
    }
    
    public IMetadataSection func_135058_a(final IMetadataSerializer p_135058_1_, final String p_135058_2_) throws IOException {
        return null;
    }
    
    public BufferedImage func_110586_a() throws IOException {
        return null;
    }
    
    public String func_130077_b() {
        return "Avaritia_Delegate_Pack";
    }
    
    public void func_110549_a(final IResourceManager p_110549_1_) {
        this.cachedImages.clear();
        this.resourcePackz = null;
    }
    
    protected int colour(final int r, final int g, final int b, final int a) {
        this.colourarray[0] = r;
        this.colourarray[1] = g;
        this.colourarray[2] = b;
        this.colourarray[3] = a;
        return TConTextureResourcePackBase.rgb.getDataElement(this.colourarray, 0);
    }
    
    static {
        TConTextureResourcePackBase.rgb = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
    }
}

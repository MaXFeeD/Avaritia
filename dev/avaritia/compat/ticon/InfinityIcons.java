// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import java.io.IOException;
import net.minecraft.client.resources.IResourceManager;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import codechicken.lib.math.MathHelper;
import java.io.InputStream;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import java.awt.image.BufferedImage;

public class InfinityIcons extends TConTextureResourcePackBase
{
    protected BufferedImage infinityImage;
    
    public InfinityIcons() {
        super("avaritia_infinitymetal");
    }
    
    protected void loadInfinityImage() {
        if (this.infinityImage == null) {
            final ResourceLocation res = new ResourceLocation("avaritia", "textures/items/ticoninfinity.png");
            try {
                final InputStream stream = Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b();
                this.infinityImage = ImageIO.read(stream);
            }
            catch (Exception err) {
                Lumberjack.log(Level.WARN, "Unable to load overlay image for Infinity tool parts");
            }
        }
    }
    
    protected InputStream loadInfinityAnimation() {
        InputStream stream = null;
        final ResourceLocation res = new ResourceLocation("avaritia", "textures/items/ticoninfinitymeta.png.mcmeta");
        try {
            stream = Minecraft.func_71410_x().func_110442_L().func_110536_a(res).func_110527_b();
        }
        catch (Exception err) {
            Lumberjack.log(Level.WARN, "Unable to load animation data for Infinity tool parts");
        }
        return stream;
    }
    
    @Override
    public BufferedImage modifyImage(BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        if (image.getType() != 2) {
            final BufferedImage temp = new BufferedImage(w, h, 2);
            final int[] data = new int[w * h];
            image.getRGB(0, 0, w, h, data, 0, w);
            temp.setRGB(0, 0, w, h, data, 0, w);
            image = temp;
        }
        int left = w - 1;
        int right = 0;
        int top = h - 1;
        int bottom = 0;
        boolean anyOpaque = false;
        int opaquecount = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                final int c = image.getRGB(x, y);
                final int a = InfinityIcons.rgb.getAlpha(c);
                if (a != 0) {
                    left = Math.min(x, left);
                    right = Math.max(x, right);
                    top = Math.min(y, top);
                    bottom = Math.max(y, bottom);
                    anyOpaque = true;
                    ++opaquecount;
                }
            }
        }
        final int opaqueWidth = right - left + 1;
        final int opaqueHeight = bottom - top + 1;
        final int opaqueSquare = Math.max(w / 2, Math.max(opaqueHeight, opaqueWidth));
        if (opaqueWidth < opaqueSquare) {
            final int diff = opaqueSquare - opaqueWidth;
            left -= diff / 2;
        }
        else if (opaqueHeight < opaqueSquare) {
            final int diff = opaqueSquare - opaqueHeight;
            top -= diff / 2;
        }
        final BufferedImage infinity = new BufferedImage(w, h, 2);
        this.loadInfinityImage();
        if (anyOpaque && this.infinityImage != null) {
            final int iw = this.infinityImage.getWidth();
            final int ih = this.infinityImage.getHeight();
            int sx1 = 0;
            int sx2 = iw;
            int sy1 = 0;
            int sy2 = ih;
            if (opaquecount > w * h * 0.3) {
                sx1 = MathHelper.floor_double(iw * 0.25);
                sx2 = MathHelper.floor_double(iw * 0.75);
                sy1 = MathHelper.floor_double(ih * 0.25);
                sy2 = MathHelper.floor_double(ih * 0.75);
            }
            final Graphics2D graphics = infinity.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawImage(this.infinityImage, left, top, left + opaqueSquare, top + opaqueSquare, sx1, sy1, sx2, sy2, null);
        }
        final int frames = 9;
        final BufferedImage framesimage = new BufferedImage(w, h * frames, 2);
        final double brighten = 1.3;
        for (int y2 = 0; y2 < h; ++y2) {
            for (int x2 = 0; x2 < w; ++x2) {
                final int c = image.getRGB(x2, y2);
                final double l = Math.min(1.0, Math.max(0.4, this.brightness(c) / 255.0 * 1.4 - 0.4));
                final int a = InfinityIcons.rgb.getAlpha(c);
                if (a != 0) {
                    final int inf = infinity.getRGB(x2, y2);
                    final int r = Math.min(255, MathHelper.floor_double(InfinityIcons.rgb.getRed(inf) * l * brighten));
                    final int g = Math.min(255, MathHelper.floor_double(InfinityIcons.rgb.getGreen(inf) * l * brighten));
                    final int b = Math.min(255, MathHelper.floor_double(InfinityIcons.rgb.getBlue(inf) * l * brighten));
                    for (int i = 0; i < frames; ++i) {
                        final double frac = 0.125 * i;
                        final int fr = MathHelper.floor_double((1.0 - frac) * r + frac * 255.0);
                        final int fg = MathHelper.floor_double((1.0 - frac) * g + frac * 255.0);
                        final int fb = MathHelper.floor_double((1.0 - frac) * b + frac * 255.0);
                        framesimage.setRGB(x2, y2 + h * i, this.colour(fr, fg, fb, a));
                    }
                }
            }
        }
        return framesimage;
    }
    
    @Override
    public void func_110549_a(final IResourceManager manager) {
        super.func_110549_a(manager);
        this.infinityImage = null;
    }
    
    @Override
    public boolean func_110589_b(final ResourceLocation resource) {
        final String resourcePath = resource.func_110623_a();
        if (resourcePath.endsWith(".mcmeta")) {
            final String testpath = resourcePath.substring(0, resourcePath.length() - 7);
            if (this.func_110589_b(new ResourceLocation(resource.func_110624_b(), testpath))) {
                return true;
            }
        }
        return super.func_110589_b(resource);
    }
    
    @Override
    public InputStream func_110590_a(final ResourceLocation resource) throws IOException {
        if (resource.func_110623_a().endsWith(".mcmeta")) {
            return this.loadInfinityAnimation();
        }
        return super.func_110590_a(resource);
    }
}

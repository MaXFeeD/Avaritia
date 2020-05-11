// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import codechicken.lib.math.MathHelper;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NeutroniumIcons extends TConTextureResourcePackBase
{
    private double[] noise;
    
    public NeutroniumIcons() {
        super("avaritia_neutronium");
        final int len = 256;
        final Random randy = new Random(12345L);
        this.noise = new double[len];
        for (int i = 0; i < len; ++i) {
            this.noise[i] = randy.nextDouble();
        }
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
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                final int c = image.getRGB(x, y);
                final int l = MathHelper.floor_double(Math.min(255.0, (255 - this.brightness(c)) * 1.05));
                final int a = NeutroniumIcons.rgb.getAlpha(c);
                if (this.isEdge(image, x, y, w, h, a)) {
                    final double n = this.noise[(y * w + x) % this.noise.length];
                    final double e = Math.min(1.0, l / 255.0 * 0.5 + 0.5 + n * 0.2);
                    final int r = MathHelper.floor_double(e * 188.0);
                    final int g = MathHelper.floor_double(e * 192.0);
                    final int b = MathHelper.floor_double(e * 219.0);
                    image.setRGB(x, y, this.colour(r, g, b, a));
                }
                else {
                    image.setRGB(x, y, this.colour(l, l, l, a));
                }
            }
        }
        return image;
    }
    
    private boolean isEdge(final BufferedImage image, final int x, final int y, final int w, final int h, final int a) {
        if (a != 0) {
            if (x == 0 || x == w - 1 || y == 0 || y == h - 1) {
                return true;
            }
            if (NeutroniumIcons.rgb.getAlpha(image.getRGB(x - 1, y)) != 255 || NeutroniumIcons.rgb.getAlpha(image.getRGB(x + 1, y)) != 255 || NeutroniumIcons.rgb.getAlpha(image.getRGB(x, y - 1)) != 255 || NeutroniumIcons.rgb.getAlpha(image.getRGB(x, y + 1)) != 255) {
                return true;
            }
        }
        return false;
    }
}

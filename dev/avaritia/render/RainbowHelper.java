// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

public class RainbowHelper
{
    public static float[] HSVtoRGB(final float h, final float s, final float v) {
        final float[] hsv = new float[3];
        final float[] rgb = new float[3];
        hsv[0] = h;
        hsv[1] = s;
        hsv[2] = v;
        if (hsv[0] == -1.0f) {
            final float[] array = rgb;
            final int n2 = 0;
            final float[] array2 = rgb;
            final int n3 = 1;
            final float[] array3 = rgb;
            final int n4 = 2;
            final float n5 = hsv[2];
            array3[n4] = n5;
            array[n2] = (array2[n3] = n5);
            return rgb;
        }
        final int i = (int)Math.floor(hsv[0]);
        float f = hsv[0] - i;
        if (i % 2 == 0) {
            f = 1.0f - f;
        }
        final float m = hsv[2] * (1.0f - hsv[1]);
        final float n = hsv[2] * (1.0f - hsv[1] * f);
        switch (i) {
            case 0:
            case 6: {
                rgb[0] = hsv[2];
                rgb[1] = n;
                rgb[2] = m;
                break;
            }
            case 1: {
                rgb[0] = n;
                rgb[1] = hsv[2];
                rgb[2] = m;
                break;
            }
            case 2: {
                rgb[0] = m;
                rgb[1] = hsv[2];
                rgb[2] = n;
                break;
            }
            case 3: {
                rgb[0] = m;
                rgb[1] = n;
                rgb[2] = hsv[2];
                break;
            }
            case 4: {
                rgb[0] = n;
                rgb[1] = m;
                rgb[2] = hsv[2];
                break;
            }
            case 5: {
                rgb[0] = hsv[2];
                rgb[1] = m;
                rgb[2] = n;
                break;
            }
        }
        return rgb;
    }
}

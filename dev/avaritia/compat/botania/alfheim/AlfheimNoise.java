// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.util.Vec3;
import net.minecraft.util.MathHelper;
import java.util.Random;

public class AlfheimNoise
{
    public final long seed;
    private final double[] randomSource;
    private static int XSEED;
    private static int ZSEED;
    private static int NSEED;
    private static int NSHIFT;
    
    public AlfheimNoise(final long seed) {
        this.seed = seed;
        this.randomSource = new double[512];
        final Random randy = new Random(this.seed);
        for (int i = 0; i < 256; ++i) {
            final double r = randy.nextDouble();
            final double a = randy.nextDouble() * 2.0 * 3.141592653589793;
            this.randomSource[i * 2] = Math.sin(a) * r;
            this.randomSource[i * 2 + 1] = Math.cos(a) * r;
        }
    }
    
    private double getNoise(final double x, final double z, final double period, final long seed) {
        final double frequency = (period == 0.0) ? 1.0 : (1.0 / period);
        return this.calcNoise(x * frequency, z * frequency, seed);
    }
    
    private double calcNoise(final double x, final double z, final long seed) {
        final int x2 = (x > 0.0) ? MathHelper.func_76128_c(x) : (MathHelper.func_76128_c(x) - 1);
        final int x3 = x2 + 1;
        final int z2 = (z > 0.0) ? MathHelper.func_76128_c(z) : (MathHelper.func_76128_c(z) - 1);
        final int z3 = z2 + 1;
        final double xs = this.deriv1(x - x2);
        final double zs = this.deriv1(z - z2);
        final double tl = this.gradientNoise(x, z, x2, z2, seed);
        final double tr = this.gradientNoise(x, z, x3, z2, seed);
        final double top = this.lerp(tl, tr, xs);
        final double bl = this.gradientNoise(x, z, x2, z3, seed);
        final double br = this.gradientNoise(x, z, x3, z3, seed);
        final double bot = this.lerp(bl, br, xs);
        return this.lerp(top, bot, zs);
    }
    
    private double gradientNoise(final double fx, final double fz, final int ix, final int iz, final long seed) {
        final Vec3 grad = this.getGradient(ix, iz, seed);
        final Vec3 point = Vec3.func_72443_a(fx - ix, fz - iz, 0.0);
        return grad.func_72430_b(point) * 2.12;
    }
    
    private Vec3 getGradient(final int x, final int z, final long seed) {
        long vectorindex = AlfheimNoise.XSEED * x + AlfheimNoise.ZSEED * z + AlfheimNoise.NSEED * seed & -1L;
        vectorindex ^= vectorindex >> AlfheimNoise.NSHIFT;
        vectorindex &= 0xFFL;
        final int vi = (int)vectorindex;
        return Vec3.func_72443_a(this.randomSource[vi * 2], this.randomSource[vi * 2 + 1], 0.0);
    }
    
    private Vec3 calcDerivative(final double x, final double z, final long seed) {
        final int ix = MathHelper.func_76128_c(x);
        final int iz = MathHelper.func_76128_c(z);
        final double fx = x - ix;
        final double fz = z - iz;
        final double wx = this.deriv1(fx);
        final double wz = this.deriv1(fz);
        final double dwx = this.deriv2(fx);
        final double dwz = this.deriv2(fz);
        final double dwpx = this.deriv3(fx);
        final double dwpz = this.deriv3(fz);
        final Vec3 vtl = this.getGradient(ix, iz, seed);
        final Vec3 vtr = this.getGradient(ix + 1, iz, seed);
        final Vec3 vbl = this.getGradient(ix, iz + 1, seed);
        final Vec3 vbr = this.getGradient(ix + 1, iz + 1, seed);
        final double tl = vtl.field_72450_a * (x - ix) + vtl.field_72448_b * (z - iz);
        final double tr = vtr.field_72450_a * (x - (ix + 1)) + vtr.field_72448_b * (z - iz);
        final double bl = vbl.field_72450_a * (x - ix) + vbl.field_72448_b * (z - (iz + 1));
        final double br = vbr.field_72450_a * (x - (ix + 1)) + vbr.field_72448_b * (z - (iz + 1));
        final double top = this.lerp(tl, tr, wx);
        final double bot = this.lerp(bl, br, wx);
        final double n = this.lerp(top, bot, wz);
        final double dx = vtl.field_72450_a + (vbl.field_72450_a - vtl.field_72450_a) * wz + ((vtr.field_72448_b - vtl.field_72448_b) * fz - vtr.field_72450_a + ((vtl.field_72448_b - vtr.field_72448_b - vbl.field_72448_b + vbr.field_72448_b) * fz + vtr.field_72450_a + vbl.field_72448_b - vbr.field_72450_a - vbr.field_72448_b) * wz) * dwx + (vtr.field_72450_a - vtl.field_72450_a + (vtl.field_72450_a - vtr.field_72450_a - vbl.field_72450_a + vbr.field_72450_a) * wz) * dwpx;
        final double dz = vtl.field_72448_b + (vtr.field_72448_b - vtl.field_72448_b) * wx + ((vbl.field_72450_a - vtl.field_72450_a) * fx - vbl.field_72448_b + ((vtl.field_72450_a - vtr.field_72450_a - vbl.field_72450_a + vbr.field_72450_a) * fx + vtr.field_72450_a + vbl.field_72448_b - vbr.field_72450_a - vbr.field_72448_b) * wx) * dwz + (vbl.field_72448_b - vtl.field_72448_b + (vtl.field_72448_b - vtr.field_72448_b - vbl.field_72448_b + vbr.field_72448_b) * wx) * dwpz;
        return Vec3.func_72443_a(n * 2.0, dx * 2.0, dz * 2.0);
    }
    
    private double getNoiseOctaves(final double x, final double z, final int octaves, final double period, final double lacunarity, final double gain, final long seed) {
        double sum = 0.0;
        double freq = (period == 0.0) ? 1.0 : (1.0 / period);
        double amp = 1.0;
        for (int i = 0; i < octaves; ++i) {
            final double n = this.getNoise(x, z, period * i, seed + i);
            sum += n * amp;
            freq *= lacunarity;
            amp *= gain;
        }
        return sum;
    }
    
    private double getJordanRaw(final double x, final double z, final int octaves, final double period, final double lacunarity, final double gain0, final double gain, final double warp0, final double warp, final double damp0, final double damp, final double dampscale, final long seed) {
        double amp = gain0;
        double freq = ((period == 0.0) ? 1.0 : (1.0 / period)) * lacunarity;
        double damped_amp = amp * gain;
        Vec3 n = this.calcDerivative(x * freq, z * freq, seed);
        Vec3 n2 = Vec3.func_72443_a(n.field_72450_a * n.field_72450_a, n.field_72448_b * n.field_72450_a, n.field_72449_c * n.field_72450_a);
        double sum = n2.field_72450_a;
        double dswx = n2.field_72448_b * warp0;
        double dswz = n2.field_72449_c * warp0;
        double dsdx = n2.field_72448_b * damp0;
        double dsdz = n2.field_72449_c * damp0;
        for (int i = 1; i < octaves; ++i) {
            n = this.calcDerivative(x * freq + dswx, z * freq + dswz, seed + i);
            n2 = Vec3.func_72443_a(n.field_72450_a * n.field_72450_a, n.field_72448_b * n.field_72450_a, n.field_72449_c * n.field_72450_a);
            sum += damped_amp * (n2.field_72450_a * 1.5 + 0.1);
            dswx += n2.field_72448_b * warp;
            dswz += n2.field_72449_c * warp;
            dsdx += n2.field_72448_b * damp;
            dsdz += n2.field_72449_c * damp;
            freq *= lacunarity;
            amp *= gain;
            final double dsddot = dsdx * dsdx + dsdz * dsdz;
            damped_amp = amp * (1.0 - dampscale / (1.0 + dsddot));
        }
        return sum;
    }
    
    public double getJordan(double x, double z, final int octaves, final double period, final double lacunarity, final double gain0, final double gain, final double warp0, final double warp, final double damp0, final double damp, final double dampscale, final int distortionoctaves, final double distortionscale, final double distortion, final double distortiongain, final long seed) {
        x += this.getNoiseOctaves(x, z, distortionoctaves, period * distortionscale, lacunarity, distortiongain, seed + 1L) * distortion * period * distortionscale;
        z += this.getNoiseOctaves(x, z, distortionoctaves, period * distortionscale, lacunarity, distortiongain, seed + 2L) * distortion * period * distortionscale;
        return this.getJordanRaw(x, z, octaves, period, lacunarity, gain0, gain, warp0, warp, damp0, damp, dampscale, seed);
    }
    
    public double getJordanDefault(final double x, final double z) {
        return this.getJordan(x, z, 4, 750.0, 4.0, 0.9, 0.7, 0.8, 0.6, 1.0, 0.8, 1.0, 1, 0.001, 0.35, 0.5, this.seed);
    }
    
    private double deriv1(final double n) {
        return n * n * n * (n * (n * 6.0 - 15.0) + 10.0);
    }
    
    private double deriv2(final double n) {
        return n * n * (n * (n * 30.0 - 60.0) + 30.0);
    }
    
    private double deriv3(final double n) {
        return n * n * n * (n * (n * 36.0 - 75.0) + 40.0);
    }
    
    private double lerp(final double a, final double b, final double n) {
        return a * (1.0 - n) + b * n;
    }
    
    static {
        AlfheimNoise.XSEED = 1619;
        AlfheimNoise.ZSEED = 31337;
        AlfheimNoise.NSEED = 1013;
        AlfheimNoise.NSHIFT = 8;
    }
}

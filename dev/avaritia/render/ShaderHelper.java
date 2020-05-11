// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.render;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import fox.spiteful.avaritia.Lumberjack;
import org.apache.logging.log4j.Level;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.ARBShaderObjects;

public final class ShaderHelper
{
    private static final int VERT = 35633;
    private static final int FRAG = 35632;
    private static final String PREFIX = "/assets/avaritia/shader/";
    public static int cosmicShader;
    
    public static void initShaders() {
        if (!useShaders()) {
            return;
        }
        ShaderHelper.cosmicShader = createProgram("cosmic.vert", "cosmic.frag");
    }
    
    public static void useShader(final int shader, final ShaderCallback callback) {
        if (!useShaders()) {
            return;
        }
        ARBShaderObjects.glUseProgramObjectARB(shader);
        if (shader != 0) {
            final int time = ARBShaderObjects.glGetUniformLocationARB(shader, (CharSequence)"time");
            final Minecraft mc = Minecraft.func_71410_x();
            if (mc.field_71439_g != null && mc.field_71439_g.field_70170_p != null) {
                ARBShaderObjects.glUniform1iARB(time, (int)(mc.field_71439_g.field_70170_p.func_72820_D() % 2147483647L));
            }
            if (callback != null) {
                callback.call(shader);
            }
        }
    }
    
    public static void useShader(final int shader) {
        useShader(shader, null);
    }
    
    public static void releaseShader() {
        useShader(0);
    }
    
    public static boolean useShaders() {
        return OpenGlHelper.field_148824_g;
    }
    
    private static int createProgram(final String vert, final String frag) {
        int vertId = 0;
        int fragId = 0;
        int program = 0;
        if (vert != null) {
            vertId = createShader("/assets/avaritia/shader/" + vert, 35633);
        }
        if (frag != null) {
            fragId = createShader("/assets/avaritia/shader/" + frag, 35632);
        }
        program = ARBShaderObjects.glCreateProgramObjectARB();
        if (program == 0) {
            return 0;
        }
        if (vert != null) {
            ARBShaderObjects.glAttachObjectARB(program, vertId);
        }
        if (frag != null) {
            ARBShaderObjects.glAttachObjectARB(program, fragId);
        }
        ARBShaderObjects.glLinkProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, 35714) == 0) {
            Lumberjack.log(Level.ERROR, getLogInfo(program));
            return 0;
        }
        ARBShaderObjects.glValidateProgramARB(program);
        if (ARBShaderObjects.glGetObjectParameteriARB(program, 35715) == 0) {
            Lumberjack.log(Level.ERROR, getLogInfo(program));
            return 0;
        }
        return program;
    }
    
    private static int createShader(final String filename, final int shaderType) {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
            if (shader == 0) {
                return 0;
            }
            ARBShaderObjects.glShaderSourceARB(shader, (CharSequence)readFileAsString(filename));
            ARBShaderObjects.glCompileShaderARB(shader);
            if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
                throw new RuntimeException("Error creating shader \"" + filename + "\": " + getLogInfo(shader));
            }
            return shader;
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(shader);
            e.printStackTrace();
            return -1;
        }
    }
    
    private static String getLogInfo(final int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, 35716));
    }
    
    private static String readFileAsString(final String filename) throws Exception {
        final StringBuilder source = new StringBuilder();
        final InputStream in = ShaderHelper.class.getResourceAsStream(filename);
        Exception exception = null;
        if (in == null) {
            return "";
        }
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            Exception innerExc = null;
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    source.append(line).append('\n');
                }
            }
            catch (Exception exc) {
                exception = exc;
                try {
                    reader.close();
                }
                catch (Exception exc) {
                    if (innerExc == null) {
                        innerExc = exc;
                    }
                    else {
                        exc.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    reader.close();
                }
                catch (Exception exc2) {
                    if (innerExc == null) {
                        innerExc = exc2;
                    }
                    else {
                        exc2.printStackTrace();
                    }
                }
            }
            if (innerExc != null) {
                throw innerExc;
            }
        }
        catch (Exception exc3) {
            exception = exc3;
        }
        finally {
            try {
                in.close();
            }
            catch (Exception exc4) {
                if (exception == null) {
                    exception = exc4;
                }
                else {
                    exc4.printStackTrace();
                }
            }
            if (exception != null) {
                throw exception;
            }
        }
        return source.toString();
    }
    
    static {
        ShaderHelper.cosmicShader = 0;
    }
}

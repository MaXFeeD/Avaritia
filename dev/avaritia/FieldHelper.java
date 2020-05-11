// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import java.lang.reflect.Method;
import org.apache.logging.log4j.Level;
import java.lang.reflect.Field;

public class FieldHelper
{
    public static <T> T get(final Field field, final Object instance) {
        try {
            return (T)field.get(instance);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
            return null;
        }
    }
    
    public static <T> void set(final Field field, final Object instance, final T value) {
        try {
            field.set(instance, value);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
        }
    }
    
    public static <T> T invoke(final Method method, final Object instance, final Object... params) {
        try {
            return (T)method.invoke(instance, params);
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e);
            return null;
        }
    }
}

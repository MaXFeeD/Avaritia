// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class Lumberjack
{
    public static final Logger logger;
    
    public static void log(final Level level, final Throwable e, final Object message) {
        log(level, message);
        e.printStackTrace();
    }
    
    public static void log(final Level level, final Object message) {
        Lumberjack.logger.log(level, message);
    }
    
    public static void info(final Object message) {
        log(Level.INFO, message);
    }
    
    static {
        logger = LogManager.getLogger("Avaritia");
    }
}

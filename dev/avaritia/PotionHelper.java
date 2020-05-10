// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import java.lang.reflect.Field;
import org.apache.logging.log4j.Level;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.potion.Potion;
import java.util.ArrayList;

public class PotionHelper
{
    private static ArrayList<Potion> badPotions;
    
    public static void healthInspection() {
        PotionHelper.badPotions = new ArrayList<Potion>();
        try {
            final Field stupidMojangPrivateVariable = ReflectionHelper.findField((Class)Potion.class, new String[] { "isBadEffect", "field_76418_K" });
            for (final Potion potion : Potion.field_76425_a) {
                if (potion != null && stupidMojangPrivateVariable.getBoolean(potion)) {
                    PotionHelper.badPotions.add(potion);
                }
            }
        }
        catch (Exception e) {
            Lumberjack.log(Level.ERROR, e, "Failure to get potions");
            e.printStackTrace();
        }
    }
    
    public static boolean badPotion(final Potion effect) {
        return PotionHelper.badPotions.contains(effect);
    }
}

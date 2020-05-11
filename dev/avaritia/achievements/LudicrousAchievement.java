// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.achievements;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.stats.Achievement;

public class LudicrousAchievement extends Achievement
{
    public static List<Achievement> achievements;
    
    public LudicrousAchievement(final String name, final int x, final int y, final ItemStack icon, final Achievement parent) {
        super("achievement.avaritia:" + name, "avaritia:" + name, x, y, icon, parent);
        LudicrousAchievement.achievements.add(this);
        this.func_75971_g();
    }
    
    public LudicrousAchievement(final String name, final int x, final int y, final Item icon, final Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }
    
    public LudicrousAchievement(final String name, final int x, final int y, final Block icon, final Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }
    
    static {
        LudicrousAchievement.achievements = new ArrayList<Achievement>();
    }
}

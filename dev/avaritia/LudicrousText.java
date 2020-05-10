// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class LudicrousText
{
    private static final EnumChatFormatting[] fabulousness;
    private static final EnumChatFormatting[] sanic;
    
    public static String makeFabulous(final String input) {
        return ludicrousFormatting(input, LudicrousText.fabulousness, 80.0, 1, 1);
    }
    
    public static String makeSANIC(final String input) {
        return ludicrousFormatting(input, LudicrousText.sanic, 50.0, 2, 1);
    }
    
    public static String ludicrousFormatting(final String input, final EnumChatFormatting[] colours, double delay, final int step, final int posstep) {
        final StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0.0) {
            delay = 0.001;
        }
        final int offset = (int)Math.floor(Minecraft.func_71386_F() / delay) % colours.length;
        for (int i = 0; i < input.length(); ++i) {
            final char c = input.charAt(i);
            final int col = (i * posstep + colours.length - offset) % colours.length;
            sb.append(colours[col].toString());
            sb.append(c);
        }
        return sb.toString();
    }
    
    static {
        fabulousness = new EnumChatFormatting[] { EnumChatFormatting.RED, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE };
        sanic = new EnumChatFormatting[] { EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.RED, EnumChatFormatting.WHITE, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY };
    }
}

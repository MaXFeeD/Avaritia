// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.botania.alfheim;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class Alfheim
{
    public static void vacationTime() {
        AlfheimBlocks.alfheimify();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            final AlfheimEvents alfevents = new AlfheimEvents();
            FMLCommonHandler.instance().bus().register((Object)alfevents);
            MinecraftForge.EVENT_BUS.register((Object)alfevents);
        }
    }
    
    public static void packYourBags() {
        final int dim = WorldProviderAlfheim.dimensionID;
        DimensionManager.registerProviderType(dim, (Class)WorldProviderAlfheim.class, false);
        DimensionManager.registerDimension(dim, dim);
        MapGenStructureIO.func_143034_b((Class)MapGenCity.Start.class, "AlfCity");
        ComponentCityParts.registerParts();
    }
}

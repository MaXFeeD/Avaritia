// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import forestry.api.apiculture.IAlleleBeeSpecies;
import net.minecraft.util.StatCollector;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;

public class Allele implements IAllele
{
    public static Allele grindySpeed;
    public static Allele grindyLife;
    private String name;
    private String id;
    private boolean dom;
    
    public static void prepareGenes() {
        Allele.grindySpeed = new AlleleFloat("speedNerfed", true, 0.1f);
        Allele.grindyLife = new AlleleInteger("lifespanArtificial", false, 75);
    }
    
    public Allele(final String moniker, final boolean dominant) {
        this.name = "avaritia.allele." + moniker;
        this.id = "avaritia." + moniker;
        this.dom = dominant;
        AlleleManager.alleleRegistry.registerAllele((IAllele)this);
    }
    
    public String getUID() {
        return this.id;
    }
    
    public String getName() {
        return StatCollector.func_74838_a(this.name);
    }
    
    public String getUnlocalizedName() {
        return this.name;
    }
    
    public boolean isDominant() {
        return this.dom;
    }
    
    public static IAlleleBeeSpecies getBaseSpecies(final String name) {
        return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele("forestry.species" + name);
    }
    
    public static IAlleleBeeSpecies getExtraSpecies(final String name) {
        return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele("extrabees.species." + name.toLowerCase());
    }
    
    public static IAlleleBeeSpecies getMagicSpecies(final String name) {
        return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele("magicbees.species" + name);
    }
    
    public static IAllele getBaseAllele(final String name) {
        return AlleleManager.alleleRegistry.getAllele("forestry." + name);
    }
}

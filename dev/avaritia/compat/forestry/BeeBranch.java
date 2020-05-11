// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import net.minecraft.util.StatCollector;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import java.util.ArrayList;
import forestry.api.genetics.IClassification;

public enum BeeBranch implements IClassification
{
    BALANCED("balanced", "Molestus"), 
    INFINITE("infinite", "Infinitus");
    
    private String name;
    private String latin;
    private ArrayList<IAlleleSpecies> species;
    private IClassification parent;
    private final IClassification.EnumClassLevel level;
    
    private BeeBranch(final String nombre, final String science) {
        this.species = new ArrayList<IAlleleSpecies>();
        this.level = IClassification.EnumClassLevel.GENUS;
        this.name = nombre;
        this.latin = science;
        this.parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
        AlleleManager.alleleRegistry.registerClassification((IClassification)this);
    }
    
    public IClassification.EnumClassLevel getLevel() {
        return this.level;
    }
    
    public String getUID() {
        return "classification." + this.name;
    }
    
    public String getName() {
        return StatCollector.func_74838_a("classification." + this.name);
    }
    
    public String getScientific() {
        return this.latin;
    }
    
    public String getDescription() {
        return StatCollector.func_74838_a("classification." + this.name + ".desc");
    }
    
    public IClassification[] getMemberGroups() {
        return null;
    }
    
    public void addMemberGroup(final IClassification group) {
    }
    
    public IAlleleSpecies[] getMemberSpecies() {
        return this.species.toArray(new IAlleleSpecies[this.species.size()]);
    }
    
    public void addMemberSpecies(final IAlleleSpecies species) {
        if (!this.species.contains(species)) {
            this.species.add(species);
        }
    }
    
    public IClassification getParent() {
        return this.parent;
    }
    
    public void setParent(final IClassification parent) {
        this.parent = parent;
    }
}

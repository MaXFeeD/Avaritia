// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import forestry.api.genetics.ISpeciesRoot;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.genetics.IGenome;
import forestry.api.apiculture.IBeeHousing;
import java.util.ArrayList;
import java.util.Collection;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.IMutation;
import forestry.api.apiculture.BeeManager;
import forestry.api.genetics.IAllele;
import forestry.api.apiculture.IBeeMutation;

public class ExpensiveMutation implements IBeeMutation
{
    private IAllele mom;
    private IAllele dad;
    private IAllele[] template;
    private boolean secret;
    private float baseChance;
    
    public ExpensiveMutation(final IAllele first, final IAllele second, final IAllele[] result, final float chance) {
        this.secret = false;
        this.mom = first;
        this.dad = second;
        this.template = result;
        this.baseChance = chance;
        BeeManager.beeRoot.registerMutation((IMutation)this);
    }
    
    public static void mutate() {
        IAllele first;
        if (Ranger.extra) {
            first = (IAllele)Allele.getExtraSpecies("Relic");
        }
        else {
            first = (IAllele)Allele.getBaseSpecies("Austere");
        }
        final IAllele second = (IAllele)Allele.getBaseSpecies("Hermitic");
        new ExpensiveMutation(first, second, Genomes.getBalanced(), 0.8f);
        if (Ranger.magic) {
            first = (IAllele)Allele.getMagicSpecies("Draconic");
        }
        else {
            first = (IAllele)Allele.getBaseSpecies("Heroic");
        }
        new ExpensiveMutation(first, (IAllele)GreedyBeeSpecies.ANNOYING, Genomes.getTedious(), 0.7f);
        if (Ranger.extra) {
            first = (IAllele)Allele.getExtraSpecies("Virulent");
        }
        else if (Ranger.magic) {
            first = (IAllele)Allele.getMagicSpecies("Withering");
        }
        else {
            first = (IAllele)Allele.getBaseSpecies("Demonic");
        }
        new ExpensiveMutation(first, (IAllele)GreedyBeeSpecies.TEDIOUS, Genomes.getInsufferable(), 0.6f);
        new ExpensiveMutation((IAllele)Allele.getBaseSpecies("Edenic"), (IAllele)GreedyBeeSpecies.INSUFFERABLE, Genomes.getInfinite(), 0.4f);
        if (Ranger.magic) {
            first = (IAllele)Allele.getMagicSpecies("Doctoral");
        }
        else if (Ranger.extra) {
            first = (IAllele)Allele.getExtraSpecies("Diamond");
        }
        else {
            first = (IAllele)Allele.getBaseSpecies("Phantasmal");
        }
        new ExpensiveMutation(first, (IAllele)GreedyBeeSpecies.TRIPPY, Genomes.getCosmic(), 0.8f);
        if (Ranger.magic) {
            first = (IAllele)Allele.getMagicSpecies("Firey");
        }
        else if (Ranger.extra) {
            first = (IAllele)Allele.getExtraSpecies("Volcanic");
        }
        else {
            first = (IAllele)Allele.getBaseSpecies("Industrious");
        }
    }
    
    public IBeeRoot getRoot() {
        return BeeManager.beeRoot;
    }
    
    public IAllele getAllele0() {
        return this.mom;
    }
    
    public IAllele getAllele1() {
        return this.dad;
    }
    
    public IAllele[] getTemplate() {
        return this.template;
    }
    
    public float getBaseChance() {
        return this.baseChance;
    }
    
    public boolean isSecret() {
        return this.secret;
    }
    
    public boolean isPartner(final IAllele allele) {
        return this.mom.getUID().equals(allele.getUID()) || this.dad.getUID().equals(allele.getUID());
    }
    
    public IAllele getPartner(final IAllele allele) {
        if (allele.getUID().equals(this.mom.getUID())) {
            return this.dad;
        }
        return this.mom;
    }
    
    public Collection<String> getSpecialConditions() {
        return new ArrayList<String>();
    }
    
    public float getChance(final IBeeHousing housing, final IAllele allele0, final IAllele allele1, final IGenome genome0, final IGenome genome1) {
        float finalChance = 0.0f;
        final float chance = this.baseChance * 1.0f;
        if (this.isPartner(allele0) && this.isPartner(allele1)) {
            finalChance = (float)Math.round(chance * housing.getMutationModifier((IBeeGenome)genome0, (IBeeGenome)genome1, chance) * BeeManager.beeRoot.getBeekeepingMode(housing.getWorld()).getMutationModifier((IBeeGenome)genome0, (IBeeGenome)genome1, chance));
        }
        return finalChance;
    }
    
    public float getChance(final IBeeHousing housing, final IAlleleBeeSpecies allele0, final IAlleleBeeSpecies allele1, final IBeeGenome genome0, final IBeeGenome genome1) {
        float finalChance = 0.0f;
        final float chance = this.baseChance * 1.0f;
        if (this.isPartner((IAllele)allele0) && this.isPartner((IAllele)allele1)) {
            finalChance = (float)Math.round(chance * housing.getMutationModifier(genome0, genome1, chance) * BeeManager.beeRoot.getBeekeepingMode(housing.getWorld()).getMutationModifier(genome0, genome1, chance));
        }
        return finalChance;
    }
}

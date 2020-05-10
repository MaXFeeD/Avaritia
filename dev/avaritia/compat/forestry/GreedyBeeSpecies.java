// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.forestry;

import forestry.api.genetics.ISpeciesRoot;
import java.util.Locale;
import net.minecraft.client.renderer.texture.IIconRegister;
import forestry.api.apiculture.EnumBeeType;
import java.awt.Color;
import java.util.Collection;
import forestry.api.genetics.IIndividual;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import java.util.Map;
import java.util.Iterator;
import forestry.api.genetics.IMutation;
import forestry.api.genetics.IChromosomeType;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IBeeRoot;
import java.util.ArrayList;
import net.minecraft.util.StatCollector;
import fox.spiteful.avaritia.items.LudicrousItems;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.AlleleManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.ItemStack;
import java.util.HashMap;
import forestry.api.genetics.IAllele;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IClassification;
import forestry.api.core.IIconProvider;
import forestry.api.apiculture.IAlleleBeeSpecies;

public enum GreedyBeeSpecies implements IAlleleBeeSpecies, IIconProvider
{
    ANNOYING("annoying", "incommodus", (IClassification)BeeBranch.BALANCED, "SpitefulFox", 6696193, 7829367, false), 
    TEDIOUS("tedious", "longus", (IClassification)BeeBranch.BALANCED, "SpitefulFox", 6696193, 6696193, false), 
    INSUFFERABLE("insufferable", "intolerabilis", (IClassification)BeeBranch.BALANCED, "SpitefulFox", 6696193, 16763632, true), 
    TRIPPY("trippy", "laetus", (IClassification)BeeBranch.INFINITE, "SpitefulFox", 16777215, -1, false), 
    COSMIC("cosmic", "caelus", (IClassification)BeeBranch.INFINITE, "SpitefulFox", 16777215, -1, true), 
    NEUTRONIUM("neutronium", "sidereus", (IClassification)BeeBranch.INFINITE, "SpitefulFox", 11137791, 2894892, true), 
    INFINITE("infinite", "infinitus", (IClassification)BeeBranch.INFINITE, "SpitefulFox", -1, -1, true, false, true);
    
    private String name;
    private String binomial;
    private IClassification branch;
    private boolean dominant;
    private String authority;
    private EnumTemperature temperature;
    private EnumHumidity humidity;
    private boolean hasEffect;
    private boolean isSecret;
    private boolean isCounted;
    private boolean fancy;
    private boolean isNocturnal;
    private IAllele[] genomeTemplate;
    private HashMap<ItemStack, Float> products;
    private HashMap<ItemStack, Float> specialties;
    private HashMap<ItemStack, Integer> legacyProducts;
    private HashMap<ItemStack, Integer> legacySpecialties;
    private int primaryColor;
    private int secondaryColor;
    @SideOnly(Side.CLIENT)
    private IIcon[][] icons;
    
    private GreedyBeeSpecies(final String nombre, final String genus, final IClassification bran, final String author, final int mainColor, final int otherColor, final boolean dom, final boolean secret, final boolean shiny) {
        this.temperature = EnumTemperature.NORMAL;
        this.humidity = EnumHumidity.NORMAL;
        this.isCounted = true;
        this.isNocturnal = false;
        this.products = new HashMap<ItemStack, Float>();
        this.specialties = new HashMap<ItemStack, Float>();
        this.legacyProducts = new HashMap<ItemStack, Integer>();
        this.legacySpecialties = new HashMap<ItemStack, Integer>();
        this.name = nombre;
        this.binomial = genus;
        this.branch = bran;
        this.primaryColor = mainColor;
        this.secondaryColor = otherColor;
        this.authority = author;
        this.dominant = dom;
        AlleleManager.alleleRegistry.registerAllele((IAllele)this);
        this.branch.addMemberSpecies((IAlleleSpecies)this);
        this.isSecret = secret;
        this.fancy = shiny;
    }
    
    private GreedyBeeSpecies(final String nombre, final String genus, final IClassification bran, final String author, final int mainColor, final int otherColor, final boolean dom) {
        this(nombre, genus, bran, author, mainColor, otherColor, dom, false, false);
    }
    
    public static void buzz() {
        GreedyBeeSpecies.ANNOYING.registerGenomeTemplate(Genomes.getBalanced());
        GreedyBeeSpecies.TEDIOUS.registerGenomeTemplate(Genomes.getTedious());
        GreedyBeeSpecies.INSUFFERABLE.registerGenomeTemplate(Genomes.getInsufferable());
        GreedyBeeSpecies.TRIPPY.registerGenomeTemplate(Genomes.getInfinite());
        GreedyBeeSpecies.COSMIC.registerGenomeTemplate(Genomes.getCosmic());
        GreedyBeeSpecies.NEUTRONIUM.registerGenomeTemplate(Genomes.getNeutronium());
        GreedyBeeSpecies.INFINITE.registerGenomeTemplate(Genomes.getInfiniteBee());
        GreedyBeeSpecies.ANNOYING.addProduct(new ItemStack(LudicrousItems.combs, 1, 0), 0.4f);
        GreedyBeeSpecies.TEDIOUS.addProduct(new ItemStack(LudicrousItems.combs, 1, 0), 0.2f);
        GreedyBeeSpecies.INSUFFERABLE.addProduct(new ItemStack(LudicrousItems.combs, 1, 0), 0.1f);
        GreedyBeeSpecies.TRIPPY.addProduct(new ItemStack(LudicrousItems.combs, 1, 1), 0.15f);
        GreedyBeeSpecies.COSMIC.addProduct(new ItemStack(LudicrousItems.combs, 1, 1), 0.15f);
        GreedyBeeSpecies.COSMIC.addSpecialty(new ItemStack(LudicrousItems.beesource, 1, 0), 0.1f);
        GreedyBeeSpecies.NEUTRONIUM.addProduct(new ItemStack(LudicrousItems.combs, 1, 1), 0.15f);
        GreedyBeeSpecies.NEUTRONIUM.addSpecialty(new ItemStack(LudicrousItems.resource, 1, 2), 0.8f);
        GreedyBeeSpecies.INFINITE.addProduct(new ItemStack(LudicrousItems.combs, 1, 1), 0.15f);
    }
    
    public String getUID() {
        return "avaritia." + this.name;
    }
    
    public String getName() {
        return StatCollector.func_74838_a("avaritia.bee." + this.name);
    }
    
    public String getUnlocalizedName() {
        return "avaritia.bee." + this.name;
    }
    
    public String getBinomial() {
        return this.binomial;
    }
    
    public String getAuthority() {
        return this.authority;
    }
    
    public String getDescription() {
        return StatCollector.func_74838_a("avaritia.bee." + this.name + ".desc");
    }
    
    public IClassification getBranch() {
        return this.branch;
    }
    
    public boolean isDominant() {
        return this.dominant;
    }
    
    public boolean isCounted() {
        return this.isCounted;
    }
    
    public boolean isSecret() {
        return this.isSecret;
    }
    
    public boolean hasEffect() {
        return this.fancy;
    }
    
    public EnumTemperature getTemperature() {
        return this.temperature;
    }
    
    public EnumHumidity getHumidity() {
        return this.humidity;
    }
    
    public boolean isNocturnal() {
        return this.isNocturnal;
    }
    
    public IAllele[] getGenome() {
        return this.genomeTemplate;
    }
    
    public void setGenome(final IAllele[] genome) {
        this.genomeTemplate = genome;
    }
    
    public int getComplexity() {
        return 1 + this.getGeneticAdvancement((IAllele)this, new ArrayList<IAllele>());
    }
    
    public HashMap<ItemStack, Float> getProductChances() {
        return this.products;
    }
    
    public HashMap<ItemStack, Float> getSpecialtyChances() {
        return this.specialties;
    }
    
    public HashMap<ItemStack, Integer> getProducts() {
        return this.legacyProducts;
    }
    
    public HashMap<ItemStack, Integer> getSpecialty() {
        return this.legacySpecialties;
    }
    
    public IBeeRoot getRoot() {
        return BeeManager.beeRoot;
    }
    
    public boolean isJubilant(final IBeeGenome genome, final IBeeHousing housing) {
        return true;
    }
    
    public void addProduct(final ItemStack produce, final float percentChance) {
        this.products.put(produce, percentChance);
        this.legacyProducts.put(produce, (int)(percentChance * 100.0f));
    }
    
    public void addSpecialty(final ItemStack produce, final float percentChance) {
        this.specialties.put(produce, percentChance);
        this.legacySpecialties.put(produce, (int)(percentChance * 100.0f));
    }
    
    public void registerGenomeTemplate(final IAllele[] genome) {
        this.genomeTemplate = genome;
        BeeManager.beeRoot.registerTemplate(this.getUID(), genome);
    }
    
    private int getGeneticAdvancement(final IAllele species, final ArrayList<IAllele> exclude) {
        final int own = 1;
        int highest = 0;
        exclude.add(species);
        for (final IMutation mutation : this.getRoot().getPaths(species, (IChromosomeType)EnumBeeChromosome.SPECIES)) {
            if (!exclude.contains(mutation.getAllele0())) {
                final int otherAdvance = this.getGeneticAdvancement(mutation.getAllele0(), exclude);
                if (otherAdvance > highest) {
                    highest = otherAdvance;
                }
            }
            if (!exclude.contains(mutation.getAllele1())) {
                final int otherAdvance = this.getGeneticAdvancement(mutation.getAllele1(), exclude);
                if (otherAdvance <= highest) {
                    continue;
                }
                highest = otherAdvance;
            }
        }
        return own + ((highest < 0) ? 0 : highest);
    }
    
    public float getResearchSuitability(final ItemStack itemStack) {
        if (itemStack == null) {
            return 0.0f;
        }
        for (final ItemStack product : this.products.keySet()) {
            if (itemStack.func_77969_a(product)) {
                return 1.0f;
            }
        }
        for (final ItemStack specialty : this.specialties.keySet()) {
            if (specialty.func_77969_a(itemStack)) {
                return 1.0f;
            }
        }
        if (itemStack.func_77973_b() == Ranger.honey) {
            return 0.5f;
        }
        if (itemStack.func_77973_b() == Ranger.honeydew) {
            return 0.7f;
        }
        if (itemStack.func_77973_b() == Ranger.comb) {
            return 0.4f;
        }
        if (this.getRoot().isMember(itemStack)) {
            return 1.0f;
        }
        for (final Map.Entry<ItemStack, Float> catalyst : BeeManager.beeRoot.getResearchCatalysts().entrySet()) {
            if (OreDictionary.itemMatches(itemStack, (ItemStack)catalyst.getKey(), false)) {
                return catalyst.getValue();
            }
        }
        return 0.0f;
    }
    
    public ItemStack[] getResearchBounty(final World world, final GameProfile researcher, final IIndividual individual, final int bountyLevel) {
        System.out.println("Bounty level: " + bountyLevel);
        final ArrayList<ItemStack> bounty = new ArrayList<ItemStack>();
        if (world.field_73012_v.nextFloat() < 10.0f / bountyLevel) {
            final Collection<? extends IMutation> resultantMutations = (Collection<? extends IMutation>)this.getRoot().getCombinations((IAllele)this);
            if (resultantMutations.size() > 0) {
                final IMutation[] candidates = resultantMutations.toArray(new IMutation[resultantMutations.size()]);
                bounty.add(AlleleManager.alleleRegistry.getMutationNoteStack(researcher, candidates[world.field_73012_v.nextInt(candidates.length)]));
            }
        }
        for (final ItemStack product : this.products.keySet()) {
            final ItemStack copy = product.func_77946_l();
            copy.field_77994_a = 1 + world.field_73012_v.nextInt(bountyLevel / 2);
            bounty.add(copy);
        }
        for (final ItemStack specialty : this.specialties.keySet()) {
            final ItemStack copy = specialty.func_77946_l();
            copy.field_77994_a = world.field_73012_v.nextInt(bountyLevel / 3);
            if (copy.field_77994_a > 0) {
                bounty.add(copy);
            }
        }
        return bounty.toArray(new ItemStack[bounty.size()]);
    }
    
    public String getEntityTexture() {
        return "/gfx/forestry/entities/bees/honeyBee.png";
    }
    
    public int getIconColour(final int renderPass) {
        int value = 16777215;
        if (renderPass == 0) {
            if (this.primaryColor == -1) {
                final int hue = (int)(System.currentTimeMillis() >> 2) % 360;
                value = Color.getHSBColor(hue / 360.0f, 0.75f, 0.8f).getRGB();
            }
            else {
                value = this.primaryColor;
            }
        }
        else if (renderPass == 1) {
            if (this.secondaryColor == -1) {
                int hue = (int)(System.currentTimeMillis() >> 3) % 360;
                hue += 60;
                hue %= 360;
                value = Color.getHSBColor(hue / 360.0f, 0.5f, 0.6f).getRGB();
            }
            else {
                value = this.secondaryColor;
            }
        }
        return value;
    }
    
    @SideOnly(Side.CLIENT)
    public IIconProvider getIconProvider() {
        return (IIconProvider)this;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final EnumBeeType type, final int renderPass) {
        return this.icons[type.ordinal()][Math.min(renderPass, 2)];
    }
    
    public void registerIcons(final IIconRegister itemMap) {
        this.icons = new IIcon[EnumBeeType.values().length][3];
        final IIcon body1 = itemMap.func_94245_a("forestry:bees/default/body1");
        for (int i = 0; i < EnumBeeType.values().length; ++i) {
            if (EnumBeeType.values()[i] != EnumBeeType.NONE) {
                this.icons[i][0] = itemMap.func_94245_a("forestry:bees/default/" + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".outline");
                this.icons[i][1] = ((EnumBeeType.values()[i] != EnumBeeType.LARVAE) ? body1 : itemMap.func_94245_a("forestry:bees/default/" + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body"));
                this.icons[i][2] = itemMap.func_94245_a("forestry:bees/default/" + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body2");
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final short texUID) {
        return this.icons[0][0];
    }
}

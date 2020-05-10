// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.items;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.common.util.EnumHelper;
import fox.spiteful.avaritia.entity.EntityImmortalItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import fox.spiteful.avaritia.LudicrousText;
import fox.spiteful.avaritia.Config;
import net.minecraft.util.StatCollector;
import net.minecraft.util.EnumChatFormatting;
import fox.spiteful.avaritia.compat.Compat;
import java.util.List;
import thaumcraft.api.aspects.Aspect;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fox.spiteful.avaritia.render.ModelArmorInfinity;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import java.util.Iterator;
import java.util.Collection;
import fox.spiteful.avaritia.PotionHelper;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.Avaritia;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.Optional;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.item.IPhantomInkable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.api.IGoggles;
import fox.spiteful.avaritia.render.ICosmicRenderItem;
import net.minecraft.item.ItemArmor;

@Optional.InterfaceList({ @Optional.Interface(iface = "thaumcraft.api.IGoggles", modid = "Thaumcraft"), @Optional.Interface(iface = "thaumcraft.api.nodes.IRevealer", modid = "Thaumcraft"), @Optional.Interface(iface = "thaumcraft.api.IVisDiscountGear", modid = "Thaumcraft"), @Optional.Interface(iface = "vazkii.botania.api.item.IPhantomInkable", modid = "Botania"), @Optional.Interface(iface = "vazkii.botania.api.mana.IManaDiscountArmor", modid = "Botania"), @Optional.Interface(iface = "vazkii.botania.api.item.IManaProficiencyArmor", modid = "Botania") })
public class ItemArmorInfinity extends ItemArmor implements ICosmicRenderItem, IGoggles, IRevealer, IVisDiscountGear, IPhantomInkable, IManaDiscountArmor, IManaProficiencyArmor
{
    public static final ItemArmor.ArmorMaterial infinite_armor;
    public IIcon cosmicMask;
    public final int slot;
    
    public ItemArmorInfinity(final int slot) {
        super(ItemArmorInfinity.infinite_armor, 0, slot);
        this.slot = slot;
        this.func_77637_a(Avaritia.tab);
        this.func_77655_b("infinity_armor_" + slot);
        this.func_111206_d("avaritia:infinity_armor_" + slot);
    }
    
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        return "avaritia:textures/models/infinity_armor.png";
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        super.setDamage(stack, 0);
    }
    
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
        if (this.field_77881_a == 0) {
            player.func_70050_g(300);
            player.func_71024_bL().func_75122_a(20, 20.0f);
        }
        else if (this.field_77881_a == 1) {
            final Collection effects = player.func_70651_bq();
            if (effects.size() > 0) {
                final ArrayList<Potion> bad = new ArrayList<Potion>();
                for (final Object effect : effects) {
                    if (effect instanceof PotionEffect) {
                        final PotionEffect potion = (PotionEffect)effect;
                        if (!PotionHelper.badPotion(Potion.field_76425_a[potion.func_76456_a()])) {
                            continue;
                        }
                        bad.add(Potion.field_76425_a[potion.func_76456_a()]);
                    }
                }
                if (bad.size() > 0) {
                    for (final Potion potion2 : bad) {
                        player.func_82170_o(potion2.field_76415_H);
                    }
                }
            }
        }
        else if (this.field_77881_a == 2 && player.func_70027_ad()) {
            player.func_70066_B();
        }
    }
    
    public EnumRarity func_77613_e(final ItemStack stack) {
        return LudicrousItems.cosmic;
    }
    
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(final EntityLivingBase entityLiving, final ItemStack itemstack, final int armorSlot) {
        final ModelArmorInfinity model = (armorSlot == 2) ? ModelArmorInfinity.legModel : ModelArmorInfinity.armorModel;
        model.update(entityLiving, itemstack, armorSlot);
        return model;
    }
    
    public Multimap getAttributeModifiers(final ItemStack stack) {
        final Multimap multimap = super.getAttributeModifiers(stack);
        return multimap;
    }
    
    @Optional.Method(modid = "Thaumcraft")
    public boolean showIngamePopups(final ItemStack itemStack, final EntityLivingBase entityLivingBase) {
        return this.field_77881_a == 0;
    }
    
    @Optional.Method(modid = "Thaumcraft")
    public boolean showNodes(final ItemStack itemStack, final EntityLivingBase entityLivingBase) {
        return this.field_77881_a == 0;
    }
    
    @Optional.Method(modid = "Thaumcraft")
    public int getVisDiscount(final ItemStack itemStack, final EntityPlayer entityPlayer, final Aspect aspect) {
        return 20;
    }
    
    public void func_77624_a(final ItemStack stack, final EntityPlayer player, final List list, final boolean par4) {
        if (Compat.thaumic) {
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
        }
        if (Compat.botan && this.hasPhantomInk(stack)) {
            list.add(StatCollector.func_74838_a("botaniamisc.hasPhantomInk").replaceAll("&", "§"));
        }
        if (this.slot == 3 && Config.fast) {
            list.add("");
            list.add(EnumChatFormatting.BLUE + "+" + EnumChatFormatting.ITALIC + LudicrousText.makeSANIC("SANIC") + EnumChatFormatting.RESET + "" + EnumChatFormatting.BLUE + "% Speed");
        }
        super.func_77624_a(stack, player, list, par4);
    }
    
    public boolean hasPhantomInk(final ItemStack stack) {
        return stack.func_77978_p() != null && stack.func_77978_p().func_74767_n("phantomInk");
    }
    
    public void setPhantomInk(final ItemStack stack, final boolean ink) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.func_77982_d(tag);
        }
        tag.func_74757_a("phantomInk", ink);
    }
    
    @Optional.Method(modid = "Botania")
    public float getDiscount(final ItemStack stack, final int slot, final EntityPlayer player) {
        return 0.25f;
    }
    
    @Optional.Method(modid = "Botania")
    public boolean shouldGiveProficiency(final ItemStack itemStack, final int i, final EntityPlayer player) {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(final IIconRegister ir) {
        super.func_94581_a(ir);
        this.cosmicMask = ir.func_94245_a("avaritia:infinity_armor_" + this.slot + "_mask");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getMaskTexture(final ItemStack stack, final EntityPlayer player) {
        return this.cosmicMask;
    }
    
    @SideOnly(Side.CLIENT)
    public float getMaskMultiplier(final ItemStack stack, final EntityPlayer player) {
        return 1.0f;
    }
    
    public boolean hasCustomEntity(final ItemStack stack) {
        return true;
    }
    
    public Entity createEntity(final World world, final Entity location, final ItemStack itemstack) {
        return (Entity)new EntityImmortalItem(world, location, itemstack);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(final ItemStack par1ItemStack, final int pass) {
        return false;
    }
    
    static {
        infinite_armor = EnumHelper.addArmorMaterial("infinity", 9999, new int[] { 6, 16, 12, 6 }, 1000);
    }
    
    public static class abilityHandler
    {
        public static List<String> playersWithHat;
        public static List<String> playersWithChest;
        public static List<String> playersWithLeg;
        public static List<String> playersWithFoot;
        
        public static boolean playerHasHat(final EntityPlayer player) {
            final ItemStack armour = player.func_82169_q(3);
            return armour != null && armour.func_77973_b() == LudicrousItems.infinity_helm;
        }
        
        public static boolean playerHasChest(final EntityPlayer player) {
            final ItemStack armour = player.func_82169_q(2);
            return armour != null && armour.func_77973_b() == LudicrousItems.infinity_armor;
        }
        
        public static boolean playerHasLeg(final EntityPlayer player) {
            final ItemStack armour = player.func_82169_q(1);
            return armour != null && armour.func_77973_b() == LudicrousItems.infinity_pants;
        }
        
        public static boolean playerHasFoot(final EntityPlayer player) {
            final ItemStack armour = player.func_82169_q(0);
            return armour != null && armour.func_77973_b() == LudicrousItems.infinity_shoes;
        }
        
        public static String playerKey(final EntityPlayer player) {
            return player.func_146103_bH().getName() + ":" + player.field_70170_p.field_72995_K;
        }
        
        @SubscribeEvent
        public void updatePlayerAbilityStatus(final LivingEvent.LivingUpdateEvent event) {
            if (event.entityLiving instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)event.entityLiving;
                final String key = playerKey(player);
                final Boolean hasHat = playerHasHat(player);
                if (abilityHandler.playersWithHat.contains(key)) {
                    if (!hasHat) {
                        abilityHandler.playersWithHat.remove(key);
                    }
                }
                else if (hasHat) {
                    abilityHandler.playersWithHat.add(key);
                }
                final Boolean hasChest = playerHasChest(player);
                if (abilityHandler.playersWithChest.contains(key)) {
                    if (hasChest) {
                        player.field_71075_bZ.field_75101_c = true;
                    }
                    else {
                        if (!player.field_71075_bZ.field_75098_d) {
                            player.field_71075_bZ.field_75101_c = false;
                            player.field_71075_bZ.field_75100_b = false;
                        }
                        abilityHandler.playersWithChest.remove(key);
                    }
                }
                else if (hasChest) {
                    abilityHandler.playersWithChest.add(key);
                }
                final Boolean hasLeg = playerHasLeg(player);
                if (abilityHandler.playersWithLeg.contains(key)) {
                    if (!hasLeg) {
                        abilityHandler.playersWithLeg.remove(key);
                    }
                }
                else if (hasLeg) {
                    abilityHandler.playersWithLeg.add(key);
                }
                final Boolean hasFoot = playerHasFoot(player) && Config.fast;
                if (abilityHandler.playersWithFoot.contains(key)) {
                    if (hasFoot) {
                        final boolean flying = player.field_71075_bZ.field_75100_b;
                        final boolean swimming = player.func_70055_a(Material.field_151586_h) || player.func_70090_H();
                        if (player.field_70122_E || flying || swimming) {
                            final boolean sneaking = player.func_70093_af();
                            final float speed = 0.15f * (flying ? 1.1f : 1.0f) * (sneaking ? 0.1f : 1.0f);
                            if (player.field_70701_bs > 0.0f) {
                                player.func_70060_a(0.0f, 1.0f, speed);
                            }
                            else if (player.field_70701_bs < 0.0f) {
                                player.func_70060_a(0.0f, 1.0f, -speed * 0.3f);
                            }
                            if (player.field_70702_br != 0.0f) {
                                player.func_70060_a(1.0f, 0.0f, speed * 0.5f * Math.signum(player.field_70702_br));
                            }
                        }
                    }
                    else {
                        abilityHandler.playersWithFoot.remove(key);
                    }
                }
                else if (hasFoot) {
                    abilityHandler.playersWithFoot.add(key);
                }
            }
        }
        
        @SubscribeEvent
        public void jumpBoost(final LivingEvent.LivingJumpEvent event) {
            if (event.entityLiving instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)event.entityLiving;
                final String key = playerKey(player);
                if (abilityHandler.playersWithFoot.contains(key)) {
                    final EntityPlayer entityPlayer = player;
                    entityPlayer.field_70181_x += 0.4000000059604645;
                }
            }
        }
        
        static {
            abilityHandler.playersWithHat = new ArrayList<String>();
            abilityHandler.playersWithChest = new ArrayList<String>();
            abilityHandler.playersWithLeg = new ArrayList<String>();
            abilityHandler.playersWithFoot = new ArrayList<String>();
        }
    }
}

// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.compat.ticon;

import net.minecraft.block.Block;
import fox.spiteful.avaritia.items.tools.ToolHelper;
import net.minecraft.init.Blocks;
import net.minecraft.block.material.Material;
import fox.spiteful.avaritia.Config;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import tconstruct.library.tools.AbilityHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.item.ItemStack;
import fox.spiteful.avaritia.LudicrousEvents;
import net.minecraftforge.event.world.BlockEvent;
import tconstruct.library.tools.ToolCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.nbt.NBTTagCompound;
import tconstruct.library.event.ToolCraftEvent;
import java.util.Random;

public class TonkersEvents
{
    private Random randy;
    
    public TonkersEvents() {
        this.randy = new Random();
    }
    
    @SubscribeEvent
    public void craftTool(final ToolCraftEvent.NormalTool event) {
        final NBTTagCompound toolTag = event.toolTag.func_74775_l("InfiTool");
        this.handleInfinityMods(toolTag, event.tool);
    }
    
    private void handleInfinityMods(final NBTTagCompound toolTag, final ToolCore tool) {
        final int plusmod = 5;
        int modifiers = toolTag.func_74762_e("Modifiers");
        if (toolTag.func_74762_e("Head") == 501) {
            modifiers += plusmod;
        }
        if (toolTag.func_74762_e("Handle") == 501) {
            modifiers += plusmod;
        }
        if (toolTag.func_74762_e("Accessory") == 501) {
            modifiers += plusmod;
        }
        if (toolTag.func_74762_e("Extra") == 501) {
            modifiers += plusmod;
        }
        if (tool.getPartAmount() == 2 && toolTag.func_74762_e("Head") == 501) {
            modifiers += plusmod;
        }
        toolTag.func_74768_a("Modifiers", modifiers);
    }
    
    @SubscribeEvent
    public void handleExtraLuck(final BlockEvent.HarvestDropsEvent event) {
        if (event.harvester == null) {
            return;
        }
        if (event.harvester.func_70694_bm() == null) {
            return;
        }
        final ItemStack held = event.harvester.func_70694_bm();
        if (held != null && held.func_77942_o() && held.func_77973_b() instanceof ToolCore) {
            final ToolCore tool = (ToolCore)held.func_77973_b();
            final NBTTagCompound toolTag = held.func_77978_p().func_74775_l("InfiTool");
            if (toolTag.func_74762_e("Head") == 501) {
                int parts = 1;
                if (toolTag.func_74762_e("Handle") == 501) {
                    ++parts;
                }
                if (toolTag.func_74762_e("Accessory") == 501) {
                    ++parts;
                }
                if (toolTag.func_74762_e("Extra") == 501) {
                    ++parts;
                }
                int luck = Math.min(3, parts);
                if (parts == tool.getPartAmount()) {
                    ++luck;
                    if (tool.getPartAmount() == 2) {
                        ++luck;
                    }
                }
                LudicrousEvents.extraLuck(event, luck);
            }
        }
    }
    
    @SubscribeEvent
    public void onHurt(final LivingHurtEvent event) {
        final Entity damaged = event.entity;
        final Entity damager = event.source.func_76364_f();
        if (damager != null && damager instanceof EntityLivingBase && damaged instanceof EntityLivingBase) {
            final EntityLivingBase attacker = (EntityLivingBase)damager;
            final EntityLivingBase attacked = (EntityLivingBase)damaged;
            final ItemStack held = attacker.func_70694_bm();
            if (held != null && held.func_77942_o() && held.func_77973_b() instanceof ToolCore) {
                final ToolCore tool = (ToolCore)held.func_77973_b();
                final NBTTagCompound toolTag = held.func_77978_p().func_74775_l("InfiTool");
                float puntpower = 0.0f;
                final float puntboost = 1.5f;
                if (toolTag.func_74762_e("Head") == 500) {
                    puntpower += puntboost;
                }
                if (toolTag.func_74762_e("Handle") == 500) {
                    puntpower += puntboost;
                }
                if (toolTag.func_74762_e("Accessory") == 500) {
                    puntpower += puntboost;
                }
                if (toolTag.func_74762_e("Extra") == 500) {
                    puntpower += puntboost;
                }
                final float knockback = (AbilityHelper.calcKnockback((Entity)attacker, (Entity)attacked, held, tool, toolTag, 0) + 1.0f) * puntpower;
                attacked.func_70024_g((double)(-MathHelper.func_76126_a(attacker.field_70177_z * 3.1415927f / 180.0f) * knockback * 0.5f), 0.1, (double)(MathHelper.func_76134_b(attacker.field_70177_z * 3.1415927f / 180.0f) * knockback * 0.5f));
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerMine(final PlayerInteractEvent event) {
        if (!Config.bedrockBreaker || event.face == -1 || event.world.field_72995_K || event.action != PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || event.entityPlayer.func_70694_bm() == null || event.entityPlayer.field_71075_bZ.field_75098_d) {
            return;
        }
        final Block block = event.world.func_147439_a(event.x, event.y, event.z);
        final int meta = event.world.func_72805_g(event.x, event.y, event.z);
        final ItemStack held = event.entityPlayer.func_70694_bm();
        if (block.func_149712_f(event.entityPlayer.field_70170_p, event.x, event.y, event.z) <= -1.0f && held.func_77942_o() && held.func_77973_b() instanceof ToolCore && (block.func_149688_o() == Material.field_151576_e || block.func_149688_o() == Material.field_151573_f)) {
            final NBTTagCompound toolTag = held.func_77978_p().func_74775_l("InfiTool");
            final ToolCore tool = (ToolCore)held.func_77973_b();
            if (toolTag != null && toolTag.func_74762_e("Head") == 501 && tool.canHarvestBlock(Blocks.field_150348_b, held)) {
                if (block.func_149745_a(this.randy) == 0) {
                    ItemStack drop = block.getPickBlock(ToolHelper.raytraceFromEntity(event.world, (Entity)event.entityPlayer, true, 10.0), event.world, event.x, event.y, event.z, event.entityPlayer);
                    if (drop == null) {
                        drop = new ItemStack(block, 1, meta);
                    }
                    LudicrousEvents.dropItem(drop, event.entityPlayer.field_70170_p, event.x, event.y, event.z);
                }
                else {
                    block.func_149636_a(event.world, event.entityPlayer, event.x, event.y, event.z, meta);
                }
                event.entityPlayer.field_70170_p.func_147468_f(event.x, event.y, event.z);
                event.world.func_72926_e(2001, event.x, event.y, event.z, Block.func_149682_b(block) + (meta << 12));
            }
        }
    }
}

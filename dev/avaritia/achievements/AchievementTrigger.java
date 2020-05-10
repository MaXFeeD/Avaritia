// 
// Decompiled by Procyon v0.5.36
// 

package fox.spiteful.avaritia.achievements;

import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fox.spiteful.avaritia.compat.Compat;
import net.minecraft.item.Item;
import fox.spiteful.avaritia.blocks.LudicrousBlocks;
import net.minecraft.stats.StatBase;
import fox.spiteful.avaritia.items.LudicrousItems;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class AchievementTrigger
{
    @SubscribeEvent
    public void onItemCrafted(final PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting != null) {
            if (event.crafting.func_77973_b() == LudicrousItems.resource) {
                switch (event.crafting.func_77960_j()) {
                    case 1: {
                        event.player.func_71064_a((StatBase)Achievements.crystal_matrix, 1);
                    }
                    case 4: {
                        event.player.func_71064_a((StatBase)Achievements.neutronium, 1);
                    }
                    case 5: {
                        event.player.func_71064_a((StatBase)Achievements.catalyst, 1);
                    }
                    case 6: {
                        event.player.func_71064_a((StatBase)Achievements.infinity, 1);
                    }
                }
            }
            else if (event.crafting.func_77973_b() == Item.func_150898_a(LudicrousBlocks.dire_crafting)) {
                event.player.func_71064_a((StatBase)Achievements.dire_crafting, 1);
            }
            else if (event.crafting.func_77973_b() == Item.func_150898_a(LudicrousBlocks.neutron_collector)) {
                event.player.func_71064_a((StatBase)Achievements.collector, 1);
            }
            else if (event.crafting.func_77973_b() == LudicrousItems.singularity) {
                event.player.func_71064_a((StatBase)Achievements.singularity, 1);
            }
            else if (Compat.blood && event.crafting.func_77973_b() == LudicrousItems.armok_orb) {
                event.player.func_71064_a((StatBase)Achievements.armok, 1);
            }
        }
    }
    
    @SubscribeEvent
    public void onItemSmelted(final PlayerEvent.ItemSmeltedEvent event) {
        if (event.smelting != null && event.smelting.func_77973_b() == LudicrousItems.singularity) {
            event.player.func_71064_a((StatBase)Achievements.singularity, 1);
        }
    }
    
    @SubscribeEvent
    public void onBorken(final BlockEvent.BreakEvent event) {
        if (event.block == LudicrousBlocks.dire_crafting && event.getPlayer() != null) {
            event.getPlayer().func_71064_a((StatBase)Achievements.dire_uncrafting, 1);
        }
    }
}

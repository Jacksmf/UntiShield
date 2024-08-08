package com.untiplugin.untishield;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("\n\n\nUntiPlugin | UntiShield\nStarted...\n\n\n");

        Bukkit.getPluginManager().registerEvents(this, this);
    }



    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if (player.isBlocking()) {
                ItemStack shield = player.getInventory().getItemInOffHand();

                if (shield.getType() == Material.SHIELD) {
                    getServer().getScheduler().runTask(this, () -> {
                        if (player.getCooldown(Material.SHIELD) > 0) {
                            player.sendMessage("Your shield is on cooldown!");
                            player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);
                            damager.playSound(damager.getLocation(), Sound.ITEM_SHIELD_BREAK, 1, 1);

                        }
                    });
                }
            }
        }
    }



    @Override
    public void onDisable() {
        Bukkit.getLogger().info("\n\n\nUntiPlugin | UntiShield\nStopped...\n\n\n");
    }
}

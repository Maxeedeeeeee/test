package com.admingui.adminguiplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GUI implements org.bukkit.command.CommandExecutor, Listener {

    private Inventory inventory;

    public GUI() {
        Bukkit.getPluginManager().registerEvents(this, JavaPlugin.getPlugin(AdminGUIPlugin.class));
    }

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mod.test")) {
                openGUI(player);
            } else {
                player.sendMessage("You don't have permission to use this command.");
            }
        }
        return true;
    }

    private void openGUI(Player player) {
        inventory = Bukkit.createInventory(null, 9, "Admin GUI");

        // Kick All Button
        ItemStack kickAllItem = new ItemStack(Material.BARRIER);
        inventory.setItem(0, kickAllItem);

        // Shutdown Server Button
        ItemStack shutdownItem = new ItemStack(Material.LAVA_BUCKET);
        inventory.setItem(1, shutdownItem);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("Admin GUI")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (event.getSlot() == 0) { // Kick All
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.kickPlayer("You have been kicked by an admin.");
                }
                player.sendMessage("All players have been kicked!");
            } else if (event.getSlot() == 1) { // Shutdown Server
                Bukkit.shutdown();
                player.sendMessage("Server is shutting down...");
            }
        }
    }
}

package br.com.joaocansi.terrapurge.gui.events;

import br.com.joaocansi.terrapurge.Config;
import br.com.joaocansi.terrapurge.shared.Items;
import br.com.joaocansi.terrapurge.plot.Terra;
import br.com.joaocansi.terrapurge.shared.Messages;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class TerraPurgeConfirmationListener implements Listener {

    @EventHandler
    public void onTerraPurgeConfirm(InventoryClickEvent event) {
        String configTitle = Config.getString("confirmation_inventory.title");
        String title = event.getView().getTitle();

        if (!title.equals(configTitle) || !(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Terra terra = Terra.data.get(player.getUniqueId());

        if (!Items.TERRA_PURGE_ITEM.isSimilar(itemInHand) || terra == null) {
            player.closeInventory();
            return;
        }

        event.setCancelled(true);
        if (event.getSlot() == Config.getInt("confirmation_inventory.items.confirm.slot")) {
            terra.clear();
            player.playSound(player.getLocation(), Config.getSound("confirmation_inventory.items.confirm.sound"), 1.0f, 1.0f);
            player.sendMessage(Messages.TERRA_PURGE_CONFIRMED);
            itemInHand.setAmount(itemInHand.getAmount()-1);
        } else if (event.getSlot() == Config.getInt("confirmation_inventory.items.cancel.slot")) {
            player.playSound(player.getLocation(), Config.getSound("confirmation_inventory.items.cancel.sound"), 1.0f, 1.0f);
            player.sendMessage(Messages.TERRA_PURGE_CANCELLED);
        }
        player.closeInventory();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Terra.data.remove(player.getUniqueId());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        String configTitle = Config.getInstance().getString("confirmation_inventory.title");
        String title = event.getView().getTitle();

        if (!title.equals(configTitle) || !(event.getPlayer() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getPlayer();
        Terra.data.remove(player.getUniqueId());
    }
}

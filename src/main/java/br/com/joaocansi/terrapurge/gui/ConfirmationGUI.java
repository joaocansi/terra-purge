package br.com.joaocansi.terrapurge.gui;

import br.com.joaocansi.terrapurge.Config;
import br.com.joaocansi.terrapurge.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ConfirmationGUI {
    private final Inventory inventory;

    public ConfirmationGUI(Player p) {
        inventory = Bukkit
                .createInventory(
                        null,
                        Config.getInstance().getInt("confirmation_inventory.size"),
                        Objects.requireNonNull(Config.getInstance().getString("confirmation_inventory.title"))
                );
        initialize();
        p.openInventory(inventory);
    }

    public void initialize() {
        String path = "confirmation_inventory.items";

        ItemStack confirmItem = new ItemBuilder(path + ".confirm").toItemStack();
        ItemStack cancelItem = new ItemBuilder(path + ".cancel").toItemStack();

        int confirmSlot = Config.getInstance().getInt(path + ".confirm.slot");
        int cancelSlot = Config.getInstance().getInt(path + ".cancel.slot");

        inventory.setItem(confirmSlot, confirmItem);
        inventory.setItem(cancelSlot, cancelItem);
    }
}

package br.com.joaocansi.terrapurge.commands;

import br.com.joaocansi.terrapurge.utils.Items;
import br.com.joaocansi.terrapurge.utils.Messages;
import br.com.joaocansi.terrapurge.utils.validators.CommandTypeValidation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TerraPurgeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player))
            return false;

        Player p = (Player)commandSender;
        CommandTypeValidation validator = new CommandTypeValidation(args, "string", "int");

        if (!validator.validate()) {
            p.sendMessage(Messages.TERRA_PURGE_COMMAND);
            return false;
        }

        String name = args[0];
        int amount = Integer.parseInt(args[1]);

        ItemStack item = Items.TERRA_PURGE_ITEM.clone();
        item.setAmount(amount);

        Player to = Bukkit.getPlayer(name);
        if (to == null || !to.isOnline()) {
            p.sendMessage(Messages.PLAYER_NOT_FOUND
                    .replace("{player}", name)
                    .replace("{amount}", String.valueOf(amount)));
            return false;
        }

        to.getInventory().addItem(item);
        p.sendMessage(Messages.TERRA_PURGE_ITEM_GAVE
                .replace("{player}", name)
                .replace("{amount}", String.valueOf(amount)));
        return true;
    }
}

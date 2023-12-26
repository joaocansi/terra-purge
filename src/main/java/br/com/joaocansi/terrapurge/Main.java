package br.com.joaocansi.terrapurge;

import br.com.joaocansi.terrapurge.commands.TerraPurgeCommand;
import br.com.joaocansi.terrapurge.listeners.TerraPurgeConfirmationListener;
import br.com.joaocansi.terrapurge.listeners.TerraPurgeInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        load();
    }

    public void load() {
        Config.init(this);
        Objects.requireNonNull(getCommand("terrapurge")).setExecutor(new TerraPurgeCommand());
        getServer().getPluginManager().registerEvents(new TerraPurgeInteractListener(), this);
        getServer().getPluginManager().registerEvents(new TerraPurgeConfirmationListener(), this);
    }
}

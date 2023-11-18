package br.com.joaocansi.terrapurge;

import br.com.joaocansi.terrapurge.commands.TerraPurgeCommand;
import br.com.joaocansi.terrapurge.gui.events.TerraPurgeConfirmationListener;
import br.com.joaocansi.terrapurge.plot.events.TerraPurgeListener;
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
        getServer().getPluginManager().registerEvents(new TerraPurgeListener(), this);
        getServer().getPluginManager().registerEvents(new TerraPurgeConfirmationListener(), this);
    }
}

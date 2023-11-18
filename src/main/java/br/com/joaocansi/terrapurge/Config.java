package br.com.joaocansi.terrapurge;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Config {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin main) {
        plugin = main;
        plugin.saveDefaultConfig();
    }

    public static FileConfiguration getInstance() {
        return plugin.getConfig();
    }

    public static String getString(String path) {
        return Objects.requireNonNull(getInstance().getString(path)).replace("&", "§");
    }

    public static Sound getSound(String path) {
        return Sound.valueOf(Objects.requireNonNull(getInstance().getString(path)));
    }

    public static int getInt(String path) {
        return getInstance().getInt(path);
    }
}

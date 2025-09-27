package traiwy.utils;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
public class PlayersConfigManager {
    private final JavaPlugin plugin;

    private File file;
    private FileConfiguration config;

    public void loadPlayersConfig() {
        file = new File(plugin.getDataFolder(), "players.yml");
        if (!file.exists()) {
            plugin.saveResource("players.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void savePlayersConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning(e.getMessage());
        }
    }

    public void setQuestInformation(UUID uuid, Status status, double progress, boolean rewardClaimed) {
        ConfigManager.getQuestsList().forEach((name1, quest1) -> {
            String basePath = "quest." + uuid + ".quests." + name1;

            config.set(basePath + ".status", status.toString());
            config.set(basePath + ".progress", progress);
            config.set(basePath + ".reward-claimed", rewardClaimed);

            savePlayersConfig();
        });

    }
}

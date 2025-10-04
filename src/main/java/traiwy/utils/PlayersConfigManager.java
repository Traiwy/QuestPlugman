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
    private final ConfigManager configManager;

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

    //Устанавливает информацию в конфиге
    public void setQuestInformation(UUID uuid, Status status, double progress, boolean rewardClaimed) {
        configManager.getQuestsList().forEach((name1, quest1) -> {
            String basePath = "quest." + uuid + ".quests." + name1;

            config.set(basePath + ".status", status.toString());
            config.set(basePath + ".progress", progress);
            config.set(basePath + ".reward-claimed", rewardClaimed);
        });
        savePlayersConfig();
    }

    //Добавляет прогресс, после выполнения определенного пункта. Доделать в конце, пока отложка
    public void addProgress(UUID uuid, double progress, String quest, Status status) {
        String path = "quest." + uuid + ".quests." + quest;
        config.set(path + ".progress", progress);
        savePlayersConfig();
    }

    //Устанавливает статус
    public void setStatus(UUID uuid, String quest, Status status) {
        String path = "quest." + uuid + ".quests." + quest;
        config.set(path + ".status", status.toString());
        savePlayersConfig();
    }

    //Получает значение статуса
    public Status getStatus(UUID uuid, String quest) {
        String path = "quest." + uuid + ".quests." + quest + ".status";
        String statusString = config.getString(path, "NOT_STARTED");
        return Status.valueOf(statusString.toUpperCase());
    }

    //Получает значение награды
    public boolean isRewardClaimed(UUID uuid, String quest) {
        String pathReward = "quest." + uuid + ".quests." + quest + ".reward-claimed";
        return config.getBoolean(pathReward, false);
    }

    //Устанавливает значение награды
    public void setRewardClaimed(UUID uuid, String quest, boolean reward){
        String pathReward = "quest." + uuid + ".quests." + quest + ".reward-claimed";
        config.set(pathReward, reward);

        savePlayersConfig();
    }
}
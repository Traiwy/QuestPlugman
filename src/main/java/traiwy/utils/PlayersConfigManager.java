package traiwy.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class PlayersConfigManager {
    private final JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    public void loadPlayersConfig(){
        file = new File(plugin.getDataFolder(), "players.yml");
        if(!file.exists()){
            plugin.saveResource("players.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void savePlayersConfig(){
        try{
            config.save(file);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }



}

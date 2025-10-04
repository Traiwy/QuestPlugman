package traiwy.questPlugman;

import org.bukkit.plugin.java.JavaPlugin;
import traiwy.command.CommandOpenMenu;
import traiwy.command.GetQuestCommand;
import traiwy.event.PlayerJoinListener;
import traiwy.event.QuestHomeWanderListener;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.inventory.main.MainMenuListener;
import traiwy.utils.ConfigManager;
import traiwy.utils.PlayersConfigManager;

import java.util.HashMap;

public final class QuestPlugman extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        final ConfigManager configManager = new ConfigManager();
        final PlayersConfigManager playersConfigManager = new PlayersConfigManager(this, configManager, getFile(), getConfig());
        configManager.loadConfig(getConfig());
        playersConfigManager.loadPlayersConfig();
        final MainMenuHolder mainMenuHolder = new MainMenuHolder(this, configManager, playersConfigManager);



        getCommand("mineskills").setExecutor(new CommandOpenMenu(mainMenuHolder));
        getCommand("quest").setExecutor(new GetQuestCommand(playersConfigManager));
        getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
        getServer().getPluginManager().registerEvents(new QuestHomeWanderListener(playersConfigManager, mainMenuHolder), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(playersConfigManager, mainMenuHolder), this);
    }

}

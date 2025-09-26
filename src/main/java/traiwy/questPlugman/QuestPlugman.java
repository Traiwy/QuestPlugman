package traiwy.questPlugman;

import org.bukkit.plugin.java.JavaPlugin;
import traiwy.command.CommandOpenMenu;
import traiwy.event.QuestHomeWanderListener;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.inventory.main.MainMenuListener;
import traiwy.utils.ConfigManager;

public final class QuestPlugman extends JavaPlugin {


    @Override
    public void onEnable() {
        saveDefaultConfig();
        ConfigManager configManager = new ConfigManager();
        ConfigManager.loadConfig(getConfig());
        final MainMenuHolder mainMenuHolder = new MainMenuHolder( this, configManager);
        getCommand("mineskills").setExecutor(new CommandOpenMenu(mainMenuHolder));
        getServer().getPluginManager().registerEvents(new MainMenuListener(), this);
        getServer().getPluginManager().registerEvents(new QuestHomeWanderListener(), this);
    }

}

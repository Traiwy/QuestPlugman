package traiwy.questPlugman;

import org.bukkit.plugin.java.JavaPlugin;
import traiwy.command.CommandOpenMenu;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.utils.ConfigManager;

public final class QuestPlugman extends JavaPlugin {


    @Override
    public void onEnable() {
        saveDefaultConfig();
        ConfigManager.loadConfig(getConfig());
        final MainMenuHolder mainMenuHolder = new MainMenuHolder();
        getCommand("mineskills").setExecutor(new CommandOpenMenu(mainMenuHolder));
    }


}

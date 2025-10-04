package traiwy.command;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.utils.ConfigManager;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.util.UUID;


@AllArgsConstructor
public class CommandOpenMenu implements CommandExecutor {
    private final MainMenuHolder mainMenuHolder;
    private final PlayersConfigManager playersConfigManager;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player player)) return false;

        Inventory inventory = mainMenuHolder.getInventory();
        UUID uuid = player.getUniqueId();

        for (String questKey : mainMenuHolder.quests.keySet()) {
            mainMenuHolder.updateQuestPanel(questKey, uuid);
        }

        player.openInventory(inventory);
        return true;
    }
}

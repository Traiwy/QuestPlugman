package traiwy.command;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.util.UUID;

@AllArgsConstructor
public class SetQuestFirstJoinCommand implements CommandExecutor {
    private final PlayersConfigManager playersConfigManager;
    private final MainMenuHolder mainMenuHolder;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        UUID targetPlayer = player.getUniqueId();

        playersConfigManager.setQuestInformation(targetPlayer, Status.NOT_STARTED, 0.0, false);
        playersConfigManager.setStatus(targetPlayer, "quest1", Status.IN_PROGRESS);
        playersConfigManager.setRewardClaimed(targetPlayer, "quest1", false);
        return true;
    }
}

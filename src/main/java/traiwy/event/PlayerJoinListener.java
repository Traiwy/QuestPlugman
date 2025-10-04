package traiwy.event;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.util.UUID;

@AllArgsConstructor
public class PlayerJoinListener implements Listener {
    private final PlayersConfigManager playersConfigManager;
    private final MainMenuHolder mainMenuHolder;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        final UUID targetPlayer = player.getUniqueId();

        if (!player.hasPlayedBefore()) {
            playersConfigManager.setQuestInformation(targetPlayer, Status.NOT_STARTED, 0.0, false);
            playersConfigManager.setStatus(targetPlayer, "quest1", Status.IN_PROGRESS);
            playersConfigManager.setRewardClaimed(targetPlayer, "quest1", false);
        }
    }
}

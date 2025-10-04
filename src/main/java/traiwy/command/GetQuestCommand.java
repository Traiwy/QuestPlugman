package traiwy.command;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetQuestCommand implements CommandExecutor, TabExecutor {
    private final PlayersConfigManager playersConfigManager;
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return false;
        String quest = strings[0];
        switch (quest) {
            case "quest1":
                playersConfigManager.setStatus(player.getUniqueId(), "quest1", Status.IN_PROGRESS);
                playersConfigManager.setRewardClaimed(player.getUniqueId(), "quest1", true);
                break;
            case "quest2":
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] SUBCOMMAND = {"quest1", "quest2", "quest3"};
        if(strings.length == 1){
            return Arrays.stream(SUBCOMMAND)
                    .filter(a -> a.startsWith(strings[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return  Collections.emptyList();
    }
}

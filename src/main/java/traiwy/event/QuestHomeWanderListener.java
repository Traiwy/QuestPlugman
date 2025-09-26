package traiwy.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.lang.reflect.Member;


public class QuestHomeWanderListener implements Listener {
    @EventHandler
    public void onBedEnter(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block bed = event.getBlockPlaced();

        if (bed.getType() == Material.RED_BED) {
            player.sendMessage("Кровать");
            final Location bedLoc = bed.getLocation();
            final int radius = 5;
            boolean foundItem = false;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        Block near = bedLoc.getWorld().getBlockAt(
                                bedLoc.getBlockX() + x,
                                bedLoc.getBlockY() + y,
                                bedLoc.getBlockZ() + z
                        );
                        if (near.getType() == Material.FLOWER_POT ||
                                near.getType() == Material.ITEM_FRAME ||
                                near.getType() == Material.PAINTING ||
                                near.getType() == Material.OAK_SIGN
                        ) {
                            foundItem = true;
                            break;
                        }
                    }
                }
            }
            if (foundItem == true) {
                player.sendMessage(ChatColor.GOLD + "§lКвест выполнен: §6Дом Странника");
                player.sendMessage(ChatColor.GRAY + "Теперь у тебя есть своё убежище...");
            }
        }
    }
}

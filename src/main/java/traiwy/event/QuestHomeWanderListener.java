package traiwy.event;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import traiwy.inventory.main.MainMenuHolder;
import traiwy.inventory.main.MainMenuListener;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.lang.reflect.Member;


//Переделать, чтобы проверялось даже, когда кровать стоит, а не так как работает сейчас
@AllArgsConstructor
public class QuestHomeWanderListener implements Listener {
    private final PlayersConfigManager playersConfigManager;
    private final MainMenuHolder mainMenuHolder;

    final static int RADIUS = 5;

    @EventHandler
    public void onBedEnter(BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final Block bed = event.getBlockPlaced();


        if (Tag.BEDS.isTagged(bed.getType())){
            player.sendMessage("Кровать");
            final Location bedLoc = bed.getLocation();
            boolean foundItem = findBlock(RADIUS, bedLoc);

            if (!foundItem) {
                for (Entity entity : bedLoc.getWorld().getNearbyEntities(bedLoc, RADIUS, RADIUS, RADIUS)) {
                    if (entity instanceof ItemFrame || entity instanceof Painting) {
                        foundItem = true;
                        break;
                    }
                }
            }
            if (foundItem) {
                player.sendMessage(ChatColor.GOLD + "§lКвест выполнен: §6Дом Странника");
                player.sendMessage(ChatColor.GRAY + "Теперь у тебя есть своё убежище...");
                playersConfigManager.setStatus(player.getUniqueId(), "quest1", Status.COMPLETED);
                mainMenuHolder.updateQuestPanel("quest1", player.getUniqueId());
            }
        }
    }
    public static boolean findBlock(int radius, Location bedLoc) {
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block near = bedLoc.getWorld().getBlockAt(
                            bedLoc.getBlockX() + x,
                            bedLoc.getBlockY() + y,
                            bedLoc.getBlockZ() + z
                    );
                    if (near.getType() == Material.FLOWER_POT ||
                            near.getType() == Material.OAK_SIGN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
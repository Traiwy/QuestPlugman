package traiwy.inventory.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        InventoryHolder holder = inv.getHolder();

        if(holder == null) return;
        if(holder != null && inv != null){
            e.setCancelled(true);
        }
    }
}

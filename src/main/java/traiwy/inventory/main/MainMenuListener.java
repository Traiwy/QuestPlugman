package traiwy.inventory.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        final Player player = (Player) e.getWhoClicked();
        final Inventory inv = e.getClickedInventory();
        final InventoryHolder holder = inv.getHolder();
        final ItemStack item = e.getCurrentItem();

        if(holder == null || item == null || inv ==  null) return;

        if(holder instanceof  MainMenuHolder && item != null){
            e.setCancelled(true);
        }
    }
}

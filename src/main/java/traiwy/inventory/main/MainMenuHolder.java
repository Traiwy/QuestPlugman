package traiwy.inventory.main;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import traiwy.utils.ConfigManager;

import java.util.ArrayList;

public class MainMenuHolder implements InventoryHolder {
    private final Inventory inventory = Bukkit.createInventory(this, 54, "Квестовик");


    @Override
    public @NotNull Inventory getInventory() {


        final ItemStack grayPanel = new ItemStack(ConfigManager.Gui.GrayPanel.material);
        final ItemStack orangePanel = new ItemStack(ConfigManager.Gui.OrangePanel.material);
        final ItemStack infoQuest = new ItemStack(ConfigManager.Gui.InfoQuestButtons.material);
        final ItemStack infoStats = new ItemStack(ConfigManager.Gui.InfoStatsButtons.material);


        final ItemMeta meta = grayPanel.getItemMeta();
        meta.setDisplayName("");
        meta.setLore(new ArrayList<>());
        grayPanel.setItemMeta(meta);

        final ItemMeta orangeMeta = orangePanel.getItemMeta();
        orangeMeta.setDisplayName("");
        orangeMeta.setLore(new ArrayList<>());
        orangePanel.setItemMeta(orangeMeta);

        final ItemMeta infoQuestMeta = infoQuest.getItemMeta();
        infoQuestMeta.setDisplayName("");
        infoQuestMeta.setLore(ConfigManager.Gui.InfoQuestButtons.lore);
        infoQuest.setItemMeta(infoQuestMeta);

        final ItemMeta infoStatsMeta = infoStats.getItemMeta();
        infoStatsMeta.setDisplayName("");
        infoStats.setItemMeta(infoStatsMeta);

        for (Integer i : ConfigManager.Gui.OrangePanel.slots) {
            inventory.setItem(i, orangePanel);
        }

        inventory.setItem(ConfigManager.Gui.InfoQuestButtons.slot, infoQuest);
        inventory.setItem(ConfigManager.Gui.InfoStatsButtons.slot, infoStats);
        return inventory;
    }
}

package traiwy.inventory.main;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import traiwy.utils.ConfigManager;
import traiwy.utils.ItemMetaManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@AllArgsConstructor
public class MainMenuHolder implements InventoryHolder {
    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    private final Inventory inventory = Bukkit.createInventory(this, 54, "Квестовик");
    final Map<String, ConfigManager.Gui.Quest> quests = ConfigManager.getQuestsList();

    @Override
    public @NotNull Inventory getInventory() {

        final ItemStack grayPanel = new ItemStack(ConfigManager.Gui.GrayPanel.material);
        final ItemStack orangePanel = new ItemStack(ConfigManager.Gui.OrangePanel.material);
        final ItemStack infoQuest = new ItemStack(ConfigManager.Gui.InfoQuestButtons.material);
        final ItemStack infoStats = new ItemStack(ConfigManager.Gui.InfoStatsButtons.material);
        final ItemStack questPanel = new ItemStack(ConfigManager.Gui.Quests.material);


        ItemMetaManager.applyMeta(grayPanel, " ", new ArrayList<>());
        ItemMetaManager.applyMeta(orangePanel, new ArrayList<>());
        ItemMetaManager.applyMeta(infoQuest, " ", ConfigManager.Gui.InfoQuestButtons.lore);
        ItemMetaManager.applyMeta(infoStats, " ", new ArrayList<>());

        for (Integer i : ConfigManager.Gui.OrangePanel.slots) {
            inventory.setItem(i, orangePanel);
        }

        List<Integer> questsSlots = plugin.getConfig().getIntegerList("gui.quests.slots");
        inventory.setItem(ConfigManager.Gui.InfoQuestButtons.slot, infoQuest);
        inventory.setItem(ConfigManager.Gui.InfoStatsButtons.slot, infoStats);
        int i = 0;
        for (Map.Entry<String, ConfigManager.Gui.Quest> entry : quests.entrySet()) {
            if (i >= questsSlots.size()) break;
            int slot = questsSlots.get(i);

            ConfigManager.Gui.Quest quest = entry.getValue();
            ItemStack questItem = new ItemStack(ConfigManager.Gui.Quests.material);
            ItemMetaManager.applyMeta(questItem, quest.getName(), quest.getLore());
            inventory.setItem(slot, questItem);

            i++;
        }
        return inventory;
    }

}


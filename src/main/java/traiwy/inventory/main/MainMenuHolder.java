package traiwy.inventory.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import traiwy.utils.ConfigManager;
import traiwy.utils.ItemMetaManager;
import traiwy.utils.PlayersConfigManager;
import traiwy.utils.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class MainMenuHolder implements InventoryHolder {
    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    private final PlayersConfigManager playersConfigManager;
    public Map<String, ConfigManager.Gui.Quest> quests;

    @Getter
    private final Inventory inventory;

    public MainMenuHolder(JavaPlugin plugin, ConfigManager configManager, PlayersConfigManager playersConfigManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.playersConfigManager = playersConfigManager;
        this.quests = configManager.getQuestsList();
        this.inventory = Bukkit.createInventory(this, 54, "Квестовик");
    }


    @Override
    public @NotNull Inventory getInventory() {

        final ItemStack orangePanel = new ItemStack(ConfigManager.Gui.OrangePanel.material);
        final ItemStack infoQuest = new ItemStack(ConfigManager.Gui.InfoQuestButtons.material);
        final ItemStack infoStats = new ItemStack(ConfigManager.Gui.InfoStatsButtons.material);
        final ItemStack questPanel = new ItemStack(ConfigManager.Gui.Quests.material);

        ItemMetaManager.applyMeta(orangePanel, new ArrayList<>());
        ItemMetaManager.applyMeta(infoQuest, " ", ConfigManager.Gui.InfoQuestButtons.lore);
        ItemMetaManager.applyMeta(infoStats, " ", new ArrayList<>());

        for (Integer i : ConfigManager.Gui.OrangePanel.slots) {
            inventory.setItem(i, orangePanel);
        }

        List<Integer> questsSlots = plugin.getConfig().getIntegerList("gui.quests.slots");

        for (int i = 0; i < questsSlots.size(); i++) {
            int slot = questsSlots.get(i);

            ConfigManager.Gui.Quest quest = quests.get("quest" + (i + 1));
            if (quest == null) continue;

            ItemStack questItem = new ItemStack(ConfigManager.Gui.Quests.material);
            ItemMetaManager.applyMeta(questItem, quest.getName(), quest.getLore());
            inventory.setItem(slot, questItem);

        }
        inventory.setItem(ConfigManager.Gui.InfoQuestButtons.slot, infoQuest);
        inventory.setItem(ConfigManager.Gui.InfoStatsButtons.slot, infoStats);
        return inventory;
    }

    //Установка зеленой панели
    public void updateQuestPanel(String questKey, UUID uuid) {
        int questNumber = Integer.parseInt(questKey.replace("quest", "")) - 1;
        List<Integer> questsSlots = plugin.getConfig().getIntegerList("gui.quests.slots");
        if (questNumber >= questsSlots.size()) return;
        int slot = questsSlots.get(questNumber);

        ConfigManager.Gui.Quest quest = quests.get(questKey);
        if (quest == null) return;

        Status status = playersConfigManager.getStatus(uuid, questKey);

        Material material = ConfigManager.Gui.Quests.material;

        if (status == Status.COMPLETED) {
            material = Material.ORANGE_STAINED_GLASS_PANE;
        } else if (status == Status.IN_PROGRESS) {
            material = Material.LIME_STAINED_GLASS_PANE;
        }

        ItemStack questItem = new ItemStack(material);
        ItemMetaManager.applyMeta(questItem, quest.getName(), quest.getLore());
        inventory.setItem(slot, questItem);
    }

}


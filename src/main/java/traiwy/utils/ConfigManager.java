package traiwy.utils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ConfigManager {

    private static FileConfiguration config;

    @Getter
    private static Map<String, Gui.Quest> questsList = new HashMap();
    private final static Logger log = Bukkit.getLogger();

    public static void loadConfig(FileConfiguration file) {
        config = file;
        parseGui();
    }

    public static void parseGui() {
        final ConfigurationSection section = config.getConfigurationSection("gui");
        isSectionIsNull(section, "gui");
        Gui.name = section.getString("name");
        Gui.size = section.getInt("size");

        final ConfigurationSection infoQuestButtonsSection = config.getConfigurationSection("gui.buttons.info-quest");
        isSectionIsNull(infoQuestButtonsSection, "gui.buttons.info-quest");
        Gui.InfoQuestButtons.slot = infoQuestButtonsSection.getInt("slot");
        Gui.InfoQuestButtons.material = Material.getMaterial(infoQuestButtonsSection.getString("material"));
        Gui.InfoQuestButtons.lore = infoQuestButtonsSection.getStringList("lore");
        log.info("Предмет: " + infoQuestButtonsSection.getString("material").toString() + ".  Слот: " + infoQuestButtonsSection.getInt("slot"));


        final ConfigurationSection infoStatsButtonsSection = config.getConfigurationSection("gui.buttons.info-stats");
        isSectionIsNull(infoStatsButtonsSection, "gui.buttons.info-stats");
        Gui.InfoStatsButtons.slot = infoStatsButtonsSection.getInt("slot");
        Gui.InfoStatsButtons.material = Material.getMaterial(infoStatsButtonsSection.getString("material"));
        log.info("Предмет: " + infoStatsButtonsSection.getString("material").toString() + ".  Слот: " + infoStatsButtonsSection.getInt("slot"));

        final ConfigurationSection orangePanelButtonsSection = config.getConfigurationSection("gui.buttons.orangePanel");
        isSectionIsNull(orangePanelButtonsSection, "gui.buttons.orangePanel");
        Gui.OrangePanel.material = Material.getMaterial(orangePanelButtonsSection.getString("material"));
        Gui.OrangePanel.slots = orangePanelButtonsSection.getIntegerList("slots");

        final ConfigurationSection grayPanelButtonsSection = config.getConfigurationSection("gui.buttons.grayPanel");
        isSectionIsNull(grayPanelButtonsSection, "gui.buttons.grayPanel");
        Gui.GrayPanel.material = Material.getMaterial(grayPanelButtonsSection.getString("material"));
        Gui.GrayPanel.slots = grayPanelButtonsSection.getIntegerList("slots");


        final ConfigurationSection questSection = config.getConfigurationSection("gui.quests");
        isSectionIsNull(questSection, "gui.quests");

        questsList.clear();
        Gui.Quests.material = Material.getMaterial(questSection.getString("material"));
        for (String questKey : questSection.getKeys(false)) {
            if (questKey.equals("material")) continue;
            if (questKey.equals("slots")) continue;
            ConfigurationSection questInfoSection = config.getConfigurationSection("gui.quests." + questKey);
            if(questInfoSection == null) log.info("Section " + questInfoSection + " null");

            if (questInfoSection != null) {
                String name = questInfoSection.getString("name");
                List<String> lore = questInfoSection.getStringList("lore");
                Gui.Quest quest = new Gui.Quest(name, lore);
                questsList.put(questKey, quest);
            } else {
                log.info("Секция:  " + questKey + " не найдена");
            }
        }
    }

    public static class Gui {
        public static String name;
        public static int size;

        public static class InfoQuestButtons {
            public static int slot;
            public static Material material;
            public static List<String> lore;
        }

        public static class InfoStatsButtons {
            public static int slot;
            public static Material material;
        }

        public static class OrangePanel {
            public static Material material;
            public static List<Integer> slots;
        }

        public static class GrayPanel {
            public static Material material;
            public static List<Integer> slots;
        }

        public static class Quests {
            public static Material material;
        }

        public static class Quest {
            private final String name;
            private final List<String> lore;

            public Quest(String name, List<String> lore) {
                this.name = name;
                this.lore = lore;
            }

            public String getName() {
                return name;
            }

            public List<String> getLore() {
                return lore;
            }
        }

    }

    public static void isSectionIsNull(ConfigurationSection configurationSection, String sections) {
        if (sections == null) log.info("Sections : " + configurationSection + " not found");
    }
}

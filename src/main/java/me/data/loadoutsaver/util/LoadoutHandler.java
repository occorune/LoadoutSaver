package me.data.loadoutsaver.util;

import me.data.loadoutsaver.LSMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoadoutHandler {
    public static void saveInventory(Player p, String fileName) {
        UUID uuid = p.getUniqueId();
        File dataFolder = new File(LSMain.getInstance().getDataFolder(), "inventories");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File playerFile = new File(dataFolder.getPath() + File.separator + uuid.toString() + "_" + fileName + ".yml");
        try {
            YamlConfiguration playerConfig = new YamlConfiguration();
            FileConfiguration config = LSMain.getInstance().getConfig();
            playerConfig.set("inventory", p.getInventory().getContents());
            playerConfig.set("armor", p.getInventory().getArmorContents());
            playerConfig.save(playerFile);
            p.sendMessage(LSMain.color(config.getString("Messages.SaveLoadout")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadInventory(Player p, String fileName) {
        UUID uuid = p.getUniqueId();
        FileConfiguration config = LSMain.getInstance().getConfig();
        File dataFolder = new File(LSMain.getInstance().getDataFolder(), "inventories");
        File playerFile = new File(dataFolder.getPath() + File.separator + uuid.toString() + "_" + fileName + ".yml");
        if (!playerFile.exists()) {
            p.sendMessage(LSMain.color(config.getString("Messages.NoLoadout")));
            return;
        }
        try {
            YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
            ItemStack[] contents = ((List<ItemStack>) playerConfig.get("inventory")).toArray(new ItemStack[0]);
            ItemStack[] armor = ((List<ItemStack>) playerConfig.get("armor")).toArray(new ItemStack[0]);
            p.getInventory().setContents(contents);
            p.getInventory().setArmorContents(armor);
            p.sendMessage(LSMain.color(config.getString("Messages.LoadLoadout")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteInventory(Player p, String filename) {
        UUID uuid = p.getUniqueId();
        FileConfiguration config = LSMain.getInstance().getConfig();
        File file = new File(LSMain.getInstance().getDataFolder(), "inventories" + File.separator + uuid.toString() + "_" + filename + ".yml");
        if (file.exists()) {
            file.delete();
            p.sendMessage(LSMain.color(config.getString("Messages.DeleteLoadout")));
        } else {
            p.sendMessage(LSMain.color(config.getString("Messages.NoLoadout")));
        }
    }

    public static boolean hasSavedInventory(Player p, int loadoutNum) {
        UUID uuid = p.getUniqueId();
        FileConfiguration config = LSMain.getInstance().getConfig();
        String loadoutPrefix = config.getString("Settings.FileSuffix") + loadoutNum;
        String fileName = uuid + "_" + loadoutPrefix;
        File dataFolder = new File(LSMain.getInstance().getDataFolder(), "inventories");
        File playerDataFile = new File(dataFolder.getPath() + File.separator + fileName + ".yml");
        return playerDataFile.exists();
    }
}

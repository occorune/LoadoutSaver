package me.data.loadoutsaver.listener;

import me.data.loadoutsaver.LSMain;
import me.data.loadoutsaver.util.LoadoutHandler;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getServer;

public class CustomLoadout implements Listener {
        @EventHandler
        public void onSave(InventoryClickEvent e) {
            FileConfiguration config = LSMain.getInstance().getConfig();
            String guiName = config.getString("Settings.GUIName");
            if (e.getView().getTitle().equalsIgnoreCase(guiName)) {
                e.setCancelled(true);
            }
            String saveMat = config.getString("Settings.Save.Material");
            String loadMat = config.getString("Settings.Load.Material");
            String deleteMat = config.getString("Settings.Delete.Material");
            if (saveMat == null || loadMat == null || deleteMat == null) {
                getServer().getConsoleSender().sendMessage(LSMain.color("&cInvalid material(s) found"));
                return;
            }

            Player p = (Player)e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            if (clicked == null) {
                return;
            }
            ItemMeta meta = clicked.getItemMeta();
            if (meta == null) {
                return;
            }
            String itemName = meta.getDisplayName();
            if (itemName == null) {
                return;
            }

            if (!p.hasPermission("LS.save") || !p.hasPermission("LS.all")) {
                return;
            }

            int inventorySlot = -1;
            String loadoutSuffix = "";
            if (itemName.equalsIgnoreCase(LSMain.color("&aSave &bLoadout #1"))) {
                inventorySlot = 10;
                loadoutSuffix = "1";
            } else if (itemName.equalsIgnoreCase(LSMain.color("&aSave &bLoadout #2"))) {
                inventorySlot = 12;
                loadoutSuffix = "2";
            } else if (itemName.equalsIgnoreCase(LSMain.color("&aSave &bLoadout #3"))) {
                inventorySlot = 14;
                loadoutSuffix = "3";
            } else if (itemName.equalsIgnoreCase(LSMain.color("&aSave &bLoadout #4"))) {
                inventorySlot = 16;
                loadoutSuffix = "4";
            }

            if (inventorySlot == -1) {
                return;
            }

            LoadoutHandler.saveInventory(p, config.getString("Settings.FileSuffix") + loadoutSuffix);
            e.getInventory().setItem(inventorySlot, createLoadItem(loadMat, loadoutSuffix));
            e.getInventory().setItem(inventorySlot + 9, createDeleteItem(deleteMat, loadoutSuffix));
        }

    @EventHandler
    public void onLoad(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        FileConfiguration config = LSMain.getInstance().getConfig();

        if (clicked == null) {
            return;
        }
        ItemMeta meta = clicked.getItemMeta();
        if (meta == null) {
            return;
        }
        String itemName = meta.getDisplayName();
        if (itemName == null) {
            return;
        }

        String guiName = config.getString("Settings.GUIName");

        if (!e.getView().getTitle().equalsIgnoreCase(guiName)) {
            return;
        }

        String loadMat = config.getString("Settings.Load.Material");
        if (loadMat == null) {
            getServer().getConsoleSender().sendMessage(LSMain.color("&cDelete Material is not valid: " + loadMat));
            return;
        }

        Material loadMaterial = Material.matchMaterial(loadMat);
        if (clicked.getType() != loadMaterial) {
            return;
        }

        if (!p.hasPermission("LS.load") || !p.hasPermission("LS.all")) {
            return;
        }

        int inventorySlot = -1;
        String loadoutSuffix = "";
        if (itemName.equalsIgnoreCase(LSMain.color("&aLoad &bLoadout #1"))) {
            inventorySlot = 10;
            loadoutSuffix = "1";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aLoad &bLoadout #2"))) {
            inventorySlot = 12;
            loadoutSuffix = "2";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aLoad &bLoadout #3"))) {
            inventorySlot = 14;
            loadoutSuffix = "3";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aLoad &bLoadout #4"))) {
            inventorySlot = 16;
            loadoutSuffix = "4";
        }

        if (inventorySlot == -1) {
            return;
        }

        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + loadoutSuffix);
    }


    @EventHandler
    public void onDelete(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        FileConfiguration config = LSMain.getInstance().getConfig();
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null) {
            return;
        }
        ItemMeta meta = clicked.getItemMeta();
        if (meta == null) {
            return;
        }
        String itemName = meta.getDisplayName();
        if (itemName == null) {
            return;
        }

        String deleteMat = config.getString("Settings.Delete.Material");
        if(deleteMat == null) {
            getServer().getConsoleSender().sendMessage(LSMain.color("&cDelete Material is not valid: " + deleteMat));
            return;
        }

        int inventorySlot = -1;
        String loadoutSuffix = "";
        if (itemName.equalsIgnoreCase(LSMain.color("&aDelete &bLoadout #1"))) {
            inventorySlot = 19;
            loadoutSuffix = "1";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aDelete &bLoadout #2"))) {
            inventorySlot = 21;
            loadoutSuffix = "2";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aDelete &bLoadout #3"))) {
            inventorySlot = 23;
            loadoutSuffix = "3";
        } else if (itemName.equalsIgnoreCase(LSMain.color("&aDelete &bLoadout #4"))) {
            inventorySlot = 25;
            loadoutSuffix = "4";
        }

        if (inventorySlot == -1) {
            return;
        }

        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + loadoutSuffix);
    }

    @EventHandler
    public void onLoadoutOpen(InventoryOpenEvent e) {
        FileConfiguration config = LSMain.getInstance().getConfig();
        String saveMat = config.getString("Settings.Save.Material");
        String loadMat = config.getString("Settings.Load.Material");
        String deleteMat = config.getString("Settings.Delete.Material");
        if (saveMat == null || loadMat == null || deleteMat == null) {
            getServer().getConsoleSender().sendMessage(LSMain.color("&cInvalid material(s) found"));
            return;
        }

        Player p = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        for (int i = 1; i <= 4; i++) {
            if (LoadoutHandler.hasSavedInventory(p, i)) {
                switch (i) {
                    case 1:
                        inv.setItem(10, createLoadItem(loadMat, "1"));
                        inv.setItem(19, createDeleteItem(deleteMat, "1"));
                        break;
                    case 2:
                        inv.setItem(12, createLoadItem(loadMat, "2"));
                        inv.setItem(21, createDeleteItem(deleteMat, "2"));
                        break;
                    case 3:
                        inv.setItem(14, createLoadItem(loadMat, "3"));
                        inv.setItem(23, createDeleteItem(deleteMat, "3"));
                        break;
                    case 4:
                        inv.setItem(16, createLoadItem(loadMat, "4"));
                        inv.setItem(25, createDeleteItem(deleteMat, "4"));
                        break;
                }
            }
        }
    }

    private ItemStack createLoadItem(String material, String loadoutSuffix) {
        ItemStack loadItem = new ItemStack(Material.matchMaterial(material), 1);
        ItemMeta meta = loadItem.getItemMeta();
        meta.setDisplayName(LSMain.color("&aLoad &bLoadout #" + loadoutSuffix));
        loadItem.setItemMeta(meta);
        return loadItem;
    }

    private ItemStack createDeleteItem(String material, String loadoutSuffix) {
        ItemStack deleteItem = new ItemStack(Material.matchMaterial(material), 1);
        ItemMeta meta = deleteItem.getItemMeta();
        meta.setDisplayName(LSMain.color("&aDelete &bLoadout #" + loadoutSuffix));
        deleteItem.setItemMeta(meta);
        return deleteItem;
    }
}
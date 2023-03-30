package me.data.loadoutsaver.util;

import me.data.loadoutsaver.LSMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIs {
    public static void layoutGUI(Player p) {
        FileConfiguration config = LSMain.getInstance().getConfig();
        Inventory layoutInv = Bukkit.createInventory(null, 27, config.getString("Settings.GUIName"));

        String fillMat = config.getString("Settings.Frame.Material");
        Material fillmat = Material.getMaterial(fillMat);
        if(fillmat == null) {
            Bukkit.getLogger().info(LSMain.color("&cFrame Material is not valid: " + fillMat));
            return;
        }
        byte data = (byte) config.getInt("Settings.Frame.Data");

        ItemStack fill = new ItemStack(fillmat, 1, data);
        ItemMeta fill_meta = fill.getItemMeta();
        fill_meta.setDisplayName(LSMain.color(config.getString("Settings.FrameName")));
        fill.setItemMeta(fill_meta);

        String saveMat = config.getString("Settings.Save.Material");
        if(saveMat == null) {
            Bukkit.getLogger().info(LSMain.color("&cSave Material is not valid: " + saveMat));
            return;
        }

        ItemStack sConfirm = new ItemStack(Material.matchMaterial(saveMat), 1);
        ItemMeta sConfirmMeta = sConfirm.getItemMeta();
        sConfirmMeta.setDisplayName(LSMain.color("&aSave &bLoadout #1"));
        sConfirm.setItemMeta(sConfirmMeta);

        ItemStack slay2 = new ItemStack(Material.matchMaterial(saveMat), 1);
        ItemMeta slay2Meta = slay2.getItemMeta();
        slay2Meta.setDisplayName(LSMain.color("&aSave &bLoadout #2"));
        slay2.setItemMeta(slay2Meta);

        ItemStack slay3 = new ItemStack(Material.matchMaterial(saveMat), 1);
        ItemMeta slay3Meta = slay3.getItemMeta();
        slay3Meta.setDisplayName(LSMain.color("&aSave &bLoadout #3"));
        slay3.setItemMeta(slay3Meta);

        ItemStack slay4 = new ItemStack(Material.matchMaterial(saveMat), 1);
        ItemMeta slay4Meta = slay4.getItemMeta();
        slay4Meta.setDisplayName(LSMain .color("&aSave &bLoadout #4"));
        slay4.setItemMeta(slay4Meta);

        for (int i = 0; i < 27; i++)
            layoutInv.setItem(i, fill);
        layoutInv.setItem(1, sConfirm);
        layoutInv.setItem(3, slay2);
        layoutInv.setItem(5, slay3);
        layoutInv.setItem(7, slay4);

        p.openInventory(layoutInv);
    }
}

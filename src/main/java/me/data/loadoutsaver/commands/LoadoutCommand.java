package me.data.loadoutsaver.commands;

import me.data.loadoutsaver.LSMain;
import me.data.loadoutsaver.util.GUIs;
import me.data.loadoutsaver.util.LoadoutHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LoadoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Use this command as a player.");
            return true;
        }

        Player p = (Player) sender;
        FileConfiguration config = LSMain.getInstance().getConfig();

        if(!p.hasPermission("LS.All")) {
            p.sendMessage(LSMain.color("&cYou do not have permission to use this command."));
            return true;
        }

        if(args.length == 0) {
            p.sendMessage(LSMain.color("&cCorrect Usage: &c/loadout <gui/save/loadout/delete] [1/2/3/4]"));
            return true;
        }

        if(args.length == 1) {
            if(p.hasPermission("LS.GUI") || p.hasPermission("LS.all")) {
                if (args[0].equalsIgnoreCase("gui")) {
                    GUIs.layoutGUI(p);
                }
            }
            if(p.hasPermission("LS.save") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("save")) {
                    p.sendMessage(LSMain.color("&cCorrect usage: &c/loadout save [1/2/3/4]"));
                }
            }
            if(p.hasPermission("LS.load") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("load")) {
                    p.sendMessage(LSMain.color("&cCorrect usage: &c/loadout load [1/2/3/4]"));
                }
            }
            if (p.hasPermission("LS.delete") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("delete")) {
                    p.sendMessage(LSMain.color("&cCorrect usage: &c/loadout delete [1/2/3/4]"));
                }
            }
            return true;
        }

        if(args.length == 2) {
            if(p.hasPermission("LS.GUI") || p.hasPermission("LS.all")) {
                if (args[0].equalsIgnoreCase("gui")) {
                    GUIs.layoutGUI(p);
                }
            }
            if(p.hasPermission("LS.save") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("save")) {
                    if(args[1].equalsIgnoreCase("1")) {
                        LoadoutHandler.saveInventory(p, config.getString("Settings.FileSuffix") + "1");
                    } else if (args[1].equalsIgnoreCase("2")) {
                        LoadoutHandler.saveInventory(p, config.getString("Settings.FileSuffix") + "2");
                    } else if (args[1].equalsIgnoreCase("3")) {
                        LoadoutHandler.saveInventory(p, config.getString("Settings.FileSuffix") + "3");
                    } else if (args[1].equalsIgnoreCase("4")) {
                        LoadoutHandler.saveInventory(p, config.getString("Settings.FileSuffix") + "4");
                    }
                }
            }
            if(p.hasPermission("LS.load") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("load")) {
                    if(args[1].equalsIgnoreCase("1")) {
                        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + "1");
                    } else if (args[1].equalsIgnoreCase("2")) {
                        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + "2");
                    } else if (args[1].equalsIgnoreCase("3")) {
                        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + "3");
                    } else if (args[1].equalsIgnoreCase("4")) {
                        LoadoutHandler.loadInventory(p, config.getString("Settings.FileSuffix") + "4");
                    }
                }
            }
            if (p.hasPermission("LS.delete") || p.hasPermission("LS.all")) {
                if(args[0].equalsIgnoreCase("delete")) {
                    if(args[1].equalsIgnoreCase("1")) {
                        LoadoutHandler.deleteInventory(p, config.getString("Settings.FileSuffix") + "1");
                    } else if (args[1].equalsIgnoreCase("2")) {
                        LoadoutHandler.deleteInventory(p, config.getString("Settings.FileSuffix") + "2");
                    } else if (args[1].equalsIgnoreCase("3")) {
                        LoadoutHandler.deleteInventory(p, config.getString("Settings.FileSuffix") + "3");
                    } else if (args[1].equalsIgnoreCase("4")) {
                        LoadoutHandler.deleteInventory(p, config.getString("Settings.FileSuffix") + "4");
                    }
                }
            }
            return true;
        }

        return true;
    }
}

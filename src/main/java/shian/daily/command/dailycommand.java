package shian.daily.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import shian.daily.gui.dailygui;

public class dailycommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String lable,String args[]){

        Player p = (Player) sender;
        if(p.hasPermission("dailyreward.command")) {
            dailygui.gui(p);

        }
        return true;
    }
}

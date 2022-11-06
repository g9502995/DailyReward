package shian.daily.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shian.daily.gui.Dailygui;
/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */
public class dailycommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String lable,String args[]){

        Player p = (Player) sender;
        if(p.hasPermission("dailyreward.command")) {
            Dailygui.gui(p);

        }
        return true;
    }
}

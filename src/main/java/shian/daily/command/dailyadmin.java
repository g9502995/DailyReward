package shian.daily.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.gui.Dailygui;
import shian.daily.Main;
/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */
public class dailyadmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String args[]){
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(Main.class);
        Player p = (Player) sender;
        if(p.isOp()||p.hasPermission("dailyreward.admin")){
            if(pl.getConfig().get("reward")==null) {
                Dailygui.guiadmin(p);
            }else {
                Dailygui.guiadmin1(p);
            }
        }else{
            p.sendMessage("你無權利使用");
            return false;
        }


        return true;
    }
}

package shian.daily.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.gui.dailygui;
import shian.daily.main;

public class dailyadmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String args[]){
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(main.class);
        Player p = (Player) sender;
        if(p.isOp()||p.hasPermission("dailyreward.admin")){
            if(pl.getConfig().get("reward")==null) {
                dailygui.guiadmin(p);
            }else {
                dailygui.guiadmin1(p);
            }
        }else{
            p.sendMessage("你無權利使用");
            return false;
        }


        return true;
    }
}

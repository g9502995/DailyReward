package shian.daily;

import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.command.dailyadmin;
import shian.daily.command.dailycommand;
import shian.daily.listener.Dailylistener;
import shian.daily.config.Reward;

/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */
public class Main extends JavaPlugin {
    private static Main instance;
    public Reward rewards;



    @Override
    public void onEnable()

    {
        System.out.println("DailyReward:" + getDescription().getVersion() + "has been enable by:Shain");
        this.getConfig().options().copyDefaults(true);
        instance = this;
        this.rewards = new Reward(this);
        getCommand("dailyreward").setExecutor(new dailycommand());
        getCommand("dailyadmin").setExecutor(new dailyadmin());
        getServer().getPluginManager().registerEvents(new Dailylistener(),this);
        saveConfig();
        reloadConfig();

    }


    @Override
    public void onDisable()
    {

    }
    public static Main getInstance(){
        return instance;
    }
}

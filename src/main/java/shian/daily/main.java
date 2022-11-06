package shian.daily;

import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.command.dailyadmin;
import shian.daily.command.dailycommand;
import shian.daily.listener.dailylistener;
import shian.daily.config.reward;

public class main extends JavaPlugin {
    private static main instance;
    public reward rewards;



    @Override
    public void onEnable()

    {   this.getConfig().options().copyDefaults(true);
        instance = this;
        this.rewards = new reward(this);
        getCommand("daily").setExecutor(new dailycommand());
        getCommand("dy").setExecutor(new dailycommand());
        getCommand("dailyadmin").setExecutor(new dailyadmin());
        getServer().getPluginManager().registerEvents(new dailylistener(),this);
        saveConfig();
        reloadConfig();
    }


    @Override
    public void onDisable()
    {

    }
    public static main getInstance(){
        return instance;
    }
}

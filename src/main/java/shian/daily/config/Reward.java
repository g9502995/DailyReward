package shian.daily.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.Main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */
public class Reward {
    JavaPlugin pl = JavaPlugin.getProvidingPlugin(Main.class);
    private Main instance;
    public Reward(Main instance){
        this.instance = instance;
    }

    public boolean getAllowReward(Player player){
        long current = System.currentTimeMillis();
        String date = getDate(player);
        return timeset(date);
    }
    public boolean timeset(String date){
        Date date1 = new Date();
        String time1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        if(time1.equals(date)){
            return true;
        }else{
            return false;
        }
    }
    public void setReward(Player player){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFile());
        Date date1 = new Date();
        String time1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        cfg.set(player.getUniqueId() + ".Date",time1);
        try {
            cfg.save(getRewardFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getRewardFile(){
        return new File(pl.getDataFolder(),"rewards.yml");
    }
    public File getRewardFiles(){
        return new File(pl.getDataFolder(),"config.yml");
    }
    public String getDate(Player player){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFile());
        return cfg.getString(player.getUniqueId()+".Date");
    }
    public String getTitle(){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFiles());
        return cfg.getString("gifttitle");
    }
    public String getmessage(){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFiles());
        return cfg.getString("message");
    }
    public String getreceivedmessage(){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFiles());
        return cfg.getString("receivedmessage");
    }
    public int getsize(){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFiles());
        return cfg.getInt("size");
    }
    public void setBase64(String a){
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(getRewardFiles());
        cfg.set("reward" , a);
        try {
            cfg.save(getRewardFiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

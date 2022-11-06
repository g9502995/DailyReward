package shian.daily.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import shian.daily.Main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */

public class Dailylistener implements Listener {
    JavaPlugin pl = JavaPlugin.getProvidingPlugin(Main.class);

    @EventHandler
    public void check(InventoryClickEvent ck){
        String admin = "§e每日簽到禮包獎勵設置";
        Player p = (Player) ck.getWhoClicked();
        if(ck.getWhoClicked().getOpenInventory().getTitle().equals(Main.getInstance().rewards.getTitle())){
            ck.setCancelled(true);
        }
        if(ck.getRawSlot()>ck.getInventory().getSize()){
            return;
        }
        if(ck.getRawSlot()<0){
            return;
        }
        if(ck.getRawSlot()==34&&ck.getWhoClicked().getOpenInventory().getTitle().equals(Main.getInstance().rewards.getTitle())){
           if(Main.getInstance().rewards.getAllowReward(p) == true) {
               p.sendMessage(Main.getInstance().rewards.getreceivedmessage());
           }else {

               try {
                   String a = "確認";
                   String b = ChatColor.RED + "離開";
                   for (ItemStack item : fromBase64(pl.getConfig().getString("reward"), "11").getContents()) {

                           if (item == null) {
                               continue;
                           }
                           if (item.getItemMeta().getDisplayName().equals(a) || item.getItemMeta().getDisplayName().equals(b)) {
                               continue;
                           }
                           if(p.getInventory().firstEmpty()==-1){
                               p.getLocation().getWorld().dropItemNaturally(p.getLocation(),item);
                           }else{
                               p.getInventory().addItem(item);
                       }
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
               Main.getInstance().rewards.setReward(p);
               p.sendMessage(Main.getInstance().rewards.getmessage());
               p.closeInventory();
           }
        }
        if(ck.getRawSlot()==35&&ck.getWhoClicked().getOpenInventory().getTitle().equals(Main.getInstance().rewards.getTitle())){
            p.closeInventory();
        }
        if(ck.getWhoClicked().getOpenInventory().getTitle().equals(admin)&&ck.getRawSlot()==35){
            p.closeInventory();
        }
        if(ck.getWhoClicked().getOpenInventory().getTitle().equals(admin)&&ck.getRawSlot()==34){

                String a = "確認";
                String b = ChatColor.RED + "離開";
                for (ItemStack item : ck.getInventory()) {
                    if (item == null) {
                        continue;
                    }
                    if (item.getItemMeta().getDisplayName().equals(a) || item.getItemMeta().getDisplayName().equals(b)) {
                        continue;
                    }
                    p.getInventory().addItem(item);
                }
                Main.getInstance().rewards.setBase64(toBase64(ck.getInventory()));
                p.closeInventory();
            }
    }


    public static String toBase64(Inventory inv)
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(inv.getSize());
            for (int i = 0; i < inv.getSize(); i++)
            {
                dataOutput.writeObject(inv.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static Inventory fromBase64(String data, String name) throws IOException
    {
        try
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory r = Bukkit.getServer().createInventory(null, dataInput.readInt(), name);
            for (int i = 0; i < r.getSize(); i++)
            {
                r.setItem(i, (ItemStack)dataInput.readObject());
            }
            dataInput.close();
            return r;
        }
        catch (ClassNotFoundException e)
        {
            throw new IOException("Unable to decode class type.", e);
        }
    }
    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }
}

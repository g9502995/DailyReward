package shian.daily.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.Main;

import java.io.IOException;

import static shian.daily.listener.Dailylistener.fromBase64;
/**
 * 創世神插件DailyReward
 *
 * @author Shian
 * @date 2022/11/06
 */
public class Dailygui {

    public static void gui(Player player)
    {
        int a = Main.getInstance().rewards.getsize() * 9;
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(Main.class);
        pl.reloadConfig();
        String reward = "reward";
        if(pl.getConfig().get(reward)==null) {
        Inventory gui = Bukkit.createInventory(null, Main.getInstance().rewards.getsize() * 9, Main.getInstance().rewards.getTitle());
        ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();
        ItemMeta barriers = (ItemMeta) barrier.getItemMeta();
        barriers.setDisplayName(ChatColor.RED + "離開");
        barrier.setItemMeta(barriers);
        playerheads.setDisplayName("領取");
        playerhead.setItemMeta(playerheads);

        gui.setItem(a-2, playerhead);
        gui.setItem(a-1, barrier);
        player.openInventory(gui);
    }else{
            try {
                Inventory gui = fromBase64(pl.getConfig().getString("reward"), Main.getInstance().rewards.getTitle());
                ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
                ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();

                playerheads.setDisplayName("領取");
                playerhead.setItemMeta(playerheads);
                gui.setItem(a-2, playerhead);
                player.openInventory(gui);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void guiadmin(Player player)
    {
        int a = Main.getInstance().rewards.getsize() * 9;
        Inventory gui = Bukkit.createInventory(null, 4 * 9, "§e每日簽到禮包獎勵設置");
        ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();
        ItemMeta barriers = (ItemMeta)  barrier.getItemMeta();
        barriers.setDisplayName(ChatColor.RED + "離開");
        barrier.setItemMeta(barriers);
        playerheads.setDisplayName("確認");
        playerhead.setItemMeta(playerheads);
        gui.setItem(a-2, playerhead);
        gui.setItem(a-1,barrier);
        player.openInventory(gui);
    }

    public static void guiadmin1(Player player)
    {
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(Main.class);
        try {
            Inventory gui = fromBase64(pl.getConfig().getString("reward"),"§e每日簽到禮包獎勵設置");
            player.openInventory(gui);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

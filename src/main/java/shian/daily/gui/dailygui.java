package shian.daily.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import shian.daily.listener.dailylistener;
import shian.daily.main;

import java.io.IOException;

import static shian.daily.listener.dailylistener.fromBase64;

public class dailygui {

    public static void gui(Player player)
    {
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(main.class);
        pl.reloadConfig();
        if(pl.getConfig().get("reward")==null) {
        Inventory gui = Bukkit.createInventory(null, 4 * 9, main.getInstance().rewards.getTitle());
        ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();
        ItemMeta barriers = (ItemMeta) barrier.getItemMeta();
        barriers.setDisplayName(ChatColor.RED + "離開");
        barrier.setItemMeta(barriers);
        playerheads.setDisplayName("領取");
        playerhead.setItemMeta(playerheads);
        gui.setItem(34, playerhead);
        gui.setItem(35, barrier);
        player.openInventory(gui);
    }else{
            try {
                Inventory gui = fromBase64(pl.getConfig().getString("reward"),main.getInstance().rewards.getTitle());
                ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
                ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();

                playerheads.setDisplayName("領取");
                playerhead.setItemMeta(playerheads);
                gui.setItem(34, playerhead);
                player.openInventory(gui);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void guiadmin(Player player)
    {
        Inventory gui = Bukkit.createInventory(null, 4 * 9, "§e每日簽到禮包獎勵設置");
        ItemStack playerhead = new ItemStack(Material.DIAMOND, 1);
        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta playerheads = (ItemMeta) playerhead.getItemMeta();
        ItemMeta barriers = (ItemMeta)  barrier.getItemMeta();
        barriers.setDisplayName(ChatColor.RED + "離開");
        barrier.setItemMeta(barriers);
        playerheads.setDisplayName("確認");
        playerhead.setItemMeta(playerheads);
        gui.setItem(34, playerhead);
        gui.setItem(35,barrier);
        player.openInventory(gui);
    }

    public static void guiadmin1(Player player)
    {
        JavaPlugin pl = JavaPlugin.getProvidingPlugin(main.class);
        try {
            Inventory gui = fromBase64(pl.getConfig().getString("reward"),"§e每日簽到禮包獎勵設置");
            player.openInventory(gui);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

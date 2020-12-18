package club.rigox.scoreboard.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Console {

    public static String color (String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void debug(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&9DEBUG &7| &fScoreboardAPI&f] %s", str)));
    }

    public static void warn(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&eWARN &7| &fScoreboardAPI&f] %s", str)));
    }

    public static void info(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&aINFO &7| &fScoreboardAPI&f] %s", str)));
    }
}
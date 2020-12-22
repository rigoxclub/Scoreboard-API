package club.rigox.scoreboard.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Console Class
 */
public class Console {
    /**
     * Method to transform messages with colors.
     * @param str String to format.
     * @return Message with formatted colors.
     */
    public static String color (String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Method to send an debug message to Server console.
     * @param str warn message
     */
    public static void debug(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&9DEBUG &7| &fScoreboardAPI&f] %s", str)));
    }

    /**
     * Method to send a warn message to Server console.
     * @param str warn message
     */
    public static void warn(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&eWARN &7| &fScoreboardAPI&f] %s", str)));
    }

    /**
     * Method to send a information message to Server console.
     * @param str info message
     */
    public static void info(String str) {
        Bukkit.getConsoleSender().sendMessage(color(String.format("&f[&aINFO &7| &fScoreboardAPI&f] %s", str)));
    }

    /**
     * Method to parseFields on Scoreboard strings
     *
     * @param field Message to PlaceholderAPI parse on.
     * @param p Player to parse Placeholders.
     * @return parseField string.
     */
    public static String parseField(String field, Player p) {
        return ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(p, field));
    }
}
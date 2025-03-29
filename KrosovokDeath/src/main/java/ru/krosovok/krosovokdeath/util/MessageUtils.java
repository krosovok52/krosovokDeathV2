package ru.krosovok.krosovokdeath.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtils {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin) {
        MessageUtils.plugin = plugin;
    }

    public static void sendStartupMessage() {
        String startupMessage = "§x§6§4§0§8§F§B┏ §x§F§6§3§5§D§2§lK§x§F§3§3§B§D§0§lr§x§F§0§4§1§C§F§lo§x§E§C§4§7§C§D§ls§x§E§9§4§D§C§C§lo§x§E§6§5§3§C§A§lv§x§E§3§5§A§C§9§lo§x§D§F§6§0§C§7§lk§x§D§C§6§6§C§5§lD§x§D§9§6§C§C§4§le§x§D§6§7§2§C§2§la§x§D§2§7§8§C§1§lt§x§C§F§7§E§B§F§lh §x§f§f§0§a§a§cv" + plugin.getDescription().getVersion() + " §aактивирован!\n" +
                "§x§6§4§0§8§F§B┣ §fРазработчик: §x§f§f§0§a§a§ckrosov_ok\n" +
                "§x§6§4§0§8§F§B┗ §fМой Телеграм канал: §x§f§f§0§a§a§ct.me/programsKrosovok\n";

        plugin.getLogger().info(ChatColor.stripColor(startupMessage.replace("§x§6§4§0§8§F§B", "").replace("§x§f§f§0§a§a§c", "")));

        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.hasPermission("krosovokdeath.notify"))
                .forEach(p -> {
                    p.sendMessage(startupMessage.split("\n"));
                    p.spigot().sendMessage(new ComponentBuilder("")
                            .append("§7[§aНажмите для получения помощи§7]")
                            .color(net.md_5.bungee.api.ChatColor.GRAY)
                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/kdeath help"))
                            .create());
                    playSound(p, Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 1.5f);
                });
    }

    public static void sendShutdownMessage() {
        String message = "§x§D§9§3§6§F§3§lk§x§D§2§4§F§E§7§ld §cотключен";
        broadcastToPermitted("krosovokdeath.notify", message);
        plugin.getLogger().info(ChatColor.stripColor(message));
    }

    public static void sendHelp(CommandSender sender) {
        String pluginInfo = "§x§6§4§0§8§F§B┏ §x§F§6§3§5§D§2§lK§x§F§3§3§B§D§0§lr§x§F§0§4§1§C§F§lo§x§E§C§4§7§C§D§ls§x§E§9§4§D§C§C§lo§x§E§6§5§3§C§A§lv§x§E§3§5§A§C§9§lo§x§D§F§6§0§C§7§lk§x§D§C§6§6§C§5§lD§x§D§9§6§C§C§4§le§x§D§6§7§2§C§2§la§x§D§2§7§8§C§1§lt§x§C§F§7§E§B§F§lh §x§f§f§0§a§a§cv" + plugin.getDescription().getVersion() + "\n" +
                "§x§6§4§0§8§F§B┣ §fАвтор: §x§f§f§0§a§a§ckrosov_ok\n" +
                "§x§6§4§0§8§F§B┗ §fТелеграм: §x§f§f§0§a§a§ct.me/programsKrosovok";

        StringBuilder commands = new StringBuilder("§x§E§3§8§1§3§1Доступные команды:\n");
        if (sender.hasPermission("krosovokdeath.reload")) {
            commands.append("§e/kdeath reload §7- Перезагрузить конфиг\n");
        }
        if (sender.hasPermission("krosovokdeath.toggle")) {
            commands.append("§e/kdeath toggle §7- Вкл/выкл уведомления\n");
        }
        commands.append("§e/kdeath help §7- Показать это сообщение\n");
        commands.append(" \n");
        commands.append("§x§6§4§0§8§F§BAliases: §7[kd]");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(pluginInfo.split("\n"));

            player.spigot().sendMessage(new ComponentBuilder("")
                    .append("§7[§x§6§4§0§8§F§BНажмите для перехода в Telegram§7]\n")
                    .color(net.md_5.bungee.api.ChatColor.of("#6408FB"))
                    .underlined(true)
                    .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://t.me/programsKrosovok"))
                    .create());

            player.sendMessage(commands.toString());
            playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1.2f);
        } else {
            sender.sendMessage(pluginInfo.split("\n"));
            sender.sendMessage(commands.toString());
        }
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage("§x§D§9§3§6§F§3§lk§x§D§2§4§F§E§7§ld §f| " + message);
    }

    public static void broadcastToPermitted(String permission, String message) {
        Bukkit.getOnlinePlayers().stream()
                .filter(p -> p.hasPermission(permission))
                .forEach(p -> p.sendMessage("§x§D§9§3§6§F§3§lk§x§D§2§4§F§E§7§ld §f| " + message));
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public static void playSound(CommandSender sender, Sound sound, float volume, float pitch) {
        if (sender instanceof Player) {
            ((Player) sender).playSound(((Player) sender).getLocation(), sound, volume, pitch);
        }
    }
}
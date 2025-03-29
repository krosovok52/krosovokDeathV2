package ru.krosovok.krosovokdeath.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import ru.krosovok.krosovokdeath.KrosovokDeath;
import ru.krosovok.krosovokdeath.manager.DeathManager;
import ru.krosovok.krosovokdeath.util.MessageUtils;

public class CommandHandler implements CommandExecutor {

    private final KrosovokDeath plugin;
    private final DeathManager deathManager;

    public CommandHandler(KrosovokDeath plugin, DeathManager deathManager) {
        this.plugin = plugin;
        this.deathManager = deathManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            MessageUtils.sendHelp(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                return handleReloadCommand(sender);
            case "toggle":
                return handleToggleCommand(sender);
            default:
                MessageUtils.sendMessage(sender, "Неизвестная команда. Используйте /kdeath help");
                return false;
        }
    }

    private boolean handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("krosovokdeath.reload")) {
            MessageUtils.sendMessage(sender, "У вас нет прав на эту команду!");
            return true;
        }

        plugin.reloadConfig();
        deathManager.reloadConfig(plugin.getConfig());
        MessageUtils.playSound(sender, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.5f);

        String status = plugin.getConfig().getBoolean("notifications.enabled") ? "§aВКЛ" : "§cВЫКЛ";
        MessageUtils.sendMessage(sender, "Конфиг перезагружен! Уведомления: " + status);
        return true;
    }

    private boolean handleToggleCommand(CommandSender sender) {
        if (!sender.hasPermission("krosovokdeath.toggle")) {
            MessageUtils.sendMessage(sender, "У вас нет прав на эту команду!");
            return true;
        }

        boolean newState = !plugin.getConfig().getBoolean("notifications.enabled");
        plugin.getConfig().set("notifications.enabled", newState);
        plugin.saveConfig();

        String toggleMessage = "Уведомления " + (newState ? "§aвключены" : "§cвыключены") +
                " §fигроком §e" + sender.getName();
        MessageUtils.broadcastToPermitted("krosovokdeath.notify", toggleMessage);

        if (newState) {
            MessageUtils.playSound(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.0f);
        }

        MessageUtils.sendMessage(sender, newState ? "§aУведомления включены" : "§cУведомления выключены");
        return true;
    }
}
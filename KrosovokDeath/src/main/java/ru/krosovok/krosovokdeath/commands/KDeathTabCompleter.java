package ru.krosovok.krosovokdeath.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import java.util.*;

public class KDeathTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

        completions.add("help");

        if (sender.hasPermission("krosovokdeath.reload")) {
            completions.add("reload");
        }
        if (sender.hasPermission("krosovokdeath.toggle")) {
            completions.add("toggle");
        }

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }

        return Collections.emptyList();
    }
}
package ru.krosovok.krosovokdeath;

import org.bukkit.plugin.java.JavaPlugin;
import ru.krosovok.krosovokdeath.commands.CommandHandler;
import ru.krosovok.krosovokdeath.commands.KDeathTabCompleter;
import ru.krosovok.krosovokdeath.listener.DeathListener;
import ru.krosovok.krosovokdeath.manager.DeathManager;
import ru.krosovok.krosovokdeath.util.MessageUtils;
import ru.krosovok.krosovokdeath.util.TimeUtils;

public final class KrosovokDeath extends JavaPlugin {

    private DeathManager deathManager;
    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        TimeUtils.init(getConfig());
        MessageUtils.init(this);

        deathManager = new DeathManager(this);
        commandHandler = new CommandHandler(this, deathManager);

        getServer().getPluginManager().registerEvents(new DeathListener(deathManager), this);
        getCommand("kdeath").setExecutor(commandHandler);
        getCommand("kdeath").setTabCompleter(new KDeathTabCompleter());

        MessageUtils.sendStartupMessage();
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage();
    }

    public DeathManager getDeathManager() {
        return deathManager;
    }
}

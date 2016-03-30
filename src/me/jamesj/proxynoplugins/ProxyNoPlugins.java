package me.jamesj.proxynoplugins;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 30/03/2016.
 */
public class ProxyNoPlugins extends Plugin {
    private List<String> blockedCommands = new ArrayList<>();
    private String prefix, message;
    @Override
    public void onEnable() {
        try (InputStream is = getResourceAsStream("config.yml");
             OutputStream os = new FileOutputStream(new File(getDataFolder(), "config.yml"))) {
             ByteStreams.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Configuration configuration;
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        blockedCommands = configuration.getStringList("protected-commands");
        message = configuration.getString("message");
        prefix = configuration.getString("prefix");
        getProxy().getPluginManager().registerListener(this, new CommandListener(this));
    }

    @Override
    public void onDisable() {
        blockedCommands = null;
        prefix = null;
        message = null;
    }

    public List<String> getBlockedCommands() {
        return blockedCommands;
    }

    public String getMessage() {
        return message;
    }

    public String getPrefix() {
        return prefix;
    }
}

package me.jamesj.proxynoplugins;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by James on 30/03/2016.
 */
public class CommandListener implements Listener {

    private final ProxyNoPlugins instance;
    public CommandListener(ProxyNoPlugins instance){
        this.instance = instance;
    }
    @EventHandler
    public void onCommand(ChatEvent e) {
        if (e.isCommand()) {
            ProxiedPlayer p = (ProxiedPlayer)e.getSender();
            if (!p.hasPermission("plugins.view")) {
                String command = e.getMessage().split(" ")[0];
                if(instance.getBlockedCommands().contains(command.toLowerCase())){
                    e.setCancelled(true);
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', instance.getMessage())));
                }
            }
        }
    }


}

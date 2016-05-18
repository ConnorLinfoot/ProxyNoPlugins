package me.jamesj.proxynoplugins;

import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabListener implements Listener {
	private ProxyNoPlugins instance;

	public TabListener(ProxyNoPlugins instance) {
		this.instance = instance;
	}

	@EventHandler
	public void onTabComplete(TabCompleteEvent event) {
		String cursor = event.getCursor();
		if (cursor.toLowerCase().startsWith("/")) {
			String command = cursor.split(" ")[0];
			if (instance.getBlockedCommands().contains(command.toLowerCase()))
				event.setCancelled(true);
		}
	}

}

package techtoolbox.Muter;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public String permission;
	
	public void onEnable() {
		
		// Get permission from configuration
		permission = getConfig().getString("permission");
		
		// Register listener
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		// Save configuration file if it doesn't already exist
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
		    saveDefaultConfig();
		}
	}
	
	public void onDisable() {
		
	}
	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
              
		// Get player who spoke
        Player player = event.getPlayer();  
        
        // Check is player has permission (from configuration)
        if (!(player.hasPermission(permission))) {
        	
        	// Cancel message
        	event.setCancelled(true);
        	
        	// Send message from configuration
        	player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("muted-message")));
        }
    }
}

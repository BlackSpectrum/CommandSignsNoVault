package eu.blackspectrum.commandsigns;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.blackspectrum.commandsigns.config.Messaging;
import eu.blackspectrum.commandsigns.listener.CommandListener;
import eu.blackspectrum.commandsigns.listener.EventListener;
import eu.blackspectrum.commandsigns.util.PlayerState;
import eu.blackspectrum.commandsigns.util.SignLoader;
import eu.blackspectrum.commandsigns.util.SignText;

public class CommandSigns extends JavaPlugin
{


	// Listeners
	private final EventListener						listener		= new EventListener( this );
	public CommandListener							commandExecutor	= new CommandListener( this );

	// Plugin variables
	public final Map<Location, SignText>			activeSigns		= new HashMap<Location, SignText>();
	public final Map<OfflinePlayer, PlayerState>	playerStates	= new HashMap<OfflinePlayer, PlayerState>();
	public final Map<OfflinePlayer, SignText>		playerText		= new HashMap<OfflinePlayer, SignText>();

	public SignLoader								loader			= new SignLoader( this );
	public Messaging								messenger		= new Messaging( this );




	public File getUpdateFile() {
		return new File( this.getServer().getUpdateFolderFile().getAbsoluteFile(), super.getFile().getName() );
	}




	public boolean hasPermission( final CommandSender player, final String string ) {
		return this.hasPermission( player, string, true );
	}




	public boolean hasPermission( final CommandSender player, final String string, final boolean notify ) {
		boolean perm;
		perm = player.hasPermission( string );

		if ( perm == false && notify )
			this.messenger.sendMessage( player, "failure.no_perms" );
		return perm;
	}




	public void load() {
		this.messenger.load();
		this.loader.loadFile();
	}




	@Override
	public void onDisable() {
		this.loader.saveFile();
	}




	@Override
	public void onEnable() {
		this.load();
		final PluginManager pm = this.getServer().getPluginManager();
		this.getCommand( "commandsigns" ).setExecutor( this.commandExecutor );
		pm.registerEvents( this.listener, this );
	}

}
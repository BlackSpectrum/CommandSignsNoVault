package eu.blackspectrum.commandsigns.handler;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;

import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.SignExecutor;
import eu.blackspectrum.commandsigns.proxy.CommandSenderProxy;
import eu.blackspectrum.commandsigns.proxy.PlayerProxy;

public class CommandHandler extends Handler
{


	@Override
	public void handle( final SignExecutor e, String command, final boolean silent, final boolean negate ) {
		if ( command.startsWith( "/" ) || command.startsWith( "\\" ) )
		{
			boolean op = false;
			PermissionAttachment grant = null;
			final Player player = e.getPlayer();
			this.plugin = e.getPlugin();
			if ( command.startsWith( "/" ) )
			{
				command = command.substring( 1 );
				if ( command.length() == 0 )
					return;
				if ( player != null )
					try
					{
						if ( command.startsWith( "*" ) )
						{
							command = command.substring( 1 );
							if ( this.plugin.hasPermission( player, "commandsigns.use.super", false ) )
							{
								// Give player access to the '*' permission node
								// temporarily
								if ( player.hasPermission( "*" ) )
									grant = player.addAttachment( this.plugin, "*", true );
								this.run( this.plugin, player, command, silent );
							}
							else
							{
								if ( !silent )
									this.plugin.messenger.sendMessage( player, "cannot_use" );
								return;
							}
						}
						else if ( command.startsWith( "^" ) )
						{
							command = command.substring( 1 );
							if ( this.plugin.hasPermission( player, "commandsigns.use.super", false ) )
							{
								if ( !player.isOp() )
								{
									op = true;
									player.setOp( true );
								}
								this.run( this.plugin, player, command, silent );
							}
							else
							{
								if ( !silent )
									this.plugin.messenger.sendMessage( player, "cannot_use" );
								return;
							}
						}
						else if ( command.startsWith( "#" ) )
						{
							command = command.substring( 1 );
							if ( this.plugin.hasPermission( player, "commandsigns.use.super", false ) )
							{
								final ConsoleCommandSender ccs = this.plugin.getServer().getConsoleSender();
								final CommandSender cs = new CommandSenderProxy( ccs, player, silent );
								this.plugin.getServer().dispatchCommand( cs, command );
							}
							else
							{
								if ( !silent )
									this.plugin.messenger.sendMessage( player, "cannot_use" );
								return;
							}
						}
						else
							this.run( this.plugin, player, command, silent );
					}
					finally
					{
						if ( grant != null )
							player.removeAttachment( grant );
						if ( op )
							player.setOp( false );
					}
				else
				{
					if ( command.startsWith( "*" ) || command.startsWith( "^" ) || command.startsWith( "#" ) )
						command = command.substring( 1 );
					final ConsoleCommandSender ccs = this.plugin.getServer().getConsoleSender();
					final CommandSender cs = new CommandSenderProxy( ccs, silent );
					this.plugin.getServer().dispatchCommand( cs, command );
				}
			}
		}
	}




	private void run( final CommandSigns plugin, final Player p, final String command, final boolean silent ) {
		final CommandSender s = new PlayerProxy( p, silent );
		final PluginManager pm = Bukkit.getPluginManager();
		final PlayerCommandPreprocessEvent e = new PlayerCommandPreprocessEvent( p, "/" + command );
		pm.callEvent( e );
		if ( !e.isCancelled() )
			Bukkit.dispatchCommand( s, command );
	}

}

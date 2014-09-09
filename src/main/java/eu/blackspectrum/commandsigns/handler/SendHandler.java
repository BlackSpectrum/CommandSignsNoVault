package eu.blackspectrum.commandsigns.handler;

import org.bukkit.ChatColor;

import eu.blackspectrum.commandsigns.SignExecutor;

public class SendHandler extends Handler
{


	@Override
	public void handle( final SignExecutor e, String command, final boolean silent, final boolean negate ) {
		if ( e.getPlayer() != null && command.startsWith( "\\" ) )
		{
			command = command.substring( 1 );
			e.getPlayer().sendMessage( ChatColor.translateAlternateColorCodes( '&', command ) );
		}
	}

}

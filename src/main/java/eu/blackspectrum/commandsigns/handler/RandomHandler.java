package eu.blackspectrum.commandsigns.handler;

import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.SignExecutor;

public class RandomHandler extends Handler
{


	@Override
	public void handle( final SignExecutor e, final String command, final boolean silent, final boolean negate ) {
		if ( command.startsWith( "`" ) )
		{
			double amount = 0;
			try
			{
				amount = Double.parseDouble( command.substring( 1 ) );
			}
			catch ( final NumberFormatException ex )
			{
				return;
			}
			amount /= 100;
			if ( Math.random() < amount ^ negate )
				e.getRestrictions().push( true );
			else
			{
				e.getRestrictions().push( false );
				if ( !silent && e.getPlayer() != null )
					CommandSigns.get().messenger.sendMessage( e.getPlayer(), "restriction.bad_random" );
			}
		}

	}

}

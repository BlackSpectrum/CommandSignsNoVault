package eu.blackspectrum.commandsigns.handler;

import eu.blackspectrum.commandsigns.SignExecutor;

public class WaitHandler extends Handler
{


	@Override
	public void handle( final SignExecutor e, final String command, final boolean silent, final boolean negate ) {
		if ( command.startsWith( "%" ) )
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
			e.setWait( amount );
		}
	}

}

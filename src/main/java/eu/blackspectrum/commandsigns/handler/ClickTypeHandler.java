package eu.blackspectrum.commandsigns.handler;

import org.bukkit.event.block.Action;

import eu.blackspectrum.commandsigns.SignExecutor;

public class ClickTypeHandler extends Handler
{


	@Override
	public void handle( final SignExecutor e, final String command, final boolean silent, final boolean negate ) {
		if ( e.getPlayer() != null )
			if ( command.startsWith( ">>" ) )
			{
				if ( e.getAction() == Action.RIGHT_CLICK_BLOCK ^ negate )
					e.getRestrictions().push( true );
				else
					e.getRestrictions().push( false );
			}
			else if ( command.startsWith( "<<" ) )
				if ( e.getAction() == Action.LEFT_CLICK_BLOCK ^ negate )
					e.getRestrictions().push( true );
				else
					e.getRestrictions().push( false );
	}

}

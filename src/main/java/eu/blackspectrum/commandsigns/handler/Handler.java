package eu.blackspectrum.commandsigns.handler;

import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.SignExecutor;

public abstract class Handler
{


	protected CommandSigns	plugin;




	public abstract void handle( SignExecutor e, String command, boolean silent, boolean negate );

}

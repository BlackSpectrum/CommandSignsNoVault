package eu.blackspectrum.commandsigns;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import eu.blackspectrum.commandsigns.handler.ChatHandler;
import eu.blackspectrum.commandsigns.handler.ClickTypeHandler;
import eu.blackspectrum.commandsigns.handler.CommandHandler;
import eu.blackspectrum.commandsigns.handler.CooldownHandler;
import eu.blackspectrum.commandsigns.handler.Handler;
import eu.blackspectrum.commandsigns.handler.RandomHandler;
import eu.blackspectrum.commandsigns.handler.SendHandler;
import eu.blackspectrum.commandsigns.handler.WaitHandler;
import eu.blackspectrum.commandsigns.util.SignText;

public class SignExecutor
{


	private static Set<Handler>		handlers		= new HashSet<Handler>();
	static
	{
		registerHandler( new CooldownHandler() );
		registerHandler( new WaitHandler() );
		registerHandler( new ClickTypeHandler() );
		registerHandler( new SendHandler() );
		registerHandler( new CommandHandler() );
		registerHandler( new RandomHandler() );
		registerHandler( new ChatHandler() );
	}

	private final Action			action;

	boolean							isValid			= false;

	private LinkedList<String>		lines;

	private final Location			location;

	private final Player			player;
	private final Stack<Boolean>	restrictions	= new Stack<Boolean>();

	private final SignText			text;

	private double					wait;




	public static Set<Handler> getHandlers() {
		return handlers;
	}




	public static void registerHandler( final Handler handler ) {
		handlers.add( handler );
	}




	public static void setHandlers( final Set<Handler> handlers ) {
		SignExecutor.handlers = handlers;
	}




	public static void unregisterAll() {
		handlers.clear();
	}




	public SignExecutor(final Player player, final Location location, final Action action) {
		this.player = player;
		this.action = action;
		this.location = location;
		this.text = CommandSigns.get().activeSigns.get( location );
		if ( this.text != null && this.text.isEnabled() )
		{
			if ( player == null || player.hasPermission( "commandsigns.use.regular" ) )
				this.lines = this.parseCommandSign( player, location );
			else
				this.lines = new LinkedList<String>();
			this.isValid = true;
		}
	}




	public Action getAction() {
		return this.action;
	}




	public LinkedList<String> getLines() {
		return this.lines;
	}




	public Location getLocation() {
		return this.location;
	}




	public Player getPlayer() {
		return this.player;
	}




	public CommandSigns getPlugin() {
		return CommandSigns.get();
	}




	public Stack<Boolean> getRestrictions() {
		return this.restrictions;
	}




	public SignText getText() {
		return this.text;
	}




	public double getWait() {
		return this.wait;
	}




	public boolean runLines() {
		if ( !this.isValid )
			return false;
		this.wait = 0;
		while ( this.wait == 0 && !this.lines.isEmpty() )
		{
			String currentLine = this.lines.poll();
			if ( currentLine.equals( "" ) )
				continue;

			boolean silent = false;
			boolean negate = false;
			boolean meta = false;
			do
			{
				meta = false;
				// The '-' delimiter ends the current restriction block
				if ( currentLine.startsWith( "-" ) && !this.restrictions.isEmpty() )
				{
					this.restrictions.pop();
					currentLine = currentLine.substring( 1 );
					meta = true;
				}
				// If the restriction begins with a ?, make it silent
				else if ( currentLine.startsWith( "?" ) )
				{
					silent = true;
					currentLine = currentLine.substring( 1 );
					meta = true;
				}
				// If the restriction starts with a !, negate the block
				else if ( currentLine.startsWith( "!" ) )
				{
					negate = true;
					currentLine = currentLine.substring( 1 );
					meta = true;
				}
			} while ( meta == true );

			// If an empty line is negated, invert the top of the stack. (For
			// else)
			if ( currentLine.equals( "" ) )
			{
				if ( negate && !this.restrictions.isEmpty() )
					this.restrictions.push( !this.restrictions.pop() );
				continue;
			}

			// If a restriction block is denied, skip to next line
			if ( !this.restrictions.isEmpty() && this.restrictions.peek().equals( false ) )
				continue;

			for ( final Handler h : handlers )
				h.handle( this, currentLine, silent, negate );

		}
		if ( this.wait != 0 )
			CommandSigns.get().getServer().getScheduler().scheduleSyncDelayedTask( CommandSigns.get(), new Runnable() {


				@Override
				public void run() {
					SignExecutor.this.runLines();
				}

			}, (long) ( this.wait * 20 ) );
		return true;
	}




	public void setWait( final double wait ) {
		this.wait = wait;
	}




	private LinkedList<String> parseCommandSign( final Player player, final Location loc ) {
		final LinkedList<String> commandList = new LinkedList<String>();
		final SignText commandSign = CommandSigns.get().activeSigns.get( this.location );
		for ( String line : commandSign )
		{
			line = line.replaceAll( "(?iu)<blockx>", "" + loc.getX() );
			line = line.replaceAll( "(?iu)<blocky>", "" + loc.getY() );
			line = line.replaceAll( "(?iu)<blockz>", "" + loc.getZ() );
			line = line.replaceAll( "(?iu)<world>", loc.getWorld().getName() );
			if ( line.toLowerCase().contains( "<near>" ) )
			{
				Player clp = null;
				final double dist = Double.MAX_VALUE;
				for ( final Player p : loc.getWorld().getPlayers() )
					if ( p.getWorld().equals( loc.getWorld() ) )
						if ( p.getLocation().distanceSquared( loc ) < dist )
							clp = p;
				if ( clp != null )
					line = line.replaceAll( "(?iu)<near>", clp.getName() );
			}
			while ( line.toLowerCase().contains( "<randomname>" ) )
			{
				final Player[] randoms = CommandSigns.get().getServer().getOnlinePlayers();
				final int rand = (int) Math.round( Math.random() * ( randoms.length - 1 ) );
				line = line.replaceFirst( "(?iu)<randomname>", randoms[rand].getName() );
			}
			if ( player != null )
			{
				line = line.replaceAll( "(?iu)<x>", "" + player.getLocation().getBlockX() );
				line = line.replaceAll( "(?iu)<y>", "" + player.getLocation().getBlockY() );
				line = line.replaceAll( "(?iu)<z>", "" + player.getLocation().getBlockZ() );
				line = line.replaceAll( "(?iu)<name>", "" + player.getName() );
				line = line.replaceAll( "(?iu)<player>", "" + player.getName() );
				final String[] addr = player.getAddress().toString().split( "/" );
				line = line.replaceAll( "(?iu)<ip>", "" + addr[addr.length - 1].split( ":" )[0] );
				line = line.replaceAll( "(?iu)<display>", player.getDisplayName() );
			}
			commandList.add( line );
		}
		return commandList;
	}

}

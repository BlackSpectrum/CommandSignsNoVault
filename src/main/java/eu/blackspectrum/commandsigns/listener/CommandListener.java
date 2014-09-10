package eu.blackspectrum.commandsigns.listener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.util.PlayerState;
import eu.blackspectrum.commandsigns.util.SignText;

public class CommandListener implements CommandExecutor
{

private static CommandListener instance = new CommandListener();
	
	public static CommandListener get(){
		return instance;
	}
	
	private boolean	registered	= false;




	public void register() {
		if ( !this.registered )
		{
			CommandSigns.get().getCommand( "commandsigns" ).setExecutor( this );
			this.registered = true;
		}
	}

	public void finishEditing( final Player player ) {
		CommandSigns.get().playerStates.remove( player );
		CommandSigns.get().playerText.remove( player );
		CommandSigns.get().messenger.sendMessage( player, "success.done_editing" );
	}




	@Override
	public boolean onCommand( final CommandSender sender, final Command cmd, final String commandLabel, final String[] args ) {
		if ( cmd.getName().equalsIgnoreCase( "commandsigns" ) )
		{
			if ( args.length < 1 || args[0].equalsIgnoreCase( "help" ) )
				// Messaging.sendMessage(sender, "usage");
				return false;
			Player tp = null;
			if ( sender instanceof Player )
				tp = (Player) sender;
			final Player player = tp;
			final String command = args[0].toLowerCase();
			Pattern pattern = Pattern.compile( "(line|l)?(\\d+)" );
			Matcher matcher = pattern.matcher( command );
			if ( matcher.matches() )
				return this.add( sender, player, Integer.parseInt( matcher.group( 2 ) ), args );
			else if ( command.equals( "batch" ) )
				return this.batch( sender, player, args );
			else if ( command.equals( "clear" ) )
				return this.clear( sender, player, args );
			else if ( command.equals( "copy" ) )
				return this.copy( sender, player, args );
			else if ( command.equals( "edit" ) )
				return this.edit( sender, player, args );
			else if ( command.equals( "insert" ) && args.length > 1 )
			{
				pattern = Pattern.compile( "(line|l)?(\\d+)" );
				matcher = pattern.matcher( args[1].toLowerCase() );
				if ( matcher.matches() )
					return this.insert( sender, player, Integer.parseInt( matcher.group( 2 ) ), args );
			}
			else if ( command.equals( "read" ) )
				return this.read( sender, player, args );
			else if ( command.equals( "redstone" ) )
				return this.redstone( sender, player, args );
			else if ( command.equals( "reload" ) )
				return this.reload( sender, player, args );
			else if ( command.equals( "remove" ) )
				return this.remove( sender, player, args );
			else if ( command.equals( "save" ) )
				return this.save( sender, player, args );
			else if ( command.equals( "toggle" ) )
				return this.toggle( sender, player, args );
			else if ( command.equals( "view" ) )
				return this.view( sender, player, args );
			else
			{
				CommandSigns.get().messenger.sendMessage( sender, "failure.wrong_syntax" );
				return true;
			}
		}
		return false;
	}




	private void clipboard( final CommandSender sender, final Player player, final int lineNumber, final int textStart, final String[] args ) {
		if ( lineNumber < 1 )
			CommandSigns.get().messenger.sendMessage( player, "failure.invalid_line" );
		else
		{
			if ( CommandSigns.get().playerStates.get( player ) == PlayerState.EDIT_SELECT )
				CommandSigns.get().messenger.sendMessage( player, "failure.must_select" );
			SignText text = CommandSigns.get().playerText.get( player );
			if ( text == null )
			{
				text = new SignText( player.getName(), false );
				CommandSigns.get().playerText.put( player, text );
			}
			final String line = StringUtils.join( args, " ", textStart, args.length );
			if ( line.startsWith( "/*" ) && !CommandSigns.get().hasPermission( player, "commandsigns.create.super", false ) )
				CommandSigns.get().messenger.sendMessage( player, "failure.no_super" );
			if ( ( line.startsWith( "/^" ) || line.startsWith( "/#" ) )
					&& !CommandSigns.get().hasPermission( player, "commandsigns.create.op", false ) )
				CommandSigns.get().messenger.sendMessage( player, "failure.no_op" );
			text.setLine( lineNumber, line );
			CommandSigns.get().messenger.sendRaw( player, "success.line_print", new String[] { "NUMBER", "LINE" }, new String[] {
					"" + lineNumber, line } );
		}
	}




	protected boolean add( final CommandSender sender, final Player player, final int lineNumber, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.regular" ) )
		{
			this.clipboard( sender, player, lineNumber, 1, args );
			if ( CommandSigns.get().playerStates.get( player ) != PlayerState.EDIT )
			{
				CommandSigns.get().playerStates.put( player, PlayerState.ENABLE );
				CommandSigns.get().messenger.sendMessage( player, "progress.add" );
			}
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean batch( final CommandSender sender, final Player player, final String[] args ) {
		PlayerState ps = CommandSigns.get().playerStates.get( player );
		if ( ps == null )
		{
			CommandSigns.get().messenger.sendMessage( player, "failure.not_in_mode" );
			return false;
		}
		switch ( ps ) {
		case REMOVE:
			player.sendMessage( "Switched to batch remove mode." );
			ps = PlayerState.BATCH_REMOVE;
			break;
		case BATCH_REMOVE:
			player.sendMessage( "Switched to single remove mode." );
			ps = PlayerState.REMOVE;
			break;
		case ENABLE:
			player.sendMessage( "Switched to batch enable mode." );
			ps = PlayerState.BATCH_ENABLE;
			break;
		case BATCH_ENABLE:
			player.sendMessage( "Switched to single enable mode." );
			ps = PlayerState.ENABLE;
			break;
		case READ:
			player.sendMessage( "Switched to batch read mode." );
			ps = PlayerState.BATCH_READ;
			break;
		case BATCH_READ:
			player.sendMessage( "Switched to single read mode." );
			ps = PlayerState.READ;
			break;
		case TOGGLE:
			player.sendMessage( "Switched to batch toggle mode." );
			ps = PlayerState.BATCH_TOGGLE;
			break;
		case BATCH_TOGGLE:
			player.sendMessage( "Switched to single toggle mode." );
			ps = PlayerState.TOGGLE;
			break;
		case REDSTONE:
			player.sendMessage( "Switched to batch redstone mode." );
			ps = PlayerState.BATCH_REDSTONE;
			break;
		case BATCH_REDSTONE:
			player.sendMessage( "Switched to single redstone mode." );
			ps = PlayerState.REDSTONE;
			break;
		default:
			CommandSigns.get().messenger.sendMessage( player, "failure.no_batch" );
		}
		CommandSigns.get().playerStates.put( player, ps );
		return true;
	}




	protected boolean clear( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.remove" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.remove( player );
			CommandSigns.get().playerText.remove( player );
			CommandSigns.get().messenger.sendMessage( player, "success.cleared" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean copy( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.regular" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.put( player, PlayerState.COPY );
			CommandSigns.get().messenger.sendMessage( player, "progress.copy" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean edit( final CommandSender sender, final Player player, final String[] args ) {
		if ( CommandSigns.get().hasPermission( sender, "commandsigns.edit", false ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT_SELECT || ps == PlayerState.EDIT )
				this.finishEditing( player );
			else
			{
				CommandSigns.get().playerStates.put( player, PlayerState.EDIT_SELECT );
				CommandSigns.get().playerText.remove( player );
				CommandSigns.get().messenger.sendMessage( player, "progress.select_sign" );
			}
		}
		return true;
	}




	protected boolean insert( final CommandSender sender, final Player player, final int lineNumber, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.regular" ) )
		{
			this.clipboard( sender, player, lineNumber, 2, args );
			if ( CommandSigns.get().playerStates.get( player ) != PlayerState.EDIT )
			{
				CommandSigns.get().playerStates.put( player, PlayerState.INSERT );
				CommandSigns.get().messenger.sendMessage( player, "progress.add" );
			}
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean read( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.regular" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.put( player, PlayerState.READ );
			CommandSigns.get().messenger.sendMessage( player, "progress.read" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean redstone( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.redstone" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.put( player, PlayerState.REDSTONE );
			CommandSigns.get().messenger.sendMessage( player, "progress.redstone" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean reload( final CommandSender sender, final Player player, final String[] args ) {
		if ( CommandSigns.get().hasPermission( sender, "commandsigns.reload", false ) )
		{
			CommandSigns.get().load();
			CommandSigns.get().messenger.sendMessage( sender, "success.reloaded" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean remove( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.remove" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.put( player, PlayerState.REMOVE );
			CommandSigns.get().messenger.sendMessage( player, "progress.remove" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean save( final CommandSender sender, final Player player, final String[] args ) {
		if ( CommandSigns.get().hasPermission( sender, "commandsigns.save", false ) )
		{
			CommandSigns.get().loader.saveFile();
			CommandSigns.get().messenger.sendMessage( sender, "success.saved" );
		}
		return true;
	}




	protected boolean toggle( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.toggle" ) )
		{
			final PlayerState ps = CommandSigns.get().playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			CommandSigns.get().playerStates.put( player, PlayerState.TOGGLE );
			CommandSigns.get().messenger.sendMessage( player, "progress.toggle" );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean view( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			CommandSigns.get().messenger.sendMessage( sender, "failure.player_only" );
		if ( CommandSigns.get().hasPermission( player, "commandsigns.create.regular" ) )
		{
			final SignText text = CommandSigns.get().playerText.get( player );
			if ( text == null )
				player.sendMessage( "No text in clipboard" );
			else
			{
				int i = 1;
				for ( final String s : text )
				{
					if ( !s.equals( "" ) )
						player.sendMessage( i + ": " + s );
					i++;
				}
			}
			CommandSigns.get().playerStates.remove( player );
		}
		else
			CommandSigns.get().messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}

}

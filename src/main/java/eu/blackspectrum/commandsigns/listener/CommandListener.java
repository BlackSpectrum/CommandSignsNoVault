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


	private final CommandSigns	plugin;




	public CommandListener(final CommandSigns plugin) {
		this.plugin = plugin;
	}




	public void finishEditing( final Player player ) {
		this.plugin.playerStates.remove( player );
		this.plugin.playerText.remove( player );
		this.plugin.messenger.sendMessage( player, "success.done_editing" );
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
				this.plugin.messenger.sendMessage( sender, "failure.wrong_syntax" );
				return true;
			}
		}
		return false;
	}




	private void clipboard( final CommandSender sender, final Player player, final int lineNumber, final int textStart, final String[] args ) {
		if ( lineNumber < 1 )
			this.plugin.messenger.sendMessage( player, "failure.invalid_line" );
		else
		{
			if ( this.plugin.playerStates.get( player ) == PlayerState.EDIT_SELECT )
				this.plugin.messenger.sendMessage( player, "failure.must_select" );
			SignText text = this.plugin.playerText.get( player );
			if ( text == null )
			{
				text = new SignText( player.getName(), false );
				this.plugin.playerText.put( player, text );
			}
			final String line = StringUtils.join( args, " ", textStart, args.length );
			if ( line.startsWith( "/*" ) && !this.plugin.hasPermission( player, "commandsigns.create.super", false ) )
				this.plugin.messenger.sendMessage( player, "failure.no_super" );
			if ( ( line.startsWith( "/^" ) || line.startsWith( "/#" ) )
					&& !this.plugin.hasPermission( player, "commandsigns.create.op", false ) )
				this.plugin.messenger.sendMessage( player, "failure.no_op" );
			text.setLine( lineNumber, line );
			this.plugin.messenger.sendRaw( player, "success.line_print", new String[] { "NUMBER", "LINE" }, new String[] { "" + lineNumber,
					line } );
		}
	}




	protected boolean add( final CommandSender sender, final Player player, final int lineNumber, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.regular" ) )
		{
			this.clipboard( sender, player, lineNumber, 1, args );
			if ( this.plugin.playerStates.get( player ) != PlayerState.EDIT )
			{
				this.plugin.playerStates.put( player, PlayerState.ENABLE );
				this.plugin.messenger.sendMessage( player, "progress.add" );
			}
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean batch( final CommandSender sender, final Player player, final String[] args ) {
		PlayerState ps = this.plugin.playerStates.get( player );
		if ( ps == null )
		{
			this.plugin.messenger.sendMessage( player, "failure.not_in_mode" );
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
			this.plugin.messenger.sendMessage( player, "failure.no_batch" );
		}
		this.plugin.playerStates.put( player, ps );
		return true;
	}




	protected boolean clear( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.remove" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.remove( player );
			this.plugin.playerText.remove( player );
			this.plugin.messenger.sendMessage( player, "success.cleared" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean copy( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.regular" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.put( player, PlayerState.COPY );
			this.plugin.messenger.sendMessage( player, "progress.copy" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean edit( final CommandSender sender, final Player player, final String[] args ) {
		if ( this.plugin.hasPermission( sender, "commandsigns.edit", false ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT_SELECT || ps == PlayerState.EDIT )
				this.finishEditing( player );
			else
			{
				this.plugin.playerStates.put( player, PlayerState.EDIT_SELECT );
				this.plugin.playerText.remove( player );
				this.plugin.messenger.sendMessage( player, "progress.select_sign" );
			}
		}
		return true;
	}




	protected boolean insert( final CommandSender sender, final Player player, final int lineNumber, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.regular" ) )
		{
			this.clipboard( sender, player, lineNumber, 2, args );
			if ( this.plugin.playerStates.get( player ) != PlayerState.EDIT )
			{
				this.plugin.playerStates.put( player, PlayerState.INSERT );
				this.plugin.messenger.sendMessage( player, "progress.add" );
			}
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean read( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.regular" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.put( player, PlayerState.READ );
			this.plugin.messenger.sendMessage( player, "progress.read" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean redstone( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.redstone" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.put( player, PlayerState.REDSTONE );
			this.plugin.messenger.sendMessage( player, "progress.redstone" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean reload( final CommandSender sender, final Player player, final String[] args ) {
		if ( this.plugin.hasPermission( sender, "commandsigns.reload", false ) )
		{
			this.plugin.load();
			this.plugin.messenger.sendMessage( sender, "success.reloaded" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean remove( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.remove" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.put( player, PlayerState.REMOVE );
			this.plugin.messenger.sendMessage( player, "progress.remove" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean save( final CommandSender sender, final Player player, final String[] args ) {
		if ( this.plugin.hasPermission( sender, "commandsigns.save", false ) )
		{
			this.plugin.loader.saveFile();
			this.plugin.messenger.sendMessage( sender, "success.saved" );
		}
		return true;
	}




	protected boolean toggle( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.toggle" ) )
		{
			final PlayerState ps = this.plugin.playerStates.get( player );
			if ( ps == PlayerState.EDIT || ps == PlayerState.EDIT_SELECT )
				this.finishEditing( player );
			this.plugin.playerStates.put( player, PlayerState.TOGGLE );
			this.plugin.messenger.sendMessage( player, "progress.toggle" );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}




	protected boolean view( final CommandSender sender, final Player player, final String[] args ) {
		if ( player == null )
			this.plugin.messenger.sendMessage( sender, "failure.player_only" );
		if ( this.plugin.hasPermission( player, "commandsigns.create.regular" ) )
		{
			final SignText text = this.plugin.playerText.get( player );
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
			this.plugin.playerStates.remove( player );
		}
		else
			this.plugin.messenger.sendMessage( player, "failure.no_perms" );
		return true;
	}

}

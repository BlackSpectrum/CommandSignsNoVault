package eu.blackspectrum.commandsigns;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import eu.blackspectrum.commandsigns.util.PlayerState;
import eu.blackspectrum.commandsigns.util.SignText;

public class ClickHandler
{


	private final Location		location;
	private final Player		player;
	private final CommandSigns	plugin;




	public ClickHandler(final CommandSigns plugin, final Player player, final Block block) {
		this.plugin = plugin;
		this.player = player;
		this.location = block.getLocation();
	}




	public void copySign() {
		synchronized ( this.plugin.activeSigns )
		{
			final SignText text = this.plugin.activeSigns.get( this.location );
			if ( text == null )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText clone = this.plugin.activeSigns.get( this.location ).clone( this.player.getName() );
			this.plugin.playerText.put( this.player, clone );
		}
		this.readSign( true );
		this.plugin.messenger.sendMessage( this.player, "success.copied" );
		this.plugin.playerStates.put( this.player, PlayerState.ENABLE );
	}




	public void createSign( final boolean batch ) {
		synchronized ( this.plugin.activeSigns )
		{
			if ( this.plugin.activeSigns.containsKey( this.location ) )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.already_enabled" );
				return;
			}
			final SignText text = this.plugin.playerText.get( this.player );

			try
			{
				text.trim();
				this.plugin.activeSigns.put( this.location, text.clone( this.player.getName() ) );
				this.plugin.messenger.sendMessage( this.player, "success.enabled" );
			}
			catch ( final Exception e )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.wrong_syntax" );
			}
		}

		if ( !batch )
		{
			this.plugin.playerStates.remove( this.player );
			this.plugin.playerText.remove( this.player );
		}
	}




	public void editSign() {
		SignText cst;
		synchronized ( this.plugin.activeSigns )
		{
			cst = this.plugin.activeSigns.get( this.location );
		}
		if ( cst == null )
		{
			this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		this.plugin.messenger.sendMessage( this.player, "progress.edit_started" );
		this.plugin.playerText.put( this.player, cst );
		this.plugin.playerStates.put( this.player, PlayerState.EDIT );
	}




	public void insert( final boolean batch ) {
		SignText currentText;
		synchronized ( this.plugin.activeSigns )
		{
			currentText = this.plugin.activeSigns.get( this.location );
		}
		if ( currentText == null )
		{
			this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		final SignText newText = this.plugin.playerText.get( this.player );

		// Insert lines from last to first - that way you don't overwrite stuff
		for ( int i = newText.count(); i >= 1; i-- )
		{
			// Move all lines after the current position up one place
			for ( int j = currentText.count(); j >= i; j-- )
				currentText.setLine( j + 1, currentText.getLine( j ) );
			currentText.setLine( i, newText.getLine( i ) );
		}
		currentText.trim();

		this.plugin.messenger.sendMessage( this.player, "success.done_editing" );
		if ( !batch )
		{
			this.plugin.playerStates.remove( this.player );
			this.plugin.playerText.remove( this.player );
		}
	}




	public boolean onInteract( final Action action ) {
		PlayerState state;
		synchronized ( this.plugin.activeSigns )
		{
			state = this.plugin.playerStates.get( this.player );
		}
		if ( state != null )
		{
			switch ( state ) {
			case ENABLE:
				this.createSign( false );
				break;
			case BATCH_ENABLE:
				this.createSign( true );
				break;
			case INSERT:
				this.insert( false );
				break;
			case BATCH_INSERT:
				this.insert( true );
				break;
			case REMOVE:
				this.removeSign( false );
				break;
			case BATCH_REMOVE:
				this.removeSign( true );
				break;
			case READ:
				this.readSign( false );
				break;
			case BATCH_READ:
				this.readSign( true );
				break;
			case COPY:
				this.copySign();
				break;
			case EDIT_SELECT:
				this.editSign();
				this.readSign( true );
				break;
			case TOGGLE:
				this.toggleSign( false );
				break;
			case BATCH_TOGGLE:
				this.toggleSign( true );
				break;
			case REDSTONE:
				this.redstoneToggle( false );
				break;
			case BATCH_REDSTONE:
				this.redstoneToggle( true );
				break;
			default:
				return new SignExecutor( this.plugin, this.player, this.location, action ).runLines();
			}
			return true;
		}
		else
			return new SignExecutor( this.plugin, this.player, this.location, action ).runLines();
	}




	public void readSign( final boolean batch ) {
		SignText text;
		synchronized ( this.plugin.activeSigns )
		{
			text = this.plugin.activeSigns.get( this.location );
		}
		if ( text == null )
		{
			this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		int i = 1;
		for ( final String line : text )
		{
			if ( !line.equals( "" ) )
				this.plugin.messenger.sendRaw( this.player, "success.line_print", new String[] { "NUMBER", "LINE" }, new String[] { "" + i,
						line } );
			i++;
		}
		if ( !batch )
			this.plugin.playerStates.remove( this.player );
	}




	public void redstoneToggle( final boolean batch ) {
		synchronized ( this.plugin.activeSigns )
		{
			if ( !this.plugin.activeSigns.containsKey( this.location ) )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText text = this.plugin.activeSigns.get( this.location );
			this.plugin.activeSigns.remove( this.location );

			final boolean enabled = text.isRedstone();
			if ( enabled )
			{
				text.setRedstone( false );
				this.plugin.activeSigns.put( this.location, text );
				this.plugin.messenger.sendMessage( this.player, "success.redstone_disabled" );
			}
			else
			{
				text.setRedstone( true );
				this.plugin.activeSigns.put( this.location, text );
				this.plugin.messenger.sendMessage( this.player, "success.redstone_enabled" );
			}
			if ( !batch )
				this.plugin.playerStates.remove( this.player );
		}
	}




	public void removeSign( final boolean batch ) {
		synchronized ( this.plugin.activeSigns )
		{
			if ( !this.plugin.activeSigns.containsKey( this.location ) )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			this.plugin.activeSigns.remove( this.location );
			this.plugin.messenger.sendMessage( this.player, "success.removed" );
		}
		if ( !batch )
			if ( this.plugin.playerText.containsKey( this.player ) )
			{
				this.plugin.playerStates.put( this.player, PlayerState.ENABLE );
				this.plugin.messenger.sendMessage( this.player, "information.text_in_clipboard" );
			}
			else
				this.plugin.playerStates.remove( this.player );
	}




	public void toggleSign( final boolean batch ) {
		synchronized ( this.plugin.activeSigns )
		{
			if ( !this.plugin.activeSigns.containsKey( this.location ) )
			{
				this.plugin.messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText text = this.plugin.activeSigns.get( this.location );
			this.plugin.activeSigns.remove( this.location );
			final boolean enabled = text.isEnabled();
			if ( enabled )
			{
				text.setEnabled( false );
				this.plugin.activeSigns.put( this.location, text );
				this.plugin.messenger.sendMessage( this.player, "success.disabled" );
			}
			else
			{
				text.setEnabled( true );
				this.plugin.activeSigns.put( this.location, text );
				this.plugin.messenger.sendMessage( this.player, "success.enabled" );
			}
			if ( !batch )
				this.plugin.playerStates.remove( this.player );
		}
	}
}

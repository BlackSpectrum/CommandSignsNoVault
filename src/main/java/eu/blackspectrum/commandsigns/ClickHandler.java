package eu.blackspectrum.commandsigns;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import eu.blackspectrum.commandsigns.util.PlayerState;
import eu.blackspectrum.commandsigns.util.SignText;

public class ClickHandler
{


	private final Location	location;
	private final Player	player;




	public ClickHandler(final Player player, final Block block) {
		this.player = player;
		this.location = block.getLocation();
	}




	public void copySign() {
		synchronized ( CommandSigns.get().activeSigns )
		{
			final SignText text = CommandSigns.get().activeSigns.get( this.location );
			if ( text == null )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText clone = CommandSigns.get().activeSigns.get( this.location ).clone( this.player.getName() );
			CommandSigns.get().playerText.put( this.player, clone );
		}
		this.readSign( true );
		CommandSigns.get().messenger.sendMessage( this.player, "success.copied" );
		CommandSigns.get().playerStates.put( this.player, PlayerState.ENABLE );
	}




	public void createSign( final boolean batch ) {
		synchronized ( CommandSigns.get().activeSigns )
		{
			if ( CommandSigns.get().activeSigns.containsKey( this.location ) )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.already_enabled" );
				return;
			}
			final SignText text = CommandSigns.get().playerText.get( this.player );

			try
			{
				text.trim();
				CommandSigns.get().activeSigns.put( this.location, text.clone( this.player.getName() ) );
				CommandSigns.get().messenger.sendMessage( this.player, "success.enabled" );
			}
			catch ( final Exception e )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.wrong_syntax" );
			}
		}

		if ( !batch )
		{
			CommandSigns.get().playerStates.remove( this.player );
			CommandSigns.get().playerText.remove( this.player );
		}
	}




	public void editSign() {
		SignText cst;
		synchronized ( CommandSigns.get().activeSigns )
		{
			cst = CommandSigns.get().activeSigns.get( this.location );
		}
		if ( cst == null )
		{
			CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		CommandSigns.get().messenger.sendMessage( this.player, "progress.edit_started" );
		CommandSigns.get().playerText.put( this.player, cst );
		CommandSigns.get().playerStates.put( this.player, PlayerState.EDIT );
	}




	public void insert( final boolean batch ) {
		SignText currentText;
		synchronized ( CommandSigns.get().activeSigns )
		{
			currentText = CommandSigns.get().activeSigns.get( this.location );
		}
		if ( currentText == null )
		{
			CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		final SignText newText = CommandSigns.get().playerText.get( this.player );

		// Insert lines from last to first - that way you don't overwrite stuff
		for ( int i = newText.count(); i >= 1; i-- )
		{
			// Move all lines after the current position up one place
			for ( int j = currentText.count(); j >= i; j-- )
				currentText.setLine( j + 1, currentText.getLine( j ) );
			currentText.setLine( i, newText.getLine( i ) );
		}
		currentText.trim();

		CommandSigns.get().messenger.sendMessage( this.player, "success.done_editing" );
		if ( !batch )
		{
			CommandSigns.get().playerStates.remove( this.player );
			CommandSigns.get().playerText.remove( this.player );
		}
	}




	public boolean onInteract( final Action action ) {
		PlayerState state;
		synchronized ( CommandSigns.get().activeSigns )
		{
			state = CommandSigns.get().playerStates.get( this.player );
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
				return new SignExecutor( this.player, this.location, action ).runLines();
			}
			return true;
		}
		else
			return new SignExecutor( this.player, this.location, action ).runLines();
	}




	public void readSign( final boolean batch ) {
		SignText text;
		synchronized ( CommandSigns.get().activeSigns )
		{
			text = CommandSigns.get().activeSigns.get( this.location );
		}
		if ( text == null )
		{
			CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
			return;
		}
		int i = 1;
		for ( final String line : text )
		{
			if ( !line.equals( "" ) )
				CommandSigns.get().messenger.sendRaw( this.player, "success.line_print", new String[] { "NUMBER", "LINE" }, new String[] {
						"" + i, line } );
			i++;
		}
		if ( !batch )
			CommandSigns.get().playerStates.remove( this.player );
	}




	public void redstoneToggle( final boolean batch ) {
		synchronized ( CommandSigns.get().activeSigns )
		{
			if ( !CommandSigns.get().activeSigns.containsKey( this.location ) )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText text = CommandSigns.get().activeSigns.get( this.location );
			CommandSigns.get().activeSigns.remove( this.location );

			final boolean enabled = text.isRedstone();
			if ( enabled )
			{
				text.setRedstone( false );
				CommandSigns.get().activeSigns.put( this.location, text );
				CommandSigns.get().messenger.sendMessage( this.player, "success.redstone_disabled" );
			}
			else
			{
				text.setRedstone( true );
				CommandSigns.get().activeSigns.put( this.location, text );
				CommandSigns.get().messenger.sendMessage( this.player, "success.redstone_enabled" );
			}
			if ( !batch )
				CommandSigns.get().playerStates.remove( this.player );
		}
	}




	public void removeSign( final boolean batch ) {
		synchronized ( CommandSigns.get().activeSigns )
		{
			if ( !CommandSigns.get().activeSigns.containsKey( this.location ) )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			CommandSigns.get().activeSigns.remove( this.location );
			CommandSigns.get().messenger.sendMessage( this.player, "success.removed" );
		}
		if ( !batch )
			if ( CommandSigns.get().playerText.containsKey( this.player ) )
			{
				CommandSigns.get().playerStates.put( this.player, PlayerState.ENABLE );
				CommandSigns.get().messenger.sendMessage( this.player, "information.text_in_clipboard" );
			}
			else
				CommandSigns.get().playerStates.remove( this.player );
	}




	public void toggleSign( final boolean batch ) {
		synchronized ( CommandSigns.get().activeSigns )
		{
			if ( !CommandSigns.get().activeSigns.containsKey( this.location ) )
			{
				CommandSigns.get().messenger.sendMessage( this.player, "failure.not_a_sign" );
				return;
			}
			final SignText text = CommandSigns.get().activeSigns.get( this.location );
			CommandSigns.get().activeSigns.remove( this.location );
			final boolean enabled = text.isEnabled();
			if ( enabled )
			{
				text.setEnabled( false );
				CommandSigns.get().activeSigns.put( this.location, text );
				CommandSigns.get().messenger.sendMessage( this.player, "success.disabled" );
			}
			else
			{
				text.setEnabled( true );
				CommandSigns.get().activeSigns.put( this.location, text );
				CommandSigns.get().messenger.sendMessage( this.player, "success.enabled" );
			}
			if ( !batch )
				CommandSigns.get().playerStates.remove( this.player );
		}
	}
}

package eu.blackspectrum.commandsigns.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.blackspectrum.commandsigns.ClickHandler;
import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.SignExecutor;
import eu.blackspectrum.commandsigns.util.SignText;

public class EventListener implements Listener
{

	private static EventListener instance = new EventListener();
	
	public static EventListener get(){
		return instance;
	}
	
	private boolean	registered	= false;




	public void register() {
		if ( !this.registered )
		{
			Bukkit.getPluginManager().registerEvents( this, CommandSigns.get() );
			this.registered = true;
		}
	}
	

	public void handleRedstone( final Block b ) {
		final Location csl = b.getLocation();
		final SignText text = CommandSigns.get().activeSigns.get( csl );
		if ( text != null && text.isRedstone() )
			new SignExecutor( null, csl, null ).runLines();
	}




	@EventHandler
	public void onBlockBreak( final BlockBreakEvent event ) {
		if ( event.isCancelled() )
			return;
		final Location location = event.getBlock().getLocation();
		if ( CommandSigns.get().activeSigns.containsKey( location ) )
		{
			CommandSigns.get().messenger.sendMessage( event.getPlayer(), "failure.remove_first" );
			event.setCancelled( true );
		}
	}




	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract( final PlayerInteractEvent event ) {
		Block block = null;
		final Action action = event.getAction();
		if ( action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK || action == Action.PHYSICAL )
		{
			block = event.getClickedBlock();
			if ( block != null )
			{
				final ClickHandler signClickEvent = new ClickHandler( event.getPlayer(), block );
				if ( signClickEvent.onInteract( action ) && action != Action.PHYSICAL )
					event.setCancelled( true );
			}
		}
	}




	@EventHandler
	public void onRedstoneChange( final BlockRedstoneEvent event ) {
		if ( event.getNewCurrent() != 0 && event.getOldCurrent() == 0 )
		{
			final Block b = event.getBlock();
			this.handleRedstone( b );
			this.handleRedstone( b.getRelative( BlockFace.NORTH ) );
			this.handleRedstone( b.getRelative( BlockFace.SOUTH ) );
			this.handleRedstone( b.getRelative( BlockFace.EAST ) );
			this.handleRedstone( b.getRelative( BlockFace.WEST ) );
			this.handleRedstone( b.getRelative( BlockFace.UP ) );
			this.handleRedstone( b.getRelative( BlockFace.DOWN ) );
		}
	}
}

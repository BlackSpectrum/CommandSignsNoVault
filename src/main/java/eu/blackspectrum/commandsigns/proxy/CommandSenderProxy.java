package eu.blackspectrum.commandsigns.proxy;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

/**
 * Creates a tapped link between an originator CommandSender and a recipient
 * CommandSender originator and recipient can be the same CommandSender Allows
 * sendMessage() methods to be intercepted if the silent flag is set
 */
public class CommandSenderProxy implements CommandSender
{


	private final CommandSender	originator;
	private final CommandSender	recipient;
	boolean						silent;




	public CommandSenderProxy(final CommandSender originator) {
		this( originator, originator, false );
	}




	public CommandSenderProxy(final CommandSender originator, final boolean silent) {
		this( originator, originator, silent );
	}




	public CommandSenderProxy(final CommandSender originator, final CommandSender recipient) {
		this( originator, recipient, false );
	}




	public CommandSenderProxy(final CommandSender originator, final CommandSender recipient, final boolean silent) {
		this.originator = originator;
		this.recipient = recipient;
		this.silent = silent;
	}




	@Override
	public PermissionAttachment addAttachment( final Plugin plugin ) {
		return this.originator.addAttachment( plugin );
	}




	@Override
	public PermissionAttachment addAttachment( final Plugin plugin, final int ticks ) {
		return this.originator.addAttachment( plugin, ticks );
	}




	@Override
	public PermissionAttachment addAttachment( final Plugin plugin, final String name, final boolean value ) {
		return this.originator.addAttachment( plugin, name, value );
	}




	@Override
	public PermissionAttachment addAttachment( final Plugin plugin, final String name, final boolean value, final int ticks ) {
		return this.originator.addAttachment( plugin, name, value, ticks );
	}




	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return this.originator.getEffectivePermissions();
	}




	@Override
	public String getName() {
		return this.originator.getName();
	}




	@Override
	public Server getServer() {
		return this.originator.getServer();
	}




	@Override
	public boolean hasPermission( final Permission perm ) {
		return this.originator.hasPermission( perm );
	}




	@Override
	public boolean hasPermission( final String name ) {
		return this.originator.hasPermission( name );
	}




	@Override
	public boolean isOp() {
		return this.originator.isOp();
	}




	@Override
	public boolean isPermissionSet( final Permission perm ) {
		return this.originator.isPermissionSet( perm );
	}




	@Override
	public boolean isPermissionSet( final String name ) {
		return this.originator.isPermissionSet( name );
	}




	public boolean isSilent() {
		return this.silent;
	}




	@Override
	public void recalculatePermissions() {
		this.originator.recalculatePermissions();
	}




	@Override
	public void removeAttachment( final PermissionAttachment attachment ) {
		this.originator.removeAttachment( attachment );
	}




	@Override
	public void sendMessage( final String message ) {
		if ( !this.silent && this.recipient != null )
			this.recipient.sendMessage( message );
	}




	@Override
	public void sendMessage( final String[] messages ) {
		if ( !this.silent && this.recipient != null )
			this.recipient.sendMessage( messages );
	}




	@Override
	public void setOp( final boolean value ) {
		this.originator.setOp( value );
	}




	public void setSilent( final boolean silent ) {
		this.silent = silent;
	}
}

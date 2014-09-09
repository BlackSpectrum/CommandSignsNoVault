package eu.blackspectrum.commandsigns.proxy;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

/**
 * Creates a tapped link between an originator Player and a recipient Player
 * originator and recipient can be the same player Allows sendMessage() methods
 * to be intercepted if the silent flag is set
 */
public class PlayerProxy implements CommandSender
{


	private final CommandSender	originator;
	private final CommandSender	recipient;

	boolean						silent;




	public PlayerProxy(final Player originator) {
		this( originator, originator, false );
	}




	public PlayerProxy(final Player originator, final boolean silent) {
		this( originator, originator, silent );
	}




	public PlayerProxy(final Player originator, final Player recipient) {
		this( originator, recipient, false );
	}




	public PlayerProxy(final Player originator, final Player recipient, final boolean silent) {
		this.originator = originator;
		this.recipient = recipient;
		this.silent = silent;
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin)
	 */
	@Override
	public PermissionAttachment addAttachment( final Plugin arg0 ) {
		return this.originator.addAttachment( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
	 *      int)
	 */
	@Override
	public PermissionAttachment addAttachment( final Plugin arg0, final int arg1 ) {
		return this.originator.addAttachment( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
	 *      java.lang.String, boolean)
	 */
	@Override
	public PermissionAttachment addAttachment( final Plugin arg0, final String arg1, final boolean arg2 ) {
		return this.originator.addAttachment( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @see org.bukkit.permissions.Permissible#addAttachment(org.bukkit.plugin.Plugin,
	 *      java.lang.String, boolean, int)
	 */
	@Override
	public PermissionAttachment addAttachment( final Plugin arg0, final String arg1, final boolean arg2, final int arg3 ) {
		return this.originator.addAttachment( arg0, arg1, arg2, arg3 );
	}




	/**
	 * @return
	 * @see org.bukkit.permissions.Permissible#getEffectivePermissions()
	 */
	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return this.originator.getEffectivePermissions();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getName()
	 */
	@Override
	public String getName() {
		return this.originator.getName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getServer()
	 */
	@Override
	public Server getServer() {
		return this.originator.getServer();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.permissions.Permissible#hasPermission(org.bukkit.permissions.Permission)
	 */
	@Override
	public boolean hasPermission( final Permission arg0 ) {
		return this.originator.hasPermission( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.permissions.Permissible#hasPermission(java.lang.String)
	 */
	@Override
	public boolean hasPermission( final String arg0 ) {
		return this.originator.hasPermission( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.permissions.ServerOperator#isOp()
	 */
	@Override
	public boolean isOp() {
		return this.originator.isOp();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.permissions.Permissible#isPermissionSet(org.bukkit.permissions.Permission)
	 */
	@Override
	public boolean isPermissionSet( final Permission arg0 ) {
		return this.originator.isPermissionSet( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.permissions.Permissible#isPermissionSet(java.lang.String)
	 */
	@Override
	public boolean isPermissionSet( final String arg0 ) {
		return this.originator.isPermissionSet( arg0 );
	}




	public boolean isSilent() {
		return this.silent;
	}




	/**
	 *
	 * @see org.bukkit.permissions.Permissible#recalculatePermissions()
	 */
	@Override
	public void recalculatePermissions() {
		this.originator.recalculatePermissions();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.permissions.Permissible#removeAttachment(org.bukkit.permissions.PermissionAttachment)
	 */
	@Override
	public void removeAttachment( final PermissionAttachment arg0 ) {
		this.originator.removeAttachment( arg0 );
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
	public void setOp( final boolean arg0 ) {
		this.originator.setOp( arg0 );

	}

}

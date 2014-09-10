package eu.blackspectrum.commandsigns.proxy;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

/**
 * Creates a tapped link between an originator Player and a recipient Player
 * originator and recipient can be the same player Allows sendMessage() methods
 * to be intercepted if the silent flag is set
 */
public class PlayerProxy implements Player
{


	private final Player	originator;
	private final Player	recipient;

	boolean					silent;




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




	@Override
	public void _INVALID_damage( final int arg0 ) {
		// TODO Auto-generated method stub

	}




	@Override
	public void _INVALID_damage( final int arg0, final Entity arg1 ) {
		// TODO Auto-generated method stub

	}




	@Override
	public int _INVALID_getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int _INVALID_getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int _INVALID_getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public void _INVALID_setHealth( final int arg0 ) {
		// TODO Auto-generated method stub

	}




	@Override
	public void _INVALID_setLastDamage( final int arg0 ) {
		// TODO Auto-generated method stub

	}




	@Override
	public void _INVALID_setMaxHealth( final int arg0 ) {
		// TODO Auto-generated method stub

	}




	/**
	 * @param arg0
	 * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation)
	 */
	@Override
	public void abandonConversation( final Conversation arg0 ) {
		this.originator.abandonConversation( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation,
	 *      org.bukkit.conversations.ConversationAbandonedEvent)
	 */
	@Override
	public void abandonConversation( final Conversation arg0, final ConversationAbandonedEvent arg1 ) {
		this.originator.abandonConversation( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.conversations.Conversable#acceptConversationInput(java.lang.String)
	 */
	@Override
	public void acceptConversationInput( final String arg0 ) {
		this.originator.acceptConversationInput( arg0 );
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
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffect(org.bukkit.potion.PotionEffect)
	 */
	@Override
	public boolean addPotionEffect( final PotionEffect arg0 ) {
		return this.originator.addPotionEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffect(org.bukkit.potion.PotionEffect,
	 *      boolean)
	 */
	@Override
	public boolean addPotionEffect( final PotionEffect arg0, final boolean arg1 ) {
		return this.originator.addPotionEffect( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffects(java.util.Collection)
	 */
	@Override
	public boolean addPotionEffects( final Collection<PotionEffect> arg0 ) {
		return this.originator.addPotionEffects( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#awardAchievement(org.bukkit.Achievement)
	 */
	@Override
	public void awardAchievement( final Achievement arg0 ) {
		this.originator.awardAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.conversations.Conversable#beginConversation(org.bukkit.conversations.Conversation)
	 */
	@Override
	public boolean beginConversation( final Conversation arg0 ) {
		return this.originator.beginConversation( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#canSee(org.bukkit.entity.Player)
	 */
	@Override
	public boolean canSee( final Player arg0 ) {
		return this.originator.canSee( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#chat(java.lang.String)
	 */
	@Override
	public void chat( final String arg0 ) {
		this.originator.chat( arg0 );
	}




	/**
	 *
	 * @see org.bukkit.entity.HumanEntity#closeInventory()
	 */
	@Override
	public void closeInventory() {
		this.originator.closeInventory();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#damage(double)
	 */
	@Override
	public void damage( final double arg0 ) {
		this.originator.damage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Damageable#damage(double,
	 *      org.bukkit.entity.Entity)
	 */
	@Override
	public void damage( final double arg0, final Entity arg1 ) {
		this.originator.damage( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#damage(int)
	 */
	@Deprecated
	public void damage( final int arg0 ) {
		this.originator.damage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#damage(int, org.bukkit.entity.Entity)
	 */
	@Deprecated
	public void damage( final int arg0, final Entity arg1 ) {
		this.originator.damage( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0 ) throws IllegalArgumentException {
		this.originator.decrementStatistic( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0, final EntityType arg1 ) throws IllegalArgumentException {
		this.originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType, int)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0, final EntityType arg1, final int arg2 ) {
		this.originator.decrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic,
	 *      int)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0, final int arg1 ) throws IllegalArgumentException {
		this.originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0, final Material arg1 ) throws IllegalArgumentException {
		this.originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material, int)
	 */
	@Override
	public void decrementStatistic( final Statistic arg0, final Material arg1, final int arg2 ) throws IllegalArgumentException {
		this.originator.decrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#eject()
	 */
	@Override
	public boolean eject() {
		return this.originator.eject();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getActivePotionEffects()
	 */
	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		return this.originator.getActivePotionEffects();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getAddress()
	 */
	@Override
	public InetSocketAddress getAddress() {
		return this.originator.getAddress();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getAllowFlight()
	 */
	@Override
	public boolean getAllowFlight() {
		return this.originator.getAllowFlight();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getBedSpawnLocation()
	 */
	@Override
	public Location getBedSpawnLocation() {
		return this.originator.getBedSpawnLocation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getCanPickupItems()
	 */
	@Override
	public boolean getCanPickupItems() {
		return this.originator.getCanPickupItems();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getCompassTarget()
	 */
	@Override
	public Location getCompassTarget() {
		return this.originator.getCompassTarget();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getCustomName()
	 */
	@Override
	public String getCustomName() {
		return this.originator.getCustomName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return this.originator.getDisplayName();
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
	 * @see org.bukkit.entity.HumanEntity#getEnderChest()
	 */
	@Override
	public Inventory getEnderChest() {
		return this.originator.getEnderChest();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getEntityId()
	 */
	@Override
	public int getEntityId() {
		return this.originator.getEntityId();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEquipment()
	 */
	@Override
	public EntityEquipment getEquipment() {
		return this.originator.getEquipment();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getExhaustion()
	 */
	@Override
	public float getExhaustion() {
		return this.originator.getExhaustion();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getExp()
	 */
	@Override
	public float getExp() {
		return this.originator.getExp();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getExpToLevel()
	 */
	@Override
	public int getExpToLevel() {
		return this.originator.getExpToLevel();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeHeight()
	 */
	@Override
	public double getEyeHeight() {
		return this.originator.getEyeHeight();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeHeight(boolean)
	 */
	@Override
	public double getEyeHeight( final boolean arg0 ) {
		return this.originator.getEyeHeight( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeLocation()
	 */
	@Override
	public Location getEyeLocation() {
		return this.originator.getEyeLocation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getFallDistance()
	 */
	@Override
	public float getFallDistance() {
		return this.originator.getFallDistance();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getFireTicks()
	 */
	@Override
	public int getFireTicks() {
		return this.originator.getFireTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getFirstPlayed()
	 */
	@Override
	public long getFirstPlayed() {
		return this.originator.getFirstPlayed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getFlySpeed()
	 */
	@Override
	public float getFlySpeed() {
		return this.originator.getFlySpeed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getFoodLevel()
	 */
	@Override
	public int getFoodLevel() {
		return this.originator.getFoodLevel();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getGameMode()
	 */
	@Override
	public GameMode getGameMode() {
		return this.originator.getGameMode();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Damageable#getHealth()
	 */
	@Override
	public double getHealth() {
		return ( (Damageable) this.originator ).getHealth();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getHealthScale()
	 */
	@Override
	public double getHealthScale() {
		return this.originator.getHealthScale();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getInventory()
	 */
	@Override
	public PlayerInventory getInventory() {
		return this.originator.getInventory();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getItemInHand()
	 */
	@Override
	public ItemStack getItemInHand() {
		return this.originator.getItemInHand();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getItemOnCursor()
	 */
	@Override
	public ItemStack getItemOnCursor() {
		return this.originator.getItemOnCursor();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getKiller()
	 */
	@Override
	public Player getKiller() {
		return this.originator.getKiller();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getLastDamage()
	 */
	@Override
	public double getLastDamage() {
		return this.originator.getLastDamage();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getLastDamageCause()
	 */
	@Override
	public EntityDamageEvent getLastDamageCause() {
		return this.originator.getLastDamageCause();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getLastPlayed()
	 */
	@Override
	public long getLastPlayed() {
		return this.originator.getLastPlayed();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getLastTwoTargetBlocks(java.util.HashSet,
	 *      int)
	 */
	@Deprecated
	@Override
	public List<Block> getLastTwoTargetBlocks( final HashSet<Byte> arg0, final int arg1 ) {
		return this.originator.getLastTwoTargetBlocks( arg0, arg1 );
	}




	/**
	 * @return
	 * @throws IllegalStateException
	 * @see org.bukkit.entity.LivingEntity#getLeashHolder()
	 */
	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		return this.originator.getLeashHolder();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.originator.getLevel();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getLineOfSight(java.util.HashSet,
	 *      int)
	 */
	@Deprecated
	@Override
	public List<Block> getLineOfSight( final HashSet<Byte> arg0, final int arg1 ) {
		return this.originator.getLineOfSight( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.plugin.messaging.PluginMessageRecipient#getListeningPluginChannels()
	 */
	@Override
	public Set<String> getListeningPluginChannels() {
		return this.originator.getListeningPluginChannels();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getLocation()
	 */
	@Override
	public Location getLocation() {
		return this.originator.getLocation();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#getLocation(org.bukkit.Location)
	 */
	@Override
	public Location getLocation( final Location arg0 ) {
		return this.originator.getLocation( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getMaxFireTicks()
	 */
	@Override
	public int getMaxFireTicks() {
		return this.originator.getMaxFireTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Damageable#getMaxHealth()
	 */
	@Override
	public double getMaxHealth() {
		return ( (Damageable) this.originator ).getMaxHealth();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getMaximumAir()
	 */
	@Override
	public int getMaximumAir() {
		return this.originator.getMaximumAir();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getMaximumNoDamageTicks()
	 */
	@Override
	public int getMaximumNoDamageTicks() {
		return this.originator.getMaximumNoDamageTicks();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.metadata.Metadatable#getMetadata(java.lang.String)
	 */
	@Override
	public List<MetadataValue> getMetadata( final String arg0 ) {
		return this.originator.getMetadata( arg0 );
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
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @see org.bukkit.entity.Entity#getNearbyEntities(double, double, double)
	 */
	@Override
	public List<Entity> getNearbyEntities( final double arg0, final double arg1, final double arg2 ) {
		return this.originator.getNearbyEntities( arg0, arg1, arg2 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getNoDamageTicks()
	 */
	@Override
	public int getNoDamageTicks() {
		return this.originator.getNoDamageTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getOpenInventory()
	 */
	@Override
	public InventoryView getOpenInventory() {
		return this.originator.getOpenInventory();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getPassenger()
	 */
	@Override
	public Entity getPassenger() {
		return this.originator.getPassenger();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getPlayer()
	 */
	@Override
	public Player getPlayer() {
		return this.originator.getPlayer();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerListName()
	 */
	@Override
	public String getPlayerListName() {
		return this.originator.getPlayerListName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerTime()
	 */
	@Override
	public long getPlayerTime() {
		return this.originator.getPlayerTime();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerTimeOffset()
	 */
	@Override
	public long getPlayerTimeOffset() {
		return this.originator.getPlayerTimeOffset();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerWeather()
	 */
	@Override
	public WeatherType getPlayerWeather() {
		return this.originator.getPlayerWeather();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getRemainingAir()
	 */
	@Override
	public int getRemainingAir() {
		return this.originator.getRemainingAir();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getRemoveWhenFarAway()
	 */
	@Override
	public boolean getRemoveWhenFarAway() {
		return this.originator.getRemoveWhenFarAway();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getSaturation()
	 */
	@Override
	public float getSaturation() {
		return this.originator.getSaturation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getScoreboard()
	 */
	@Override
	public Scoreboard getScoreboard() {
		return this.originator.getScoreboard();
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
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getSleepTicks()
	 */
	@Override
	public int getSleepTicks() {
		return this.originator.getSleepTicks();
	}




	/**
	 * @param arg0
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic)
	 */
	@Override
	public int getStatistic( final Statistic arg0 ) throws IllegalArgumentException {
		return this.originator.getStatistic( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType)
	 */
	@Override
	public int getStatistic( final Statistic arg0, final EntityType arg1 ) throws IllegalArgumentException {
		return this.originator.getStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material)
	 */
	@Override
	public int getStatistic( final Statistic arg0, final Material arg1 ) throws IllegalArgumentException {
		return this.originator.getStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getTargetBlock(java.util.HashSet,
	 *      int)
	 */
	@Deprecated
	@Override
	public Block getTargetBlock( final HashSet<Byte> arg0, final int arg1 ) {
		return this.originator.getTargetBlock( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getTicksLived()
	 */
	@Override
	public int getTicksLived() {
		return this.originator.getTicksLived();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getTotalExperience()
	 */
	@Override
	public int getTotalExperience() {
		return this.originator.getTotalExperience();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getType()
	 */
	@Override
	public EntityType getType() {
		return this.originator.getType();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getUniqueId()
	 */
	@Override
	public UUID getUniqueId() {
		return this.originator.getUniqueId();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getVehicle()
	 */
	@Override
	public Entity getVehicle() {
		return this.originator.getVehicle();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getVelocity()
	 */
	@Override
	public Vector getVelocity() {
		return this.originator.getVelocity();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getWalkSpeed()
	 */
	@Override
	public float getWalkSpeed() {
		return this.originator.getWalkSpeed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getWorld()
	 */
	@Override
	public World getWorld() {
		return this.originator.getWorld();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#giveExp(int)
	 */
	@Override
	public void giveExp( final int arg0 ) {
		this.originator.giveExp( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#giveExpLevels(int)
	 */
	@Override
	public void giveExpLevels( final int arg0 ) {
		this.originator.giveExpLevels( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#hasAchievement(org.bukkit.Achievement)
	 */
	@Override
	public boolean hasAchievement( final Achievement arg0 ) {
		return this.originator.hasAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#hasLineOfSight(org.bukkit.entity.Entity)
	 */
	@Override
	public boolean hasLineOfSight( final Entity arg0 ) {
		return this.originator.hasLineOfSight( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.metadata.Metadatable#hasMetadata(java.lang.String)
	 */
	@Override
	public boolean hasMetadata( final String arg0 ) {
		return this.originator.hasMetadata( arg0 );
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
	 * @see org.bukkit.OfflinePlayer#hasPlayedBefore()
	 */
	@Override
	public boolean hasPlayedBefore() {
		return this.originator.hasPlayedBefore();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#hasPotionEffect(org.bukkit.potion.PotionEffectType)
	 */
	@Override
	public boolean hasPotionEffect( final PotionEffectType arg0 ) {
		return this.originator.hasPotionEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#hidePlayer(org.bukkit.entity.Player)
	 */
	@Override
	public void hidePlayer( final Player arg0 ) {
		this.originator.hidePlayer( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0, final EntityType arg1 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType, int)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0, final EntityType arg1, final int arg2 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic,
	 *      int)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0, final int arg1 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0, final Material arg1 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material, int)
	 */
	@Override
	public void incrementStatistic( final Statistic arg0, final Material arg1, final int arg2 ) throws IllegalArgumentException {
		this.originator.incrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isBanned()
	 */
	@Override
	public boolean isBanned() {
		return this.originator.isBanned();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#isBlocking()
	 */
	@Override
	public boolean isBlocking() {
		return this.originator.isBlocking();
	}




	/**
	 * @return
	 * @see org.bukkit.conversations.Conversable#isConversing()
	 */
	@Override
	public boolean isConversing() {
		return this.originator.isConversing();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#isCustomNameVisible()
	 */
	@Override
	public boolean isCustomNameVisible() {
		return this.originator.isCustomNameVisible();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isDead()
	 */
	@Override
	public boolean isDead() {
		return this.originator.isDead();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.originator.isEmpty();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isFlying()
	 */
	@Override
	public boolean isFlying() {
		return this.originator.isFlying();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isHealthScaled()
	 */
	@Override
	public boolean isHealthScaled() {
		return this.originator.isHealthScaled();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isInsideVehicle()
	 */
	@Override
	public boolean isInsideVehicle() {
		return this.originator.isInsideVehicle();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#isLeashed()
	 */
	@Override
	public boolean isLeashed() {
		return this.originator.isLeashed();
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.Player#isOnGround()
	 */
	@Deprecated
	@Override
	public boolean isOnGround() {
		return this.originator.isOnGround();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isOnline()
	 */
	@Override
	public boolean isOnline() {
		return this.originator.isOnline();
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




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isPlayerTimeRelative()
	 */
	@Override
	public boolean isPlayerTimeRelative() {
		return this.originator.isPlayerTimeRelative();
	}




	public boolean isSilent() {
		return this.silent;
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#isSleeping()
	 */
	@Override
	public boolean isSleeping() {
		return this.originator.isSleeping();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSleepingIgnored()
	 */
	@Override
	public boolean isSleepingIgnored() {
		return this.originator.isSleepingIgnored();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSneaking()
	 */
	@Override
	public boolean isSneaking() {
		return this.originator.isSneaking();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSprinting()
	 */
	@Override
	public boolean isSprinting() {
		return this.originator.isSprinting();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isValid()
	 */
	@Override
	public boolean isValid() {
		return this.originator.isValid();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isWhitelisted()
	 */
	@Override
	public boolean isWhitelisted() {
		return this.originator.isWhitelisted();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#kickPlayer(java.lang.String)
	 */
	@Override
	public void kickPlayer( final String arg0 ) {
		this.originator.kickPlayer( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.projectiles.ProjectileSource#launchProjectile(java.lang.Class)
	 */
	@Override
	public <T extends Projectile> T launchProjectile( final Class<? extends T> arg0 ) {
		return this.originator.launchProjectile( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.projectiles.ProjectileSource#launchProjectile(java.lang.Class,
	 *      org.bukkit.util.Vector)
	 */
	@Override
	public <T extends Projectile> T launchProjectile( final Class<? extends T> arg0, final Vector arg1 ) {
		return this.originator.launchProjectile( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#leaveVehicle()
	 */
	@Override
	public boolean leaveVehicle() {
		return this.originator.leaveVehicle();
	}




	/**
	 *
	 * @see org.bukkit.entity.Player#loadData()
	 */
	@Override
	public void loadData() {
		this.originator.loadData();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openEnchanting(org.bukkit.Location,
	 *      boolean)
	 */
	@Override
	public InventoryView openEnchanting( final Location arg0, final boolean arg1 ) {
		return this.originator.openEnchanting( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openInventory(org.bukkit.inventory.Inventory)
	 */
	@Override
	public InventoryView openInventory( final Inventory arg0 ) {
		return this.originator.openInventory( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#openInventory(org.bukkit.inventory.InventoryView)
	 */
	@Override
	public void openInventory( final InventoryView arg0 ) {
		this.originator.openInventory( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openWorkbench(org.bukkit.Location,
	 *      boolean)
	 */
	@Override
	public InventoryView openWorkbench( final Location arg0, final boolean arg1 ) {
		return this.originator.openWorkbench( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#performCommand(java.lang.String)
	 */
	@Override
	public boolean performCommand( final String arg0 ) {
		return this.originator.performCommand( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#playEffect(org.bukkit.EntityEffect)
	 */
	@Override
	public void playEffect( final EntityEffect arg0 ) {
		this.originator.playEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#playEffect(org.bukkit.Location,
	 *      org.bukkit.Effect, int)
	 */
	@Deprecated
	@Override
	public void playEffect( final Location arg0, final Effect arg1, final int arg2 ) {
		this.originator.playEffect( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#playEffect(org.bukkit.Location,
	 *      org.bukkit.Effect, java.lang.Object)
	 */
	@Override
	public <T> void playEffect( final Location arg0, final Effect arg1, final T arg2 ) {
		this.originator.playEffect( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#playNote(org.bukkit.Location, byte, byte)
	 */
	@Deprecated
	@Override
	public void playNote( final Location arg0, final byte arg1, final byte arg2 ) {
		this.originator.playNote( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#playNote(org.bukkit.Location,
	 *      org.bukkit.Instrument, org.bukkit.Note)
	 */
	@Override
	public void playNote( final Location arg0, final Instrument arg1, final Note arg2 ) {
		this.originator.playNote( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see org.bukkit.entity.Player#playSound(org.bukkit.Location,
	 *      org.bukkit.Sound, float, float)
	 */
	@Override
	public void playSound( final Location arg0, final Sound arg1, final float arg2, final float arg3 ) {
		this.originator.playSound( arg0, arg1, arg2, arg3 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @deprecated
	 * @see org.bukkit.entity.Player#playSound(org.bukkit.Location,
	 *      java.lang.String, float, float)
	 */
	@Deprecated
	@Override
	public void playSound( final Location arg0, final String arg1, final float arg2, final float arg3 ) {
		this.originator.playSound( arg0, arg1, arg2, arg3 );
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
	 *
	 * @see org.bukkit.entity.Entity#remove()
	 */
	@Override
	public void remove() {
		this.originator.remove();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#removeAchievement(org.bukkit.Achievement)
	 */
	@Override
	public void removeAchievement( final Achievement arg0 ) {
		this.originator.removeAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.permissions.Permissible#removeAttachment(org.bukkit.permissions.PermissionAttachment)
	 */
	@Override
	public void removeAttachment( final PermissionAttachment arg0 ) {
		this.originator.removeAttachment( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.metadata.Metadatable#removeMetadata(java.lang.String,
	 *      org.bukkit.plugin.Plugin)
	 */
	@Override
	public void removeMetadata( final String arg0, final Plugin arg1 ) {
		this.originator.removeMetadata( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#removePotionEffect(org.bukkit.potion.PotionEffectType)
	 */
	@Override
	public void removePotionEffect( final PotionEffectType arg0 ) {
		this.originator.removePotionEffect( arg0 );
	}




	/**
	 *
	 * @see org.bukkit.entity.Damageable#resetMaxHealth()
	 */
	@Override
	public void resetMaxHealth() {
		this.originator.resetMaxHealth();
	}




	/**
	 *
	 * @see org.bukkit.entity.Player#resetPlayerTime()
	 */
	@Override
	public void resetPlayerTime() {
		this.originator.resetPlayerTime();
	}




	/**
	 *
	 * @see org.bukkit.entity.Player#resetPlayerWeather()
	 */
	@Override
	public void resetPlayerWeather() {
		this.originator.resetPlayerWeather();
	}




	/**
	 *
	 * @see org.bukkit.entity.Player#saveData()
	 */
	@Override
	public void saveData() {
		this.originator.saveData();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendBlockChange(org.bukkit.Location, int,
	 *      byte)
	 */
	@Deprecated
	@Override
	public void sendBlockChange( final Location arg0, final int arg1, final byte arg2 ) {
		this.originator.sendBlockChange( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendBlockChange(org.bukkit.Location,
	 *      org.bukkit.Material, byte)
	 */
	@Deprecated
	@Override
	public void sendBlockChange( final Location arg0, final Material arg1, final byte arg2 ) {
		this.originator.sendBlockChange( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendChunkChange(org.bukkit.Location, int,
	 *      int, int, byte[])
	 */
	@Deprecated
	@Override
	public boolean sendChunkChange( final Location arg0, final int arg1, final int arg2, final int arg3, final byte[] arg4 ) {
		return this.originator.sendChunkChange( arg0, arg1, arg2, arg3, arg4 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#sendMap(org.bukkit.map.MapView)
	 */
	@Override
	public void sendMap( final MapView arg0 ) {
		this.originator.sendMap( arg0 );
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




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.plugin.messaging.PluginMessageRecipient#sendPluginMessage(org.bukkit.plugin.Plugin,
	 *      java.lang.String, byte[])
	 */
	@Override
	public void sendPluginMessage( final Plugin arg0, final String arg1, final byte[] arg2 ) {
		this.originator.sendPluginMessage( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#sendRawMessage(java.lang.String)
	 */
	@Override
	public void sendRawMessage( final String arg0 ) {
		this.originator.sendRawMessage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#sendSignChange(org.bukkit.Location,
	 *      java.lang.String[])
	 */
	@Override
	public void sendSignChange( final Location arg0, final String[] arg1 ) throws IllegalArgumentException {
		this.originator.sendSignChange( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.configuration.serialization.ConfigurationSerializable#serialize()
	 */
	@Override
	public Map<String, Object> serialize() {
		return this.originator.serialize();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setAllowFlight(boolean)
	 */
	@Override
	public void setAllowFlight( final boolean arg0 ) {
		this.originator.setAllowFlight( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.OfflinePlayer#setBanned(boolean)
	 */
	@Deprecated
	@Override
	public void setBanned( final boolean arg0 ) {
		this.originator.setBanned( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setBedSpawnLocation(org.bukkit.Location)
	 */
	@Override
	public void setBedSpawnLocation( final Location arg0 ) {
		this.originator.setBedSpawnLocation( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Player#setBedSpawnLocation(org.bukkit.Location,
	 *      boolean)
	 */
	@Override
	public void setBedSpawnLocation( final Location arg0, final boolean arg1 ) {
		this.originator.setBedSpawnLocation( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCanPickupItems(boolean)
	 */
	@Override
	public void setCanPickupItems( final boolean arg0 ) {
		this.originator.setCanPickupItems( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setCompassTarget(org.bukkit.Location)
	 */
	@Override
	public void setCompassTarget( final Location arg0 ) {
		this.originator.setCompassTarget( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCustomName(java.lang.String)
	 */
	@Override
	public void setCustomName( final String arg0 ) {
		this.originator.setCustomName( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCustomNameVisible(boolean)
	 */
	@Override
	public void setCustomNameVisible( final boolean arg0 ) {
		this.originator.setCustomNameVisible( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setDisplayName(java.lang.String)
	 */
	@Override
	public void setDisplayName( final String arg0 ) {
		this.originator.setDisplayName( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setExhaustion(float)
	 */
	@Override
	public void setExhaustion( final float arg0 ) {
		this.originator.setExhaustion( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setExp(float)
	 */
	@Override
	public void setExp( final float arg0 ) {
		this.originator.setExp( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setFallDistance(float)
	 */
	@Override
	public void setFallDistance( final float arg0 ) {
		this.originator.setFallDistance( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setFireTicks(int)
	 */
	@Override
	public void setFireTicks( final int arg0 ) {
		this.originator.setFireTicks( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setFlying(boolean)
	 */
	@Override
	public void setFlying( final boolean arg0 ) {
		this.originator.setFlying( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setFlySpeed(float)
	 */
	@Override
	public void setFlySpeed( final float arg0 ) throws IllegalArgumentException {
		this.originator.setFlySpeed( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setFoodLevel(int)
	 */
	@Override
	public void setFoodLevel( final int arg0 ) {
		this.originator.setFoodLevel( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setGameMode(org.bukkit.GameMode)
	 */
	@Override
	public void setGameMode( final GameMode arg0 ) {
		this.originator.setGameMode( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#setHealth(double)
	 */
	@Override
	public void setHealth( final double arg0 ) {
		this.originator.setHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#setHealth(int)
	 */
	@Deprecated
	public void setHealth( final int arg0 ) {
		this.originator.setHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setHealthScale(double)
	 */
	@Override
	public void setHealthScale( final double arg0 ) throws IllegalArgumentException {
		this.originator.setHealthScale( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setHealthScaled(boolean)
	 */
	@Override
	public void setHealthScaled( final boolean arg0 ) {
		this.originator.setHealthScaled( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setItemInHand(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public void setItemInHand( final ItemStack arg0 ) {
		this.originator.setItemInHand( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setItemOnCursor(org.bukkit.inventory.ItemStack)
	 */
	@Override
	public void setItemOnCursor( final ItemStack arg0 ) {
		this.originator.setItemOnCursor( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setLastDamage(double)
	 */
	@Override
	public void setLastDamage( final double arg0 ) {
		this.originator.setLastDamage( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#setLastDamage(int)
	 */
	@Deprecated
	public void setLastDamage( final int arg0 ) {
		this.originator.setLastDamage( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setLastDamageCause(org.bukkit.event.entity.EntityDamageEvent)
	 */
	@Override
	public void setLastDamageCause( final EntityDamageEvent arg0 ) {
		this.originator.setLastDamageCause( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#setLeashHolder(org.bukkit.entity.Entity)
	 */
	@Override
	public boolean setLeashHolder( final Entity arg0 ) {
		return this.originator.setLeashHolder( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setLevel(int)
	 */
	@Override
	public void setLevel( final int arg0 ) {
		this.originator.setLevel( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#setMaxHealth(double)
	 */
	@Override
	public void setMaxHealth( final double arg0 ) {
		this.originator.setMaxHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#setMaxHealth(int)
	 */
	@Deprecated
	public void setMaxHealth( final int arg0 ) {
		this.originator.setMaxHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setMaximumAir(int)
	 */
	@Override
	public void setMaximumAir( final int arg0 ) {
		this.originator.setMaximumAir( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setMaximumNoDamageTicks(int)
	 */
	@Override
	public void setMaximumNoDamageTicks( final int arg0 ) {
		this.originator.setMaximumNoDamageTicks( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.metadata.Metadatable#setMetadata(java.lang.String,
	 *      org.bukkit.metadata.MetadataValue)
	 */
	@Override
	public void setMetadata( final String arg0, final MetadataValue arg1 ) {
		this.originator.setMetadata( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setNoDamageTicks(int)
	 */
	@Override
	public void setNoDamageTicks( final int arg0 ) {
		this.originator.setNoDamageTicks( arg0 );
	}




	@Override
	public void setOp( final boolean arg0 ) {
		this.originator.setOp( arg0 );

	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#setPassenger(org.bukkit.entity.Entity)
	 */
	@Override
	public boolean setPassenger( final Entity arg0 ) {
		return this.originator.setPassenger( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setPlayerListName(java.lang.String)
	 */
	@Override
	public void setPlayerListName( final String arg0 ) {
		this.originator.setPlayerListName( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Player#setPlayerTime(long, boolean)
	 */
	@Override
	public void setPlayerTime( final long arg0, final boolean arg1 ) {
		this.originator.setPlayerTime( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setPlayerWeather(org.bukkit.WeatherType)
	 */
	@Override
	public void setPlayerWeather( final WeatherType arg0 ) {
		this.originator.setPlayerWeather( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setRemainingAir(int)
	 */
	@Override
	public void setRemainingAir( final int arg0 ) {
		this.originator.setRemainingAir( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setRemoveWhenFarAway(boolean)
	 */
	@Override
	public void setRemoveWhenFarAway( final boolean arg0 ) {
		this.originator.setRemoveWhenFarAway( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setResourcePack(java.lang.String)
	 */
	@Override
	public void setResourcePack( final String arg0 ) {
		this.originator.setResourcePack( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSaturation(float)
	 */
	@Override
	public void setSaturation( final float arg0 ) {
		this.originator.setSaturation( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @see org.bukkit.entity.Player#setScoreboard(org.bukkit.scoreboard.Scoreboard)
	 */
	@Override
	public void setScoreboard( final Scoreboard arg0 ) throws IllegalArgumentException, IllegalStateException {
		this.originator.setScoreboard( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSleepingIgnored(boolean)
	 */
	@Override
	public void setSleepingIgnored( final boolean arg0 ) {
		this.originator.setSleepingIgnored( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSneaking(boolean)
	 */
	@Override
	public void setSneaking( final boolean arg0 ) {
		this.originator.setSneaking( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSprinting(boolean)
	 */
	@Override
	public void setSprinting( final boolean arg0 ) {
		this.originator.setSprinting( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic,
	 *      org.bukkit.entity.EntityType, int)
	 */
	@Override
	public void setStatistic( final Statistic arg0, final EntityType arg1, final int arg2 ) {
		this.originator.setStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic, int)
	 */
	@Override
	public void setStatistic( final Statistic arg0, final int arg1 ) throws IllegalArgumentException {
		this.originator.setStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic,
	 *      org.bukkit.Material, int)
	 */
	@Override
	public void setStatistic( final Statistic arg0, final Material arg1, final int arg2 ) throws IllegalArgumentException {
		this.originator.setStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Player#setTexturePack(java.lang.String)
	 */
	@Deprecated
	@Override
	public void setTexturePack( final String arg0 ) {
		this.originator.setTexturePack( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setTicksLived(int)
	 */
	@Override
	public void setTicksLived( final int arg0 ) {
		this.originator.setTicksLived( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setTotalExperience(int)
	 */
	@Override
	public void setTotalExperience( final int arg0 ) {
		this.originator.setTotalExperience( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setVelocity(org.bukkit.util.Vector)
	 */
	@Override
	public void setVelocity( final Vector arg0 ) {
		this.originator.setVelocity( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setWalkSpeed(float)
	 */
	@Override
	public void setWalkSpeed( final float arg0 ) throws IllegalArgumentException {
		this.originator.setWalkSpeed( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.OfflinePlayer#setWhitelisted(boolean)
	 */
	@Override
	public void setWhitelisted( final boolean arg0 ) {
		this.originator.setWhitelisted( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#setWindowProperty(org.bukkit.inventory.InventoryView.Property,
	 *      int)
	 */
	@Override
	public boolean setWindowProperty( final Property arg0, final int arg1 ) {
		return this.originator.setWindowProperty( arg0, arg1 );
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#shootArrow()
	 */
	@Deprecated
	@Override
	public Arrow shootArrow() {
		return this.originator.shootArrow();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#showPlayer(org.bukkit.entity.Player)
	 */
	@Override
	public void showPlayer( final Player arg0 ) {
		this.originator.showPlayer( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.entity.Entity)
	 */
	@Override
	public boolean teleport( final Entity arg0 ) {
		return this.originator.teleport( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.entity.Entity,
	 *      org.bukkit.event.player.PlayerTeleportEvent.TeleportCause)
	 */
	@Override
	public boolean teleport( final Entity arg0, final TeleportCause arg1 ) {
		return this.originator.teleport( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.Location)
	 */
	@Override
	public boolean teleport( final Location arg0 ) {
		return this.originator.teleport( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.Location,
	 *      org.bukkit.event.player.PlayerTeleportEvent.TeleportCause)
	 */
	@Override
	public boolean teleport( final Location arg0, final TeleportCause arg1 ) {
		return this.originator.teleport( arg0, arg1 );
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#throwEgg()
	 */
	@Deprecated
	@Override
	public Egg throwEgg() {
		return this.originator.throwEgg();
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#throwSnowball()
	 */
	@Deprecated
	@Override
	public Snowball throwSnowball() {
		return this.originator.throwSnowball();
	}




	/**
	 * @deprecated
	 * @see org.bukkit.entity.Player#updateInventory()
	 */
	@Deprecated
	@Override
	public void updateInventory() {
		this.originator.updateInventory();
	}

}

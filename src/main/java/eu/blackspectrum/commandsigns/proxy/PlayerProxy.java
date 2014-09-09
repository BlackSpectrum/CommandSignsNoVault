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
	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation, org.bukkit.conversations.ConversationAbandonedEvent)
	 */
	public void abandonConversation( Conversation arg0, ConversationAbandonedEvent arg1 ) {
		originator.abandonConversation( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.conversations.Conversable#abandonConversation(org.bukkit.conversations.Conversation)
	 */
	public void abandonConversation( Conversation arg0 ) {
		originator.abandonConversation( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.conversations.Conversable#acceptConversationInput(java.lang.String)
	 */
	public void acceptConversationInput( String arg0 ) {
		originator.acceptConversationInput( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffect(org.bukkit.potion.PotionEffect, boolean)
	 */
	public boolean addPotionEffect( PotionEffect arg0, boolean arg1 ) {
		return originator.addPotionEffect( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffect(org.bukkit.potion.PotionEffect)
	 */
	public boolean addPotionEffect( PotionEffect arg0 ) {
		return originator.addPotionEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#addPotionEffects(java.util.Collection)
	 */
	public boolean addPotionEffects( Collection<PotionEffect> arg0 ) {
		return originator.addPotionEffects( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#awardAchievement(org.bukkit.Achievement)
	 */
	public void awardAchievement( Achievement arg0 ) {
		originator.awardAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.conversations.Conversable#beginConversation(org.bukkit.conversations.Conversation)
	 */
	public boolean beginConversation( Conversation arg0 ) {
		return originator.beginConversation( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#canSee(org.bukkit.entity.Player)
	 */
	public boolean canSee( Player arg0 ) {
		return originator.canSee( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#chat(java.lang.String)
	 */
	public void chat( String arg0 ) {
		originator.chat( arg0 );
	}




	/**
	 * 
	 * @see org.bukkit.entity.HumanEntity#closeInventory()
	 */
	public void closeInventory() {
		originator.closeInventory();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Damageable#damage(double, org.bukkit.entity.Entity)
	 */
	public void damage( double arg0, Entity arg1 ) {
		originator.damage( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#damage(double)
	 */
	public void damage( double arg0 ) {
		originator.damage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#damage(int, org.bukkit.entity.Entity)
	 */
	public void damage( int arg0, Entity arg1 ) {
		originator.damage( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#damage(int)
	 */
	public void damage( int arg0 ) {
		originator.damage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType, int)
	 */
	public void decrementStatistic( Statistic arg0, EntityType arg1, int arg2 ) {
		originator.decrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType)
	 */
	public void decrementStatistic( Statistic arg0, EntityType arg1 ) throws IllegalArgumentException {
		originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic, int)
	 */
	public void decrementStatistic( Statistic arg0, int arg1 ) throws IllegalArgumentException {
		originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic, org.bukkit.Material, int)
	 */
	public void decrementStatistic( Statistic arg0, Material arg1, int arg2 ) throws IllegalArgumentException {
		originator.decrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic, org.bukkit.Material)
	 */
	public void decrementStatistic( Statistic arg0, Material arg1 ) throws IllegalArgumentException {
		originator.decrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#decrementStatistic(org.bukkit.Statistic)
	 */
	public void decrementStatistic( Statistic arg0 ) throws IllegalArgumentException {
		originator.decrementStatistic( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#eject()
	 */
	public boolean eject() {
		return originator.eject();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getActivePotionEffects()
	 */
	public Collection<PotionEffect> getActivePotionEffects() {
		return originator.getActivePotionEffects();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getAddress()
	 */
	public InetSocketAddress getAddress() {
		return originator.getAddress();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getAllowFlight()
	 */
	public boolean getAllowFlight() {
		return originator.getAllowFlight();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getBedSpawnLocation()
	 */
	public Location getBedSpawnLocation() {
		return originator.getBedSpawnLocation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getCanPickupItems()
	 */
	public boolean getCanPickupItems() {
		return originator.getCanPickupItems();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getCompassTarget()
	 */
	public Location getCompassTarget() {
		return originator.getCompassTarget();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getCustomName()
	 */
	public String getCustomName() {
		return originator.getCustomName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getDisplayName()
	 */
	public String getDisplayName() {
		return originator.getDisplayName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getEnderChest()
	 */
	public Inventory getEnderChest() {
		return originator.getEnderChest();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getEntityId()
	 */
	public int getEntityId() {
		return originator.getEntityId();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEquipment()
	 */
	public EntityEquipment getEquipment() {
		return originator.getEquipment();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getExhaustion()
	 */
	public float getExhaustion() {
		return originator.getExhaustion();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getExp()
	 */
	public float getExp() {
		return originator.getExp();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getExpToLevel()
	 */
	public int getExpToLevel() {
		return originator.getExpToLevel();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeHeight()
	 */
	public double getEyeHeight() {
		return originator.getEyeHeight();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeHeight(boolean)
	 */
	public double getEyeHeight( boolean arg0 ) {
		return originator.getEyeHeight( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getEyeLocation()
	 */
	public Location getEyeLocation() {
		return originator.getEyeLocation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getFallDistance()
	 */
	public float getFallDistance() {
		return originator.getFallDistance();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getFireTicks()
	 */
	public int getFireTicks() {
		return originator.getFireTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getFirstPlayed()
	 */
	public long getFirstPlayed() {
		return originator.getFirstPlayed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getFlySpeed()
	 */
	public float getFlySpeed() {
		return originator.getFlySpeed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getFoodLevel()
	 */
	public int getFoodLevel() {
		return originator.getFoodLevel();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getGameMode()
	 */
	public GameMode getGameMode() {
		return originator.getGameMode();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Damageable#getHealth()
	 */
	public double getHealth() {
		return ((Damageable)originator).getHealth();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getHealthScale()
	 */
	public double getHealthScale() {
		return originator.getHealthScale();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getInventory()
	 */
	public PlayerInventory getInventory() {
		return originator.getInventory();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getItemInHand()
	 */
	public ItemStack getItemInHand() {
		return originator.getItemInHand();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getItemOnCursor()
	 */
	public ItemStack getItemOnCursor() {
		return originator.getItemOnCursor();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getKiller()
	 */
	public Player getKiller() {
		return originator.getKiller();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getLastDamage()
	 */
	public double getLastDamage() {
		return originator.getLastDamage();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getLastDamageCause()
	 */
	public EntityDamageEvent getLastDamageCause() {
		return originator.getLastDamageCause();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getLastPlayed()
	 */
	public long getLastPlayed() {
		return originator.getLastPlayed();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getLastTwoTargetBlocks(java.util.HashSet, int)
	 */
	public List<Block> getLastTwoTargetBlocks( HashSet<Byte> arg0, int arg1 ) {
		return originator.getLastTwoTargetBlocks( arg0, arg1 );
	}




	/**
	 * @return
	 * @throws IllegalStateException
	 * @see org.bukkit.entity.LivingEntity#getLeashHolder()
	 */
	public Entity getLeashHolder() throws IllegalStateException {
		return originator.getLeashHolder();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getLevel()
	 */
	public int getLevel() {
		return originator.getLevel();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getLineOfSight(java.util.HashSet, int)
	 */
	public List<Block> getLineOfSight( HashSet<Byte> arg0, int arg1 ) {
		return originator.getLineOfSight( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.plugin.messaging.PluginMessageRecipient#getListeningPluginChannels()
	 */
	public Set<String> getListeningPluginChannels() {
		return originator.getListeningPluginChannels();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getLocation()
	 */
	public Location getLocation() {
		return originator.getLocation();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#getLocation(org.bukkit.Location)
	 */
	public Location getLocation( Location arg0 ) {
		return originator.getLocation( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getMaxFireTicks()
	 */
	public int getMaxFireTicks() {
		return originator.getMaxFireTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Damageable#getMaxHealth()
	 */
	public double getMaxHealth() {
		return ((Damageable)originator).getMaxHealth();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getMaximumAir()
	 */
	public int getMaximumAir() {
		return originator.getMaximumAir();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getMaximumNoDamageTicks()
	 */
	public int getMaximumNoDamageTicks() {
		return originator.getMaximumNoDamageTicks();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.metadata.Metadatable#getMetadata(java.lang.String)
	 */
	public List<MetadataValue> getMetadata( String arg0 ) {
		return originator.getMetadata( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @see org.bukkit.entity.Entity#getNearbyEntities(double, double, double)
	 */
	public List<Entity> getNearbyEntities( double arg0, double arg1, double arg2 ) {
		return originator.getNearbyEntities( arg0, arg1, arg2 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getNoDamageTicks()
	 */
	public int getNoDamageTicks() {
		return originator.getNoDamageTicks();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getOpenInventory()
	 */
	public InventoryView getOpenInventory() {
		return originator.getOpenInventory();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getPassenger()
	 */
	public Entity getPassenger() {
		return originator.getPassenger();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#getPlayer()
	 */
	public Player getPlayer() {
		return originator.getPlayer();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerListName()
	 */
	public String getPlayerListName() {
		return originator.getPlayerListName();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerTime()
	 */
	public long getPlayerTime() {
		return originator.getPlayerTime();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerTimeOffset()
	 */
	public long getPlayerTimeOffset() {
		return originator.getPlayerTimeOffset();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getPlayerWeather()
	 */
	public WeatherType getPlayerWeather() {
		return originator.getPlayerWeather();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getRemainingAir()
	 */
	public int getRemainingAir() {
		return originator.getRemainingAir();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#getRemoveWhenFarAway()
	 */
	public boolean getRemoveWhenFarAway() {
		return originator.getRemoveWhenFarAway();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getSaturation()
	 */
	public float getSaturation() {
		return originator.getSaturation();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getScoreboard()
	 */
	public Scoreboard getScoreboard() {
		return originator.getScoreboard();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#getSleepTicks()
	 */
	public int getSleepTicks() {
		return originator.getSleepTicks();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType)
	 */
	public int getStatistic( Statistic arg0, EntityType arg1 ) throws IllegalArgumentException {
		return originator.getStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic, org.bukkit.Material)
	 */
	public int getStatistic( Statistic arg0, Material arg1 ) throws IllegalArgumentException {
		return originator.getStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#getStatistic(org.bukkit.Statistic)
	 */
	public int getStatistic( Statistic arg0 ) throws IllegalArgumentException {
		return originator.getStatistic( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#getTargetBlock(java.util.HashSet, int)
	 */
	public Block getTargetBlock( HashSet<Byte> arg0, int arg1 ) {
		return originator.getTargetBlock( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getTicksLived()
	 */
	public int getTicksLived() {
		return originator.getTicksLived();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getTotalExperience()
	 */
	public int getTotalExperience() {
		return originator.getTotalExperience();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getType()
	 */
	public EntityType getType() {
		return originator.getType();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getUniqueId()
	 */
	public UUID getUniqueId() {
		return originator.getUniqueId();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getVehicle()
	 */
	public Entity getVehicle() {
		return originator.getVehicle();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getVelocity()
	 */
	public Vector getVelocity() {
		return originator.getVelocity();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#getWalkSpeed()
	 */
	public float getWalkSpeed() {
		return originator.getWalkSpeed();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#getWorld()
	 */
	public World getWorld() {
		return originator.getWorld();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#giveExp(int)
	 */
	public void giveExp( int arg0 ) {
		originator.giveExp( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#giveExpLevels(int)
	 */
	public void giveExpLevels( int arg0 ) {
		originator.giveExpLevels( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#hasAchievement(org.bukkit.Achievement)
	 */
	public boolean hasAchievement( Achievement arg0 ) {
		return originator.hasAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#hasLineOfSight(org.bukkit.entity.Entity)
	 */
	public boolean hasLineOfSight( Entity arg0 ) {
		return originator.hasLineOfSight( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.metadata.Metadatable#hasMetadata(java.lang.String)
	 */
	public boolean hasMetadata( String arg0 ) {
		return originator.hasMetadata( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#hasPlayedBefore()
	 */
	public boolean hasPlayedBefore() {
		return originator.hasPlayedBefore();
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#hasPotionEffect(org.bukkit.potion.PotionEffectType)
	 */
	public boolean hasPotionEffect( PotionEffectType arg0 ) {
		return originator.hasPotionEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#hidePlayer(org.bukkit.entity.Player)
	 */
	public void hidePlayer( Player arg0 ) {
		originator.hidePlayer( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType, int)
	 */
	public void incrementStatistic( Statistic arg0, EntityType arg1, int arg2 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType)
	 */
	public void incrementStatistic( Statistic arg0, EntityType arg1 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic, int)
	 */
	public void incrementStatistic( Statistic arg0, int arg1 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic, org.bukkit.Material, int)
	 */
	public void incrementStatistic( Statistic arg0, Material arg1, int arg2 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic, org.bukkit.Material)
	 */
	public void incrementStatistic( Statistic arg0, Material arg1 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#incrementStatistic(org.bukkit.Statistic)
	 */
	public void incrementStatistic( Statistic arg0 ) throws IllegalArgumentException {
		originator.incrementStatistic( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isBanned()
	 */
	public boolean isBanned() {
		return originator.isBanned();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#isBlocking()
	 */
	public boolean isBlocking() {
		return originator.isBlocking();
	}




	/**
	 * @return
	 * @see org.bukkit.conversations.Conversable#isConversing()
	 */
	public boolean isConversing() {
		return originator.isConversing();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#isCustomNameVisible()
	 */
	public boolean isCustomNameVisible() {
		return originator.isCustomNameVisible();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isDead()
	 */
	public boolean isDead() {
		return originator.isDead();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isEmpty()
	 */
	public boolean isEmpty() {
		return originator.isEmpty();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isFlying()
	 */
	public boolean isFlying() {
		return originator.isFlying();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isHealthScaled()
	 */
	public boolean isHealthScaled() {
		return originator.isHealthScaled();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isInsideVehicle()
	 */
	public boolean isInsideVehicle() {
		return originator.isInsideVehicle();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.LivingEntity#isLeashed()
	 */
	public boolean isLeashed() {
		return originator.isLeashed();
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.Player#isOnGround()
	 */
	public boolean isOnGround() {
		return originator.isOnGround();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isOnline()
	 */
	public boolean isOnline() {
		return originator.isOnline();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isPlayerTimeRelative()
	 */
	public boolean isPlayerTimeRelative() {
		return originator.isPlayerTimeRelative();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.HumanEntity#isSleeping()
	 */
	public boolean isSleeping() {
		return originator.isSleeping();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSleepingIgnored()
	 */
	public boolean isSleepingIgnored() {
		return originator.isSleepingIgnored();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSneaking()
	 */
	public boolean isSneaking() {
		return originator.isSneaking();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Player#isSprinting()
	 */
	public boolean isSprinting() {
		return originator.isSprinting();
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#isValid()
	 */
	public boolean isValid() {
		return originator.isValid();
	}




	/**
	 * @return
	 * @see org.bukkit.OfflinePlayer#isWhitelisted()
	 */
	public boolean isWhitelisted() {
		return originator.isWhitelisted();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#kickPlayer(java.lang.String)
	 */
	public void kickPlayer( String arg0 ) {
		originator.kickPlayer( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.projectiles.ProjectileSource#launchProjectile(java.lang.Class, org.bukkit.util.Vector)
	 */
	public <T extends Projectile> T launchProjectile( Class<? extends T> arg0, Vector arg1 ) {
		return originator.launchProjectile( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.projectiles.ProjectileSource#launchProjectile(java.lang.Class)
	 */
	public <T extends Projectile> T launchProjectile( Class<? extends T> arg0 ) {
		return originator.launchProjectile( arg0 );
	}




	/**
	 * @return
	 * @see org.bukkit.entity.Entity#leaveVehicle()
	 */
	public boolean leaveVehicle() {
		return originator.leaveVehicle();
	}




	/**
	 * 
	 * @see org.bukkit.entity.Player#loadData()
	 */
	public void loadData() {
		originator.loadData();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openEnchanting(org.bukkit.Location, boolean)
	 */
	public InventoryView openEnchanting( Location arg0, boolean arg1 ) {
		return originator.openEnchanting( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openInventory(org.bukkit.inventory.Inventory)
	 */
	public InventoryView openInventory( Inventory arg0 ) {
		return originator.openInventory( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#openInventory(org.bukkit.inventory.InventoryView)
	 */
	public void openInventory( InventoryView arg0 ) {
		originator.openInventory( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#openWorkbench(org.bukkit.Location, boolean)
	 */
	public InventoryView openWorkbench( Location arg0, boolean arg1 ) {
		return originator.openWorkbench( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Player#performCommand(java.lang.String)
	 */
	public boolean performCommand( String arg0 ) {
		return originator.performCommand( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#playEffect(org.bukkit.EntityEffect)
	 */
	public void playEffect( EntityEffect arg0 ) {
		originator.playEffect( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#playEffect(org.bukkit.Location, org.bukkit.Effect, int)
	 */
	public void playEffect( Location arg0, Effect arg1, int arg2 ) {
		originator.playEffect( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#playEffect(org.bukkit.Location, org.bukkit.Effect, java.lang.Object)
	 */
	public <T> void playEffect( Location arg0, Effect arg1, T arg2 ) {
		originator.playEffect( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#playNote(org.bukkit.Location, byte, byte)
	 */
	public void playNote( Location arg0, byte arg1, byte arg2 ) {
		originator.playNote( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#playNote(org.bukkit.Location, org.bukkit.Instrument, org.bukkit.Note)
	 */
	public void playNote( Location arg0, Instrument arg1, Note arg2 ) {
		originator.playNote( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @see org.bukkit.entity.Player#playSound(org.bukkit.Location, org.bukkit.Sound, float, float)
	 */
	public void playSound( Location arg0, Sound arg1, float arg2, float arg3 ) {
		originator.playSound( arg0, arg1, arg2, arg3 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @deprecated
	 * @see org.bukkit.entity.Player#playSound(org.bukkit.Location, java.lang.String, float, float)
	 */
	public void playSound( Location arg0, String arg1, float arg2, float arg3 ) {
		originator.playSound( arg0, arg1, arg2, arg3 );
	}




	/**
	 * 
	 * @see org.bukkit.entity.Entity#remove()
	 */
	public void remove() {
		originator.remove();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#removeAchievement(org.bukkit.Achievement)
	 */
	public void removeAchievement( Achievement arg0 ) {
		originator.removeAchievement( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.metadata.Metadatable#removeMetadata(java.lang.String, org.bukkit.plugin.Plugin)
	 */
	public void removeMetadata( String arg0, Plugin arg1 ) {
		originator.removeMetadata( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#removePotionEffect(org.bukkit.potion.PotionEffectType)
	 */
	public void removePotionEffect( PotionEffectType arg0 ) {
		originator.removePotionEffect( arg0 );
	}




	/**
	 * 
	 * @see org.bukkit.entity.Damageable#resetMaxHealth()
	 */
	public void resetMaxHealth() {
		originator.resetMaxHealth();
	}




	/**
	 * 
	 * @see org.bukkit.entity.Player#resetPlayerTime()
	 */
	public void resetPlayerTime() {
		originator.resetPlayerTime();
	}




	/**
	 * 
	 * @see org.bukkit.entity.Player#resetPlayerWeather()
	 */
	public void resetPlayerWeather() {
		originator.resetPlayerWeather();
	}




	/**
	 * 
	 * @see org.bukkit.entity.Player#saveData()
	 */
	public void saveData() {
		originator.saveData();
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendBlockChange(org.bukkit.Location, int, byte)
	 */
	public void sendBlockChange( Location arg0, int arg1, byte arg2 ) {
		originator.sendBlockChange( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)
	 */
	public void sendBlockChange( Location arg0, Material arg1, byte arg2 ) {
		originator.sendBlockChange( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.Player#sendChunkChange(org.bukkit.Location, int, int, int, byte[])
	 */
	public boolean sendChunkChange( Location arg0, int arg1, int arg2, int arg3, byte[] arg4 ) {
		return originator.sendChunkChange( arg0, arg1, arg2, arg3, arg4 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#sendMap(org.bukkit.map.MapView)
	 */
	public void sendMap( MapView arg0 ) {
		originator.sendMap( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.plugin.messaging.PluginMessageRecipient#sendPluginMessage(org.bukkit.plugin.Plugin, java.lang.String, byte[])
	 */
	public void sendPluginMessage( Plugin arg0, String arg1, byte[] arg2 ) {
		originator.sendPluginMessage( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#sendRawMessage(java.lang.String)
	 */
	public void sendRawMessage( String arg0 ) {
		originator.sendRawMessage( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#sendSignChange(org.bukkit.Location, java.lang.String[])
	 */
	public void sendSignChange( Location arg0, String[] arg1 ) throws IllegalArgumentException {
		originator.sendSignChange( arg0, arg1 );
	}




	/**
	 * @return
	 * @see org.bukkit.configuration.serialization.ConfigurationSerializable#serialize()
	 */
	public Map<String, Object> serialize() {
		return originator.serialize();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setAllowFlight(boolean)
	 */
	public void setAllowFlight( boolean arg0 ) {
		originator.setAllowFlight( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.OfflinePlayer#setBanned(boolean)
	 */
	public void setBanned( boolean arg0 ) {
		originator.setBanned( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Player#setBedSpawnLocation(org.bukkit.Location, boolean)
	 */
	public void setBedSpawnLocation( Location arg0, boolean arg1 ) {
		originator.setBedSpawnLocation( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setBedSpawnLocation(org.bukkit.Location)
	 */
	public void setBedSpawnLocation( Location arg0 ) {
		originator.setBedSpawnLocation( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCanPickupItems(boolean)
	 */
	public void setCanPickupItems( boolean arg0 ) {
		originator.setCanPickupItems( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setCompassTarget(org.bukkit.Location)
	 */
	public void setCompassTarget( Location arg0 ) {
		originator.setCompassTarget( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCustomName(java.lang.String)
	 */
	public void setCustomName( String arg0 ) {
		originator.setCustomName( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setCustomNameVisible(boolean)
	 */
	public void setCustomNameVisible( boolean arg0 ) {
		originator.setCustomNameVisible( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setDisplayName(java.lang.String)
	 */
	public void setDisplayName( String arg0 ) {
		originator.setDisplayName( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setExhaustion(float)
	 */
	public void setExhaustion( float arg0 ) {
		originator.setExhaustion( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setExp(float)
	 */
	public void setExp( float arg0 ) {
		originator.setExp( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setFallDistance(float)
	 */
	public void setFallDistance( float arg0 ) {
		originator.setFallDistance( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setFireTicks(int)
	 */
	public void setFireTicks( int arg0 ) {
		originator.setFireTicks( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setFlySpeed(float)
	 */
	public void setFlySpeed( float arg0 ) throws IllegalArgumentException {
		originator.setFlySpeed( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setFlying(boolean)
	 */
	public void setFlying( boolean arg0 ) {
		originator.setFlying( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setFoodLevel(int)
	 */
	public void setFoodLevel( int arg0 ) {
		originator.setFoodLevel( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setGameMode(org.bukkit.GameMode)
	 */
	public void setGameMode( GameMode arg0 ) {
		originator.setGameMode( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#setHealth(double)
	 */
	public void setHealth( double arg0 ) {
		originator.setHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#setHealth(int)
	 */
	public void setHealth( int arg0 ) {
		originator.setHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setHealthScale(double)
	 */
	public void setHealthScale( double arg0 ) throws IllegalArgumentException {
		originator.setHealthScale( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setHealthScaled(boolean)
	 */
	public void setHealthScaled( boolean arg0 ) {
		originator.setHealthScaled( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setItemInHand(org.bukkit.inventory.ItemStack)
	 */
	public void setItemInHand( ItemStack arg0 ) {
		originator.setItemInHand( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.HumanEntity#setItemOnCursor(org.bukkit.inventory.ItemStack)
	 */
	public void setItemOnCursor( ItemStack arg0 ) {
		originator.setItemOnCursor( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setLastDamage(double)
	 */
	public void setLastDamage( double arg0 ) {
		originator.setLastDamage( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#setLastDamage(int)
	 */
	public void setLastDamage( int arg0 ) {
		originator.setLastDamage( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setLastDamageCause(org.bukkit.event.entity.EntityDamageEvent)
	 */
	public void setLastDamageCause( EntityDamageEvent arg0 ) {
		originator.setLastDamageCause( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.LivingEntity#setLeashHolder(org.bukkit.entity.Entity)
	 */
	public boolean setLeashHolder( Entity arg0 ) {
		return originator.setLeashHolder( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setLevel(int)
	 */
	public void setLevel( int arg0 ) {
		originator.setLevel( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Damageable#setMaxHealth(double)
	 */
	public void setMaxHealth( double arg0 ) {
		originator.setMaxHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Damageable#setMaxHealth(int)
	 */
	public void setMaxHealth( int arg0 ) {
		originator.setMaxHealth( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setMaximumAir(int)
	 */
	public void setMaximumAir( int arg0 ) {
		originator.setMaximumAir( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setMaximumNoDamageTicks(int)
	 */
	public void setMaximumNoDamageTicks( int arg0 ) {
		originator.setMaximumNoDamageTicks( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.metadata.Metadatable#setMetadata(java.lang.String, org.bukkit.metadata.MetadataValue)
	 */
	public void setMetadata( String arg0, MetadataValue arg1 ) {
		originator.setMetadata( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setNoDamageTicks(int)
	 */
	public void setNoDamageTicks( int arg0 ) {
		originator.setNoDamageTicks( arg0 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#setPassenger(org.bukkit.entity.Entity)
	 */
	public boolean setPassenger( Entity arg0 ) {
		return originator.setPassenger( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setPlayerListName(java.lang.String)
	 */
	public void setPlayerListName( String arg0 ) {
		originator.setPlayerListName( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @see org.bukkit.entity.Player#setPlayerTime(long, boolean)
	 */
	public void setPlayerTime( long arg0, boolean arg1 ) {
		originator.setPlayerTime( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setPlayerWeather(org.bukkit.WeatherType)
	 */
	public void setPlayerWeather( WeatherType arg0 ) {
		originator.setPlayerWeather( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setRemainingAir(int)
	 */
	public void setRemainingAir( int arg0 ) {
		originator.setRemainingAir( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.LivingEntity#setRemoveWhenFarAway(boolean)
	 */
	public void setRemoveWhenFarAway( boolean arg0 ) {
		originator.setRemoveWhenFarAway( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setResourcePack(java.lang.String)
	 */
	public void setResourcePack( String arg0 ) {
		originator.setResourcePack( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSaturation(float)
	 */
	public void setSaturation( float arg0 ) {
		originator.setSaturation( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 * @see org.bukkit.entity.Player#setScoreboard(org.bukkit.scoreboard.Scoreboard)
	 */
	public void setScoreboard( Scoreboard arg0 ) throws IllegalArgumentException, IllegalStateException {
		originator.setScoreboard( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSleepingIgnored(boolean)
	 */
	public void setSleepingIgnored( boolean arg0 ) {
		originator.setSleepingIgnored( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSneaking(boolean)
	 */
	public void setSneaking( boolean arg0 ) {
		originator.setSneaking( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setSprinting(boolean)
	 */
	public void setSprinting( boolean arg0 ) {
		originator.setSprinting( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic, org.bukkit.entity.EntityType, int)
	 */
	public void setStatistic( Statistic arg0, EntityType arg1, int arg2 ) {
		originator.setStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic, int)
	 */
	public void setStatistic( Statistic arg0, int arg1 ) throws IllegalArgumentException {
		originator.setStatistic( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setStatistic(org.bukkit.Statistic, org.bukkit.Material, int)
	 */
	public void setStatistic( Statistic arg0, Material arg1, int arg2 ) throws IllegalArgumentException {
		originator.setStatistic( arg0, arg1, arg2 );
	}




	/**
	 * @param arg0
	 * @deprecated
	 * @see org.bukkit.entity.Player#setTexturePack(java.lang.String)
	 */
	public void setTexturePack( String arg0 ) {
		originator.setTexturePack( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setTicksLived(int)
	 */
	public void setTicksLived( int arg0 ) {
		originator.setTicksLived( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#setTotalExperience(int)
	 */
	public void setTotalExperience( int arg0 ) {
		originator.setTotalExperience( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Entity#setVelocity(org.bukkit.util.Vector)
	 */
	public void setVelocity( Vector arg0 ) {
		originator.setVelocity( arg0 );
	}




	/**
	 * @param arg0
	 * @throws IllegalArgumentException
	 * @see org.bukkit.entity.Player#setWalkSpeed(float)
	 */
	public void setWalkSpeed( float arg0 ) throws IllegalArgumentException {
		originator.setWalkSpeed( arg0 );
	}




	/**
	 * @param arg0
	 * @see org.bukkit.OfflinePlayer#setWhitelisted(boolean)
	 */
	public void setWhitelisted( boolean arg0 ) {
		originator.setWhitelisted( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.HumanEntity#setWindowProperty(org.bukkit.inventory.InventoryView.Property, int)
	 */
	public boolean setWindowProperty( Property arg0, int arg1 ) {
		return originator.setWindowProperty( arg0, arg1 );
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#shootArrow()
	 */
	public Arrow shootArrow() {
		return originator.shootArrow();
	}




	/**
	 * @param arg0
	 * @see org.bukkit.entity.Player#showPlayer(org.bukkit.entity.Player)
	 */
	public void showPlayer( Player arg0 ) {
		originator.showPlayer( arg0 );
	}







	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.entity.Entity, org.bukkit.event.player.PlayerTeleportEvent.TeleportCause)
	 */
	public boolean teleport( Entity arg0, TeleportCause arg1 ) {
		return originator.teleport( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.entity.Entity)
	 */
	public boolean teleport( Entity arg0 ) {
		return originator.teleport( arg0 );
	}




	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.Location, org.bukkit.event.player.PlayerTeleportEvent.TeleportCause)
	 */
	public boolean teleport( Location arg0, TeleportCause arg1 ) {
		return originator.teleport( arg0, arg1 );
	}




	/**
	 * @param arg0
	 * @return
	 * @see org.bukkit.entity.Entity#teleport(org.bukkit.Location)
	 */
	public boolean teleport( Location arg0 ) {
		return originator.teleport( arg0 );
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#throwEgg()
	 */
	public Egg throwEgg() {
		return originator.throwEgg();
	}




	/**
	 * @return
	 * @deprecated
	 * @see org.bukkit.entity.LivingEntity#throwSnowball()
	 */
	public Snowball throwSnowball() {
		return originator.throwSnowball();
	}




	/**
	 * @deprecated
	 * @see org.bukkit.entity.Player#updateInventory()
	 */
	public void updateInventory() {
		originator.updateInventory();
	}




	private final Player	recipient;

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




	@Override
	public int _INVALID_getLastDamage() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public void _INVALID_setLastDamage( int arg0 ) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void _INVALID_damage( int arg0 ) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void _INVALID_damage( int arg0, Entity arg1 ) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public int _INVALID_getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int _INVALID_getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public void _INVALID_setHealth( int arg0 ) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void _INVALID_setMaxHealth( int arg0 ) {
		// TODO Auto-generated method stub
		
	}

}

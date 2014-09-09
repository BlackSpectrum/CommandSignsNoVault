package eu.blackspectrum.commandsigns.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.blackspectrum.commandsigns.CommandSigns;

public class SignLoader
{


	private final CommandSigns	plugin;




	public SignLoader(final CommandSigns plugin) {
		this.plugin = plugin;
	}




	public synchronized void loadFile() {
		this.plugin.activeSigns.clear();

		if ( new File( this.plugin.getDataFolder(), "signs.dat" ).exists() )
		{
			this.loadOldFile();
			if ( !new File( this.plugin.getDataFolder(), "signs.dat" ).exists() )
				this.saveFile();
			new File( this.plugin.getDataFolder(), "signs.dat" ).renameTo( new File( this.plugin.getDataFolder(), "signs.bak" ) );
		}

		final FileConfiguration config = YamlConfiguration.loadConfiguration( new File( this.plugin.getDataFolder(), "signs.yml" ) );
		final ConfigurationSection data = config.getConfigurationSection( "signs" );

		if ( data == null )
		{
			this.plugin.getLogger().info( "No signs found." );
			return;
		}

		String[] locText;
		World world;
		int x, y, z;
		Material block;
		Location loc;
		int attempts = 0;

		for ( final String key : data.getKeys( false ) )
			try
			{
				attempts++;
				locText = key.split( "," );
				world = Bukkit.getWorld( locText[0] );
				if ( world == null )
					continue;
				x = Integer.parseInt( locText[1] );
				y = Integer.parseInt( locText[2] );
				z = Integer.parseInt( locText[3] );
				loc = new Location( world, x, y, z );

				// Throws exception for an invalid location AND if the
				// location is air
				block = loc.getBlock().getType();
				if ( block == null || block == Material.AIR )
					throw new IllegalArgumentException( "Location not valid: " + loc.toString() + "." );

				final boolean redstone = data.getBoolean( key + ".redstone", false );
				final String owner = data.getString( key + ".owner", null );

				final SignText cst = new SignText( owner, redstone );
				for ( final Object o : data.getList( key + ".text", new ArrayList<String>() ) )
					cst.addLine( o.toString() );

				cst.setEnabled( data.getBoolean( key + ".active", true ) );

				final Map<String, Long> timeouts = cst.getTimeouts();
				ConfigurationSection cooldowns = data.getConfigurationSection( key + ".cooldowns" );
				if ( cooldowns == null )
					cooldowns = data.createSection( key + "cooldowns" );
				for ( final String subKey : cooldowns.getKeys( false ) )
					timeouts.put( subKey, cooldowns.getLong( subKey ) );

				this.plugin.activeSigns.put( loc, cst );
			}
			catch ( final Exception ex )
			{
				this.plugin.getLogger().warning( "Unable to load sign " + attempts + " in signs.yml. " + ex.getMessage() );
				ex.printStackTrace();
			}
		this.plugin.getLogger().info( "Successfully loaded " + this.plugin.activeSigns.size() + " signs" );
	}




	public synchronized void loadOldFile() {
		try
		{
			final File file = new File( this.plugin.getDataFolder(), "signs.dat" );
			if ( file.exists() )
			{
				final FileInputStream inStream = new FileInputStream( file );
				final Scanner scanner = new Scanner( inStream );
				this.plugin.activeSigns.clear();

				String line = "";
				String[] raw = null;
				boolean redstone = false;
				World world = null;
				int x = 0;
				int y = 0;
				int z = 0;
				Material block;
				int lineNumber = 0;

				while ( scanner.hasNextLine() )
				{
					lineNumber++;
					try
					{
						line = scanner.nextLine();
						raw = line.split( "[\u00A7\u001D]" );

						redstone = Boolean.parseBoolean( raw[6] );

						world = Bukkit.getWorld( raw[0] );
						x = Integer.parseInt( raw[1] );
						y = Integer.parseInt( raw[2] );
						z = Integer.parseInt( raw[3] );
						final Location csl = new Location( world, x, y, z );

						// Throws exception for an invalid location AND if the
						// location is air
						block = csl.getBlock().getType();
						if ( block == null || block == Material.AIR )
							throw new IllegalArgumentException( "Location not valid." );

						final String owner = raw[4];
						final SignText cst = new SignText( owner, redstone );
						for ( final String command : raw[5].split( "[\u00B6\u001E]" ) )
							cst.addLine( command );
						this.plugin.activeSigns.put( csl, cst );
					}
					catch ( final Exception ex )
					{
						this.plugin.getLogger().warning( "Unable to load sign in signs.dat line " + lineNumber );
					}
				}
				scanner.close();
				inStream.close();
				this.plugin.getLogger().info( "Imported " + this.plugin.activeSigns.size() + " old signs" );
			}
		}
		catch ( final Exception ex )
		{
			ex.printStackTrace();
		}
	}




	public synchronized void saveFile() {
		final FileConfiguration config = new YamlConfiguration();
		final ConfigurationSection data = config.createSection( "signs" );

		for ( final Map.Entry<Location, SignText> sign : this.plugin.activeSigns.entrySet() )
		{
			final Location loc = sign.getKey();
			final SignText cst = sign.getValue();
			cst.trim();
			final String key = loc.getWorld().getName() + "," + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();

			final ConfigurationSection signData = data.createSection( key );
			signData.set( "redstone", cst.isRedstone() );
			signData.set( "owner", cst.getOwner() );
			signData.set( "text", cst.getText() );
			signData.set( "active", cst.isEnabled() );
			signData.createSection( "cooldowns", cst.getTimeouts() );

			try
			{
				config.save( new File( this.plugin.getDataFolder(), "signs.yml" ) );
			}
			catch ( final IOException e )
			{
				this.plugin.getLogger().severe( "Failed to save CommandSigns" );
				e.printStackTrace();
			}
		}
		this.plugin.getLogger().info( this.plugin.activeSigns.size() + " signs saved" );
	}




	public synchronized void saveOldFile() {
		try
		{
			final File file = new File( this.plugin.getDataFolder(), "signs.dat" );
			if ( !file.exists() )
			{
				this.plugin.getDataFolder().mkdir();
				file.createNewFile();
			}
			final BufferedWriter writer = new BufferedWriter( new FileWriter( file ) );

			Location csl = null;
			final String sep = "\u001D";
			String line = "";
			String commands = "";
			boolean first = true;
			int signNumber = 0;

			writer.write( "" );
			for ( final Map.Entry<Location, SignText> entry : this.plugin.activeSigns.entrySet() )
				try
				{
					signNumber++;
					entry.getValue().trim();
					commands = "";
					for ( final String command : entry.getValue() )
					{
						if ( !first )
							commands += "\u001E";
						commands += command;
						first = false;
					}
					csl = entry.getKey();
					line = csl.getWorld().getName();
					line += sep;
					line += csl.getBlockX();
					line += sep;
					line += csl.getBlockY();
					line += sep;
					line += csl.getBlockZ();
					line += sep;
					line += entry.getValue().getOwner();
					line += sep;
					line += commands;
					line += sep;
					line += entry.getValue().isRedstone();
					writer.write( line + "\n" );
				}
				catch ( final Exception ex )
				{
					if ( csl != null )
						this.plugin.getLogger().warning( "Unable to save sign #" + signNumber + " at " + csl.toString() );
					else
						this.plugin.getLogger().warning( "Unable to save sign #" + signNumber );
				}
			writer.close();
		}
		catch ( final Exception ex )
		{
			this.plugin.getLogger().severe( "Failed to save signs!" );
			ex.printStackTrace();
		}
	}
}

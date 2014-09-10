package eu.blackspectrum.commandsigns.config;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import eu.blackspectrum.commandsigns.CommandSigns;
import eu.blackspectrum.commandsigns.util.YamlLoader;

public class Messaging extends ConfigStore
{


	public Messaging(final CommandSigns plugin) {
		super();
	}




	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load() {
		final Configuration config = YamlLoader.loadResource( CommandSigns.get(), "messages.yml" );

		for ( final String k : config.getKeys( true ) )
			if ( !config.isConfigurationSection( k ) )
				this.put( k, config.getString( k ) );
	}




	public String parseMessage( final String messageName ) {
		return this.parseMessage( messageName, null, null );
	}




	public String parseMessage( final String message, final String[] variables, final String[] replacements ) {
		final String raw = this.parseRaw( message, variables, replacements );
		final String prefix = this.get( "prefix" );
		if ( prefix != null )
			return ChatColor.translateAlternateColorCodes( '&', prefix + raw );
		else
			return "Could not find message " + prefix + ".";
	}




	public String parseRaw( final String messageName ) {
		return this.parseRaw( messageName, null, null );
	}




	public String parseRaw( String messageName, final String[] variables, final String[] replacements ) {
		messageName = messageName.toLowerCase();
		final String prefix = this.get( messageName.split( "\\." )[0] + ".prefix" );
		String raw = this.get( messageName );
		if ( raw != null )
		{
			if ( variables != null && replacements != null )
			{
				if ( variables.length != replacements.length )
					return "The variables and replacements don't match in size! Please alert a developer.";
				for ( int i = 0; i < variables.length; i++ )
				{
					// Sanitise replacements
					final String replacement = replacements[i].replace( "\\", "\\\\" ).replace( "$", "\\$" );
					raw = raw.replaceAll( "(?iu)\\{" + variables[i] + "\\}", replacement );
				}
			}
			raw = raw.replaceAll( "(?iu)\\{PREFIX\\}", prefix != null ? prefix : "" );
			return ChatColor.translateAlternateColorCodes( '&', ( prefix != null ? prefix : "" ) + raw );
		}
		else
			return "Could not find message " + messageName + ".";
	}




	public void sendMessage( final CommandSender cs, final String messageName ) {
		this.sendMessage( cs, messageName, null, null );
	}




	public void sendMessage( final CommandSender cs, final String messageName, final String[] variables, final String[] replacements ) {
		if ( cs != null )
			cs.sendMessage( this.parseMessage( messageName, variables, replacements ) );
	}




	public void sendRaw( final CommandSender cs, final String messageName ) {
		this.sendRaw( cs, messageName, null, null );
	}




	public void sendRaw( final CommandSender cs, final String messageName, final String[] variables, final String[] replacements ) {
		if ( cs != null )
			cs.sendMessage( this.parseRaw( messageName, variables, replacements ) );
	}
}

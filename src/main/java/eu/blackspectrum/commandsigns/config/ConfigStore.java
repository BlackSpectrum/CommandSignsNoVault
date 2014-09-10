package eu.blackspectrum.commandsigns.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ConfigStore implements Map<String, String>
{


	private final Map<String, String>	config	= new ConcurrentHashMap<String, String>();




	public ConfigStore() {
	}




	@Override
	public void clear() {
		this.config.clear();
	}




	@Override
	public boolean containsKey( final Object key ) {
		return this.config.containsKey( key );
	}




	@Override
	public boolean containsValue( final Object value ) {
		return this.config.containsValue( value );
	}




	@Override
	public Set<Entry<String, String>> entrySet() {
		return this.config.entrySet();
	}




	@Override
	public String get( final Object key ) {
		return this.config.get( key );
	}




	/**
	 * Gets the boolean value mapped to the given key.
	 *
	 * @param key
	 * @return
	 */
	public boolean getBoolean( final Object key ) {
		try
		{
			return Boolean.parseBoolean( this.get( key ) );
		}
		catch ( final Exception ex )
		{
			return false;
		}
	}




	/**
	 * Gets the integer value mapped to the given key.
	 *
	 * @param key
	 * @return
	 */
	public int getInt( final Object key ) {
		try
		{
			return Integer.parseInt( this.get( key ) );
		}
		catch ( final Exception ex )
		{
			return 0;
		}
	}




	@Override
	public boolean isEmpty() {
		return this.config.isEmpty();
	}




	@Override
	public Set<String> keySet() {
		return this.config.keySet();
	}




	/**
	 * Loads the configuration file into memory
	 */
	public abstract void load();




	@Override
	public String put( final String key, final String value ) {
		return this.config.put( key, value );
	}




	@Override
	public void putAll( final Map<? extends String, ? extends String> m ) {
		this.config.putAll( m );
	}




	@Override
	public String remove( final Object key ) {
		return this.config.remove( key );
	}




	@Override
	public int size() {
		return this.config.size();
	}




	@Override
	public Collection<String> values() {
		return this.config.values();
	}

}

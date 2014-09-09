package eu.blackspectrum.commandsigns.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SignText implements Iterable<String>
{


	private boolean					enabled		= true;
	private final String			owner;
	private boolean					redstone	= false;
	private final List<String>		text;
	private final Map<String, Long>	timeouts	= new HashMap<String, Long>();




	public SignText(final String owner, final boolean redstone) {
		this.owner = owner;
		this.text = new ArrayList<String>();
		this.redstone = redstone;
	}




	public void addLine( final String string ) {
		this.text.add( string );
	}




	public SignText clone( final String owner ) {
		final SignText cst = new SignText( owner, this.redstone );
		for ( final String s : this.text )
			cst.getText().add( s );
		return cst;
	}




	public int count() {
		int size = this.text.size();
		// Count from last to first, stop whenever a non-blank
		// is found, or if the size hits 'zero'
		while ( size > 0 && this.getLine( size ) == "" )
			size--;
		return size;
	}




	// Internal list is ZERO indexed, one indexed externally only
	public String getLine( final int index ) {
		return this.text.get( index - 1 );
	}




	public String getOwner() {
		return this.owner;
	}




	public List<String> getText() {
		return this.text;
	}




	public Map<String, Long> getTimeouts() {
		return this.timeouts;
	}




	public boolean isEnabled() {
		return this.enabled;
	}




	public boolean isRedstone() {
		return this.redstone;
	}




	@Override
	public Iterator<String> iterator() {
		return this.text.iterator();
	}




	public void removeLine( final int index ) {
		if ( index >= 1 && index <= this.text.size() )
			this.text.remove( index - 1 );
	}




	public void setEnabled( final boolean enabled ) {
		this.enabled = enabled;
	}




	public void setLine( final int index, final String line ) {
		while ( this.text.size() < index )
			this.text.add( "" );
		this.text.set( index - 1, line );
	}




	public void setRedstone( final boolean redstone ) {
		this.redstone = redstone;
	}




	@Override
	public String toString() {
		final String string = "";

		return string;
	}




	public void trim() {
		int blank;
		while ( ( blank = this.text.lastIndexOf( "" ) ) >= 0 )
			this.text.remove( blank );
		for ( final String line : this.text )
			line.trim();
	}
}

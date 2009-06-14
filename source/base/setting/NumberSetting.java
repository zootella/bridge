package base.setting;


import base.data.Number;
import base.data.Outline;
import base.exception.MessageException;


public class NumberSetting {
	
	// -------- Make a NumberSetting --------
	
	/** Make a NumberSetting saved in store at path, with the given default value. */
	public NumberSetting(Store store, String path, long value, long minimum) {
		
		// Save the given objects in this new one
		this.store = store;
		this.path = path;
		this.value = value;
		this.minimum = minimum;
		
		// If store's Outline has path, get the Outline object there
		try {
			this.outline = store.outline.path(path);
		} catch (MessageException e) {} // path not found, leave outline null
	}
	
	/** The Store this setting will save itself in, the file Store.txt. */
	private Store store;
	/** This setting's path like "name.name.name" in store's Outline. */
	private String path;
	/** The Outline object at path, or null if store's Outline doesn't have one there. */
	private Outline outline;
	
	/** This setting's default value the program set when it made this object. */
	private long value;
	/** The minimum value, set() won't change to a lower value than this minimum. */
	private long minimum;

	// -------- Get and set the value --------
	
	/** Get this setting's value in Store.txt, or the program's default value if not found. */
	public long value() {
		if (outline == null) return value; // Not found in Store.txt, return our default
		try {
			long n = outline.getNumber();
			if (n < minimum) return value; // The outline value is too small
			return n;
		} catch (MessageException e) { return value; } // The outline value isn't a number
	}
	
	/** Give this setting a new value, and save it in Store.txt for the next time the program runs. */
	public void set(long value) {
		if (value < minimum) return; // Make sure the given value is minimum or bigger
		if (outline == null) outline = store.outline.make(path); // Make our object in store's Outline
		outline.set(value);
	}
	
	// -------- Convert to and from a String --------
	
	/** Get this setting's value in Store.txt, or the program's default value if not found, as a String. */
	public String toString() {
		return Number.toString(value()); // Get our value and convert it into a String
	}
	
	/** Give this setting a new value, and save it in Store.txt for the next time the program runs. */
	public void set(String value) {
		try {
			set(Number.toLong(value));
		} catch (MessageException e) {} // Couldn't turn the given String into a number
	}
}

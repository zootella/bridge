package base.setting;


import base.data.Data;
import base.data.Outline;
import base.exception.MessageException;


public class DataSetting {
	
	// -------- Make a DataSetting --------
	
	/** Make a DataSetting saved in store at path, with the given default value. */
	public DataSetting(Store store, String path, Data value) {
		
		// Save the given objects in this new one
		this.store = store;
		this.path = path;
		this.value = value;
		
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
	private Data value;

	// -------- Get and set the value --------
	
	/** Get this setting's value in Store.txt, or the program's default value if not found. */
	public Data value() {
		if (outline == null) return value; // Not found in Store.txt, return our default
		return outline.getData();
	}

	/** Give this setting a new value, and save it in Store.txt for the next time the program runs. */
	public void set(boolean value) {
		if (outline == null) outline = store.outline.make(path); // Make our object in store's Outline
		outline.set(value);
	}
}

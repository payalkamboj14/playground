/**
 * 
 */
package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author payal
 * 
 * Class to contain the Item info include all it's dependencies 
 * and also other Items that depend on it
 *
 */
public class DependencyGraph {

	private final List<String> installedItems = new ArrayList<>();
	private final Map<String, Item> itemNameToObject = new HashMap<>();
	
	public List<String> getInstalledItems() {
		//TODO : We might want to make the installedItems field immutable
		// to avoid anyone playing around with it
		return installedItems;
	}
	
	
	public Map<String, Item> getItemNameToObj() {
		return itemNameToObject;
	}
	
	public boolean isItemInstalled(String item) {
		if(installedItems == null || !installedItems.contains(item))
			return false;
		return true;
	}
	
}

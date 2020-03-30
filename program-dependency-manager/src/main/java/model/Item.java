/**
 * 
 */
package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payal
 * 
 * Class to contain the command info include all it's dependencies 
 * and also other commands that depend on it
 *
 */
public class Item {
	private final String name;
	
	//All items dependent on current item
	private List<String> incomingDependencies;
	
	//List of items that must be created before creating current item
	private List<String> outgoingDependencies;
	
	public Item(String name, List<String> incomingDependencies, List<String> outgoingDependencies) {
		this.name = name;
		this.incomingDependencies = incomingDependencies;
		this.outgoingDependencies = outgoingDependencies;
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getIncomingDependencies() {
		//may be return a copy of original array to make it immutable
		return incomingDependencies;
	}
	
	public List<String> getOutgoingDependencies() {
		//may be return a copy of original array to make it immutable
		return outgoingDependencies;
	}
	
	public void setOutgoingDependencies(List<String> outgoingDependencies) {
		this.outgoingDependencies = outgoingDependencies;
	}
	
	public void addIncomingDependency(String item) {
		if(incomingDependencies == null) {
			incomingDependencies = new ArrayList<>();
		}
		if(!incomingDependencies.contains(item)) {
			incomingDependencies.add(item);
		}
	}
	
	public void removeIncomingDependency(String item) {
		if(incomingDependencies != null && incomingDependencies.contains(item)) {
			incomingDependencies.remove(item);
		}
	}

}

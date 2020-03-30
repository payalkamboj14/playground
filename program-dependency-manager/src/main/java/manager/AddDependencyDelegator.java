/**
 * 
 */
package main.java.manager;

import java.util.List;
import java.util.Map;

import main.java.model.Item;
import main.java.model.DependencyGraph;

/**
 * @author payal
 * 
 * Class to record dependencies across various Items
 *
 */
public class AddDependencyDelegator {
	
	
	protected String addDependencies(DependencyGraph dependencyGraph, List<String> parsedCommandLine) {
		//TODO : add size validations of each string size

		StringBuilder outputBuilder = new StringBuilder();
		
		if(parsedCommandLine == null || parsedCommandLine.size() < 3) {
			outputBuilder.append("\tDEPEND command must have at least 3 args \n");
		}
		String dependent = parsedCommandLine.get(1);

		Map<String, Item> itemNameToObject = dependencyGraph.getItemNameToObj();

		
		List<String> dependencies = parsedCommandLine.subList(2, (parsedCommandLine.size()));
		//Ensure each dependency is present in the graph
		for (String item : dependencies) {
			if(itemNameToObject.get(item) == null) {
				itemNameToObject.put(item, new Item(item, null, null));
			}
		}

		//creating an object for dependent being processed
		Item dependentItem = itemNameToObject.get(dependent);
		if(dependentItem == null) {
			dependentItem = new Item(dependent, null, dependencies);
			itemNameToObject.put(dependent, dependentItem);
		} else {
			dependentItem.setOutgoingDependencies(dependencies);
		}
		
		return outputBuilder.toString();
	}
	
}

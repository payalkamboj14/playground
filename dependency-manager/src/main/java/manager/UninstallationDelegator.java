
package main.java.manager;

import java.util.List;

import main.java.model.DependencyGraph;
import main.java.model.Item;

/**
 * @author payal
 * 
 * Class to manage item uninstallations
 *
 */
public class UninstallationDelegator {
	
	//@NonNull
	protected String uninstall(DependencyGraph dependencyGraph, List<String> parsedCommandLine) {
		//TODO : add size validations of each string size

		StringBuilder outputBuilder = new StringBuilder();

		if(parsedCommandLine == null || parsedCommandLine.size() != 2) {
			outputBuilder.append("\t REMOVE command must have at exactly 2 args \n");
		}
		
		String itemToUninstall = parsedCommandLine.get(1);
		Item item = dependencyGraph.getItemNameToObj().get(itemToUninstall);

		if(item == null || !dependencyGraph.getInstalledItems().contains(itemToUninstall)) {
			outputBuilder.append("\t").append(itemToUninstall + " is not installed \n");
		} else if(item.getIncomingDependencies() != null && !item.getIncomingDependencies().isEmpty()) {
			outputBuilder.append("\t").append(itemToUninstall + " is still needed \n");
		} else {
			uninstallWithDependencies(dependencyGraph, item, outputBuilder);
		}
		
		return outputBuilder.toString();
	}

	/**
	 * Uninstalls an item by first uninstalling current item and then recursively
	 * uninstalling any redundand dependency installing all the dependencies
	 * 
	 * @param dependencyGraph
	 * @param itemToUninstall
	 */
	private void uninstallWithDependencies(DependencyGraph dependencyGraph, Item itemToUninstall, 
			StringBuilder outputBuilder) {
		
		List<String> dependencies = itemToUninstall.getOutgoingDependencies();
		
		//install the un-installed dependencies using DFS
		if(dependencies != null) {
			for(String dependency : dependencies) {
				//update the incomingDependencies for dependency item. This will help us later track 
				// what other items are dependent on a given item
				Item item = dependencyGraph.getItemNameToObj().get(dependency);
				item.removeIncomingDependency(itemToUninstall.getName());
				
				//recursively uninstall each item if it has no more incoming dependency
				if(item.getIncomingDependencies() == null || item.getIncomingDependencies().isEmpty()) {
					uninstallWithDependencies(dependencyGraph, item, outputBuilder);
				}
			}
		}
		
		//install current item as dependencies have been installed
		uninstallItem(dependencyGraph, itemToUninstall.getName(), outputBuilder);
	}

	
	private void uninstallItem(DependencyGraph dependencyGraph, String itemName, 
			StringBuilder outputBuilder) {
		List<String> installedItems = dependencyGraph.getInstalledItems();
		if(installedItems != null && installedItems.contains(itemName)) {
			installedItems.remove(itemName);
			outputBuilder.append("\t Removing " + itemName + "\n");
		}
	}
	
}

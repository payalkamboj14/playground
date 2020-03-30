                                /**
 * 
 */
package main.java.manager;

import java.util.List;

import main.java.model.DependencyGraph;
import main.java.model.Item;

/**
 * @author payal
 * 
 * Class to manage item installations
 *
 */
public class InstallationDelegator {
	
	//@NonNull
	protected String install(DependencyGraph dependencyGraph, List<String> parsedCommandLine) {

		StringBuilder outputBuilder = new StringBuilder();
		
		if(parsedCommandLine == null || parsedCommandLine.size() != 2) {
			outputBuilder.append("\tINSTALL command must have at exactly 2 args \n");
		}
		String itemToInstall = parsedCommandLine.get(1);

		//check if item is already installed
		if(dependencyGraph.isItemInstalled(itemToInstall)) {
			outputBuilder.append("\t" + itemToInstall + " is already installed \n");
		} else {
			Item item = dependencyGraph.getItemNameToObj().get(itemToInstall);
			//Define item if not already defined in the graph
			if(item == null) {
				item = new Item(itemToInstall, null, null);
				dependencyGraph.getItemNameToObj().put(itemToInstall, item);
			}
			installWithDependencies(dependencyGraph, item, outputBuilder);
		}
		
		return outputBuilder.toString();
	}
	
	/**
	 * Installs an item by first recursively installing all the dependencies
	 * which are currently not installed
	 * 
	 * @param dependencyGraph
	 * @param itemToInstall
	 */
	private void installWithDependencies(DependencyGraph dependencyGraph, Item itemToInstall, StringBuilder builder) {
		
		List<String> dependencies = itemToInstall.getOutgoingDependencies();
		
		//install the un-installed dependencies
		if(dependencies != null) {
			for(String dependency : dependencies) {
				//update the incomingDependencies for dependency item. This will help us later track 
				// what other items are dependent on a given item
				Item item = dependencyGraph.getItemNameToObj().get(dependency);
				item.addIncomingDependency(itemToInstall.getName());
				
				//recursively install each item
				installWithDependencies(dependencyGraph, item, builder);
			}
		}
		
		//install current item as dependencies have been installed
		addInstalledItem(dependencyGraph, itemToInstall.getName(), builder);
	}
	

	private void addInstalledItem(DependencyGraph dependencyGraph, String item, StringBuilder builder) {
		if(!dependencyGraph.getInstalledItems().contains(item)) {
			dependencyGraph.getInstalledItems().add(item);			
			builder.append("\tInstalling " + item + "\n");
		}
	}
}

/**
 * 
 */
package main.java.manager;

import main.java.model.DependencyGraph;

/**
 * @author payal
 * 
 * Class to list installed dependecies in the order of their installation
 *
 */
public class ListDependencyDelegator {
	
	
	protected String listDependencies(DependencyGraph dependencyGraph) {

		StringBuilder outputBuilder = new StringBuilder();
		
		for(String installedItem : dependencyGraph.getInstalledItems()) {
			outputBuilder.append("\t").append(installedItem).append("\n");
		}
		
		return outputBuilder.toString();
	}
	
}

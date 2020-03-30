/**
 * 
 */
package main.java.manager;

import java.util.Arrays;
import java.util.List;

import main.java.model.Command;
import main.java.model.DependencyGraph;

/**
 * @author payal
 * 
 * Class to manage dependencies across various Items and installations
 *
 */
public class DependencyManager {

	private DependencyGraph dependencyGraph = new DependencyGraph();
	private AddDependencyDelegator addDependencyDelegator = new AddDependencyDelegator();
	private InstallationDelegator installationDelegator = new InstallationDelegator();
	private UninstallationDelegator uninstallationDelegator = new UninstallationDelegator();
	private ListDependencyDelegator listDependencyDelegator = new ListDependencyDelegator();
	
	private String process(String line) {
		if(line.length() > 80) {
			throw new IllegalArgumentException("Only 80 chars allowed per input command");
		}
		
		List<String> parsedLine = Arrays.asList(line.split(" "));
		
		//TODO : Handle incorrect cmd name here
		Command cmd = Command.getCommand(parsedLine.get(0));
		
		if(cmd.equals(Command.DEPEND)) {
			//verify no other command came by now
			return addDependencyDelegator.addDependencies(dependencyGraph, parsedLine);
		} else if(cmd.equals(Command.INSTALL)) {
			return installationDelegator.install(dependencyGraph, parsedLine);
		} else if(cmd.equals(Command.REMOVE)) {
			return uninstallationDelegator.uninstall(dependencyGraph, parsedLine);
		} else if(cmd.equals(Command.LIST)) {
			return listDependencyDelegator.listDependencies(dependencyGraph);
		} else if(cmd.equals(Command.END)) {
			return "\n";
		} else {
			return "Invalid Input command \n";
		}
	}
	
	
	public String process(List<String> inputCommands) {
		if(inputCommands == null || inputCommands.size() < 1) {
			return "";
		}
		
		StringBuilder outputBuilder = new StringBuilder();
		for(String line : inputCommands) {
			//trim extra spaces between strings
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll("\n", "");
			outputBuilder.append(line).append("\n");
			outputBuilder.append(process(line));
		}
		
		return outputBuilder.toString();
	}

}

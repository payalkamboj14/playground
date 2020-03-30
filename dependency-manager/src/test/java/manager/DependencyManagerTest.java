package test.java.manager;

import java.util.ArrayList;
import java.util.List;

import main.java.manager.DependencyManager;

public class DependencyManagerTest {

	public static void main(String[] args) {
		
		List<String> input = new ArrayList<>();
		input.add("DEPEND TELNET TCPIP NETCARD\n");
		input.add("DEPEND TCPIP NETCARD\n");
		input.add("DEPEND DNS TCPIP NETCARD\n");
		input.add("DEPEND  BROWSER   TCPIP  HTML\n");
		input.add("INSTALL NETCARD\n");
		input.add("INSTALL TELNET\n");
		input.add("INSTALL foo\n");
		input.add("REMOVE NETCARD\n");
		input.add("INSTALL BROWSER\n");
		input.add("INSTALL DNS\n");
		input.add("LIST\n");
		input.add("REMOVE TELNET\n");
		input.add("REMOVE NETCARD\n");
		input.add("REMOVE DNS\n");
		input.add("REMOVE NETCARD\n");
		input.add("INSTALL NETCARD\n");
		input.add("REMOVE TCPIP\n");
		input.add("REMOVE BROWSER\n");
		input.add("REMOVE TCPIP\n");
		input.add("END\n");

		
		DependencyManager dependencyManager = new DependencyManager();
		System.out.println(dependencyManager.process(input));
	}

}

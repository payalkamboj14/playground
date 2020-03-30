package main.java.model;

public enum Command {
   DEPEND("DEPEND"),
   INSTALL("INSTALL"),
   REMOVE("REMOVE"),
   LIST("LIST"),
   END("END");
   
   private final String cmdName;
   
   Command(String cmdName) {
       this.cmdName = cmdName;
   }
   
   static public Command getCommand(String cmd) {
	   return Command.valueOf(cmd);
   }
}

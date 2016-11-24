package Command;

import java.util.HashMap;

public class Command_List {
    private static Command_List instance = null;
    private HashMap<String, Command> commands;
    
    private Command_List() { 
        // delegate responsibility to builder
        Command_List_Builder CB = new Command_List_Builder();
        this.commands = CB.build();
    }
    
    public synchronized static Command_List getInstance() {
        if(instance == null)
            instance = new Command_List();
        return instance;
    }
    
    public boolean canBeCommand(String s) {
        return s.startsWith(".");
    }
    
    public Command getCommand(String s) {
        if(canBeCommand(s)) {
            Command bestMatch = null;
            int matchCount = 0;
            for (HashMap.Entry<String, Command> elem : commands.entrySet()) {
                String prefix = elem.getKey();
                // find best possible matching command
                if(s.startsWith(prefix) && matchCount < prefix.length()) {
                    matchCount = prefix.length();
                    bestMatch = elem.getValue();
                }
            }
            // have special checks for unary commands (exact match maybe)
            if(bestMatch == null)
                bestMatch = new Command_Invalid();
            return bestMatch;
        }
        return null;
    }
}

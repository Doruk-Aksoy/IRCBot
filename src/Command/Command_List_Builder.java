package Command;

import Command.HelpCommands.Command_Help_Join;
import Command.HelpCommands.Command_Help;
import Command.HelpCommands.Command_Help_Register;
import java.util.HashMap;

public class Command_List_Builder {
    public Command_List_Builder() { }
    
    public HashMap<String, Command> build() {
        HashMap<String, Command> hm = new HashMap<>();
        // build all command instances here and clone if needed
        // Help commands
        hm.put(".help join", new Command_Help_Join());
        hm.put(".help register", new Command_Help_Register());
        hm.put(".help", new Command_Help());
        // Registration
        hm.put(".register", new Command_Register());
        // Login
        hm.put(".login", new Command_Login());
        
        return hm;
    }
}

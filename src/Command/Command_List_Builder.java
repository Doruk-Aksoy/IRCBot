package Command;

import Command.HelpCommands.*;
import java.util.HashMap;
import ConstantData.Constant_Data_Manager;

public class Command_List_Builder {
    public Command_List_Builder() { }
    
    public HashMap<String, Command> build() {
        HashMap<String, Command> hm = new HashMap<>();
        // build all command instances here and clone if needed
        // Help commands
        hm.put(Constant_Data_Manager.helpjoin_command, new Command_Help_Join());
        hm.put(Constant_Data_Manager.helpregister_command, new Command_Help_Register());
        hm.put(Constant_Data_Manager.helpstats_command, new Command_Help_Stats());
        hm.put(Constant_Data_Manager.helplogin_command, new Command_Help_Login());
        hm.put(Constant_Data_Manager.help_command, new Command_Help());
        // Registration
        hm.put(Constant_Data_Manager.register_command, new Command_Register());
        // Login
        hm.put(Constant_Data_Manager.login_command, new Command_Login());
        // User Stats
        hm.put(Constant_Data_Manager.stats_command, new Command_Stats());
        
        return hm;
    }
}

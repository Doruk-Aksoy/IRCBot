package Command;

import Command.HelpCommands.*;
import java.util.HashMap;
import ConstantData.Message_Data;

public class Command_List_Builder {
    public Command_List_Builder() { }
    
    public HashMap<String, Command> build() {
        HashMap<String, Command> hm = new HashMap<>();
        // build all command instances here and clone if needed
        // Help commands
        hm.put(Message_Data.helpjoin_command, new Command_Help_Join());
        hm.put(Message_Data.helpregister_command, new Command_Help_Register());
        hm.put(Message_Data.helpstats_command, new Command_Help_Stats());
        hm.put(Message_Data.helplogin_command, new Command_Help_Login());
        hm.put(Message_Data.help_command, new Command_Help());
        // Registration
        hm.put(Message_Data.register_command, new Command_Register());
        // Login
        hm.put(Message_Data.login_command, new Command_Login());
        // User Stats
        hm.put(Message_Data.stats_command, new Command_Stats());
        // Join Game
        hm.put(Message_Data.join_command, new Command_Join());
        // Create Game
        hm.put(Message_Data.creategame_command, new Command_Create());
        // Answer
        hm.put(Message_Data.answer_command, new Command_Answer());
        // Test command -- Stop Game
        hm.put(Message_Data.stopgame_command, new Command_StopGame());
        
        return hm;
    }
}

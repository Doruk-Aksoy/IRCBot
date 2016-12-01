package Handlers;

import Command.Command;
import Handlers.Event_Handler;
import Message.*;
import ircbot.IRCBot;
import ircbot.IRCBot_UserManager;
import UserType.GameUser;

public class Quit_Handler implements Event_Handler {
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        Message_Quit nmsg = (Message_Quit) msg;
        IRCBot Bot = IRCBot.getInstance();
        IRCBot_UserManager UM = Bot.getUserManager();
        GameUser u = UM.getUser(nmsg.getSender());
        if(u == null)
            return false;
        // update old name
        UM.deleteUser(u);
        System.out.println("User " + u.getIRCName() + " logged off.");
        return true;
    }
    
    @Override public boolean handle_event(Message msg) {
        return handle_cmd(null, msg);
    }
}

package Handlers;

import Command.Command;
import Mediator.BotMediator;
import Message.*;
import ircbot.IRCBot;
import UserType.GameUser;

public class Quit_Handler implements Event_Handler {
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        Message_Quit nmsg = (Message_Quit) msg;
        GameUser u = BotMediator.getUser(nmsg.getSender());
        if(u == null)
            return false;
        // update old name
        BotMediator.deleteUser(u);
        System.out.println("User " + u.getIRCName() + " logged off.");
        return true;
    }
    
    @Override public boolean handle_event(Message msg) {
        return handle_cmd(null, msg);
    }
}

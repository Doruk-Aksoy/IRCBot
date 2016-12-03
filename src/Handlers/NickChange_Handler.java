package Handlers;

import Command.Command;
import Message.*;
import Mediator.BotMediator;
import UserType.GameUser;

public class NickChange_Handler implements Event_Handler {
    @Override public boolean handle_cmd(Command cmd, Message msg) {
        Message_NickChange nmsg = (Message_NickChange) msg;
        GameUser u = BotMediator.getUser(nmsg.getOldName());
        if(u == null)
            return false;
        // update old name
        u.setIRCName(nmsg.getNewName());
        System.out.println("Name change result = " + BotMediator.getUser(nmsg.getNewName()) == null);
        return true;
    }
    
    @Override public boolean handle_event(Message msg) {
        return handle_cmd(null, msg);
    }
}

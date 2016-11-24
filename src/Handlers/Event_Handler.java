package Handlers;

import Command.*;
import Message.*;

public interface Event_Handler {
    public boolean handle_cmd(Command cmd, Message msg);
    public boolean handle_event(Message msg);
}

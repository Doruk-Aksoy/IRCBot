package Handlers;

import Command.*;
import Message.*;

public interface Event_Handler {
    public boolean handle_event(Message msg);
}

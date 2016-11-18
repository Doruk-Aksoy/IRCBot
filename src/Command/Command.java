package Command;

import Message.Message;

public interface Command {
    public void operate(Message msg);
}

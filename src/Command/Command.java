package Command;

import Message.Message;

public interface Command {
    public enum Command_Validity {
        CMD_VALID,
        CMD_BADFORMAT,
        CMD_POSSIBLYBAD,
        CMD_REQUIRELOGIN
    }

    public Command_Validity validate(Message msg);
    public void operate(Message msg);
}

package ircbot.Features;

import java.sql.ResultSet;

import Message.Message;

public interface IRCBot_Feature {
    public void execute(Message msg, ResultSet rs);
}
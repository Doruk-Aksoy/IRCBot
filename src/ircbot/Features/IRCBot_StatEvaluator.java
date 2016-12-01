package ircbot.Features;

import java.sql.ResultSet;
import java.sql.SQLException;

import ircbot.IRCBot;
import Message.Message;
import ConstantData.Message_Data;

// takes a resultset
public class IRCBot_StatEvaluator implements IRCBot_Feature {
    
    private String evaluate(String name, int games_won, int games_played, int score, int highest_score) {
        String result = null;
        if(games_played != 0) {
            result = "User " + name + " has played " + games_played + " games, won " + games_won + " out of them. Win percentage: " + 100 * ((double) games_won / (double) games_played) + " Current Score: " + score + " Highest Score: " + highest_score + ".";
        }
        else
            result = "User " + name + " hasn't played any gamaes yet!";
        return result;
    }
    
    public void execute(Message msg, ResultSet rs) {
        IRCBot Bot = IRCBot.getInstance();
        try {
            String name = rs.getString("login");
            int games_won = rs.getInt("games_won");
            int games_played = rs.getInt("games_played");
            int score = rs.getInt("score");
            int highest_score = rs.getInt("highest_score");
            String toSend = evaluate(name, games_won, games_played, score, highest_score);
            Bot.sendMessage(msg, toSend);
        }
        catch(SQLException e) {
            System.out.println(e);
            Bot.sendMessage(msg, Message_Data.register_dbconnection_fail);
        }
    }
}

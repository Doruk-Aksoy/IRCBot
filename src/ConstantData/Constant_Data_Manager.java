package ConstantData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constant_Data_Manager {
    public static final String newLine = System.getProperty("line.seperator");
    
    // Some generic error messages
    public static final String invalid_command = "Invalid command. See '.help' for a list of valid commands.";
    
    // Help Messages
    public static final String gen_help_message = "Hello user! This is the IRCBot made by IvanDobrovski. It can do many stuff! Mobius sucks!";
    public static final String join_help_message = "Use this to join a game before it begins. You can only join one game at a time. You also need to be a registered user in order to join.";
    public static final String no_score_message = "Sorry, but it seems you haven't participated in any games!";
    public static final String help_register = "In order to participate in activities that save information (such as games), you must first register. '.register name password' is the format.";
    
    // Registration messages
    public static final String register_begin_message = "Attempting to register your credentials...";
    public static final String register_dbconnection_fail = "Oops! Unfortunately the database did not respond. Try again later.";
    public static final String register_bad_format = "Invalid format. Please use the .help command for more info.";
    public static final String register_user_exists = "The user with the requested name already exists.";
    public static final String register_success = "Registration successful! You may now login using the information provided. Use the '.login' command.";
    
    // Game constants
    public static final int jeopardy_rounds = 9;
    public static final int scramble_rounds = 10;
    
    public static final List<String> scramble_words = Collections.unmodifiableList(Arrays.asList("foo", "bar"));
}

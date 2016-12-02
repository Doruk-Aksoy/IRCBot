package ConstantData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Message_Data {
    // help commands
    public static final String help_command = ".help";
    public static final String helpregister_command = ".help register";
    public static final String helplogin_command = ".help login";
    public static final String helpjoin_command = ".help join";
    public static final String helpstats_command = ".help stats";
    public static final String helpkeyword_command = ".help keyword";
    // normal commands
    public static final String register_command = ".register";
    public static final String login_command = ".login";
    public static final String stats_command = ".stats";
    public static final String keywords_command = ".keyword";
    public static final String join_command = ".join";
    public static final String answer_command = ".ans";
    
    // Some generic error messages
    public static final String invalid_command = "Invalid command. See '.help' for a list of valid commands.";
    public static final String unsafe_command = "Please type that command as a private message to me.";
    public static final String possiblywrong_command = "Invalid command or entered to wrong place. See '.help' for a list of valid commands.";
    public static final String bad_format = "Invalid format. Please use the .help command for more info.";
    
    // Help Messages
    public static final String gen_help_message = "Hello user! This is the IRCBot made by IvanDobrovski. It can do many stuff! Valid commands: .help command_name, .join, .keyword, .login, .register, .stats";
    public static final String join_help_message = "Use this to join a game before it begins. You can only join one game at a time. You also need to be a registered user in order to join. Usage: .join game_name";
    public static final String stats_help_message = "Shows stats of a user, such as high score, games won and such. Usage: .stats username";
    public static final String register_help_message = "In order to participate in activities that save information (such as games), you must first register. Usage: .register username password";
    public static final String login_help_message = "Logs you in. Let's you participate in games and other features. Usage: .login username password";
    public static final String keyword_help_message = "Allows you to specify a list of keywords to the bot. Whenever a word among these is used in the channel, you will be alerted. Requires you to be logged in. Usage: .keyword word1 word2 etc. Maximum word length is 30, and you can enter at most 10 words at a time.";
    
    // Registration messages
    public static final String register_begin_message = "Attempting to register your credentials...";
    public static final String register_dbconnection_fail = "Oops! Unfortunately the database did not respond. Try again later.";
    public static final String register_user_exists = "The user with the requested name already exists.";
    public static final String register_success = "Registration successful! You may now login using the information provided. Use the '.login' command.";
    
    // Login messages
    public static final String login_begin_message = "Checking your login credentials...";
    public static final String login_already = "You are already logged in!";
    public static final String login_nouser = "Sorry, no user was found with the given login.";
    public static final String login_invalid_password = "Invalid password.";
    public static final String login_success = "You've successfully logged in! You can now participate in games.";
    
    // Stats messages
    public static final String stat_begin_message = "Checking user's score data...";
    public static final String stat_nouser = "Sorry, no user was found with the given name.";
    public static final String no_score_message = "It seems this user hasn't participated in any games!";
}

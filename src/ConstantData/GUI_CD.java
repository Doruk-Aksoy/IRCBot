package ConstantData;

public class GUI_CD {
    public enum GUI_Popup_Type {
        Success,
        Error
    }
    
    public static final int max_channel_length = 100;
    public static final int max_server_length = 50;
    public static final int max_botname_length = 16;
    
    public static final int window_X = 640;
    public static final int window_Y = 480;
    
    public static final int fbrowser_X = 320;
    public static final int fbrowser_Y = 160;
    
    public static final String icon_path = "/resources/icons/";
    
    // error msgs
    public static final String IRCBot_Error = "IRCBot Error";
    public static final String IRCBot_Success = "IRCBot Success";
    
    public static final String unknown_host_msg = "Could not connect to given server! Possibly incorrect server address.";
    public static final String cant_join_msg = "Server rejected join request. Try again later."; 
    public static final String illegal_arg_msg = "Illegal arguments provided.";
    public static final String success_msg = "The bot is successfully set up!";
}

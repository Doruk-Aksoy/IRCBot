package GUI;

import java.net.UnknownHostException;
import javax.swing.JOptionPane;

import ConstantData.GUI_CD;
import org.jibble.pircbot.IrcException;

public class GUI_ErrorPopupHandler {
    public static void handle(Exception e) {
        if(e instanceof UnknownHostException)
            handle((UnknownHostException)e);
        else if(e instanceof IrcException)
            handle((IrcException)e);
    }
    
    public static void handle(IllegalArgumentException e) {
        JOptionPane.showMessageDialog(GUI_Main.getInstance(), GUI_CD.illegal_arg_msg, GUI_CD.IRCBot_Error, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void handle(UnknownHostException e) {
        JOptionPane.showMessageDialog(GUI_Main.getInstance(), GUI_CD.unknown_host_msg, GUI_CD.IRCBot_Error, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void handle(IrcException e) {
        JOptionPane.showMessageDialog(GUI_Main.getInstance(), GUI_CD.cant_join_msg, GUI_CD.IRCBot_Error, JOptionPane.ERROR_MESSAGE);
    }
}

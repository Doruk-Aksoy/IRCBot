package GUI;

import ConstantData.GUI_CD;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import ircbot.IRCBot;
import org.jibble.pircbot.IrcException;

public class GUI_Main extends Frame implements WindowListener {
    private GUI_Components components;
    
    public GUI_Main() {
        components = new GUI_Components();
        setLayout(new FlowLayout());
        addComponents();
        addWindowListener(this);
        
        setTitle("IRCBot Launcher");
        setSize(GUI_CD.window_X, GUI_CD.window_Y);
        setVisible(true);
    }
    
    private void addComponents() {
        for(Panel p : components.panels)
            add(p);
    }
    
    public static void main(String args[]) throws IOException, IrcException {
        GUI_Main gui = new GUI_Main();
        
        /*
        IRCBot Bot = IRCBot.getInstance();
        Bot.makeName("Test_Bot");
        // Connect to IRC -- TODO: Take input from user via GUI interaction
        Bot.connect("irc.esper.net");
        Bot.joinChannel("#mxu", true);*/
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
 
    }

    @Override
    public void windowIconified(WindowEvent e) {
  
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
  
    }

    @Override
    public void windowActivated(WindowEvent e) {
  
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
 
    }
}

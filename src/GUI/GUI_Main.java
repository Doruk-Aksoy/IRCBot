package GUI;

import java.awt.*;
import java.io.IOException;
import org.jibble.pircbot.IrcException;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;

import GUI.Event.GUI_WindowClose;
import ConstantData.GUI_CD;

public class GUI_Main extends JFrame {
    private GUI_Components components;
    
    public GUI_Main() {
        JPanel P = new JPanel();
        Border margins = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        P.setBorder(margins);
        this.setContentPane(P);
        components = new GUI_Components(this.getContentPane());
        setLayout(null);
        addWindowListener(new GUI_WindowClose());
        
        setTitle("IRCBot Launcher");
        setSize(GUI_CD.window_X, GUI_CD.window_Y);
        setVisible(true);
    }
    
    public static void main(String args[]) throws IOException, IrcException {
        new GUI_Main();
    }
}

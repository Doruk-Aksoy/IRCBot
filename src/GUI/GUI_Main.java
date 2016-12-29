package GUI;

import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.Border;

import GUI.Event.GUI_WindowClose;
import ConstantData.GUI_CD;
import GUI.SubComponent.GUI_ImageIconMaker;

public class GUI_Main extends JFrame {
    private GUI_Components components;
    private static GUI_Main instance = null;
    private boolean browser_on;
    
    
    private GUI_Main() {
        JPanel P = new JPanel();
        Border margins = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        P.setBorder(margins);
        this.setContentPane(P);
        components = new GUI_Components(this.getContentPane());
        addWindowListener(new GUI_WindowClose());
        
        setLayout(null);
        setResizable(false);
        setTitle("IRCBot Launcher");
        setSize(GUI_CD.window_X, GUI_CD.window_Y);
        setLocationRelativeTo(null); // has special behavior with null, puts to center
        GUI_ImageIconMaker imaker = new GUI_ImageIconMaker();
        setIconImage(imaker.createImageIcon(GUI_CD.icon_path + "robot-icon.png").getImage());
        
        browser_on = false;
        
        setVisible(true);
    }
    
    public static GUI_Main getInstance() {
        if(instance != null)
            return instance;
        instance = new GUI_Main();
        return instance;
    }
    
    public boolean isBrowserOpen() {
        return browser_on;
    }
    
    public void setBrowserState(boolean t) {
        browser_on = t;
    }
    
    public static void main(String args[]) {
        GUI_Main.getInstance();
    }
}

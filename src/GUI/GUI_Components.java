package GUI;

// Contains every component of the GUI
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_Components {
    // allow access within package
    protected JPanel button_panel;
    protected JButton run_button;
    
    protected JLabel serverInputL;
    public static JTextField serverInputTF;
    
    protected JLabel channelInputL;
    public static JTextField channelInputTF;
    
    protected JLabel botnameInputL;
    public static JTextField botnameInputTF;
    
    public static ArrayList<GUI_Object> capables;
    
    public GUI_Components(Container C) {
        GUI_Builder B = new GUI_Builder();
        B.build(this, C);
    }
}

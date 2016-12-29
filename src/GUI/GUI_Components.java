package GUI;

// Contains every component of the GUI
import GUI.SubComponent.GUI_SwitchableImagePanel;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.*;

public class GUI_Components {
    // allow access within package
    protected JLabel serverInputL;
    public static JTextField serverInputTF;
    
    protected JLabel channelInputL;
    public static JTextField channelInputTF;
    
    protected JLabel botnameInputL;
    public static JTextField botnameInputTF;
    
    protected GUI_SwitchableImagePanel db_statusIMG;
    protected JLabel db_statusL;
    
    protected JButton run_button;
    protected JLabel dbfile_label;
    protected JButton dbfile_button;    
    
    protected JButton disconnect_button;
    
    public static ArrayList<GUI_Object> capables;
    
    public GUI_Components(Container C) {
        GUI_Builder B = new GUI_Builder();
        B.build(this, C);
    }
}

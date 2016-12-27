package GUI;

// Contains every component of the GUI

import java.awt.*;
import java.util.ArrayList;

public class GUI_Components {
    // allow access within package
    protected Panel button_panel;
    protected Button run_button;
    
    protected Panel input_panel;
    protected Label channelInputL;
    protected TextField channelInputTF;
    
    protected ArrayList<Panel> panels;
    
    public GUI_Components() {
        GUI_Builder B = new GUI_Builder();
        B.build(this);
    }
}

package GUI;

import java.awt.*;
import java.util.ArrayList;

import ConstantData.GUI_CD;

public class GUI_Builder {
    public void build(GUI_Components C) {
        C.panels = new ArrayList<>();
        
        C.input_panel = new Panel();
        C.channelInputL = new Label("Enter channels to join:");
        C.input_panel.add(C.channelInputL);
        C.channelInputTF = new TextField(GUI_CD.max_channel_length);
        C.input_panel.add(C.channelInputTF);
        
        C.button_panel = new Panel();
        C.run_button = new Button("Launch");
        C.button_panel.add(C.run_button);
        
        // add list of panels that contain sub-components
        C.panels.add(C.button_panel);
        C.panels.add(C.input_panel);
    }
}

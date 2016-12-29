package GUI.Event;

import GUI.GUI_Main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_WindowClose_FileBrowser extends WindowAdapter {
    @Override public void windowClosing(WindowEvent evt) {
       GUI_Main.getInstance().setBrowserState(false);
    }
}
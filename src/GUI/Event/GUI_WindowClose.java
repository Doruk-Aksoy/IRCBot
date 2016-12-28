package GUI.Event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI_WindowClose extends WindowAdapter {
    @Override public void windowClosing(WindowEvent evt) {
       System.exit(0);
    }
}
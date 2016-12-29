package GUI.Event;

import java.awt.event.ActionEvent;

import GUI.GUI_Main;
import GUI.SubComponent.GUI_FileBrowser;

public class GUI_DBFileButtonHandler implements GUI_EventHandler {
    @Override public void handle(ActionEvent e) {
        if(!GUI_Main.getInstance().isBrowserOpen()) {
            GUI_Main.getInstance().setBrowserState(true);
            new GUI_FileBrowser();
        }
    }
}

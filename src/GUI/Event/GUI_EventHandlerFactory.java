package GUI.Event;

import GUI.GUI_Object;

public class GUI_EventHandlerFactory {
    public GUI_EventHandler getHandler(GUI_Object.GUI_Object_Type type) {
        if(type == GUI_Object.GUI_Object_Type.GUIO_LAUNCHBUTTON)
            return new GUI_LaunchButtonHandler();
        else if(type == GUI_Object.GUI_Object_Type.GUIO_DBFILEBUTTON)
            return new GUI_DBFileButtonHandler();
        else if(type == GUI_Object.GUI_Object_Type.GUIO_DISCONNECTBUTTON)
            return new GUI_DisconnectButtonHandler();
        return null;
    }
}

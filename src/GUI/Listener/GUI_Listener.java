package GUI.Listener;

import java.awt.event.*;

import GUI.GUI_Components;
import GUI.GUI_Object;
import GUI.Event.GUI_EventHandlerFactory;

public class GUI_Listener implements ActionListener {
    @Override public void actionPerformed(ActionEvent e) {
        for(GUI_Object o : GUI_Components.capables)
            if(o.getObject().equals(e.getSource()))
                new GUI_EventHandlerFactory().getHandler(o.getType()).handle(e);
    }
}

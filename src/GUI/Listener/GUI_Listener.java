package GUI.Listener;

import java.awt.event.*;

import GUI.GUI_Components;
import GUI.GUI_Object;
import GUI.Event.GUI_LaunchButtonHandler;

public class GUI_Listener implements ActionListener {
    @Override public void actionPerformed(ActionEvent e) {
        for(GUI_Object o : GUI_Components.capables)
            if(o.getObject().equals(e.getSource()) && o.getType() == GUI_Object.GUI_Object_Type.GUIO_BUTTON)
                try {
                    new GUI_LaunchButtonHandler().handle(e);
                } catch(Exception E) {
                    System.out.println(E.toString() + " caught!");
                }
    }
}

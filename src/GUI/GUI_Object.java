package GUI;

import javax.swing.*;

public class GUI_Object {
    public enum GUI_Object_Type {
        GUIO_LAUNCHBUTTON,
        GUIO_DBFILEBUTTON,
        GUIO_DISCONNECTBUTTON
    };
    
    private final Object obj;
    private final GUI_Object_Type type;
    
    public GUI_Object(Object o, GUI_Object_Type t) {
        obj = o;
        type = t;
    }
    
    public Object getObject() {
        return obj;
    }
    
    public GUI_Object_Type getType() {
        return type;
    }
}

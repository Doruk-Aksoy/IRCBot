package GUI;

import javax.swing.*;

public class GUI_Object {
    public enum GUI_Object_Type {
        GUIO_BUTTON
    };
    
    private final Object obj;
    private final GUI_Object_Type type;
    
    public GUI_Object(JButton B) {
        obj = B;
        type = GUI_Object_Type.GUIO_BUTTON;
    }
    
    public Object getObject() {
        return obj;
    }
    
    public GUI_Object_Type getType() {
        return type;
    }
}

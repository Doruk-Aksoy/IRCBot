package GUI;

import ConstantData.GUI_CD;
import javax.swing.JOptionPane;

public class GUI_PopupHandler {
    public static void handle(GUI_CD.GUI_Popup_Type type) {
        JOptionPane.showMessageDialog(GUI_Main.getInstance(), GUI_CD.success_msg, GUI_CD.IRCBot_Success, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void handle(GUI_CD.GUI_Popup_Type type, Exception e) {
        GUI_ErrorPopupHandler.handle(e);
    }
}

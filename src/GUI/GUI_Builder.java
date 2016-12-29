package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Insets;

import ConstantData.GUI_CD;
import Database.Database_Connection;
import GUI.Listener.GUI_Listener;
import GUI.SubComponent.GUI_SwitchableImagePanel;

public class GUI_Builder {
    public void build(GUI_Components C, Container Ct) {
        ActionListener listener = new GUI_Listener();
        
        C.serverInputL = new JLabel("Enter server name to join:");
        C.serverInputTF = new JTextField(GUI_CD.max_server_length);
        C.channelInputL = new JLabel("Enter channels to join:");
        C.channelInputTF = new JTextField(GUI_CD.max_channel_length);
        C.botnameInputL = new JLabel("Enter the bot name:");
        C.botnameInputTF = new JTextField(GUI_CD.max_botname_length);
        
        C.serverInputL.setBounds(10, 12, 145, 15);
        C.serverInputTF.setBounds(165, 10, 125, 20);
        C.channelInputL.setBounds(10, 37, 125, 15);
        C.channelInputTF.setBounds(165, 35, 125, 20);
        C.botnameInputL.setBounds(10, 62, 145, 15);
        C.botnameInputTF.setBounds(165, 60, 125, 20);
        
        C.serverInputTF.setDocument(new GUI_JTextFieldLimit(GUI_CD.max_server_length));
        C.channelInputTF.setDocument(new GUI_JTextFieldLimit(GUI_CD.max_channel_length));
        C.botnameInputTF.setDocument(new GUI_JTextFieldLimit(GUI_CD.max_botname_length));
        
        C.run_button = new JButton("Launch");
        C.run_button.setBounds(GUI_CD.window_X / 2 - 50, GUI_CD.window_Y / 2, 80, 20);
        C.run_button.addActionListener(listener);
        
        // formula to center => w_X / 2 - length / 2 - margin_x, w_Y / 2 - height / 2 - margin_y
        C.disconnect_button = new JButton("Disconnect");
        C.disconnect_button.setBounds(GUI_CD.window_X / 2 - 70, GUI_CD.window_Y / 2 + 30, 120, 20);
        C.disconnect_button.addActionListener(listener);
        
        C.dbfile_label = new JLabel("Select Database File:");
        C.dbfile_button = new JButton("Open");
        C.dbfile_button.setBounds(165, 85, 80, 20);
        C.dbfile_button.addActionListener(listener);
        C.dbfile_label.setBounds(10, 87, 125, 15);
        
        C.db_statusL = new JLabel("Database Status");
        C.db_statusIMG = new GUI_SwitchableImagePanel(GUI_CD.icon_path + "cross.png", 30, 30, GUI_CD.icon_path + "tick.png", 30, 30);
        C.db_statusL.setBounds(260, 87, 125, 15);
        C.db_statusIMG.setBounds(360, 80, 40, 40);
        // add trigger of button
        Database_Connection DB = Database_Connection.getInstance();
        DB.addTrigger(C.db_statusIMG);
        
        // add everything to main panel
        Ct.add(C.run_button);
        Ct.add(C.disconnect_button);
        Ct.add(C.channelInputTF);
        Ct.add(C.channelInputL);
        Ct.add(C.serverInputL);
        Ct.add(C.serverInputTF);
        Ct.add(C.botnameInputL);
        Ct.add(C.botnameInputTF);
        Ct.add(C.dbfile_label);
        Ct.add(C.dbfile_button);
        Ct.add(C.db_statusIMG);
        Ct.add(C.db_statusL);
        
        // add to event capable lists object
        C.capables = new ArrayList<>();
        C.capables.add(new GUI_Object(C.run_button, GUI_Object.GUI_Object_Type.GUIO_LAUNCHBUTTON));
        C.capables.add(new GUI_Object(C.dbfile_button, GUI_Object.GUI_Object_Type.GUIO_DBFILEBUTTON));
        C.capables.add(new GUI_Object(C.disconnect_button, GUI_Object.GUI_Object_Type.GUIO_DISCONNECTBUTTON));
    }
    
    // for panels -- improve later
    public void setPos(Container Ct, Component C, int x, int y) {
        Insets insets = Ct.getInsets();
        Dimension size = C.getPreferredSize();
        C.setBounds(insets.left + x, insets.top + y, size.width, size.height);
    }
}

package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Insets;

import ConstantData.GUI_CD;
import GUI.Listener.GUI_Listener;

public class GUI_Builder {
    public void build(GUI_Components C, Container Ct) {
        ActionListener listener = new GUI_Listener();
        
        C.serverInputL = new JLabel("Enter server name to join:");
        C.serverInputTF = new JTextField(GUI_CD.max_server_length);
        C.channelInputL = new JLabel("Enter channels to join:");
        C.channelInputTF = new JTextField(GUI_CD.max_channel_length);
        C.botnameInputL = new JLabel("Enter the bot name:");
        C.botnameInputTF = new JTextField(GUI_CD.max_channel_length);
        
        C.serverInputL.setBounds(10, 12, 145, 15);
        C.serverInputTF.setBounds(165, 10, 125, 20);
        C.channelInputL.setBounds(10, 37, 125, 15);
        C.channelInputTF.setBounds(165, 35, 125, 20);
        C.botnameInputL.setBounds(10, 62, 145, 15);
        C.botnameInputTF.setBounds(165, 60, 125, 20);
        
        C.run_button = new JButton("Launch");
        C.run_button.setBounds(GUI_CD.window_X / 2 - 50, GUI_CD.window_Y / 2, 80, 20);
        C.run_button.addActionListener(listener);
        
        // add everything to main panel
        Ct.add(C.run_button);
        Ct.add(C.channelInputTF);
        Ct.add(C.channelInputL);
        Ct.add(C.serverInputL);
        Ct.add(C.serverInputTF);
        Ct.add(C.botnameInputL);
        Ct.add(C.botnameInputTF);
        
        // add to event capable lists object
        C.capables = new ArrayList<>();
        C.capables.add(new GUI_Object(C.run_button));
    }
    
    // for panels
    public void setPos(Container Ct, Component C, int x, int y) {
        Insets insets = Ct.getInsets();
        Dimension size = C.getPreferredSize();
        C.setBounds(insets.left + x, insets.top + y, size.width, size.height);
    }
}

package GUI.SubComponent;

import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

import ConstantData.GUI_CD;
import ConstantData.Game_Data;
import Database.Database_Connection;
import Database.Database_Info;
import GUI.Event.GUI_WindowClose_FileBrowser;
import GUI.GUI_Main;
import Games.GameData.GameDelay;
import java.util.concurrent.TimeUnit;

public class GUI_FileBrowser extends JFrame implements ActionListener {
    static private final String newline = "\n";
    private JButton openButton;
    private JTextArea log;
    private JFileChooser fc;

    public GUI_FileBrowser() {
        // log has to be first because listeners rely on it
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        // create a file chooser
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt Files", "txt");
        fc.setFileFilter(filter);
        openButton = new JButton("Open a File");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        GUI_ImagePanel imgPanel = new GUI_ImagePanel(GUI_CD.icon_path + "open-file-icon.png", 40, 40);

        
        buttonPanel.setBounds(110, 0, 120, 40);
        logScrollPane.setBounds(10, 40, 295, 80);
        imgPanel.setBounds(75, 0, 90, 90);
        // add stuff
        add(buttonPanel);
        add(logScrollPane);
        add(imgPanel);
        
        addWindowListener(new GUI_WindowClose_FileBrowser());
        
        setIconImage(GUI_Main.getInstance().getIconImage());
        setLocation(GUI_Main.getInstance().getLocation());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(GUI_CD.fbrowser_X, GUI_CD.fbrowser_Y);
        setResizable(false);
        setLayout(null);
        // make this shown
        setVisible(true);
    }
    
    // handle open button
    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(GUI_FileBrowser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Opening: " + file.getName() + "." + newline);
                readDBFile(file);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    private void readDBFile(File f) {
        try {
            Database_Info.reset();
            Database_Info.read(f);
        } catch(IOException E) {
            log.append("Something went wrong with the file." + newline);
        }
        // try database to see if it works with this file
        Database_Connection DB = Database_Connection.getInstance();
        DB.connect();
        if(DB.getConnection() != null) {
            log.append("Database connection established successfully." + newline);
            //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else
            log.append("Database file is either invalid or information is incorrect." + newline);
    }
}

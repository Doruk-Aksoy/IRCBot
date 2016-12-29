package GUI.SubComponent;

import javax.swing.ImageIcon;
import java.awt.Dimension;

import Trigger.*;
import Database.Database_Connection;

public class GUI_SwitchableImagePanel extends GUI_ImagePanel implements Triggerable {
    ImageIcon img2;
    
    public GUI_SwitchableImagePanel(ImageIcon I, ImageIcon I2) {
        super(I);
        img2 = I2;
    }
    
    public GUI_SwitchableImagePanel(String path1, String path2) {
        super(path1);
        img2 = new GUI_ImageIconMaker().createImageIcon(path2);
        setPreferredSize(new Dimension(img2.getIconWidth(), img2.getIconHeight()));
    }
    
    public GUI_SwitchableImagePanel(String path1, int w1, int h1, String path2, int w2, int h2) {
        super(path1, w1, h1);
        img2 = new GUI_ImageIconMaker().makeResized(path2, w2, h2);
        setPreferredSize(new Dimension(w2, h2));
    }
    
    @Override public void trigger(Notifier N) {
        if(((Database_Connection) N).getConnection() != null)
            toDraw = img2;
        else
            toDraw = img;
        repaint();
    }
}

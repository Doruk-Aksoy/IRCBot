package GUI.SubComponent;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GUI_ImagePanel extends JPanel {
    protected ImageIcon img, toDraw;
    
    public GUI_ImagePanel(ImageIcon I) {
        img = I;
        toDraw = img;
        setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
    }
    
    public GUI_ImagePanel(String path) {
        img = new GUI_ImageIconMaker().createImageIcon(path);
        toDraw = img;
        setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
    }
    
    public GUI_ImagePanel(String path, int w, int h) {
        img = new GUI_ImageIconMaker().makeResized(path, w, h);
        toDraw = img;
        setPreferredSize(new Dimension(w, h));
    }
    
    public void setImage(ImageIcon I) {
        img = I;
    }
    
    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);

        toDraw.paintIcon(this, g, 0, 0);
    }
}

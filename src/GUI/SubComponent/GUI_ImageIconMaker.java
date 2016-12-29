package GUI.SubComponent;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GUI_ImageIconMaker {
    public ImageIcon makeResized(String path, int x, int y) {
        ImageIcon img = createImageIcon(path);
        return new ImageIcon(img.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
    }
    
    public ImageIcon createImageIcon(String path) {
        java.net.URL imgsrc = getClass().getResource(path);
        if (imgsrc != null) {
            return new ImageIcon(imgsrc);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

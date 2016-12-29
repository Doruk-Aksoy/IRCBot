package GUI;

// from: http://stackoverflow.com/questions/3519151/how-to-limit-the-number-of-characters-in-jtextfield

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class GUI_JTextFieldLimit extends PlainDocument {
    private final int limit;

    GUI_JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
     }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}

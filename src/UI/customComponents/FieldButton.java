package UI.customComponents;

import javax.swing.*;
import java.awt.*;

public class FieldButton extends JButton {

    public FieldButton(String title){
        super(title);
    }

    public void setEnabledWithTextColor(boolean b, Color color){
        super.setEnabled(b);
        if(!b) {
            this.setForeground(color);
            this.setBackground(color);
            repaint();
            revalidate();
        }
    }
}

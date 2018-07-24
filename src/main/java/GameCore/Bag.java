package GameCore;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Bag extends GameObject {

    private Border border;


    public Bag(){
        setBounds(500,300,500,300);
        setBackground(Color.gray);
        border  = BorderFactory.createLineBorder(Color.red);
        setBorder(border);
        setVisible(false);
        setOpaque(true);
    }

}

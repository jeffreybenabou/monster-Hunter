package GameCore;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Bag extends GameObject {

    private Border border;
    private JLabel key1,key2,key3;


    public Bag(){
        setBagProperties();
        setTheBagInside();
    }

    private void setTheBagInside() {
        key1=new JLabel();
        key2=new JLabel();
        key3=new JLabel();

        key1.setBounds(50,30,100,100);
        key1.setBorder(border);
        key2.setBounds(200,30,100,100);
        key2.setBorder(border);
        key3.setBounds(350,30,100,100);
        key3.setBorder(border);
        add(key1);
        add(key2);
        add(key3);

    }

    private void setBagProperties() {
        setBounds(500,300,500,300);
        setBackground(Color.gray);
        border  = BorderFactory.createLineBorder(Color.red);
        setBorder(border);
        setVisible(false);
        setOpaque(true);
    }

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
    }

    public JLabel getKey1() {
        return key1;
    }

    public void setKey1(JLabel key1) {
        this.key1 = key1;
    }

    public JLabel getKey2() {
        return key2;
    }

    public void setKey2(JLabel key2) {
        this.key2 = key2;
    }

    public JLabel getKey3() {
        return key3;
    }

    public void setKey3(JLabel key3) {
        this.key3 = key3;
    }
}

package GameCore;

import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class World extends JLabel implements MouseListener {

    public World(){
        setBounds(0,0,10000,10000);
        setLayout(null);
        setBackground(Color.cyan);
        setOpaque(true);
        addMouseListener(this);
        add(StaticVariables.mainPlayer);


    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        StaticVariables.mainPlayer.calculateTheAngle(e.getX(),e.getY());
        MainPlayer.point=new Point(e.getPoint().x,e.getPoint().y);

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

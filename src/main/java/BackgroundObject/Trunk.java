package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Trunk extends GameObject {


    public Trunk(){
        setIcon(new ImageIcon(StaticVariables.trunk));
        setSize(200,150);
        setTheTrunkSpecial();
    }

    public void setTheTrunkSpecial(){
        setIcon(new ImageIcon(StaticVariables.specialTrunk));
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                setIcon(new ImageIcon(StaticVariables.hollowTrunk));
                repaint();
                new SackMoney();
                removeMouseListener(this);


            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });


    }

}

package GameCore;

import BackgroundObject.House;
import BackgroundObject.Tree;
import BackgroundObject.Trunk;
import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLabel implements MouseListener {


    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private Random random;
    public World(){
        setBounds(0,0,10000,10000);
        setLayout(null);
        setBackground(Color.cyan);
        setOpaque(true);
        addMouseListener(this);

        addBackGroundObjects();


    }

    private void addMouseLisenersToObject(){
        /*
        * this method use for insert mouse liseners to obecjt to know if the user is
        * preesing on object to avoid the main player to walk on object e.g:
        * walking on house
        * */
        for (GameObject gameObject :backGroundObjects) {
            if(gameObject.getClass().getSimpleName().equals("House"))
            gameObject.addMouseListener(this);
        }
    }

    private void addBackGroundObjects() {

        new Thread(new Runnable() {

            public void run() {
                random=new Random();


                for (int i = 0; i <80 ; ) {
                    Tree tree=new Tree();
                    tree.setBounds(random.nextInt(getWidth()),random.nextInt(getHeight()),400,600);
                    if(!checkIfInetrcet(tree))
                    {
                        backGroundObjects.add(tree);
                        add(tree);
                        i++;
                    }

                }
                add(StaticVariables.mainPlayer);

                for (int i = 0; i < 3; i++) {
                    House house = new House(i);
                    backGroundObjects.add(house);
                    add(house);

                }
                for (int i = 0; i <80 ; ) {
                    Trunk trunk=new Trunk();
                    trunk.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                    if(!checkIfInetrcet(trunk))
                    {
                        backGroundObjects.add(trunk);
                        add(trunk);
                        i++;
                    }
                }
                addMouseLisenersToObject();
            }
        }).start();


    }

    private boolean checkIfInetrcet(GameObject  object) {
        for (GameObject gameObject: backGroundObjects) {
            if(gameObject.getBounds().intersects(object.getBounds()))
            {
                return true;
            }

        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        StaticVariables.mainPlayer.calculateTheAngle(e.getX(),e.getY());
        MainPlayer.point=new Point(StaticVariables.mainPlayer.getX(),StaticVariables.mainPlayer.getY());
        if(e.getSource().equals(this))
        {

            MainPlayer.point=new Point(e.getPoint().x,e.getPoint().y);
        }


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

package GameCore;

import BackgroundObject.House;
import BackgroundObject.Rock;
import BackgroundObject.Tree;
import BackgroundObject.Trunk;
import Objects.Ghost;
import Objects.MainPlayer;
import sun.awt.windows.ThemeReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLabel implements MouseListener {


    private ArrayList<Ghost>ghostArrayList=new ArrayList<Ghost>();
    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private Random random;
    private Ghost firstGhost;
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
    public void addGhost(){
        if(!Ghost.notTheFirstGhost)
        {
            new Thread(new Runnable() {
                public void run() {
                    firstGhost=new Ghost(0);
                    firstGhost.setLocation(5000,5000);
                    ghostArrayList.add(firstGhost);
                    add(firstGhost);

                }
            }).start();

        }
        else
            switch (StaticVariables.level)
            {
                case 1:{

                    for (int i = 0; i <10 ; i++) {
                        Random random=new Random();
                        Ghost ghost=new Ghost(0);
                        ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                        ghostArrayList.add(ghost);
                        add(ghost);
                    }
                    break;
                }
                case 2:{
                    boolean ok=false;
                    int sum;
                    for (int i = 0; i <15 ; i++) {
                        Random random=new Random();
                        sum=random.nextInt(1);
                        if(sum==1)
                            ok=true;

                        Ghost ghost=new Ghost(sum);
                        ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                        ghostArrayList.add(ghost);
                        add(ghost);
                    }
                    if(!ok)
                    {
                        Random random=new Random();
                        Ghost ghost=new Ghost(2);
                        ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                        ghostArrayList.add(ghost);
                        add(ghost);
                    }
                    break;
                }
                case 3:{
                    boolean ok=false;
                    int sum=0;
                    for (int i = 0; i <20 ; i++) {
                        Random random=new Random();
                        sum=random.nextInt(2);
                        if(sum>1)
                            ok=true;
                        Ghost ghost=new Ghost(random.nextInt(2));
                        ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                        ghostArrayList.add(ghost);
                        add(ghost);
                    }
                    if(!ok)
                    {
                        Random random=new Random();

                        Ghost ghost=new Ghost(1);
                        ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                        ghostArrayList.add(ghost);
                        add(ghost);
                    }
                    break;
                }
            }


    }

    private void addBackGroundObjects() {

        new Thread(new Runnable() {

            public void run() {
                random=new Random();
                addGhost();

                for (int i = 0; i < 3; ) {
                    House house = new House(i);
                    if (!checkIfInetrcet(house)) {
                        backGroundObjects.add(house);
                        house.setVisible(false);
                        add(house);
                        i++;
                    }
                }

                for (int i = 0; i <140 ; ) {
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

                for (int i = 0; i <10 ; ) {
                    Rock rock=new Rock();
                    rock.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                    if(!checkIfInetrcet(rock))
                    {
                        backGroundObjects.add(rock);
                        add(rock);
                        i++;

                    }
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
        if(e.getComponent().getClass().getSimpleName().equals("Ghost")&&StaticVariables.mainPlayer.getBounds().intersects(e.getComponent().getBounds())&&!StaticVariables.mainPlayer.isAttacking())
        {

            Random random=new Random();
            StaticVariables.mainPlayer.setDamgeToGhost((random.nextInt(700)+400));
            Ghost ghost=(Ghost) (e.getComponent());
            ghost.getLifeBar().getjProgressBar().setValue(ghost.getLifeBar().getjProgressBar().getValue()-StaticVariables.mainPlayer.getDamgeToGhost());
            ghost.getLifeBar().getjProgressBar().setString(""+ghost.getLifeBar().getjProgressBar().getValue());
            StaticVariables.mainPlayer.setAttacking(true);
            StaticVariables.mainPlayer.setIndex(0);
            ghost.repaint();
        }


    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

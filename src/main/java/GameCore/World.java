package GameCore;
import BackgroundObject.*;

import Objects.Ghost;
import Objects.MainPlayer;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLabel implements MouseListener {


    public static ArrayList<Ghost>ghostArrayList=new ArrayList<Ghost>();
    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private Random random;
    private Ghost firstGhost;

    public World(){
        setBounds(0,0,10000,10000);
        setLayout(null);
        setBackground(Color.GRAY);
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
        new Thread(new Runnable() {
            public void run() {
                if(!Ghost.notTheFirstGhost)
                {
                    /*
                     *  first ghost will apper to the player and when the player kill the ghost
                     *  then the method will be called again but with Ghost.notTheFirstGhost=true
                     *  and will add ghosts and house to the world
                     *
                     * */

                            firstGhost=new Ghost(1);
                            firstGhost.setLocation(1000,1000);
                            add(firstGhost);
                            ghostArrayList.add(firstGhost);
                            while(firstGhost.getLifeBar().isAlive()){
                                try {
                                    Thread.sleep(20);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    ghostArrayList.remove(firstGhost);
                            firstGhost=null;
                            Ghost.notTheFirstGhost=true;
                            addGhost();



                }
                else
                {
                    for (GameObject house:backGroundObjects) {
                        if(house.getClass().getSimpleName().equals("House"))
                            house.setVisible(true);
                    }
                    switch (StaticVariables.level)
                    {
                        case 1:{

                            for (int i = 0; i <5 ; i++) {
                                Random random=new Random();
                                Ghost ghost=new Ghost(1);
                                ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                                System.out.println(ghost.getLocation());
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }

                            break;
                        }
                        case 2:{
                            ghostArrayList=new ArrayList<Ghost>();
                            Ghost.addGhostImage();
                            for (int i = 0; i <5 ; i++) {
                                Random random=new Random();
                                Ghost ghost=new Ghost(2);
                                ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                                System.out.println(ghost.getLocation());
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }


                            break;
                        }
                        case 3:{
                            ghostArrayList=new ArrayList<Ghost>();
                            boolean ok=false;
                            int sum;
                            for (int i = 0; i <10 ; i++) {
                                Random random=new Random();
                                sum=random.nextInt(3);
                                if(sum>2)
                                    ok=true;
                                Ghost ghost=new Ghost(random.nextInt(3));
                                ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                                ghostArrayList.add(ghost);
                                add(ghost);
                            }
                            if(!ok)
                            {
                                Random random=new Random();

                                Ghost ghost=new Ghost(3);
                                ghost.setLocation(random.nextInt(getWidth()),random.nextInt(getHeight()));
                                ghostArrayList.add(ghost);
                                add(ghost);
                            }


                            break;
                        }
                    }

                }
            }
        }).start();




    }

    private void addBackGroundObjects() {



        new Thread(new Runnable() {

            public void run() {

                random=new Random();

                        for (int i = 0; i <10 ; i++) {

                            {

                                        Cloud cloud=new Cloud(random.nextInt(getWidth()),random.nextInt(getHeight()));
                                        add(cloud);


                            }



                        }
                        for (int i = 0; i <30 ; i++) {
                            Bird bird=new Bird(random.nextInt(getWidth()),random.nextInt(getHeight()));
                            add(bird);

                        }




                addGhost();

                for (int i = 0; i < 3; ) {
                    House house = new House(i);

                        backGroundObjects.add(house);
                        house.setVisible(false);
                        add(house);
                        i++;

                }

                for (int i = 0; i <120 ; ) {
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

    public void checkIfMainPlayerFightTheGhost(MouseEvent e){

        if(
                MainPlayer.intersect
                &&!StaticVariables.mainPlayer.isAttacking()&&e.getComponent().getClass().getSimpleName().equals("Ghost"))
        {
            Ghost ghost=(Ghost)e.getComponent();
            MainPlayer.walking=false;
            MainPlayer.stand=false;
            StaticVariables.mainPlayer.calculateTheAngle(ghost.getX(),ghost.getY());
            StaticVariables.mainPlayer.setDamgeToGhost(10);
            StaticVariables.mainPlayer.setIndex(0);
            // TODO: 29/06/2018 work on upgrade
            StaticVariables.mainPlayer.setAttacking(true);

        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        

        if(e.getButton()==MouseEvent.BUTTON1)
        {
            checkIfPlayerPreesTheWorldOrGhost(e);
            checkIfMainPlayerFightTheGhost(e);

        }
        if(e.getButton()==MouseEvent.BUTTON3)
        {
            MainPlayer.walking=false;
            MainPlayer.stand=true;
        }




    }



    private void checkIfPlayerPreesTheWorldOrGhost(MouseEvent e) {
        if(e.getSource().equals(this)
                ||e.getSource().getClass().getSimpleName().equals("Ghost")&&!e.getComponent().getBounds().intersects(StaticVariables.mainPlayer.getBounds()))
        {
            Ghost ghost;
            if(e.getSource().getClass().getSimpleName().equals("Ghost"))
            {
                ghost=((Ghost)e.getSource());
                StaticVariables.mainPlayer.calculateTheAngle(ghost.getX(),ghost.getY());
                MainPlayer.point=new Point(ghost.getX(),ghost.getY());

            }
            else
            {
                StaticVariables.mainPlayer.calculateTheAngle(e.getX(),e.getY());
                MainPlayer.point=new Point(e.getX(),e.getY());
            }

            MainPlayer.stand=false;
            MainPlayer.walking=true;

        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }


    public ArrayList<Ghost> getGhostArrayList() {
        return ghostArrayList;
    }

    public void setGhostArrayList(ArrayList<Ghost> ghostArrayList) {
        this.ghostArrayList = ghostArrayList;
    }

    public ArrayList<GameObject> getBackGroundObjects() {
        return backGroundObjects;
    }

    public void setBackGroundObjects(ArrayList<GameObject> backGroundObjects) {
        this.backGroundObjects = backGroundObjects;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Ghost getFirstGhost() {
        return firstGhost;
    }

    public void setFirstGhost(Ghost firstGhost) {
        this.firstGhost = firstGhost;
    }


}

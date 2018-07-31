package GameCore;
import BackgroundObject.*;

import Objects.Ghost;
import Objects.MainPlayer;
import sound.Sound;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class World extends JLabel implements MouseListener {



    private  boolean housePreesed=false;
    private  ArrayList<Ghost>ghostArrayList=new ArrayList<Ghost>();
    private  boolean ghostAreBeingAdd=false;
    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private  ArrayList<House>houseArrayList=new ArrayList<House>();
    private Random random;
    private Ghost firstGhost;
    private Sound backGroundWorld;
    private boolean key1IsPreesed=false,key2IsPreesed=false,key3IsPreesed=false;

    public World(){
        try
        {
            setBounds(-500,-750,5000,5000);
            setBackground(Color.GRAY);
            backGroundWorld=new Sound();
            backGroundWorld.playSound(Sound.path.get(4));
            backGroundWorld.setVolume(-10);
            addMouseListener(this);

            addBackGroundObjects();
        }catch (Exception e)
        {

        }





    }

    private void addMouseLisenersToObject(){
        /*
        * this method use for insert mouse liseners to obecjt to know if the user is
        * preesing on object to avoid the main player to walk on object e.g:
        * walking on house
        * */
        for (House house :getHouseArrayList()) {

            house.addMouseListener(this);
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

                    setFirstGhost(new Ghost(1));

                            getFirstGhost().setLocation(2500,2500);
                    getFirstGhost().setName(""+0);
                    getGhostArrayList().add(getFirstGhost());
                    add(getFirstGhost());
                    StaticVariables.miniMap.addTheGhostLocationToMap(0,getFirstGhost().getLocation());
                            while(getFirstGhost().getLifeBar().isAlive()){
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }catch (NullPointerException e)
                                {

                                }
                            }

                            Ghost.notTheFirstGhost=true;
                            addGhost();



                }
                else
                {
                    setGhostAreBeingAdd(true);

                    try
                    {
                        for (House house:StaticVariables.world.getHouseArrayList()) {
                            house.setVisible(true);
                            house.getHouseEntrance().setVisible(true);
                        }
                    }catch (ConcurrentModificationException house )
                    {
                        house.printStackTrace();
                    }

                    getGhostArrayList().clear();
                    switch (StaticVariables.level)
                    {
                        case 1:{
                            for (int i = 0; i <1; i++) {

                                Ghost ghost=new Ghost(1);

                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                getGhostArrayList().add(ghost);
                                add(ghost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }

                            break;
                        }
                        case 2:{

                            ghostArrayList=new ArrayList<Ghost>();
                            Ghost.addGhostImage();
                            for (int i = 0; i <1 ; i++) {
                                Ghost ghost=new Ghost(2);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }


                            break;
                        }
                        case 3:{
                            ghostArrayList=new ArrayList<Ghost>();
                            Ghost.addGhostImage();
                            for (int i = 0; i <20 ; i++) {

                                Ghost ghost=new Ghost(3);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }


                            break;
                        }
                    }
                    getGhostArrayList().remove(getFirstGhost());
                    setFirstGhost(null);
                    setGhostAreBeingAdd(false);

                }
            }
        }).start();




    }

    private void addBackGroundObjects() {
        setRandom(new Random());



        new Thread(new Runnable() {

            public void run() {



                        for (int i = 0; i <15 ; i++) {

                            {

                                        Cloud cloud=new Cloud(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));
                                        add(cloud);
                                Bird bird=new Bird(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));
                                add(bird);


                            }



                        }





                addGhost();



                for (int i = 0; i <30 ; ) {
                    Tree tree=new Tree();
                    tree.setBounds(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()),400,600);
                    if(!checkIfInetrcet(tree))
                    {
                        getBackGroundObjects().add(tree);
                        add(tree);
                        i++;

                    }

                }
                add(StaticVariables.mainPlayer);

                for (int i = 0; i < 3; ) {
                    House house = new House(i);
                    getBackGroundObjects().add(house);
                    getHouseArrayList().add(house);
                    house.setVisible(false);
                    add(house);
                    i++;

                }






                for (int i = 0; i <20 ; ) {
                    Trunk trunk=new Trunk();
                    trunk.setLocation(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));

                    if(!checkIfInetrcet(trunk))
                    {
                        getBackGroundObjects().add(trunk);
                        add(trunk);
                        i++;
                    }
                }

                addMouseLisenersToObject();


            }
        }).start();


    }


    private boolean checkIfInetrcet(GameObject  object) {
        for (GameObject gameObject: getBackGroundObjects()) {
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
                &&!MainPlayer.isAttacking()&&e.getComponent().getClass().getSimpleName().equals("Ghost"))
        {
            Ghost ghost=(Ghost)e.getComponent();
            MainPlayer.walking=false;
            MainPlayer.stand=false;
            StaticVariables.mainPlayer.calculateTheAngle(ghost.getX(),ghost.getY());
            StaticVariables.mainPlayer.setDamgeToGhost(10);
            StaticVariables.mainPlayer.setIndex(0);
            // TODO: 29/06/2018 work on upgrade
            MainPlayer.setAttacking(true);

        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {


        if(e.getButton()==MouseEvent.BUTTON2)
        {
            if(GamePanel.jProgressBar.getValue()==GamePanel.jProgressBar.getMaximum()&&MainPlayer.intersect)
            {

                StaticVariables.mainPlayer.setIndex(0);
                MainPlayer.spacielAttack=true;


            }

        }
        if(e.getButton()==MouseEvent.BUTTON1&&!MainPlayer.spacielAttack)
        {

            checkIfPreesTheSetting(e);
            checkIfPlayerPreesTheWorldOrGhost(e);
            checkIfMainPlayerFightTheGhost(e);
//            checkIfUserPreesTheHouse(e);


        }
        if(e.getButton()==MouseEvent.BUTTON3&&!MainPlayer.spacielAttack)
        {
            MainPlayer.walking=false;
            MainPlayer.stand=true;

        }




    }

    private void checkIfPreesTheSetting(MouseEvent e) {
        if(e.getComponent().equals(StaticVariables.gamePanel.getMoneyIcon())||
                e.getComponent().equals(StaticVariables.gamePanel.getBag())||
                e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey1())||
                e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey2())||
                e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey3()))
        {
            StaticVariables.gamePanel.getBag().setVisible(true);


        }
        else
            StaticVariables.gamePanel.getBag().setVisible(false);

        for (House house :houseArrayList) {
            if(e.getComponent().equals(house)&&key1IsPreesed||key2IsPreesed||key3IsPreesed)
            {
                setKey1IsPreesed(false);
                setKey2IsPreesed(false);
                setKey3IsPreesed(false);

                Key.removeTheKeyLiseners();
                StaticVariables.level++;
                addGhost();
                StaticVariables.miniMap.addActionOfMiniMap();
                break;

            }
        }
        setKey1IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey1()));
        setKey2IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey2()));
        setKey3IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey3()));









    }

    private void checkIfUserPreesTheHouse(MouseEvent e) {

        House house=null;
        Point point=null;
        if(!e.getComponent().getClass().getSimpleName().equals("House"))
        {
            point=new Point(getX(),getY());

        }
        else if (e.getComponent().getClass().getSimpleName().equals("House"))
            house=(House)e.getComponent();

        if(e.getComponent().equals(house)&&housePreesed)
        {

            setLocation(point);
            housePreesed=false;
        }
        if(house!=null&&!housePreesed)
        {
            switch (Integer.parseInt(house.getName()))
            {
                case 1:
                {
                    setLocation(getX()+500,getY()+500);
                    break;
                }
                case 2:
                {
                    setLocation(getX()+500,getY()+500);
                    break;
                }
                case 3:
                {
                    setLocation(getX()-500,getY()-500);
                    break;
                }
            }
            housePreesed=true;
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


    public boolean isHousePreesed() {
        return housePreesed;
    }

    public void setHousePreesed(boolean housePreesed) {
        this.housePreesed = housePreesed;
    }

    public boolean isGhostAreBeingAdd() {
        return ghostAreBeingAdd;
    }

    public void setGhostAreBeingAdd(boolean ghostAreBeingAdd) {
        this.ghostAreBeingAdd = ghostAreBeingAdd;
    }

    public ArrayList<House> getHouseArrayList() {
        return houseArrayList;
    }

    public void setHouseArrayList(ArrayList<House> houseArrayList) {
        this.houseArrayList = houseArrayList;
    }

    public boolean isKey1IsPreesed() {
        return key1IsPreesed;
    }

    public void setKey1IsPreesed(boolean key1IsPreesed) {
        this.key1IsPreesed = key1IsPreesed;
    }

    public boolean isKey2IsPreesed() {
        return key2IsPreesed;
    }

    public void setKey2IsPreesed(boolean key2IsPreesed) {
        this.key2IsPreesed = key2IsPreesed;
    }

    public boolean isKey3IsPreesed() {
        return key3IsPreesed;
    }

    public void setKey3IsPreesed(boolean key3IsPreesed) {
        this.key3IsPreesed = key3IsPreesed;
    }

    public Sound getBackGroundWorld() {
        return backGroundWorld;
    }

    public void setBackGroundWorld(Sound backGroundWorld) {
        this.backGroundWorld = backGroundWorld;
    }
}

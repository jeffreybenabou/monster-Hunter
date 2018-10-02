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



    private JLabel hole;
    private  boolean housePreesed=false;
    private  ArrayList<Ghost>ghostArrayList=new ArrayList<Ghost>();
    private  boolean ghostAreBeingAdd=false;
    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private  ArrayList<House>houseArrayList=new ArrayList<House>();
    private Random random;
    private Ghost firstGhost;
    private Sound backGroundWorld;
    private boolean key1IsPreesed=false,key2IsPreesed=false,key3IsPreesed=false;
    public static boolean userInProgressToOpenHouse=false;
    private int index =0,indexOfGhost=0;

    public World(){



            setBounds(-500,-750,5000,5000);
            setBackground(Color.GRAY);
            backGroundWorld=new Sound();
            backGroundWorld.playSound(Sound.path.get(4));

            addMouseListener(this);

            addBackGroundObjects();
            setVisible(true);








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

                    try
                    {
                        setFirstGhost(new Ghost(1));

                        getFirstGhost().setLocation(2500,2500);
                        getFirstGhost().setName(""+0);
                        getGhostArrayList().add(getFirstGhost());
                        indexOfGhost=index;
                        add(getFirstGhost(), index++);

                    }catch (Exception e)
                    {
e.printStackTrace();
                    }
                    StaticVariables.miniMap.addTheGhostLocationToMap(0,getFirstGhost().getLocation());

                    try
                    {
                        while(getFirstGhost().getLifeBar().isAlive()){
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }catch (NullPointerException e)
                            {
 e.printStackTrace();
                            }
                        }

                    }catch (Exception e)
                    {

                        e.printStackTrace();
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
                            for (int i = 0; i <30; i++) {

                                Ghost ghost=new Ghost(1);

                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                getGhostArrayList().add(ghost);
                                add(ghost, indexOfGhost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }

                            break;
                        }
                        case 2:{

                            ghostArrayList=new ArrayList<Ghost>();
                            Ghost.addGhostImage();
                            for (int i = 0; i <10 ; i++) {
                                Ghost ghost=new Ghost(2);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost, indexOfGhost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }


                            break;
                        }
                        case 3:{
                            ghostArrayList=new ArrayList<Ghost>();
                            Ghost.addGhostImage();
                            for (int i = 0; i <5; i++) {

                                Ghost ghost=new Ghost(3);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                ghostArrayList.add(ghost);
                                add(ghost, indexOfGhost);
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
                                        Cloud cloud=new Cloud(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));
                                        add(cloud, index++);
                                Bird bird=new Bird(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));
                                add(bird, index++);
                        }

                for (int i = 0; i < 3; ) {
                    House house = new House(i);
                    getBackGroundObjects().add(house);
                    getHouseArrayList().add(house);
                    house.setVisible(false);
                    add(house, index++);
                    i++;

                }







                for (int i = 0; i <30 ; ) {
                    Tree tree=new Tree();
                    tree.setBounds(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()),400,600);
                    if(!checkIfInetrcet(tree))
                    {
                        getBackGroundObjects().add(tree);
                        add(tree, index++);
                        i++;

                    }

                }
                add(StaticVariables.mainPlayer, index++);




                addGhost();



                for (int i = 0; i <20 ; ) {
                    Trunk trunk=new Trunk();
                    trunk.setLocation(getRandom().nextInt(getWidth()),getRandom().nextInt(getHeight()));

                    if(!checkIfInetrcet(trunk))
                    {
                        getBackGroundObjects().add(trunk);
                        add(trunk, index++);
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
            StaticVariables.mainPlayer.setDemageToGhost(10);
            StaticVariables.mainPlayer.setIndex(0);
            MainPlayer.setAttacking(true);

        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        checkIfUserPreesTheCoin(e);

        if(e.getButton()==MouseEvent.BUTTON2)
        {
            checkIfSpacialAttack();


        }
        if(e.getButton()==MouseEvent.BUTTON1&&!MainPlayer.spacialAttack)
        {

            checkIfPreesTheSetting(e);
            checkIfPlayerPreesTheWorldOrGhost(e);
            checkIfMainPlayerFightTheGhost(e);
            checkIfUserIsInsideTheEntrance(e);


        }
        if(e.getButton()==MouseEvent.BUTTON3&&!MainPlayer.spacialAttack)
        {
            MainPlayer.walking=false;
            MainPlayer.stand=true;

        }




    }
    private boolean checkIfEnoughMoney(int amount){
        return amount<=StaticVariables.sumOfMoney;
    }
    private void checkIfUserWantToBuySomething(MouseEvent e){
        if(e.getComponent().equals(StaticVariables.gamePanel.getLife1000()))
        {
            if(checkIfEnoughMoney(1000))
            {
                if(Integer.parseInt(MainPlayer.getLife().getjProgressBar().getString())+5000<=MainPlayer.getLife().getjProgressBar().getMaximum())
                {
                    MainPlayer.getLife().getjProgressBar().setString( ""+(MainPlayer.getLife().getjProgressBar().getValue()+5000));
                    MainPlayer.getLife().getjProgressBar().setValue( MainPlayer.getLife().getjProgressBar().getValue()+5000);


                }
                else
                {
                    MainPlayer.getLife().getjProgressBar().setValue( MainPlayer.getLife().getjProgressBar().getMaximum());
                    MainPlayer.getLife().getjProgressBar().setString( ""+(MainPlayer.getLife().getjProgressBar().getMaximum()));

                }

                StaticVariables.sumOfMoney-=1000;
                StaticVariables.gamePanel.getBuy().startSound();
                StaticVariables.gamePanel.getBuy().getClip().loop(0);
            }
            else
            {
                StaticVariables.gamePanel.getNotbuy().startSound();
                StaticVariables.gamePanel.getNotbuy().getClip().loop(0);
            }

        }
        else if(e.getComponent().equals(StaticVariables.gamePanel.getLife2000()))
        {
            if(checkIfEnoughMoney(2000))
            {
                if(Integer.parseInt(MainPlayer.getLife().getjProgressBar().getString())+10000<=MainPlayer.getLife().getjProgressBar().getMaximum())
                {
                    MainPlayer.getLife().getjProgressBar().setString( ""+(MainPlayer.getLife().getjProgressBar().getValue()+10000));
                    MainPlayer.getLife().getjProgressBar().setValue( MainPlayer.getLife().getjProgressBar().getValue()+10000);


                }
                else
                {
                    MainPlayer.getLife().getjProgressBar().setValue( MainPlayer.getLife().getjProgressBar().getMaximum());
                    MainPlayer.getLife().getjProgressBar().setString( ""+(MainPlayer.getLife().getjProgressBar().getMaximum()));

                }
                StaticVariables.gamePanel.getBuy().startSound();
                StaticVariables.gamePanel.getBuy().getClip().loop(0);
                StaticVariables.sumOfMoney-=2000;
            }else
            {
                StaticVariables.gamePanel.getNotbuy().startSound();
                StaticVariables.gamePanel.getNotbuy().getClip().loop(0);
            }
        }
       else  if(e.getComponent().equals(StaticVariables.gamePanel.getPower1()))
        {
            if(checkIfEnoughMoney(400))
            {
                if(GamePanel.getjProgressBar().getValue()+25<=100)
                {
                    GamePanel.getjProgressBar().setValue( GamePanel.getjProgressBar().getValue()+25);



                }
                else
                {
                    GamePanel.getjProgressBar().setString( ""+GamePanel.getjProgressBar().getMaximum());
                    GamePanel.getjProgressBar().setValue( GamePanel.getjProgressBar().getMaximum());

                }
                StaticVariables.gamePanel.getBuy().startSound();
                StaticVariables.gamePanel.getBuy().getClip().loop(0);
                StaticVariables.sumOfMoney-=400;
            }else
            {
                StaticVariables.gamePanel.getNotbuy().startSound();
                StaticVariables.gamePanel.getNotbuy().getClip().loop(0);
            }
        }
        else if(e.getComponent().equals(StaticVariables.gamePanel.getPower2()))
        {
            if(checkIfEnoughMoney(800))
            {
                if(GamePanel.getjProgressBar().getValue()+50<=100)
                {
                    GamePanel.getjProgressBar().setValue( GamePanel.getjProgressBar().getValue()+50);





                }
                else
                {
                    GamePanel.getjProgressBar().setString( ""+GamePanel.getjProgressBar().getMaximum());
                    GamePanel.getjProgressBar().setValue( GamePanel.getjProgressBar().getMaximum());

                }
                StaticVariables.sumOfMoney-=800;
                StaticVariables.gamePanel.getBuy().startSound();
                StaticVariables.gamePanel.getBuy().getClip().loop(0);
            }
            else
            {
                StaticVariables.gamePanel.getNotbuy().startSound();
                StaticVariables.gamePanel.getNotbuy().getClip().loop(0);
            }
        }

    }

    private void checkIfUserIsInsideTheEntrance(MouseEvent e){


        for (House house:houseArrayList) {
            if(house.getHouseEntrance().equals(e.getComponent()))
            {
                checkIfUserPreesTheHouse(e);
                break;
            }


        }




    }

    private void checkIfUserPreesTheCoin(MouseEvent e) {
        if(e.getComponent().getClass().getSimpleName().equals("CoinAdd"))
        {
            StaticVariables.gamePanel.getBuy().startSound();
            StaticVariables.gamePanel.getBuy().getClip().loop(0);
            CoinAdd coinAdd=(CoinAdd)e.getComponent();
            coinAdd.changeTheSumOfMoney();
            coinAdd.setVisible(false);


        }

    }

    private void checkIfSpacialAttack() {
        if(GamePanel.jProgressBar.getValue()==GamePanel.jProgressBar.getMaximum()&&MainPlayer.intersect)
        {

            StaticVariables.mainPlayer.setIndex(0);
            MainPlayer.spacialAttack =true;


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

        if(e.getComponent().equals(StaticVariables.gamePanel.getSettingLabel()))
        {
            new SaveGame(MainMenu.pathToFile,MainMenu.pathToImage);
        }

        for (House house :houseArrayList) {
            if(e.getComponent().equals(house)&&key1IsPreesed||key2IsPreesed||key3IsPreesed)
            {
                setKey1IsPreesed(false);
                setKey2IsPreesed(false);
                setKey3IsPreesed(false);

                Key.removeTheKeyLiseners();
                StaticVariables.level++;
                StaticVariables.gamePanel.getLabel().setText(""+StaticVariables.level);
                addGhost();
                StaticVariables.miniMap.addActionOfMiniMap();
                World.userInProgressToOpenHouse=false;

                break;

            }
        }
        setKey1IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey1()));
        setKey2IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey2()));
        setKey3IsPreesed(e.getComponent().equals(StaticVariables.gamePanel.getBag().getKey3()));

        if(e.getComponent().equals(StaticVariables.gamePanel.getShopIcon())
                ||e.getComponent().equals(StaticVariables.gamePanel.getShopLabel()))
        {

            StaticVariables.gamePanel.getShopLabel().setVisible(true);
        }
        else if (e.getComponent().equals(this)){
            StaticVariables.gamePanel.getShopLabel().setVisible(false);

        }
        checkIfUserWantToBuySomething(e);









    }

    private void checkIfUserPreesTheHouse(MouseEvent e) {



            if(e.getComponent().getBounds().intersects(StaticVariables.mainPlayer.getBounds())&&!housePreesed)
            {

                switch (Integer.parseInt(e.getComponent().getName())) {
                    case 0: {
                        //                downleft

                        if(!House.houseNumber1Open&&Key.key1)
                        {
                            housePreesed=true;
                            House.houseNumber1Open=true;
                            new Thread(new Runnable() {
                                public void run() {
                                    int counter=0;
                                    MainPlayer.spacialAttack =false;

                                    MainPlayer.attacking=false;
                                    MainPlayer.stand=true;
                                    while (counter<=600)
                                    {

                                        MainPlayer.walking=false;
                                        counter++;
                                        setLocation(getX()+1,getY()-1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    while(counter>0){
                                        counter--;
                                        MainPlayer.walking=false;
                                        setLocation(getX()-1,getY()+1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    housePreesed=false;
                                }
                            }).start();
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(null,"need to kill al the monster before opening this house");
                        }

                        break;




                    }
                    case 1: {
//                        up left
                        if(!House.houseNumber2Open&&Key.key2)
                        {
                            MainPlayer.spacialAttack =false;
                            MainPlayer.walking=false;
                            MainPlayer.attacking=false;
                            MainPlayer.stand=true;
                            housePreesed=true;
                            House.houseNumber2Open=true;
                            new Thread(new Runnable() {
                                public void run() {
                                    int counter=0;

                                    while (counter<=600)
                                    {
                                        MainPlayer.walking=false;
                                        counter++;
                                        setLocation(getX()+1,getY()+1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    while(counter>0){
                                        MainPlayer.walking=false;
                                        counter--;
                                        setLocation(getX()-1,getY()-1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    housePreesed=false;
                                }
                            }).start();
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(null,"need to kill al the monster before opening this house");
                        }
                        break;
                    }
                    case 2: {
                        if(!House.houseNumber3Open&&Key.key3)
                        {
                            MainPlayer.spacialAttack =false;
                            MainPlayer.walking=false;
                            MainPlayer.attacking=false;
                            MainPlayer.stand=true;
                            housePreesed=true;
                            House.houseNumber3Open=true;
                            new Thread(new Runnable() {
                                public void run() {
                                    int counter=0;

                                    while (counter<=600)
                                    {
                                        MainPlayer.walking=false;

                                        counter++;
                                        setLocation(getX()-1,getY()+1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    while(counter>0){
                                        MainPlayer.walking=false;
                                        counter--;
                                        setLocation(getX()+1,getY()-1);
                                        try {
                                            Thread.sleep(2);
                                        } catch (InterruptedException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                    JOptionPane.showMessageDialog(null,"you won the game !!!!!");

                                    housePreesed=false;
                                }
                            }).start();
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(null,"need to kill al the monster before opening this house");
                        }
                        break;
                    }
                }
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

        checkIfUserIsHoverOnItem(e);


    }

    private void checkIfUserIsHoverOnItem(MouseEvent e) {
        if(e.getComponent().equals(StaticVariables.gamePanel.getLife1000()))
        {
            StaticVariables.gamePanel.getPriceTag().setText("1000");
        }
        if(e.getComponent().equals(StaticVariables.gamePanel.getLife2000()))
        {
            StaticVariables.gamePanel.getPriceTag().setText("2000");
        }
        if(e.getComponent().equals(StaticVariables.gamePanel.getPower1()))
        {
            StaticVariables.gamePanel.getPriceTag().setText("400");
        }
        if(e.getComponent().equals(StaticVariables.gamePanel.getPower2()))
        {
            StaticVariables.gamePanel.getPriceTag().setText("600");
        }
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

    public JLabel getHole() {
        return hole;
    }

    public void setHole(JLabel hole) {
        this.hole = hole;
    }

    public Sound getBackGroundWorld() {
        return backGroundWorld;
    }

    public void setBackGroundWorld(Sound backGroundWorld) {
        this.backGroundWorld = backGroundWorld;
    }


}

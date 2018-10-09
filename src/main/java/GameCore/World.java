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
import java.util.Random;

public class World extends JLabel implements MouseListener {




    private JLabel hole;
    private  boolean housePreesed=false,ghostAreBeingAdd=false,key1IsPreesed=false,key2IsPreesed=false,key3IsPreesed=false;
    public static boolean userInProgressToOpenHouse=false,keyCanBeAdded=false;

    private  ArrayList<Ghost>ghostArrayList=new ArrayList<Ghost>();
    private ArrayList<GameObject> backGroundObjects =new ArrayList<GameObject>();
    private  ArrayList<House>houseArrayList=new ArrayList<House>();

    private Random random;
    private Ghost firstGhost;
    private Sound backGroundWorld;

    private int index =0,indexOfGhost=0;

    public World(){




            setBounds(-500,-750,5000,5000);
            setBackground(Color.GRAY);
            backGroundWorld=new Sound();
            backGroundWorld.playSound(Sound.path.get(4));

            addMouseListener(this);

            addBackGroundObjects();
            setVisible(true);

        checkIfUserIsLevelUp();








    }

    private void checkIfUserIsLevelUp() {
        new Thread(new Runnable() {
            public void run() {
                while (true)
                {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }




                    if(keyCanBeAdded&&Ghost.numberOfDeadGhost==ghostArrayList.size()&&ghostArrayList.size()>0)
                    {
                        addTheKeyToWorld();
                    }
                    if(StaticVariables.level==4)
                    {
                        JOptionPane.showMessageDialog(null,"You clean the world from evil !!!");
                        break;
                    }


                }
            }
        }).start();
    }

    private  void addTheKeyToWorld() {

        StaticVariables.miniMap.setKey(new Key());
        StaticVariables.miniMap.getKey().setVisible(true);
        StaticVariables.gamePanel.add(StaticVariables.miniMap.getKey());

        StaticVariables.miniMap.getKey().moveTheKey();
        Ghost.numberOfDeadGhost = 0;
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
                    getGhostArrayList().remove(getFirstGhost());
                    setFirstGhost(null);

                            Ghost.notTheFirstGhost=true;
                    Ghost.numberOfDeadGhost = 0;
                            addGhost();



                }
                else
                {


                    try
                    {
                        for (int i = 0; i <StaticVariables.world.getHouseArrayList().size() ; i++) {
                            StaticVariables.world.getHouseArrayList().get(i).setVisible(true);
                            StaticVariables.world.getHouseArrayList().get(i).getHouseEntrance().setVisible(true);
                        }

                    }catch (Exception house )
                    {
                        house.printStackTrace();
                    }

                    getGhostArrayList().clear();
                    switch (StaticVariables.level)
                    {
                        case 1:{

                            for (int i = 0; i <7+Ghost.difficulty*3; i++) {

                                Ghost ghost=new Ghost(1);

                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                getGhostArrayList().add(ghost);
                                add(ghost, indexOfGhost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }
                            keyCanBeAdded=true;


                            break;
                        }
                        case 2:{


                            Ghost.addGhostImage();
                            for (int i = 0; i <5 +Ghost.difficulty*3; i++) {
                                Ghost ghost=new Ghost(2);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                getGhostArrayList().add(ghost);
                                add(ghost, indexOfGhost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }
                            keyCanBeAdded=true;

                            break;
                        }
                        case 3:{

                            Ghost.addGhostImage();
                            for (int i = 0; i <3+Ghost.difficulty*3; i++) {

                                Ghost ghost=new Ghost(3);
                                ghost.checkIfGhostIntersectHouse();
                                ghost.setName(""+i);
                                getGhostArrayList().add(ghost);
                                add(ghost, indexOfGhost);
                                StaticVariables.miniMap.addTheGhostLocationToMap(i,ghost.getLocation());
                            }
                            keyCanBeAdded=true;

                            break;
                        }
                    }

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
                playTheErrorSound();
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
                playTheErrorSound();
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
                playTheErrorSound();
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
                playTheErrorSound();
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
            if(!GamePanel.muteActive)
            {
                StaticVariables.gamePanel.getBuy().startSound();
                StaticVariables.gamePanel.getBuy().getClip().loop(0);
            }

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



        for (House house :houseArrayList) {
            if(e.getComponent().equals(house)&&key1IsPreesed||key2IsPreesed||key3IsPreesed)
            {

                if(key1IsPreesed)
                    StaticVariables.gamePanel.getBag().getKey1().removeMouseListener(this);
                if(key2IsPreesed)
                    StaticVariables.gamePanel.getBag().getKey2().removeMouseListener(this);
                if(key3IsPreesed)
                    StaticVariables.gamePanel.getBag().getKey3().removeMouseListener(this);
                key1IsPreesed=false;
                key2IsPreesed=false;
                key3IsPreesed=false;
                Key.removeTheKeyLiseners();
                StaticVariables.level++;
                StaticVariables.gamePanel.getLabel().setText(""+StaticVariables.level);
                addGhost();
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

    private void checkIfUserPreesTheHouse(final MouseEvent e) {



            if(e.getComponent().getBounds().intersects(StaticVariables.mainPlayer.getBounds())&&!housePreesed)
            {

                switch (Integer.parseInt(e.getComponent().getName())) {
                    case 0: {
                        //                downleft

                        if(!House.houseNumber1Open&&Key.key1)
                        {
                            housePreesed=true;
                            House.houseNumber1Open=true;
                            startTheMoveThread(0);
                        }
                        else
                        {
                           playTheErrorSound();
                        }

                        break;




                    }
                    case 1: {
//                        up left
                        if(!House.houseNumber2Open&&Key.key2)
                        {

                            housePreesed=true;
                            House.houseNumber2Open=true;
                            startTheMoveThread(1);

                        }
                        else
                        {
                            playTheErrorSound();
                        }
                        break;
                    }
                    case 2: {
                        if(!House.houseNumber3Open&&Key.key3)
                        {

                            housePreesed=true;
                            House.houseNumber3Open=true;
                            startTheMoveThread(2);


                        }
                        else
                        {
                            playTheErrorSound();
                        }
                        break;
                    }
                }
            }
















    }

    private void playTheErrorSound() {
        StaticVariables.gamePanel.getNotbuy().startSound();
        StaticVariables.gamePanel.getNotbuy().getClip().loop(0);
    }

    private void startTheMoveThread(  final int houseNumber) {
        new Thread(new Runnable() {
            public void run() {

                int x=2;
                int y=2;
                int counter=0;
                MainPlayer.spacialAttack =false;
                MainPlayer.attacking=false;
                MainPlayer.stand=true;


                while (counter<=200)
                {


                    MainPlayer.walking=false;
                    counter++;
                    changeTheXAndY(x,y,true);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

                while(counter>0){
                    counter--;
                    MainPlayer.walking=false;
                    changeTheXAndY(x,y,false);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                housePreesed=false;
            }

            private void changeTheXAndY(int x, int y,boolean start) {
                switch (houseNumber)
                {
                    case 0:
                    {
                        if(start)
                        setLocation(getX()+x,getY()-y);
                        else
                            setLocation(getX()-x,getY()+y);

                        break;
                    }
                    case 1:
                    {
                        if(start)
                            setLocation(getX()+x,getY()+y);
                        else
                            setLocation(getX()-x,getY()-y);
                        break;
                    }
                    case 2:
                    {
                        if(start)
                            setLocation(getX()-x,getY()+y);
                        else
                            setLocation(getX()+x,getY()-y);
                        break;
                    }
                }
            }
        }).start();

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

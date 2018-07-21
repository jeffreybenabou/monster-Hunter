package Objects;

import BackgroundObject.House;
import GameCore.*;
import ImageHandel.ImageLoader;



import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Vector;

public class Ghost extends GameObject {

    private int ghostType,damgeToMainPlayer,damgeFromMainPlayer,life;

    private static String pathOfFile;

    public static ArrayList<ImageIcon>
            moveLeft,
            moveRight,
            moveUp,
            moveDown,
            attackLeft,
            attackRight,
            attackUp,
            attackDown,

    dead;
    public static int numberOfDeadGhost=0;


    private Life lifeBar;
    private boolean stopMoving=false;

    private boolean

            dirLeft,
            dirUp,
            dirRight,
            dirDown,
            isAttackLeft,
            isAttackRight,
            isAttackUp,
            isAttackDown,
            isAttackLeftDown,
            isAttackLeftUp,
            isAttackRightUp,
            isAttackRightDown;

    private int speed;
    public static boolean notTheFirstGhost=false;
    private int index=0;
    private int width, height;


    public Ghost(int ghostType){
        this.ghostType=ghostType;
        setHorizontalAlignment(JLabel.CENTER);
        checkTheGhostType();
        setTheDir();
        setTheActionToGhost();
        addMouseListener(StaticVariables.world);


    }

    public void checkIfGhostIntersectHouse(){
        Random random=new Random();
        for (House house:World.houseArrayList) {

                switch (StaticVariables.level) {
                    case 1: {
                        width = 250;
                        height = 300;
                        break;
                    }
                    case 2: {
                        width = 300;
                        height = 350;
                        break;
                    }
                    case 3: {
                        width = 400;
                        height = 450;
                        break;
                    }
                }
                setBounds(new Rectangle(random.nextInt(StaticVariables.world.getWidth()), random.nextInt(StaticVariables.world.getHeight()), width, height));
                if (house.getBounds().intersects(getBounds())) {
                   setBounds(3000, 3000, width, height);
                }


        }
    }
    public void setTheDir(){
        Random random=new Random();
        dirLeft=random.nextBoolean();
        dirUp=random.nextBoolean();
        dirRight = !dirLeft;

        dirDown = !dirUp;
    }

    public static void addTheVector() {
        moveLeft =new ArrayList<ImageIcon>();
        moveRight =new ArrayList<ImageIcon>();
        moveUp =new ArrayList<ImageIcon>();
        moveDown =new ArrayList<ImageIcon>();

        attackDown=new ArrayList<ImageIcon>();
        attackLeft=new ArrayList<ImageIcon>();
        attackRight=new ArrayList<ImageIcon>();
        attackUp=new ArrayList<ImageIcon>();

        dead=new ArrayList<ImageIcon>();


    }

    public void removeTheGhostWhenDead(){
            StaticVariables.world.remove(this);

            setVisible(false);
    }



    public void setTheActionToGhost(){
        new Thread(new Runnable() {
            public void run() {
                while(lifeBar.isAlive())
                {
                    stopMoving = getBounds().intersects(StaticVariables.mainPlayer.getBounds());

                    if(notTheFirstGhost)
                    moveTheGhostAroundTheWorld();
                    else
                        changeTheGhostIcon();
                    decreaseLife(StaticVariables.mainPlayer.getDamgeToGhost());



                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setTheGhostDeadAnimation();

                removeTheGhostWhenDead();
            }
        }).start();
    }

    private void decreaceMainPlayerLife() {
        MainPlayer.life.getjProgressBar().setValue(MainPlayer.life.getjProgressBar().getValue()-damgeToMainPlayer);
        MainPlayer.life.getjProgressBar().setString(""+(MainPlayer.life.getjProgressBar().getValue()-damgeToMainPlayer));

    }

    public void decreaseLife(int damge) {
        if(getBounds().intersects(StaticVariables.mainPlayer.getBounds())&&(StaticVariables.mainPlayer.isAttacking()||MainPlayer.spacielAttack))
        {


            getLifeBar().getjProgressBar().setValue(getLifeBar().getjProgressBar().getValue()-damge);
            getLifeBar().getjProgressBar().setString(""+getLifeBar().getjProgressBar().getValue());
        }
    }

    private void setTheGhostDeadAnimation() {
        index=0;
        while (index<dead.size())
        {
            setIcon(dead.get(index));
            index++;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moveTheGhostAroundTheWorld() {

        /*
         *
         * this method check all the direction of the cloud to check the dir to mov to .
         *
         * also check that the player is not getting out of the world bound .
         *
         * */
        try
        {
            for (House house:World.getHouseArrayList()) {

                if(getBounds().intersects(house.getBounds()))
                {
                    switch (Integer.parseInt(house.getName()))
                    {
                        case 1:
                        {
                            setLocation(getX()+10,getY());
                            dirUp=true;
                            dirDown=false;
                            dirLeft=false;
                            dirRight=true;
                            break;
                        }
                        case 2:
                        {
                            setLocation(getX()+10,getY());
                            dirUp=false;
                            dirDown=true;
                            dirLeft=false;
                            dirRight=true;
                            break;
                        }
                        case 3:
                        {
                            setLocation(getX()-10,getY());
                            dirUp=false;
                            dirDown=true;
                            dirLeft=true;
                            dirRight=false;
                            break;
                        }

                    }

                }

            }
        }catch (ConcurrentModificationException e)
        {

            e.printStackTrace();
        }


        if (dirDown)
            setLocation(getX(), getY() + speed);
        if (dirUp)
            setLocation(getX(), getY() - speed);
        if (dirLeft)
            setLocation(getX() - speed, getY());
        if (dirRight)
            setLocation(getX() + speed, getY());


        if(getX()> StaticVariables.world.getWidth()-getWidth())
        {
            dirRight=false;
            dirLeft=true;
        }
        if(getX()<0)
        {
            dirRight=true;
            dirLeft=false;
        }
        if(getY()> StaticVariables.world.getHeight()-getHeight())
        {
            dirDown=false;
            dirUp=true;
        }
        if(getY()<0)
        {
            dirDown=true;
            dirUp=false;
        }






        changeTheGhostIcon();




    }

    public static   void addGhostImage(){
        addTheVector();
        final Dimension dimension=new Dimension(500,320);

        switch (StaticVariables.level)
        {
            case 1:
            {
                pathOfFile="firstGhost";

                break;
            }
            case 2:
            {
                pathOfFile="secondeGhost";

                break;
            }
            case 3:
            {
                pathOfFile="thirdGhost";

                break;
            }
        }



                File   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_down/");
                ImageLoader.addImageOfObject(DIR_1,attackDown, null,dimension);


                  DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_left/");
                ImageLoader.addImageOfObject(DIR_1,attackLeft, null,dimension);




                   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_right/");
                ImageLoader.addImageOfObject(DIR_1,attackRight, null,dimension);



                    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_up/");
                ImageLoader.addImageOfObject(DIR_1,attackUp, null,dimension);





                    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_up/");
                ImageLoader.addImageOfObject(DIR_1,moveUp, null,dimension);


                      DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_right/");
                ImageLoader.addImageOfObject(DIR_1,moveRight, null,dimension);



                    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_left/");
                ImageLoader.addImageOfObject(DIR_1,moveLeft, null,dimension);



                    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_down/");
                ImageLoader.addImageOfObject(DIR_1,moveDown, null,dimension);





            DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/dead/");
        ImageLoader.addImageOfObject(DIR_1,dead, null,dimension);





    }


    private void changeTheGhostIcon() {
        try {
            if (index < moveLeft.size()) {
                if (stopMoving) {
                    dirLeft = false;
                    dirDown = false;
                    dirUp = false;
                    dirRight = false;
                     if (MainPlayer.isWalking())
                         setIcon(moveRight.get(index));
                    else if (StaticVariables.mainPlayer.isLeftFromTheGhost())
                        setIcon((attackLeft.get(index)));
                    else if (StaticVariables.mainPlayer.isDownFromTheGhost())
                         setIcon((attackDown.get(index)));
                    else if (StaticVariables.mainPlayer.isRightFromTheGhost())
                        setIcon((attackRight.get(index)));
                    else if (StaticVariables.mainPlayer.isUpFromTheGhost())
                         setIcon((attackUp.get(index)));

                    decreaceMainPlayerLife();






                }
                else
                {

                    if(!dirUp&&!dirDown&&!dirLeft&&!dirRight)
                        setTheDir();

                    if (dirLeft)
                        setIcon((moveLeft.get(index)));
                    if (dirRight)
                        setIcon((moveRight.get(index)));
                    if (dirUp)
                        setIcon((moveUp.get(index)));
                    if (dirDown)
                        setIcon((moveDown.get(index)));
                }



            } else {
                index = 0;
            }



            index++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }catch (IndexOutOfBoundsException e)
        {

        }


    }


    private void checkTheGhostType() {
        Random random=new Random();
       switch (ghostType)
       {
           case 1:
           {
               setSize(250,300);
               damgeToMainPlayer=2*random.nextInt(2)+1;
               life=5000;
               speed=1;
               break;
           }
           case 2:
           {


               setSize(300,350);
               damgeToMainPlayer=3*random.nextInt(2)+1;
               life=10000;
               speed=2;
               break;
           }
           case 3:
           {

               setSize(400,450);
               damgeToMainPlayer=4*random.nextInt(2)+1;
               life=15000;
               speed=3;

               break;
           }
       }
        lifeBar=new Life(life,this);
       add(lifeBar.getjProgressBar());




    }



    public int getGhostType() {
        return ghostType;
    }

    public void setGhostType(int ghostType) {
        this.ghostType = ghostType;
    }

    public int getDamgeToMainPlayer() {
        return damgeToMainPlayer;
    }

    public void setDamgeToMainPlayer(int damgeToMainPlayer) {
        this.damgeToMainPlayer = damgeToMainPlayer;
    }

    public boolean isStopMoving() {
        return stopMoving;
    }

    public void setStopMoving(boolean stopMoving) {
        this.stopMoving = stopMoving;
    }

    public static String getPathOfFile() {
        return pathOfFile;
    }

    public static void setPathOfFile(String pathOfFile) {
        Ghost.pathOfFile = pathOfFile;
    }

    public static ArrayList<ImageIcon> getMoveLeft() {
        return moveLeft;
    }

    public static void setMoveLeft(ArrayList<ImageIcon> moveLeft) {
        Ghost.moveLeft = moveLeft;
    }

    public static ArrayList<ImageIcon> getMoveRight() {
        return moveRight;
    }

    public static void setMoveRight(ArrayList<ImageIcon> moveRight) {
        Ghost.moveRight = moveRight;
    }

    public static ArrayList<ImageIcon> getMoveUp() {
        return moveUp;
    }

    public static void setMoveUp(ArrayList<ImageIcon> moveUp) {
        Ghost.moveUp = moveUp;
    }

    public static ArrayList<ImageIcon> getMoveDown() {
        return moveDown;
    }

    public static void setMoveDown(ArrayList<ImageIcon> moveDown) {
        Ghost.moveDown = moveDown;
    }

    public static ArrayList<ImageIcon> getAttackLeft() {
        return attackLeft;
    }

    public static void setAttackLeft(ArrayList<ImageIcon> attackLeft) {
        Ghost.attackLeft = attackLeft;
    }

    public static ArrayList<ImageIcon> getAttackRight() {
        return attackRight;
    }

    public static void setAttackRight(ArrayList<ImageIcon> attackRight) {
        Ghost.attackRight = attackRight;
    }

    public static ArrayList<ImageIcon> getAttackUp() {
        return attackUp;
    }

    public static void setAttackUp(ArrayList<ImageIcon> attackUp) {
        Ghost.attackUp = attackUp;
    }

    public static ArrayList<ImageIcon> getAttackDown() {
        return attackDown;
    }

    public static void setAttackDown(ArrayList<ImageIcon> attackDown) {
        Ghost.attackDown = attackDown;
    }

    public static ArrayList<ImageIcon> getDead() {
        return dead;
    }

    public static void setDead(ArrayList<ImageIcon> dead) {
        Ghost.dead = dead;
    }

    public static int getNumberOfDeadGhost() {
        return numberOfDeadGhost;
    }

    public static void setNumberOfDeadGhost(int numberOfDeadGhost) {
        Ghost.numberOfDeadGhost = numberOfDeadGhost;
    }

    public boolean isAttackLeft() {
        return isAttackLeft;
    }

    public void setAttackLeft(boolean attackLeft) {
        isAttackLeft = attackLeft;
    }

    public boolean isAttackRight() {
        return isAttackRight;
    }

    public void setAttackRight(boolean attackRight) {
        isAttackRight = attackRight;
    }

    public boolean isAttackUp() {
        return isAttackUp;
    }

    public void setAttackUp(boolean attackUp) {
        isAttackUp = attackUp;
    }

    public boolean isAttackDown() {
        return isAttackDown;
    }

    public void setAttackDown(boolean attackDown) {
        isAttackDown = attackDown;
    }

    public boolean isAttackLeftDown() {
        return isAttackLeftDown;
    }

    public void setAttackLeftDown(boolean attackLeftDown) {
        isAttackLeftDown = attackLeftDown;
    }

    public boolean isAttackLeftUp() {
        return isAttackLeftUp;
    }

    public void setAttackLeftUp(boolean attackLeftUp) {
        isAttackLeftUp = attackLeftUp;
    }

    public boolean isAttackRightUp() {
        return isAttackRightUp;
    }

    public void setAttackRightUp(boolean attackRightUp) {
        isAttackRightUp = attackRightUp;
    }

    public boolean isAttackRightDown() {
        return isAttackRightDown;
    }

    public void setAttackRightDown(boolean attackRightDown) {
        isAttackRightDown = attackRightDown;
    }

    public static boolean isNotTheFirstGhost() {
        return notTheFirstGhost;
    }

    public static void setNotTheFirstGhost(boolean notTheFirstGhost) {
        Ghost.notTheFirstGhost = notTheFirstGhost;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isDirLeft() {
        return dirLeft;
    }

    public void setDirLeft(boolean dirLeft) {
        this.dirLeft = dirLeft;
    }

    public boolean isDirUp() {
        return dirUp;
    }

    public void setDirUp(boolean dirUp) {
        this.dirUp = dirUp;
    }

    public boolean isDirRight() {
        return dirRight;
    }

    public void setDirRight(boolean dirRight) {
        this.dirRight = dirRight;
    }

    public boolean isDirDown() {
        return dirDown;
    }

    public void setDirDown(boolean dirDown) {
        this.dirDown = dirDown;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamgeFromMainPlayer() {
        return damgeFromMainPlayer;
    }

    public void setDamgeFromMainPlayer(int damgeFromMainPlayer) {
        this.damgeFromMainPlayer = damgeFromMainPlayer;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Life getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(Life lifeBar) {
        this.lifeBar = lifeBar;
    }
}

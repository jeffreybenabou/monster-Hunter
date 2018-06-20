package Objects;

import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.Vector;

public class Ghost extends GameObject {

    private int ghostType,damgeToMainPlayer,damgeFromMainPlayer,speedOfAttack,life;

    public static Vector<Image>
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


    public Ghost(int ghostType){
        this.ghostType=ghostType;
        checkTheGhostType();
        setTheDir();
        setTheActionToGhost();

    }

    public void setTheDir(){
        Random random=new Random();
        dirLeft=random.nextBoolean();
        dirUp=random.nextBoolean();
        dirRight = !dirLeft;

        dirDown = !dirUp;
    }

    public static void addTheVector() {
        moveLeft =new Vector<Image>();
        moveRight =new Vector<Image>();
        moveUp =new Vector<Image>();
        moveDown =new Vector<Image>();

        attackDown=new Vector<Image>();
        attackLeft=new Vector<Image>();
        attackRight=new Vector<Image>();
        attackUp=new Vector<Image>();

        dead=new Vector<Image>();


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

                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                index=0;
                while (index<dead.size())
                {
                    setIcon(new ImageIcon(dead.get(index)));
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

                removeTheGhostWhenDead();
            }
        }).start();
    }

    private void moveTheGhostAroundTheWorld() {

        /*
         *
         * this method check all the direction of the cloud to check the dir to mov to .
         *
         * also check that the player is not getting out of the world bound .
         *
         * */


        if (dirDown)
            setLocation(getX(), getY() + speed);
        if (dirUp)
            setLocation(getX(), getY() - speed);
        if (dirLeft)
            setLocation(getX() - speed, getY());
        if (dirRight)
            setLocation(getX() + speed, getY());


        if(getX()> StaticVariables.world.getWidth())
        {
            dirRight=false;
            dirLeft=true;
        }
        if(getX()<0)
        {
            dirRight=true;
            dirLeft=false;
        }
        if(getY()> StaticVariables.world.getHeight())
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
        final Dimension dimension=new Dimension(300,320);




        new Thread(new Runnable() {
            public void run() {
                File   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackDown/");
                ImageLoader.addImageOfObject(DIR_1, attackDown,dimension);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                File  DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackLeft/");
                ImageLoader.addImageOfObject(DIR_1, attackLeft,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackRight/");
                ImageLoader.addImageOfObject(DIR_1, attackRight,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackUp/");
                ImageLoader.addImageOfObject(DIR_1, attackUp,dimension);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveUp/");
                ImageLoader.addImageOfObject(DIR_1, moveUp,dimension);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                File      DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveRight/");
                ImageLoader.addImageOfObject(DIR_1, moveRight,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveLeft/");
                ImageLoader.addImageOfObject(DIR_1, moveLeft,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveDown/");
                ImageLoader.addImageOfObject(DIR_1, moveDown,dimension);
            }
        }).start();




        File     DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/dead/");
        ImageLoader.addImageOfObject(DIR_1, dead,dimension);





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
                         setIcon(new ImageIcon(moveRight.get(index)));
                    else if (StaticVariables.mainPlayer.isLeftFromTheGhost())
                        setIcon(new ImageIcon(attackRight.get(index)));
                    else if (StaticVariables.mainPlayer.isDownFromTheGhost())
                         setIcon(new ImageIcon(attackDown.get(index)));
                    else if (StaticVariables.mainPlayer.isRightFromTheGhost())
                        setIcon(new ImageIcon(attackLeft.get(index)));
                    else if (StaticVariables.mainPlayer.isUpFromTheGhost())
                         setIcon(new ImageIcon(attackUp.get(index)));





                }
                else
                {

                    if(!dirUp&&!dirDown&&!dirLeft&&!dirRight)
                        setTheDir();

                    if (dirLeft)
                        setIcon(new ImageIcon(moveLeft.get(index)));
                    if (dirRight)
                        setIcon(new ImageIcon(moveRight.get(index)));
                    if (dirUp)
                        setIcon(new ImageIcon(moveUp.get(index)));
                    if (dirDown)
                        setIcon(new ImageIcon(moveDown.get(index)));
                }



            } else {
                if (getBounds().intersects(StaticVariables.mainPlayer.getBounds())) {
                    MainPlayer.getLife().getjProgressBar().setValue(MainPlayer.getLife().getjProgressBar().getValue() - damgeToMainPlayer);
                    MainPlayer.getLife().getjProgressBar().setString("" + MainPlayer.getLife().getjProgressBar().getValue());
                }

                index = 0;
            }



            index++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }


    }


    private void checkTheGhostType() {
        Random random=new Random();
       switch (ghostType)
       {
           case 1:
           {

               setSize(300,320);
               damgeToMainPlayer=20*random.nextInt(3)+1;
               damgeFromMainPlayer=2200;
               speedOfAttack=2000;
               life=10000;
               speed=1;
               break;
           }
           case 2:
           {

               setSize(getIcon().getIconWidth(),getIcon().getIconHeight());
               damgeToMainPlayer=1000*random.nextInt(3)*StaticVariables.level;
               damgeFromMainPlayer=2300;
               speedOfAttack=1800;
               life=13000;
               speed=2;
               break;
           }
           case 3:
           {

               setSize(getIcon().getIconWidth(),getIcon().getIconHeight());
               damgeToMainPlayer=1000*random.nextInt(3)*StaticVariables.level;
               damgeFromMainPlayer=2400;
               speedOfAttack=1500;
               life=15000;
               speed=3;

               break;
           }
       }
        lifeBar=new Life(life,this);
       add(lifeBar.getjProgressBar());
        addMouseListener(StaticVariables.world);



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

    public int getSpeedOfAttack() {
        return speedOfAttack;
    }

    public void setSpeedOfAttack(int speedOfAttack) {
        this.speedOfAttack = speedOfAttack;
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

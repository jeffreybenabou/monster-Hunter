package Objects;

import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;
import sun.awt.windows.ThemeReader;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Ghost extends GameObject {

    private int ghostType,damgeToMainPlayer,damgeFromMainPlayer,speedOfAttack,life;

    public static Vector<Icon>
            left,
            right,
            up,
            down,
            attackLeft,
            attackRight,
            attackUp,
            attackDown,
            attackLeftUp,
            attackLeftDown,
            attackRightDown,
            attackRightUp;


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



        Random random=new Random();
        dirLeft=random.nextBoolean();
        dirUp=random.nextBoolean();

        dirRight = !dirLeft;

        dirDown = !dirUp;
        setTheActionToGhost();

    }

    public static void addTheVector() {
        left=new Vector<Icon>();
        right=new Vector<Icon>();
        up=new Vector<Icon>();
        down=new Vector<Icon>();
        attackDown=new Vector<Icon>();
        attackLeft=new Vector<Icon>();
        attackLeftDown=new Vector<Icon>();
        attackLeftUp=new Vector<Icon>();
        attackRight=new Vector<Icon>();
        attackRightDown=new Vector<Icon>();
        attackRightUp=new Vector<Icon>();
        attackUp=new Vector<Icon>();


    }

    public void removeTheGhostWhenDead(){
// TODO: 29/05/2018 add an explotion or something to make the ghost dissaper

            StaticVariables.world.remove(this);
            StaticVariables.world.getGhostArrayList().remove(this);

            setVisible(false);


    }


    public void setTheActionToGhost(){
        new Thread(new Runnable() {
            public void run() {
                while(lifeBar.isAlive())
                {
                    if(notTheFirstGhost)
                    moveTheGhostAroundTheWorld();


                    if(getBounds().intersects(StaticVariables.mainPlayer.getBounds()))
                        stopMoving=true;
                    else
                        stopMoving=false;
                    if(StaticVariables.mainClass!=null)
                        StaticVariables.mainClass.repaint();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        Dimension dimension=new Dimension(300,320);
        File DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackDown/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackDown,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackLeft/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackLeft,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackLeftDown/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackLeftDown,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackLeftUp/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackLeftUp,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackRight/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackRight,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackRightDown/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackRightDown,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackRightUp/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackRightUp,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/attackUp/");
        ImageLoader.addImageOfMainPlayer(DIR_1, attackUp,dimension);

        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveUp/");
        ImageLoader.addImageOfMainPlayer(DIR_1, up,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveRight/");
        ImageLoader.addImageOfMainPlayer(DIR_1, right,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveLeft/");
        ImageLoader.addImageOfMainPlayer(DIR_1, left,dimension);
        DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/moveDown/");
        ImageLoader.addImageOfMainPlayer(DIR_1, down,dimension);




    }

    private void changeTheGhostIcon() {
        try {
            if (index < left.size()) {
                if (stopMoving) {
                    dirLeft = false; dirDown = false; dirUp = false; dirRight = false;
                    if (StaticVariables.mainPlayer.isIs_stand_down())
                        setIcon(attackUp.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_left())
                        setIcon(attackRight.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_right())
                        setIcon(attackLeft.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_up())
                        setIcon(attackDown.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_left_down())
                        setIcon(attackRightUp.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_right_up())
                        setIcon(attackLeftDown.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_left_up())
                        setIcon(attackRightDown.get(index));
                    if (StaticVariables.mainPlayer.isIs_stand_right_dowb())
                        setIcon(attackLeftUp.get(index));
                }
                if (dirLeft)
                    setIcon(left.get(index));
                if (dirRight)
                    setIcon(right.get(index));
                if (dirUp)
                    setIcon(up.get(index));
                if (dirDown)
                    setIcon(down.get(index));


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

               setSize(attackDown.get(0).getIconWidth(),attackDown.get(0).getIconHeight());
               damgeToMainPlayer=1000*random.nextInt(3)+1;
               damgeFromMainPlayer=2200;
               speedOfAttack=2000;
               life=10000;
               speed=1;
               break;
           }
           case 2:
           {
               setIcon(new ImageIcon(StaticVariables.ghost2));
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
               setIcon(new ImageIcon(StaticVariables.ghost3));
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

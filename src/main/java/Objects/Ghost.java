package Objects;

import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ghost extends GameObject {

    private int ghostType,damgeToMainPlayer,damgeFromMainPlayer,speedOfAttack,life;


    private Life lifeBar;
    private boolean stopMoving=false;

    private boolean

            dirLeft,
            dirUp,
            dirRight,
            dirDown;

    private int speed;
    public static boolean notTheFirstGhost=false;

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
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(getBounds().intersects(StaticVariables.mainPlayer.getBounds()))
                    {
                        stopMoving=true;
                        MainPlayer.getLife().getjProgressBar().setValue(MainPlayer.getLife().getjProgressBar().getValue()-damgeToMainPlayer);
                        MainPlayer.getLife().getjProgressBar().setString(""+MainPlayer.getLife().getjProgressBar().getValue());

                        try {
                            Thread.sleep(speedOfAttack);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                        stopMoving=false;
                    if(StaticVariables.mainClass!=null)
                        StaticVariables.mainClass.repaint();
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




    }



    private void checkTheGhostType() {
        Random random=new Random();
       switch (ghostType)
       {
           case 1:
           {
               setIcon(new ImageIcon(StaticVariables.ghost1));
               setSize(getIcon().getIconWidth(),getIcon().getIconHeight());
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

package Objects;

import GameCore.*;
import ImageHandel.ImageLoader;



import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.Vector;

public class Ghost extends GameObject {

    private int ghostType,damgeToMainPlayer,damgeFromMainPlayer,life;

    private static String pathOfFile;

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
        setHorizontalAlignment(JLabel.CENTER);
        checkTheGhostType();
        setTheDir();
        setTheActionToGhost();
        addMouseListener(StaticVariables.world);


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
            World.ghostArrayList.remove(this);
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
                    decreaseLife();



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

    private void decreaseLife() {
        if(getBounds().intersects(StaticVariables.mainPlayer.getBounds())&&StaticVariables.mainPlayer.isAttacking())
        {


            getLifeBar().getjProgressBar().setValue(getLifeBar().getjProgressBar().getValue()-StaticVariables.mainPlayer.getDamgeToGhost());
            getLifeBar().getjProgressBar().setString(""+getLifeBar().getjProgressBar().getValue());
        }
    }

    private void setTheGhostDeadAnimation() {
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


        new Thread(new Runnable() {
            public void run() {
                File   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_down/");
                ImageLoader.addImageOfObject(DIR_1, attackDown,dimension);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                File  DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_left/");
                ImageLoader.addImageOfObject(DIR_1, attackLeft,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File   DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_right/");
                ImageLoader.addImageOfObject(DIR_1, attackRight,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/attack/attack_up/");
                ImageLoader.addImageOfObject(DIR_1, attackUp,dimension);

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {

                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_up/");
                ImageLoader.addImageOfObject(DIR_1, moveUp,dimension);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                File      DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_right/");
                ImageLoader.addImageOfObject(DIR_1, moveRight,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_left/");
                ImageLoader.addImageOfObject(DIR_1, moveLeft,dimension);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/walk/walk_down/");
                ImageLoader.addImageOfObject(DIR_1, moveDown,dimension);
            }
        }).start();




        File    DIR_1 = new File("src/main/java/ImageHandel/Photos/ghost/"+pathOfFile+"/dead/");
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
                        setIcon(new ImageIcon(attackLeft.get(index)));
                    else if (StaticVariables.mainPlayer.isDownFromTheGhost())
                         setIcon(new ImageIcon(attackDown.get(index)));
                    else if (StaticVariables.mainPlayer.isRightFromTheGhost())
                        setIcon(new ImageIcon(attackRight.get(index)));
                    else if (StaticVariables.mainPlayer.isUpFromTheGhost())
                         setIcon(new ImageIcon(attackUp.get(index)));

                    decreaceMainPlayerLife();






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
               setSize(250,300);
               damgeToMainPlayer=2*random.nextInt(2)+1;
               damgeFromMainPlayer=2200;
               life=10000;
               speed=1;
               break;
           }
           case 2:
           {


               setSize(250,300);
               damgeToMainPlayer=1000*random.nextInt(3)*StaticVariables.level;
               damgeFromMainPlayer=2300;
               life=13000;
               speed=2;
               break;
           }
           case 3:
           {

               setSize(getIcon().getIconWidth(),getIcon().getIconHeight());
               damgeToMainPlayer=1000*random.nextInt(3)*StaticVariables.level;
               damgeFromMainPlayer=2400;
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

    public static Vector<Image> getMoveLeft() {
        return moveLeft;
    }

    public static void setMoveLeft(Vector<Image> moveLeft) {
        Ghost.moveLeft = moveLeft;
    }

    public static Vector<Image> getMoveRight() {
        return moveRight;
    }

    public static void setMoveRight(Vector<Image> moveRight) {
        Ghost.moveRight = moveRight;
    }

    public static Vector<Image> getMoveUp() {
        return moveUp;
    }

    public static void setMoveUp(Vector<Image> moveUp) {
        Ghost.moveUp = moveUp;
    }

    public static Vector<Image> getMoveDown() {
        return moveDown;
    }

    public static void setMoveDown(Vector<Image> moveDown) {
        Ghost.moveDown = moveDown;
    }

    public static Vector<Image> getAttackLeft() {
        return attackLeft;
    }

    public static void setAttackLeft(Vector<Image> attackLeft) {
        Ghost.attackLeft = attackLeft;
    }

    public static Vector<Image> getAttackRight() {
        return attackRight;
    }

    public static void setAttackRight(Vector<Image> attackRight) {
        Ghost.attackRight = attackRight;
    }

    public static Vector<Image> getAttackUp() {
        return attackUp;
    }

    public static void setAttackUp(Vector<Image> attackUp) {
        Ghost.attackUp = attackUp;
    }

    public static Vector<Image> getAttackDown() {
        return attackDown;
    }

    public static void setAttackDown(Vector<Image> attackDown) {
        Ghost.attackDown = attackDown;
    }

    public static Vector<Image> getDead() {
        return dead;
    }

    public static void setDead(Vector<Image> dead) {
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

package Objects;

import BackgroundObject.FootStep;
import BackgroundObject.GroundCrack;
import GameCore.*;
import ImageHandel.ImageLoader;
import sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MainPlayer extends GameObject {

    public static int sumOfLife = 20000;
    public static Life life;
    public static boolean spacielAttack;

    private static ImageLoader imageLoader;

    public static Sound walkingSound, attackingSound, earthQuaqe;
    public static int imageFrameRate = 0, addLifeToMainPlayer = 0;
    public static boolean intersect = false;
    private int xSpriteSheet = 350, ySprtieSheet = 320, index = 0, imageSpeed = 3, damgeToGhost;
    public static String nameOfPlayer, type;
    public static boolean attacking;
    public static boolean walking = false;
    public static boolean stand = true;


    public static Point point;


    private boolean leftFromTheGhost, rightFromTheGhost, upFromTheGhost, downFromTheGhost,

    is_stand_left_up, is_stand_left_down, is_stand_right_dowb, is_stand_right_up;
    private static String DIR_1;

    public static ArrayList<ImageIcon>
            up, down, left, right,
            standDown,
            attackLeft, attackRight, attackDown, attackUp, die, spacielAttackA;
    private double angle = 0;
    private double distanceFromPoint;
    private long speedOfMove = 20;
    private FootStep footStep;


    float startTime;


    float stopTime;


    public MainPlayer() {
        try
        {

            imageLoader=new ImageLoader();
            walkingSound = new Sound();
            attackingSound = new Sound();
            earthQuaqe = new Sound();
            getWalkingSound().playSound(Sound.path.get(0));
            getWalkingSound().setVolume(-80);
            getEarthQuaqe().playSound(Sound.path.get(5));
            getEarthQuaqe().setVolume(-80);
            getWalkingSound().setVolume(-80);
            setTheMainPlayerAttackSound();
            setBounds(1000, 1000, xSpriteSheet, ySprtieSheet);
            point = new Point(getX(), getY());
            setTheUserAction();
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(StaticVariables.mainClass,e.getStackTrace());
        }



    }


    public void calculateTheAngle(int x, int y) {
        float xDistance = getX() - x;
        float yDistance = getY() - y;

        angle = (360 + Math.toDegrees(Math.atan2(yDistance, xDistance))) % 360;
        /*
         * calculate the angle of main player position in order to
         * know what is the direction that the main player wants to move
         * */
    }

    public static void addMainPlayerPosition(final String type2) {

        type = type2;
        new Thread(new Runnable() {
            public void run() {


                final Dimension stand = new Dimension(350, 330);
                final Dimension side = new Dimension(400, 350);
                final Dimension upDown = new Dimension(400, 380);

                imageLoader=new ImageLoader();
                DIR_1 = "ImageHandel/Photos/character/" + type2 + "/stand/";
                imageLoader.addImageOfObject(31,DIR_1, standDown, stand);



                        DIR_1 = "ImageHandel/Photos/character/" + type2 + "/walk/right/";
                imageLoader.addImageOfObject(31,DIR_1, right, side);

                        DIR_1 ="ImageHandel/Photos/character/" + type2 + "/walk/left/";
                imageLoader.addImageOfObject(31,DIR_1, left, side);





                        DIR_1 = "ImageHandel/Photos/character/" + type2 + "/walk/down/";
                imageLoader.addImageOfObject(31,DIR_1, down, upDown);

                        DIR_1 = "ImageHandel/Photos/character/" + type2 + "/walk/up/";
                imageLoader.addImageOfObject(31,DIR_1, up, upDown);



                DIR_1 = "ImageHandel/Photos/character/" + type2 + "/attack/spacielAttack/";
                imageLoader.addImageOfObject(72,DIR_1, spacielAttackA, stand);

                DIR_1 = "ImageHandel/Photos/character/" + type2 + "/attack/left/";
                if(type2.equals("male"))
                imageLoader.addImageOfObject(32,DIR_1, attackLeft, side);
                else
                    imageLoader.addImageOfObject(42,DIR_1, attackLeft, side);


                DIR_1 ="ImageHandel/Photos/character/" + type2 + "/attack/up/";
                if(type2.equals("male"))
                    imageLoader.addImageOfObject(32,DIR_1, attackUp, upDown);
                else
                    imageLoader.addImageOfObject(42,DIR_1, attackUp, upDown);



                DIR_1 = "ImageHandel/Photos/character/" + type2 + "/attack/down/";
                if(type2.equals("male"))
                imageLoader.addImageOfObject(32,DIR_1, attackDown, side);
                else
                    imageLoader.addImageOfObject(42,DIR_1, attackDown, side);


                DIR_1 ="ImageHandel/Photos/character/" + type2 + "/attack/right/";
                if(type2.equals("male"))
                imageLoader.addImageOfObject(32,DIR_1, attackRight, upDown);
                else
                    imageLoader.addImageOfObject(42,DIR_1, attackRight, upDown);

//                DIR_1 = "ImageHandel/Photos/character/"+ type2 +"/die/";
//                ImageLoader.addImageOfObject(DIR_1, die, upDown);
// TODO: 22/07/2018 add die to male

            }
        }).start();


    }

    private void setTheMainPlayerAttackSound() {
        if (type.equals("male")) {
            attackingSound.playSound(Sound.path.get(1));
            attackingSound.setVolume(-80);
        } else {
            attackingSound.playSound(Sound.path.get(3));

            attackingSound.setVolume(-80);
        }

    }

    public static void makeNewElements(String type) {

        up = new ArrayList<ImageIcon>();
        down = new ArrayList<ImageIcon>();
        left = new ArrayList<ImageIcon>();
        right = new ArrayList<ImageIcon>();

        standDown = new ArrayList<ImageIcon>();
        spacielAttackA = new ArrayList<ImageIcon>();

        attackLeft = new ArrayList<ImageIcon>();
        attackDown = new ArrayList<ImageIcon>();
        attackRight = new ArrayList<ImageIcon>();
        attackUp = new ArrayList<ImageIcon>();
        die = new ArrayList<ImageIcon>();
        addMainPlayerPosition(type);

    }

    private void setTheUserAction() {

      /*  new Thread(new Runnable() {
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checkSoundStatus();
                }
            }
        }).start();*/

        new Thread(new Runnable() {
            public void run() {

                while (true) {


                    setImageFrameRate(getImageFrameRate() + 1);

                    setPlace();

                    checkIfMainPlayerOutOfBound();
                    checkedMainPlayerInteractWithMonster();
                    setLocationOfMainPlayerWhenFightTheGhost();
                    addLifeToPlayer();


                    try {
                        Thread.sleep(getSpeedOfMove());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        setTheUserAction();
                        break;
                    }
                }


            }
        }).start();
    }

    private void addLifeToPlayer() {

        if (!isAttacking() && isWalking()) {
            setAddLifeToMainPlayer(getAddLifeToMainPlayer() + 1);
            if (getAddLifeToMainPlayer() == 15 && getLife().getjProgressBar().getValue() < getLife().getjProgressBar().getMaximum()) {
                setAddLifeToMainPlayer(0);

                getLife().getjProgressBar().setValue(getLife().getjProgressBar().getValue() + 1);
                getLife().getjProgressBar().setString("" + getLife().getjProgressBar().getValue());
            }
            if (getAddLifeToMainPlayer() > 15)
                setAddLifeToMainPlayer(0);
        }
    }

    private void checkedMainPlayerInteractWithMonster() {

        try {
            for (Ghost g : StaticVariables.world.getGhostArrayList()) {
                try {

                    if (g.isStopMoving() && g.getLifeBar().isAlive())
                        setIntersect(g.isStopMoving());

                    else
                        setIntersect(false);

                    if (isIntersect())
                        break;


                } catch (NullPointerException e) {
                    e.printStackTrace();
                    break;

                }
            }
        } catch (NullPointerException e) {

        }


    }

    private void setLocationOfMainPlayerWhenFightTheGhost() {
        if (isAttacking()) {
            if (getAngle() < 164 && getAngle() >= 52) {
                setDownFromTheGhost(true);
                setLeftFromTheGhost(false);
                setRightFromTheGhost(false);
                setUpFromTheGhost(false);
            }
            if (getAngle() < 183 && getAngle() >= 164) {
                setDownFromTheGhost(false);
                setLeftFromTheGhost(true);
                setRightFromTheGhost(false);
                setUpFromTheGhost(false);

            }
            if (getAngle() < 205 && getAngle() >= 183) {
                setDownFromTheGhost(false);
                setLeftFromTheGhost(true);
                setRightFromTheGhost(false);
                setUpFromTheGhost(false);
            }
            if (getAngle() < 231 && getAngle() >= 205) {
                setDownFromTheGhost(false);
                setLeftFromTheGhost(true);
                setRightFromTheGhost(false);
                setUpFromTheGhost(false);
            }
            if (getAngle() < 281 && getAngle() >= 231) {
//                    down
                setDownFromTheGhost(false);
                setLeftFromTheGhost(false);
                setRightFromTheGhost(false);
                setUpFromTheGhost(true);
            }
            if (getAngle() < 318 && getAngle() >= 281) {
//                    left up
                setDownFromTheGhost(false);
                setLeftFromTheGhost(false);
                setRightFromTheGhost(true);
                setUpFromTheGhost(false);
            }
            if (getAngle() >= 318) {
//                    left
                setDownFromTheGhost(false);
                setLeftFromTheGhost(false);
                setRightFromTheGhost(true);
                setUpFromTheGhost(false);
            }
            if (getAngle() < 52 && getAngle() >= 0) {
//                    left down
                setDownFromTheGhost(false);
                setLeftFromTheGhost(false);
                setRightFromTheGhost(true);
                setUpFromTheGhost(false);
            }


        }

    }



    private void setPlace() {


        if (getImageFrameRate() % 20 == 0)
            footStep = new FootStep();


        checkIfWalking();
        checkIfStand();
        checkIfAttacking();
        checkIfSpacialAttack();

        index++;

    }

    public static void checkSoundStatus() {
        if(isStand())
        {
            getWalkingSound().setVolume(-80);
            getAttackingSound().setVolume(-80);
            getEarthQuaqe().setVolume(-80);
        }
        if(isWalking())
        {
            getWalkingSound().setVolume(6);
            getAttackingSound().setVolume(-80);
            getEarthQuaqe().setVolume(-80);
        }
         if(isAttacking())
        {
            getWalkingSound().setVolume(-80);
            getAttackingSound().setVolume(6);
            getEarthQuaqe().setVolume(-80);
        }
         if(isSpacielAttack())
        {
            getWalkingSound().setVolume(-80);
            getAttackingSound().setVolume(-80);
            getEarthQuaqe().setVolume(6);
        }
    }

    private void checkIfSpacialAttack() {
        try {


            if (isSpacielAttack()) {



                setAttacking(false);
                setStand(false);
                setWalking(false);
                if (getIndex() >= getSpacielAttackA().size() - 1) {
                    setIndex(getSpacielAttackA().size() - 1);


                }

                setIcon(getSpacielAttackA().get(getIndex()));
                if (getIndex() == 40)
                    vibrateTheScreen();

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void vibrateTheScreen() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2500);

                    while (GamePanel.jProgressBar.getValue() > 0) {
                        GamePanel.jProgressBar.setValue(GamePanel.jProgressBar.getValue() - 1);
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            boolean dir = false;

            public void run() {

                GroundCrack groundCrack=new GroundCrack(StaticVariables.mainPlayer.getX(),StaticVariables.mainPlayer.getY()+200);
                StaticVariables.world.add(groundCrack);
                while (isIntersect()) {

                    earthQuaqe.playSound(Sound.path.get(5));

                    for (Ghost g : World.ghostArrayList) {
                        g.decreaseLife(50);
                    }
                    int x;
                    if (dir)
                        x = 30;
                    else
                        x = -30;
                    dir = !dir;
                    StaticVariables.world.setLocation(StaticVariables.world.getX() + x, StaticVariables.world.getY());

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                setSpacielAttack(false);
                setStand(true);

            }
        }).start();
    }

    private void checkIfAttacking() {
        try {


            if (isAttacking()) {


                setSize(getxSpriteSheet(), getySprtieSheet());




                if (isLeftFromTheGhost()) {
                    checkIndexOutOfBound(getAttackRight());
                    setIcon((getAttackRight().get(getIndex())));
                } else if (isRightFromTheGhost()) {
                    checkIndexOutOfBound(getAttackLeft());
                    setIcon((getAttackLeft().get(getIndex())));

                } else if (isDownFromTheGhost()) {
                    checkIndexOutOfBound(getAttackUp());
                    setIcon((getAttackUp().get(getIndex())));

                } else if (isUpFromTheGhost()) {
                    checkIndexOutOfBound(getAttackDown());
                    setIcon((getAttackDown().get(getIndex())));

                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }



        private void checkIfWalking() {

        try
        {


            if (isWalking()) {

                if (getIndex() >= getLeft().size()-1)
                    setIndex(0);
                setAttacking(false);
                setStand(false);


                if (getAngle() < 164 && getAngle() >= 52) {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX(), getY() - getImageSpeed());
                        setIcon((getUp().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(3);
// up

                }
                else if (getAngle() < 183 && getAngle() >= 164) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - getImageSpeed(), StaticVariables.world.getY() + getImageSpeed());
                    setLocation(getX() + getImageSpeed(), getY() - getImageSpeed());
                        setIcon((getRight().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(4);
//                    rightup
                }
                else if (getAngle() < 205 && getAngle() >= 183) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - getImageSpeed(), StaticVariables.world.getY());
                    setLocation(getX() + getImageSpeed(), getY());
                    setIcon(getRight().get(getIndex()));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(5);

//                    right

                }
                else if (getAngle() < 231 && getAngle() >= 205) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - getImageSpeed(), StaticVariables.world.getY() - getImageSpeed());
                    setLocation(getX() + getImageSpeed(), getY() + getImageSpeed());
                        setIcon((getRight().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(6);
//right down
                }
                else if (getAngle() < 281 && getAngle() >= 231) {
//                    down


                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() - getImageSpeed());
                    setLocation(getX(), getY() + getImageSpeed());
                        setIcon((getDown().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(7);
                }
                else if (getAngle() < 318 && getAngle() >= 281) {
//                    left up


                    StaticVariables.world.setLocation(StaticVariables.world.getX() + getImageSpeed(), StaticVariables.world.getY() - getImageSpeed());
                    setLocation(getX() - getImageSpeed(), getY() + getImageSpeed());
                        setIcon((getLeft().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(2);

                }

                else if (getAngle() >= 318) {
//                    left

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + getImageSpeed(), StaticVariables.world.getY());
                    setLocation(getX() - getImageSpeed(), getY());
                        setIcon(getLeft().get(getIndex()));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(1);

                }
                else if (getAngle() < 52 && getAngle() >= 0) {
//                    left down

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + getImageSpeed(), StaticVariables.world.getY() + getImageSpeed());
                    setLocation(getX() - getImageSpeed(), getY() - getImageSpeed());
                        setIcon((getLeft().get(getIndex())));
                    if (getImageFrameRate() % 20 == 0)
                        getFootStep().setTheImage(8);
                }

            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }

        private void checkIndexOutOfBound (ArrayList < ImageIcon > arralist) {


            if (getIndex() == arralist.size() - 1) {
                setAttacking(false);
                setIndex(0);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(400);
                            GamePanel.jProgressBar.setValue(GamePanel.jProgressBar.getValue()+100);
                            if (getIndex() != 0 && !isAttacking())
                                setStand(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

        private void checkIfMainPlayerOutOfBound () {
            try {

                for (GameObject house : StaticVariables.world.getBackGroundObjects()) {
                    if (house.getClass().getSimpleName().equals("House")) {
                        if (house.getBounds().intersects(getBounds())) {
                            setStand(true);
                            setWalking(false);
                        }
                    }
                }

                if (getX() <= 0 || getY() <= 0 || getX() >= StaticVariables.world.getWidth() - getWidth() || getY() >= StaticVariables.world.getHeight() - getHeight()) {
                    setStand(true);
                    setWalking(false);


                }
            } catch (NullPointerException e) {


            } catch (ConcurrentModificationException e) {

            }

        }

    public static int getSumOfLife() {
        return sumOfLife;
    }

    public static void setSumOfLife(int sumOfLife) {
        MainPlayer.sumOfLife = sumOfLife;
    }

    public static Life getLife() {
        return life;
    }

    public static void setLife(Life life) {
        MainPlayer.life = life;
    }

    public static boolean isSpacielAttack() {
        return spacielAttack;
    }

    public static void setSpacielAttack(boolean spacielAttack) {
        MainPlayer.spacielAttack = spacielAttack;
    }

    public static Sound getWalkingSound() {
        return walkingSound;
    }

    public static void setWalkingSound(Sound walkingSound) {
        MainPlayer.walkingSound = walkingSound;
    }

    public static Sound getAttackingSound() {
        return attackingSound;
    }

    public static void setAttackingSound(Sound attackingSound) {
        MainPlayer.attackingSound = attackingSound;
    }

    public static Sound getEarthQuaqe() {
        return earthQuaqe;
    }

    public static void setEarthQuaqe(Sound earthQuaqe) {
        MainPlayer.earthQuaqe = earthQuaqe;
    }

    public static int getImageFrameRate() {
        return imageFrameRate;
    }

    public static void setImageFrameRate(int imageFrameRate) {
        MainPlayer.imageFrameRate = imageFrameRate;
    }

    public static int getAddLifeToMainPlayer() {
        return addLifeToMainPlayer;
    }

    public static void setAddLifeToMainPlayer(int addLifeToMainPlayer) {
        MainPlayer.addLifeToMainPlayer = addLifeToMainPlayer;
    }

    public static boolean isIntersect() {
        return intersect;
    }

    public static void setIntersect(boolean intersect) {
        MainPlayer.intersect = intersect;
    }

    public int getxSpriteSheet() {
        return xSpriteSheet;
    }

    public void setxSpriteSheet(int xSpriteSheet) {
        this.xSpriteSheet = xSpriteSheet;
    }

    public int getySprtieSheet() {
        return ySprtieSheet;
    }

    public void setySprtieSheet(int ySprtieSheet) {
        this.ySprtieSheet = ySprtieSheet;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getImageSpeed() {
        return imageSpeed;
    }

    public void setImageSpeed(int imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public int getDamgeToGhost() {
        return damgeToGhost;
    }

    public void setDamgeToGhost(int damgeToGhost) {
        this.damgeToGhost = damgeToGhost;
    }

    public static String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public static void setNameOfPlayer(String nameOfPlayer) {
        MainPlayer.nameOfPlayer = nameOfPlayer;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        MainPlayer.type = type;
    }

    public static boolean isAttacking() {
        return attacking;
    }

    public static void setAttacking(boolean attacking) {
        MainPlayer.attacking = attacking;
    }

    public static boolean isWalking() {
        return walking;
    }

    public static void setWalking(boolean walking) {
        MainPlayer.walking = walking;
    }

    public static boolean isStand() {
        return stand;
    }

    public static void setStand(boolean stand) {
        MainPlayer.stand = stand;
    }

    public static Point getPoint() {
        return point;
    }

    public static void setPoint(Point point) {
        MainPlayer.point = point;
    }

    public boolean isLeftFromTheGhost() {
        return leftFromTheGhost;
    }

    public void setLeftFromTheGhost(boolean leftFromTheGhost) {
        this.leftFromTheGhost = leftFromTheGhost;
    }

    public boolean isRightFromTheGhost() {
        return rightFromTheGhost;
    }

    public void setRightFromTheGhost(boolean rightFromTheGhost) {
        this.rightFromTheGhost = rightFromTheGhost;
    }

    public boolean isUpFromTheGhost() {
        return upFromTheGhost;
    }

    public void setUpFromTheGhost(boolean upFromTheGhost) {
        this.upFromTheGhost = upFromTheGhost;
    }

    public boolean isDownFromTheGhost() {
        return downFromTheGhost;
    }

    public void setDownFromTheGhost(boolean downFromTheGhost) {
        this.downFromTheGhost = downFromTheGhost;
    }

    public boolean isIs_stand_left_up() {
        return is_stand_left_up;
    }

    public void setIs_stand_left_up(boolean is_stand_left_up) {
        this.is_stand_left_up = is_stand_left_up;
    }

    public boolean isIs_stand_left_down() {
        return is_stand_left_down;
    }

    public void setIs_stand_left_down(boolean is_stand_left_down) {
        this.is_stand_left_down = is_stand_left_down;
    }

    public boolean isIs_stand_right_dowb() {
        return is_stand_right_dowb;
    }

    public void setIs_stand_right_dowb(boolean is_stand_right_dowb) {
        this.is_stand_right_dowb = is_stand_right_dowb;
    }

    public boolean isIs_stand_right_up() {
        return is_stand_right_up;
    }

    public void setIs_stand_right_up(boolean is_stand_right_up) {
        this.is_stand_right_up = is_stand_right_up;
    }



    public static ArrayList<ImageIcon> getUp() {
        return up;
    }

    public static void setUp(ArrayList<ImageIcon> up) {
        MainPlayer.up = up;
    }

    public static ArrayList<ImageIcon> getDown() {
        return down;
    }

    public static void setDown(ArrayList<ImageIcon> down) {
        MainPlayer.down = down;
    }

    public static ArrayList<ImageIcon> getLeft() {
        return left;
    }

    public static void setLeft(ArrayList<ImageIcon> left) {
        MainPlayer.left = left;
    }

    public static ArrayList<ImageIcon> getRight() {
        return right;
    }

    public static void setRight(ArrayList<ImageIcon> right) {
        MainPlayer.right = right;
    }

    public static ArrayList<ImageIcon> getStandDown() {
        return standDown;
    }

    public static void setStandDown(ArrayList<ImageIcon> standDown) {
        MainPlayer.standDown = standDown;
    }

    public static ArrayList<ImageIcon> getAttackLeft() {
        return attackLeft;
    }

    public static void setAttackLeft(ArrayList<ImageIcon> attackLeft) {
        MainPlayer.attackLeft = attackLeft;
    }

    public static ArrayList<ImageIcon> getAttackRight() {
        return attackRight;
    }

    public static void setAttackRight(ArrayList<ImageIcon> attackRight) {
        MainPlayer.attackRight = attackRight;
    }

    public static ArrayList<ImageIcon> getAttackDown() {
        return attackDown;
    }

    public static void setAttackDown(ArrayList<ImageIcon> attackDown) {
        MainPlayer.attackDown = attackDown;
    }

    public static ArrayList<ImageIcon> getAttackUp() {
        return attackUp;
    }

    public static void setAttackUp(ArrayList<ImageIcon> attackUp) {
        MainPlayer.attackUp = attackUp;
    }

    public static ArrayList<ImageIcon> getDie() {
        return die;
    }

    public static void setDie(ArrayList<ImageIcon> die) {
        MainPlayer.die = die;
    }

    public static ArrayList<ImageIcon> getSpacielAttackA() {
        return spacielAttackA;
    }

    public static void setSpacielAttackA(ArrayList<ImageIcon> spacielAttackA) {
        MainPlayer.spacielAttackA = spacielAttackA;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getDistanceFromPoint() {
        return distanceFromPoint;
    }

    public void setDistanceFromPoint(double distanceFromPoint) {
        this.distanceFromPoint = distanceFromPoint;
    }

    public long getSpeedOfMove() {
        return speedOfMove;
    }

    public void setSpeedOfMove(long speedOfMove) {
        this.speedOfMove = speedOfMove;
    }

    public FootStep getFootStep() {
        return footStep;
    }

    public void setFootStep(FootStep footStep) {
        this.footStep = footStep;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getStopTime() {
        return stopTime;
    }

    public void setStopTime(float stopTime) {
        this.stopTime = stopTime;
    }

    private void checkIfStand() {
        try {


            if (stand) {

                setSize(xSpriteSheet, ySprtieSheet);


                if (index >= standDown.size())
                    index = 0;
                if (standDown.size() > 0)
                    setIcon(standDown.get(index));

                attacking = false;
                walking = false;
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

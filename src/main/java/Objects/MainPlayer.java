package Objects;

import BackgroundObject.FootStep;
import GameCore.*;
import ImageHandel.ImageLoader;
import sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Vector;

public class MainPlayer extends GameObject {

    public static int sumOfLife = 20000;
    public static Life life;
    public static boolean spacielAttack;


    private Sound walkingSound, attackingSound,spacielSound;
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
    private static File DIR_1;

    public static ArrayList<Image>
            up, down, left, right,
            standDown,
            attackLeft, attackRight, attackDown, attackUp, die,spacielAttackA;
    private double angle = 0;
    private double distanceFromPoint;
    private long speedOfMove = 20;
    private FootStep footStep;


    public MainPlayer() {
        walkingSound = new Sound();
        attackingSound = new Sound();

        setBounds(1000, 1000, xSpriteSheet, ySprtieSheet);
        point = new Point(getX(), getY());
        setTheUserAction();


    }


    public void calculateTheAngle(int x, int y) {
        float xDistance = getX() - x;
        float yDistance = getY() - y;

        angle = (360 + Math.toDegrees(Math.atan2(yDistance, xDistance))) % 360;
        /*
         * calculate the angle of main player position in order to
         * know what is the diriction that the main player wants to move
         * */
    }

    public static void addMainPlayerPosition(final String type2) {

        type = type2;
        new Thread(new Runnable() {
            public void run() {


                final Dimension stand = new Dimension(350, 330);
                final Dimension side = new Dimension(400, 350);
                final Dimension upDown = new Dimension(400, 380);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/stand/");
                ImageLoader.addImageOfObject(DIR_1, standDown,null, stand);



                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/attack/spacielAttack");
                ImageLoader.addImageOfObject(DIR_1, spacielAttackA,null, stand);


                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/attack/left");
                        ImageLoader.addImageOfObject(DIR_1, attackLeft,null, side);



                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/attack/up");
                        ImageLoader.addImageOfObject(DIR_1, attackUp,null, upDown);


                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/attack/down");
                        ImageLoader.addImageOfObject(DIR_1, attackDown,null, side);


                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/attack/right");
                        ImageLoader.addImageOfObject(DIR_1, attackRight,null, upDown);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/die");
                        ImageLoader.addImageOfObject(DIR_1, die,null, upDown);


                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/walk/right");
                        ImageLoader.addImageOfObject(DIR_1, right,null, side);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/walk/left");
                        ImageLoader.addImageOfObject(DIR_1, left,null, side);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/walk/down");
                        ImageLoader.addImageOfObject(DIR_1, down,null, upDown);

                        DIR_1 = new File("src/main/java/ImageHandel/Photos/character/" + type2 + "/walk/up");
                        ImageLoader.addImageOfObject(DIR_1, up,null, upDown);



            }
        }).start();


    }

    private void setTheMainPlayerAttackSound() {
        if (type.equals("male")) {
            attackingSound.playSound(Sound.path.get(1), false);
            attackingSound.setVolume(-15);
        } else {
            attackingSound.playSound(Sound.path.get(3), false);

            attackingSound.setVolume(-15);
        }

    }

    public static void makeNewElements(String type) {

        up = new ArrayList<Image>();
        down = new ArrayList<Image>();
        left = new ArrayList<Image>();
        right = new ArrayList<Image>();

        standDown = new ArrayList<Image>();
        spacielAttackA=new ArrayList<Image>();

        attackLeft = new ArrayList<Image>();
        attackDown = new ArrayList<Image>();
        attackRight = new ArrayList<Image>();
        attackUp = new ArrayList<Image>();
        die = new ArrayList<Image>();
        addMainPlayerPosition(type);

    }

    private void setTheUserAction() {
        new Thread(new Runnable() {
            public void run() {

                while (true) {


                    imageFrameRate++;
                    setPlace();
                    checkIfMainPlayerOutOfBound();
                    checkifMainPlayerIntercetWithMonster();
                    setLocationOfMainPlayerWhenFightTheGhost();
                    addLifeToPlayer();


                    try {
                        Thread.sleep(speedOfMove);
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

        if (!attacking && walking) {
            addLifeToMainPlayer++;
            if (addLifeToMainPlayer == 15 && life.getjProgressBar().getValue() < life.getjProgressBar().getMaximum()) {
                addLifeToMainPlayer = 0;
                life.getjProgressBar().setValue(life.getjProgressBar().getValue() + 1);
                life.getjProgressBar().setString("" + life.getjProgressBar().getValue());
            }
            if (addLifeToMainPlayer > 15)
                addLifeToMainPlayer = 0;
        }
    }

    private void checkifMainPlayerIntercetWithMonster() {

        try {
            for (Ghost g : StaticVariables.world.getGhostArrayList()) {
                try {

                    if (g.isStopMoving()&&g.getLifeBar().isAlive())
                        intersect = g.isStopMoving();
                    else
                        intersect = false;
                    if (intersect)
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
        if (attacking) {
            if (angle < 164 && angle >= 52) {
                downFromTheGhost = true;
                leftFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 183 && angle >= 164) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 205 && angle >= 183) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 231 && angle >= 205) {
                leftFromTheGhost = true;
                downFromTheGhost = false;
                rightFromTheGhost = false;
                upFromTheGhost = false;
            }
            if (angle < 281 && angle >= 231) {
//                    down
                upFromTheGhost = true;
                leftFromTheGhost = false;
                downFromTheGhost = false;
                rightFromTheGhost = false;
            }
            if (angle < 318 && angle >= 281) {
//                    left up
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }
            if (angle >= 318) {
//                    left
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }
            if (angle < 52 && angle >= 0) {
//                    left down
                rightFromTheGhost = true;
                upFromTheGhost = false;
                leftFromTheGhost = false;
                downFromTheGhost = false;
            }


        }

    }

    private void setPlace() {


        if (imageFrameRate % 20 == 0)
            footStep = new FootStep();
        try {
            checkIfWalking();
            checkIfStand();
            checkIfAttacking();
            checkIfSpacialAttack();
        }catch (Exception e) {
            e.printStackTrace();
        }
        index++;

    }

    private void checkIfSpacialAttack() {
        if(spacielAttack)
        {
            attacking=false;
            stand=false;
            walking=false;
            if(index>=spacielAttackA.size()-1)
            {
                index=spacielAttackA.size()-1;

            }
            setIcon(new ImageIcon(spacielAttackA.get(index)));
            if(index==40)
                vibrateTheScreen();

        }
    }

    private void vibrateTheScreen() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2500);

                    while (GamePanel.jProgressBar.getValue()>0)
                    {
                        GamePanel.jProgressBar.setValue(GamePanel.jProgressBar.getValue()-1);
                        Thread.sleep(5);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {

            boolean dir=false;
            public void run() {
                while (intersect)
                {


                    for (Ghost g:World.ghostArrayList) {
                        g.decreaseLife(50);
                    }
                    int x;
                    if(dir)
                    x=30;
                    else
                        x=-30;
                    dir=!dir;
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+x,StaticVariables.world.getY());

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                spacielAttack=false;
                stand=true;
            }
        }).start();
    }

    private void checkIfAttacking() {

        if (attacking) {
            setTheMainPlayerAttackSound();


            if (leftFromTheGhost) {
                checkIndexOutOfBound(attackRight);
                setIcon(new ImageIcon(attackRight.get(index)));
            } else if (rightFromTheGhost) {
                checkIndexOutOfBound(attackLeft);
                setIcon(new ImageIcon(attackLeft.get(index)));

            } else if (downFromTheGhost) {
                checkIndexOutOfBound(attackUp);
                setIcon(new ImageIcon(attackUp.get(index)));

            } else if (upFromTheGhost) {
                checkIndexOutOfBound(attackDown);
                setIcon(new ImageIcon(attackDown.get(index)));

            }
        }
    }

        private void checkIfWalking() {
            if (walking) {
                attackingSound.stopSound();
                walkingSound.playSound(Sound.path.get(0), false);
                if (index >= left.size())
                    index = 0;
                attacking = false;
                stand = false;
                if (angle < 164 && angle >= 52) {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX(), getY() - imageSpeed);
                    if (up.size() > 0 && index <= up.size())
                        setIcon(new ImageIcon(up.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(3);
// up

                }
                if (angle < 183 && angle >= 164) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX() + imageSpeed, getY() - imageSpeed);
                    if (right.size() > 0 && index <= right.size())
                        setIcon(new ImageIcon(right.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(4);
//                    rightup
                }
                if (angle < 205 && angle >= 183) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY());
                    setLocation(getX() + imageSpeed, getY());
                    if (right.size() > 0 && index <= right.size())
                        setIcon(new ImageIcon(right.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(5);
//                    right

                }
                if (angle < 231 && angle >= 205) {

                    StaticVariables.world.setLocation(StaticVariables.world.getX() - imageSpeed, StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX() + imageSpeed, getY() + imageSpeed);
                    if (right.size() > 0 && index <= right.size())
                        setIcon(new ImageIcon(right.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(6);
//right down
                }
                if (angle < 281 && angle >= 231) {
//                    down


                    StaticVariables.world.setLocation(StaticVariables.world.getX(), StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX(), getY() + imageSpeed);
                    if (down.size() > 0 && index <= down.size())
                        setIcon(new ImageIcon(down.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(7);
                }
                if (angle < 318 && angle >= 281) {
//                    left up


                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY() - imageSpeed);
                    setLocation(getX() - imageSpeed, getY() + imageSpeed);
                    if (left.size() > 0 && index <= left.size())
                        setIcon(new ImageIcon(left.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(2);

                }

                if (angle >= 318) {
//                    left

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY());
                    setLocation(getX() - imageSpeed, getY());
                    if (left.size() > 0 && index <= left.size())
                        setIcon(new ImageIcon(left.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(1);

                }
                if (angle < 52 && angle >= 0) {
//                    left down

                    StaticVariables.world.setLocation(StaticVariables.world.getX() + imageSpeed, StaticVariables.world.getY() + imageSpeed);
                    setLocation(getX() - imageSpeed, getY() - imageSpeed);
                    if (left.size() > 0 && index <= left.size())
                        setIcon(new ImageIcon(left.get(index)));
                    if (imageFrameRate % 20 == 0)
                        footStep.setTheImage(8);
                }
            }
        }

        private void checkIndexOutOfBound (ArrayList < Image > arralist) {


            if (index == arralist.size() - 1) {
                attacking = false;
                index = 0;
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(400);
                            GamePanel.jProgressBar.setValue(GamePanel.jProgressBar.getValue()+20);
                            if (index != 0 && !attacking)
                                stand = true;
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
                            stand = true;
                            walking = false;
                        }
                    }
                }

                if (getX() <= 0 || getY() <= 0 || getX() >= StaticVariables.world.getWidth() - getWidth() || getY() >= StaticVariables.world.getHeight() - getHeight()) {

                    stand = true;
                    walking = false;

                }
            } catch (NullPointerException e) {


            } catch (ConcurrentModificationException e) {

            }

        }


        public static int getSumOfLife () {
            return sumOfLife;
        }

        public static void setSumOfLife ( int sumOfLife){
            MainPlayer.sumOfLife = sumOfLife;
        }

        public static Life getLife () {
            return life;
        }

        public static void setLife (Life life){
            MainPlayer.life = life;
        }

        public int getxSpriteSheet () {
            return xSpriteSheet;
        }

        public void setxSpriteSheet ( int xSpriteSheet){
            this.xSpriteSheet = xSpriteSheet;
        }

        public int getySprtieSheet () {
            return ySprtieSheet;
        }

        public void setySprtieSheet ( int ySprtieSheet){
            this.ySprtieSheet = ySprtieSheet;
        }

        public int getIndex () {
            return index;
        }

        public void setIndex ( int index){
            this.index = index;
        }

        public int getImageFrameRate () {
            return imageFrameRate;
        }

        public void setImageFrameRate ( int imageFrameRate){
            this.imageFrameRate = imageFrameRate;
        }

        public int getImageSpeed () {
            return imageSpeed;
        }

        public void setImageSpeed ( int imageSpeed){
            this.imageSpeed = imageSpeed;
        }

        public static String getNameOfPlayer () {
            return nameOfPlayer;
        }

        public static void setNameOfPlayer (String nameOfPlayer){
            MainPlayer.nameOfPlayer = nameOfPlayer;
        }


        public boolean isAttacking () {
            return attacking;
        }

        public void setAttacking ( boolean attacking){
            this.attacking = attacking;
        }

        public static boolean isWalking () {
            return walking;
        }

        public static void setWalking ( boolean walking){
            MainPlayer.walking = walking;
        }

        public static Point getPoint () {
            return point;
        }

        public static void setPoint (Point point){
            MainPlayer.point = point;
        }

        public boolean isLeftFromTheGhost () {
            return leftFromTheGhost;
        }

        public int getDamgeToGhost () {
            return damgeToGhost;
        }

        public void setDamgeToGhost ( int damgeToGhost){
            this.damgeToGhost = damgeToGhost;
        }

    public static boolean isSpacielAttack() {
        return spacielAttack;
    }

    public static void setSpacielAttack(boolean spacielAttack) {
        MainPlayer.spacielAttack = spacielAttack;
    }

    public Sound getWalkingSound() {
        return walkingSound;
    }

    public void setWalkingSound(Sound walkingSound) {
        this.walkingSound = walkingSound;
    }

    public Sound getAttackingSound() {
        return attackingSound;
    }

    public void setAttackingSound(Sound attackingSound) {
        this.attackingSound = attackingSound;
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

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        MainPlayer.type = type;
    }

    public static boolean isStand() {
        return stand;
    }

    public static void setStand(boolean stand) {
        MainPlayer.stand = stand;
    }

    public static File getDir1() {
        return DIR_1;
    }

    public static void setDir1(File dir1) {
        DIR_1 = dir1;
    }

    public static ArrayList<Image> getUp() {
        return up;
    }

    public static void setUp(ArrayList<Image> up) {
        MainPlayer.up = up;
    }

    public static ArrayList<Image> getDown() {
        return down;
    }

    public static void setDown(ArrayList<Image> down) {
        MainPlayer.down = down;
    }

    public static ArrayList<Image> getLeft() {
        return left;
    }

    public static void setLeft(ArrayList<Image> left) {
        MainPlayer.left = left;
    }

    public static ArrayList<Image> getRight() {
        return right;
    }

    public static void setRight(ArrayList<Image> right) {
        MainPlayer.right = right;
    }

    public static ArrayList<Image> getStandDown() {
        return standDown;
    }

    public static void setStandDown(ArrayList<Image> standDown) {
        MainPlayer.standDown = standDown;
    }

    public static ArrayList<Image> getAttackLeft() {
        return attackLeft;
    }

    public static void setAttackLeft(ArrayList<Image> attackLeft) {
        MainPlayer.attackLeft = attackLeft;
    }

    public static ArrayList<Image> getAttackRight() {
        return attackRight;
    }

    public static void setAttackRight(ArrayList<Image> attackRight) {
        MainPlayer.attackRight = attackRight;
    }

    public static ArrayList<Image> getAttackDown() {
        return attackDown;
    }

    public static void setAttackDown(ArrayList<Image> attackDown) {
        MainPlayer.attackDown = attackDown;
    }

    public static ArrayList<Image> getAttackUp() {
        return attackUp;
    }

    public static void setAttackUp(ArrayList<Image> attackUp) {
        MainPlayer.attackUp = attackUp;
    }

    public static ArrayList<Image> getDie() {
        return die;
    }

    public static void setDie(ArrayList<Image> die) {
        MainPlayer.die = die;
    }

    public static ArrayList<Image> getSpacielAttackA() {
        return spacielAttackA;
    }

    public static void setSpacielAttackA(ArrayList<Image> spacielAttackA) {
        MainPlayer.spacielAttackA = spacielAttackA;
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

    public void setLeftFromTheGhost (boolean leftFromTheGhost){
            this.leftFromTheGhost = leftFromTheGhost;
        }

        public boolean isRightFromTheGhost () {
            return rightFromTheGhost;
        }

        public void setRightFromTheGhost ( boolean rightFromTheGhost){
            this.rightFromTheGhost = rightFromTheGhost;
        }

        public boolean isUpFromTheGhost () {
            return upFromTheGhost;
        }

        public void setUpFromTheGhost ( boolean upFromTheGhost){
            this.upFromTheGhost = upFromTheGhost;
        }

        public boolean isDownFromTheGhost () {
            return downFromTheGhost;
        }

        public void setDownFromTheGhost ( boolean downFromTheGhost){
            this.downFromTheGhost = downFromTheGhost;
        }

        public boolean isIs_stand_left_up () {
            return is_stand_left_up;
        }

        public void setIs_stand_left_up ( boolean is_stand_left_up){
            this.is_stand_left_up = is_stand_left_up;
        }

        public boolean isIs_stand_left_down () {
            return is_stand_left_down;
        }

        public void setIs_stand_left_down ( boolean is_stand_left_down){
            this.is_stand_left_down = is_stand_left_down;
        }

        public boolean isIs_stand_right_dowb () {
            return is_stand_right_dowb;
        }

        public void setIs_stand_right_dowb ( boolean is_stand_right_dowb){
            this.is_stand_right_dowb = is_stand_right_dowb;
        }

        public boolean isIs_stand_right_up () {
            return is_stand_right_up;
        }

        public void setIs_stand_right_up ( boolean is_stand_right_up){
            this.is_stand_right_up = is_stand_right_up;
        }

        public File getDIR_1 () {
            return DIR_1;
        }

        public void setDIR_1 (File DIR_1){
            this.DIR_1 = DIR_1;
        }


        public double getAngle () {
            return angle;
        }

        public void setAngle ( double angle){
            this.angle = angle;
        }

        public double getDistanceFromPoint () {
            return distanceFromPoint;
        }

        public void setDistanceFromPoint ( double distanceFromPoint){
            this.distanceFromPoint = distanceFromPoint;

    }

    private void checkIfStand() {
        if (stand) {

            attackingSound.stopSound();
            walkingSound.stopSound();
            if (index >= standDown.size())
                index = 0;
            if (standDown.size() > 0)
                setIcon(new ImageIcon(standDown.get(index)));
            attacking = false;
            walking = false;
        }

    }
}

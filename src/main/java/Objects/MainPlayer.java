package Objects;

import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import java.util.Vector;

public class MainPlayer extends GameObject {

    public static int sumOfLife=35000;
    public static Life life;
    private int xSpriteSheet=220,ySprtieSheet=240,index=0,imageFrameRate=0,imageSpeed=3,damgeToGhost;
    public static String nameOfPlayer;
    private  boolean attacking;
    public static boolean walking;



    public static Point point;


    private boolean  is_stand_left, is_stand_right, is_stand_up, is_stand_down,

    is_stand_left_up, is_stand_left_down, is_stand_right_dowb, is_stand_right_up;
    private File DIR_1;

    private Vector<Image>
            up, down, left, right,
            leftUp, leftDown, rightUp, rightDown,
            standUp, standDown, standLeft, standRight,
            standLefDown, standRightDown, standRightUp, standLeftUp, attack, die;
    private double angle=0;
    private double distanceFromPoint;



    public MainPlayer(){
        setBounds(550,250,xSpriteSheet,ySprtieSheet);
        point=new Point(getX(),getY());
        makeNewElements();
        addMainPlayerPosition();



    }


    public  void calculateTheAngle(int x, int y) {
        float xDistance = getX()-x ;
        float yDistance =  getY()-y ;

        angle= (360 + Math.toDegrees(Math.atan2(yDistance, xDistance)))%360 ;

    }

    private void addMainPlayerPosition() {

        new Thread(new Runnable() {
            public void run() {

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, up,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, down,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left/");
                ImageLoader.addImageOfMainPlayer(DIR_1, left,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right/");
                ImageLoader.addImageOfMainPlayer(DIR_1, right,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standUp,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standRight,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standDown,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standLeft,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standRightDown,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standRightUp,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, leftDown,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, leftUp,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, rightDown,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, rightUp,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left_down/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standLefDown,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left_up/");
                ImageLoader.addImageOfMainPlayer(DIR_1, standLeftUp,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/fall/");
                ImageLoader.addImageOfMainPlayer(DIR_1, die,getSize());

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/attack2/");
                ImageLoader.addImageOfMainPlayer(DIR_1, attack,getSize());
                setTheUserAction();

            }
        }).start();











    }


    private void makeNewElements() {
        up = new Vector<Image>();
        down =  new Vector<Image>();
        left =  new Vector<Image>();
        right =  new Vector<Image>();

        standDown =  new Vector<Image>();
        standUp =  new Vector<Image>();
        standLeft =  new Vector<Image>();
        standRight =  new Vector<Image>();

        standLefDown =  new Vector<Image>();
        standLeftUp =  new Vector<Image>();
        standRightDown =  new Vector<Image>();
        standRightUp =  new Vector<Image>();

        leftUp =  new Vector<Image>();
        leftDown =  new Vector<Image>();
        rightDown =  new Vector<Image>();
        rightUp =  new Vector<Image>();

        attack =  new Vector<Image>();
        die =  new Vector<Image>();


    }

    public void calculateTheDistance(){
       distanceFromPoint = Math.hypot(getX() - point.getLocation().getX(), getY() -point.getLocation().getY());
        distanceFromPoint-=100;






    }


    private void setTheUserAction(){
        new Thread(new Runnable() {
            public void run() {
                while (life.isAlive())
                {



                    changeIcon();
                    calculateTheDistance();

                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void changeIcon()
    {

        imageFrameRate++;
        if(imageFrameRate%5==0)
        {


            if(distanceFromPoint<50&&!attacking)
            {

                walking=false;
                if(is_stand_up)
                    setIcon(new ImageIcon(standUp.get(index)));
                if(is_stand_down)
                    setIcon(new ImageIcon(standDown.get(index)));
                if(is_stand_left)
                    setIcon(new ImageIcon(standLeft.get(index)));
                if(is_stand_right)
                    setIcon(new ImageIcon(standRight.get(index)));
                if(is_stand_left_down)
                    setIcon(new ImageIcon(standLefDown.get(index)));
                if(is_stand_left_up)
                    setIcon(new ImageIcon(standLeftUp.get(index)));
                if(is_stand_right_dowb)
                    setIcon(new ImageIcon(standRightDown.get(index)));
                if(is_stand_right_up)
                    setIcon(new ImageIcon(standRightUp.get(index)));
            }

            if(distanceFromPoint>=50&&!attacking)
            {
                walking=true;
                changeTheBollen();
                if(angle<164&&angle>=52)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(),StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX(),getY()-imageSpeed);
                    setIcon(new ImageIcon(up.get(index)));

                }
                if(angle<183&&angle>=164)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX()+imageSpeed,getY()-imageSpeed);
                    setIcon(new ImageIcon(rightUp.get(index)));

                }
                if(angle<205&&angle>=183)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY());
                    setLocation(getX()+imageSpeed,getY());
                    setIcon(new ImageIcon(right.get(index)));
                }
                if(angle<231&&angle>=205)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX()+imageSpeed,getY()+imageSpeed);
                    setIcon(new ImageIcon(rightDown.get(index)));

                }
                if(angle<281&&angle>=231)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(),StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX(),getY()+imageSpeed);
                    setIcon(new ImageIcon(down.get(index)));

                }
                if(angle<318&&angle>=281)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX()-imageSpeed,getY()+imageSpeed);
                    setIcon(new ImageIcon(leftDown.get(index)));
                }

                if(angle>=318)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY());
                    setLocation(getX()-imageSpeed,getY());
                    setIcon(new ImageIcon(left.get(index)));

                }
                if(angle<52&&angle>=0)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX()-imageSpeed,getY()-imageSpeed);
                    setIcon(new ImageIcon(leftUp.get(index)));

                }


            }
            else if(!attacking)
            {
                changeTheBollen();
                if(angle<164&&angle>=52)
                    is_stand_up=true;
                if(angle<183&&angle>=164)
                    is_stand_right_up=true;
                if(angle<205&&angle>=183)
                    is_stand_right=true;
                if(angle<231&&angle>=205)
                    is_stand_right_dowb=true;
                if(angle<281&&angle>=231)
                    is_stand_down=true;
                if(angle<318&&angle>=281)
                    is_stand_left_down=true;
                if(angle>=318)
                    is_stand_left=true;
                if(angle<52&&angle>=0)
                    is_stand_left_up=true;
            }

            if(attacking)
            {
                setIcon(new ImageIcon(attack.get(index)));
                if(index==attack.size()-1)
                {
                    attacking=false;
                    index=0;
                }


            }


            if(index==attack.size()-1)
                index=0;
            else
            index++;
        }
        StaticVariables.gamePanel.repaint();

    }

    private void changeTheBollen(){
        is_stand_up=false;
        is_stand_right_up=false;
        is_stand_right=false;
        is_stand_right_dowb=false;
        is_stand_down=false;
        is_stand_left_down=false;
        is_stand_left_up=false;
        is_stand_left=false;
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

    public int getImageFrameRate() {
        return imageFrameRate;
    }

    public void setImageFrameRate(int imageFrameRate) {
        this.imageFrameRate = imageFrameRate;
    }

    public int getImageSpeed() {
        return imageSpeed;
    }

    public void setImageSpeed(int imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public static String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public static void setNameOfPlayer(String nameOfPlayer) {
        MainPlayer.nameOfPlayer = nameOfPlayer;
    }



    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public static boolean isWalking() {
        return walking;
    }

    public static void setWalking(boolean walking) {
        MainPlayer.walking = walking;
    }

    public static Point getPoint() {
        return point;
    }

    public static void setPoint(Point point) {
        MainPlayer.point = point;
    }

    public boolean isIs_stand_left() {
        return is_stand_left;
    }

    public int getDamgeToGhost() {
        return damgeToGhost;
    }

    public void setDamgeToGhost(int damgeToGhost) {
        this.damgeToGhost = damgeToGhost;
    }

    public Vector<Image> getUp() {
        return up;
    }

    public void setUp(Vector<Image> up) {
        this.up = up;
    }

    public Vector<Image> getDown() {
        return down;
    }

    public void setDown(Vector<Image> down) {
        this.down = down;
    }

    public Vector<Image> getLeft() {
        return left;
    }

    public void setLeft(Vector<Image> left) {
        this.left = left;
    }

    public Vector<Image> getRight() {
        return right;
    }

    public void setRight(Vector<Image> right) {
        this.right = right;
    }

    public Vector<Image> getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Vector<Image> leftUp) {
        this.leftUp = leftUp;
    }

    public Vector<Image> getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Vector<Image> leftDown) {
        this.leftDown = leftDown;
    }

    public Vector<Image> getRightUp() {
        return rightUp;
    }

    public void setRightUp(Vector<Image> rightUp) {
        this.rightUp = rightUp;
    }

    public Vector<Image> getRightDown() {
        return rightDown;
    }

    public void setRightDown(Vector<Image> rightDown) {
        this.rightDown = rightDown;
    }

    public Vector<Image> getStandUp() {
        return standUp;
    }

    public void setStandUp(Vector<Image> standUp) {
        this.standUp = standUp;
    }

    public Vector<Image> getStandDown() {
        return standDown;
    }

    public void setStandDown(Vector<Image> standDown) {
        this.standDown = standDown;
    }

    public Vector<Image> getStandLeft() {
        return standLeft;
    }

    public void setStandLeft(Vector<Image> standLeft) {
        this.standLeft = standLeft;
    }

    public Vector<Image> getStandRight() {
        return standRight;
    }

    public void setStandRight(Vector<Image> standRight) {
        this.standRight = standRight;
    }

    public Vector<Image> getStandLefDown() {
        return standLefDown;
    }

    public void setStandLefDown(Vector<Image> standLefDown) {
        this.standLefDown = standLefDown;
    }

    public Vector<Image> getStandRightDown() {
        return standRightDown;
    }

    public void setStandRightDown(Vector<Image> standRightDown) {
        this.standRightDown = standRightDown;
    }

    public Vector<Image> getStandRightUp() {
        return standRightUp;
    }

    public void setStandRightUp(Vector<Image> standRightUp) {
        this.standRightUp = standRightUp;
    }

    public Vector<Image> getStandLeftUp() {
        return standLeftUp;
    }

    public void setStandLeftUp(Vector<Image> standLeftUp) {
        this.standLeftUp = standLeftUp;
    }

    public Vector<Image> getAttack() {
        return attack;
    }

    public void setAttack(Vector<Image> attack) {
        this.attack = attack;
    }

    public Vector<Image> getDie() {
        return die;
    }

    public void setDie(Vector<Image> die) {
        this.die = die;
    }

    public void setIs_stand_left(boolean is_stand_left) {
        this.is_stand_left = is_stand_left;
    }

    public boolean isIs_stand_right() {
        return is_stand_right;
    }

    public void setIs_stand_right(boolean is_stand_right) {
        this.is_stand_right = is_stand_right;
    }

    public boolean isIs_stand_up() {
        return is_stand_up;
    }

    public void setIs_stand_up(boolean is_stand_up) {
        this.is_stand_up = is_stand_up;
    }

    public boolean isIs_stand_down() {
        return is_stand_down;
    }

    public void setIs_stand_down(boolean is_stand_down) {
        this.is_stand_down = is_stand_down;
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

    public File getDIR_1() {
        return DIR_1;
    }

    public void setDIR_1(File DIR_1) {
        this.DIR_1 = DIR_1;
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
}

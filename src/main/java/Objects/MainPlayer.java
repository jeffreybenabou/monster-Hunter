package Objects;

import GameCore.GameObject;
import GameCore.Life;
import GameCore.StaticVariables;

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
    public static boolean Walking;



    public static Point point;


    private boolean  is_stand_left, is_stand_right, is_stand_up, is_stand_down,

    is_stand_left_up, is_stand_left_down, is_stand_right_dowb, is_stand_right_up;
    private File DIR_1;

    private Vector<Icon>
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
    private synchronized void addImageOfMainPlayer(File dir, Vector<Icon> linkedList) {
        String ImagePath;
        for (int i = 0; dir.listFiles().length > i; i++) {

            ImagePath = dir.getPath() + "\\" + i + ".png";
            ImagePath = ImagePath.replace("\\", "/");
            linkedList.add(new ImageIcon(new ImageIcon(ImagePath).getImage().getScaledInstance(getWidth(),getHeight(),0)));

        }
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
                addImageOfMainPlayer(DIR_1, up);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_down/");
                addImageOfMainPlayer(DIR_1, down);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left/");
                addImageOfMainPlayer(DIR_1, left);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right/");
                addImageOfMainPlayer(DIR_1, right);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_up/");
                addImageOfMainPlayer(DIR_1, standUp);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right/");
                addImageOfMainPlayer(DIR_1, standRight);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_down/");
                addImageOfMainPlayer(DIR_1, standDown);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left/");
                addImageOfMainPlayer(DIR_1, standLeft);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right_down/");
                addImageOfMainPlayer(DIR_1, standRightDown);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_right_up/");
                addImageOfMainPlayer(DIR_1, standRightUp);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left_down/");
                addImageOfMainPlayer(DIR_1, leftDown);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_left_up/");
                addImageOfMainPlayer(DIR_1, leftUp);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right_down/");
                addImageOfMainPlayer(DIR_1, rightDown);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/walk_right_up/");
                addImageOfMainPlayer(DIR_1, rightUp);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left_down/");
                addImageOfMainPlayer(DIR_1, standLefDown);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/stand_left_up/");
                addImageOfMainPlayer(DIR_1, standLeftUp);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/fall/");
                addImageOfMainPlayer(DIR_1, die);

                DIR_1 = new File("src/main/java/ImageHandel/Photos/character/attack2/");
                addImageOfMainPlayer(DIR_1, attack);
                setTheUserAction();

            }
        }).start();











    }


    private void makeNewElements() {
        up = new Vector<Icon>();
        down =  new Vector<Icon>();
        left =  new Vector<Icon>();
        right =  new Vector<Icon>();

        standDown =  new Vector<Icon>();
        standUp =  new Vector<Icon>();
        standLeft =  new Vector<Icon>();
        standRight =  new Vector<Icon>();

        standLefDown =  new Vector<Icon>();
        standLeftUp =  new Vector<Icon>();
        standRightDown =  new Vector<Icon>();
        standRightUp =  new Vector<Icon>();

        leftUp =  new Vector<Icon>();
        leftDown =  new Vector<Icon>();
        rightDown =  new Vector<Icon>();
        rightUp =  new Vector<Icon>();

        attack =  new Vector<Icon>();
        die =  new Vector<Icon>();


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

                if(is_stand_up)
                    setIcon(standUp.get(index));
                if(is_stand_down)
                    setIcon(standDown.get(index));
                if(is_stand_left)
                    setIcon(standLeft.get(index));
                if(is_stand_right)
                    setIcon(standRight.get(index));
                if(is_stand_left_down)
                    setIcon(standLefDown.get(index));
                if(is_stand_left_up)
                    setIcon(standLeftUp.get(index));
                if(is_stand_right_dowb)
                    setIcon(standRightDown.get(index));
                if(is_stand_right_up)
                    setIcon(standRightUp.get(index));
            }

            if(distanceFromPoint>=50&&!attacking)
            {
                changeTheBollen();
                if(angle<164&&angle>=52)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(),StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX(),getY()-imageSpeed);
                    setIcon(up.get(index));

                }
                if(angle<183&&angle>=164)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX()+imageSpeed,getY()-imageSpeed);
                    setIcon(rightUp.get(index));

                }
                if(angle<205&&angle>=183)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY());
                    setLocation(getX()+imageSpeed,getY());
                    setIcon(right.get(index));
                }
                if(angle<231&&angle>=205)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()-imageSpeed,StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX()+imageSpeed,getY()+imageSpeed);
                    setIcon(rightDown.get(index));

                }
                if(angle<281&&angle>=231)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX(),StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX(),getY()+imageSpeed);
                    setIcon(down.get(index));

                }
                if(angle<318&&angle>=281)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY()-imageSpeed);
                    setLocation(getX()-imageSpeed,getY()+imageSpeed);
                    setIcon(leftDown.get(index));
                }

                if(angle>=318)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY());
                    setLocation(getX()-imageSpeed,getY());
                    setIcon(left.get(index));

                }
                if(angle<52&&angle>=0)
                {
                    StaticVariables.world.setLocation(StaticVariables.world.getX()+imageSpeed,StaticVariables.world.getY()+imageSpeed);
                    setLocation(getX()-imageSpeed,getY()-imageSpeed);
                    setIcon(leftUp.get(index));

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
                setIcon(attack.get(index));
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
        return Walking;
    }

    public static void setWalking(boolean walking) {
        Walking = walking;
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

    public Vector<Icon> getUp() {
        return up;
    }

    public void setUp(Vector<Icon> up) {
        this.up = up;
    }

    public Vector<Icon> getDown() {
        return down;
    }

    public void setDown(Vector<Icon> down) {
        this.down = down;
    }

    public Vector<Icon> getLeft() {
        return left;
    }

    public void setLeft(Vector<Icon> left) {
        this.left = left;
    }

    public Vector<Icon> getRight() {
        return right;
    }

    public void setRight(Vector<Icon> right) {
        this.right = right;
    }

    public Vector<Icon> getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Vector<Icon> leftUp) {
        this.leftUp = leftUp;
    }

    public Vector<Icon> getLeftDown() {
        return leftDown;
    }

    public int getDamgeToGhost() {
        return damgeToGhost;
    }

    public void setDamgeToGhost(int damgeToGhost) {
        this.damgeToGhost = damgeToGhost;
    }

    public void setLeftDown(Vector<Icon> leftDown) {
        this.leftDown = leftDown;
    }

    public Vector<Icon> getRightUp() {
        return rightUp;
    }

    public void setRightUp(Vector<Icon> rightUp) {
        this.rightUp = rightUp;
    }

    public Vector<Icon> getRightDown() {
        return rightDown;
    }

    public void setRightDown(Vector<Icon> rightDown) {
        this.rightDown = rightDown;
    }

    public Vector<Icon> getStandUp() {
        return standUp;
    }

    public void setStandUp(Vector<Icon> standUp) {
        this.standUp = standUp;
    }

    public Vector<Icon> getStandDown() {
        return standDown;
    }

    public void setStandDown(Vector<Icon> standDown) {
        this.standDown = standDown;
    }

    public Vector<Icon> getStandLeft() {
        return standLeft;
    }

    public void setStandLeft(Vector<Icon> standLeft) {
        this.standLeft = standLeft;
    }

    public Vector<Icon> getStandRight() {
        return standRight;
    }

    public void setStandRight(Vector<Icon> standRight) {
        this.standRight = standRight;
    }

    public Vector<Icon> getStandLefDown() {
        return standLefDown;
    }

    public void setStandLefDown(Vector<Icon> standLefDown) {
        this.standLefDown = standLefDown;
    }

    public Vector<Icon> getStandRightDown() {
        return standRightDown;
    }

    public void setStandRightDown(Vector<Icon> standRightDown) {
        this.standRightDown = standRightDown;
    }

    public Vector<Icon> getStandRightUp() {
        return standRightUp;
    }

    public void setStandRightUp(Vector<Icon> standRightUp) {
        this.standRightUp = standRightUp;
    }

    public Vector<Icon> getStandLeftUp() {
        return standLeftUp;
    }

    public void setStandLeftUp(Vector<Icon> standLeftUp) {
        this.standLeftUp = standLeftUp;
    }

    public Vector<Icon> getAttack() {
        return attack;
    }

    public void setAttack(Vector<Icon> attack) {
        this.attack = attack;
    }

    public Vector<Icon> getDie() {
        return die;
    }

    public void setDie(Vector<Icon> die) {
        this.die = die;
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

package Objects;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import java.util.Vector;

public class MainPlayer extends GameObject {

    private int xSpriteSheet=220,ySprtieSheet=240,index=0,imageFrameRate=0,imageSpeed=2;
    public static String nameOfPlayer;
    public static boolean isAlive;
    public static boolean attacking;
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
        isAlive=true;
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
        System.out.println(distanceFromPoint);






    }


    private void setTheUserAction(){
        new Thread(new Runnable() {
            public void run() {
                while (isAlive)
                {

                    calculateTheDistance();

                    changeIcon();


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
        StaticVariables.gamePanel.repaint();
        imageFrameRate++;
        if(imageFrameRate%5==0)
        {
            if(attacking)
                setIcon(attack.get(index));
            if(distanceFromPoint<50)
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

            if(distanceFromPoint>=50)
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
            else
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



            if(index==attack.size()-1)
                index=0;
            else
                index++;
        }


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
}

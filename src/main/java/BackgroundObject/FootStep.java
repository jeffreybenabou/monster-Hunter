package BackgroundObject;
import GameCore.GameObject;
import GameCore.StaticVariables;
import Objects.MainPlayer;
import javax.swing.*;
import java.awt.*;
public class FootStep extends GameObject {
    public static Image left, right, up, down, leftUp, leftDown, rightUp, rightDown;
    public static  int x=0,y=0;
    public FootStep() {
        setSize(50,50);
    }

    public void setTheImage(final int dir) {
        new Thread(new Runnable() {
            public void run() {

                switch (dir) {
                    case 1: {

                        setIcon(new ImageIcon(new ImageIcon(left).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+300,StaticVariables.mainPlayer.getY()+260);
                        break;
                    }
                    case 2: {
                        setIcon(new ImageIcon(new ImageIcon(leftDown).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+250,StaticVariables.mainPlayer.getY()+260);


                        break;
                    }
                    case 3: {
                        setIcon(new ImageIcon(new ImageIcon(up).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+160,StaticVariables.mainPlayer.getY()+250);

                        break;
                    }
                    case 4: {
                        setIcon(new ImageIcon(new ImageIcon(rightUp).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+180,StaticVariables.mainPlayer.getY()+260);

                        break;
                    }
                    case 5: {
                        setIcon(new ImageIcon(new ImageIcon(right).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX(),StaticVariables.mainPlayer.getY()+250);

                        break;
                    }
                    case 6: {
                        setIcon(new ImageIcon(new ImageIcon(rightDown).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+50+x,StaticVariables.mainPlayer.getY()+160+y);

                        break;
                    }
                    case 7: {
                        setIcon(new ImageIcon(new ImageIcon(down).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+200,StaticVariables.mainPlayer.getY()+50);


                        break;
                    }
                    case 8: {
                        setIcon(new ImageIcon(new ImageIcon(leftUp).getImage().getScaledInstance(50, 50, 0)));
                        setLocation(StaticVariables.mainPlayer.getX()+200,StaticVariables.mainPlayer.getY()+250);


                        break;
                    }


                }

                new Thread(new Runnable() {
                    public void run() {
                        addThisToWorld();
                    }
                }).start();


            }
        }).start();

    }

    public void addThisToWorld(){
/*
* this method add the foot step to world and then remove it after 2.5 second
* */
            MainPlayer.imageFrameRate=1;
            StaticVariables.world.add(this);


            try {
                Thread.sleep(2500);
                StaticVariables.world.remove(this);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

}

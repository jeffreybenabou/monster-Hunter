package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Bird extends GameObject  {

    private int index=0;
    public static Vector<Image> birdLeft,birdRight;
    private boolean left,down;

    public Bird(int x ,int y){
        setBounds(x,y,200,200);
        moveTheBird();
    }

    private void moveTheBird() {
        new Thread(new Runnable() {
            public void run() {
                while (true)
                {

                    if(left)
                        setIcon(new ImageIcon(birdLeft.get(index)));
                    else
                        setIcon(new ImageIcon(birdRight.get(index)));
                    repaint();
                    index++;
                    if(index==birdRight.size()-1)
                        index=0;
                    try {

                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {



                while (true)
                {

                    if(left)
                        setLocation(getX()-1,getY());
                    else
                        setLocation(getX()+1,getY());
                    if(down)
                        setLocation(getX(),getY()+1);
                    else
                        setLocation(getX(),getY()-1);
                    checkIfIntercet();



                    try {
                        Thread.sleep(20);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (ArrayIndexOutOfBoundsException a)
                    {
                        moveTheBird();
                    }


                }
            }
        }).start();
    }

    private void checkIfIntercet() {
        if(getX()<=0)
            left=false;
        if(getY()<=0)
            down=true;
        if(getY()>= StaticVariables.world.getHeight())
            down=false;
        if(getX()>=StaticVariables.world.getWidth())
            left=true;
    }
}

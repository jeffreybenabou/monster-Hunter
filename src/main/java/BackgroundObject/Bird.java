package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Bird extends GameObject  {

    private static   boolean firstLoad=true;
    private int index=0;
    public static ArrayList<Image> birdLeft,birdRight;
    private boolean left,down;

    public Bird(int x ,int y){
        if(firstLoad)
        {
            birdLeft=new ArrayList<Image>();
            birdRight=new ArrayList<Image>();
            ImageLoader.addImageOfObject(new File("src/main/java/ImageHandel/Photos/bird/left/"),birdLeft,null,new Dimension(200,200));
            ImageLoader.addImageOfObject(new File("src/main/java/ImageHandel/Photos/bird/right/"),birdRight,null,new Dimension(200,200));
            firstLoad=false;

        }

        setBounds(x,y,200,200);
        setIcon(new ImageIcon(birdLeft.get(0)));
        moveTheBird();
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void moveTheBird() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                    if (left)
                    {
                        // TODO: 02/07/2018 change the bird icon

                        setLocation(getX()-1,getY());
                        setIcon(new ImageIcon(birdLeft.get(index)));


                    }
                    else
                    {
                        setLocation(getX()+1,getY());
                        setIcon(new ImageIcon(birdRight.get(index)));

                    }
                    if(down)
                        setLocation(getX(),getY()+1);
                    else
                        setLocation(getX(),getY()-1);
                    index++;
                    if (index >= birdRight.size()-1)
                        index = 0;
                    checkIfIntercet();


                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }catch (Exception e)
                    {
                        System.out.println(Arrays.toString(e.getStackTrace()));
                    }
                }
            }
        }).start();

    }

    private void checkIfIntercet() {
        try
        {
            if(getX()<=0)
                left=false;
            if(getY()<=0)
                down=true;
            if(getY()>= StaticVariables.world.getHeight())
                down=false;
            if(getX()>=StaticVariables.world.getWidth())
                left=true;
        }catch (NullPointerException e)
        {

        }


    }
}

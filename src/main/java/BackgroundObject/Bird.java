package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bird extends GameObject  {

    private static   boolean firstLoad=true;
    private int index=0,imageFrameRate=0;
    public static ArrayList<ImageIcon> birdLeft,birdRight;
    private boolean left,down;
    private ImageLoader imageLoader;

    public Bird(int x ,int y){
        imageLoader=new ImageLoader();
        if(firstLoad)
        {
            setBirdLeft(new ArrayList<ImageIcon>());

            birdRight=new ArrayList<ImageIcon>();
            try
            {
                imageLoader.addImageOfObject(13,"ImageHandel/Photos/bird/left/",birdLeft,new Dimension(200,200));
                imageLoader.addImageOfObject(13,"ImageHandel/Photos/bird/right/",birdRight,new Dimension(200,200));
                firstLoad=false;
            }catch (NullPointerException e)
            {
                e.printStackTrace();
            }


        }

        setBounds(x,y,200,200);
        setIcon(birdLeft.get(0));
        moveTheBird();
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void moveTheBird() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        imageFrameRate++;
                    if (left)
                    {


                        setLocation(getX()-1,getY());
                        if(imageFrameRate%10==0)
                        setIcon(birdLeft.get(index));


                    }
                    else
                    {
                        setLocation(getX()+1,getY());
                        if(imageFrameRate%10==0)
                            setIcon((birdRight.get(index)));

                    }
                    if(down)
                        setLocation(getX(),getY()+1);
                    else
                        setLocation(getX(),getY()-1);
                    index++;
                    if (index >= birdRight.size()-1||index>=birdLeft.size()-1)
                        index = 0;
                    checkIfIntercet();


                        Thread.sleep(15);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (IndexOutOfBoundsException e) {
                        index=0;
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

    public static boolean isFirstLoad() {
        return firstLoad;
    }

    public static void setFirstLoad(boolean firstLoad) {
        Bird.firstLoad = firstLoad;
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

    public static ArrayList<ImageIcon> getBirdLeft() {
        return birdLeft;
    }

    public static void setBirdLeft(ArrayList<ImageIcon> birdLeft) {
        Bird.birdLeft = birdLeft;
    }

    public static ArrayList<ImageIcon> getBirdRight() {
        return birdRight;
    }

    public static void setBirdRight(ArrayList<ImageIcon> birdRight) {
        Bird.birdRight = birdRight;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}

package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;
import ImageHandel.ImageLoader;

import javax.swing.*;
import java.util.LinkedList;

public class Bird extends GameObject  {

    private static   boolean firstLoad=true;
    private Integer index=0,imageFrameRate=0;
    public static LinkedList<ImageIcon> birdLeft,birdRight;
    private boolean left,down;
    private ImageLoader imageLoader;

    public Bird(int x ,int y){
        imageLoader=new ImageLoader();
        if(firstLoad)
        {
            setBirdLeft(new LinkedList<ImageIcon>());

            birdRight=new LinkedList<ImageIcon>();
            try
            {
                imageLoader.addImageOfObject(13,"Photos/bird/left/",birdLeft);
                imageLoader.addImageOfObject(13,"Photos/bird/right/",birdRight);
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
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!StaticVariables.stopTheGame) {
                        try {
                            imageFrameRate++;
                            if (left) {


                                setLocation(getX() - 1, getY());
                                if (imageFrameRate % 10 == 0)
                                    setIcon(birdLeft.get(index));


                            } else {
                                setLocation(getX() + 1, getY());
                                if (imageFrameRate % 10 == 0)
                                    setIcon((birdRight.get(index)));

                            }
                            if (down)
                                setLocation(getX(), getY() + 1);
                            else
                                setLocation(getX(), getY() - 1);
                            index++;
                            if (index >= birdRight.size() - 1 || index >= birdLeft.size() - 1)
                                index = 0;
                            checkIfIntercet();





                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (IndexOutOfBoundsException e) {
                            index = 0;
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getImageFrameRate() {
        return imageFrameRate;
    }

    public void setImageFrameRate(Integer imageFrameRate) {
        this.imageFrameRate = imageFrameRate;
    }

    public static LinkedList<ImageIcon> getBirdLeft() {
        return birdLeft;
    }

    public static void setBirdLeft(LinkedList<ImageIcon> birdLeft) {
        Bird.birdLeft = birdLeft;
    }

    public static LinkedList<ImageIcon> getBirdRight() {
        return birdRight;
    }

    public static void setBirdRight(LinkedList<ImageIcon> birdRight) {
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

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }
}

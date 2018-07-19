package BackgroundObject;
import GameCore.GameObject;
import GameCore.StaticVariables;
import javax.swing.*;
import java.util.Random;

public class Cloud extends GameObject {

    private boolean left,down;
    private static ImageIcon imageIcon=new ImageIcon(StaticVariables.cloud);

    public Cloud(int x ,int y){
        Random random=new Random();
        left=random.nextBoolean();
        down=random.nextBoolean();
        setIcon(imageIcon);
    }

    private void moveTheCloud() {

        new Thread(new Runnable() {
            public void run() {


                while (true) {

                    try {
                        if (left)
                            setLocation(getX() - 1, getY());
                        else
                            setLocation(getX() + 1, getY());
                        if (down)
                            setLocation(getX(), getY() + 1);
                        else
                            setLocation(getX(), getY() - 1);
                        checkIfIntercet();


                        Thread.sleep(10);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception ee) {
                        ee.printStackTrace();

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
        if(getY()>=StaticVariables.world.getHeight())
            down=false;
        if(getX()>=StaticVariables.world.getWidth())
            left=true;
    }
}

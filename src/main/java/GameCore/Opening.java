package GameCore;



import ImageHandel.ImageLoader;
import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;

public class Opening extends JLabel {

    private int x=0,y=0;
    private Image intro;
    public Opening(){
        intro=ImageLoader.loadImage("Photos/Untitled-1.png");
        setBounds(0,0,StaticVariables.dimension.width,StaticVariables.dimension.height);
        setIcon(new ImageIcon(intro.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH)));
        setIcon(new ImageIcon(intro.getScaledInstance(intro.getWidth(null)+x,intro.getHeight(null)+y,Image.SCALE_SMOOTH)));

        new Thread(new Runnable() {
            public void run() {

                    new Thread(new Runnable() {
                        public void run() {
                            while(x<230) {
                                setIcon(new ImageIcon(intro.getScaledInstance(intro.getWidth(null) + x, intro.getHeight(null) + y, Image.SCALE_REPLICATE)));
                                x += 3;
                                y += 3;

                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            setVisible(false);
                            StaticVariables.mainMenu.setVisible(true);
                            MainClass.addTheMainMenu();
                        }

                    }).start();




            }
        }).start();
    }
}

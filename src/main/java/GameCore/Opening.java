package GameCore;



import ImageHandel.ImageLoader;
import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Opening extends JLabel {

    private ImageLoader imageLoader;
    private int x=0,y=0;
    private Image intro;
    public Opening(){

imageLoader=new ImageLoader();




        try
        {
            intro=imageLoader.loadImage("ImageHandel/Photos/opening.png");
            setIcon(new ImageIcon(intro.getScaledInstance(StaticVariables.dimension.width,StaticVariables.dimension.height,Image.SCALE_SMOOTH)));
            setBounds(0,0,StaticVariables.dimension.width,StaticVariables.dimension.height);



        }catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(StaticVariables.mainClass,e.fillInStackTrace());
            e.printStackTrace();


        }

        new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                StaticVariables.mainMenu.setVisible(true);
                MainClass.addTheMainMenu();




            }
        }).start();
    }
}

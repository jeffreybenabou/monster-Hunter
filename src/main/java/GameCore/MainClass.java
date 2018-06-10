package GameCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainClass extends JFrame  {

    public static Dimension dimension= Toolkit.getDefaultToolkit().getScreenSize();


    public MainClass(){


        setTheJFrame();
        addMouseListener(StaticVariables.world);
        setVisible(true);
    }

    public static void addTheMainMenu() {
        StaticVariables.mainClass.add(StaticVariables.mainMenu);
        StaticVariables.mainMenu.setVisible(true);

        removeTheWorld();

    }
    public static void removeTheMainMenu(){
        StaticVariables.mainClass.repaint();
        StaticVariables.mainMenu.setVisible(false);


    }
    public static void addTheWorld(){
        try{
            StaticVariables.mainClass.add(StaticVariables.gamePanel);
            StaticVariables.mainClass.add(StaticVariables.world);

            StaticVariables.world.setVisible(true);
            StaticVariables.gamePanel.setVisible(true);
            removeTheMainMenu();
        }catch (NullPointerException e)
        {

        }

    }

    public static void removeTheWorld(){
        StaticVariables.mainClass.repaint();
        if(StaticVariables.world!=null)
        StaticVariables.world.setVisible(false);
        if(  StaticVariables.gamePanel!=null)
        StaticVariables.gamePanel.setVisible(false);


    }

    private void setTheJFrame() {
        setBounds(0,0,dimension.width,dimension.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
repaint();
    }



}

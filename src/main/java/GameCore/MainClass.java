package GameCore;

import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame  {

    public static Dimension dimension;
    public static int differenceX,differenceY;


    public MainClass(){


        getContentPane().setBackground(Color.black);
        dimension= Toolkit.getDefaultToolkit().getScreenSize();
        differenceX=(1366-dimension.width)/2;
        differenceY=(768-dimension.height)/2;


        setPreferredSize(new Dimension(1366+differenceX, 768+differenceY));
        setTheJFrame();
        addMouseListener(StaticVariables.world);
       StaticVariables. into=StaticVariables.imageLoader.loadImage("Photos/opening.png");
        Opening opening=new Opening();
        add(opening);
        setVisible(true);
    }

    public static void addTheMainMenu() {
        StaticVariables.mainClass.add(StaticVariables.mainMenu);
        StaticVariables.mainMenu.setVisible(true);
        removeTheWorld();

    }
    public static void removeTheMainMenu(){


        StaticVariables.mainMenu.setVisible(false);
        StaticVariables.mainClass.remove(StaticVariables.mainMenu);



    }
    public static void addTheWorld(){
        try{
            StaticVariables.mainClass.add(StaticVariables.gamePanel,0);
            StaticVariables.mainClass.add(StaticVariables.world,1);

            StaticVariables.world.setVisible(true);
            StaticVariables.gamePanel.setVisible(true);

            removeTheMainMenu();
        }catch (NullPointerException e)
        {

        }

    }

    public static void removeTheWorld(){

        if(StaticVariables.world!=null)
        StaticVariables.world.setVisible(false);
        if(  StaticVariables.gamePanel!=null)
        StaticVariables.gamePanel.setVisible(false);


    }

    private void setTheJFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(0,0,dimension.width,dimension.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);


    }



}

package GameCore;

import javax.swing.*;
import java.awt.*;

public class Key extends GameObject {

    private Image image;
    private boolean keyActive=false,keyReachToDestination=false;
    public static boolean key1=false,key2=false,key3=false;


    public Key(){
        addTheKeyToWorld();
        setBackground(new Color(0,0,0,0));
        setOpaque(true);
    }
    public void addTheKeyToWorld(){
        switch (StaticVariables.level)
        {
            case 1:
            {
                World.userInProgressToOpenHouse=true;
                key1=true;
                image=StaticVariables.key1;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));

                break;
            }
            case 2:
            {
                World.userInProgressToOpenHouse=true;

                key2=true;
                image=StaticVariables.key2;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));

                break;
            }
            case 3:
            {
                World.userInProgressToOpenHouse=true;

                key3=true;
                image=StaticVariables.key3;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));
                break;
            }
        }
        setBounds(500,200,getIcon().getIconWidth(),getIcon().getIconHeight());
    }

    public static void addKeyToBag(int level){
        switch (level)
        {
            case 1:
            {
                StaticVariables.gamePanel.getBag().getKey1().setIcon(new ImageIcon(StaticVariables.key1.getScaledInstance(StaticVariables.gamePanel.getWidth_(),StaticVariables.gamePanel.getWidth_(),0)));

                break;
            }
            case 2:
            {
                StaticVariables.gamePanel.getBag().getKey2().setIcon(new ImageIcon(StaticVariables.key2.getScaledInstance(StaticVariables.gamePanel.getWidth_(),StaticVariables.gamePanel.getWidth_(),0)));

                break;
            }
            case 3:
            {

                StaticVariables.gamePanel.getBag().getKey3().setIcon(new ImageIcon(StaticVariables.key3.getScaledInstance(StaticVariables.gamePanel.getWidth_(),StaticVariables.gamePanel.getWidth_(),0)));

                break;
            }
        }
    }

    public static void removeTheKeyLiseners(){
        switch (StaticVariables.level)
        {
            case 1:
            {
                StaticVariables.gamePanel.getBag().getKey1().removeMouseListener(StaticVariables.world);
                break;
            }
            case 2 :
            {
                StaticVariables.gamePanel.getBag().getKey2().removeMouseListener(StaticVariables.world);
                break;
            }
            case 3 :
            {
                StaticVariables.gamePanel.getBag().getKey3().removeMouseListener(StaticVariables.world);
                break;
            }
        }
    }

    public static void changeTheKeyState(){


                    if(StaticVariables.level==1)

                        StaticVariables.gamePanel.getBag().getKey1().addMouseListener(StaticVariables.world);


                    if(StaticVariables.level==2)

                        StaticVariables.gamePanel.getBag().getKey2().addMouseListener(StaticVariables.world);


                    if(StaticVariables.level==3)

                        StaticVariables.gamePanel.getBag().getKey3().addMouseListener(StaticVariables.world);

    }

    public void moveTheKey() {


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!keyReachToDestination) {

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try
                    {
                        setIcon(new ImageIcon(image.getScaledInstance(getWidth() - 2, getHeight() - 2, 4)));
                        setLocation(getX() - 8, getY() - 3);


                        if (getBounds().intersects(StaticVariables.gamePanel.getMoneyIcon().getBounds()) && !keyActive) {
                            new Thread(new Runnable() {
                                public void run() {
                                    keyActive = true;
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    keyReachToDestination = true;
                                        setVisible(false);
                                        changeTheKeyState();
                                        addKeyToBag(StaticVariables.level);

                                        removeFromWorld();

                                }
                            }).start();
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }







    }

    private void removeFromWorld() {
        StaticVariables.gamePanel.remove(this);
    }


}

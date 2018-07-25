package GameCore;

import javax.swing.*;
import java.awt.*;

public class Key extends GameObject {

    private Image image;
    private boolean keyActive=false,keyReachToDestination=false;


    public Key(){
        addTheKeyToWorld();
    }
    public void addTheKeyToWorld(){
        switch (StaticVariables.level)
        {
            case 1:
            {

                image=StaticVariables.key1;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));

                break;
            }
            case 2:
            {
                image=StaticVariables.key2;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));

                break;
            }
            case 3:
            {
                image=StaticVariables.key3;
                setIcon(new ImageIcon(image.getScaledInstance(200,200,4)));
                break;
            }
        }
        setBounds(500,200,getIcon().getIconWidth(),getIcon().getIconHeight());
    }

    public void addKeyToBag(){
        switch (StaticVariables.level)
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
                StaticVariables.gamePanel.getBag().getKey3().setIcon(new ImageIcon(StaticVariables.key3.getScaledInstance(StaticVariables.gamePanel.getWidth(),StaticVariables.gamePanel.getHeight(),0)));

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

        new Thread(new Runnable() {
            public void run() {
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
                    setIcon(new ImageIcon(image.getScaledInstance(getWidth() - 2, getHeight() - 2, 4)));
                    setBounds(getX() - 9, getY() - 4, getIcon().getIconWidth(), getIcon().getIconHeight());
                    if (getBounds().intersects(getBounds()) && !keyActive) {
                        new Thread(new Runnable() {
                            public void run() {
                                keyActive = true;
                                try {
                                    Thread.sleep(1000);
                                    keyReachToDestination = true;
                                    setVisible(false);
                                    changeTheKeyState();
                                    addKeyToBag();
                                    removeFromWorld();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }

            }
        }).start();

        setVisible(true);


    }

    private void removeFromWorld() {
        StaticVariables.gamePanel.remove(this);
    }


}

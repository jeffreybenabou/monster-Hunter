package GameCore;

import javax.swing.*;
import java.awt.*;

public class Key extends GameObject {


    public Key(){
        addTheKeyToWorld();
    }
    public void addTheKeyToWorld(){
        switch (StaticVariables.level)
        {
            case 1:
            {

                setIcon(new ImageIcon(StaticVariables.key1));
                break;
            }
            case 2:
            {
                setIcon(new ImageIcon(StaticVariables.key2));

                break;
            }
            case 3:
            {
                setIcon(new ImageIcon(StaticVariables.key3));
                break;
            }
        }
        setBounds(500,200,getIcon().getIconWidth(),getIcon().getIconHeight());
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
                    {
                        StaticVariables.gamePanel.getBag().getKey1().setIcon(new ImageIcon(StaticVariables.key1.getScaledInstance(StaticVariables.gamePanel.getBag().getKey1().getWidth(),StaticVariables.gamePanel.getBag().getKey1().getHeight(),Image.SCALE_SMOOTH)));
                        StaticVariables.gamePanel.getBag().getKey1().addMouseListener(StaticVariables.world);
                    }

                    if(StaticVariables.level==2)
                    {
                        StaticVariables.gamePanel.getBag().getKey2().setIcon(new ImageIcon(StaticVariables.key2.getScaledInstance(StaticVariables.gamePanel.getBag().getKey2().getWidth(),StaticVariables.gamePanel.getBag().getKey2().getHeight(),Image.SCALE_SMOOTH)));
                        StaticVariables.gamePanel.getBag().getKey2().addMouseListener(StaticVariables.world);
                    }

                    if(StaticVariables.level==3)
                    {
                        StaticVariables.gamePanel.getBag().getKey3().setIcon(new ImageIcon(StaticVariables.key3.getScaledInstance(StaticVariables.gamePanel.getBag().getKey3().getWidth(),StaticVariables.gamePanel.getBag().getKey3().getHeight(),Image.SCALE_SMOOTH)));
                        StaticVariables.gamePanel.getBag().getKey3().addMouseListener(StaticVariables.world);
                    }







    }


}

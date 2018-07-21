package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;

public class Key extends GameObject {


    public static boolean firstKey=false,secondKey=false,thirdKey=false;
    public Key(){
        addTheKeyToWorld();
    }
    public void addTheKeyToWorld(){
        switch (StaticVariables.level)
        {
            case 1:
            {

                setIcon(new ImageIcon(StaticVariables.key1));
                setBounds(500,200,getIcon().getIconWidth(),getIcon().getIconHeight());
                break;
            }
        }
    }
    public void changeKeyStatusWhenReachTheHouse(){

        switch (StaticVariables.level)
        {
            case 1:
            {

                firstKey=true;
                break;
            }
        }
    }

}

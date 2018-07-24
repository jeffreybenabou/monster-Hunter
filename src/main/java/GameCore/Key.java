package GameCore;

import javax.swing.*;

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


}

package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class House extends GameObject {

    public House(int houseNumber){

        pickHouse(houseNumber);

    }

    private void pickHouse(int houseNumber) {
        switch (houseNumber)
        {
            case 0:
            {
//                down left
                setName(""+1);
                setIcon(new ImageIcon(StaticVariables.houseNum1));
                setBounds(0, StaticVariables.world.getHeight() - 1200,StaticVariables.houseNum1.getWidth(null),StaticVariables.houseNum1.getHeight(null));
                break;
            }
            case 1:
            {
//                left up
                setName(""+2);
                setIcon(new ImageIcon(StaticVariables.houseNum2));
                setBounds(0, 0,StaticVariables.houseNum2.getWidth(null),StaticVariables.houseNum2.getHeight(null));
                break;
            }
            case 2:
            {
//                right up
                setName(""+3);
                setIcon(new ImageIcon(StaticVariables.houseNum3));
                setBounds(StaticVariables.world.getWidth() - 1000, 0,StaticVariables.houseNum3.getWidth(null),StaticVariables.houseNum3.getHeight(null));
                break;
            }

        }
    }
}

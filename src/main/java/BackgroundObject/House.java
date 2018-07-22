package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;
import java.awt.*;

public class House extends GameObject {

    private int houseNumber;
    private JLabel houseEntrance;
    public House(int houseNumber){
        this.houseNumber=houseNumber;
        pickHouse(houseNumber);
        addHouseEntrance();

    }

    private void addHouseEntrance(){
        houseEntrance=new JLabel();
        houseEntrance.setVisible(false);
        switch (houseNumber)
        {
            case 0:
            {


                houseEntrance.setIcon(new ImageIcon(StaticVariables.carpet.getScaledInstance(400,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getWidth(),getHeight(),getIcon().getIconWidth(),getIcon().getIconHeight());
                StaticVariables.world.add(houseEntrance);
                break;
            }
            case 1:
            {
                houseEntrance.setIcon(new ImageIcon(StaticVariables.carpet.getScaledInstance(400,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getX()-getWidth(),getY()+300,getIcon().getIconWidth(),getIcon().getIconHeight());
                StaticVariables.world.add(houseEntrance);
               break;
            }
            case 2:
            {
                houseEntrance.setIcon(new ImageIcon(StaticVariables.carpet.getScaledInstance(400,200,Image.SCALE_SMOOTH)));
                houseEntrance.setBounds(getWidth(),getHeight(),getIcon().getIconWidth(),getIcon().getIconHeight());
                StaticVariables.world.add(houseEntrance);
                break;
            }
        }

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

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public JLabel getHouseEntrance() {
        return houseEntrance;
    }

    public void setHouseEntrance(JLabel houseEntrance) {
        this.houseEntrance = houseEntrance;
    }
}

package BackgroundObject;

import GameCore.StaticVariables;

import javax.swing.*;

public class GroundCrack extends JLabel {


    public GroundCrack(int x,int y){

        setIcon(new ImageIcon(StaticVariables.crack));
        setBounds(x,y,getIcon().getIconWidth(),getIcon().getIconHeight());


    }
}

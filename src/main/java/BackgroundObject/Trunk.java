package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class Trunk extends GameObject {


    public Trunk(){
        setIcon(new ImageIcon(StaticVariables.trunk.getScaledInstance(200,150,0)));
        setSize(200,150);

    }
}

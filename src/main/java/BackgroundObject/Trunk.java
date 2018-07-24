package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class Trunk extends GameObject {


    public Trunk(){
        setIcon(new ImageIcon(StaticVariables.trunk));
        setSize(200,150);

    }
}

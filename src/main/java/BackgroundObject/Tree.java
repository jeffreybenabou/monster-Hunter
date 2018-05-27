package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class Tree extends GameObject {

    public Tree(){
        setIcon(new ImageIcon(StaticVariables.tree.getScaledInstance(200,400,0)));
        setSize(200,400);
    }
}

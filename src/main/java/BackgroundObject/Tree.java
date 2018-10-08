package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class Tree extends GameObject {

    private static ImageIcon imageIcon=new ImageIcon(StaticVariables.tree);
    public Tree(){
        setIcon(imageIcon);
        setSize(400,600);
    }
}

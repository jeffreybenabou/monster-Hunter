package BackgroundObject;

import GameCore.GameObject;
import GameCore.StaticVariables;

import javax.swing.*;

public class Rock extends GameObject {

    public Rock()
    {
        setIcon(new ImageIcon(StaticVariables.rock.getScaledInstance(StaticVariables.rock.getWidth(null)/2,StaticVariables.rock.getHeight(null)/2,0)));
        setSize(getIcon().getIconWidth(),getIcon().getIconHeight());
    }
}

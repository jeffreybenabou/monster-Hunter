package GameCore;





import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {

    private  Border blackline;
    private ArrayList<JLabel> aghost=new ArrayList<JLabel>();
    public MiniMap(){
        blackline = BorderFactory. createRaisedBevelBorder();

        setBorder(blackline);
        new Thread(new Runnable() {
            public void run() {
                while (StaticVariables.worldBackGround==null)
                {
                    setBounds(0,StaticVariables.mainClass.getHeight()-160,250,160);

                }
                setIcon(new ImageIcon(StaticVariables.worldBackGround.getScaledInstance(getWidth(),getHeight(),0)));

            }

        }).start();



    }

    public void addTheGhostLocationToMap() {
        for (int i = 0; i <StaticVariables.world.getGhostArrayList().size() ; i++) {
            JLabel jLabel=new JLabel();
            jLabel.setBounds(20,20,10,10);
            jLabel.setBackground(Color.RED);
            jLabel.setOpaque(true);
            add(jLabel);
            aghost.add(jLabel);
        }
        while (StaticVariables.world.isVisible()){
            for (int i = 0; i <StaticVariables.world.getGhostArrayList().size() ; i++) {
                int x;
                int y;
                y=StaticVariables.world.getGhostArrayList().get(i).getLocation().y/32;
                x=StaticVariables.world.getGhostArrayList().get(i).getLocation().x/50;
                aghost.get(i).setLocation(x,y);

            }
        }
    }
}

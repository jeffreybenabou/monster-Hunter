package GameCore;





import Objects.Ghost;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {

    private  Border blackline;
    public static ArrayList<JLabel> aghost=new ArrayList<JLabel>();

    public MiniMap(){
        blackline = BorderFactory. createRaisedBevelBorder();
        setBorder(blackline);
        setBounds(0,StaticVariables.mainClass.getHeight()-160,250,160);
        setBackground(Color.black);


        new Thread(new Runnable() {
            public void run() {
                addTheMainPlayerLocationToMap();
                while (true)
                {
                    for (int i = 0; i <aghost.size() ; i++) {
                        try{
                            if(!StaticVariables.world.getGhostArrayList().get(i).getLifeBar().isAlive())
                            {
                                remove(aghost.get(i));
                                aghost.get(i).setVisible(false);
                            }
                            else
                            aghost.get(i).setLocation(StaticVariables.world.getGhostArrayList().get(i).getX()/40,StaticVariables.world.getGhostArrayList().get(i).getY()/64);

                        }catch (IndexOutOfBoundsException e)
                        {

                        }

                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if(World.ghostArrayList.size()==0&&Ghost.notTheFirstGhost)
                    {

                        StaticVariables.level++;
                        aghost.clear();
                        StaticVariables.world.addGhost();
                    }

                }
            }
        }).start();



    }

    public void addTheMainPlayerLocationToMap(){
        new Thread(new Runnable() {
            public void run() {
                JLabel jLabel=new JLabel();
                jLabel.setBounds(20,20,5,5);
                jLabel.setBackground(Color.blue);
                jLabel.setOpaque(true);
                add(jLabel);
                while (true){
                    int x;
                    int y;
                    try{
                        y=StaticVariables.mainPlayer.getLocation().y/64;
                        x=StaticVariables.mainPlayer.getLocation().x/40;
                        jLabel.setLocation(x,y);
                        Thread.sleep(1000);
                    }catch (NullPointerException e)
                    {

                        addTheMainPlayerLocationToMap();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }

    public void addTheGhostLocationToMap(int position,Point location) {

            JLabel jLabel=new JLabel();
            jLabel.setBounds(location.x,location.y,5,5);
            jLabel.setBackground(Color.RED);
            jLabel.setOpaque(true);
        jLabel.setName(""+position);
            add(jLabel);
            aghost.add(jLabel);







    }
}

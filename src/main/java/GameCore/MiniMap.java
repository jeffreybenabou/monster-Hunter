package GameCore;





import Objects.Ghost;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {

    private Thread thread;
    private  Border blackline;
    public static ArrayList<JLabel> aghost=new ArrayList<JLabel>();

    public MiniMap(){
        blackline = BorderFactory. createRaisedBevelBorder();
        setBorder(blackline);
        setBounds(10,StaticVariables.mainClass.getHeight()-170,250,160);
        setBackground(Color.black);


        new Thread(new Runnable() {
            public void run() {
                addTheMainPlayerLocationToMap();
                while (true)
                {
                    // TODO: 03/07/2018 fix the mini map
                    for (int i = 0; i <aghost.size() ; i++) {
                        try{
                            if(!StaticVariables.world.getGhostArrayList().get(i).getLifeBar().isAlive())
                            {
                                remove(aghost.get(i));
                                aghost.get(i).setVisible(false);

                            }
                            else
                            aghost.get(i).setLocation(StaticVariables.world.getGhostArrayList().get(i).getX()/20,StaticVariables.world.getGhostArrayList().get(i).getY()/32);

                        }catch (IndexOutOfBoundsException e)
                        {
                            e.printStackTrace();
                            break;

                        }

                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                    if(World.ghostArrayList.size()==0&&Ghost.notTheFirstGhost)
                    {


                        // TODO: 02/07/2018 add the key to the world
                        thread=new Thread(new Runnable() {
                            public void run() {
                                Ghost.numberOfDeadGhost=0;
                                StaticVariables.level++;
                                aghost.clear();
                                StaticVariables.world.addGhost();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                       if(!thread.isAlive())
                       {
                           thread.start();
                       }
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
                        y=StaticVariables.mainPlayer.getLocation().y/32;
                        x=StaticVariables.mainPlayer.getLocation().x/20;
                        jLabel.setLocation(x,y);
                        Thread.sleep(200);
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

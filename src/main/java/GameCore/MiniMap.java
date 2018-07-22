package GameCore;





import ImageHandel.ImageLoader;
import Objects.Ghost;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {
    boolean changeLevel = false;
    private boolean loadFuncation=false;
    private  Border blackline;
    private Key key;
    public static ArrayList<JLabel> aghost=new ArrayList<JLabel>();

    public MiniMap() {
        blackline = BorderFactory.createRaisedBevelBorder();
        setBorder(blackline);
        setBounds(10, StaticVariables.mainClass.getHeight() - 170, 250, 160);
        setBackground(Color.black);

    }

    public void addActionOfMiniMap() {
        new Thread(new Runnable() {
            public void run() {
                loadFuncation = false;
                addTheMainPlayerLocationToMap();
                while (!loadFuncation) {


                    // TODO: 03/07/2018 fix the mini map
                    for (int i = 0; i < StaticVariables.world.getGhostArrayList().size(); i++) {
                        try {

                            if (StaticVariables.world.getGhostArrayList().get(i).isVisible())
                                aghost.get(i).setLocation(StaticVariables.world.getGhostArrayList().get(i).getX() / 20, StaticVariables.world.getGhostArrayList().get(i).getY() / 32);
                            else
                                aghost.get(i).setVisible(false);


                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                            break;

                        } catch (NullPointerException e) {
                            e.printStackTrace();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if(Ghost.notTheFirstGhost)
                    for (Ghost ghost : World.ghostArrayList) {
                        if (ghost.isVisible()) {
                            changeLevel = false;
                            break;
                        } else
                            changeLevel = true;

                    }
                    if(changeLevel)
                        break;



                }
                levelUp();

            }
        }).start();
    }


    private void levelUp() {

        if (!World.ghostAreBeingAdd) {

            if (changeLevel && Ghost.notTheFirstGhost) {
                key=new Key();
                StaticVariables.gamePanel.add(key);
                for (JLabel label:aghost) {
                    label.setVisible(false);
                }
                aghost.clear();

                while (StaticVariables.gamePanel.getMoneyIcon().getX()+100<key.getX())
                {

                    if(key.getWidth()>=100)
                    {
                        key.setBounds(key.getX()-2,key.getY()-1,key.getWidth()-5,key.getHeight()-5);
                        key.setIcon(new ImageIcon(StaticVariables.key1.getScaledInstance(key.getWidth(),key.getHeight(),0)));
                    }
                    else
                        key.setBounds(key.getX()-2,key.getY()-1,key.getWidth(),key.getHeight());


                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                key.setVisible(false);

                // TODO: 21/07/2018 add the key to the bag
//                Ghost.numberOfDeadGhost = 0;


                /*StaticVariables.level++;


                try {
                    StaticVariables.world.changeLevel();
                    loadFuncation=true;
                    Thread.sleep(4000);

                    addActionOfMiniMap();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


            }


        }
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

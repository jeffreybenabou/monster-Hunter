package GameCore;





import Objects.Ghost;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {
    boolean changeLevel = false;
    private JLabel mainPlayerLabel;
    private boolean loadFuncation=false;
    private  Border blackline;
    private Key key;
    public static ArrayList<JLabel> aghost=new ArrayList<JLabel>();
    public static ArrayList<JLabel> house=new ArrayList<JLabel>();

    public MiniMap() {
        setBlackline(BorderFactory.createRaisedBevelBorder());
        setBorder(blackline);
        setBounds(10, StaticVariables.mainClass.getHeight() - 170, 250, 160);
        new Thread(new Runnable() {
                    public void run() {
                    try
                    {
                        setIcon(new ImageIcon(StaticVariables.worldBackGround.getScaledInstance(getWidth(),getHeight(),4)));

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        setBackground(Color.black);
        setTheMainPlayerLabel();

    }

    public void setTheMainPlayerLabel(){
         mainPlayerLabel=new JLabel();
        mainPlayerLabel.setBounds(20,20,5,5);
        mainPlayerLabel.setBackground(Color.blue);
        mainPlayerLabel.setOpaque(true);
        add(mainPlayerLabel);
    }
    public void addActionOfMiniMap() {


        new Thread(new Runnable() {
            public void run() {
                setLoadFuncation(false);
                setChangeLevel(false);


                new Thread(new Runnable() {
                    public void run() {
                        while (true)
                        {
                            addTheMainPlayerLocationToMap();
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                while (!isLoadFuncation()) {

                    addTheHouseLocationToMap();
                    addGhostToMiniMap();

                    try {
                        Thread.sleep(10);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    if(Ghost.notTheFirstGhost)
                    for (Ghost ghost : StaticVariables.world.getGhostArrayList()) {
                        if (ghost.isVisible()) {
                            setChangeLevel(false);
                            break;
                        } else
                            setChangeLevel(true);


                    }


                    if(isChangeLevel())
                        break;



                }

                levelUp();

            }
        }).start();
    }

    private void addGhostToMiniMap() {
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
    }

    private void addTheHouseLocationToMap() {
        if(Ghost.notTheFirstGhost)
        for (int i = 0; i < house.size(); i++) {
            try {
                if(i==StaticVariables.level-1)
                {
                    house.get(i).setIcon(new ImageIcon(StaticVariables.houseIconChooseMiniMap));
                }
                else
                    house.get(i).setIcon(new ImageIcon(StaticVariables.houseIconMiniMap));

                    house.get(i).setVisible(true);
                    house.get(i).setBounds(StaticVariables.world.getHouseArrayList().get(i).getX() / 19+10, StaticVariables.world.getHouseArrayList().get(i).getY() / 32+5,house.get(i).getIcon().getIconWidth(),house.get(i).getIcon().getIconHeight());



            } catch (IndexOutOfBoundsException e) {

                break;

            } catch (NullPointerException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void levelUp() {

        if (!StaticVariables.world.isGhostAreBeingAdd()) {

            if (isChangeLevel() && Ghost.notTheFirstGhost) {
                new Thread(new Runnable() {
                    public void run() {
                        setKey(new Key());
                        getKey().setVisible(true);
                        StaticVariables.gamePanel.add(getKey());
                        for (JLabel label:getAghost()) {
                            label.setVisible(false);
                        }
                        getAghost().clear();


                        getKey().moveTheKey();
                        Ghost.numberOfDeadGhost = 0;
                    }
                }).start();


            }


        }
    }

    public void addTheMainPlayerLocationToMap(){



                    int x;
                    int y;
                    try{
                        y=StaticVariables.mainPlayer.getLocation().y/32+10;
                        x=StaticVariables.mainPlayer.getLocation().x/20+10;
                        mainPlayerLabel.setLocation(x,y);

                    }catch (NullPointerException e)
                    {


                    }






    }

    public void addTheGhostLocationToMap(int position,Point location) {

            JLabel jLabel=new JLabel();
            jLabel.setBounds(location.x,location.y,5,5);
            jLabel.setBackground(Color.RED);
            jLabel.setOpaque(true);
        jLabel.setName(""+position);
            add(jLabel);
            getAghost().add(jLabel);







    }

    public boolean isChangeLevel() {
        return changeLevel;
    }

    public void setChangeLevel(boolean changeLevel) {
        this.changeLevel = changeLevel;
    }

    public boolean isLoadFuncation() {
        return loadFuncation;
    }

    public void setLoadFuncation(boolean loadFuncation) {
        this.loadFuncation = loadFuncation;
    }

    public Border getBlackline() {
        return blackline;
    }

    public void setBlackline(Border blackline) {
        this.blackline = blackline;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public static ArrayList<JLabel> getAghost() {
        return aghost;
    }

    public static void setAghost(ArrayList<JLabel> aghost) {
        MiniMap.aghost = aghost;
    }
}

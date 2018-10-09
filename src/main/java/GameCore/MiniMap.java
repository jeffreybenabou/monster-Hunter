package GameCore;


import ImageHandel.SpriteSheet;
import Objects.Ghost;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiniMap extends JLabel {

    public static JLabel mainPlayerLabel;
    public static ArrayList<JLabel> house=new ArrayList<JLabel>();


    private SpriteSheet pickHouse;
    private int pickHouseCounter=0;


    private Key key;


     MiniMap() {

        pickHouse = new SpriteSheet(StaticVariables.houseIconChooseMiniMap);
        setBounds(10, StaticVariables.mainClass.getHeight() - 170, 250, 160);

        setIcon(new ImageIcon(StaticVariables.worldBackGroundScaled.getScaledInstance(getWidth(), getHeight(), 4)));

        setBackground(Color.black);
        new Thread(new Runnable() {
            public void run() {
//noinspection InfiniteLoopStatement

                while (true)
                {
                    addTheHouseLocationToMap();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }

    public static void setTheMainPlayerLabel(int x,int y){
         mainPlayerLabel=new JLabel();
        mainPlayerLabel.setBounds(x,y,5,5);
        mainPlayerLabel.setBackground(Color.blue);
        mainPlayerLabel.setOpaque(true);

    }





        public static JLabel  addGhostToMiniMap(int x,int y) {
            JLabel label=new JLabel();
            label.setBounds(x,y,5,5);
            label.setBackground(Color.red);
            label.setOpaque(true);
            return label;





    }

    private void addTheHouseLocationToMap() {
        if(Ghost.notTheFirstGhost)
        for (int i = 0; i < house.size(); i++) {
            try {
                if(i==StaticVariables.level-1)
                {
                    if(pickHouseCounter*25<pickHouse.getSheet().getRaster().getWidth())
                    house.get(i).setIcon(new ImageIcon(pickHouse.crop(pickHouseCounter*24,0,24,24)));
                    else
                        pickHouseCounter=0;
                    pickHouseCounter++;
                }
                else
                    house.get(i).setIcon(new ImageIcon(StaticVariables.houseIconMiniMap));
                    house.get(i).setVisible(true);
                    house.get(i).setBounds(StaticVariables.world.getHouseArrayList().get(i).getX() / 19+10, StaticVariables.world.getHouseArrayList().get(i).getY() / 32+5,house.get(i).getIcon().getIconWidth(),house.get(i).getIcon().getIconHeight());



            }  catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
    }



    public static void addTheMainPlayerLocationToMap(int xPlayer,int yPlayer){



                    int x;
                    int y;
                    try{
                        y=yPlayer/32+3;
                        x=xPlayer/20+5;

                        mainPlayerLabel.setLocation(x,y);

                    }catch (NullPointerException e)
                    {

                        e.printStackTrace();

                    }






    }

     void addTheGhostLocationToMap(int position,Point location) {

        JLabel jLabel = new JLabel();
        jLabel.setBounds(location.x, location.y, 5, 5);
        jLabel.setBackground(Color.RED);
        jLabel.setOpaque(true);
        jLabel.setName("" + position);
        add(jLabel);
    }







     Key getKey() {
        return key;
    }

     void setKey(Key key) {
        this.key = key;
    }

}

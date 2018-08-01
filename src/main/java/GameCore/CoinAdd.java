package GameCore;

import javax.swing.*;
import java.util.Random;

public class CoinAdd extends JLabel {


    private Random random;
    public CoinAdd(int x,int y)
    {
        addMouseListener(StaticVariables.world);
        random=new Random();
        setIcon(new ImageIcon(StaticVariables.coin.getScaledInstance(70,70,4)));
        setBounds(x,y,getIcon().getIconWidth(),getIcon().getIconHeight());
        new Thread(new Runnable() {
            public void run() {
                removeFromWorld();
            }
        }).start();



    }

    public void changeTheSumOfMoney()
    {

        StaticVariables.sumOfMoney+=random.nextInt(250)+1;
        StaticVariables.gamePanel.getSumOfMoney().setText(""+StaticVariables.sumOfMoney);
    }


    private void removeFromWorld() {
        StaticVariables.world.add(this);

                try {
                    Thread.sleep(10000);
                    StaticVariables.world.remove(this);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


    }
}

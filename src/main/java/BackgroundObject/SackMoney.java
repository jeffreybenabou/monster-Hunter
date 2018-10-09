package BackgroundObject;

import GameCore.StaticVariables;
import sound.Sound;

import javax.swing.*;
import java.awt.*;

 class SackMoney extends JLabel {
    private Font myFont;
    private Sound moneySound;
    private SackMoney sackMoney;
     SackMoney(){
        setTheSound();
        setTheProperties();
        setTheAnimation();




    }

    private void setTheAnimation(){
        new Thread(new Runnable() {
            int x=50;
            public void run() {
                while (getY()!=0)
                {



                    myFont = new Font("Serif", Font.BOLD,x++);
                    setSize(getWidth()+7,getHeight()+7);
                    setFont(myFont);
                    repaint();
                    setLocation(getX(),getY()-5);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                moneySound.stopSound();


                StaticVariables.sumOfMoney+=250;
                StaticVariables.gamePanel.getSumOfMoney().setText(""+StaticVariables.sumOfMoney);
                setVisible(false);
                StaticVariables.gamePanel.remove(sackMoney);
            }
        }).start();
    }
    private void setTheProperties() {
        sackMoney=this;
        setForeground(Color.yellow);
        myFont = new Font("Serif", Font.BOLD, 50);
        setFont(myFont);
        setText("+250$");
        setVerticalTextPosition(JLabel.CENTER);
        setBounds(600,300,200,60);
        StaticVariables.gamePanel.add(this);
    }

    private void setTheSound() {
        moneySound =new Sound();
        moneySound.playSound(Sound.path.get(11));
        moneySound.setVolume(6);
    }
}

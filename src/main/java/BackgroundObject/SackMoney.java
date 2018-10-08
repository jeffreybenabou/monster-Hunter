package BackgroundObject;

import GameCore.StaticVariables;
import sound.Sound;

import javax.swing.*;
import java.awt.*;

public class SackMoney extends JLabel {
    private Font myFont;
    private Sound sound;
    private SackMoney sackMoney;
    public SackMoney(){
        sound=new Sound();
        sound.playSound(Sound.path.get(11));
        sound.setVolume(6);

        sackMoney=this;
        setForeground(Color.yellow);
         myFont = new Font("Serif", Font.BOLD, 50);
        setFont(myFont);
        setText("+250$");
        setVerticalTextPosition(JLabel.CENTER);
        setBounds(600,300,200,60);
        StaticVariables.gamePanel.add(this);
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
                sound.stopSound();


                StaticVariables.sumOfMoney+=250;
                StaticVariables.gamePanel.getSumOfMoney().setText(""+StaticVariables.sumOfMoney);
                setVisible(false);
                StaticVariables.gamePanel.remove(sackMoney);
            }
        }).start();


    }
}

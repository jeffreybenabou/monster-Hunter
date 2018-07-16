package GameCore;



import Objects.Ghost;
import Objects.MainPlayer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class GamePanel extends JLabel {


    private JLabel lifeBar;
    private JLabel levelLabel;
    private JLabel shopIcon;
    private JLabel settingLabel;
    private JLabel moneyIcon;
    private JProgressBar jProgressBar;

    private Font font=new Font("Serif", Font.BOLD, 20);

    public GamePanel (){
        setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
        addLifeBar();
        addLevelLabel();
        addShopIcon();
        addSettingIcon();
        addMoneyLabel();
        addSpacialAttack();

    }
    private void addSpacialAttack() {
        JLabel jLabel=new JLabel();
        jLabel.setBounds(50,0,345,11);
        jLabel.setForeground(Color.black);
        jProgressBar=new JProgressBar();
        jProgressBar.  setUI(new BasicProgressBarUI() {
            protected Color getSelectionBackground() { return Color.white; }
            protected Color getSelectionForeground() { return Color.white; }
        });
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setBackground(Color.gray);
        jProgressBar.setForeground(Color.yellow);
        jProgressBar.setBounds(222,75,345,11);
        jProgressBar.setFont(new Font("Verdana", Font.BOLD, 12));
        jProgressBar.add(jLabel);
        lifeBar.add(jProgressBar);
    }


    private void addLevelLabel(){
         levelLabel=new JLabel();
        levelLabel.setBounds(MainClass.dimension.width-220,MainClass.dimension.height-220,230,250);
        levelLabel.setIcon(new ImageIcon(StaticVariables.levelLabel.getScaledInstance(levelLabel.getWidth(),levelLabel.getHeight(),1)));

        JLabel label=new JLabel(""+1);
        label.setBounds(95,0,levelLabel.getWidth(),levelLabel.getHeight()-20);
        label.setForeground(Color.red);
        label.setFont(font);
        levelLabel.add(label);
        add(levelLabel);
    }

    private void addShopIcon() {
        shopIcon = new JLabel();
        shopIcon.setBounds( MainClass.dimension.width-50, 0,50,50);
        shopIcon.setIcon(new ImageIcon(StaticVariables.shopIcon.getScaledInstance(shopIcon.getWidth(),shopIcon.getHeight(),0)));
        add(shopIcon);
    }

    private void addSettingIcon() {
        settingLabel = new JLabel();
        settingLabel.setBounds( MainClass.dimension.width-130, 0,50,50);
        settingLabel.setIcon(new ImageIcon(StaticVariables.settingLabel.getScaledInstance(settingLabel.getWidth(),settingLabel.getHeight(),0)));
        add(settingLabel);
    }

    private void addMoneyLabel() {
        moneyIcon = new JLabel();
        moneyIcon.setBounds(50,0,200,150);
        moneyIcon.setIcon(new ImageIcon(StaticVariables.moneyIcon.getScaledInstance(moneyIcon.getWidth(),moneyIcon.getHeight(),0)));

        add(moneyIcon);


    }

    private void addLifeBar() {
        lifeBar=new JLabel();
        lifeBar.setBounds(MainClass.dimension.width/4,0,MainClass.dimension.width/2,MainClass.dimension.height/7);
        lifeBar.setIcon(new ImageIcon(StaticVariables.lifePanel.getScaledInstance(lifeBar.getWidth(),lifeBar.getHeight(),0)));
        add(lifeBar);

        MainPlayer.life=new Life(MainPlayer.sumOfLife,null);

        MainPlayer.life.getjProgressBar().setBounds(108,35,543,12);
        MainPlayer.life.getjProgressBar().setBackground(Color.BLACK);
        MainPlayer.life.getjProgressBar().setForeground(Color.RED);
        lifeBar.add(MainPlayer.life.getjProgressBar());


    }


}

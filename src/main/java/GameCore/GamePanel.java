package GameCore;



import Objects.MainPlayer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JLabel {


    private JLabel lifeBar;
    private JLabel levelLabel;
    private JLabel shopIcon;
    private JLabel settingLabel;
    private JLabel moneyIcon;
    public GamePanel (){
        setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
        addLifeBar();
        addLevelLabel();
        addShopIcon();
        addSettingIcon();
        addMoneyLabel();
    }

    private void addLevelLabel(){
         levelLabel=new JLabel();
        levelLabel.setBounds(MainClass.dimension.width-150,MainClass.dimension.height-150,170,150);
        levelLabel.setIcon(new ImageIcon(StaticVariables.levelLabel.getScaledInstance(levelLabel.getWidth(),levelLabel.getHeight(),1)));
        levelLabel.setForeground(Color.red);
        levelLabel.setFont(new Font("Serif", Font.BOLD, 20));
        levelLabel.setHorizontalTextPosition(JLabel.CENTER);
        levelLabel.setText(""+ 1);
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
        lifeBar.add(MainPlayer.life.getjProgressBar());

    }


}

package GameCore;



import BackgroundObject.FootStep;
import Objects.MainPlayer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JLabel  {


    private JLabel lifeBar;
    private JLabel levelLabel;
    private JLabel shopIcon;
    private JLabel settingLabel;
    private JLabel moneyIcon;
    public static JProgressBar jProgressBar;

    JTextField jTextField=new JTextField(),jTextField1=new JTextField();

    private int width_ =100;

    private Font font=new Font("Serif", Font.BOLD, 20);
    private Bag bag;

    public GamePanel (){
        setBounds(0,0,MainClass.dimension.width,MainClass.dimension.height);
        addLifeBar();
        addLevelLabel();
        addShopIcon();
        addSettingIcon();
        addMoneyLabel();
        addSpacialAttack();

        setVisible(true);
/*
        jTextField.setBounds(100,300,100,30);
        jTextField1.setBounds(100,400,100,30);
        jTextField.addKeyListener(this);
        jTextField1.addKeyListener(this);
        add(jTextField);
        add(jTextField1);*/


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
        try
        {
            moneyIcon = new JLabel();
            moneyIcon.setBounds(50,0,200,150);
            moneyIcon.setIcon(new ImageIcon(StaticVariables.moneyIcon.getScaledInstance(moneyIcon.getWidth(),moneyIcon.getHeight(),0)));
            moneyIcon.addMouseListener(StaticVariables.world);
            add(moneyIcon);
            bag=new Bag();
            bag.addMouseListener(StaticVariables.world);
            add(bag);
        }catch (Exception e)
        {
            e.printStackTrace();
        }



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


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        JTextField jTextField5 = (JTextField) e.getComponent();
        if (jTextField5.getText().matches("[0-9]+"))
        {

            if(e.getComponent().equals(jTextField))
            FootStep.x=Integer.parseInt(jTextField5.getText());
            else
            FootStep.y=Integer.parseInt(jTextField5.getText());
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public JLabel getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(JLabel lifeBar) {
        this.lifeBar = lifeBar;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(JLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public JLabel getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(JLabel shopIcon) {
        this.shopIcon = shopIcon;
    }

    public JLabel getSettingLabel() {
        return settingLabel;
    }

    public void setSettingLabel(JLabel settingLabel) {
        this.settingLabel = settingLabel;
    }

    public JLabel getMoneyIcon() {
        return moneyIcon;
    }

    public void setMoneyIcon(JLabel moneyIcon) {
        this.moneyIcon = moneyIcon;
    }

    public static JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public static void setjProgressBar(JProgressBar jProgressBar) {
        GamePanel.jProgressBar = jProgressBar;
    }


    public int getWidth_() {
        return width_;
    }

    public void setWidth_(int width_) {
        this.width_ = width_;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public void setjTextField1(JTextField jTextField1) {
        this.jTextField1 = jTextField1;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}
